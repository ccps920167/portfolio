<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ page import="com.order_list.model.*" %>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
Order_listService Order= new Order_listService();
List<Order_listVO> list=Order.getAll();
pageContext.setAttribute("list", list);
%>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
	<table>
<c:if test="${not empty error}">
	<font style="color:red">
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
		<tr>
			<th>明細編號</th>
			<th>訂單編號</th>
			<th>課程編號</th>
			<th>購買方案</th>
		
		</tr>
		<c:forEach var="order_list" items="${list}">
		<tr>
		<td>${order_list.order_list_id}</td>
		<td>${order_list.order_id}</td>
		<td>
		
		<jsp:useBean id="class_id" class="com.class_info.model.Class_infoService">
		<c:if test="${class_id == order_list.class_id}">
				<td>${class_id.class_name}</td>
		</c:if>
		</jsp:useBean>
		<td>${order_list.purchase_plan}</td>	
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Order_list/Order_listServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="order_list_id"  value="${order_list.order_list_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			  </FORM>
			</td>
		
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Order_list/Order_listServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="order"  value="${order_list.order_list_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
		
		</c:forEach>
	</table>
	<a href="<%=request.getContextPath()%>/back-end/order_list/select-page.jsp">回首頁</a>
	
</body>
</html>