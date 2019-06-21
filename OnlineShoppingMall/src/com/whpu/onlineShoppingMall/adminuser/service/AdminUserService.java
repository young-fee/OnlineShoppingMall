package com.whpu.onlineShoppingMall.adminuser.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.whpu.onlineShoppingMall.adminuser.dao.AdminUserDao;
import com.whpu.onlineShoppingMall.adminuser.entity.AdminUser;
import com.whpu.onlineShoppingMall.utils.MD5Encoder;

/**
 * 后台用户
* @date: 2019-3-30 
* @author: yangtz
* @title: AdminUserService 
* @version: 1.0 
* @description： 
* update_version: update_date: update_author: update_note:
 */
@Transactional
public class AdminUserService {

	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	/**
	 * 后台登录
	* @date: 2019-3-30 
	* @author: yangtz
	* @title: login 
	* @param adminUser
	* @return 
	* @exception: 
	* @version: 1.0 
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws DataAccessException 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public AdminUser login(AdminUser adminUser) throws DataAccessException, NoSuchAlgorithmException, UnsupportedEncodingException {
		return adminUserDao.login(adminUser);
	}

	/**
	 * 修改后台管理员密码
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: updateAdminPassword 
	* @param adminUser 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public void updateAdminPassword(AdminUser adminUser) {
		adminUserDao.updateAdminPassword(adminUser);
	}
	
}
