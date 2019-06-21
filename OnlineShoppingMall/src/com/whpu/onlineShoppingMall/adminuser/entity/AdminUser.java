package com.whpu.onlineShoppingMall.adminuser.entity;

/**
 * 后台用户管理 
* @date: 2019-3-30 
* @author: yangtz
* @title: AdminUser 
* @version: 1.0 
* @description： 
* update_version: update_date: update_author: update_note:
 */
public class AdminUser {

	private Integer uid;
	private String username;
	private String password;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
