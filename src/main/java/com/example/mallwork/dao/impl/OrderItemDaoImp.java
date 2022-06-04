package com.example.mallwork.dao.impl;

import com.example.mallwork.dao.OrderItemDao;
import com.example.mallwork.Entity.OrderItem;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderItemDaoImp implements OrderItemDao {
	@Autowired
	private QueryRunner queryRunner;
	
	private String alias="id,uid as userId,order_no as orderNo,goods_id as goodsId,"
			+ "goods_name as goodsName,icon_url as iconUrl,price,quantity,total_price as totalPrice"
			+ "createde,updated";
	/**
	 * ���ݶ����Ų�ѯ��������
	 */
	@Override
	public List<OrderItem> getItemsByOrderNo(Long orderNo) {
		String sql = "select "+this.alias+" from order_item "
				+ "where order_no=?";
		try {
			return queryRunner.query(sql, new BeanListHandler<OrderItem>(OrderItem.class), orderNo);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * �������붩����
	 */
	
	@Override
	public int[] batchInsert(List<OrderItem> orderItems) {
		String sql="insert into order_item(uid,order_no,"
				+ "goods_id,goods_name,icon_url,"
				+ "price,quantity,total_price,created,updated)"
				+ " values(?,?,?,?,?,?,?,?,?,?)";
		Object[][] params = new Object[orderItems.size()][];
		for(int i=0;i<orderItems.size();i++) {
			OrderItem item =orderItems.get(i);
			params[i]=new Object[] {
				item.getUserId(),item.getOrderNo(),item.getGoodsId(),item.getGoodsName(),
				item.getIconUrl(),item.getPrice(),item.getQuantity(),item.getTotalPrice(),
				item.getCreated(),item.getUpdated()
			};
		}
		try {
			return queryRunner.batch(sql, params);
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		
	}
}
