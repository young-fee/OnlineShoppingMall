<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>会员登录</title>

<link href="${pageContext.request.contextPath}/statics/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/statics/css/login.css" rel="stylesheet" type="text/css"/>

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
					<img src="${pageContext.request.contextPath}/statics/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障">
</div>	</div>
</div>	<div class="container login">
		<div class="span12">
<div class="ad">
					<img src="${pageContext.request.contextPath}/statics/image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">
</div>		</div>
		<div class="span12 last">
			<div class="wrap">
				<div class="main">
					<div class="title">
						<strong>会员登录</strong>USER LOGIN 
					</div>
					<form id="loginForm" action="${ pageContext.request.contextPath }/user_login.action" method="post" novalidate="novalidate" onsubmit="return checkForm();">
						<table>
							<tbody><tr>
								<th>
										用户名/E-mail:
								</th>
								<td>
									<input type="text" id="username" name="username" class="text" maxlength="20">
									<span id="info"></span>
								</td>
							</tr>
							<tr>
								<th>
									密&nbsp;&nbsp;码:
								</th>
								<td>
									<input type="password" id="password" name="password" class="text" maxlength="20" autocomplete="off">
									<span id="passwordErr1"></span>
								</td>
							</tr>
								<tr>
									<th><span class="requiredField">*</span>验证码:</th>
									<td>
										<span class="fieldSet">
											<input type="text" id="checkCode" name="checkCode" class="text captcha" maxlength="4" autocomplete="off">
											<img id="checkImg" onclick="change()" class="captchaImage" src="${pageContext.request.contextPath}/check_changeImg.action" title="点击更换验证码">
											<span id="checkErr"></span>
										</span>
									</td>
								</tr>
								&nbsp;
							<tr>
								<th>&nbsp;
									
								</th>
								<td>
									<input type="submit" class="submit" value="登 录"/>
									<font color='red' size='3'><s:actionerror /></font>
								</td>
							</tr>
							<tr class="register">
								<th>&nbsp;
									
								</th>
								<td>
									<dl>
										<dt>还没有注册账号？</dt>
										<dd>
											立即注册即可体验在线购物！
											<a href="${ pageContext.request.contextPath }/user_registPage.action">立即注册</a>
										</dd>
									</dl>
								</td>
							</tr>
						</tbody>
						</table>
					</form>
				</div>
			</div>
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
<script>
	function change(){
		var img1 = document.getElementById("checkImg");
		img1.src="${pageContext.request.contextPath}/check_changeImg.action?"+new Date().getTime();
	}
	function checkForm(){
		// 校验用户名:
		// 获得用户名文本框的值:
		var username = document.getElementById("username").value;
		if(username == null || username == ''){
			document.getElementById("info").innerHTML="<font color='red'>用户名不能为空!</font>"
			return false;
		}else{
			document.getElementById("info").innerHTML="";
		} 
		// 校验密码:
		// 获得密码框的值:
		var password = document.getElementById("password").value;
		if(password == null || password == ''){
			document.getElementById("passwordErr1").innerHTML="<font color='red'>密码不能为空!</font>"
			return false;
		}else{
			document.getElementById("passwordErr1").innerHTML="";
		}
		//验证码
		var checkCode = document.getElementById("checkCode").value;
		if(checkCode == null || checkCode == ''){
			document.getElementById("checkErr").innerHTML="<font color='red'>验证码不能为空!</font>"
			return false;
		}else{
			document.getElementById("checkErr").innerHTML="";
		}
	}
</script>
</html>
