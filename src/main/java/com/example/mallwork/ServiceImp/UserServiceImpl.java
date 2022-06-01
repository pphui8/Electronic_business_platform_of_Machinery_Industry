package com.example.mallwork.ServiceImp;

import com.example.mallwork.Common.SverResponse;
import com.example.mallwork.Dao.UserDao;
import com.example.mallwork.Entity.User;
import com.example.mallwork.Service.UserService;
import com.example.mallwork.Tools.ConstUtil;
import com.example.mallwork.Tools.MD5Util;
import com.example.mallwork.Tools.TokenCache;
import com.example.mallwork.Vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
	//创建actionuserdao对象
	@Autowired//自动装载对象
	private UserDao actionUserDao;
	@Override
	public SverResponse<User> doLogin(String account, String password) {
		// TODO Auto-generated method stub
		//1.判断用户名是否存在
		int rs = actionUserDao.checkUserByAccount(account);
		if (rs==0) {
			return SverResponse.createByErrorMessage("用户不存在");
		}
		//2.根据用户名与密码查找并且加密密码
		String md5pwd = MD5Util.MD5Encode(password,"utf-8",false);
		User user=actionUserDao.findUserByAccountAndPassword(account, md5pwd);

		//3.判断用户是否存在，并给出信息提示
		if (user==null) {
			return SverResponse.createByErrorMessage("密码错误！");
		}
		//置空密码
		user.setPassword(StringUtils.EMPTY);
		return SverResponse.createRespBySuccess("登录成功！",user);
	}
	
	/**
	 * 用户注册
	 */
	@Override
	public SverResponse<String> doRegister(User user) {
		//1.检查用户名是否存在
		SverResponse<String> resp =  checkValidation(user.getAccount(), ConstUtil.TYPE_ACCOUNT);
		if (!resp.isSuccess()) {
			return resp;
		}
		//2.检查邮箱是否被注册
		resp =  checkValidation(user.getEmail(), ConstUtil.TYPE_EMAIL);
		if (!resp.isSuccess()) {
			return resp;
		}
		//4.指定用户角色，通过前端注册的用户都为普通用户
		user.setRole(ConstUtil.Role.ROLE_CUSTOMER);
		//5.密码加密
		user.setPassword(MD5Util.MD5Encode(user.getPassword(),"utf-8", false));
		//6.执行dao层注册
		Date curDate = new Date();
		user.setCreate_time(curDate);
		user.setUpData_time(curDate);
		int rs = actionUserDao.insertUser(user);
		if (rs==0) {
			return SverResponse.createByErrorMessage("注册失败！");
		}
		return SverResponse.createRespBySuccessMessage("注册成功！");
	}
	/**
	 * 信息校验验证
	 */
	@Override
	public SverResponse<String> checkValidation(String str, String type) {
		//1.判断字符创长度type不为空
		//2.判断用户名是否已存在
		if (StringUtils.isNoneBlank(type)) {
			if (ConstUtil.TYPE_ACCOUNT.equals(type)) {
				int rs = actionUserDao.checkUserByAccount(str);
				if (rs>0) {
					return	SverResponse.createByErrorMessage("用户名已存在！");
				}
			}
		}
		//3.验证邮箱是否已存在
		if (StringUtils.isNoneBlank(type)) {
			if (ConstUtil.TYPE_EMAIL.equals(type)) {
				int rs = actionUserDao.checkUserByEamil(str);
				if (rs>0) {
					return	SverResponse.createByErrorMessage("所用邮箱已存在！");
				}
			}
		}
		//4.验证手机号是否已存在
		if (StringUtils.isNoneBlank(type)) {
			if (ConstUtil.TYPE_PHONE.equals(type)) {
				int rs = actionUserDao.checkUserByPhone(str);
				if (rs>0) {
					return	SverResponse.createByErrorMessage("所用手机号已存在！");
				}
			}
		}else {
			return SverResponse.createByErrorMessage("信息类别错误！");
		}
		return SverResponse.createRespBySuccessMessage("信息验证成功");
	}
	/**
	 * 通过用户名获取用户对象
	 */
	@Override
	public SverResponse<User> findUserByAccount(String account) {
		//1.通过用户名查找到用户
		User user = actionUserDao.findUserByAccount(account);
		if (user==null) {
			return SverResponse.createByErrorMessage("用户名错误！");
		}
		//2.密码置空
		user.setPassword(StringUtils.EMPTY);
		//3.安全问题答案置空
		user.setAsw(StringUtils.EMPTY);
		return SverResponse.createRespBySuccess(user);
	}
	/**
	 * 校验安全问题答案
	 */
	@Override
	public SverResponse<String> checkUserAnswer(String account, String question, String asw) {
		//1.获取校验结果
		int rs = actionUserDao.checkUserAnswer(account,question,asw);
		if (rs>0) {
			//2.答案正确，生成token
			String token = UUID.randomUUID().toString();
			//3.放入缓存
			TokenCache.setCacheData(TokenCache.PREFIX+account, token);
			return SverResponse.createRespBySuccessMessage(token);
		}
		return SverResponse.createByErrorMessage("问题答案错误!");
	}
	
	/**
	 * 重置密码
	 */
	@Override
	public SverResponse<String> resetPassword(Integer userId, String password) {
		//1.密码加密
		String pwd = MD5Util.MD5Encode(password, "utf-8", false);
		//2.获取user对象
		User user =actionUserDao.findUserById(userId);
		//3.更新密码
		user.setPassword(pwd);
		user.setUpData_time(new Date());
		int rs = actionUserDao.updateUserInfo(user);
		if (rs>0) {
			return SverResponse.createRespBySuccessMessage("密码修改成功！");
		}
		return SverResponse.createByErrorMessage("密码修改失败！");
	}
	/**
	 * 更新用户信息
	 */
	@Override
	public SverResponse<User> updateUserInfo(UserVo userVo) {
		//1.根据id获取user对象
		User updateUser = actionUserDao.findUserById(userVo.getId());
		//2.更新修改数据
		updateUser.setAccount(userVo.getAccount());
		updateUser.setEmail(userVo.getEmail());
		updateUser.setPhone(userVo.getPhone());
		updateUser.setUpData_time(new Date());
		updateUser.setAge(userVo.getAge());
		//3.判断男女
		if (userVo.getSex().equals("男")) {
			updateUser.setSex(1);
		}else {
			updateUser.setSex(0);
		}
		updateUser.setName(userVo.getName());
		int rs =actionUserDao.updateUserInfo(updateUser);
		if (rs>0) {
			return SverResponse.createRespBySuccess("用户信息修改成功", updateUser);
		}
		return SverResponse.createByErrorMessage("用户信息修改失败！");
	}
	/**
	 * 重设密码
	 */
	@Override
	public SverResponse<String> updatePassword(User user, String newPassword, String oldPassword) {
		//1.检测旧密码是否正确
		oldPassword = MD5Util.MD5Encode(oldPassword, "utf-8", false);
		int rs = actionUserDao.checkPassword(user.getAccount(),oldPassword);
		if (rs==0) {
			return SverResponse.createByErrorMessage("原始密码错误！");
		}
		//2.将新密码插入数据库
		newPassword = MD5Util.MD5Encode(newPassword, "utf-8", false);
		user.setPassword(newPassword);
		user.setUpData_time(new Date());
		rs = actionUserDao.updateUserInfo(user);
		if (rs>0) {
			return SverResponse.createRespBySuccessMessage("密码修改成功");
		}
		return SverResponse.createByErrorMessage("密码修改失败");
	}
	/**
	 * 查询密码问题
	 */
	@Override
	public SverResponse<String> getUserQuestion(String account) {
		//判断参数
		if (account==null) {
			return SverResponse.createByErrorMessage("参数错误！");
		}
		User user= actionUserDao.findUserByAccount(account);
		if(user==null) {
			return SverResponse.createByErrorMessage("用户名错误");
		}
		if(user.getQuestion().trim().equals("")||user.getQuestion()==null) {
			return SverResponse.createByErrorMessage("未设置密码问题!");
		}else {
			return SverResponse.createRespBySuccess(user.getQuestion());
		
		}
	}

}
