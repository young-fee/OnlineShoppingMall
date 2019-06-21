<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
	<head>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath }/admin/pages/css/buttons.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath }/admin/pages/css/select.css">
		<link href="${pageContext.request.contextPath}/admin/pages/css/link_button.css" rel="stylesheet">
	</head>
	<body>
		<br>
		<form id="Form1" name="Form1" action="" method="post" onsubmit="return false;">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<tbody>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<div style="float: left; height:30px;">
								<form id="searchForm" >
									<input type="text" placeholder="收货人/收货地址" class="input" value="" id="keywords"/>
								    <select id="searchState" class="select">
								        <option value="">---请选择---</option>
								        <option value="1">未付款</option>
								        <option value="2">未发货</option>
								        <option value="3">已发货</option>
								        <option value="4">已完成</option>
								   	</select>
								<button type="button" id="search" name="search" value="查找" class="button button-primary button-pill button-small" onclick="searchByKeywords()">查找</button>
								</form>
							</div>
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="border-right: gray 1px solid; border-top: gray 1px solid; border-left: gray 1px solid; width: 100%; word-break: break-all; border-bottom: gray 1px solid; border-collapse: collapse; background-color: #f5fafe; word-wrap: break-word">
								<tr
									style="font-weight: bold; font-size: 12pt; height: 25px; background-color: #afd1f3">

									<td align="center" width="3%">
										序号
									</td>
									<td align="center" width="10%">
										订单编号
									</td>
									<td align="center" width="10%">
										订单金额
									</td>
									<td align="center" width="10%">
										收货人
									</td>
									<td align="center" width="10%">
										电话
									</td>
									<td align="center" width="17%">
										收货地址
									</td>
									<td align="center" width="10%">
										订单状态
									</td>
									<td align="center" width="40%">
										订单详情
									</td>
								</tr>
									<s:iterator var="o" value="pageBean.list" status="status">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="cursor: hand; HEIGHT: 35px" align="center"
												width="3%">
												<s:property value="#status.count"/>
											</td>
											<td style="cursor: hand; HEIGHT: 35px" align="center"
												width="10%">
												<s:property value="#o.oid"/>
											</td>
											<td style="cursor: hand; HEIGHT: 35px" align="center"
												width="10%">
												<s:property value="#o.total"/>
											</td>
											<td style="cursor: hand; HEIGHT: 35px" align="center"
												width="10%">
												<s:property value="#o.name"/>
											</td>
											<td style="cursor: hand; HEIGHT: 35px" align="center"
												width="10%">
												<s:property value="#o.phone"/>
											</td>
											<td style="cursor: hand; HEIGHT: 35px" align="center"
												width="17%">
												<s:property value="#o.addr"/>
											</td>
											<td style="cursor: hand; HEIGHT: 35px" align="center"
												width="10%">
												<s:if test="#o.state==1">
													<font color="red" size="4">未付款</font>
												</s:if>
												<s:if test="#o.state==2">
													<a href="${ pageContext.request.contextPath }/adminOrder_updateState.action?oid=<s:property value="#o.oid"/>" onClick="return confirm('确定发货?');"><font color="red" size="4">发货</font></a>
												</s:if>
												<s:if test="#o.state==3">
													等待确认收货
												</s:if>
												<s:if test="#o.state==4">
													<font color="mediumpurple" size="4">订单完成</font>
												</s:if>
											
											</td>
											<td align="center" style="HEIGHT: 35px">
												<input type="button" value="订单详情" id="but<s:property value="#o.oid"/>" onclick="showDetail(<s:property value="#o.oid"/>)"/>
												<div id="div<s:property value="#o.oid"/>">
													
												</div>
											</td>
							
										</tr>
									</s:iterator>	
							</table>
						</td>
					</tr>
					<tr align="center" id="pageContent">
						<td colspan="7">
							第<s:property value="pageBean.page"/>/<s:property value="pageBean.totalPage"/>页 
							<s:if test="pageBean.page != 1">
								<a class="link_button" href="${ pageContext.request.contextPath }/adminOrder_findAll.action?page=1">首页</a>|
								<a class="link_button" href="${ pageContext.request.contextPath }/adminOrder_findAll.action?page=<s:property value="pageBean.page-1"/>">上一页</a>|
							</s:if>
							<s:if test="pageBean.page != pageBean.totalPage">
								<a class="link_button" href="${ pageContext.request.contextPath }/adminOrder_findAll.action?page=<s:property value="pageBean.page+1"/>">下一页</a>|
								<a class="link_button" href="${ pageContext.request.contextPath }/adminOrder_findAll.action?page=<s:property value="pageBean.totalPage"/>">尾页</a>|
							</s:if>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</body>
</html>
<script src="${pageContext.request.contextPath}/statics/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	function showDetail(oid){
		var button = document.getElementById("but"+oid);
		var div1 = document.getElementById("div"+oid);
 		if(button.value == "订单详情"){
			// 1.创建异步对象
			var xhr = createXmlHttp();
			// 2.设置监听
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4){
					if(xhr.status == 200){
						
						div1.innerHTML = xhr.responseText;
					}
				}
			}
			// 3.打开连接
			xhr.open("GET","${pageContext.request.contextPath}/adminOrder_findOrderItem.action?oid="+oid+"&time="+new Date().getTime(),true);
			// 4.发送
			xhr.send(null);
			button.value = "关闭";
		}else{
			div1.innerHTML = "";
			button.value="订单详情";
		}
		
	}
	function createXmlHttp(){
		   var xmlHttp;
		   try{ // Firefox, Opera 8.0+, Safari
		        xmlHttp=new XMLHttpRequest();
		    }
		    catch (e){
			   try{// Internet Explorer
			         xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
			      }
			    catch (e){
			      try{
			         xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
			      }
			      catch (e){}
			      }
		    }

			return xmlHttp;
		 }
	function searchByKeywords(){
			var keywords=document.getElementById("keywords").value;
			var searchState=document.getElementById("searchState").value;
			if(keywords!=""||searchState!=""){
				$.ajax({
	             type: "POST",
	             url: "${pageContext.request.contextPath}/adminOrder_searchByKeywords.action",
	             data: {
	             	"keywords":keywords,
	             	"searchState":searchState
	             },
	             dataType: "html",
	             success: function(data){
						$("#pageContent").remove();
						$("#DataGrid1").html(data);
	         }});
			}else{
				window.location.href = "${pageContext.request.contextPath}/adminOrder_findAll.action?page=1";
			}
	}
$("body").keydown(function() {
   if (event.keyCode == "13") {//keyCode=13是回车键
       $('#search').click();
     }
});	
</script>

