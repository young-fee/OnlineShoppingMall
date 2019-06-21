package com.whpu.onlineShoppingMall.order.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.whpu.onlineShoppingMall.cart.entity.Cart;
import com.whpu.onlineShoppingMall.cart.entity.CartItem;
import com.whpu.onlineShoppingMall.order.entity.Order;
import com.whpu.onlineShoppingMall.order.entity.OrderItem;
import com.whpu.onlineShoppingMall.order.service.OrderService;
import com.whpu.onlineShoppingMall.user.entity.User;
import com.whpu.onlineShoppingMall.utils.PageBean;
import com.whpu.onlineShoppingMall.utils.PaymentUtil;

/**
 * 订单管理的action
* @date: 2019-3-27 
* @author: yangtz
* @title: OrderAction 
* @version: 1.0 
* @description： 
* update_version: update_date: update_author: update_note:
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order> {

	private Order order = new Order();
	private OrderService orderService;
	private Integer page;
	//银行支付通道编码
	private String pd_FrpId;
	private String r6_Order;
	private String r3_Amt;
	
	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}
	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	public Order getModel() {
		return order;
	}

	/**
	 * 生成订单
	* @date: 2019-3-27 
	* @author: yangtz
	* @title: saveOrder 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String saveOrder(){
		order.setOrdertime(new Date());	
		order.setState(1);//1 为付款     2已付款，未发货      3已发货，未确定人收货      4交易完成
		
		//获得购物车的total
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null || cart.getTotal() == 0){
			this.addActionError("亲!您还没有购物!");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		
		//获得购物项
		for (CartItem cartItem : cart.getCartItems()) {
			// 订单项的信息从购物项获得的.
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);

			order.getOrderItems().add(orderItem);
		}
		
		//用户
		User loginUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("loginUser");
		if (loginUser == null) {
			this.addActionMessage("亲!您还没有登录!");
			return "msg";
		}
		order.setUser(loginUser);
		//保存
		//Integer newid = Integer.parseInt(new SimpleDateFormat("mmss").format(new Date())+new RandomUtils().generateNumber2());
		//order.setOid(newid);
		orderService.save(order);
		cart.clearCart();
		return "saveOrder";
	}
	
	/**
	 * 获取用户的订单
	* @date: 2019-3-28 
	* @author: yangtz
	* @title: findByUid 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String findByUid(){
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		PageBean<Order> pageBean = orderService.findByPageUid(user.getUid(),page);
		//数据存入值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUid";
	}
	/**
	 * 订单页面通过状态筛选
	* @date: 2019-4-26 
	* @author: yangtz
	* @title: searchByState 
	* @return 
	* @exception: 
	* @version: 1.0 
	 * @throws IOException 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public void searchByState() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		String searchState = ServletActionContext.getRequest().getParameter("searchState");
		
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		PageBean<Order> pageBean = orderService.searchByState(user.getUid(),searchState,page);
//		//数据存入值栈
//		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		String contentHtml = "";
		if(pageBean.getList()!=null&&pageBean.getList().size()>0){
			for(int i=0;i<pageBean.getList().size();i++){
				contentHtml +="		<tr>                                                                                                                                                                                                                    ";
				contentHtml +="			<th colspan='5' style='background: beige;'>订单编号:"+pageBean.getList().get(i).getOid()+"&nbsp;&nbsp;&nbsp;&nbsp;订单金额:                                                                                              ";
				contentHtml +="			<font color='red'>"+pageBean.getList().get(i).getTotal()+"</font>                                                                                                                                                        ";
				contentHtml +="			&nbsp;&nbsp;&nbsp;&nbsp;                                                                                                                                                                                            ";
				contentHtml +="			                                                                                                                                                                                                                    ";
				if(pageBean.getList().get(i).getState()==1){
					contentHtml +="					<a href='/OnlineShoppingMall/order_findByOid.action?oid="+pageBean.getList().get(i).getOid()+"'>订单状态：<font color='red'><font color='mediumpurple' size='4'>付款</font></font></a>          ";
				}else if(pageBean.getList().get(i).getState()==2){
					contentHtml +="					<font color='mediumpurple' size='4'>已付款</font>                                                                                                                                                           ";
				}else if(pageBean.getList().get(i).getState()==3){
					contentHtml +="					<a href='/OnlineShoppingMall/order_updateState.action?oid="+pageBean.getList().get(i).getOid()+"' onClick='return confirm('确定收货?');'><font color='mediumpurple' size='4'>确认收货</font></a>";
				}else if(pageBean.getList().get(i).getState()==4){
					contentHtml +="					<font color='mediumpurple' size='4'>交易完成</font>                                                                                                                                                         ";
				}
				contentHtml +="			</th>                                                                                                                                                                                                               ";
				contentHtml +="		</tr>                                                                                                                                                                                                                   ";
				contentHtml +="		<tr>                                                                                                                                                                                                                    ";
				contentHtml +="			<th>图片</th>                                                                                                                                                                                                       ";
				contentHtml +="			<th>商品</th>                                                                                                                                                                                                       ";
				contentHtml +="			<th>价格</th>                                                                                                                                                                                                       ";
				contentHtml +="			<th>数量</th>                                                                                                                                                                                                       ";
				contentHtml +="			<th>小计</th>                                                                                                                                                                                                       ";
				contentHtml +="		</tr>                                                                                                                                                                                                                   ";
				for (OrderItem orderItem : pageBean.getList().get(i).getOrderItems()){
					contentHtml +="			<tr>                                                                                                                                                                                                                ";
					contentHtml +="				<td width='60'><img                                                                                                                                                                                             ";
					contentHtml +="					src='/OnlineShoppingMall/statics/"+orderItem.getProduct().getImage()+"' />                                                                                                         ";
					contentHtml +="				</td>                                                                                                                                                                                                           ";
					contentHtml +="				<td>"+orderItem.getProduct().getPname()+"</td>                                                                                                                                                        ";
					contentHtml +="				<td>"+orderItem.getProduct().getShop_price()+"</td>                                                                                                                                                   ";
					contentHtml +="				<td class='quantity' width='60'>"+orderItem.getCount()+"</td>                                                                                                                                                                        ";
					contentHtml +="				<td width='140'><span class='subtotal'>￥"+orderItem.getSubtotal()+"</span>                                                                                                                                                               ";
					contentHtml +="				</td>                                                                                                                                                                                                           ";
					contentHtml +="			</tr>                                                                                                                                                                                                               ";
				}
		}
//		contentHtml +="	<tr>                                                                                                                                                                                                                        ";
//		contentHtml +="		<td colspan='5'>                                                                                                                                                                                                        ";
//		contentHtml +="		<!-- 分页实现 -->                                                                                                                                                                                                       ";
//		contentHtml +="			<div class='pagination'>                                                                                                                                                                                            ";
//		contentHtml +="				<span>第 "+pageBean.getPage()+"/"+pageBean.getTotalPage()+" 页</span>                                                                                                                 ";
//		contentHtml +="				<s:if test='pageBean.page != 1'>                                                                                                                                                                                ";
//		contentHtml +="					<a href='/OnlineShoppingMall/order_searchByState.action?page=1' class='firstPage'>&nbsp;</a>                                                                                                     ";
//		contentHtml +="					<a href='/OnlineShoppingMall/order_searchByState.action?page="+(pageBean.getPage())+"-1' class='previousPage'>&nbsp;</a>                                                              ";
//		contentHtml +="				</s:if>                                                                                                                                                                                                         ";
//		contentHtml +="					                                                                                                                                                                                                            ";
//					for(int m=1;m<=pageBean.getTotalPage();m++){
//								if(pageBean.getPage() != m){
//									contentHtml +="						<a href='/OnlineShoppingMall/order_searchByState.action?page="+m+"'>"+m+"</a>                                                                        ";
//								}else{
//									contentHtml +="						<span class='currentPage'>"+m+"</span>                                                                                                                                               ";
//								}
//					}
//					if(pageBean.getPage() !=pageBean.getTotalPage()){
//							contentHtml +="					<a class='nextPage' href='/OnlineShoppingMall/order_searchByState.action?page="+pageBean.getPage()+"+1'>&nbsp;</a>                                                                ";
//							contentHtml +="					<a class='lastPage' href='/OnlineShoppingMall/order_searchByState.action?page="+pageBean.getTotalPage()+"'>&nbsp;</a>                                                             ";
//					}
//		contentHtml +="			</div>                                                                                                                                                                                                              ";
//		contentHtml +="		</td>                                                                                                                                                                                                                   ";
//		contentHtml +="	</tr>                                                                                                                                                                                                                       ";
		}else{
			contentHtml = "<h1 align='center' style='color: yellowgreen; font-family: '宋体';'>无数据</h1>";
		}
		response.getWriter().println(contentHtml);
	}
	/**
	 * 根据订单id查询订单
	* @date: 2019-3-28 
	* @author: yangtz
	* @title: findByOid 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String findByOid() {
		order = orderService.findByOid(order.getOid());
		return "findByOid";
	}
	
	/**
	 * 为订单付款
	* @date: 2019-3-29 
	* @author: yangtz
	* @title: payOrder 
	* @return 
	* @exception: 
	* @version: 1.0 
	 * @throws IOException 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String payOrder() throws IOException{
		
		//修改订单
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		orderService.update(currOrder);
		
		//付款,设置
		String p0_Cmd = "Buy"; // 业务类型:
		String p1_MerId = "10001126856";// 商户编号:
		String p2_Order = currOrder.getOid().toString();// 订单编号:
//		String p3_Amt = currOrder.getTotal().toString(); // 付款金额:
		String p3_Amt = "0.01";
		String p4_Cur = "CNY"; // 交易币种:
		String p5_Pid = ""; // 商品名称:
		String p6_Pcat = ""; // 商品种类:
		String p7_Pdesc = ""; // 商品描述:
		String p8_Url = "http://127.0.0.1:8081/OnlineShoppingMall/order_callBack.action"; // 商户接收支付成功数据的地址:
		String p9_SAF = ""; // 送货地址:
		String pa_MP = ""; // 商户扩展信息:
		String pd_FrpId = this.pd_FrpId;// 支付通道编码:
		String pr_NeedResponse = "1"; // 应答机制:
		String keyValue = "w0P75wMZ203fr46r5i70V556WHFa94j14yW5J6vuh4yo3nRl5jsqF3c41677"; // 秘钥
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue); // hmac数据签名
		System.out.println("参数"+p0_Cmd+"=="+p1_MerId+"=="+p2_Order+"=="+p3_Amt+"=="
				+p4_Cur+"=="+p5_Pid+"=="+p6_Pcat+"=="+p7_Pdesc+"=="+p8_Url+"=="+p9_SAF+"=="
				+pa_MP+"=="+pd_FrpId+"=="+pr_NeedResponse+"=="+keyValue+"交易签名====================="+hmac);
		//将参数传递给易宝
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		
		// 重定向到易宝
		ServletActionContext.getResponse().sendRedirect(sb.toString());
		return NONE;
	}
	
	/**
	 * 付款成功回调函数
	* @date: 2019-3-29 
	* @author: yangtz
	* @title: callBack 
	* @return 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public String callBack(){
		// 修改订单的状态:
		Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		// 修改订单状态为2:已经付款:
		currOrder.setState(2);
		orderService.update(currOrder);
		this.addActionMessage("支付成功!订单编号为: "+r6_Order +" 付款金额为: "+r3_Amt);
		return "msg";
	}
	
	/**
	 * 修改订单的状态:
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
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(4);
		orderService.update(currOrder);
		return "updateStateSuccess";
	}
	/**
	 * 首页报表
	* @date: 2019-4-24 
	* @author: yangtz
	* @title: reportForIndexPage 
	* @param date
	* @return 
	* @exception: 
	* @version: 1.0 
	 * @throws IOException 
	 * @throws ParseException 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public void reportForIndexPage() throws IOException, ParseException{
		HttpServletResponse response = ServletActionContext.getResponse();
//		HttpServletRequest request = ServletActionContext.getRequest();
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Date today=new Date();  
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String datatable = "";
//		datatable += "<thead>               ";
//		datatable += "	<tr>               ";
//		datatable += "		<th></th>      ";
//		datatable += "		<th>订单量</th>";
//		datatable += "	</tr>              ";
//		datatable += "</thead>              ";
//		datatable += "<tbody>               ";
		for(int i=0;i<15;i++){
			String date = df.format(new Date(today.getTime() - i * 24 * 60 * 60 * 1000));
			int count = orderService.countOrderByDate(date);
			datatable += "	<tr>               ";
			datatable += "		<td>           ";
			datatable += date;
			datatable += "		</td>          ";
			datatable += "		<td>           ";
			datatable += count;
			datatable += "		</td>          ";
			datatable += "	</tr>              ";
		}
//		datatable += "</tbody>              ";
		response.getWriter().println(datatable);
	}
	/**
	 * 
	* @date: 2019-4-26 
	* @author: yangtz
	* @title: reportForIndexPage2 
	* @throws IOException 
	* @exception: 
	* @version: 1.0 
	* @description: 
	* update_version: update_date: update_author: update_note:
	 */
	public void reportForIndexPage2() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		int noPay = orderService.countNoPay();
		int noPost = orderService.countNoPost();
		String datatable = "";
		datatable += "<li><span>待支付&emsp;&emsp;"+noPay+"</span><span>待支付&emsp;&emsp;"+noPay+"</span></li>  ";
		datatable += "<li><span>待发货&emsp;&emsp;"+noPost+"</span><span>待发货&emsp;&emsp;"+noPost+"</span></li>";
		response.getWriter().println(datatable);
	}
	
}
