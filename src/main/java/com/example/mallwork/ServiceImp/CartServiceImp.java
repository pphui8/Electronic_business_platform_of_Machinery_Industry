package com.example.mallwork.ServiceImp;

import com.example.mallwork.Common.SverResponse;
import com.example.mallwork.Dao.CartDao;
import com.example.mallwork.Dao.ProductDao;
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
		//验证参数是否存在
		if (userid==null||productId==null||count==null) {
			return SverResponse.createByErrorMessage("参数错误！");
		}
		//查看用户的购物车是否存在商品
		Cart Cart = CartDao.findCartByUserAndProductId(userid,productId);
		//不存在新增
		if (Cart==null) {
			Cart cart = new Cart();
			cart.setUserId(userid);
			cart.setProductId(productId);
			cart.setQuantity(count);
			cart.setCreated(new Date());
			cart.setUpdated(new Date());
			CartDao.insertCart(cart);
		}else {
			//存在更新
			int cartCount = Cart.getQuantity()+count;
			Cart.setQuantity(cartCount);
			CartDao.updateCartById(Cart);
		}

		return SverResponse.createRespBySuccessMessage("商品已成功添加到购物车！");
	}
	/**
	 * 查找购物车中所有信息
	 */
	@Override
	public SverResponse<CartVo> findAllCarts(Integer userid) {
		if (userid==null) {
			return SverResponse.createByErrorMessage("参数错误！");
		}
		//1.查找用户购物车中商品
		List<Cart> list = CartDao.findCartByUser(userid);
		//2.封装CartVo对象
		CartVo cartVo = createCartVo(list);
		return SverResponse.createRespBySuccess(cartVo);
	}
	/**
	 * 封装购物车vo对象
	 * @param carts
	 * @return
	 */
	private CartVo createCartVo(List<Cart> carts) {
		CartVo cartVo = new CartVo();
		List<CartsListVo> list = Lists.newArrayList();
		//购物车商品总价格
		BigDecimal cartTotalPrice = new BigDecimal("0");
		if (CollectionUtils.isEmpty(null)) {
			for(Cart cart:carts ) {
				//转换对象
				CartsListVo listVo =new CartsListVo();
				listVo.setId(cart.getId());
				listVo.setUserId(cart.getUserId());
				listVo.setProductId(cart.getProductId());
				listVo.setChecked(cart.getChecked());
				//封装商品信息
				Product product = ProductDao.findProductById(listVo.getProductId());
				if(product!=null) {
					listVo.setName(product.getName());
					listVo.setStatus(product.getStatus());
					listVo.setPrice(product.getPrice());
					listVo.setStock(product.getStock());;;
					listVo.setIconUrl(product.getIconUrl());
					//判断库存
					int buyCount=0;
					if(product.getStock()>=cart.getQuantity()) {
						buyCount=cart.getQuantity();
					}else {
						buyCount = product.getStock();
						//更新购物车中商品数量
						Cart updateCart = new Cart();
						updateCart.setId(cart.getId());
						updateCart.setQuantity(buyCount);
						//更新选中状态
						updateCart.setChecked(cart.getChecked());
						CartDao.updateCartById(updateCart);
					}
					listVo.setQuantity(buyCount);
					//计算购物车中某商品总价格
					BigDecimal totalPrice = CalcUtil.mul(listVo.getPrice().doubleValue(), listVo.getQuantity().doubleValue());
					listVo.setTotalPrice(totalPrice);	
					if(cart.getChecked() ==1) {
						//计算购物车选中状态商品的总价格
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
	 * 清空购物车
	 */
	@Override
	public SverResponse<String> clearCart(Integer userid) {
		//判断参数正确
		if(userid == null) {
			return SverResponse.createByErrorMessage("参数错误！");
		}
		//清空购物车，判断正确
		int rs = CartDao.deleteCartByUserId(userid);
		if(rs >0) {
			return SverResponse.createRespBySuccessMessage("成功清空购物车！");
		}
		return SverResponse.createByErrorMessage("清空购物车失败");
	}
	/**
	 * 更新购物车
	 */
	@Override
	public SverResponse<CartVo> updateCart(Integer userid, Integer productId, Integer count, Integer checked) {
		//判断参数
		if(userid==null || productId==null || count ==null) {
			return SverResponse.createRespBySuccessMessage("参数错误！");
		}
		//更新购物车信息
		Cart Cart = new Cart();
		Cart.setUserId(userid);
		Cart.setProductId(productId);
		Cart.setQuantity(count);
		Cart.setChecked(checked);
		CartDao.updateCartByUserIdAndProductId(Cart);
		//返回所有购物车信息
		return findAllCarts(userid);
	}
	/**
	 * 删除购物车中商品
	 */
	@Override
	public SverResponse<CartVo> deleteCart(Integer userId, Integer productId) {
		//判断参数
		if(userId ==null || productId ==null ) {
			return SverResponse.createRespBySuccessMessage("参数错误！");
		}
		//删除商品
		int rs = CartDao.deleteCarts(userId,productId);
		if(rs >0) {
			//返回所有购物车信息
			return this.findAllCarts(userId);
		}
		return SverResponse.createRespBySuccessMessage("删除商品失败！");
	}
	/**
	 * 显示购物车中数量
	 */
	@Override
	public SverResponse<Integer> getCartCount(Integer userid) {
		//判断参数
		if(userid == null) {
			return SverResponse.createRespBySuccessMessage("参数错误！");
		}
		int count = CartDao.getCartCountByUserId(userid);
		return SverResponse.createRespBySuccess(Integer.valueOf(count));
	}

}
