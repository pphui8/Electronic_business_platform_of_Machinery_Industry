package com.example.mallwork.Service;

import com.example.mallwork.Common.SverResponse;
import com.example.mallwork.Tools.PageBean;
import com.example.mallwork.Vo.OrderVo;

public interface OrderService {
/**
 * 订单分页列表
 * @param userid
 * @param status
 * @param pageNum
 * @param pageSize
 * @return
 */
	public SverResponse<PageBean<OrderVo>> findOrder(Integer userid, Integer status, int pageNum, int pageSize);
/**
 * 取消订单
 * @param id
 * @param orderNo
 * @return
 */
public SverResponse<String> cancelOrder(Integer id, Long orderNo);
/**
 * 根据订单号获取订单详情
 * @param userid
 * @param orderNo
 * @return
 */
public SverResponse<OrderVo> findOrderDetail(Integer userid, Long orderNo);
/**
 * 创建订单
 * @param userid
 * @param addrId
 * @return
 */
public SverResponse<OrderVo> generateOrder(Integer userid, Integer addrId);

}
