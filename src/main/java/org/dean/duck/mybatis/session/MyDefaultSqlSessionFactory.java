package org.dean.duck.mybatis.session;

import org.dean.duck.mybatis.configuration.MyConfiguration;

/**
 * @description:
 * @author: dean
 * @create: 2019/07/16 18:13
 */
public class MyDefaultSqlSessionFactory implements MySqlSessionFactory {

	private final MyConfiguration configuration;

	public MyDefaultSqlSessionFactory(MyConfiguration configuration) {
		this.configuration = configuration;
	}


	@Override
	public MyConfiguration getConfiguration() {
		return configuration;
	}
}
