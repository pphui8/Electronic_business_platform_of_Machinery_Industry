package com.example.mallwork.ServiceImp;

import com.example.mallwork.Common.SverResponse;
import com.example.mallwork.dao.ParamDao;
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
	 * ������Ʒ������չʾ
	 */
	@Override
	public SverResponse<List<Param>> findAllParams() {
		//����һ���ӽڵ�
		List<Param> paramList = ParamDao.findParamsByParentId(0);
		//�ݹ��ѯ�����ӽڵ�
		for(Param param : paramList) {
			findDirectChildren(param);
		}
		return SverResponse.createRespBySuccess(paramList);
	}
	//�ݹ����
	private void findDirectChildren(Param parentParam) {
		//�����ӽڵ�
		List<Param> paramList = ParamDao.findParamsByParentId(parentParam.getId());
		parentParam.setChildren(paramList);
		for(Param p:paramList) {
			findDirectChildren(p);
		}
	}
}
