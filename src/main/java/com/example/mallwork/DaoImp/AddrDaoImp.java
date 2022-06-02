package com.example.mallwork.DaoImp;

import com.example.mallwork.Dao.AddrDao;
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

@Repository//数据访问层
public  class AddrDaoImp implements AddrDao {
	
	@Autowired
	private QueryRunner queryRunner;
	/**
	 * 查询默认地址
	 */
	@Override
	public int findDefaultAddrByUserId(Integer userId) {
		//查询
		String sql = "select count(id) as num from action_address where user_id = ? and default_addr=1 and del_state=0";
		try {
			return queryRunner.query(sql,new ColumnListHandler<Long>("num"),userId).get(0).intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * 新增地址信息
	 */
	@Override
	public int instertAddress(Address addr) {
		//新增收获方法
		String sql = "insert into action_address(user_id,name,phone,mobile,"
				+ "province,city,district,addr,zip,default_addr,created,updated) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {
				addr.getUserId(),addr.getName(),addr.getPhone(),addr.getMobile(),
				addr.getProvince(),addr.getCity(),addr.getDistrict(),addr.getAddr(),
				addr.getZip(),addr.getDefaultAddr(),addr.getCreated(),addr.getUpdated()
		};
		//执行
		try {
			return queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * 更新地址信息
	 */
	@Override
	public int updateAddress(Address addr) {
		String sql = "update action_address set updated = ?";
		List<Object> params = new ArrayList<>();
		params.add(addr.getUpdated());
		//使用java中long的工具类判断name是否为空
		if (!StringUtils.isEmpty(addr.getName())) {
			sql+=" ,name = ?";
			params.add(addr.getName());
		}
		//使用java中long的工具类判断phone是否为空
		if (!StringUtils.isEmpty(addr.getPhone())) {
			sql+=" ,phone = ?";
			params.add(addr.getPhone());
		}
		//使用java中long的工具类判断moblie是否为空
		if (!StringUtils.isEmpty(addr.getMobile())) {
			sql+=" ,mobile = ?";
			params.add(addr.getMobile());
		}
		//使用java中long的工具类判断province是否为空
		if (!StringUtils.isEmpty(addr.getProvince())) {
			sql+=" ,province = ?";
			params.add(addr.getProvince());
		}
		//使用java中long的工具类判断city是否为空
		if (!StringUtils.isEmpty(addr.getCity())) {
			sql+=" ,city = ?";
			params.add(addr.getCity());
		}
		//使用java中long的工具类判断district是否为空
		if(!StringUtils.isEmpty(addr.getDistrict())) {
			sql+=",district = ?";
			params.add(addr.getDistrict());
		}
		//使用java中long的工具类判断addr是否为空
		if(!StringUtils.isEmpty(addr.getAddr())) {
			sql+=",addr = ?";
			params.add(addr.getAddr());
		}
		//使用java中long的工具类判断zip是否为空
		if(!StringUtils.isEmpty(addr.getZip())) {
			sql+=",zip = ?";
			params.add(addr.getZip());
		}
		//使用java中long的工具类判断default_addr是否为空
		if(addr.getDefaultAddr()!=null) {
			sql+=",default_addr = ?";
			params.add(addr.getDefaultAddr());
		}
		//使用java中long的工具类判断del_state是否为空
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
	 * 查询用户所有地址信息
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
	 * 读取默认地址
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
	 * 根据收货人id查询地址信息
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
