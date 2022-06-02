package com.example.mallwork.Dao;

import com.example.mallwork.Entity.OrderItem;

import java.util.List;

public interface OrderItemDao {
	
	/**
	 * 根据订单号获得订单项
	 * @param orderNo
	 * @return
	 */
	public List<OrderItem> getItemsByOrderNo(Long orderNo);
	/**
	 * 批量插入订单项
	 * @param orderItems
	 */
	public int[] batchInsert(List<OrderItem> orderItems);

}
