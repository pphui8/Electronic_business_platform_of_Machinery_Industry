package com.example.mallwork.Vo;

import java.math.BigDecimal;
import java.util.List;



public class OrderVo {
	private Long orderNo;//订单编号
	private BigDecimal amount;//订单价格
	private Integer type;//支付类型
	private String typeDess;//支付描述
	private Integer fieight;//邮费支付类型
	private Integer status;//订单状态
	private String statusDesc;//订单状态描述
	private String paymentTime;//支付时间
	private String finishTime;//交易完成时间
	private String closeTime;//订单关闭时间
	private String created;//订单创建时间
	
	
	private List<OrderItemVo> orderItems;//订单描述
	private Integer addrId;//收获地址Id
	private String deliveryName;//收货人姓名
	private String deliveryTime;//收货时间
	private AddressVo address;//收货人详情
	
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTypeDess() {
		return typeDess;
	}
	public void setTypeDess(String typeDess) {
		this.typeDess = typeDess;
	}
	public Integer getFieight() {
		return fieight;
	}
	public void setFieight(Integer fieight) {
		this.fieight = fieight;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}
	public String getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	public String getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public List<OrderItemVo> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItemVo> orderItems) {
		this.orderItems = orderItems;
	}
	public Integer getAddrId() {
		return addrId;
	}
	public void setAddrId(Integer addrId) {
		this.addrId = addrId;
	}
	public String getDeliveryName() {
		return deliveryName;
	}
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}
	public String getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public AddressVo getAddress() {
		return address;
	}
	public void setAddress(AddressVo address) {
		this.address = address;
	}

}
