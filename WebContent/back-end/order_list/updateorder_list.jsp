<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order_list.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	Order_listVO order_listVO = (Order_listVO) request.getAttribute("order_listVO");
%>
<html>
<head>

<title>Insert title here</title>
</head>
<body>
	<c:if test="${not empty error}">
		<font style="color: red"></font>
		<ul>
			<c:forEach var="message" items="${error}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<FORM method="post"
		action="<%=request.getContextPath()%>/Order_list/Order_listServlet">
		<table>
			<tr>
				<td><b>���ӽs��</b></td>
				<td><%=order_listVO.getOrder_list_id()%></td>
			</tr>

			<tr>
				<td><b>�q��s��:</b></td>
				<td><input type="text" name="order_id"
					value=<%=order_listVO.getOrder_id()%>></td>
			</tr>

			<tr>
				<td><b>�ҵ{�s��:</b></td>
				<td><input  name="class_id"  type="text"
					value=<%=order_listVO.getClass_id()%>></td>
			</tr>

			<tr>
				<td><b>�ʶR���:</b></td>
				<td><select size="1" name="purchase_plan">
				<option value="�Ҹ��">�Ҹ��</option>
				<option value="���">���</option></select></td>
			</tr>

		</table>
		<input type="hidden" name="action" value="update"> <input
			type="hidden" name="order_list_id" value=<%=order_listVO.getOrder_list_id()%>>
		<input type="submit" value="�e�X�ק�">
	</FORM>
</body>
</html>