<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath }/admin/pages/css/buttons.css">
	</HEAD>
	<body>
		<br>
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="right">
							<button type="button" id="add" name="add" value="添加" class="button button-primary button-pill button-small" onclick="addCategory()">
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
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 35px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="10%">
										序号
									</td>
									<td align="center" width="30%">
										一级分类名称
									</td>
									<td width="20%" align="center">
										编辑
									</td>
									<td width="20%" align="center">
										删除
									</td>
								</tr>
									<s:iterator var="c" value="cList" status="status">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 35px" align="center"
												width="18%">
												<s:property value="#status.count"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 35px" align="center"
												width="17%">
												<s:property value="#c.cname"/>
											</td>
											<td align="center" style="HEIGHT: 35px">
												<a href="${ pageContext.request.contextPath }/adminCategory_edit.action?cid=<s:property value="#c.cid"/>">
													<img src="${pageContext.request.contextPath}/statics/images/i_edit.gif" border="0" style="CURSOR: hand">
												</a>
											</td>
									
											<td align="center" style="HEIGHT: 35px">
												<a href="${ pageContext.request.contextPath }/adminCategory_delete.action?cid=<s:property value="#c.cid"/>" onClick="return confirm('确定删除?');">
													<img src="${pageContext.request.contextPath}/statics/images/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
												</a>
											</td>
										</tr>
									</s:iterator>	
							</table>
						</td>
					</tr>
				</TBODY>
			</table>
	</body>
</HTML>
<script type="text/javascript">
	function addCategory(){
		window.location.href = "${pageContext.request.contextPath}/admin/category/add.jsp";
	}
</script>
