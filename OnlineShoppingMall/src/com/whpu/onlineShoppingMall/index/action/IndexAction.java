package com.whpu.onlineShoppingMall.index.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whpu.onlineShoppingMall.category.entity.Category;
import com.whpu.onlineShoppingMall.category.service.CategoryService;
import com.whpu.onlineShoppingMall.product.entity.Product;
import com.whpu.onlineShoppingMall.product.service.ProductService;

/**
 *首页访问的action
* @date: 2019-3-9 
* @author: yangtz
* @title: IndexAction 
* @version: 1.0 
* @description： 
* update_version: update_date: update_author: update_note:
 */
public class IndexAction extends ActionSupport{
	//注入一级分类service
	private CategoryService categoryService;
	//注入商品service
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * 
	* @date: 2019-3-24 
	* @author: yangtz
	* @title: execute 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String execute(){
		//查询所有一级分类
		List<Category> clist = categoryService.findAll();
		//存入session
		ActionContext.getContext().getSession().put("clist", clist);
		
		//查询热门商品
		List<Product> hotlist = productService.findHot();
		//保存到值栈中
		ActionContext.getContext().getValueStack().set("hotlist", hotlist);
		
		//查询最新商品
		List<Product> newlist = productService.findNew();
		//保存到值栈中
		ActionContext.getContext().getValueStack().set("newlist", newlist);
		
		return "index";
	}
}
