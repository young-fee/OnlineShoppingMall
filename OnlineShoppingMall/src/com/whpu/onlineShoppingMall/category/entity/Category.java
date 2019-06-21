package com.whpu.onlineShoppingMall.category.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.whpu.onlineShoppingMall.categorysecond.entity.CategorySecond;

/**
 * 一级分类实体类
* @date: 2019-3-24 
* @author: yangtz
* @title: Category 
* @version: 1.0 
* @description： 
* update_version: update_date: update_author: update_note:
 */
public class Category implements Serializable {

	private Integer cid;
	private String cname;
	//一级分类中应该存放二级分类的集合
	private Set<CategorySecond> categorySeconds = new HashSet<CategorySecond>();
	
	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
}
