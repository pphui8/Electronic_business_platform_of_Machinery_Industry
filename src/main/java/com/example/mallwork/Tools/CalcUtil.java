package com.example.mallwork.Tools;

import java.math.BigDecimal;

public class CalcUtil {
	private CalcUtil() {
		
	}
	
	public static BigDecimal mul(double v1,double v2) {
		BigDecimal t1 = new BigDecimal(Double.toString(v1));
		BigDecimal t2 = new BigDecimal(Double.toString(v2));
		return t1.multiply(t2);
	}
	
	public static BigDecimal add(double v1,double v2) {
		BigDecimal t1 = new BigDecimal(Double.toString(v1));
		BigDecimal t2 = new BigDecimal(Double.toString(v2));
		return t1.add(t2);
	}

}
