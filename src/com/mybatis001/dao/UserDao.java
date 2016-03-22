package com.mybatis001.dao;

import java.util.List;

import com.mybatis001.bean.User;

public interface UserDao {
	
	public User findUserById(int id) throws Exception;
	
	public List<User> findUserList(String name) throws Exception;
	
	public Integer insertUser(User user) throws Exception;
	
	public void deleteUser(int id) throws Exception;
	
	public void updateUser(User user) throws Exception;
	
}
