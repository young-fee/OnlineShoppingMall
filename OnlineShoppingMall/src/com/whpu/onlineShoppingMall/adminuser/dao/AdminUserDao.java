package com.whpu.onlineShoppingMall.adminuser.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.whpu.onlineShoppingMall.adminuser.entity.AdminUser;
import com.whpu.onlineShoppingMall.utils.MD5Encoder;

/**
 * 后台用户
* @date: 2019-3-30 
* @author: yangtz
* @title: AdminUserDao 
* @version: 1.0 
* @description： 
* update_version: update_date: update_author: update_note:
 */
public class AdminUserDao extends HibernateDaoSupport{

	/**
	 * 
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
		
		String hql = "from AdminUser where username = ?";
		List<AdminUser> list = this.getHibernateTemplate().find(hql,adminUser.getUsername());
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	/**
	 * 修改密码
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
		this.getHibernateTemplate().update(adminUser);
	}

}
