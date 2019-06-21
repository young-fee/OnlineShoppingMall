package com.whpu.onlineShoppingMall.cart.action;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.whpu.onlineShoppingMall.cart.entity.Cart;
import com.whpu.onlineShoppingMall.cart.entity.CartItem;
import com.whpu.onlineShoppingMall.product.entity.Product;
import com.whpu.onlineShoppingMall.product.service.ProductService;

/**
 * 购物车
* @date: 2019-3-26 
* @author: yangtz
* @title: CartAction 
* @version: 1.0 
* @description： 
* update_version: update_date: update_author: update_note:
 */
public class CartAction extends ActionSupport{

	private Integer pid;
	private Integer count;
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 添加到购物车
	* @date: 2019-3-26 
	* @author: yangtz
	* @title: addCart 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String addCart(){
		//接收参数pid  count
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		//查询商品
		Product product = productService.findByPid(pid);
		cartItem.setProduct(product);
		//将购物项添加到购物车  购物车存到session
		Cart cart = getCart();
		cart.addCart(cartItem);
		//商品数量变化
		Integer num = product.getNum()-count;
		product.setNum(num);
		productService.update(product);
		return "addCart";
	}
	
	/**
	 * 从session获得购物车
	* @date: 2019-3-26 
	* @author: yangtz
	* @title: getCart 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	private Cart getCart() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null){
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	/**
	 * 清空购物车
	* @date: 2019-3-26 
	* @author: yangtz
	* @title: clearCart 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String clearCart(){
		// 获得购物车对象.
		Cart cart = getCart();
		
		Collection<CartItem> collection = cart.getCartItems();
		Iterator<CartItem> c = collection.iterator();
		while(c.hasNext()){
			CartItem cartItem = c.next();
			Product product = cartItem.getProduct();
			System.out.println("==================商品数量"+product.getNum()+"购买数量"+cartItem.getCount());
			product.setNum(product.getNum()+cartItem.getCount());
			productService.update(product);
		}
//		for(int i=0;i<list.size();i++){
//			CartItem cartItem = list.;
//			Product product = cartItem.getProduct();
//			product.setNum(product.getNum()+cartItem.getCount());
//			productService.update(product);
//		}
		// 调用购物车中清空方法.
		cart.clearCart();
		return "clearCart";
	}
	/**
	 * 购物车中移除购物项
	* @date: 2019-3-26 
	* @author: yangtz
	* @title: removeCart  
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String removeCart(){
		// 获得购物车对象
		Cart cart = getCart();
	
		Collection<CartItem> collection =  cart.getCartItems();
		Iterator<CartItem> c = collection.iterator();
		while(c.hasNext()){
			CartItem cartItem = c.next();
			Product product = cartItem.getProduct();
			product.setNum(product.getNum()+cartItem.getCount());
			productService.update(product);
		}
		
		// 调用购物车中移除的方法:
		cart.removeCart(pid);
		// 返回页面:
		return "removeCart";
	}
	/**
	 * 跳转购物车
	* @date: 2019-3-26 
	* @author: yangtz
	* @title: myCart 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String myCart(){
		return "myCart";
	}
}
