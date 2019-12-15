package org.dean.duck.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.dean.duck.mybatis.mapper.BaseMapper;
import org.dean.duck.mybatis.model.Emp;

import java.io.IOException;
import java.io.Reader;

/**
 * mysql基本的流程
 */
public class StartProcess {
	public static void main(String[] args) {
		try (Reader reader = Resources.getResourceAsReader("mybatis-config.xml")) {
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
			SqlSession sqlSession = factory.openSession();
			BaseMapper baseMapper = sqlSession.getMapper(BaseMapper.class);
			baseMapper.insert(Emp.builder()
					.name("eric")
					.deg("engineering")
					.salary("20")
					.dept("uds")
					.build());
			sqlSession.commit();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
