package com.example.mallwork.Dao;

import com.example.mallwork.Entity.User;

public interface UserDao {

	public int checkUserByAccount(String account);

	public User findUserByAccountAndPassword(String account, String password);

	public int checkUserByEamil(String email);

	public int checkUserByPhone(String phone);

	public int insertUser(User user);

	public User findUserByAccount(String account);

	public int checkUserAnswer(String account, String question, String asw);

	public User findUserById(Integer userId);

	public int updateUserInfo(User user);

	public int checkPassword(String account, String oldPassword);
}
