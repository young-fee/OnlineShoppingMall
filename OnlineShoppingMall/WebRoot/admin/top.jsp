<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link href="${pageContext.request.contextPath }/admin/pages/css/base.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath }/admin/pages/css/platform.css" rel="stylesheet">
		<link rel="stylesheet" href="${pageContext.request.contextPath }/admin/custom/easyui.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath }/statics/css/home.css">
	</head>
	<body style="background: #f0f9fd;">
	 <div class="container">
	 	<div id="pf-hd">
            <div class="pf-logo">
           	 <a href="${pageContext.request.contextPath }/adminUser_toMainPage.action"  target="rightFrame" >
                <img src="${pageContext.request.contextPath }/admin/pages/images/main/shopLogo.png" alt="logo" width="250px">
            </a>
            </div>
		<!--用户信息-->
			<div class="home-set">
				<ul class="home-action">
					 <a href="${pageContext.request.contextPath }/adminUser_toMainPage.action"  target="rightFrame" >
						 <li title="首页" style="float: left; margin-top: 30px; margin-right: 20px; "> 
						 	<i class="iconfont xiala">&#xe648;</i>
						 	<span style="color: white;">首页</span>
						 </li>
					  <a href="${pageContext.request.contextPath }/adminUser_updatePassPage.action"  target="rightFrame" >
					<li title="密码服务" style="float: left; margin-top: 30px;">
						 <a href="${pageContext.request.contextPath }/adminUser_updatePassPage.action"  target="rightFrame" >
							 <i class="iconfont xiala">&#xe634;</i>
                     		<span style="color: white;">密码服务</span>
                     	</a>
					</li>
					<li title="退出"  style="float: left;margin-top: 30px;">
						 <a href="${pageContext.request.contextPath }/adminUser_quit.action"  target="admin/login.jsp" >
							 <i class="iconfont xiala">&#xe60e;</i>
	                		 <span style="color: white;">退出</span>
	                	</a>
					</li>
				</ul>
			</div>
	 </div>
</body> 
</html>