package com.example.mallwork.Vo;

import java.math.BigDecimal;
import java.util.List;

public class CartVo {
	private List<CartsListVo> lists;
	private BigDecimal totalPrice; //×Ü¼Û¸ñ
	public List<CartsListVo> getLists() {
		return lists;
	}
	public void setLists(List<CartsListVo> lists) {
		this.lists = lists;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
