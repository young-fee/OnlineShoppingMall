package com.whpu.onlineShoppingMall.order.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.whpu.onlineShoppingMall.categorysecond.entity.CategorySecond;
import com.whpu.onlineShoppingMall.order.dao.OrderDao;
import com.whpu.onlineShoppingMall.order.entity.Order;
import com.whpu.onlineShoppingMall.order.entity.OrderItem;
import com.whpu.onlineShoppingMall.utils.PageBean;

/**
 * 订单
* @date: 2019-3-27 
* @author: yangtz
* @title: OrderService 
* @version: 1.0 
* @description： 
* update_version: update_date: update_author: update_note:
 */
@Transactional
public class OrderService {

	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	/**
	 * 保存订单
	* @date: 2019-3-27 
	* @author: yangtz
	* @title: save  
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public void save(Order order) {
		orderDao.save(order);
	}

	/**
	 * 获取用户订单
	* @date: 2019-3-28 
	* @author: yangtz
	* @title: findByPageUid 
	* @param uid
	* @param page
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public PageBean<Order> findByPageUid(Integer uid, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setPage(page);
		Integer limit = 5;
		pageBean.setLimit(limit);
		int totalCount = 0;
		int totalPage = 0;
		totalCount = orderDao.findCountByUid(uid);
		pageBean.setTotalCount(totalCount);
		if(totalCount % limit == 0){
			totalPage = totalCount/limit;
		}else{
			totalPage = totalCount/limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		int begin = (page -1) * limit ;
		List<Order> list = orderDao.findByPageUid(uid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	/**
	 * 获取状态为searchState用户订单
	* @date: 2019-4-26 
	* @author: yangtz
	* @title: searchByState 
	* @param uid
	* @param searchState
	* @param page
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public PageBean<Order> searchByState(Integer uid,String searchState,Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setPage(page);
		Integer limit = 5;
		pageBean.setLimit(limit);
		int totalCount = 0;
		int totalPage = 0;
		totalCount = orderDao.findCountByState(uid,searchState);
		pageBean.setTotalCount(totalCount);
		if(totalCount % limit == 0){
			totalPage = totalCount/limit;
		}else{
			totalPage = totalCount/limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		int begin = (page -1) * limit ;
		List<Order> list = orderDao.searchByState(uid,searchState,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 
	* @date: 2019-3-28 
	* @author: yangtz
	* @title: findByOid 
	* @param oid
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}

	/**
	 * 订单修改
	* @date: 2019-3-29 
	* @author: yangtz
	* @title: update 
	* @param currOrder 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public void update(Order currOrder) {
		orderDao.update(currOrder);
	}
	
	/**
	 * 业务层查询所有订单方法
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: findAll 
	* @param page
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public PageBean<Order> findAll(Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		// 设置参数
		pageBean.setPage(page);
		// 设置每页显示的记录数:
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总记录数
		int totalCount = orderDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示数据集合
		int begin = (page - 1) * limit;
		List<Order> list = orderDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 业务层查询订单项的方法
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: findOrderItem 
	* @param oid
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public List<OrderItem> findOrderItem(Integer oid) {
		return orderDao.findOrderItem(oid);
	}
	/**
	 * 
	* @date: 2019-4-23 
	* @author: yangtz
	* @title: findByPage 
	* @param keywords
	* @return 
	* @exception: 
	* @version: 1.0 
	 * @param searchState 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public PageBean<Order> findByPage(String keywords, String searchState) {
		PageBean<Order> pageBean = new PageBean<Order>();

		List<Order> list = orderDao.findByPage(keywords,searchState);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 首页报表计数
	* @date: 2019-4-24 
	* @author: yangtz
	* @title: countOrderByDate 
	* @param date
	* @return 
	* @exception: 
	* @version: 1.0 
	 * @throws ParseException 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public int countOrderByDate(String date) throws ParseException {
		return orderDao.countOrderByDate(date);
	}

	/**
	 * 计数未付款
	* @date: 2019-4-26 
	* @author: yangtz
	* @title: countNoPay 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public int countNoPay() {
		return orderDao.countNoPay();
	}

	/**
	 * 计数未发货
	* @date: 2019-4-26 
	* @author: yangtz
	* @title: countNoPost 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public int countNoPost() {
		return orderDao.countNoPost();
	}

}
