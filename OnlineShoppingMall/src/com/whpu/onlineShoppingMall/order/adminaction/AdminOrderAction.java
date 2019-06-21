package com.whpu.onlineShoppingMall.order.adminaction;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.whpu.onlineShoppingMall.categorysecond.entity.CategorySecond;
import com.whpu.onlineShoppingMall.order.entity.Order;
import com.whpu.onlineShoppingMall.order.entity.OrderItem;
import com.whpu.onlineShoppingMall.order.service.OrderService;
import com.whpu.onlineShoppingMall.utils.PageBean;

/**
 * 后台订单管理的Action
 * @author 传智.郭嘉
 *
 */
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{
	// 模型驱动使用的类
	private Order order = new Order();

	public Order getModel() {
		return order;
	}
	// 接收page参数
	private Integer page;
	
	public void setPage(Integer page) {
		this.page = page;
	}
	
	// 注入OrderService
	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}


	/**
	 * 提供后台查询所有订单的方法:
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: findAll 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String findAll(){
		// 订单的分页查询
		PageBean<Order> pageBean = orderService.findAll(page);
		// 将数据存入到值栈中保存到页面
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		// 页面跳转:
		return "findAll";
	}

	/**
	 * 修改订单状态
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: updateState 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String updateState(){
		// 根据id查询订单
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(3);
		orderService.update(currOrder);
		// 页面跳转
		return "updateStateSuccess";
	}
	
	/**
	 * 根据订单的id查询订单项:
	* @date: 2019-3-31 
	* @author: yangtz
	* @title: findOrderItem 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String findOrderItem(){
		// 根据订单id查询订单项:
		List<OrderItem> list = orderService.findOrderItem(order.getOid());
		// 显示到页面:
		ActionContext.getContext().getValueStack().set("list", list);
		// 页面跳转
		return "findOrderItem";
	}
	/**
	 * 关键字搜索
	* @date: 2019-4-23 
	* @author: yangtz
	* @title: searchByKeywords 
	* @throws IOException 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public void searchByKeywords() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		String keywords = ServletActionContext.getRequest().getParameter("keywords");
		String searchState = ServletActionContext.getRequest().getParameter("searchState");
		// 调用Service进行查询.
		PageBean<Order> pageBean = orderService
				.findByPage(keywords,searchState);
		String contentHtml = "";
		if(pageBean.getList()!=null&&pageBean.getList().size()>0){
			contentHtml +="	<tr                                                                                      ";
			contentHtml +="		style='font-weight: bold; font-size: 12pt; height: 25px; background-color: #afd1f3'> ";
	        contentHtml +="                                                                                          ";
			contentHtml +="		<td align='center' width='3%'>                                                       ";
			contentHtml +="			序号                                                                             ";
			contentHtml +="		</td>                                                                                ";
			contentHtml +="		<td align='center' width='10%'>                                                      ";
			contentHtml +="			订单编号                                                                         ";
			contentHtml +="		</td>                                                                                ";
			contentHtml +="		<td align='center' width='10%'>                                                      ";
			contentHtml +="			订单金额                                                                         ";
			contentHtml +="		</td>                                                                                ";
			contentHtml +="		<td align='center' width='10%'>                                                      ";
			contentHtml +="			收货人                                                                           ";
			contentHtml +="		</td>                                                                                ";
			contentHtml +="		<td align='center' width='10%'>                                                      ";
			contentHtml +="			电话                                                                      ";
			contentHtml +="		</td>                                                                                ";
			contentHtml +="		<td align='center' width='17%'>                                                      ";
			contentHtml +="			收货地址                                                                         ";
			contentHtml +="		</td>                                                                                ";
			contentHtml +="		<td align='center' width='10%'>                                                      ";
			contentHtml +="			订单状态                                                                         ";
			contentHtml +="		</td>                                                                                ";
			contentHtml +="		<td align='center' width='40%'>                                                      ";
			contentHtml +="			订单详情                                                                         ";
			contentHtml +="		</td>                                                                                ";
			contentHtml +="	</tr>                                                                                    ";
			for(int i=0;i<pageBean.getList().size();i++){
				contentHtml +="	<tr onmouseover='this.style.backgroundColor = 'white''";
				contentHtml +="		onmouseout='this.style.backgroundColor = '#F5FAFE';'>";
				contentHtml +="		<td style='cursor: hand; HEIGHT: 35px' align='center'";
				contentHtml +="			width='3%'>";
				contentHtml +=			i+1;
				contentHtml +="		</td>";
				contentHtml +="		<td style='cursor: hand; HEIGHT: 35px' align='center'";
				contentHtml +="			width='10%'>";
				contentHtml +=			pageBean.getList().get(i).getOid();
				contentHtml +="		</td>                                                                                                                                                                                        ";
				contentHtml +="		<td style='cursor: hand; HEIGHT: 35px' align='center'                                                                                                                                        ";
				contentHtml +="			width='10%'>                                                                                                                                                                             ";
				contentHtml +=			pageBean.getList().get(i).getTotal();                                                                                                                                                    
				contentHtml +="		</td>                                                                                                                                                                                        ";
				contentHtml +="		<td style='cursor: hand; HEIGHT: 35px' align='center'                                                                                                                                        ";
				contentHtml +="			width='10%'>                                                                                                                                                                             ";
				contentHtml +=			pageBean.getList().get(i).getName();                                                                                                                                                     
				contentHtml +="		</td>                                                                                                                                                                                        ";
				contentHtml +="		<td style='cursor: hand; HEIGHT: 35px' align='center'                                                                                                                                        ";
				contentHtml +="			width='10%'>                                                                                                                                                                             ";
				contentHtml +=			pageBean.getList().get(i).getPhone();                                                                                                                                                     
				contentHtml +="		</td>                                                                                                                                                                                        ";
				contentHtml +="		<td style='cursor: hand; HEIGHT: 35px' align='center'                                                                                                                                        ";
				contentHtml +="			width='17%'>                                                                                                                                                                             ";
				contentHtml +=			pageBean.getList().get(i).getAddr();                                                                                                                                                     
				contentHtml +="		</td>                                                                                                                                                                                        ";
				contentHtml +="		<td style='cursor: hand; HEIGHT: 35px' align='center'                                                                                                                                        ";
				contentHtml +="			width='10%'>                                                                                                                                                                             ";
				if(pageBean.getList().get(i).getState()==1){
					contentHtml +="				<font color='red' size='4'>未付款</font>";
				}else if(pageBean.getList().get(i).getState()==2)
					contentHtml +="				<a href='/OnlineShoppingMall/adminOrder_updateState.action?oid="+pageBean.getList().get(i).getOid()+"' onClick='return confirm('确定发货?');'><font color='red' size='4'>发货</font></a>";
				else if(pageBean.getList().get(i).getState()==3){
					contentHtml +="				等待确认收货                                                                                                                                                                         ";
				}else if(pageBean.getList().get(i).getState()==4){
					contentHtml +="				<font color='mediumpurple' size='4'>订单完成</font>                                                                                                                                  ";
				}
				contentHtml +="		</td>                                                                                                                                                                                        ";
				contentHtml +="		<td align='center' style='HEIGHT: 35px'>                                                                                                                                                     ";
				contentHtml +="			<input type='button' value='订单详情' id='but"+pageBean.getList().get(i).getOid()+"' onclick='showDetail("+pageBean.getList().get(i).getOid()+")'/>                                            ";
				contentHtml +="			<div id='div"+pageBean.getList().get(i).getOid()+"'>                                                                                                                                        ";
				contentHtml +="				                                                                                                                                                                                     ";
				contentHtml +="			</div>                                                                                                                                                                                   ";
				contentHtml +="		</td>                                                                                                                                                                                        ";
		        contentHtml +="                                                                                                                                                                                                  ";
				contentHtml +="	</tr>                                                                                                                                                                                            ";
			}
		}else{
			contentHtml = "<tr><td style='border: hidden; height:40px;'>无数据</td></tr>";
		}
		response.getWriter().println(contentHtml);
	}

}
