package com.whpu.onlineShoppingMall.cart.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.whpu.onlineShoppingMall.product.entity.Product;
import com.whpu.onlineShoppingMall.product.service.ProductService;

/**
 * 购物车实体以及购物车的功能
* @date: 2019-3-25 
* @author: yangtz
* @title: Cart 
* @version: 1.0 
* @description： 
* update_version: update_date: update_author: update_note:
 */
public class Cart implements Serializable {

	// 购物车属性
	// 购物项集合:Map的key就是商品pid,value:购物项
	private Map<Integer, CartItem> map = new LinkedHashMap<Integer,CartItem>();
	ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	// Cart对象中有一个叫cartItems属性.
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	// 购物总计:
	private double total;

	public double getTotal() {
		return total;
	}

	/**
	 * 将购物项添加到购物车
	* @date: 2019-3-25 
	* @author: yangtz
	* @title: addCart 
	* @param cartItem 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public void addCart(CartItem cartItem) {
		// 判断购物车中是否已经存在该购物项:
		/*
		 *  * 如果存在:
		 *  	* 数量增加
		 *  	* 总计 = 总计 + 购物项小计
		 *  * 如果不存在:
		 *  	* 向map中添加购物项
		 *  	* 总计 = 总计 + 购物项小计
		 */
		// 获得商品id.
		Integer pid = cartItem.getProduct().getPid();
//		Integer num = cartItem.getProduct().getNum()-cartItem.getCount();
//		//商品数量减少
//		Product product = productService.findByPid(pid);
//		product.setNum(num);
//		productService.update(product);
		// 判断购物车中是否已经存在该购物项:
		if(map.containsKey(pid)){
			// 存在
			CartItem _cartItem = map.get(pid);// 获得购物车中原来的购物项
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}else{
			// 不存在
			map.put(pid, cartItem);
		}
		// 设置总计的值
		total += cartItem.getSubtotal();
	}

	/**
	 * 从购物车移除购物项
	* @date: 2019-3-25 
	* @author: yangtz
	* @title: removeCart 
	* @param pid 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public void removeCart(Integer pid) {
		// 将购物项移除购物车:
		CartItem cartItem = map.remove(pid);
//		//数量变化
//		Integer num = cartItem.getProduct().getNum()+cartItem.getCount();
//		Product product = productService.findByPid(pid);
//		product.setNum(num);
//		productService.update(product);
		// 总计 = 总计 -移除的购物项小计:
		total -= cartItem.getSubtotal();
	}

	/**
	 * 清空购物车
	* @date: 2019-3-25 
	* @author: yangtz
	* @title: clearCart  
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public void clearCart() {
		//遍历map中的键 
//		for (Map.Entry<Integer, CartItem> entry : map.entrySet()) { 
//		  Integer pid = entry.getKey();
//		  CartItem cartItem = entry.getValue();
//		  Product product = productService.findByPid(pid);
//		  Integer num = product.getNum()+cartItem.getCount();
//		  productService.update(product);
//		} 
		// 将所有购物项清空
		map.clear();
		// 将总计设置为0
		total = 0;
	}

}
