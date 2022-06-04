package com.example.mallwork.dao.impl;

import com.example.mallwork.dao.ParamDao;
import com.example.mallwork.Entity.Param;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class ParamDaoImp implements ParamDao {
	@Autowired
	private QueryRunner queryRunner;
	@Override
	public List<Param> findParamsByParentId(Integer parentId) {
		String sql = "select id , parent_id as parentId,name,sort_order as sortOrder,"
				+ "status,created,updated,level from kind where "
				+ "parent_id = ? order by sort_order";
		try {
			return queryRunner.query(sql, new BeanListHandler<Param>(Param.class), parentId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * ���ݽڵ�id���Ҷ���
	 */
	@Override
	public Param findParamById(Integer id) {
		String sql ="select id, parent_id,name,sort_order,status,created,updated,level from kind where id = ?";
		try {
			return queryRunner.query(sql, new BeanHandler<Param>(Param.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
