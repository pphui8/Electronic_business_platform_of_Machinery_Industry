package com.example.mallwork.dao;

import com.example.mallwork.Entity.Cart;

import java.util.List;

public interface CartDao {
	/**
	 * �����û����ﳵ����Ʒ��Ϣ
	 * @param userid
	 * @return
	 */
	public List<Cart> findCartByUser(Integer userid);
	/***
	 * ����userId��productId��ѯ���ﳵ
	 * @param userid
	 * @param productId
	 * @return
	 */
	public Cart findCartByUserAndProductId(Integer userid, Integer productId);
	/**
	 * �������ﳵ
	 * @param cart
	 */
	public int insertCart(Cart cart);
	/**
	 * ���¹��ﳵ����Ʒ����
	 * @param actionCart
	 */
	public int updateCartById(Cart actionCart);
	/**
	 * ��չ��ﳵ
	 * @param userId
	 * @return
	 */
	public int deleteCartByUserId(Integer userId);
	/**
	 * ���¹��ﳵ
	 * @param actionCart
	 */
	public int updateCartByUserIdAndProductId(Cart actionCart);
	/**
	 * ɾ�����ﳵ����Ʒ
	 * @param userId
	 * @param productId
	 * @return
	 */
	public int deleteCarts(Integer userId, Integer productId);
	/**
	 * ��ȡ���ﳵ������
	 * @param userid
	 * @return
	 */
	public int getCartCountByUserId(Integer userid);

}
