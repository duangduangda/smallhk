package org.dean.duck.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.dean.duck.mybatis.mapper.BaseMapper;
import org.dean.duck.mybatis.model.Emp;

import java.io.IOException;
import java.io.Reader;
import java.util.Objects;

/**
 * Mybatis基本的流程:
 * 1. 读取配置文件
 * 2. 构建SqlSessionFactory
 * 3. 打开Sqlsession
 * 4. 获取mapper
 * 5. 执行事务操作
 * 6. 提交事务
 * 7. 释放资源
 */
public class StartProcess {
	public static void main(String[] args) {
		SqlSession sqlSession = null;
		try (Reader reader = Resources.getResourceAsReader("mybatis-config.xml")) {
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
			sqlSession = factory.openSession();
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
		} finally {
			if (Objects.nonNull(sqlSession)) {
				sqlSession.close();
			}
		}
	}
}
