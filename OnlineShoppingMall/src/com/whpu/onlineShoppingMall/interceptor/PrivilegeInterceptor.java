package com.whpu.onlineShoppingMall.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.whpu.onlineShoppingMall.adminuser.entity.AdminUser;

/**
 * 后台拦截器，对没有登陆的用户，不允许进行访问
* @date: 2019-3-31 
* @author: yangtz
* @title: PrivilegeInterceptor 
* @version: 1.0 
* @description： 
* update_version: update_date: update_author: update_note:
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor{

	//执行拦截的方法
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		// 判断是否登录,如果登录,放行,没有登录,跳转到登录页面.
		AdminUser loginAdminUser = (AdminUser) ServletActionContext.getRequest()
				.getSession().getAttribute("loginAdminUser");
		if(loginAdminUser != null){
			// 已经登录过
			return actionInvocation.invoke();
		}else{
			// 跳转到登录页面:
			ActionSupport support = (ActionSupport) actionInvocation.getAction();
			support.addActionError("您还没有登录!没有权限访问!");
			return "loginFail";
		}
	}

	
}
