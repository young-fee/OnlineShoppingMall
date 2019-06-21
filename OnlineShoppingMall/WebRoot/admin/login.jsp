<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html> 
<html lang="en"> 
<head> 
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <title>商城后台管理系统</title> 
    <link href="${pageContext.request.contextPath}/admin/pages/css/base.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/admin/pages/css/login/login.css" rel="stylesheet">

</head> 
<body>
	<div class="login-hd">
                <img src="${pageContext.request.contextPath}/admin/pages/images/main/shopLogo.png" alt="logo" width="25%"/>
        </div>
	</div>
	<div class="login-bd">
		<div class="bd-inner">
			<div class="inner-wrap">
				<div class="lg-zone">
					<div class="lg-box">
						<div class="lg-label"><h4>后台管理员登录</h4></div>
						<form  id="loginForm" action="${pageContext.request.contextPath}/adminUser_login.action" method="post" target="_parent">
							<div class="lg-username input-item clearfix">
								<i class="iconfont">&#xe60d;</i>
								<input id="username" name="username" type="text" placeholder="账号" value="">
							</div>
							<div class="lg-password input-item clearfix">
								<i class="iconfont">&#xe634;</i>
								<input  name="password" type="password" placeholder="密码">
							</div>
							<div align="center" style="height: 30px ; color: red; font-size: medium;"><s:actionerror /><s:actionmessage /> </div>
							<div class="enter">
								<a href="javascript:;" id="loginclick" class="purchaser" style="margin-left: 110px;" onClick="adminLogin();">登录</a>
							</div>
						</form>
					</div>
				</div>
				<div class="lg-poster"></div>
			</div>
		</div>
	</div>
	<div class="login-ft">
		<div class="ft-inner">
			<div class="other-info">CopyRight© 网上商城毕业设计</div>
		</div>
	</div>
</body> 
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery-1.8.3.js" ></script>
<script type="text/javascript">
  function adminLogin(){
  	document.getElementById("loginForm").submit();
  }
  $("body").keydown(function() {
             if (event.keyCode == "13") {//keyCode=13是回车键
                 $('#loginclick').click();
             }
         });
</script>
