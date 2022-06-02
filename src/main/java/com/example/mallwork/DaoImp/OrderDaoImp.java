package com.example.mallwork.DaoImp;

import com.example.mallwork.Dao.OrderDao;
import com.example.mallwork.Entity.Order;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDaoImp implements OrderDao {
	@Autowired
	private QueryRunner queryRunner;
	/**
	 * 获取用户订单总数
	 */
	private String alias="id,order_no as orderNo,uid as uId,addr_id as addrId,amount,type,"
			+ "freight,status,payment_time as paymentTime,delivery_time as deliveryTime,"
			+ "finish_time as finishTime,close_time as closeTime,updated,created ";
	@Override
	public int getTotalRecord(Integer userid, Integer status) {
		String sql="select count(id) as num from action_orders where uid =? ";
		List<Object> params = new ArrayList<>();
		params.add(userid);
		//判断订单状态
		if (status!=0) {
			sql+="and status = ?";
			params.add(status);
		}
		try {
			//获取总数
			return queryRunner.query(sql, new ColumnListHandler<Long>("num"),params.toArray()).get(0).intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;

		}
	}
	/**
	 * 获取用户订单详情分页显示
	 */
	@Override
	public List<Order> findOrders(Integer userid, Integer status, int startIndex, int pageSize) {
		String sql = "select " + this.alias +" from action_orders where uid=? ";
		List<Object> params = new ArrayList<>();
		params.add(userid);
		//判断订单状态
//		System.out.println(userid);
		if (status!=0) {
			sql+="and status = ? ";
			params.add(status);
		}
		sql+="limit ?,?";
		params.add(startIndex);
		params.add(pageSize);
		try {
			return queryRunner.query(sql, new BeanListHandler<Order>(Order.class), params.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		}
	}
	/**
	 * 根据用户id及订单号查询订单
	 */
	@Override
	public Order findOrderByUserAndOrderNo(Integer userid, Long orderNo) {
		String sql="select " + this.alias + " from action_orders where uid=? and order_no =?";
		try {
			return queryRunner.query(sql, new BeanHandler<Order>(Order.class), userid,orderNo);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 取消订单即更新订单消息
	 */
	@Override
	public int updateOrder(Order updateOrder) {
		String sql = "update action_orders set updated = ?";
		List<Object> params = new ArrayList<>();
		params.add(updateOrder.getUpdate());
		if (updateOrder.getStatus()!=null) {
			sql+=" ,status = ?";
			params.add(updateOrder.getStatus());
		}
		if (updateOrder.getPaymentTime()!=null) {
			sql+=" ,payment_time = ?";
			params.add(updateOrder.getPaymentTime());
		}
		if (updateOrder.getDeliveryTime()!=null) {
			sql+=" ,delivery_time = ?";
			params.add(updateOrder.getDeliveryTime());
		}
		if (updateOrder.getFinishTime()!=null) {
			sql+=" ,finish_time = ?";
			params.add(updateOrder.getFinishTime());
		}
		if (updateOrder.getCloseTime()!=null) {
			sql+=" ,close_time = ?";
			params.add(updateOrder.getCloseTime());
		}
		sql+=" where id=?";
		params.add(updateOrder.getId());
		try {
			return queryRunner.update(sql, params.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * 保存订单信息
	 */
	@Override
	public int insertOrder(Order order) {
		String sql="insert into action_orders(order_no,"
				+ "uid,addr_id,amount,type,freight,"
				+ "status,payment_time,delivery_time,"
				+ "finish_time,close_time,updated,created)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {order.getOrderNo(),order.getuId(),order.getAddrId(),order.getAmount(),
				order.getType(),order.getFreight(),order.getStatus(),order.getPaymentTime(),
				order.getDeliveryTime(),order.getFinishTime(),order.getCloseTime(),order.getUpdate(),order.getCreated()};
		try {
			return queryRunner.update(sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}

	}
}
