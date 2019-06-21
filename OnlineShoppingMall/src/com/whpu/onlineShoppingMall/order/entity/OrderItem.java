package com.whpu.onlineShoppingMall.order.entity;

import com.whpu.onlineShoppingMall.product.entity.Product;

/**
 * 订单项
* @date: 2019-3-27 
* @author: yangtz
* @title: OrderItem 
* @version: 1.0 
* @description： 
* update_version: update_date: update_author: update_note:
 */
public class OrderItem {

	private Integer itemid;
	private Integer count;
	private Double subtotal;
	// 商品外键:对象
	private Product product;
	// 订单外键:对象
	private Order order;
	public Integer getItemid() {
		return itemid;
	}
	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
}
