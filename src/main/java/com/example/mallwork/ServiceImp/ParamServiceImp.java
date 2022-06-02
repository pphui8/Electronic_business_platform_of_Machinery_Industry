package com.example.mallwork.ServiceImp;

import com.example.mallwork.Common.SverResponse;
import com.example.mallwork.Dao.ParamDao;
import com.example.mallwork.Entity.Param;
import com.example.mallwork.Service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParamServiceImp implements ParamService {
	@Autowired
	public ParamDao ParamDao;
	/**
	 * 查找商品，分类展示
	 */
	@Override
	public SverResponse<List<Param>> findAllParams() {
		//查找一级子节点
		List<Param> paramList = ParamDao.findParamsByParentId(0);
		//递归查询所有子节点
		for(Param param : paramList) {
			findDirectChildren(param);
		}
		return SverResponse.createRespBySuccess(paramList);
	}
	//递归查找
	private void findDirectChildren(Param parentParam) {
		//查找子节点
		List<Param> paramList = ParamDao.findParamsByParentId(parentParam.getId());
		parentParam.setChildren(paramList);
		for(Param p:paramList) {
			findDirectChildren(p);
		}
	}
}
