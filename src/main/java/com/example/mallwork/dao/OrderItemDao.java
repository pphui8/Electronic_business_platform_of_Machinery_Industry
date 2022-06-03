package com.example.mallwork.dao;

import com.example.mallwork.Entity.OrderItem;

import java.util.List;

public interface OrderItemDao {
	
	/**
	 * ���ݶ����Ż�ö�����
	 * @param orderNo
	 * @return
	 */
	public List<OrderItem> getItemsByOrderNo(Long orderNo);
	/**
	 * �������붩����
	 * @param orderItems
	 */
	public int[] batchInsert(List<OrderItem> orderItems);

}
