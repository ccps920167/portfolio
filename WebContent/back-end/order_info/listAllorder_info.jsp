<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ page import="com.order_info.model.*" %>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
Order_infoService Order= new Order_infoService();
List<Order_infoVO> list=Order.getAll();
pageContext.setAttribute("list", list);
%>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>

<!---------------------------------------- css ---------------------------------------->

<!-- jquery.datetimepicker -->	
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.css" />
	
		
<!-- jquery.datetimepicker -->		
<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>
<!---------------------------------------- css ---------------------------------------->

</head>
<body>
	<%@ include file="header-back.jsp"%>

	<div style="margin-left: 250px; margin-right: 20px; margin-top: 100px" >
		<nav aria-label="breadcrumb" style="margin-top: 30px;">
			<ol class="breadcrumb">
				<li class="breadcrumb-item">Home</li>
				<li class="breadcrumb-item active" aria-current="page">訂單管理</li>
			</ol>
		</nav>
		<br>
		<div class="container">
		<h1>訂單管理</h1>
		<table class="table table-striped">

		<tr>
			<th>訂單編號</th>
			<th>會員編號</th>
			<th>訂單時間</th>
			<th>結帳時間</th>
			<th>付款方式匯款/信用卡</th>
			<th>訂單狀態</th>
			<th>訂單詳情</th>
		</tr>
		<c:forEach var="order_info" items="${list}">
		<tr>
		<td>${order_info.order_ID}</td>
		<td>${order_info.member_id}</td>
		<td><fmt:formatDate value="${order_info.order_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		<td><fmt:formatDate value="${order_info.payment_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		<td>${order_info.pay_way}</td>
		<c:if test="${order_info.order_status==1}">		
		<td>完成</td>
		</c:if>
		<c:if test="${order_info.order_status==0}">		
		<td>取消</td>
		</c:if>

		
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forward/backSrevlet" style="margin-bottom: 0px;">
			     <input class="btn btn-danger" type="submit" value="查看">
			     <input type="hidden" name="action"  value="listOneOrder_list">
			     <input type="hidden" name="order_id"  value="${order_info.order_ID}">
			  </FORM>
			</td>
		
		</tr>
		
		</c:forEach>
	</table>
	<!---------------------------------------- script ---------------------------------------->

<!-- jquery-->
<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>

<!-- popper-->
<script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>

<!-- bootstrap -->
<script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>

<!-- jquery.datetimepicker -->
<script src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.full.js"></script>

<!-- public.js-->
<script src="<%=request.getContextPath()%>/js/back-end/public.js"></script>

<!---------------------------------------- script ---------------------------------------->
	
</body>
</html>