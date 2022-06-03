package com.example.mallwork.controller;

import com.example.mallwork.Common.SverResponse;
import com.example.mallwork.Entity.User;
import com.example.mallwork.Service.CartService;
import com.example.mallwork.Tools.ConstUtil;
import com.example.mallwork.Vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService CartService;
	/**
	 * ��ӹ��ﳵ
	 * @param session
	 * @param productId
	 * @param count
	 * @return
	 */
	@RequestMapping("/savecart.do")
	@ResponseBody
	public SverResponse<String> addProductCart(HttpSession session, Integer productId, Integer count) {
		//1.�ж�User�Ƿ��¼
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user==null) {
			return SverResponse.createByErrorMessage("���¼���ٽ��в�����");
		}
		return CartService.saveOrUpdate(user.getId(),productId,count);
		
	}
	/**
	 * �鿴���ﳵ
	 * @param session
	 * @return
	 */
	@RequestMapping("/findallcarts.do")
	@ResponseBody
	public SverResponse<CartVo> findAllCarts(HttpSession session) {
		//1.�ж�User�Ƿ��¼
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user==null) {
			return SverResponse.createByErrorMessage("���¼���ٽ��в�����");
		}
		return CartService.findAllCarts(user.getId());
		
	}
	/**
	 * ��չ��ﳵ
	 * @param session
	 * @return
	 */
	@RequestMapping("/clearcarts.do")
	@ResponseBody
	public SverResponse<String> clearCarts(HttpSession session){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user == null ) {
			return SverResponse.createByErrorMessage("���¼���ڲ鿴���ﳵ��");
		}
		//��չ��ﳵ
		return CartService.clearCart(user.getId());
	}
	
	/**
	 * ���¹��ﳵ
	 * @param session
	 * @param productId
	 * @param productId
	 * @param productId
	 * @return
	 */
	@RequestMapping("/updatecarts.do")
	@ResponseBody
	public SverResponse<CartVo> updateCarts(HttpSession session,Integer productId,Integer count,Integer checked){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user == null ) {
			return SverResponse.createByErrorMessage("���¼���ڲ鿴���ﳵ��");
		}
		return CartService.updateCart(user.getId(),productId,count,checked);
	}
	
	/**
	 * ɾ����Ʒ
	 * @param session
	 * @param productId
	 * @return
	 */
	@RequestMapping("/delcarts.do")
	@ResponseBody
	public SverResponse<CartVo> deleteCart(HttpSession session,Integer productId){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user == null ) {
			return SverResponse.createByErrorMessage("���¼���ڲ鿴���ﳵ��");
		}
		return CartService.deleteCart(user.getId(),productId);
	}
	/**
	 * ���ﳵ����
	 * @param session
	 * @return
	 */
	@RequestMapping("/getcartcount.do")
	@ResponseBody
	public SverResponse<Integer> getCartsCount(HttpSession session){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user == null ) {
			return SverResponse.createByErrorMessage("���¼���ڲ鿴���ﳵ��");
		}
		return CartService.getCartCount(user.getId());
	}
}
