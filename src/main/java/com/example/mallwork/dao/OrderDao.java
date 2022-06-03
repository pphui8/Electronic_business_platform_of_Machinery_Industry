package com.example.mallwork.dao;

import com.example.mallwork.Entity.Order;

import java.util.List;

public interface OrderDao {
	
	/**
	 * ��ȡ�û���������(����״̬�£�
	 * @param userid
	 * @param status
	 * @return
	 */
	public int getTotalRecord(Integer userid, Integer status);
	/**
	 * ��ȡ�û�������ҳ�б�
	 * @param userid
	 * @param status
	 * @param startIndex
	 * @param pageSize
	 */
	public List<Order> findOrders(Integer userid, Integer status, int startIndex, int pageSize);
	/**
	 * �����û�id�������Ų�ѯ����
	 * @param userid
	 * @param orderNo
	 * @return
	 */
	public Order findOrderByUserAndOrderNo(Integer userid, Long orderNo);
	/**
	 * ���¶�����Ϣ
	 * @param updateOrder
	 * @return
	 */
	public int updateOrder(Order updateOrder);
	/**
	 * ���涩����Ϣ
	 * @param order
	 * @return
	 */
	public int insertOrder(Order order);


}
