<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.order_info.model.*" %>
<%@ page import="com.order_list.model.*" %>
<%@ page import="com.class_info.model.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
Map <Order_infoVO,List<Order_listVO>>order_infoVO1= (Map<Order_infoVO,List<Order_listVO>>)request.getAttribute("order_infoVO1");
Set<Order_infoVO> key = order_infoVO1.keySet();	
pageContext.setAttribute("key", key);
String member_id=(String)request.getAttribute("member_id");
pageContext.setAttribute("member_id",member_id);
%>
<!-- bootstrap -->
<link 
	rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
	
<title>TOMATO��x�޲z���x</title>
</head>
<body>
<%@ include file="header-back.jsp"%>

	<div style="margin-left: 250px; margin-right: 20px; margin-top: 100px" >
		<nav aria-label="breadcrumb" style="margin-top: 30px;">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Home</a></li>
				<li class="breadcrumb-item active" aria-current="page">�|���޲z</li>
				<li class="breadcrumb-item active" aria-current="page">�|���C��</li>
				<li class="breadcrumb-item active" aria-current="page">${member_id}</li>
				<li class="breadcrumb-item active" aria-current="page">�q��d��</li>
			
			</ol>
		</nav>
		<br>
<table class="table">
	<tr>
			<th>�q��s��</th>
			<th>�q�ʮɶ�</th>
			<th>�I�ڤ覡</th>
			<th>�q����B</th>
			<th>�q�檬�A</th>
			<th>�ާ@</th>	
	</tr>
	
	<c:forEach var="order_infoVO1" items="${key}"> 
	<tr>
	<td>${order_infoVO1.order_ID}</td>
	<td><fmt:formatDate value="${order_infoVO1.order_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	<td>${order_infoVO1.pay_way}</td>
	<td>${order_infoVO1.amount}</td>
	<c:if test="${order_infoVO1.order_status==1}">
	<td>����</td>
	</c:if>
	<c:if test="${order_infoVO1.order_status==0}">
	<td>����</td>
	</c:if>
	<td>
		<FORM METHOD="post" action="<%=request.getContextPath()%>/Order_info/backOrder_DetailsSelevet">
				<input type="hidden" name="action" value="getOne_For_order">
				<input type="hidden" name="member_id" value="${order_infoVO1.member_id}">
				<input type="hidden" name="order_ID" value="${order_infoVO1.order_ID}">
				<input class="btn btn-danger"type="submit" value="�d��">
		</FORM>
	</td>
	
	</tr>
	</c:forEach> 

	</table>
		<c:if test="${empty key}">
	<img src="<%=request.getContextPath()%>/img/NoResult/NoResult.jpg">
	</c:if>
	</div>
</body>
</html>