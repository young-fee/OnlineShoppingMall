<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0048)http://localhost:8080/mango/product/list/1.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网上商城</title>
<link href="${pageContext.request.contextPath}/statics/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/statics/css/product.css" rel="stylesheet" type="text/css"/>

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
<div class="container productList">
		<div class="span6">
			<div class="hotProductCategory">
					<s:iterator var="c" value="#session.clist">
						<dl>
							<dt>
								<a href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="#c.cid"/>&page=1"><s:property value="#c.cname"/></a>
							</dt>
								<s:iterator var="cs" value="#c.categorySeconds">
									<dd>
										<a href="${ pageContext.request.contextPath }/product_findByCsid.action?csid=<s:property value="#cs.csid"/>&page=1"><s:property value="#cs.csname"/></a>
									</dd>
								</s:iterator>
						</dl>
					</s:iterator>
			</div>
		</div>
		<div class="span18 last">
			
			<form id="productForm" action="./image/蔬菜 - Powered By Mango Team.htm" method="get">
					
				<div id="result" class="result table clearfix">
						<ul>
							<s:iterator var="p" value="pageBean.list">
								<li>
									<a href="${ pageContext.request.contextPath }/product_findByPid.action?pid=<s:property value="#p.pid"/>">
										<img src="${pageContext.request.contextPath}/statics/<s:property value="#p.image"/>" width="170" height="170"  style="display: inline-block;">
										   
										<span style='color:green'>
										 <s:property value="#p.pname"/>
										</span>
										 
										<span class="price">
											商城价： ￥<s:property value="#p.shop_price"/>
										</span>
										 
									</a>
								</li>
							</s:iterator>	
								
						</ul>
				</div>
	<!-- 分页实现 -->
	<div class="pagination">
			<span>第 <s:property value="pageBean.page"/>/<s:property value="pageBean.totalPage"/> 页</span>
		<s:if test="cid != null">
			<s:if test="pageBean.page != 1">
				<a href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="cid"/>&page=1" class="firstPage">&nbsp;</a>
				<a href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.page-1"/>" class="previousPage">&nbsp;</a>
			</s:if>
				
			<s:iterator var="i" begin="1" end="pageBean.totalPage">
				<s:if test="pageBean.page != #i">
					<a href="${ pageContext.request.contextPath }/product_findByCid.action?cid=<s:property value="cid"/>&page=<s:property value="#i"/>"><s:property value="#i"/></a>
				</s:if>
				<s:else>
					<span class="currentPage"><s:property value="#i"/></span>
				</s:else>
			</s:iterator>
			
			<s:if test="pageBean.page != pageBean.totalPage">	
				<a class="nextPage" href="${ pageContext.request.contextPath }/product_findByCid.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.page+1"/>">&nbsp;</a>
				<a class="lastPage" href="${ pageContext.request.contextPath }/product_findByCid.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
			</s:if>
		</s:if>	
		<s:if test="csid != null">
			<s:if test="pageBean.page != pageBean.totalPage">	
				<a class="nextPage" href="${pageContext.request.contextPath}/product_findByCsid.action?csid=<s:property value="csid"/>&page=<s:property value="pageBean.page+1"/>">&nbsp;</a>
				
				<a class="lastPage" href="${pageContext.request.contextPath}/product_findByCsid.action?csid=<s:property value="csid"/>&page=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
			</s:if>
			<s:iterator var="i" begin="1" end="pageBean.totalPage">
				<s:if test="pageBean.page != #i">
					<a href="${ pageContext.request.contextPath }/product_findByCsid.action?csid=<s:property value="csid"/>&page=<s:property value="#i"/>"><s:property value="#i"/></a>
				</s:if>
				<s:else>
					<span class="currentPage"><s:property value="#i"/></span>
				</s:else>
			</s:iterator>
			
			<s:if test="pageBean.page != pageBean.totalPage">	
				<a class="nextPage" href="${ pageContext.request.contextPath }/product_findByCsid.action?csid=<s:property value="csid"/>&page=<s:property value="pageBean.page+1"/>">&nbsp;</a>
				<a class="lastPage" href="${ pageContext.request.contextPath }/product_findByCsid.action?csid=<s:property value="csid"/>&page=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
			</s:if>
		</s:if>	
	</div>
			</form>
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