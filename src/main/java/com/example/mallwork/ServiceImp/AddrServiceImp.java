package com.example.mallwork.ServiceImp;

import com.example.mallwork.Common.SverResponse;
import com.example.mallwork.dao.AddrDao;
import com.example.mallwork.Entity.Address;
import com.example.mallwork.Service.AddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddrServiceImp implements AddrService {
	@Autowired
	private AddrDao actionAddrDao;
	
	/**
	 * �����ջ���ַ
	 */
	@Override
	public SverResponse<String> addAddress(Address addr) {
		//1.�жϲ���
		if (addr==null) {
			return SverResponse.createByErrorMessage("��������");
		}
		//2.�ж����е�ַ���Ƿ���Ĭ�ϵ�ַ�����û��������ַΪĬ�ϵ�ַ������Ϊһ���ַ
		int count = actionAddrDao.findDefaultAddrByUserId(addr.getUserId());
		System.out.println(count);
		if (count==0) {
			addr.setDefaultAddr(1);
		}
		else {
			addr.setDefaultAddr(0);
		}
		//3.��ӵ�ַ��������
		addr.setCreated(new Date());
		addr.setUpdated(new Date());
		
		//4.�����ַ��Ϣ
		int rs = actionAddrDao.instertAddress(addr);
		//5.�ж��Ƿ����ɹ�
		if (rs>0) {
			return SverResponse.createRespBySuccessMessage("���ӵ�ַ�ɹ���");
		}
		return SverResponse.createByErrorMessage("��ַ����ʧ�ܣ�");
	}
	/**
	 * �����ջ���ַ
	 */
	@Override
	public SverResponse<String> updateAddress(Address addr) {
		//1.�жϲ���
		if (addr==null) {
			return SverResponse.createByErrorMessage("��������");
		}
		//2.�����������
		addr.setUpdated(new Date());
		int rs  = actionAddrDao.updateAddress(addr);
		if (rs>0) {
			return SverResponse.createRespBySuccessMessage("���µ�ַ�ɹ���");
		}
		return SverResponse.createByErrorMessage("��ַ����ʧ�ܣ�");
	}
	
	/**
	 * ��ѯĳ�û����е�ַ��Ϣ
	 */
	@Override
	public SverResponse<List<Address>> findAddressByUserId(Integer userId) {
		//1.�жϲ���
		if (userId==null) {
			return SverResponse.createByErrorMessage("��������");
		}
		List<Address> list = actionAddrDao.findAddressByUserId(userId);
		return SverResponse.createRespBySuccess(list);
	}
	/**
	 * ɾ����ַ
	 */
	@Override
	public SverResponse<String> deleteAddress(Integer userId, Integer id) {
		//1.�жϲ���
		if (id==null) {
			return SverResponse.createByErrorMessage("��������");
		}
		//2.ɾ����ַ����del_state�ֶν����޸�
		Address Address = new Address();
		Address.setId(id);
		Address.setDelState(1);
		Address.setUpdated(new Date());
		int rs = actionAddrDao.updateAddress(Address);
		if (rs>0) {
			return SverResponse.createRespBySuccessMessage("ɾ����ַ�ɹ���");
		}
		return SverResponse.createByErrorMessage("��ַɾ��ʧ�ܣ�");
	}
	/**
	 * ����Ĭ�ϵ�ַ
	 */
	@Override
	public SverResponse<String> updateDeaufultAddress(Integer userId, Integer id) {
		int a;
		//1.�жϲ���
		if (id==null||userId==null) {
			return SverResponse.createByErrorMessage("��������");
		}
		//2.��ȡԭ��Ĭ�ϵ�ַ
		Address oldAddress = actionAddrDao.findDefaultAddr(userId);
		if (oldAddress!=null) {
			//ȡ��Ĭ�ϵ�ַ
			oldAddress.setDefaultAddr(0);
			oldAddress.setUpdated(new Date());
//			System.out.println(oldAddress.getUserId());
//			System.out.println("00000"+oldAddress.getId());
//			a= actionAddrDao.updateAddress(oldAddress);
//			System.out.println(a);
			if (actionAddrDao.updateAddress(oldAddress)>=0) {
				return SverResponse.createByErrorMessage("����Ĭ�ϵ�ַʧ�ܣ�");
			}
		}
		//3.����Ĭ�ϵ�ַ
		Address newAddress = new Address();
		newAddress.setDefaultAddr(1);
		newAddress.setId(id);
		newAddress.setUpdated(new Date());
		
		if (actionAddrDao.updateAddress(newAddress)<=0) {
			return SverResponse.createByErrorMessage("����Ĭ�ϵ�ַʧ�ܣ�");
		}
		return SverResponse.createRespBySuccessMessage("Ĭ�ϵ�ַ���óɹ���");
	}
}
