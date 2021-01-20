<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ page import="com.order_list.model.*" %>
<%@ page import="com.order_info.model.*" %>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 


Order_infoVO Order_info = (Order_infoVO)request.getAttribute("Order_info");
List<Order_listVO> list = (List<Order_listVO>)request.getAttribute("Order_listVO");
pageContext.setAttribute("Order_info", Order_info);
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
<style>



.head{
width: 1100px; 
padding: 10px;
margin-left:360px
}
div.orderstatus{
vertical-align:top; 
margin-left:800px;
}
div.orderno{
margin-left:20px
}
div.orderno1{
margin-left:20px
}
div.orderstatus1{
vertical-align:top; 
margin-left:50px;
}
.Class_name{
width:360px;
}
.purchase_plan{
width:100px;
}
.Original_price{
width:80px;
} 
.orderAmout{

margin-left:470px;
}
</style>

</head>
<body>
	<%@ include file="header-back.jsp"%>

	<div style="margin-left: 250px; margin-right: 20px; margin-top: 100px" >
		<nav aria-label="breadcrumb" style="margin-top: 30px;">
			<ol class="breadcrumb">
				<li class="breadcrumb-item">Home</li>
				<li class="breadcrumb-item active" aria-current="page">訂單管理</li>
				<li class="breadcrumb-item active" aria-current="page">訂單明細</li>
				<li class="breadcrumb-item active" aria-current="page">${Order_info.order_ID}</li>
			</ol>
		</nav>
		<br>
		<div class="container">
		<h1>${Order_info.order_ID} 訂單明細</h1>
		
			<div class="row head">	
		<div class="orderno">訂單編號:${Order_info.order_ID}</div>  
		<c:if test="${Order_info.order_status==1}">		
		<div class="orderstatus">訂單狀態:完成</div>
		</c:if>
		<c:if test="${Order_info.order_status==0}">		
		<div class="orderstatus">訂單狀態:取消</div>
		</c:if>
	</div>
	<div class="row head">	
	<div>
		<div class="orderno">訂單成立:<fmt:formatDate value="${Order_info.order_time}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
		<div class="orderno">付款時間:<fmt:formatDate value="${Order_info.payment_time}" pattern="yyyy-MM-dd HH:mm:ss"/></div>		
	</div>
	<div>
		<div class="orderstatus1">付款方式:${Order_info.pay_way}</div>
		<div class="orderstatus1">實付金額:${Order_info.amount}</div>
	</div>
	</div>
		<table class="table table-striped">
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
		<td>${order_list.class_id}</td>
		<td>${order_list.purchase_plan}</td>	
		
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