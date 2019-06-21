package com.whpu.onlineShoppingMall.product.dao;

import java.util.List;
import java.util.regex.Pattern;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.whpu.onlineShoppingMall.categorysecond.entity.CategorySecond;
import com.whpu.onlineShoppingMall.product.entity.Product;
import com.whpu.onlineShoppingMall.utils.PageHibernateCallback;

/**
 * 商品持久层
* @date: 2019-3-24 
* @author: yangtz
* @title: ProductDao 
* @version: 1.0 
* @description： 
* update_version: update_date: update_author: update_note:
 */
public class ProductDao extends HibernateDaoSupport{

	/**
	 * 首页热门商品的查询
	* @date: 2019-3-24 
	* @author: yangtz
	* @title: findHot 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public List<Product> findHot() {
		// 使用离线条件查询.
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// 查询热门的商品,条件就是is_host = 1
		criteria.add(Restrictions.eq("is_hot", 1));
		// 倒序排序输出:
		criteria.addOrder(Order.desc("pdate"));
		// 执行查询:
		List<Product> list = this.getHibernateTemplate().findByCriteria(
				criteria, 0, 10);
		return list;
	}

	/**
	 * 最新商品
	* @date: 2019-3-24 
	* @author: yangtz
	* @title: findNew 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public List<Product> findNew() {
		// 使用离线条件查询:
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// 按日期进行倒序排序:
		criteria.addOrder(Order.desc("pdate"));
		// 执行查询:
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}

	/**
	 * 根据商品id查找商品详情
	* @date: 2019-3-24 
	* @author: yangtz
	* @title: findByPid 
	* @param pid
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public Product findByPid(Integer pid) {
		return 	this.getHibernateTemplate().get(Product.class, pid);
	}

	/**
	 * 获取商品个数
	* @date: 2019-3-24 
	* @author: yangtz
	* @title: findCountCid 
	* @param cid
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public int findCountCid(Integer cid) {
		//需要配置二级分类与商品的关系
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,cid);
		if(null != list && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * 商品集合
	* @date: 2019-3-24 
	* @author: yangtz
	* @title: findByPageCid 
	* @param cid
	* @param begin
	* @param limit
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		// select p.* from category c,categorysecond cs,product p where c.cid = cs.cid and cs.csid = p.csid and c.cid = 2
		// select p from Category c,CategorySecond cs,Product p where c.cid = cs.category.cid and cs.csid = p.categorySecond.csid and c.cid = ?
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		// 分页另一种写法:
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	/**
	 * 根据二级分类获取商品个数
	* @date: 2019-3-25 
	* @author: yangtz
	* @title: findCountCsid 
	* @param csid
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public int findCountCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,csid);
		if(null != list && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * 
	* @date: 2019-3-25 
	* @author: yangtz
	* @title: findByPageCsid 
	* @param csid
	* @param begin
	* @param limit
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		// 分页另一种写法:
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	/**
	 * 后台统计商品个数的方法
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: findCount 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public int findCount() {
		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * 后台查询所有商品的方法
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
	public List<Product> findByPage(int begin, int limit) {
		String hql = "from Product order by pdate desc";
		List<Product> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, null, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	/**
	 * DAO中的保存商品的方法
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: save 
	* @param product 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}

	/**
	 * DAO中的删除商品的方法
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: delete 
	* @param product 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
	}

	/**
	 * 修改
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: update 
	* @param product 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}
	
	/**
	 * 
	* @date: 2019-4-22 
	* @author: yangtz
	* @title: findByPage 
	* @param keywords
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public List<Product> findByPage(String keywords) {
		String hql = "from Product where pname like ? order by pid desc";
		List<Product> list = this.getHibernateTemplate().find(hql, "%"+keywords+"%");
		return list;
	}

	/**
	 * 
	* @date: 2019-4-27 
	* @author: yangtz
	* @title: getHotProductImage 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public List<Product> getHotProductImage() {
		// 使用离线条件查询.
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// 查询热门的商品,条件就是is_host = 1
		criteria.add(Restrictions.eq("is_hot", 1));
		// 倒序排序输出:
		criteria.addOrder(Order.desc("pdate"));
		// 执行查询:
		List<Product> list = this.getHibernateTemplate().findByCriteria(
				criteria, 0, 6);
		return list;
	}
}
