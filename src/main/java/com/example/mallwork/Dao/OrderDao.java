package com.example.mallwork.Dao;

import com.example.mallwork.Entity.Order;

import java.util.List;

public interface OrderDao {
	
	/**
	 * 获取用户订单总数(各种状态下）
	 * @param userid
	 * @param status
	 * @return
	 */
	public int getTotalRecord(Integer userid, Integer status);
	/**
	 * 获取用户订单分页列表
	 * @param userid
	 * @param status
	 * @param startIndex
	 * @param pageSize
	 */
	public List<Order> findOrders(Integer userid, Integer status, int startIndex, int pageSize);
	/**
	 * 根据用户id及订单号查询订单
	 * @param userid
	 * @param orderNo
	 * @return
	 */
	public Order findOrderByUserAndOrderNo(Integer userid, Long orderNo);
	/**
	 * 更新订单信息
	 * @param updateOrder
	 * @return
	 */
	public int updateOrder(Order updateOrder);
	/**
	 * 保存订单信息
	 * @param order
	 * @return
	 */
	public int insertOrder(Order order);


}
