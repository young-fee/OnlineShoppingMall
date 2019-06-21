package com.whpu.onlineShoppingMall.user.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.dao.DataAccessException;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.whpu.onlineShoppingMall.user.entity.User;
import com.whpu.onlineShoppingMall.user.service.UserService;
import com.whpu.onlineShoppingMall.utils.MD5Encoder;

/**
 * 用户模块action
* @date: 2019-3-9 
* @author: yangtz
* @title: UserAction 
* @version: 1.0 
* @description： 
* update_version: update_date: update_author: update_note:
 */
public class UserAction extends ActionSupport implements ModelDriven<User>{
	
	private static final long serialVersionUID = 1L;
	//模型驱动要使用的对象
	private User user = new User();
	//注入userService
	private UserService userService;
	
	//接收验证码
	private String checkCode;
	
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public User getModel() {
		return user;
	}
	/**
	 * 跳转到注册页面
	* @date: 2019-3-9 
	* @author: yangtz
	* @title: userRegistPage 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String registPage(){
		return "registPage";
	}
	
	/**
	 * 查询用户是否存在
	* @date: 2019-3-16 
	* @author: yangtz
	* @title: findByUsername 
	* @param username
	* @return
	* @throws IOException 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String findByUsername() throws IOException{
		//调用service进行查询
		User existUser = userService.findByUsername(user.getUsername());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		//判断
		if(existUser != null){
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
		}else{
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		return NONE;
	}
	/**
	 * 用户注册
	* @date: 2019-3-22 
	* @author: yangtz
	* @title: regist 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String regist(){
		//验证码验证
		String checkCodeForCheck = (String) ServletActionContext.getRequest().getSession().getAttribute("checkCode");
		if(!checkCode.equalsIgnoreCase(checkCodeForCheck)){
			this.addActionError("验证码错误");
			return "registCheckFail";
		}
		userService.save(user);
		this.addActionMessage("注册成功！请去邮箱进行激活");
		return "msg";
	}
	/**
	 * 
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: active 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String active(){
		String code = user.getCode();
		User existUser = userService.findByCode(code);
		if(existUser==null){
			//激活失败
			this.addActionMessage("激活失败，激活码错误");
		}else{
			//成功
			existUser.setState("1");
			existUser.setCode(null);
			userService.update(existUser);
			this.addActionMessage("激活成功！请去登陆");
		}
		return "msg";
	}
	/**
	 * 
	* @date: 2019-3-23 
	* @author: yangtz
	* @title: loginPage 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String loginPage(){
		return "loginPage";
	}
	/**
	 * 用户登录
	* @date: 2019-3-23 
	* @author: yangtz
	* @title: login 
	* @return 
	* @exception: 
	* @version: 1.0 
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws DataAccessException 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String login() throws DataAccessException, NoSuchAlgorithmException, UnsupportedEncodingException{
		//验证码验证
		String checkCodeForCheck = (String) ServletActionContext.getRequest().getSession().getAttribute("checkCode");
		if(!checkCode.equalsIgnoreCase(checkCodeForCheck)){
			this.addActionError("验证码错误");
			return "loginCheckFail";
		}
		User loginUser = userService.login(user);
		if(loginUser == null){
			this.addActionError("用户名错误或用户未激活！");
			return LOGIN;
		}else if(MD5Encoder.validPassword(user.getPassword(), loginUser.getPassword())){
			//信息存入session
			ServletActionContext.getRequest().getSession().setAttribute("loginUser", loginUser);
			return "loginSuccess";
		}else{
			this.addActionError("密码错误！");
			return LOGIN;
		}
	}
	/**
	 * 用户退出的方法
	* @date: 2019-3-23 
	* @author: yangtz
	* @title: quit 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String quit(){
		//销毁session
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
	
}
