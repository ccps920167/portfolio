<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order_list.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%     
Order_listVO order_listVO=(Order_listVO)request.getAttribute("order_listVO");
%>
<html>
<head>
<title>Insert title here</title>
</head>
<c:if test="${not empty error}">
		<font style="color: red"></font>
		<ul>
			<c:forEach var="message" items="${error}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
<body>
	<FORM method="post" action="<%=request.getContextPath()%>/Order_list/Order_listServlet">
		<table>

			<tr>	
				<td><b>�q��s��:</b></td>
				<td><input type="TEXT" name="order_id"
				value="<%=(order_listVO==null)?"":order_listVO.getOrder_id() %>"></td>
			</tr>
			<tr>
				<td><b>�ҵ{�s��:</b></td>
				<td><input type="TEXT" name="class_id"
				value="<%=(order_listVO==null)?"":order_listVO.getClass_id()%>"></td>
			</tr>
			<tr>
				<td><b>�ʶR���:</b></td>
				<td><select size="1" name="purchase_plan">
					<option value="�Ҹ��">�Ҹ��</option>
					<option value="���">���</option>
				</select></td>
		</table>
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="�s�W"> 
	</FORM>
</body>
</html>