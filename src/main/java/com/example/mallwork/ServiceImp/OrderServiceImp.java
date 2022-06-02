
package com.example.mallwork.ServiceImp;

import com.example.mallwork.Common.SverResponse;
import com.example.mallwork.Dao.*;
import com.example.mallwork.Entity.*;
import com.example.mallwork.Service.OrderService;
import com.example.mallwork.Tools.CalcUtil;
import com.example.mallwork.Tools.ConstUtil;
import com.example.mallwork.Tools.DateUtils;
import com.example.mallwork.Tools.PageBean;
import com.example.mallwork.Vo.AddressVo;
import com.example.mallwork.Vo.OrderItemVo;
import com.example.mallwork.Vo.OrderVo;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class OrderServiceImp implements OrderService {
	@Autowired
	private OrderDao OrderDao;
	@Autowired
	private AddrDao AddrDao;
	@Autowired
	private OrderItemDao OrderItemDao;
	@Autowired
	private CartDao CartDao;
	@Autowired
	private ProductDao actionProductDao;
	/**
	 * 查询所有订单列表分页显示
	 */
	@Override
	public SverResponse<PageBean<OrderVo>> findOrder(Integer userid, Integer status, int pageNum, int pageSize) {
		//1.判断userid是否为空
		if (userid==null) {
			return SverResponse.createByErrorMessage("参数错误");
		}
		//2.查找符合条件的总记录数
		int totalRecord = OrderDao.getTotalRecord(userid,status);
//		System.out.print(totalRecord);
		//3.创建分页封装对象
		PageBean<OrderVo> pageBean = new PageBean<>(pageNum, pageSize, totalRecord);

		//4.读取数据
		List<Order>orders=OrderDao.findOrders(userid,status,pageBean.getStartIndex(),pageSize);
		//5.封装Vo
		List<OrderVo> voList = Lists.newArrayList();
		for (Order order:orders) {
			voList.add(createOrderVo1(order,false));
		}
		pageBean.setData(voList);
//		System.out.println(voList.size());
		return SverResponse.createRespBySuccess(pageBean);
	}
	/**
	 * 创建订单封装Vo
	 * @param order
	 * @param orderItems
	 * @return
	 */
	private OrderVo createOrderVo(Order order, List<OrderItem> orderItems) {
		OrderVo orderVo = new OrderVo();
		setNormalProperty(order,orderVo);
		setAddressProperty(order,orderVo,true);
		//设置订单项
		setOrderItemProperty(orderItems,orderVo);
		return orderVo;
	}
	//封装vo
	private OrderVo createOrderVo1(Order order,boolean hasAddress) {
		OrderVo orderVo = new OrderVo();
		setNormalProperty(order,orderVo);
		setAddressProperty(order,orderVo,hasAddress);
		//设置订单项
		List<OrderItem> orderItems = OrderItemDao.getItemsByOrderNo(order.getOrderNo());
		setOrderItemProperty(orderItems,orderVo);
		return orderVo;
		
	}
	/**
	 * 封装订单项属性
	 * @param orderItems
	 * @param orderVo
	 */
	private void setOrderItemProperty(List<OrderItem> orderItems, OrderVo orderVo) {
		List<OrderItemVo> items = Lists.newArrayList();
		for(OrderItem orderItem:orderItems) {
			items.add(createOrderItemVo(orderItem));
		}
		orderVo.setOrderItems(items);
	}
	/**
	 * 封装订单项vo
	 * @param orderItem
	 * @return
	 */
	private OrderItemVo createOrderItemVo(OrderItem orderItem) {
		OrderItemVo itemVo = new OrderItemVo();
		itemVo.setOrderNo(orderItem.getOrderNo());
		itemVo.setGoodsId(orderItem.getGoodsId());
		itemVo.setGoodsName(orderItem.getGoodsName());
		itemVo.setIconUrl(orderItem.getIconUrl());
		itemVo.setCurPrice(orderItem.getPrice());
		itemVo.setTotalPrice(orderItem.getTotalPrice());
		System.out.println(orderItem.getTotalPrice());
		itemVo.setQuantity(orderItem.getQuantity());
		return itemVo;
	}
	/**
	 * 封装地址属性
	 * @param order
	 * @param orderVo
	 * @param hasAddress
	 */
	private void setAddressProperty(Order order, OrderVo orderVo, boolean hasAddress) {
		Address address = AddrDao.findAddressById(order.getAddrId());
		if (address != null) {
			orderVo.setDeliveryName(address.getName());
			if (hasAddress) {
				orderVo.setAddress(createAddressVo(address));
			}else {
				orderVo.setAddress(null);
			}
		}
	}
	/**
	 * 封装地址vo
	 * @param address
	 * @return
	 */
	private AddressVo createAddressVo(Address address) {
		AddressVo AddressVo = new AddressVo();
		AddressVo.setName(address.getName());
		AddressVo.setMobile(address.getMobile());
		AddressVo.setPhone(address.getPhone());
		AddressVo.setProvince(address.getProvince());
		AddressVo.setCity(address.getCity());
		AddressVo.setDistrict(address.getDistrict());
		AddressVo.setAddr(address.getAddr());
		AddressVo.setZip(address.getZip());
		return AddressVo;
	}
	/**
	 * 封装订单的属性
	 * @param order
	 * @param orderVo
	 * @return 
	 */
	private void setNormalProperty(Order order, OrderVo orderVo) {
		orderVo.setOrderNo(order.getOrderNo());
		orderVo.setAmount(order.getAmount());
		orderVo.setType(order.getType());
		orderVo.setTypeDess(ConstUtil.PaymentType.getTypeDesc(order.getType()));//用工具类获取
		orderVo.setFieight(order.getFreight());
		orderVo.setStatus(order.getStatus());
		orderVo.setStatusDesc(ConstUtil.OrderStatus.getStatusDesc(order.getStatus()));
		orderVo.setAddrId(order.getAddrId());
		//时间
		orderVo.setPaymentTime(DateUtils.date2Str(order.getPaymentTime()));
		orderVo.setDeliveryTime(DateUtils.date2Str(order.getDeliveryTime()));
		orderVo.setFinishTime(DateUtils.date2Str(order.getFinishTime()));
		orderVo.setCloseTime(DateUtils.date2Str(order.getCloseTime()));
		orderVo.setCreated(DateUtils.date2Str(order.getCreated()));
	}
	/**
	 * 取消订单
	 * 确认收货
	 */
	@Override
	public SverResponse<String> cancelOrder(Integer userid, Long orderNo) {
		//1.根据订单号查询订单
		Order order = OrderDao.findOrderByUserAndOrderNo(userid,orderNo);
		//2.判断订单是否存在
		if (order==null) {
			return SverResponse.createByErrorMessage("用户订单不存在！");
		}
		//3.判断订单是否已付款
		if (order.getStatus()==ConstUtil.OrderStatus.ORDER_PAID) {
			return SverResponse.createByErrorMessage("订单已付款不能取消！");
		}
		//4.判断状态修改地址信息
		Order updateOrder = new Order();
		updateOrder.setId(order.getId());
		updateOrder.setUpDate(new Date());
		if (order.getStatus()==1) {
			updateOrder.setStatus(ConstUtil.OrderStatus.ORDER_CANCELED);
			int role = OrderDao.updateOrder(updateOrder);
			if (role>0) {
				return SverResponse.createRespBySuccessMessage("订单已经取消！");
			}
		}
		//交易完成
		if (order.getStatus()==3) {
			updateOrder.setStatus(ConstUtil.OrderStatus.ORDER_SUCCESS);
			int role = OrderDao.updateOrder(updateOrder);
			if (role>0) {
				return SverResponse.createRespBySuccessMessage("订单已经确认收货！");
			}
		}
		return SverResponse.createByErrorMessage("订单取消失败！");
	}
	/**
	 * 根据订单号获取订单详情
	 */
	@Override
	public SverResponse<OrderVo> findOrderDetail(Integer userid, Long orderNo) {
		//判断参数是否正确
		if (userid == null || orderNo == null) {
			return SverResponse.createByErrorMessage("参数错误！");
		}
		//查找订单封装
		Order order = OrderDao.findOrderByUserAndOrderNo(userid, orderNo);
		if (order==null) {
			return SverResponse.createByErrorMessage("该用户订单不存在！");
		}
		OrderVo orderVo = createOrderVo1(order, true);
		return SverResponse.createRespBySuccess(orderVo);
	}
	/**
	 * 创建订单
	 */
	@Override
	public SverResponse<OrderVo> generateOrder(Integer userid, Integer addrId) {
		//1.提取购物车中商品信息
		List<Cart> carts = CartDao.findCartByUser(userid);
		//2.计算购物车中每个商品价格并生成订单项
		SverResponse resp = this.cart2OrderItem(userid,carts);
		if (!resp.isSuccess()) {
			return resp;
		}
		//3.取出订单项中价格并计算总价格
		List<OrderItem> orderItems = (List<OrderItem>) resp.getData();
		BigDecimal totalPrice = this.calcOrderTotalPrice(orderItems);
		//4.生成订单插入数据
		Order order = saveOrder(userid,addrId,totalPrice);
		if (order==null) {
			return SverResponse.createByErrorMessage("订单产生错误，请检查后重新提！");
		}
		if (CollectionUtils.isEmpty(orderItems)) {
			return SverResponse.createByErrorMessage("订单项为空，请选择要购买的商品！");
		}
		//5.批量插入订单项
		for(OrderItem orderItem:orderItems) {
			//为订单项设置主键
			orderItem.setOrderNo(order.getOrderNo());
			OrderItemDao.batchInsert(orderItems);
		}
		//6.减少库存
		for(OrderItem orderItem:orderItems) {
			Product Product = actionProductDao.findProductById(orderItem.getGoodsId());
			//减少数量
			Product.setStock(Product.getStock() - orderItem.getQuantity());
			Product.setupdated(new Date());
			//更新库存
			actionProductDao.updateProduct(Product);
		}
		//7.请空购物车
		actionProductDao.deleteCartProduct(userid);
		//8.封装返回前端
		OrderVo orderVo = createOrderVo(order, orderItems);
		return SverResponse.createRespBySuccess(orderVo);
	}

	/**
	 * 保存订单
	 * @param userid
	 * @param addrId
	 * @param totalPrice
	 * @return
	 */
	private Order saveOrder(Integer userid, Integer addrId, BigDecimal totalPrice) {
		Order order = new Order();
		//生成订单号
		Long currentTime = System.currentTimeMillis();
		Long orderNo = currentTime+new Random().nextInt(100);
		order.setOrderNo(orderNo);
		order.setStatus(ConstUtil.OrderStatus.ORDER_NO_PAY);//修改默认状态未付款
		order.setType(ConstUtil.PaymentType.PAY_ON_LINE);//支付状态在线支付
		order.setFreight(0);//邮寄状态
		order.setAmount(totalPrice);//订单总额
		order.setAddrId(addrId);
		order.setuId(userid);
		order.setUpDate(new Date());
		order.setCreated(new Date());
		//插入订单
		int rs = OrderDao.insertOrder(order);
		if (rs>0) {
			return order;
		}
		return null;
	}
	/**
	 * 计算商品总价格
	 * @param orderItems
	 * @return
	 */
	private BigDecimal calcOrderTotalPrice(List<OrderItem> orderItems) {
		BigDecimal totalPrice = new BigDecimal("0");
		for (OrderItem item:orderItems) {
			totalPrice = CalcUtil.add(totalPrice.doubleValue(), item.getTotalPrice().doubleValue());
		}
		return totalPrice;
	}
	/**
	 * 将购物车中商品并封装为订单项
	 * @param userid
	 * @param carts
	 * @return
	 */
	private SverResponse cart2OrderItem(Integer userid, List<Cart> carts) {
		List<OrderItem> items = Lists.newArrayList();
		//判断购物车是否为空
		if (CollectionUtils.isEmpty(carts)) {
			return SverResponse.createByErrorMessage("购物车为空，请选择要购买的商品");
		}
		for (Cart cart:carts) {
			//查看购物车商品状态
			Product product = actionProductDao.findProductById(cart.getProductId());
			//查看商品状态
			if (ConstUtil.ProductStatus.STATUS_ON_SALE!=product.getStatus()) {
				//商品不是上架状态返回提示信息、
				return SverResponse.createByErrorMessage("有下架商品，不能在线购买！");
			}
			//查看库存
			if (cart.getQuantity()>product.getStock()) {
				return SverResponse.createByErrorMessage("商品库存不足！");
			}
			//封装订单
			OrderItem orderItem = new OrderItem();
			orderItem.setUserId(userid);
			orderItem.setGoodsName(product.getName());
			orderItem.setGoodsId(product.getId());
			orderItem.setIconUrl(product.getIconUrl());
			orderItem.setPrice(product.getPrice());
			orderItem.setQuantity(cart.getQuantity());//数量
			orderItem.setTotalPrice(CalcUtil.mul(orderItem.getPrice().doubleValue(),orderItem.getQuantity().doubleValue()));
			orderItem.setCreated(new Date());
			orderItem.setUpdated(new Date());
			items.add(orderItem);
		}
		return SverResponse.createRespBySuccess(items);
	}

	

}
