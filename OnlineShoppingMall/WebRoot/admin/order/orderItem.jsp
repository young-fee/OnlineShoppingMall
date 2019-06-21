<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<table width="100%">
	<s:iterator var="orderItem" value="list">
	<tr>
		<td width="25%"><img width="150" height="150" src="${ pageContext.request.contextPath }/statics/<s:property value="#orderItem.product.image"/>"></td>
		<td width="15%"><s:property value="#orderItem.product.pname"/></td>
		<td width="20%">数量：<s:property value="#orderItem.count"/></td>
		<td width="40%">总计：<s:property value="#orderItem.subtotal"/></td>
	</tr>
	</s:iterator>
</table>