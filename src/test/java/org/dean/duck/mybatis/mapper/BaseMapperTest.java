package org.dean.duck.mybatis.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.dean.duck.mybatis.model.Emp;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class BaseMapperTest {
	private static SqlSessionFactory sqlSessionFactory;

	@BeforeClass
	public static void setUp() {
		try (Reader reader = Resources.getResourceAsReader("mybatis-config.xml")) {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Test
	public void testJDBC() throws SQLException {
		assertNotNull(sqlSessionFactory);
		Configuration configuration = sqlSessionFactory.getConfiguration();
		assertNotNull(configuration);
		DataSource dataSource = configuration.getEnvironment().getDataSource();
		assertNotNull(dataSource);
		Connection connection = dataSource.getConnection();
		assertNotNull(connection);
		PreparedStatement ps = connection.prepareStatement("select * from emp");
		ResultSet resultSet = ps.executeQuery();
		assertNotNull(resultSet);
		while (resultSet.next()) {
			System.out.println(resultSet.getString(2));
		}
	}

	@Test
	public void testListEmp() {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			BaseMapper baseMapper = sqlSession.getMapper(BaseMapper.class);
			List<Emp> empList = baseMapper.list();
			assertNotNull(empList);
			assertEquals(12, empList.size());
			empList = sqlSession.selectList("list");
			assertNotNull(empList);
			assertEquals(12, empList.size());
		}
	}
}