<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath }/admin/pages/css/buttons.css">
		<link href="${pageContext.request.contextPath}/admin/pages/css/link_button.css" rel="stylesheet">
		<style type="text/css">
			.showImage{
				  cursor:pointer;
				  transition:all 0.5s ease-in-out;
				}
			.showImage:hover{
			  display:block;
			  transform:scale(5);
			  margin-left:20%;
			}
		</style>
	</HEAD>
	<body>
		<br>
		<form id="Form1" name="Form1" action="" method="post" onsubmit="return false;">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="right">
							<div style="float: left;">
								<form id="searchForm" >
									<input type="text" placeholder="请输入关键字" class="input" value="" id="keywords" /> 
								<button type="button" id="search" name="search" value="查找" class="button button-primary button-pill button-small" onclick="searchByKeywords()">查找</button>
								</form>
							</div>
							<button type="button" id="add" name="add" value="添加" class="button button-primary button-pill button-small" onclick="addProduct()">
								&#28155;&#21152;
							</button>
						</td>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="5%">
										序号
									</td>
									<td align="center" width="10%">
										商品图片
									</td>
									<td align="center" width="25%">
										商品名称
									</td>
									<td align="center" width="12%">
										商品价格
									</td>
									<td align="center" width="12%">
										商品数量
									</td>
									<td align="center" width="12%">
										是否热门
									</td>
									<td width="12%" align="center">
										编辑
									</td>
									<td width="12%" align="center">
										删除
									</td>
								</tr>
									<s:iterator var="p" value="pageBean.list" status="status">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												<s:property value="#status.count"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												<img width="40" class="showImage" height="45" src="${ pageContext.request.contextPath }/statics/<s:property value="#p.image"/>">
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="25%">
												<s:property value="#p.pname"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="12%">
												<s:property value="#p.shop_price"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="12%">
												<s:property value="#p.num"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="12%">
												<s:if test="#p.is_hot==1">
													是
												</s:if>
												<s:else>
													否
												</s:else>
											</td>
											<td align="center" style="HEIGHT: 22px">
												<a href="${ pageContext.request.contextPath }/adminProduct_edit.action?pid=<s:property value="#p.pid"/>">
													<img src="${pageContext.request.contextPath}/statics/images/i_edit.gif" border="0" style="CURSOR: hand">
												</a>
											</td>
									
											<td align="center" style="HEIGHT: 22px">
												<a href="${ pageContext.request.contextPath }/adminProduct_delete.action?pid=<s:property value="#p.pid"/>" onClick="return confirm('确定删除?');">
													<img src="${pageContext.request.contextPath}/statics/images/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
												</a>
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
								<a class="link_button" href="${ pageContext.request.contextPath }/adminProduct_findAll.action?page=1">首页</a>|
								<a class="link_button" href="${ pageContext.request.contextPath }/adminProduct_findAll.action?page=<s:property value="pageBean.page-1"/>">上一页</a>|
							</s:if>
							<s:if test="pageBean.page != pageBean.totalPage">
								<a class="link_button" href="${ pageContext.request.contextPath }/adminProduct_findAll.action?page=<s:property value="pageBean.page+1"/>">下一页</a>|
								<a class="link_button" href="${ pageContext.request.contextPath }/adminProduct_findAll.action?page=<s:property value="pageBean.totalPage"/>">尾页</a>|
							</s:if>
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
	</body>
</HTML>
<script src="${pageContext.request.contextPath}/statics/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	function addProduct(){
		window.location.href = "${pageContext.request.contextPath}/adminProduct_addPage.action";
	}
	
	function searchByKeywords(){
			var keywords=document.getElementById("keywords").value;
			if(keywords!=""){
				$.ajax({
	             type: "POST",
	             url: "${pageContext.request.contextPath}/adminProduct_searchByKeywords.action",
	             data: {
	             	"keywords":keywords
	             },
	             dataType: "html",
	             success: function(data){
	             		console.log(data);
						$("#pageContent").remove();
						$("#DataGrid1").html(data);
	        	 }
	         });
			}else{
				window.location.href = "${pageContext.request.contextPath}/adminProduct_findAll.action?page=1";
			}
	}
$("body").keydown(function() {
   if (event.keyCode == "13") {//keyCode=13是回车键
       $('#search').click();
     }
});	
</script>

