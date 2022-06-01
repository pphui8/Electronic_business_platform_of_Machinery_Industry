package com.example.mallwork.Tools;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static String date2Str(Date date,String formatStr) {
		if(date == null || formatStr==null) {
			return StringUtils.EMPTY;
		}
		DateFormat formater = new SimpleDateFormat(formatStr);
		return formater.format(date);
	}
	
	public static String date2Str(Date date) {
		return date2Str(date, STANDARD_FORMAT);
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(date2Str(new Date()));
	}
}
