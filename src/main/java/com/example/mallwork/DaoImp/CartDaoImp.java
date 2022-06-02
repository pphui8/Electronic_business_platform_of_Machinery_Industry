package com.example.mallwork.DaoImp;

import com.example.mallwork.Dao.CartDao;
import com.example.mallwork.Entity.Cart;
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
public class CartDaoImp implements CartDao {
	@Autowired
	private QueryRunner queryRunner;
	
	private String alais="id,user_id as userId,product_id as productId,quantity,created,updated,checked";
	/**
	 * 查找用户购物车中商品信息
	 */
	@Override
	public List<Cart> findCartByUser(Integer userid) {
		String sql="select "+this.alais+" from action_carts where user_id=?";
		try {
			return queryRunner.query(sql,new BeanListHandler<Cart>(Cart.class), userid);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 根据用户id和商品id查询
	 */
	@Override
	public Cart findCartByUserAndProductId(Integer userid, Integer productId) {
		String sql="select " + this.alais+ " from action_carts where user_id=? and product_id=?";
		try {
			return queryRunner.query(sql,new BeanHandler<Cart>(Cart.class) ,userid,productId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 新增购物车
	 */
	@Override
	public int insertCart(Cart cart) {
		String sql = "insert into action_carts(user_id,product_id,quantity,created,updated)"
				+ " values(?,?,?,?,?)";
		Object[] params = {cart.getUserId(),cart.getProductId(),cart.getQuantity(),cart.getCreated(),cart.getUpdated()  };
		try {
			return queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}	
	}
	/**
	 * 更新商品信息
	 */
	@Override
	public int updateCartById(Cart Cart) {
		String sql = "update action_carts set quantity = ? where id=?";
		Object[] params = {
				Cart.getQuantity(),Cart.getId()
		};
		try {
			return queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * 清空购物车
	 */

	@Override
	public int deleteCartByUserId(Integer userId) {
		String sql = "delete from action_carts where user_id = ?";
		try {
			return queryRunner.update(sql, userId);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * 更新购物车内容
	 */
	@Override
	public int updateCartByUserIdAndProductId(Cart Cart) {
		String sql = "update action_carts set quantity = ?";
		List<Object> params = new ArrayList<>();
		params.add(Cart.getQuantity());
		if(Cart.getChecked()!=null) {
			sql+=" ,checked = ?";
			params.add(Cart.getChecked());
		}
		sql+=" where user_id = ? and product_id = ?";
		params.add(Cart.getUserId());
		params.add(Cart.getProductId());
		
		try {
			return queryRunner.update(sql, params.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	/**
	 * 删除购物车中商品
	 */
	@Override
	public int deleteCarts(Integer userId, Integer productId) {
		String sql ="delete from action_carts where product_id = ? and user_id = ?";
		try {
			return queryRunner.update(sql,productId,userId);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * 查询购物车中商品数量
	 */
	@Override
	public int getCartCountByUserId(Integer userid) {
		String sql = "select count(id) as num from action_carts where user_id = ?";
		try {
			return queryRunner.query(sql, new ColumnListHandler<Long>("num"), userid).get(0).intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
