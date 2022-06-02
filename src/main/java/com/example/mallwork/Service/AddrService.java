package com.example.mallwork.Service;

import com.example.mallwork.Common.SverResponse;
import com.example.mallwork.Entity.Address;

import java.util.List;

public interface AddrService {
	/**
	 * 新增收件人地址信息
	 * @param addr
	 * @return
	 */
	public SverResponse<String> addAddress(Address addr);
	
	/**
	 * 更新收件人地址信息
	 * @param addr
	 * @return
	 */
	public SverResponse<String> updateAddress(Address addr);
	/**
	 * 查找某个用户所有收货地址
	 * @param userId
	 * @return
	 */
	public SverResponse<List<Address>> findAddressByUserId(Integer userId);
	
	/**
	 * 根据id删除地址信息
	 * @param userId
	 * @param id
	 * @return
	 */
	public SverResponse<String> deleteAddress(Integer userId, Integer id);
	
	/**
	 * 更新默认地址
	 * @param userId
	 * @param id
	 * @return
	 */
	public SverResponse<String> updateDeaufultAddress(Integer userId, Integer id);

}
