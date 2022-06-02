package com.example.mallwork.Dao;

import com.example.mallwork.Entity.Address;
import java.util.List;

public interface AddrDao {
	
	/**
	 * 查询是否存在默认地址
	 * @param userId
	 * @return
	 */
	public int findDefaultAddrByUserId(Integer userId);
	
	/**
	 * 新增收件地址
	 * @param addr
	 * @return
	 */
	public int instertAddress(Address addr);
	/**
	 * 更新收件地址
	 * @param addr
	 * @return
	 */

	public int updateAddress(Address addr);
	/**
	 * 查询用户的收件人地址信息
	 * @param userId
	 * @return
	 */
	public List<Address> findAddressByUserId(Integer userId);
	
	/**
	 * 读取用户默认地址
	 * @param userId
	 * @return
	 */

	public Address findDefaultAddr(Integer userId);
	/**
	 * 根据id查询收货人地址信息
	 * @param addrId
	 * @return
	 */
	public Address findAddressById(Integer addrId);

}
