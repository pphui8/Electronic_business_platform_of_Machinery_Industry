package com.example.mallwork.daoImp;

import com.example.mallwork.dao.AddrDao;
import com.example.mallwork.Entity.Address;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public  class AddrDaoImp implements AddrDao {
	
	@Autowired
	private QueryRunner queryRunner;
	/**
	 * ????????
	 */
	@Override
	public int findDefaultAddrByUserId(Integer userId) {
		//???
		String sql = "select count(id) as num from action_address where user_id = ? and default_addr=1 and del_state=0";
		try {
			return queryRunner.query(sql,new ColumnListHandler<Long>("num"),userId).get(0).intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * ??????????
	 */
	@Override
	public int instertAddress(Address addr) {
		//????????
		String sql = "insert into action_address(user_id,name,phone,mobile,"
				+ "province,city,district,addr,zip,default_addr,created,updated) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {
				addr.getUserId(),addr.getName(),addr.getPhone(),addr.getMobile(),
				addr.getProvince(),addr.getCity(),addr.getDistrict(),addr.getAddr(),
				addr.getZip(),addr.getDefaultAddr(),addr.getCreated(),addr.getUpdated()
		};
		//???
		try {
			return queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * ?????????
	 */
	@Override
	public int updateAddress(Address addr) {
		String sql = "update action_address set updated = ?";
		List<Object> params = new ArrayList<>();
		params.add(addr.getUpdated());
		//???java??long????????§Ø?name??????
		if (!StringUtils.isEmpty(addr.getName())) {
			sql+=" ,name = ?";
			params.add(addr.getName());
		}
		//???java??long????????§Ø?phone??????
		if (!StringUtils.isEmpty(addr.getPhone())) {
			sql+=" ,phone = ?";
			params.add(addr.getPhone());
		}
		//???java??long????????§Ø?moblie??????
		if (!StringUtils.isEmpty(addr.getMobile())) {
			sql+=" ,mobile = ?";
			params.add(addr.getMobile());
		}
		//???java??long????????§Ø?province??????
		if (!StringUtils.isEmpty(addr.getProvince())) {
			sql+=" ,province = ?";
			params.add(addr.getProvince());
		}
		//???java??long????????§Ø?city??????
		if (!StringUtils.isEmpty(addr.getCity())) {
			sql+=" ,city = ?";
			params.add(addr.getCity());
		}
		//???java??long????????§Ø?district??????
		if(!StringUtils.isEmpty(addr.getDistrict())) {
			sql+=",district = ?";
			params.add(addr.getDistrict());
		}
		//???java??long????????§Ø?addr??????
		if(!StringUtils.isEmpty(addr.getAddr())) {
			sql+=",addr = ?";
			params.add(addr.getAddr());
		}
		//???java??long????????§Ø?zip??????
		if(!StringUtils.isEmpty(addr.getZip())) {
			sql+=",zip = ?";
			params.add(addr.getZip());
		}
		//???java??long????????§Ø?default_addr??????
		if(addr.getDefaultAddr()!=null) {
			sql+=",default_addr = ?";
			params.add(addr.getDefaultAddr());
		}
		//???java??long????????§Ø?del_state??????
		if(addr.getDelState()!=null) {
			sql+=",del_state = ?";
			params.add(addr.getDelState());
		}
		sql+= " where id = ?";
		params.add(addr.getId());
		try {
			return queryRunner.update(sql, params.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * ?????????§Ö?????
	 */
	@Override
	public List<Address> findAddressByUserId(Integer userId) {
		String sql="select id,user_id as userId,name,phone,mobile,province,city,"
				+"district,addr,zip,default_addr as defaultAddr,del_state as delState,created,updated"
				+ " from action_address where user_id  = ? and del_state=0 order by default_addr desc,updated desc";
		try {
			return queryRunner.query(sql, new BeanListHandler<Address>(Address.class), userId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * ????????
	 */
	@Override
	public Address findDefaultAddr(Integer userId) {
		String sql="select id,user_id as userId,name,phone,mobile,province,city,"
				+"district,addr,zip,default_addr as defaultAddr,del_state as delState,created,updated"
				+ " from action_address where user_id=? and default_addr=1";
		try {
			return queryRunner.query(sql, new BeanHandler<Address>(Address.class),userId);
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * ?????????id?????????
	 */
	@Override
	public Address findAddressById(Integer addrId) {
		String sql="select id,user_id as userId,name,phone,mobile,province,city,"
				+"district,addr,zip,default_addr as defaultAddr,del_state as delState,created,updated"
				+ " from action_address where id=? and del_state=0";
		try {
			return queryRunner.query(sql, new BeanHandler<Address>(Address.class), addrId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
