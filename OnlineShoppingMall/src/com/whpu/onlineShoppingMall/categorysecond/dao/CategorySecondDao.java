package com.whpu.onlineShoppingMall.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.whpu.onlineShoppingMall.adminuser.entity.AdminUser;
import com.whpu.onlineShoppingMall.categorysecond.entity.CategorySecond;
import com.whpu.onlineShoppingMall.utils.PageHibernateCallback;

/**
 * 二级分类的Dao层的代码
* @date: 2019-3-31 
* @author: yangtz
* @title: CategorySecondDao 
* @version: 1.0 
* @description： 
* update_version: update_date: update_author: update_note:
 */
public class CategorySecondDao extends HibernateDaoSupport {

	// DAO中的统计二级分类个数的方法
	public int findCount() {
		String hql = "select count(*) from CategorySecond";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * DAO中分页查询的方法
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
	public List<CategorySecond> findByPage(int begin, int limit) {
		String hql = "from CategorySecond order by csid desc";
		List<CategorySecond> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<CategorySecond>(hql, null, begin,
						limit));
		return list;
	}

	/**
	 * DAO中的保存二级分类的方法
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: save 
	* @param categorySecond 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
	}

	/**
	 * DAO中的删除二级分类的方法
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: delete 
	* @param categorySecond 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public void delete(CategorySecond categorySecond) {
		this.getHibernateTemplate().delete(categorySecond);
	}

	/**
	 *  DAO中根据id查询二级分类的方法
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: findByCsid 
	* @param csid
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public CategorySecond findByCsid(Integer csid) {
		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}

	/**
	 * DAO中的修改二级分类的方法
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: update 
	* @param categorySecond 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public void update(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
	}

	/**
	 * DAO中的查询所有二级分类的方法
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: findAll 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public List<CategorySecond> findAll() {
		String hql = "from CategorySecond";
		return this.getHibernateTemplate().find(hql);
	}

	/**
	 * 通过关键字查询
	* @date: 2019-4-18 
	* @author: yangtz
	* @title: findByPage 
	* @param begin
	* @param limit
	* @param keywords
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public List<CategorySecond> findByPage(String keywords) {
		String hql = "from CategorySecond where csname like ? order by csid desc";
		List<CategorySecond> list = this.getHibernateTemplate().find(hql,"%"+keywords+"%");
		return list;
	}

}
