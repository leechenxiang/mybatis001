package com.test.mybatis001;


import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.mybatis001.bean.User;
import com.mybatis001.dao.UserDao;
import com.mybatis001.dao.impl.UserDaoImpl;

public class UserDaoImplTest {

	// 会话工厂
	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void setUp() throws Exception {
		// 加载配置文件
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);

		// 根据mytais的配置创建SqlSessionFactory
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void testFindUserById() throws Exception {
		UserDao userDao = new UserDaoImpl(sqlSessionFactory);
		User user = userDao.findUserById(1001);
//		System.out.println(user.toString());
		
		List<User> userList = userDao.findUserList("lee");
		
		if (!userList.isEmpty()) {
			for (User u : userList) {
				System.out.println(u.toString());
			}
		}
	}
	
	@Test
	public void insertUser() throws Exception {
		UserDao userDao = new UserDaoImpl(sqlSessionFactory);
		
		User u1 = new User("nathan.lee.salvatore", 18, "男");
		User u2 = new User("nathan", 22, "男");
		User u3 = new User("leechenxiang", 20, "男");
		User u4 = new User("李晨翔", 19, "男");
		
		userDao.insertUser(u1);
		userDao.insertUser(u2);
		userDao.insertUser(u3);
		userDao.insertUser(u4);
	}

	@Test
	public void deleteUser() throws Exception {
		UserDao userDao = new UserDaoImpl(sqlSessionFactory);
		userDao.deleteUser(1013);
	}
	
	@Test
	public void updateUser() throws Exception {
		UserDao userDao = new UserDaoImpl(sqlSessionFactory);
		User user = new User("李斯涵", 1, "女");
		user.setId(1001);
		userDao.updateUser(user);
	}
}
