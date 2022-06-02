package com.example.mallwork.Service;

import com.example.mallwork.Common.SverResponse;
import com.example.mallwork.Entity.Param;

import java.util.List;

public interface ParamService {
	/**
	 * 查询所有商品分类信息
	 * @return
	 */
	public SverResponse<List<Param>> findAllParams();

}
