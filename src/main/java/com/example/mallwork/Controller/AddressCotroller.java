package com.example.mallwork.Controller;

import com.example.mallwork.Common.SverResponse;
import com.example.mallwork.Entity.Address;
import com.example.mallwork.Entity.User;
import com.example.mallwork.Service.AddrService;
import com.example.mallwork.Tools.ConstUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/addr")
public class AddressCotroller {
	@Autowired
	private AddrService AddrService;
	/**
	 * 新增地址
	 * @param session
	 * @param addr
	 * @return
	 */
	@RequestMapping("/saveaddr.do")
	@ResponseBody
	public SverResponse<List<Address>> saveAddress(HttpSession session, Address addr) {
		//1.判断User是否登录
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user==null) {
			return SverResponse.createByErrorMessage("请登录后再进行操作！");
		}
		addr.setUserId(user.getId());//赋值
		
		//判断是修改还是添加
		SverResponse<String> result = null;
		if (addr.getId()==null) {//添加
			result = AddrService.addAddress(addr);
		}else {//修改
			result = AddrService.updateAddress(addr);
		}
		//返回当前用户的所有地址
		if (result.isSuccess()) {
			return AddrService.findAddressByUserId(user.getId());
		}
		return SverResponse.createByErrorMessage(result.getMsg());
		
	}
	/**
	 * 修改地址
	 * @param session
	 * @param addr
	 * @return
	 */
	@RequestMapping("/findAddressById.do ")
	@ResponseBody
	public SverResponse<List<Address>> changeAddress(HttpSession session,Address addr) {
		//1.判断User是否登录
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user==null) {
			return SverResponse.createByErrorMessage("请登录后再进行操作！");
		}
		addr.setUserId(user.getId());//赋值
		
		//判断是修改还是添加
		SverResponse<String> result = null;
		if (addr.getId()==null) {//添加
			result = AddrService.addAddress(addr);
		}else {//修改
			result = AddrService.updateAddress(addr);
		}
		//返回当前用户的所有地址
		if (result.isSuccess()) {
			return AddrService.findAddressByUserId(user.getId());
		}
		return SverResponse.createByErrorMessage(result.getMsg());
		
	}
	/**
	 * 删除地址信息
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping("/deladdr.do")
	@ResponseBody
	public SverResponse<List<Address>> deleteAddress (HttpSession session,Integer id) {
		//1.判断User是否登录
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user==null) {
			return SverResponse.createByErrorMessage("请登录后再进行操作！");
		}
		//2.调用删除方法，隐形删除地址
		SverResponse<String> result = AddrService.deleteAddress(user.getId(),id);
		//3.删除地址后返回当前用户所有地址
		if (result.isSuccess()) {
			return AddrService.findAddressByUserId(user.getId());
		}
		return SverResponse.createByErrorMessage(result.getMsg());
		
	}
	/**
	 * 设置默认地址
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping("setdefault.do")
	@ResponseBody
	public SverResponse<List<Address>> setDefaultAddress(HttpSession session, Integer id) {
		//1.判断User是否登录
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user==null) {
			return SverResponse.createByErrorMessage("请登录后再进行操作！");
		}
		//2.更新信息
		SverResponse<String> result = AddrService.updateDeaufultAddress(user.getId(),id);
		if (result.isSuccess()) {
			return AddrService.findAddressByUserId(user.getId());
		}
		return SverResponse.createByErrorMessage(result.getMsg());
	}
	/**
	 * 查找登录用户的所有地址信息
	 * @param session
	 * @return
	 */
	@RequestMapping("/findaddrs.do")
	@ResponseBody
	public SverResponse<List<Address>> findAdderss(HttpSession session) {
		//1.判断User是否登录
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user==null) {
			return SverResponse.createByErrorMessage("请登录后再进行操作！");
		}
		return AddrService.findAddressByUserId(user.getId());
		
	}
}
