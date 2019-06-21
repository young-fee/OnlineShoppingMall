package com.whpu.onlineShoppingMall.user.adminaction;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.whpu.onlineShoppingMall.user.entity.User;
import com.whpu.onlineShoppingMall.user.service.UserService;
import com.whpu.onlineShoppingMall.utils.MD5Encoder;
import com.whpu.onlineShoppingMall.utils.PageBean;
/**
 * 后台用户管理的Action类
 * @author 传智.郭嘉
 *
 */
public class UserAdminAction extends ActionSupport implements ModelDriven<User>{
	// 模型驱动使用的类
	private User user = new User();

	public User getModel() {
		return user;
	}
	
	// 注入用户的Service
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// 接受page参数
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	/**
	 * 后台查询所有用户的方法带分页:
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: findAll 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String findAll(){
		PageBean<User> pageBean = userService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	/**
	 * 后台用户的删除
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: delete 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String delete(){
		User existUser = userService.findByUid(user.getUid());
		userService.delete(existUser);
		return "deleteSuccess";
	}
	
	/**
	 * 后台用户的编辑
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: edit 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String edit(){
		user = userService.findByUid(user.getUid());
		return "editSuccess";
	}
	
	/**
	 * 台用户的修改:
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: update 
	* @return 
	* @exception: 
	* @version: 1.0 
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String update() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		//密码加密
		String updatePassword = user.getPassword();
		user.setPassword(MD5Encoder.getEncryptedPwd(updatePassword));
		userService.update(user);
		return "updateSuccess";
	}
	
}
