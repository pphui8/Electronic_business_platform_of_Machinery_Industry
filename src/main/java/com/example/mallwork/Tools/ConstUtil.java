package com.example.mallwork.Tools;

public class ConstUtil {
	//当前用户标识
	public static final String CUR_USER="curUser";
	
	//用户可以验证的信息
	public static final String TYPE_ACCOUNT="account";
	public static final String TYPE_EMAIL="email";
	public static final String TYPE_PHONE="phone";
	//图片文件上传路径
	public static final String UPLOAD_IMAGES_PATH="/upload/";
	
	/**
	 * 用户角色常量
	 */
	public static class Role{
		public static final int ROLE_CUSTOMER=1;
		public static final int ROLE_ADMIN=2;
	}
	//商品状态常量
	public static class ProductStatus{
		public static final int STATUS_NEW=1;        //新增，待售
		public static final int STATUS_ON_SALE=2;	//上架，在售
		public static final int STATUS_OFF_SALE=3;   //下架，
		public static String getStatusDesc(int code) {
			String desc = "未知状态";
			switch (code) {
			case STATUS_NEW:
				desc="待售";
				break;
			case STATUS_ON_SALE:
				desc="在售";
				break;
			case STATUS_OFF_SALE:
				desc="下架";
				break;
			default:
				break;
			}
			return desc;
		}
	}
	//订单状态
	public static class OrderStatus{
		
		public static final int ORDER_NO_PAY=1;		//未付款
		public static final int ORDER_PAID=2;		//已经付款
		public static final int ORDER_SHIPPED=3;	//已经发货
		public static final int ORDER_SUCCESS=4;	//订单完成
		public static final int ORDER_CLOESD = 5;	//订单关闭
		public static final int ORDER_CANCELED=6;  //订单取消
		public static String getStatusDesc(int code) {
			String desc="未知状态";
			switch (code) {
			case ORDER_CANCELED:
				desc="订单取消";
				break;
			case ORDER_NO_PAY:
				desc="未付款";
				break;
			case ORDER_PAID:
				desc="已付款";
				break;
			case ORDER_SHIPPED:
				desc="已发货";
				break;
			case ORDER_SUCCESS:
				desc="交易完成";
				break;
			case ORDER_CLOESD:
				desc="交易关闭";
				break;
			default:
				break;
			}
			return desc;
		}
	}
	//付款方式
	public static class PaymentType{
		public static final int PAY_ON_LINE=1;    //在线支付
		public static final int PAY_CASH_ON_DELIVERY=2;//货到付款
		
		public static String getTypeDesc(int type) {
			if(type==PAY_ON_LINE) {
				return "在线支付";
			}else if(type==PAY_CASH_ON_DELIVERY) {
				return "到付";
			}else {
				return "未知方式";
			}
		}
	}

	public static class HotStatus{
		public static final int HOT_STATUS=1;//热销
		public static final int NORMAL_STATUS=2;	//非热销
		public static String getHotDesc(int hot) {
			if(hot==HOT_STATUS) {
				return "热销";
			}else {
				return "一般";
			}
		}
	}
	
	public static class ProductType{
		public static final int TYPE_HNTJX=10023;	//混凝土机械
		public static final int TYPE_JZQZJJX=10024;	//建筑起重机机械
		public static final int TYPE_GCQZJJX=10025;	//工程起重机机械
		public static final int TYPE_LMJX=10026;	//路面机械
	}

}
