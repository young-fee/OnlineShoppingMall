package com.whpu.onlineShoppingMall.user.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.whpu.onlineShoppingMall.user.entity.User;
import com.whpu.onlineShoppingMall.utils.MD5Encoder;
import com.whpu.onlineShoppingMall.utils.PageHibernateCallback;

/**
 * 用户模块持久层代码
* @date: 2019-3-15 
* @author: yangtz
* @title: UserDao 
* @version: 1.0 
* @description： 
* update_version: update_date: update_author: update_note:
 */
public class UserDao extends HibernateDaoSupport{
	/**
	 * 按用户名查询用户是否存在
	* @date: 2019-3-15 
	* @author: yangtz
	* @title: findByUsername 
	* @param username
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public User findByUsername(String username){
		String hql = "from User where username = ?";
		List<User> list = this.getHibernateTemplate().find(hql,username);
		System.out.println("list"+list);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
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
		this.getHibernateTemplate().save(user);
	}

	/**
	 * 激活
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
		String hql = "from User where code = ?";
		List<User> list = this.getHibernateTemplate().find(hql,code);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
			return null;
	}

	/**
	 * 修改用户状态
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
		this.getHibernateTemplate().update(existUser);
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
		
		String hql1 = "from User where username = ? and state = ?";
		List<User> list = this.getHibernateTemplate().find(hql1,user.getUsername(),"1");
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	public int findCount() {
		String hql = "select count(*) from User";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * 
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: findByPage 
	* @param begin
	* @param limit
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public List<User> findByPage(int begin, int limit) {
		String hql = "from User";
		List<User> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<User>(hql, null, begin, limit));
		return list;
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
		return this.getHibernateTemplate().get(User.class, uid);
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
		this.getHibernateTemplate().delete(existUser);
	}
}
