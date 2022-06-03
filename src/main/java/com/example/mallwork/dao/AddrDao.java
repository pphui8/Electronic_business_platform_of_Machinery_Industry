package com.example.mallwork.dao;

import com.example.mallwork.Entity.Address;
import java.util.List;

public interface AddrDao {
	
	/**
	 * ��ѯ�Ƿ����Ĭ�ϵ�ַ
	 * @param userId
	 * @return
	 */
	public int findDefaultAddrByUserId(Integer userId);
	
	/**
	 * �����ռ���ַ
	 * @param addr
	 * @return
	 */
	public int instertAddress(Address addr);
	/**
	 * �����ռ���ַ
	 * @param addr
	 * @return
	 */

	public int updateAddress(Address addr);
	/**
	 * ��ѯ�û����ռ��˵�ַ��Ϣ
	 * @param userId
	 * @return
	 */
	public List<Address> findAddressByUserId(Integer userId);
	
	/**
	 * ��ȡ�û�Ĭ�ϵ�ַ
	 * @param userId
	 * @return
	 */

	public Address findDefaultAddr(Integer userId);
	/**
	 * ����id��ѯ�ջ��˵�ַ��Ϣ
	 * @param addrId
	 * @return
	 */
	public Address findAddressById(Integer addrId);

}
