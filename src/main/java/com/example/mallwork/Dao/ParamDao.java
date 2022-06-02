package com.example.mallwork.Dao;

import com.example.mallwork.Entity.Param;

import java.util.List;

public interface ParamDao {
	/**
	 * 通过父节点id查找子节点参数
	 * @param parentId
	 * @return
	 */
	public List<Param> findParamsByParentId(Integer parentId);
	/**
	 * 根据节点id查找参考对象
	 * @param id
	 * @return
	 */
	public Param findParamById(Integer id);

}
