package com.whpu.onlineShoppingMall.cart.entity;

import com.whpu.onlineShoppingMall.product.entity.Product;
/**
 * 购物项实体
* @date: 2019-3-25 
* @author: yangtz
* @title: CartItem 
* @version: 1.0 
* @description： 
* update_version: update_date: update_author: update_note:
 */
public class CartItem {

	private Product product;	// 购物项中商品信息
	private int count;			// 购买某种商品数量
	private double subtotal;	// 购买某种商品小计
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	// 小计自动计算的.
	public double getSubtotal() {
		return count * product.getShop_price();
	}
	/*public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	*/
}
