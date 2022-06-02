package com.example.mallwork.Service;

import com.example.mallwork.Common.SverResponse;
import com.example.mallwork.Vo.CartVo;

public interface CartService {
	/**
	 * 保存商品信息至购物车
	 * @param userid
	 * @param productId
	 * @param count
	 * @return
	 */
	public SverResponse<String> saveOrUpdate(Integer userid, Integer productId, Integer count);
	/**
	 * 查看购物车所有信息
	 * @param userid
	 * @return
	 */
	public SverResponse<CartVo> findAllCarts(Integer userid);
	/**
	 * 清空购物车
	 * @param userid
	 * @return
	 */
	public SverResponse<String> clearCart(Integer userid);
	/**
	 * 更新购物车信息
	 * @param userid
	 * @param productId
	 * @param count
	 * @param checked
	 * @return
	 */
	public SverResponse<CartVo> updateCart(Integer userid, Integer productId, Integer count, Integer checked);
	/**
	 * 删除商品
	 * @param userid
	 * @param productId
	 * @return
	 */
	public SverResponse<CartVo> deleteCart(Integer userid, Integer productId);
	/**
	 * 获得购物车中数量
	 * @param userid
	 * @return
	 */
	public SverResponse<Integer> getCartCount(Integer userid);

}
