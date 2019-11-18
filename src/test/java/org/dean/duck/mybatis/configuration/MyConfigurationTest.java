package org.dean.duck.mybatis.configuration;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.dean.duck.mybatis.session.MySqlSessionFactory;
import org.dean.duck.mybatis.session.MySqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @description: 配置项
 * @author: dean
 * @create: 2019/07/16 17:58
 */
public class MyConfigurationTest {

	@Test
	public void testConfiguration() {
		try (
				Reader reader = Resources.getResourceAsReader("mybatis-config.xml")) {
			MySqlSessionFactory sqlSessionFactory = MySqlSessionFactoryBuilder.build(reader);
			MyConfiguration configuration = sqlSessionFactory.getConfiguration();
			assertNotNull(configuration);
			Environment environment = configuration.getEnvironment();
			assertEquals("development", environment.getId());
			UnpooledDataSource dataSource = (UnpooledDataSource) environment.getDataSource();
			assertNotNull(dataSource);
			assertEquals("root", dataSource.getUsername());
			assertEquals("com.mysql.jdbc.Driver", dataSource.getDriver());
			assertEquals("jdbc:mysql://127.0.0.1/uds_data", dataSource.getUrl());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
