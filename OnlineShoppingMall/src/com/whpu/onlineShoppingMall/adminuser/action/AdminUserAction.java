package com.whpu.onlineShoppingMall.adminuser.action;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.apache.struts2.ServletActionContext;
import org.springframework.dao.DataAccessException;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.whpu.onlineShoppingMall.adminuser.entity.AdminUser;
import com.whpu.onlineShoppingMall.adminuser.service.AdminUserService;
import com.whpu.onlineShoppingMall.utils.MD5Encoder;

/**
 * 后台管理人员action
* @date: 2019-3-30 
* @author: yangtz
* @title: AdminUserAction 
* @version: 1.0 
* @description： 
* update_version: update_date: update_author: update_note:
 */
public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{

	private AdminUser adminUser = new AdminUser();
	private AdminUserService adminUserService;
	
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}

	public AdminUser getModel() {
		return adminUser;
	}

	/**
	 * 后台登录
	* @date: 2019-3-30 
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
		
		AdminUser loginAdminUser = adminUserService.login(adminUser);
		// 判断
		if (loginAdminUser == null) {
			// 用户名或密码错误
			this.addActionError("用户名错误!");
			return "loginFail";
		} else if(!MD5Encoder.validPassword(adminUser.getPassword(), loginAdminUser.getPassword())){
			this.addActionError("密码错误!");
			return "loginFail";
		} else{
			// 登录成功:
			ServletActionContext.getRequest().getSession()
					.setAttribute("loginAdminUser", loginAdminUser);
			return "loginSuccess";
		}
	}
	/**
	 * 
	* @date: 2019-3-31 
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
	
	/**
	 * 修改后台管理员密码
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: updateAdminPassword  
	* @exception: 
	* @version: 1.0 
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String updateAdminPassword() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		String oldPass = ServletActionContext.getRequest().getParameter("oldPass");
		String newPass = ServletActionContext.getRequest().getParameter("newPass");
		String username = ServletActionContext.getRequest().getParameter("username");
		AdminUser loginAdminUser = (AdminUser) ServletActionContext.getRequest().getSession().getAttribute("loginAdminUser");
		if(MD5Encoder.validPassword(oldPass, loginAdminUser.getPassword())){
			adminUser.setPassword(MD5Encoder.getEncryptedPwd(newPass));
			adminUser.setUsername(username);
			adminUser.setUid(loginAdminUser.getUid());
			adminUserService.updateAdminPassword(adminUser);
			this.addActionMessage("密码修改成功，请重新登录!");
			ServletActionContext.getRequest().getSession().setAttribute("loginAdminUser", adminUser);
			return "updateSuccess";
		}else{
			this.addActionMessage("旧密码错误!");
			return "updateFail";
		}
	}
	/**
	 * 修改密码跳转
	* @date: 2019-4-23 
	* @author: yangtz
	* @title: updatePassPage 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String updatePassPage(){
		return "updatePassPage";
	}
	/**
	 * 首页跳转
	* @date: 2019-4-23 
	* @author: yangtz
	* @title: toMainPage 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String toMainPage(){
		return "toMainPage";
	}
	
	public static void main(String[] args) {
		
		try {
			String pass = "test";
			String MD = MD5Encoder.getEncryptedPwd(pass);
			System.out.println(pass+"加密之后"+MD);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
