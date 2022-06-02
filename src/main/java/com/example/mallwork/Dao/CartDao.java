package com.example.mallwork.Dao;

import com.example.mallwork.Entity.Cart;

import java.util.List;

public interface CartDao {
	/**
	 * 查找用户购物车中商品信息
	 * @param userid
	 * @return
	 */
	public List<Cart> findCartByUser(Integer userid);
	/***
	 * 根据userId和productId查询购物车
	 * @param userid
	 * @param productId
	 * @return
	 */
	public Cart findCartByUserAndProductId(Integer userid, Integer productId);
	/**
	 * 新增购物车
	 * @param cart
	 */
	public int insertCart(Cart cart);
	/**
	 * 更新购物车中商品数量
	 * @param actionCart
	 */
	public int updateCartById(Cart actionCart);
	/**
	 * 清空购物车
	 * @param userId
	 * @return
	 */
	public int deleteCartByUserId(Integer userId);
	/**
	 * 更新购物车
	 * @param actionCart
	 */
	public int updateCartByUserIdAndProductId(Cart actionCart);
	/**
	 * 删除购物车中商品
	 * @param userId
	 * @param productId
	 * @return
	 */
	public int deleteCarts(Integer userId, Integer productId);
	/**
	 * 获取购物车中数量
	 * @param userid
	 * @return
	 */
	public int getCartCountByUserId(Integer userid);

}
