package com.whpu.onlineShoppingMall.product.entity;

import java.util.Date;

import com.whpu.onlineShoppingMall.categorysecond.entity.CategorySecond;

/**
 * 
* @date: 2019-3-24 
* @author: yangtz
* @title: Product 
* @version: 1.0 
* @description： 
* update_version: update_date: update_author: update_note:
 */
public class Product {
	private Integer pid;
	private String pname;
	private Double market_price;
	private Double shop_price;
	private String image;
	private Integer num;
	private String pdesc;
	private Integer is_hot;
	private Date pdate;
	// 二级分类的外键:使用二级分类的对象.
	private CategorySecond categorySecond;
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Double getMarket_price() {
		return market_price;
	}
	public void setMarket_price(Double market_price) {
		this.market_price = market_price;
	}
	public Double getShop_price() {
		return shop_price;
	}
	public void setShop_price(Double shop_price) {
		this.shop_price = shop_price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	public Integer getIs_hot() {
		return is_hot;
	}
	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	public CategorySecond getCategorySecond() {
		return categorySecond;
	}
	public void setCategorySecond(CategorySecond categorySecond) {
		this.categorySecond = categorySecond;
	}
	
}
