package com.mybatis001.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.mybatis001.bean.User;
import com.mybatis001.dao.UserDao;

public class UserDaoImpl implements UserDao {

	// 注入SqlSessionFactory
	private SqlSessionFactory sqlSessionFactory;

	public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public User findUserById(int id) throws Exception {

		// 根据SqlSessionFactory创建SqlSession

		SqlSession sqlSession = sqlSessionFactory.openSession();

		// 通过sqlSession查询用户信息(发起数据库操作)
		// 第一个参数statement：指定mapper映射文件中statement的id，指定 时需要前边加上statement所属的命名空间
		// 第二个参数parameter，指定 输入参数
		// selectOne返回的是单条记录，如果select返回多条记录(list集合)，使用selectOne会报错
		// 根据映射文件中的resultType指定输出类型
		User user = sqlSession.selectOne("test.findUserById", id);
		return user;
	}

	@Override
	public List<User> findUserList(String name) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<User> userList = sqlSession.selectList("test.findUserList", name);
		return userList;
	}

	@Override
	public Integer insertUser(User user) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int lastUserId = sqlSession.insert("test.insertUser", user);
		
		sqlSession.commit();
		sqlSession.close();
		return lastUserId;
	}

	@Override
	public void deleteUser(int id) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.delete("test.deleteUser", id);
		sqlSession.commit();
		sqlSession.close();
	}

	@Override
	public void updateUser(User user) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.update("test.updateUser", user);
		sqlSession.commit();
		sqlSession.close();
	}

}
