package com.example.mallwork.Controller;

import com.example.mallwork.Common.SverResponse;
import com.example.mallwork.Entity.User;
import com.example.mallwork.Service.UserService;
import com.example.mallwork.Tools.ConstUtil;
import com.example.mallwork.Vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService actionUserService;

	@RequestMapping("/do_login.do")
	@ResponseBody
	public SverResponse<User> doLogin(HttpSession session, String account, String password) {
		//1.调用service登录方法
		SverResponse<User> response = actionUserService.doLogin(account, password);
		//2.判断是否能登录登录后状态存储在session中
		if (response.isSuccess()) {
			//3.能登录则判断是否是管理员，是管理员存放在session，否则报告错误信息
			User user = response.getData();
			if(user.getRole()== ConstUtil.Role.ROLE_ADMIN) {
				session.setAttribute(ConstUtil.CUR_USER, user);
				return response;
			}
//			return SverResponse.createByErrorMessage("不是管理员无法登录！");
			if (user.getRole()==ConstUtil.Role.ROLE_CUSTOMER) {
				session.setAttribute(ConstUtil.CUR_USER, user);
				return response;
			}
		}
		return response;
	}
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@RequestMapping("/do_register.do")
	@ResponseBody
	public SverResponse<String> registerUser(User user){
		
		return actionUserService.doRegister(user);
		
	}
	/**
	 * 验证用户并获得用户对象
	 * @param account
	 * @return
	 */
	@RequestMapping("getUserByAccount.do")
	@ResponseBody
	public SverResponse<User> getUserByAccount(String account) {
		return actionUserService.findUserByAccount(account);
		
	}
	/**
	 * 注销登录
	 * @param request
	 * @return
	 */
	@RequestMapping("/do_logout.do")
	@ResponseBody
	public SverResponse<String> doLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute(ConstUtil.CUR_USER);
		return SverResponse.createRespBySuccessMessage("注销成功！");
	}
	/**
	 * 验证用户密码修改,问题答案
	 * @param account
	 * @param question
	 * @param asw
	 * @return
	 */
	@RequestMapping("/checkuserasw.do")
	@ResponseBody
	public SverResponse<String> checkUserAnswer(String account,String question,String asw) {
		return actionUserService.checkUserAnswer(account,question,asw);
		
	}
	
	/**
	 * 重置密码
	 * @param userId
	 * @param newpwd
	 * @return
	 */
	@RequestMapping("/resetpassword.do")
	@ResponseBody
	public SverResponse<String> resetPassword(Integer userId,String newpwd) {
		return actionUserService.resetPassword(userId,newpwd);
		
	}
	/**
	 * 修改用户个人资料
	 * @param session
	 * @param userVo
	 * @return
	 */
	//Vo业务实体类
	@RequestMapping("/updateuserinfo.do")
	@ResponseBody
	public SverResponse<User> updateUserInfo(HttpSession session, UserVo userVo) {
		//1.获得当前页面user对象
		User curUser = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (curUser==null) {
			return SverResponse.createByErrorMessage("用户尚未登录!");
		}
		userVo.setId(curUser.getId());
		userVo.setAccount(curUser.getAccount());
		SverResponse<User> resp = actionUserService.updateUserInfo(userVo);
		if (resp.isSuccess()) {
			//2.重写session
			session.setAttribute(ConstUtil.CUR_USER, resp.getData());
		}
		return resp;
		
	}
	/**
	 * 修改密码
	 * @param session
	 * @param newpwd
	 * @param oldpwd
	 * @return
	 */
	//修改密码
	@RequestMapping("/updatepassword.do")
	@ResponseBody
	public SverResponse<String> updatePassword(HttpSession session,String newpwd,String oldpwd) {
		//1.将session取出
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user==null) {
			return SverResponse.createByErrorMessage("请先登录！");
		}
		SverResponse<String> result = actionUserService.updatePassword(user,newpwd,oldpwd);
		//2.修改成功后请空session重新登录
		if (result.isSuccess()) {
			session.removeAttribute(ConstUtil.CUR_USER);
		}
		return result;
		
	}
	/**
	 * 获得用户密码问题
	 * @param account
	 * @return
	 */
	@RequestMapping("/getuserquestion.do")
	@ResponseBody
	public SverResponse<String> getUserQuestion(String account) {
		return actionUserService.getUserQuestion(account);
	}
	
	@RequestMapping(value = "/getuserinfo.do",method = RequestMethod.GET)
	@ResponseBody
	public SverResponse<User> getUserInfo(HttpSession session){
		User user= (User) session.getAttribute(ConstUtil.CUR_USER);
		if (user==null){
			return SverResponse.createByErrorMessage("无法获取用户信息!");
		}
		else
		{
			return SverResponse.createRespBySuccess(user);
		}
	}
	

	@RequestMapping("/do_check_info.do")
	@ResponseBody
	public SverResponse<String> checkindo(String info,String type){
		return actionUserService.checkValidation(info,type);
	}

}
