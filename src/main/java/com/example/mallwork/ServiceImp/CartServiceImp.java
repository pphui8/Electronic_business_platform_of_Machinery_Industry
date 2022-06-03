package com.example.mallwork.ServiceImp;

import com.example.mallwork.Common.SverResponse;
import com.example.mallwork.dao.CartDao;
import com.example.mallwork.dao.ProductDao;
import com.example.mallwork.Entity.Cart;
import com.example.mallwork.Entity.Product;
import com.example.mallwork.Service.CartService;
import com.example.mallwork.Tools.CalcUtil;
import com.example.mallwork.Vo.CartVo;
import com.example.mallwork.Vo.CartsListVo;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class CartServiceImp implements CartService {
	@Autowired
	private CartDao CartDao;
	@Autowired
	private ProductDao ProductDao;
	@Override
	public SverResponse<String> saveOrUpdate(Integer userid, Integer productId, Integer count) {
		//��֤�����Ƿ����
		if (userid==null||productId==null||count==null) {
			return SverResponse.createByErrorMessage("��������");
		}
		//�鿴�û��Ĺ��ﳵ�Ƿ������Ʒ
		Cart Cart = CartDao.findCartByUserAndProductId(userid,productId);
		//����������
		if (Cart==null) {
			Cart cart = new Cart();
			cart.setUserId(userid);
			cart.setProductId(productId);
			cart.setQuantity(count);
			cart.setCreated(new Date());
			cart.setUpdated(new Date());
			CartDao.insertCart(cart);
		}else {
			//���ڸ���
			int cartCount = Cart.getQuantity()+count;
			Cart.setQuantity(cartCount);
			CartDao.updateCartById(Cart);
		}

		return SverResponse.createRespBySuccessMessage("��Ʒ�ѳɹ���ӵ����ﳵ��");
	}
	/**
	 * ���ҹ��ﳵ��������Ϣ
	 */
	@Override
	public SverResponse<CartVo> findAllCarts(Integer userid) {
		if (userid==null) {
			return SverResponse.createByErrorMessage("��������");
		}
		//1.�����û����ﳵ����Ʒ
		List<Cart> list = CartDao.findCartByUser(userid);
		//2.��װCartVo����
		CartVo cartVo = createCartVo(list);
		return SverResponse.createRespBySuccess(cartVo);
	}
	/**
	 * ��װ���ﳵvo����
	 * @param carts
	 * @return
	 */
	private CartVo createCartVo(List<Cart> carts) {
		CartVo cartVo = new CartVo();
		List<CartsListVo> list = Lists.newArrayList();
		//���ﳵ��Ʒ�ܼ۸�
		BigDecimal cartTotalPrice = new BigDecimal("0");
		if (CollectionUtils.isEmpty(null)) {
			for(Cart cart:carts ) {
				//ת������
				CartsListVo listVo =new CartsListVo();
				listVo.setId(cart.getId());
				listVo.setUserId(cart.getUserId());
				listVo.setProductId(cart.getProductId());
				listVo.setChecked(cart.getChecked());
				//��װ��Ʒ��Ϣ
				Product product = ProductDao.findProductById(listVo.getProductId());
				if(product!=null) {
					listVo.setName(product.getName());
					listVo.setStatus(product.getStatus());
					listVo.setPrice(product.getPrice());
					listVo.setStock(product.getStock());;;
					listVo.setIconUrl(product.getIconUrl());
					//�жϿ��
					int buyCount=0;
					if(product.getStock()>=cart.getQuantity()) {
						buyCount=cart.getQuantity();
					}else {
						buyCount = product.getStock();
						//���¹��ﳵ����Ʒ����
						Cart updateCart = new Cart();
						updateCart.setId(cart.getId());
						updateCart.setQuantity(buyCount);
						//����ѡ��״̬
						updateCart.setChecked(cart.getChecked());
						CartDao.updateCartById(updateCart);
					}
					listVo.setQuantity(buyCount);
					//���㹺�ﳵ��ĳ��Ʒ�ܼ۸�
					BigDecimal totalPrice = CalcUtil.mul(listVo.getPrice().doubleValue(), listVo.getQuantity().doubleValue());
					listVo.setTotalPrice(totalPrice);	
					if(cart.getChecked() ==1) {
						//���㹺�ﳵѡ��״̬��Ʒ���ܼ۸�
						cartTotalPrice = CalcUtil.add(cartTotalPrice.doubleValue(), listVo.getTotalPrice().doubleValue());
					}
				}
				list.add(listVo);
			}
		}
		cartVo.setLists(list);
		cartVo.setTotalPrice(cartTotalPrice);
		return cartVo;
	}
	/**
	 * ��չ��ﳵ
	 */
	@Override
	public SverResponse<String> clearCart(Integer userid) {
		//�жϲ�����ȷ
		if(userid == null) {
			return SverResponse.createByErrorMessage("��������");
		}
		//��չ��ﳵ���ж���ȷ
		int rs = CartDao.deleteCartByUserId(userid);
		if(rs >0) {
			return SverResponse.createRespBySuccessMessage("�ɹ���չ��ﳵ��");
		}
		return SverResponse.createByErrorMessage("��չ��ﳵʧ��");
	}
	/**
	 * ���¹��ﳵ
	 */
	@Override
	public SverResponse<CartVo> updateCart(Integer userid, Integer productId, Integer count, Integer checked) {
		//�жϲ���
		if(userid==null || productId==null || count ==null) {
			return SverResponse.createRespBySuccessMessage("��������");
		}
		//���¹��ﳵ��Ϣ
		Cart Cart = new Cart();
		Cart.setUserId(userid);
		Cart.setProductId(productId);
		Cart.setQuantity(count);
		Cart.setChecked(checked);
		CartDao.updateCartByUserIdAndProductId(Cart);
		//�������й��ﳵ��Ϣ
		return findAllCarts(userid);
	}
	/**
	 * ɾ�����ﳵ����Ʒ
	 */
	@Override
	public SverResponse<CartVo> deleteCart(Integer userId, Integer productId) {
		//�жϲ���
		if(userId ==null || productId ==null ) {
			return SverResponse.createRespBySuccessMessage("��������");
		}
		//ɾ����Ʒ
		int rs = CartDao.deleteCarts(userId,productId);
		if(rs >0) {
			//�������й��ﳵ��Ϣ
			return this.findAllCarts(userId);
		}
		return SverResponse.createRespBySuccessMessage("ɾ����Ʒʧ�ܣ�");
	}
	/**
	 * ��ʾ���ﳵ������
	 */
	@Override
	public SverResponse<Integer> getCartCount(Integer userid) {
		//�жϲ���
		if(userid == null) {
			return SverResponse.createRespBySuccessMessage("��������");
		}
		int count = CartDao.getCartCountByUserId(userid);
		return SverResponse.createRespBySuccess(Integer.valueOf(count));
	}

}
