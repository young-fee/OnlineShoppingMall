<%@page import="com.whpu.onlineShoppingMall.utils.PageBean"%>
<%@page import="com.whpu.onlineShoppingMall.categorysecond.entity.CategorySecond"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
	<head>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath }/admin/pages/css/buttons.css">
		<link href="${pageContext.request.contextPath}/admin/pages/css/link_button.css" rel="stylesheet">
	</head>
	<body>
		<br>
		<form id="Form1" name="Form1" action="" method="post" onsubmit="return false;">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<tbody>
					<tr>
						<td class="ta_01" align="right">
						<div style="float: left;">
							<form id="searchForm" >
								<input type="text" placeholder="请输入关键字" class="input" value="" id="keywords"/> 
							<button type="button" id="search" name="search" value="查找" class="button button-primary button-pill button-small" onclick="searchByKeywords()">查找</button>
							</form>
						</div>
							<button type="button" id="add" name="add" value="添加" class="button button-primary button-pill button-small" onclick="addCategorySecond()">&#28155;&#21152;</button>
						</td>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe" >
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="border-right: gray 1px solid; border-top: gray 1px solid; border-left: gray 1px solid; width: 100%; word-break: break-all; border-bottom: gray 1px solid; border-collapse: collapse; background-color: #f5fafe; word-wrap: break-word">
								<tr
									style="font-weight: bold; font-size: 12pt; height: 25px; background-color: #afd1f3">

									<td align="center" width="8%" height="35px">
										序号
									</td>
									<td align="center" width="25%" height="35px">
										二级分类名称
									</td>
									<td width="10%" align="center" height="35px">
										编辑
									</td>
									<td width="10%" align="center" height="35px">
										删除
									</td>
								</tr>
									<s:iterator var="cs" value="pageBean.list" status="status">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand;" align="center"
												width="8%" height="35px">
												<s:property value="#status.count"/>
											</td>
											<td style="CURSOR: hand;" align="center"
												width="25%"  height="35px">
												<s:property value="#cs.csname"/>
											</td>
											<td align="center" height="35px">
												<a href="${ pageContext.request.contextPath }/adminCategorySecond_edit.action?csid=<s:property value="#cs.csid"/>">
													<img src="${pageContext.request.contextPath}/statics/images/i_edit.gif" border="0" style="CURSOR: hand">
												</a>
											</td>
									
											<td align="center" height="35px">
												<a href="${ pageContext.request.contextPath }/adminCategorySecond_delete.action?csid=<s:property value="#cs.csid"/>" onClick="return confirm('确定删除?');">
													<img src="${pageContext.request.contextPath}/statics/images/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
												</a>
											</td>
										</tr>
									</s:iterator>	
							</table>
						</td>
						
					</tr>
					<tr align="center" id="pageContent">
						<td colspan="4" >
							第<s:property value="pageBean.page"/>/<s:property value="pageBean.totalPage"/>页  
							<s:if test="pageBean.page != 1">
								<a class="link_button" href="${pageContext.request.contextPath }/adminCategorySecond_findAll.action?page=1">首页</a> |
								<a class="link_button" href="${pageContext.request.contextPath }/adminCategorySecond_findAll.action?page=<s:property value="pageBean.page-1"/>">上一页</a> |
							</s:if>
							<s:if test="pageBean.page != pageBean.totalPage">
							<a class="link_button" href="${pageContext.request.contextPath }/adminCategorySecond_findAll.action?page=<s:property value="pageBean.page+1"/>">下一页</a> |
							<a class="link_button" href="${pageContext.request.contextPath }/adminCategorySecond_findAll.action?page=<s:property value="pageBean.totalPage"/>">尾页</a> 
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
	function addCategorySecond(){
		window.location.href = "${pageContext.request.contextPath}/adminCategorySecond_addPage.action";
	}
	function searchByKeywords(){
			var keywords=document.getElementById("keywords").value;
			if(keywords!=""){
				$.ajax({
	             type: "POST",
	             url: "${pageContext.request.contextPath}/adminCategorySecond_searchByKeywords.action",
	             data: {
	             	"keywords":keywords
	             },
	             dataType: "html",
	             success: function(data){
						$("#pageContent").remove();
						$("#DataGrid1").html(data);
	         }});
			}else{
				window.location.href = "${pageContext.request.contextPath}/adminCategorySecond_findAll.action?page=1";
			}
	}
	$("body").keydown(function() {
             if (event.keyCode == "13") {//keyCode=13是回车键
                 $('#search').click();
             }
         });
</script>
