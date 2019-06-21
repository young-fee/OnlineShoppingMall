package com.whpu.onlineShoppingMall.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.whpu.onlineShoppingMall.category.entity.Category;

/**
 * 
* @date: 2019-3-24 
* @author: yangtz
* @title: CategoryDao 
* @version: 1.0 
* @description： 
* update_version: update_date: update_author: update_note:
 */
public class CategoryDao extends HibernateDaoSupport{

	/**
	 * 查询所有一级分类
	* @date: 2019-3-24 
	* @author: yangtz
	* @title: findAll 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public List<Category> findAll() {
		String hql = "from Category";
		List<Category> list = this.getHibernateTemplate().find(hql);
		if(null != list && list.size() > 0){
			return list;
		}
		return null;
	}

	/**
	 * 
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: save 
	* @param category 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}
	/**
	 * 查询
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: findByCid 
	* @param cid
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public Category findByCid(Integer cid) {
		return this.getHibernateTemplate().get(Category.class, cid);
	}

	/**
	 * 删除
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: delete 
	* @param category
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}

	/**
	 * 
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: update 
	* @param category 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}

}
