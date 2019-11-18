package org.dean.duck.mybatis.session;

import org.dean.duck.mybatis.configuration.MyConfiguration;

/**
 * @description:
 * @author: dean
 * @create: 2019/07/16 18:04
 */
public interface MySqlSessionFactory {

	/**
	 * 获取configuration对象
	 *
	 * @return
	 */
	MyConfiguration getConfiguration();
}
