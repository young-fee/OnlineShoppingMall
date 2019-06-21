<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>订单页面</title>
<link href="${pageContext.request.contextPath}/statics/css/common.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/css/cart.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/css/product.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/admin/pages/css/buttons.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/admin/pages/css/select.css">
</head>
<body>

<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="${pageContext.request.contextPath}/index.action">
				<img src="${pageContext.request.contextPath}/statics/image/r___________renleipic_01/logo2.png" height="50" width="200"  alt="网上商城"/>
			</a>
		</div>
	</div>
	<div class="span9">
		<div class="headerAd">
			<img src="${pageContext.request.contextPath}/statics/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
		</div>	
	</div>
	<!-- 将菜单页面包含进来 -->
	<%@include file="menu.jsp" %>
	</div>
	<div class="container cart">
		<div class="span24">
			<div style="float: right; height:30px;">
					<form id="searchForm" >
					    <select id="searchState" class="select">
					        <option value="">---请选择---</option>
					        <option value="1">未付款</option>
					        <option value="2">已付款</option>
					        <option value="3">未收货</option>
					        <option value="4">已完成</option>
					   	</select>
					<button type="button" id="search" name="search" value="查找" class="button button-primary button-rounded button-small" onclick="searchByKeywords()">查找</button>
					</form>
			</div>
			<table>
				<tbody id="orderBody">
					<s:iterator var="order" value="pageBean.list">
						<tr>
							<th colspan="5" style="background: beige;">订单编号:<s:property value="#order.oid" />&nbsp;&nbsp;&nbsp;&nbsp;订单金额:
							<font color="red"><s:property value="#order.total" /></font>
							&nbsp;&nbsp;&nbsp;&nbsp;
							
								<s:if test="#order.state == 1">
									<a href="${ pageContext.request.contextPath }/order_findByOid.action?oid=<s:property value="#order.oid" />">订单状态：<font color="red"><font color="mediumpurple" size="4">付款</font></font></a>
								</s:if>
								<s:if test="#order.state == 2">
									<font color="mediumpurple" size="4">已付款</font>
								</s:if>
								<s:if test="#order.state == 3">
									<a href="${ pageContext.request.contextPath }/order_updateState.action?oid=<s:property value="#order.oid" />" onClick="return confirm('确定收货?');"><font color="mediumpurple" size="4">确认收货</font></a>
								</s:if>
								<s:if test="#order.state == 4">
									<font color="mediumpurple" size="4">交易完成</font>
								</s:if>
							</th>
						</tr>
						<tr>
							<th>图片</th>
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
						</tr>
						<s:iterator var="orderItem" value="#order.orderItems">
							<tr>
								<td width="60"><img
									src="${pageContext.request.contextPath }/statics/<s:property value="#orderItem.product.image"/>" />
								</td>
								<td><s:property value="#orderItem.product.pname" /></td>
								<td><s:property value="#orderItem.product.shop_price" /></td>
								<td class="quantity" width="60"><s:property
										value="#orderItem.count" /></td>
								<td width="140"><span class="subtotal">￥<s:property
											value="#orderItem.subtotal" /></span>
								</td>
							</tr>
						</s:iterator>
					</s:iterator>
					<tr>
						<td colspan="5">
						<!-- 分页实现 -->
							<div class="pagination">
								<span>第 <s:property value="pageBean.page"/>/<s:property value="pageBean.totalPage"/> 页</span>
								<s:if test="pageBean.page != 1">
									<a href="${pageContext.request.contextPath}/order_findByUid.action?page=1" class="firstPage">&nbsp;</a>
									<a href="${pageContext.request.contextPath}/order_findByUid.action?page=<s:property value="pageBean.page-1"/>" class="previousPage">&nbsp;</a>
								</s:if>
									
								<s:iterator var="i" begin="1" end="pageBean.totalPage">
									<s:if test="pageBean.page != #i">
										<a href="${ pageContext.request.contextPath }/order_findByUid.action?page=<s:property value="#i"/>"><s:property value="#i"/></a>
									</s:if>
									<s:else>
										<span class="currentPage"><s:property value="#i"/></span>
									</s:else>
								</s:iterator>
								
								<s:if test="pageBean.page != pageBean.totalPage">	
									<a class="nextPage" href="${ pageContext.request.contextPath }/order_findByUid.action?page=<s:property value="pageBean.page+1"/>">&nbsp;</a>
									<a class="lastPage" href="${ pageContext.request.contextPath }/order_findByUid.action?page=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
								</s:if>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="${pageContext.request.contextPath}/statics/image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势">
</div>	</div>
	<div class="span24">
		<ul class="bottomNav">
			<li><a>关于我们</a>|</li>
			<li><a>联系我们</a>|</li>
			<li><a>招贤纳士</a>|</li>
			<li><a>法律声明</a>|</li>
			<li><a>友情链接</a>|</li>
			<li><a target="_blank">支付方式</a>|</li>
			<li><a target="_blank">配送方式</a>|</li>
			<li><a>服务声明</a>|</li>
			<li><a>广告声明</a></li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 网上商城毕业设计</div>
	</div>
</div>
</body>
</html>
<script src="${pageContext.request.contextPath}/statics/js/jquery-1.8.3.js"></script>
<script>
function searchByKeywords(){
	var searchState=document.getElementById("searchState").value;
	if(searchState!=""){
			$.ajax({
             type: "POST",
             url: "${pageContext.request.contextPath}/order_searchByState.action?page=1",
             data: {
             	"searchState":searchState
             },
             dataType: "html",
             success: function(data){
             		$("#orderBody").html(data);
         }});
		}else{
			window.location.href = "${ pageContext.request.contextPath }/order_findByUid.action?page=1";
		}
}
$("body").keydown(function() {
   if (event.keyCode == "13") {//keyCode=13是回车键
       $('#search').click();
     }
});	
</script>