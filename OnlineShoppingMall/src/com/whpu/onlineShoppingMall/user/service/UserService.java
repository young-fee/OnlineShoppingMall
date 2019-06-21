package com.whpu.onlineShoppingMall.user.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.whpu.onlineShoppingMall.user.dao.UserDao;
import com.whpu.onlineShoppingMall.user.entity.User;
import com.whpu.onlineShoppingMall.utils.MD5Encoder;
import com.whpu.onlineShoppingMall.utils.MailUtils;
import com.whpu.onlineShoppingMall.utils.PageBean;
import com.whpu.onlineShoppingMall.utils.UUIDUtils;

/**
 * 用户模块业务层代码 
* @date: 2019-3-15 
* @author: yangtz
* @title: UserService 
* @version: 1.0 
* @description： 
* update_version: update_date: update_author: update_note:
 */
@Transactional
public class UserService {
	//注入userDao
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	/**
	 * 按用户名查询用户
	* @date: 2019-3-15 
	* @author: yangtz
	* @title: findByUsername 
	* @param username 
	* @exception: 
	* @version: 1.0 
	 * @return 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public User findByUsername(String username){
		return userDao.findByUsername(username);		
	}
	/**
	 * 用户注册
	* @date: 2019-3-22 
	* @author: yangtz
	* @title: save 
	* @param user 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public void save(User user) {
		//盐值加密
		try {
			String registPassword = user.getPassword();
			String MdPassword = MD5Encoder.getEncryptedPwd(registPassword);
			user.setPassword(MdPassword);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		user.setState("0");//0表示未激活    1表示已激活
		String code = UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		//发送激活邮件
		MailUtils.sendMail(user.getEmail(), code);
	}
	/**
	 * 
	* @date: 2019-3-23 
	* @author: yangtz
	* @title: findByCode 
	* @param code 
	* @exception: 
	* @version: 1.0 
	 * @return 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}
	/**
	 * 
	* @date: 2019-3-23 
	* @author: yangtz
	* @title: update 
	* @param existUser 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public void update(User existUser) {
		userDao.update(existUser);
	}
	/**
	 * 
	* @date: 2019-3-23 
	* @author: yangtz
	* @title: login 
	* @param user
	* @return 
	* @exception: 
	* @version: 1.0 
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws DataAccessException 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public User login(User user) throws DataAccessException, NoSuchAlgorithmException, UnsupportedEncodingException {
		return userDao.login(user);
	}
	
	/**
	 * 业务层用户查询所有
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: findByPage 
	* @param page
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public PageBean<User> findByPage(Integer page) {
		PageBean<User> pageBean = new PageBean<User>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		// 显示5个
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = userDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示数据集合:
		int begin = (page - 1)*limit;
		List<User> list = userDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: findByUid 
	* @param uid
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public User findByUid(Integer uid) {
		return userDao.findByUid(uid);
	}

	/**
	 * 
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: delete 
	* @param existUser 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public void delete(User existUser) {
		userDao.delete(existUser);
	}
}
