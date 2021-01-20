<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ page import="com.order_info.model.*" %>
<%@ page import="com.order_list.model.*" %>
<%@ page import="com.class_info.model.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
<!-- css -->
<link href="<%=request.getContextPath()%>/css/header.css"
	rel="stylesheet" />
<%@ include file="header.jsp"%>
<title>TOMATO - 讓你分分鐘鐘都在學習的平台</title>

<%
Map <Order_infoVO,List<Order_listVO>>order_infoVO1= (Map<Order_infoVO,List<Order_listVO>>)request.getAttribute("order_infoVO1");
Set<Order_infoVO> key = order_infoVO1.keySet();	
pageContext.setAttribute("key", key);
String member_id=(String)session.getAttribute("member_id");
%>
<jsp:useBean id="orderSvc" scope="page" class="com.order_info.model.Order_infoService" />
<jsp:useBean id="class_infoSvc" scope="page" class="com.class_info.model.Class_infoService" />
<style>
.styled-table {
    border-collapse: collapse;
    margin: 25px 0;
    font-size: 0.9em;
    min-width: 400px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
}
.styled-table thead tr {
    background-color: cornflowerblue;
    color: #ffffff;
    text-align: left;
}
.styled-table th,
.styled-table td {
    padding: 12px 15px;
}
.styled-table tbody tr {
    border-bottom: 1px solid #dddddd;
}

.styled-table tbody tr:nth-of-type(even) {
    background-color: #f3f3f3;
}

.styled-table tbody tr.active-row {
    font-weight: bold;
    color: #009879;
}
</style>

</head>
<body>
<div class="container" >
	<!-- 標題 -->
		<div class="jumbotron jumbotron-fluid shadow-sm rounded">
			<div class="container">
				<h1>訂單紀錄</h1>
				<p></p>
			</div>
		</div>
				<!-- 內容 -->
<div id="content">
<div class="row">
	<div class="col-12" style="margin-bottom: 150px">
		<div class="accordion" id="accordionExample">
		<c:if test="${empty order_infoVO1}">
		<img src="<%=request.getContextPath()%>/img/NoResult/NoResult.jpg">
		</c:if>
			<c:forEach var="order_infoVO1" items="${key}" varStatus="loop">	
		        <div class="card">
		          <div class="card-header" id="heading${loop.index}">
						<table class="table">
						<tr>
							<th>#</th>
							<th>訂單編號</th>
							<th>訂購時間</th>
							<th>付款方式</th>
							<th>訂單狀態</th>
						</tr>
						<tr>
							<td>
								<button class="btn btn-danger text-left" type="button" data-toggle="collapse" data-target="#collapse${loop.index}" aria-expanded="false" aria-controls="collapse${loop.index}">
			             		  查看詳情
			             		</button>
		             		</td>
							<td>${order_infoVO1.order_ID}</td>
							<td><fmt:formatDate value="${order_infoVO1.order_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td>${order_infoVO1.pay_way}</td>
							<td>
								<c:if test="${order_infoVO1.order_status==0}">
								取消
								</c:if>
								<c:if test="${order_infoVO1.order_status==1}">
								完成
								</c:if>
							</td>
						</tr>
						</table>


		          </div>
		      
		          <div id="collapse${loop.index}" class="collapse" aria-labelledby="heading${loop.index}" data-parent="#accordionExample">
		            
		            
						<table class="styled-table col-12">
							<thead>
								<tr>
									<th>課程名稱</th>
									<th>&emsp;&emsp;購買方案</th>
									<th>&emsp;&emsp;售價</th>
								</tr>
							</thead>
		                	<c:forEach var="order_listVO" items="${orderSvc.memberOrderList}">		
								<%!int amount=0 ;%>
								<c:if test="${order_listVO.order_id==order_infoVO1.order_ID}">
									<tr>
										<td>${class_infoSvc.getOneClass_info(order_listVO.class_id).class_name}</td>
										<td>&emsp;&emsp;${order_listVO.purchase_plan}</td>
										<td>&emsp;&emsp;${class_infoSvc.getOneClass_info(order_listVO.class_id).startfund_price}</td>
										
									</tr>
								</c:if>
							</c:forEach>
							<tr>
							<td  colspan="2"></td>
							<td>總價:${order_infoVO1.amount - 1000}元</td>
							</tr>
						</table>
		           
		          </div>
		        </div>
	        </c:forEach> 
		</div>
	</div>
	</div>
		</div>
	</div>

		<%@ include file="footer.jsp"%>
	
	<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
		

</body>
</html>