package com.whpu.onlineShoppingMall.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.whpu.onlineShoppingMall.categorysecond.entity.CategorySecond;
import com.whpu.onlineShoppingMall.product.dao.ProductDao;
import com.whpu.onlineShoppingMall.product.entity.Product;
import com.whpu.onlineShoppingMall.utils.PageBean;

/**
 * 商品业务层 
* @date: 2019-3-24 
* @author: yangtz
* @title: ProductService 
* @version: 1.0 
* @description： 
* update_version: update_date: update_author: update_note:
 */
@Transactional
public class ProductService {
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	/**
	 * 首页热门商品的查询
	* @date: 2019-3-24 
	* @author: yangtz
	* @title: findAll 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public List<Product> findHot() {
		return productDao.findHot();
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
		return productDao.findNew();
	}

	/**
	 *  根据商品id查找商品详情
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
		return productDao.findByPid(pid);
	}

	/**
	 * 根据以及分类查询商品带分页,封装到bean中
	* @date: 2019-3-24 
	* @author: yangtz
	* @title: findByPageCid 
	* @param cid
	* @param page
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public PageBean<Product> findByPageCid(Integer cid, Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		int limit = 8;
		int totalCount = 0;
		int totalPage = 0;
		totalCount = productDao.findCountCid(cid);
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		pageBean.setTotalCount(totalCount);
		if(totalCount % limit == 0){
			totalPage = totalCount/limit;
		}else{
			totalPage = totalCount/limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		int begin = (page -1) * limit ;
		List<Product> list = productDao.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 根据二级分类查询商品
	* @date: 2019-3-25 
	* @author: yangtz
	* @title: findByPageCsid 
	* @param csid
	* @param page
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public PageBean<Product> findByPageCsid(Integer csid, Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		int limit = 8;
		int totalCount = 0;
		int totalPage = 0;
		totalCount = productDao.findCountCsid(csid);
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		pageBean.setTotalCount(totalCount);
		if(totalCount % limit == 0){
			totalPage = totalCount/limit;
		}else{
			totalPage = totalCount/limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		int begin = (page -1) * limit ;
		List<Product> list = productDao.findByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	
	/**
	 * 后台查询所有商品带分页
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
	public PageBean<Product> findByPage(Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = productDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 业务层保存商品方法:
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
		productDao.save(product);
	}

	/**
	 * 业务层删除商品
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
		productDao.delete(product);
	}

	/**
	 * 业务层修改商品的方法
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
		productDao.update(product);
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
	public PageBean<Product> findByPage(String keywords) {
		PageBean<Product> pageBean = new PageBean<Product>();

		List<Product> list = productDao.findByPage(keywords);
		pageBean.setList(list);
		return pageBean;
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
		return productDao.getHotProductImage();
	}
}
