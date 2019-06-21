package com.whpu.onlineShoppingMall.product.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.whpu.onlineShoppingMall.category.service.CategoryService;
import com.whpu.onlineShoppingMall.product.entity.Product;
import com.whpu.onlineShoppingMall.product.service.ProductService;
import com.whpu.onlineShoppingMall.utils.PageBean;

/**
 * 上平action
* @date: 2019-3-24 
* @author: yangtz
* @title: ProductAction 
* @version: 1.0 
* @description： 
* update_version: update_date: update_author: update_note:
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product>{

	private ProductService productService;
	//注入一级分类的service
	private CategoryService categoryService;
	//接收分类的cid
	private Integer cid;
	//接收二级分类的cid
	private Integer csid;
	//接收当前页数
	private Integer page;
	//接收数据的模型驱动
	private Product product = new Product();
	
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	//提供get方法使得前台页面可以在值栈中找到cid
	public Integer getCid() {
		return cid;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public Product getModel() {
		return product;
	}
	//提供get方法使得前台页面可以在值栈中找到csid
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	/**
	 * 根据商品id查找商品详情
	* @date: 2019-3-24 
	* @author: yangtz
	* @title: findByPid 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String findByPid(){
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}
	/**
	 * 根据分类查询商品
	* @date: 2019-3-24 
	* @author: yangtz
	* @title: findByCid 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String findByCid(){
		//List<Category> clist = categoryService.findAll();//session里面有
		PageBean<Product> pageBean = productService.findByPageCid(cid,page);
		//将pageBean存入值栈中
		ActionContext.getContext().getValueStack().set("pageBean",pageBean);
		return "findByCid";
	}
	/**
	 * 根据二级分类查询商品
	* @date: 2019-3-25 
	* @author: yangtz
	* @title: findByCsid 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String findByCsid(){
		//根据二级分类查询商品
		PageBean<Product> pageBean = productService.findByPageCsid(csid,page);
		//将pageBean存入值栈中
		ActionContext.getContext().getValueStack().set("pageBean",pageBean);
		return "findByCsid";
	}
	
	/**
	 * 
	* @date: 2019-4-27 
	* @author: yangtz
	* @title: reportForIndexPage3 
	* @throws IOException 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public void reportForIndexPage3() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		List<Product> list = productService.getHotProductImage();
		String datatable = "";
		datatable +="<div class='scollimg'>";
		for(Product p : list){
			datatable +=" <img src='/OnlineShoppingMall/statics/"+p.getImage()+"' alt='' height='195px' width='220px'>";
		}
		datatable +="</div>";
		datatable +="<div class='btn'>";
		datatable +="<div id='btnleft'><div class='triangle'></div></div>";
		datatable +=" <div id='btnright'><div class='triangle'></div></div>";
		datatable +="</div>";
		datatable +="<div class='item'>";
		for(Product p2:list){
			datatable +=" <span></span>";
		}
		datatable +="</div>";
		response.getWriter().println(datatable);
	}
}
