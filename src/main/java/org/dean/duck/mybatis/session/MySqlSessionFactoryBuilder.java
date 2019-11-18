package org.dean.duck.mybatis.session;

import org.dean.duck.mybatis.xml.MyXMLConfigBuilder;

import java.io.Reader;

/**
 * @description:
 * @author: dean
 * @create: 2019/07/16 18:05
 */
public class MySqlSessionFactoryBuilder {
	public static MySqlSessionFactory build(Reader reader) {
		MyXMLConfigBuilder parse = new MyXMLConfigBuilder(reader);
		return new MyDefaultSqlSessionFactory(parse.parse());
	}
}
