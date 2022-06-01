package com.example.mallwork.Service;

import com.example.mallwork.Common.SverResponse;
import com.example.mallwork.Entity.User;
import com.example.mallwork.Vo.UserVo;

public interface UserService {
	/**
	 * 用户登录
	 * @param account
	 * @param password
	 * @return
	 */
	public SverResponse<User> doLogin(String account, String password);
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	public SverResponse<String> doRegister(User user);
	
	/**
	 * 信息校验验证
	 * @param str
	 * @param type
	 * @return
	 */
	public SverResponse<String> checkValidation(String str,String type) ;
	
	/**
	 * 根据用户名获得用户对象
	 * @param account
	 * @return
	 */
	public SverResponse<User> findUserByAccount(String account);
	/**
	 * 校验用户问题答案
	 * @param account
	 * @param question
	 * @param asw
	 * @return
	 */
	public SverResponse<String> checkUserAnswer(String account, String question, String asw);
	
	/**
	 * 重置密码
	 * @param userId
	 * @param password
	 * @return
	 */
	public SverResponse<String> resetPassword(Integer userId, String password);
	
	/**
	 * 更新用户信息
	 * @param userVo
	 * @return
	 */
	public SverResponse<User> updateUserInfo(UserVo userVo);
	
	/**
	 * 重设密码
	 * @param user
	 * @param newPassword
	 * @param oldPassword
	 * @return
	 */
	public SverResponse<String> updatePassword(User user, String newPassword, String oldPassword);
	/**
	 * 查询密码问题
	 * @param account
	 * @return
	 */
	public SverResponse<String> getUserQuestion(String account);
}
