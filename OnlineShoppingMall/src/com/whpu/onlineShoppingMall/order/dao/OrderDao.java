package com.whpu.onlineShoppingMall.order.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.whpu.onlineShoppingMall.order.entity.Order;
import com.whpu.onlineShoppingMall.order.entity.OrderItem;
import com.whpu.onlineShoppingMall.utils.DateUtil;
import com.whpu.onlineShoppingMall.utils.PageHibernateCallback;

/**
 * 订单
* @date: 2019-3-27 
* @author: yangtz
* @title: OrderDao 
* @version: 1.0 
* @description： 
* update_version: update_date: update_author: update_note:
 */
public class OrderDao extends HibernateDaoSupport{

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
		this.getHibernateTemplate().save(order);
	}

	/**
	 * 获取订单数
	* @date: 2019-3-28 
	* @author: yangtz
	* @title: findCountByUid 
	* @return 
	* @exception: 
	* @version: 1.0 
	 * @param uid 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public int findCountByUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}
	/**
	 * 获取状态为searchState的订单数
	* @date: 2019-4-26 
	* @author: yangtz
	* @title: findCountByState 
	* @param uid
	* @param searchState
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public int findCountByState(Integer uid ,String searchState) {
		String hql = "select count(*) from Order o where o.user.uid = ? and o.state = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, uid ,Integer.parseInt(searchState));
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * 获取订单分页
	* @date: 2019-3-28 
	* @author: yangtz
	* @title: findByPageUid 
	* @param uid
	* @param begin
	* @param limit
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public List<Order> findByPageUid(Integer uid, int begin, Integer limit) {
		String hql = "from Order o where o.user.uid = ? order by o.ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql, new Object[] { uid },
						begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	/**
	 * 获取状态为searchState订单分页
	* @date: 2019-4-26 
	* @author: yangtz
	* @title: searchByState 
	* @param uid
	* @param searchState
	* @param begin
	* @param limit
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public List<Order> searchByState(Integer uid,String searchState, int begin, Integer limit) {
		String hql = "from Order o where o.user.uid = ? and o.state = ? order by o.ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql, new Object[] { uid ,Integer.parseInt(searchState)},
						begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
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
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	/**
	 * 修改订单
	* @date: 2019-3-29 
	* @author: yangtz
	* @title: update 
	* @param currOrder
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
	}
	
	/**
	 * DAO中统计订单个数的方法
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: findCount 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public int findCount() {
		String hql = "select count(*) from Order";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 *  DAO中分页查询订单的方法
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: findByPage 
	* @param begin
	* @param limit
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public List<Order> findByPage(int begin, int limit) {
		String hql = "from Order order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql, null, begin, limit));
		return list;
	}

	/**
	 * DAo中根据订单id查询订单项O
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
		String hql = "from OrderItem oi where oi.order.oid = ?";
		List<OrderItem> list = this.getHibernateTemplate().find(hql, oid);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
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
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public List<Order> findByPage(String keywords , String searchState) {
		if(searchState!=""){
			String hql = "from Order where (name like ? or addr like ? ) and state = ? order by oid desc";
			List<Order> list = this.getHibernateTemplate().find(hql,"%"+keywords+"%","%"+keywords+"%",Integer.parseInt(searchState));
			return list;
		}else{
			String hql = "from Order where name like ? or addr like ? order by oid desc";
			List<Order> list = this.getHibernateTemplate().find(hql,"%"+keywords+"%","%"+keywords+"%");
			return list;
		}
	}

	/**
	 * 
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
		String date1 = date+" 00:00:00";
		String date2 = date+" 24:60:60";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date3 = sdf.parse(date1);
		Date date4 = sdf.parse(date2);
		String hql = "select count(*) from Order where ordertime >= ? and ordertime <= ? and state in (2,3,4)";
		List list = this.getHibernateTemplate().find(hql,date3,date4);
		if(list!=null && list.size()>0){
			Long result = (Long)list.get(0);
			return  result.intValue();
		}
		return  0;
	}

	/**
	 * 
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
		String hql = "select count(*) from Order where state = 1";
		List list =this.getHibernateTemplate().find(hql);
		if(list!=null && list.size()>0){
			Long result = (Long)list.get(0);
			return  result.intValue();
		}
		return  0;
	}

	/**
	 * 
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
		String hql = "select count(*) from Order where state = 2";
		List list =this.getHibernateTemplate().find(hql);
		if(list!=null && list.size()>0){
			Long result = (Long)list.get(0);
			return  result.intValue();
		}
		return  0;
	}

}
