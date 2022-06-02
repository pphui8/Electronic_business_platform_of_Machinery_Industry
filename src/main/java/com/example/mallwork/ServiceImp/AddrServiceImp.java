package com.example.mallwork.ServiceImp;

import com.example.mallwork.Common.SverResponse;
import com.example.mallwork.Dao.AddrDao;
import com.example.mallwork.Entity.Address;
import com.example.mallwork.Service.AddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddrServiceImp implements AddrService {
	@Autowired
	private AddrDao actionAddrDao;
	
	/**
	 * 新增收货地址
	 */
	@Override
	public SverResponse<String> addAddress(Address addr) {
		//1.判断参数
		if (addr==null) {
			return SverResponse.createByErrorMessage("参数错误！");
		}
		//2.判断已有地址中是否含有默认地址，如果没有新增地址为默认地址，否则为一般地址
		int count = actionAddrDao.findDefaultAddrByUserId(addr.getUserId());
		System.out.println(count);
		if (count==0) {
			addr.setDefaultAddr(1);
		}
		else {
			addr.setDefaultAddr(0);
		}
		//3.添加地址其他属性
		addr.setCreated(new Date());
		addr.setUpdated(new Date());
		
		//4.插入地址信息
		int rs = actionAddrDao.instertAddress(addr);
		//5.判断是否插入成功
		if (rs>0) {
			return SverResponse.createRespBySuccessMessage("增加地址成功！");
		}
		return SverResponse.createByErrorMessage("地址增加失败！");
	}
	/**
	 * 更新收货地址
	 */
	@Override
	public SverResponse<String> updateAddress(Address addr) {
		//1.判断参数
		if (addr==null) {
			return SverResponse.createByErrorMessage("参数错误！");
		}
		//2.添加其他属性
		addr.setUpdated(new Date());
		int rs  = actionAddrDao.updateAddress(addr);
		if (rs>0) {
			return SverResponse.createRespBySuccessMessage("更新地址成功！");
		}
		return SverResponse.createByErrorMessage("地址更新失败！");
	}
	
	/**
	 * 查询某用户所有地址信息
	 */
	@Override
	public SverResponse<List<Address>> findAddressByUserId(Integer userId) {
		//1.判断参数
		if (userId==null) {
			return SverResponse.createByErrorMessage("参数错误！");
		}
		List<Address> list = actionAddrDao.findAddressByUserId(userId);
		return SverResponse.createRespBySuccess(list);
	}
	/**
	 * 删除地址
	 */
	@Override
	public SverResponse<String> deleteAddress(Integer userId, Integer id) {
		//1.判断参数
		if (id==null) {
			return SverResponse.createByErrorMessage("参数错误！");
		}
		//2.删除地址，对del_state字段进行修改
		Address Address = new Address();
		Address.setId(id);
		Address.setDelState(1);
		Address.setUpdated(new Date());
		int rs = actionAddrDao.updateAddress(Address);
		if (rs>0) {
			return SverResponse.createRespBySuccessMessage("删除地址成功！");
		}
		return SverResponse.createByErrorMessage("地址删除失败！");
	}
	/**
	 * 更新默认地址
	 */
	@Override
	public SverResponse<String> updateDeaufultAddress(Integer userId, Integer id) {
		int a;
		//1.判断参数
		if (id==null||userId==null) {
			return SverResponse.createByErrorMessage("参数错误！");
		}
		//2.读取原先默认地址
		Address oldAddress = actionAddrDao.findDefaultAddr(userId);
		if (oldAddress!=null) {
			//取消默认地址
			oldAddress.setDefaultAddr(0);
			oldAddress.setUpdated(new Date());
//			System.out.println(oldAddress.getUserId());
//			System.out.println("00000"+oldAddress.getId());
//			a= actionAddrDao.updateAddress(oldAddress);
//			System.out.println(a);
			if (actionAddrDao.updateAddress(oldAddress)>=0) {
				return SverResponse.createByErrorMessage("设置默认地址失败！");
			}
		}
		//3.设置默认地址
		Address newAddress = new Address();
		newAddress.setDefaultAddr(1);
		newAddress.setId(id);
		newAddress.setUpdated(new Date());
		
		if (actionAddrDao.updateAddress(newAddress)<=0) {
			return SverResponse.createByErrorMessage("设置默认地址失败！");
		}
		return SverResponse.createRespBySuccessMessage("默认地址设置成功！");
	}
}
