<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
	<head>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	
	<body>
		<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/userAdmin_update.action" method="post" >
			<input type="hidden" name="uid" value="<s:property value="model.uid"/>" />
			<input type="hidden" name="state" value="<s:property value="model.state"/>" />
			<input type="hidden" name="code" value="<s:property value="model.code"/>" />
			&nbsp;
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
						height="26">
						<strong>编辑用户
						</strong>
					</td>
				</tr>

				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						用户名：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="username" value="<s:property value="model.username"/>" id="userAction_save_do_logonName"  onblur="checkUsername()" class="bg"/>
						<span id="info"></span>
					</td>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						密码：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="password" value="<s:property value="model.password"/>" id="userAction_save_do_logonName" class="bg"/>
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						真实姓名：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="name" value="<s:property value="model.name"/>" id="userAction_save_do_logonName" class="bg"/>
						</td>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						邮箱：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="email" value="<s:property value="model.email"/>" id="userAction_save_do_logonName" class="bg"/>
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						电话：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="phone" value="<s:property value="model.phone"/>" id="userAction_save_do_logonName" class="bg"/>
						</td>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						地址：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="addr" value="<s:property value="model.addr"/>" id="userAction_save_do_logonName" class="bg"/>
					</td>
				</tr>
			
				<tr>
					<td class="ta_01" style="width: 100%" align="center"
						bgColor="#f5fafe" colSpan="4">
						<button type="submit" id="userAction_save_do_submit" value="确定" class="button_ok">
							&#30830;&#23450;
						</button>

						<font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
						<button type="reset" value="重置" class="button_cancel">&#37325;&#32622;</button>

						<font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
						<input class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
						<span id="Label1"></span>
					</td>
				</tr>
			</table>
		</form>
	</body>
</HTML>
<script src="${pageContext.request.contextPath}/statics/js/jquery-1.8.3.js"></script>
<script>
	function checkUsername(){
		// 校验用户名:
		// 获得用户名文本框的值:
		var username = document.Form1.username.value;
		if(username == null || username == ''){
			document.getElementById("info").innerHTML="<font color='red'>用户名不能为空!</font>"
			return false;
		}else if(username == '<s:property value="model.username"/>'){
			document.getElementById("info").innerHTML="";
			return false;
		}else{
			document.getElementById("info").innerHTML="";
		}
		$.ajax({
	             type: "GET",
	             url: "${pageContext.request.contextPath}/user_findByUsername.action",
	             data: {
	             	"username":username
	             },
	             dataType: "html",
	             success: function(data){
	             				document.getElementById("info").innerHTML = data;
	                      }
	         });
	}
</script>