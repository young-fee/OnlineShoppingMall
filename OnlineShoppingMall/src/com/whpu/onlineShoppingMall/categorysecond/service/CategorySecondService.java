package com.whpu.onlineShoppingMall.categorysecond.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.whpu.onlineShoppingMall.categorysecond.dao.CategorySecondDao;
import com.whpu.onlineShoppingMall.categorysecond.entity.CategorySecond;
import com.whpu.onlineShoppingMall.utils.PageBean;

/**
 * 
* @date: 2019-3-31 
* @author: yangtz
* @title: CategorySecondService 
* @version: 1.0 
* @description： 
* update_version: update_date: update_author: update_note:
 */
@Transactional
public class CategorySecondService {
	// 注入Dao
	private CategorySecondDao categorySecondDao;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	/**
	 * 二级分类带有分页的查询操作:
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
	public PageBean<CategorySecond> findByPage(Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();

		// 设置参数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置页面显示数据的集合:
		int begin = (page - 1) * limit;
		List<CategorySecond> list = categorySecondDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 业务层的保存二级分类的操作
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
		categorySecondDao.save(categorySecond);
	}

	/**
	 * 业务层的删除二级分类的操作
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
		categorySecondDao.delete(categorySecond);
	}

	/**
	 * 业务层根据id查询二级分类
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
		return categorySecondDao.findByCsid(csid);
	}

	/**
	 * 业务层修改二级分类的方法
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
		categorySecondDao.update(categorySecond);
	}

	/**
	 * 业务层查询所有二级分类(不带分页)
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
		return categorySecondDao.findAll();
	}

	/**
	 * 通过关键字查询
	* @date: 2019-4-18 
	* @author: yangtz
	* @title: findByPage 
	* @param page
	* @param keywords
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public PageBean<CategorySecond> findByPage(String keywords) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();

		List<CategorySecond> list = categorySecondDao.findByPage(keywords);
		pageBean.setList(list);
		return pageBean;
	}

}
