package org.dean.duck.mybatis.xml;

import org.apache.ibatis.builder.BuilderException;
import org.apache.ibatis.builder.xml.XMLMapperEntityResolver;
import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.dean.duck.mybatis.configuration.MyConfiguration;

import javax.sql.DataSource;
import java.io.Reader;
import java.sql.Connection;
import java.util.Properties;

/**
 * @description:
 * @author: dean
 * @create: 2019/07/16 19:21
 */
public class MyXMLConfigBuilder {
	private final MyConfiguration configuration;

	private boolean parsed;

	private final XPathParser parser;

	private String environment;

	public MyXMLConfigBuilder(Reader reader) {
		this(reader, null, null);
	}

	public MyXMLConfigBuilder(Reader reader, String environment, Properties properties) {
		this(new XPathParser(reader, true, properties, new XMLMapperEntityResolver()), environment, properties);
	}

	public MyXMLConfigBuilder(XPathParser xPathParser, String environment, Properties properties) {
//        super(new MyConfiguration());
		parsed = false;
		this.environment = environment;
		parser = xPathParser;
		configuration = new MyConfiguration();
	}


	public MyConfiguration parse() {
		if (parsed) {
			throw new BuilderException("Each XMLConfigBuilder can only be used once.");
		}
		parsed = true;
		parseConfiguration(parser.evalNode("/configuration"));
		return configuration;
	}

	private void parseConfiguration(XNode root) {
		try {
			environmentsElement(root.evalNode("environments"));
		} catch (Exception e) {
			throw new BuilderException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
		}
	}

	private void environmentsElement(XNode context) throws Exception {
		if (null != context) {
			if (null == environment) {
				environment = context.getStringAttribute("default");
			}

			for (XNode child : context.getChildren()) {
				String id = child.getStringAttribute("id");
				if (isSpecifiedEnvironment(id)) {
//                    TransactionFactory txFactory = transactionManagerElement(child.evalNode("transactionManager"));
					DataSourceFactory dsFactory = dataSourceElement(child.evalNode("dataSource"));
					DataSource dataSource = dsFactory.getDataSource();
					Environment.Builder environmentBuilder = new Environment.Builder(id)
							.transactionFactory(new TransactionFactory() {
								@Override
								public void setProperties(Properties properties) {

								}

								@Override
								public Transaction newTransaction(Connection connection) {
									return null;
								}

								@Override
								public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel transactionIsolationLevel, boolean b) {
									return null;
								}
							})
							.dataSource(dataSource);
//                            .transactionFactory(txFactory)
//                            .dataSource(dataSource);
					configuration.setEnvironment(environmentBuilder.build());
				}
			}
		}
	}

	private TransactionFactory transactionManagerElement(XNode context) throws Exception {
		if (context != null) {
			String type = context.getStringAttribute("type");
			Properties props = context.getChildrenAsProperties();
//            TransactionFactory factory = (TransactionFactory) resolveClass(type).newInstance();
			TransactionFactory factory = new JdbcTransactionFactory();
			factory.setProperties(props);
			return factory;
		}
		throw new BuilderException("Environment declaration requires a TransactionFactory.");
	}

	private DataSourceFactory dataSourceElement(XNode context) throws Exception {
		if (context != null) {
			String type = context.getStringAttribute("type");
			Properties props = context.getChildrenAsProperties();
			DataSourceFactory factory = (DataSourceFactory) resolveClass(type).newInstance();
			factory.setProperties(props);
			return factory;
		}
		throw new BuilderException("Environment declaration requires a DataSourceFactory.");
	}

	private boolean isSpecifiedEnvironment(String id) {
		if (environment == null) {
			throw new BuilderException("No environment specified.");
		} else if (id == null) {
			throw new BuilderException("Environment requires an id attribute.");
		} else if (environment.equals(id)) {
			return true;
		}
		return false;
	}

	protected <T> Class<? extends T> resolveClass(String alias) {
		if (alias == null) {
			return null;
		}
		try {
			return resolveAlias(alias);
		} catch (Exception e) {
			throw new BuilderException("Error resolving class. Cause: " + e, e);
		}
	}

	protected <T> Class<? extends T> resolveAlias(String alias) {
		return configuration.getTypeAliasRegistry().resolveAlias(alias);
	}
}
