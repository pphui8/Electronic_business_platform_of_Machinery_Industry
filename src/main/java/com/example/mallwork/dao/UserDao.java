package com.example.mallwork.dao;

import com.example.mallwork.Entity.User;

public interface UserDao {
	/**
	 *
	 * @param account
	 * @return
	 */
	public int checkUserByAccount(String account);

	/**
	 *
	 * @param account
	 * @param password
	 * @return
	 */
	public User findUserByAccountAndPassword(String account, String password);

	/**
	 *
	 * @param email
	 * @return
	 */
	public int checkUserByEamil(String email);

	/**
	 *
	 * @param phone
	 * @return
	 */
	public int checkUserByPhone(String phone);

	/**
	 *
	 * @param user
	 * @return
	 */
	public int insertUser(User user);

	/**
	 *
	 * @param account
	 * @return
	 */
	public User findUserByAccount(String account);

	/**
	 *
	 * @param account
	 * @param question
	 * @param asw
	 * @return
	 */
	public int checkUserAnswer(String account, String question, String asw);

	/**
	 *
	 * @param userId
	 * @return
	 */
	public User findUserById(Integer userId);

	/**
	 *
	 * @param user
	 * @return
	 */
	public int updateUserInfo(User user);

	/**
	 *
	 * @param account
	 * @param oldPassword
	 * @return
	 */
	public int checkPassword(String account, String oldPassword);
}
