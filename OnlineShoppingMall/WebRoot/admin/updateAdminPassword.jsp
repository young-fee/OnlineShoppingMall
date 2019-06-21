<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
		<meta charset="UTF-8">
		<style>
			.button {
			background-color: lightblue;
			border: none;
			color: #0E2D5F;
			padding: 5px 15px;
			text-align: center;
			text-decoration: none;
			display: inline-block;
			font-size: 16px;
			margin: 4px 2px;
			cursor: pointer;
			}
		</style>
	</head>
	<body  style="background-color: gainsboro;">	
	<div style="padding-left: 100px; float: left;">
		<form action="${pageContext.request.contextPath}/adminUser_updateAdminPassword.action" method="post" name="myForm" onsubmit="return check();">
			<table border="1" cellspacing="0" cellpadding="5px" align="left"> 
				<tr align="center">
					<td colspan="2"><font color="grey">修改密码</font></td>
				</tr>
				<tr>
					<td><font color="grey">用户名</font></td>
					<td><input type="text" name="username" value=<s:property value="#session.loginAdminUser.username"/> style="padding:10px 10px 0px 0px; border: 0px;background-color: gainsboro;"/></td>
				</tr>
				<tr>
					<td><font color="grey">旧密码</font></td>
					<td><input type="password" name="oldPass" style="padding:10px 10px 0px 0px; border: 0px;background-color: gainsboro; "/></td>
				</tr>
				<tr>
					<td><font color="grey">新密码</font></td>
					<td><input type="password" name="newPass" style="padding:10px 10px 0px 0px; border: 0px;background-color: gainsboro;"/></td>
				</tr>
				<tr>
					<td><font color="grey">确认密码</font></td>
					<td><input type="password" name="rePass" style="padding:10px 10px 0px 0px; border: 0px;background-color: gainsboro;"/></td>
				</tr>
				<tr align="center">
					<td colspan="2"><input type="submit" name="" id="" value="确认修改" class="button"/></td>
				</tr>
			</table>
			
		</form>
			<span id="info" style="float: left; font-size: larger; color: #F29503;"></span>
			<span id="message" style="color: #F29503; font-size: larger;"><s:actionmessage /></span>
	</div>
	</body>
</html>
<script>
	function check(){
		var oldPass = document.myForm.oldPass.value;
		var newPass = document.myForm.newPass.value;
		var rePass = document.myForm.rePass.value;
		if(newPass == null || newPass==''||oldPass==''){
			document.getElementById("info").innerHTML="<font color='#F29503'>密码不能为空!</font>"
			return false;
		}else{
			var pattern = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$/;
			if(!pattern.test(newPass)){
				document.getElementById("info").innerHTML="<font color='#F29503'>密码要求：同时含有数字和字母，且长度要在8-16位之间</font>"
				return false;
			}
			document.getElementById("info").innerHTML="";
		}
		// 校验确认密码:
		if(rePass != newPass){
			document.getElementById("info").innerHTML="<font color='#F29503'>两次密码输入不一致!</font>"
			return false;
		}else{
			document.getElementById("info").innerHTML="";
			document.getElementById("message").innerHTML="";
		}
	}
</script>