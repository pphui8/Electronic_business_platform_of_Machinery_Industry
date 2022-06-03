package com.example.mallwork.Service.impl;

import com.example.mallwork.Common.SverResponse;
import com.example.mallwork.dao.UserDao;
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
public class UserServiceImp implements UserService {
	//����actionuserdao����
	@Autowired//�Զ�װ�ض���
	private UserDao actionUserDao;

	@Override
	public SverResponse<User> doLogin(String account, String password) {
		// TODO Auto-generated method stub
		//1.�ж��û����Ƿ����
		int rs = actionUserDao.checkUserByAccount(account);
		if (rs==0) {
			return SverResponse.createByErrorMessage("�û�������");
		}
		//2.�����û�����������Ҳ��Ҽ�������
		String md5pwd = MD5Util.MD5Encode(password,"utf-8",false);
		User user=actionUserDao.findUserByAccountAndPassword(account, md5pwd);

		//3.�ж��û��Ƿ���ڣ���������Ϣ��ʾ
		if (user==null) {
			return SverResponse.createByErrorMessage("�������");
		}
		//�ÿ�����
		user.setPassword(StringUtils.EMPTY);
		return SverResponse.createRespBySuccess("��¼�ɹ���",user);
	}
	
	/**
	 * �û�ע��
	 */
	@Override
	public SverResponse<String> doRegister(User user) {
		//1.����û����Ƿ����
		SverResponse<String> resp =  checkValidation(user.getAccount(), ConstUtil.TYPE_ACCOUNT);
		if (!resp.isSuccess()) {
			return resp;
		}
		//2.��������Ƿ�ע��
		resp =  checkValidation(user.getEmail(), ConstUtil.TYPE_EMAIL);
		if (!resp.isSuccess()) {
			return resp;
		}
		//4.ָ���û���ɫ��ͨ��ǰ��ע����û���Ϊ��ͨ�û�
		user.setRole(ConstUtil.Role.ROLE_CUSTOMER);
		//5.�������
		user.setPassword(MD5Util.MD5Encode(user.getPassword(),"utf-8", false));
		//6.ִ��dao��ע��
		Date curDate = new Date();
		user.setCreate_time(curDate);
		user.setUpData_time(curDate);
		int rs = actionUserDao.insertUser(user);
		if (rs==0) {
			return SverResponse.createByErrorMessage("ע��ʧ�ܣ�");
		}
		return SverResponse.createRespBySuccessMessage("ע��ɹ���");
	}
	/**
	 * ��ϢУ����֤
	 */
	@Override
	public SverResponse<String> checkValidation(String str, String type) {
		//1.�ж��ַ�������type��Ϊ��
		//2.�ж��û����Ƿ��Ѵ���
		if (StringUtils.isNoneBlank(type)) {
			if (ConstUtil.TYPE_ACCOUNT.equals(type)) {
				int rs = actionUserDao.checkUserByAccount(str);
				if (rs>0) {
					return	SverResponse.createByErrorMessage("�û����Ѵ��ڣ�");
				}
			}
		}
		//3.��֤�����Ƿ��Ѵ���
		if (StringUtils.isNoneBlank(type)) {
			if (ConstUtil.TYPE_EMAIL.equals(type)) {
				int rs = actionUserDao.checkUserByEamil(str);
				if (rs>0) {
					return	SverResponse.createByErrorMessage("���������Ѵ��ڣ�");
				}
			}
		}
		//4.��֤�ֻ����Ƿ��Ѵ���
		if (StringUtils.isNoneBlank(type)) {
			if (ConstUtil.TYPE_PHONE.equals(type)) {
				int rs = actionUserDao.checkUserByPhone(str);
				if (rs>0) {
					return	SverResponse.createByErrorMessage("�����ֻ����Ѵ��ڣ�");
				}
			}
		}else {
			return SverResponse.createByErrorMessage("��Ϣ������");
		}
		return SverResponse.createRespBySuccessMessage("��Ϣ��֤�ɹ�");
	}
	/**
	 * ͨ���û�����ȡ�û�����
	 */
	@Override
	public SverResponse<User> findUserByAccount(String account) {
		//1.ͨ���û������ҵ��û�
		User user = actionUserDao.findUserByAccount(account);
		if (user==null) {
			return SverResponse.createByErrorMessage("�û�������");
		}
		//2.�����ÿ�
		user.setPassword(StringUtils.EMPTY);
		//3.��ȫ������ÿ�
		user.setAsw(StringUtils.EMPTY);
		return SverResponse.createRespBySuccess(user);
	}
	/**
	 * У�鰲ȫ�����
	 */
	@Override
	public SverResponse<String> checkUserAnswer(String account, String question, String asw) {
		//1.��ȡУ����
		int rs = actionUserDao.checkUserAnswer(account,question,asw);
		if (rs>0) {
			//2.����ȷ������token
			String token = UUID.randomUUID().toString();
			//3.���뻺��
			TokenCache.setCacheData(TokenCache.PREFIX+account, token);
			return SverResponse.createRespBySuccessMessage(token);
		}
		return SverResponse.createByErrorMessage("����𰸴���!");
	}
	
	/**
	 * ��������
	 */
	@Override
	public SverResponse<String> resetPassword(Integer userId, String password) {
		//1.�������
		String pwd = MD5Util.MD5Encode(password, "utf-8", false);
		//2.��ȡuser����
		User user =actionUserDao.findUserById(userId);
		//3.��������
		user.setPassword(pwd);
		user.setUpData_time(new Date());
		int rs = actionUserDao.updateUserInfo(user);
		if (rs>0) {
			return SverResponse.createRespBySuccessMessage("�����޸ĳɹ���");
		}
		return SverResponse.createByErrorMessage("�����޸�ʧ�ܣ�");
	}
	/**
	 * �����û���Ϣ
	 */
	@Override
	public SverResponse<User> updateUserInfo(UserVo userVo) {
		//1.����id��ȡuser����
		User updateUser = actionUserDao.findUserById(userVo.getId());
		//2.�����޸�����
		updateUser.setAccount(userVo.getAccount());
		updateUser.setEmail(userVo.getEmail());
		updateUser.setPhone(userVo.getPhone());
		updateUser.setUpData_time(new Date());
		updateUser.setAge(userVo.getAge());
		//3.�ж���Ů
		if (userVo.getSex().equals("��")) {
			updateUser.setSex(1);
		}else {
			updateUser.setSex(0);
		}
		updateUser.setName(userVo.getName());
		int rs =actionUserDao.updateUserInfo(updateUser);
		if (rs>0) {
			return SverResponse.createRespBySuccess("�û���Ϣ�޸ĳɹ�", updateUser);
		}
		return SverResponse.createByErrorMessage("�û���Ϣ�޸�ʧ�ܣ�");
	}
	/**
	 * ��������
	 */
	@Override
	public SverResponse<String> updatePassword(User user, String newPassword, String oldPassword) {
		//1.���������Ƿ���ȷ
		oldPassword = MD5Util.MD5Encode(oldPassword, "utf-8", false);
		int rs = actionUserDao.checkPassword(user.getAccount(),oldPassword);
		if (rs==0) {
			return SverResponse.createByErrorMessage("ԭʼ�������");
		}
		//2.��������������ݿ�
		newPassword = MD5Util.MD5Encode(newPassword, "utf-8", false);
		user.setPassword(newPassword);
		user.setUpData_time(new Date());
		rs = actionUserDao.updateUserInfo(user);
		if (rs>0) {
			return SverResponse.createRespBySuccessMessage("�����޸ĳɹ�");
		}
		return SverResponse.createByErrorMessage("�����޸�ʧ��");
	}
	/**
	 * ��ѯ��������
	 */
	@Override
	public SverResponse<String> getUserQuestion(String account) {
		//�жϲ���
		if (account==null) {
			return SverResponse.createByErrorMessage("��������");
		}
		User user= actionUserDao.findUserByAccount(account);
		if(user==null) {
			return SverResponse.createByErrorMessage("�û�������");
		}
		if(user.getQuestion().trim().equals("")||user.getQuestion()==null) {
			return SverResponse.createByErrorMessage("δ������������!");
		}else {
			return SverResponse.createRespBySuccess(user.getQuestion());
		}
	}

}
