package com.example.mallwork.Controller;

import com.example.mallwork.Common.SverResponse;
import com.example.mallwork.Entity.User;
import com.example.mallwork.Service.OrderService;
import com.example.mallwork.Tools.ConstUtil;
import com.example.mallwork.Tools.PageBean;
import com.example.mallwork.Vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService OrderService;
	@RequestMapping("/getlist.do")
	@ResponseBody
	/**
	 * 获取订单列表
	 * @param session
	 * @param status
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public SverResponse<PageBean<OrderVo>> list(HttpSession session, Integer status,
												@RequestParam(value="pageNum",defaultValue="1") int pageNum,
												@RequestParam(value="pageSize",defaultValue="10") int pageSize) {
		//1.判断用户是否已经登录
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user==null) {
			return SverResponse.createByErrorMessage("请登录后再进行操作！");
		}
		//2.查询所有订单
		return OrderService.findOrder(user.getId(),status,pageNum,pageSize);
		
	}
	/**
	 * 取消订单
	 * @param session
	 * @param orderNo
	 * @return
	 */
	@RequestMapping("/cancelorder.do")
	@ResponseBody
	public SverResponse<String> cancelOrder(HttpSession session,Long orderNo) {
		//1.判断用户是否登录
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user==null) {
			return SverResponse.createByErrorMessage("请登录后再进行操作！");
		}
		//2.取消订单返回消息
		return OrderService.cancelOrder(user.getId(),orderNo);
	}
	/**
	 * 确定订单
	 * @param session
	 * @param orderNo
	 * @return
	 */
	@RequestMapping("/confirmreceipt.do")
	@ResponseBody
	public SverResponse<String> confirmReceipt(HttpSession session,Long orderNo) {
		//1.判断用户是否登录
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user==null) {
			return SverResponse.createByErrorMessage("请登录后再进行操作！");
		}
		//2.取消订单返回消息
		return OrderService.cancelOrder(user.getId(),orderNo);
	}
	/**
	 * 获取订单详情
	 * @param session
	 * @param orderNo
	 * @return
	 */
	@RequestMapping("/getdetail.do")
	@ResponseBody
	public SverResponse<OrderVo> getDetail(HttpSession session,Long orderNo) {
		//1.判断用户是否登录
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user==null) {
			return SverResponse.createByErrorMessage("请登录后再进行操作！");
		}
		return OrderService.findOrderDetail(user.getId(),orderNo);
		
	}
	/**
	 * 创建订单
	 * @param session
	 * @param addrId
	 * @return
	 */
	@RequestMapping("/createorder.do")
	@ResponseBody
	public SverResponse<OrderVo> createOrder(HttpSession session,Integer addrId) {
		//1.判断用户是否登录
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user==null) {
			return SverResponse.createByErrorMessage("请登录后再进行操作！");
		}
		return OrderService.generateOrder(user.getId(),addrId);
		
	}
}
