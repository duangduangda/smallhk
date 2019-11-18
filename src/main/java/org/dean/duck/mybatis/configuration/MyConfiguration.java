package org.dean.duck.mybatis.configuration;


import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.type.TypeAliasRegistry;

/**
 * @description:
 * @author: dean
 * @create: 2019/07/15 11:45
 */
public class MyConfiguration {
	protected Environment environment;

	protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

	public MyConfiguration(Environment environment) {
		this();
		this.environment = environment;
	}

	public MyConfiguration() {
		typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);

		typeAliasRegistry.registerAlias("UNPOOLED", UnpooledDataSourceFactory.class);

	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public TypeAliasRegistry getTypeAliasRegistry() {
		return typeAliasRegistry;
	}
}
