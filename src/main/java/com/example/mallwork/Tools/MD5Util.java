package com.example.mallwork.Tools;

import java.security.MessageDigest;

public class MD5Util {

	private final static String[] hexDigits= {
			"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"
	};
	/**
	 * 转换byte为16进制
	 * @param b 要转换的byte
	 * @return 16进制对应的字符
	 */
	private static String byteToHexString(byte b) {
		int n=b;
		if(n<0) {
			n=256+n;
		}
		int d1=n/16;
		int d2=n%16;
		return hexDigits[d1]+hexDigits[d2];
	}
	/**
	 * 转换字节数组为16进制字符串
	 * @param bytes
	 * @return
	 */
	private static String byteArrayToHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for(byte b:bytes) {
			sb.append(byteToHexString(b));
		}
		return sb.toString();
	}
	/**
	 * MD5加密
	 * @param source 要加密的字符串
	 * @param encoding 指定编码类型
	 * @param upperCase 是否转为大写字符串
	 * @return
	 */
	public static String MD5Encode(String source,String encoding,boolean upperCase) {
		String result = null;
		try {
			result = source;
			//获得MD5摘要对象
			MessageDigest mesageDigest = MessageDigest.getInstance("MD5");
			mesageDigest.update(result.getBytes(encoding));
			result = byteArrayToHexString(mesageDigest.digest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return upperCase?result.toUpperCase():result;
	}
	
}















