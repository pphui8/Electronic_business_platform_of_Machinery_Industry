
package com.example.mallwork.ServiceImp;

import com.example.mallwork.Common.SverResponse;
import com.example.mallwork.dao.*;
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
	 * ��ѯ���ж����б��ҳ��ʾ
	 */
	@Override
	public SverResponse<PageBean<OrderVo>> findOrder(Integer userid, Integer status, int pageNum, int pageSize) {
		//1.�ж�userid�Ƿ�Ϊ��
		if (userid==null) {
			return SverResponse.createByErrorMessage("��������");
		}
		//2.���ҷ����������ܼ�¼��
		int totalRecord = OrderDao.getTotalRecord(userid,status);
//		System.out.print(totalRecord);
		//3.������ҳ��װ����
		PageBean<OrderVo> pageBean = new PageBean<>(pageNum, pageSize, totalRecord);

		//4.��ȡ����
		List<Order>orders=OrderDao.findOrders(userid,status,pageBean.getStartIndex(),pageSize);
		//5.��װVo
		List<OrderVo> voList = Lists.newArrayList();
		for (Order order:orders) {
			voList.add(createOrderVo1(order,false));
		}
		pageBean.setData(voList);
//		System.out.println(voList.size());
		return SverResponse.createRespBySuccess(pageBean);
	}
	/**
	 * ����������װVo
	 * @param order
	 * @param orderItems
	 * @return
	 */
	private OrderVo createOrderVo(Order order, List<OrderItem> orderItems) {
		OrderVo orderVo = new OrderVo();
		setNormalProperty(order,orderVo);
		setAddressProperty(order,orderVo,true);
		//���ö�����
		setOrderItemProperty(orderItems,orderVo);
		return orderVo;
	}
	//��װvo
	private OrderVo createOrderVo1(Order order,boolean hasAddress) {
		OrderVo orderVo = new OrderVo();
		setNormalProperty(order,orderVo);
		setAddressProperty(order,orderVo,hasAddress);
		//���ö�����
		List<OrderItem> orderItems = OrderItemDao.getItemsByOrderNo(order.getOrderNo());
		setOrderItemProperty(orderItems,orderVo);
		return orderVo;
		
	}
	/**
	 * ��װ����������
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
	 * ��װ������vo
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
	 * ��װ��ַ����
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
	 * ��װ��ַvo
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
	 * ��װ����������
	 * @param order
	 * @param orderVo
	 * @return 
	 */
	private void setNormalProperty(Order order, OrderVo orderVo) {
		orderVo.setOrderNo(order.getOrderNo());
		orderVo.setAmount(order.getAmount());
		orderVo.setType(order.getType());
		orderVo.setTypeDess(ConstUtil.PaymentType.getTypeDesc(order.getType()));//�ù������ȡ
		orderVo.setFieight(order.getFreight());
		orderVo.setStatus(order.getStatus());
		orderVo.setStatusDesc(ConstUtil.OrderStatus.getStatusDesc(order.getStatus()));
		orderVo.setAddrId(order.getAddrId());
		//ʱ��
		orderVo.setPaymentTime(DateUtils.date2Str(order.getPaymentTime()));
		orderVo.setDeliveryTime(DateUtils.date2Str(order.getDeliveryTime()));
		orderVo.setFinishTime(DateUtils.date2Str(order.getFinishTime()));
		orderVo.setCloseTime(DateUtils.date2Str(order.getCloseTime()));
		orderVo.setCreated(DateUtils.date2Str(order.getCreated()));
	}
	/**
	 * ȡ������
	 * ȷ���ջ�
	 */
	@Override
	public SverResponse<String> cancelOrder(Integer userid, Long orderNo) {
		//1.���ݶ����Ų�ѯ����
		Order order = OrderDao.findOrderByUserAndOrderNo(userid,orderNo);
		//2.�ж϶����Ƿ����
		if (order==null) {
			return SverResponse.createByErrorMessage("�û����������ڣ�");
		}
		//3.�ж϶����Ƿ��Ѹ���
		if (order.getStatus()==ConstUtil.OrderStatus.ORDER_PAID) {
			return SverResponse.createByErrorMessage("�����Ѹ����ȡ����");
		}
		//4.�ж�״̬�޸ĵ�ַ��Ϣ
		Order updateOrder = new Order();
		updateOrder.setId(order.getId());
		updateOrder.setUpDate(new Date());
		if (order.getStatus()==1) {
			updateOrder.setStatus(ConstUtil.OrderStatus.ORDER_CANCELED);
			int role = OrderDao.updateOrder(updateOrder);
			if (role>0) {
				return SverResponse.createRespBySuccessMessage("�����Ѿ�ȡ����");
			}
		}
		//�������
		if (order.getStatus()==3) {
			updateOrder.setStatus(ConstUtil.OrderStatus.ORDER_SUCCESS);
			int role = OrderDao.updateOrder(updateOrder);
			if (role>0) {
				return SverResponse.createRespBySuccessMessage("�����Ѿ�ȷ���ջ���");
			}
		}
		return SverResponse.createByErrorMessage("����ȡ��ʧ�ܣ�");
	}
	/**
	 * ���ݶ����Ż�ȡ��������
	 */
	@Override
	public SverResponse<OrderVo> findOrderDetail(Integer userid, Long orderNo) {
		//�жϲ����Ƿ���ȷ
		if (userid == null || orderNo == null) {
			return SverResponse.createByErrorMessage("��������");
		}
		//���Ҷ�����װ
		Order order = OrderDao.findOrderByUserAndOrderNo(userid, orderNo);
		if (order==null) {
			return SverResponse.createByErrorMessage("���û����������ڣ�");
		}
		OrderVo orderVo = createOrderVo1(order, true);
		return SverResponse.createRespBySuccess(orderVo);
	}
	/**
	 * ��������
	 */
	@Override
	public SverResponse<OrderVo> generateOrder(Integer userid, Integer addrId) {
		//1.��ȡ���ﳵ����Ʒ��Ϣ
		List<Cart> carts = CartDao.findCartByUser(userid);
		//2.���㹺�ﳵ��ÿ����Ʒ�۸����ɶ�����
		SverResponse resp = this.cart2OrderItem(userid,carts);
		if (!resp.isSuccess()) {
			return resp;
		}
		//3.ȡ���������м۸񲢼����ܼ۸�
		List<OrderItem> orderItems = (List<OrderItem>) resp.getData();
		BigDecimal totalPrice = this.calcOrderTotalPrice(orderItems);
		//4.���ɶ�����������
		Order order = saveOrder(userid,addrId,totalPrice);
		if (order==null) {
			return SverResponse.createByErrorMessage("����������������������ᣡ");
		}
		if (CollectionUtils.isEmpty(orderItems)) {
			return SverResponse.createByErrorMessage("������Ϊ�գ���ѡ��Ҫ�������Ʒ��");
		}
		//5.�������붩����
		for(OrderItem orderItem:orderItems) {
			//Ϊ��������������
			orderItem.setOrderNo(order.getOrderNo());
			OrderItemDao.batchInsert(orderItems);
		}
		//6.���ٿ��
		for(OrderItem orderItem:orderItems) {
			Product Product = actionProductDao.findProductById(orderItem.getGoodsId());
			//��������
			Product.setStock(Product.getStock() - orderItem.getQuantity());
			Product.setupdated(new Date());
			//���¿��
			actionProductDao.updateProduct(Product);
		}
		//7.��չ��ﳵ
		actionProductDao.deleteCartProduct(userid);
		//8.��װ����ǰ��
		OrderVo orderVo = createOrderVo(order, orderItems);
		return SverResponse.createRespBySuccess(orderVo);
	}

	/**
	 * ���涩��
	 * @param userid
	 * @param addrId
	 * @param totalPrice
	 * @return
	 */
	private Order saveOrder(Integer userid, Integer addrId, BigDecimal totalPrice) {
		Order order = new Order();
		//���ɶ�����
		Long currentTime = System.currentTimeMillis();
		Long orderNo = currentTime+new Random().nextInt(100);
		order.setOrderNo(orderNo);
		order.setStatus(ConstUtil.OrderStatus.ORDER_NO_PAY);//�޸�Ĭ��״̬δ����
		order.setType(ConstUtil.PaymentType.PAY_ON_LINE);//֧��״̬����֧��
		order.setFreight(0);//�ʼ�״̬
		order.setAmount(totalPrice);//�����ܶ�
		order.setAddrId(addrId);
		order.setuId(userid);
		order.setUpDate(new Date());
		order.setCreated(new Date());
		//���붩��
		int rs = OrderDao.insertOrder(order);
		if (rs>0) {
			return order;
		}
		return null;
	}
	/**
	 * ������Ʒ�ܼ۸�
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
	 * �����ﳵ����Ʒ����װΪ������
	 * @param userid
	 * @param carts
	 * @return
	 */
	private SverResponse cart2OrderItem(Integer userid, List<Cart> carts) {
		List<OrderItem> items = Lists.newArrayList();
		//�жϹ��ﳵ�Ƿ�Ϊ��
		if (CollectionUtils.isEmpty(carts)) {
			return SverResponse.createByErrorMessage("���ﳵΪ�գ���ѡ��Ҫ�������Ʒ");
		}
		for (Cart cart:carts) {
			//�鿴���ﳵ��Ʒ״̬
			Product product = actionProductDao.findProductById(cart.getProductId());
			//�鿴��Ʒ״̬
			if (ConstUtil.ProductStatus.STATUS_ON_SALE!=product.getStatus()) {
				//��Ʒ�����ϼ�״̬������ʾ��Ϣ��
				return SverResponse.createByErrorMessage("���¼���Ʒ���������߹���");
			}
			//�鿴���
			if (cart.getQuantity()>product.getStock()) {
				return SverResponse.createByErrorMessage("��Ʒ��治�㣡");
			}
			//��װ����
			OrderItem orderItem = new OrderItem();
			orderItem.setUserId(userid);
			orderItem.setGoodsName(product.getName());
			orderItem.setGoodsId(product.getId());
			orderItem.setIconUrl(product.getIconUrl());
			orderItem.setPrice(product.getPrice());
			orderItem.setQuantity(cart.getQuantity());//����
			orderItem.setTotalPrice(CalcUtil.mul(orderItem.getPrice().doubleValue(),orderItem.getQuantity().doubleValue()));
			orderItem.setCreated(new Date());
			orderItem.setUpdated(new Date());
			items.add(orderItem);
		}
		return SverResponse.createRespBySuccess(items);
	}

	

}
