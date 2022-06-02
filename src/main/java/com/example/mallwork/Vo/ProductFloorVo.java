package com.example.mallwork.Vo;

import com.example.mallwork.Entity.Product;

import java.util.List;

public class ProductFloorVo {
	private List<Product> oneFloor;//一楼
	private List<Product> twoFloor;//二楼
	private List<Product> threeFloor;//三楼
	private List<Product> fourFloor;//四楼

	public List<Product> getOneFloor() {
		return oneFloor;
	}
	public void setOneFloor(List<Product> oneFloor) {
		this.oneFloor = oneFloor;
	}
	public List<Product> getTwoFloor() {
		return twoFloor;
	}
	public void setTwoFloor(List<Product> twoFloor) {
		this.twoFloor = twoFloor;
	}
	public List<Product> getThreeFloor() {
		return threeFloor;
	}
	public void setThreeFloor(List<Product> threeFloor) {
		this.threeFloor = threeFloor;
	}
	public List<Product> getFourFloor() {
		return fourFloor;
	}
	public void setFourFloor(List<Product> fourFloor) {
		this.fourFloor = fourFloor;
	}

}
