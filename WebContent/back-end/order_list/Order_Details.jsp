<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ page import="com.order_info.model.*" %>
<%@ page import="com.order_list.model.*" %>
<%@ page import="com.class_info.model.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%	

Order_infoVO order_infoVO=(Order_infoVO)request.getAttribute("order_infoVO");
List<Class_infoVO> list=(List <Class_infoVO>)request.getAttribute("list");
String member_id=(String)request.getAttribute("member_id");
pageContext.setAttribute("member_id",member_id);
pageContext.setAttribute("order_infoVO",order_infoVO);
%>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
<!-- css -->
<link
	href="<%=request.getContextPath()%>/css/index.css"
	rel="stylesheet" />	
<style>



.head{
border: 2px rgb(175, 175, 175) solid;
width: 750px; 
padding: 10px;
margin-left:250px;
display: block;
}
div.orderstatus{
vertical-align:top; 
margin-left:360px;
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

<body>
<div>
	<%@ include file="header-back.jsp"%>

	<div style="margin-left: 250px; margin-right: 20px; margin-top: 100px" >
		<nav aria-label="breadcrumb" style="margin-top: 30px;">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Home</a></li>
				<li class="breadcrumb-item active" aria-current="page">會員管理</li>
				<li class="breadcrumb-item active" aria-current="page">會員列表</li>
				<li class="breadcrumb-item active" aria-current="page">${member_id}</li>
				<li class="breadcrumb-item active" aria-current="page">訂單編號：${order_infoVO.order_ID}</li>
			
			</ol>
		</nav>
		<br>
<div class="row">
	<div class="head">	
		<div class="orderno">訂單編號:<%=order_infoVO.getOrder_ID()%></div>  
		<c:if test="${order_infoVO.order_status==1}">		
		<div class="orderno">訂單狀態:完成</div>
		</c:if>
		<c:if test="${order_infoVO.order_status==0}">		
		<div class="orderstatus">訂單狀態:取消</div>
		</c:if>
	</div>
	<br>
	<div class="head" style="margin-right:15px">	
	<div>
		<div class="orderno">訂單成立:<fmt:formatDate value="${order_infoVO.order_time}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
		<div class="orderno">付款時間:<fmt:formatDate value="${order_infoVO.payment_time}" pattern="yyyy-MM-dd HH:mm:ss"/></div>		
	</div>
	<div >
		<div class="orderstatus1">付款方式:<%=order_infoVO.getPay_way()%></div>
		<div class="orderstatus1">實付金額:<%=order_infoVO.getAmount()%></div>
	</div>
	</div>
	<div class="head">
	<table class="table">
	<tr>
		<th class="Class_name">課程明細</th>
		<th class="purchase_plan">購買方案</th>
		<th class=Original_price>售價</th>
		<th class="Amount">實付金額</th>
	</tr>
		<c:forEach var="class1" items="${list}"> 

	<tr>
			<th>${class1.class_name}</th> 
			<th>${purchase_plan}</th>
			<th>${class1.original_price}</th>
 			<th>
 			<c:if test="${fn:contains(purchase_plan, '早鳥')}">
 				${class1.startfund_price}
 			</c:if>
 			<c:if test="${fn:contains(purchase_plan, '原價')}">
 				${class1.original_price}
 			</c:if>
 			</th>  
	</tr>		
	</c:forEach>
	</table>
	
	<div class="orderAmout"><br><b>訂單總計:<%=order_infoVO.getAmount()%></b></div>
</div>	
</div>

</div>
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