package com.example.mallwork.dao;

import com.example.mallwork.Entity.Param;

import java.util.List;

public interface ParamDao {
	/**
	 * ͨ�����ڵ�id�����ӽڵ����
	 * @param parentId
	 * @return
	 */
	public List<Param> findParamsByParentId(Integer parentId);
	/**
	 * ���ݽڵ�id���Ҳο�����
	 * @param id
	 * @return
	 */
	public Param findParamById(Integer id);

}
