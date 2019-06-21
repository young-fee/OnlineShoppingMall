package com.whpu.onlineShoppingMall.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.whpu.onlineShoppingMall.category.dao.CategoryDao;
import com.whpu.onlineShoppingMall.category.entity.Category;

/**
 * 
* @date: 2019-3-24 
* @author: yangtz
* @title: CategoryService 
* @version: 1.0 
* @description： 
* update_version: update_date: update_author: update_note:
 */
@Transactional
public class CategoryService {

	//注入
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	/**
	 * 查询一级分类
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
		
		return categoryDao.findAll();
	}

	/**
	 * 添加一级分类
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
		categoryDao.save(category);
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
		return categoryDao.findByCid(cid);
	}

	/**
	 * 删除
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: delete 
	* @param category 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public void delete(Category category) {
		categoryDao.delete(category);
	}

	/**
	 * 修改
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
		categoryDao.update(category);
	}
}
