<%@page import="com.sub_class.model.Sub_classVO"%>
<%@page import="com.class_unit.model.Class_unitVO" %>
<%@page import="com.class_unit.model.Class_unitService" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="java.util.*" %>
<%@page import="com.class_info.model.*" %>

<% 
String amount=(String)session.getAttribute("amount");
%>

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
<style>
/* 套用全部 */
* {
	box-sizing: border-box;
}



ul.car {
	background-color: rgb(235, 235, 235);
	background-clip: content-box;
	/* border:  solid black; */
}

.total-amount {
	display: inline-block;
	padding: 0;
	right: 80px;
	bottom: 130px;
	border: 2px rgb(175, 175, 175) solid;
	position: fixed;
	background-color: white;
	width: 200px;
	height: 200px;
}

ul.car li.item_detail {
	display: inline;
	margin-left: 10px;
}

ul.car li.price {
	padding-left: 50%;
	display: inline;
}

ul.car li.count {
	padding-left: 12%;
	display: inline;

	/* width: 30%; */
}

ul.car li.amount {
	display: inline;
	/* margin-left: 50px; */
	margin-right: 20px;
	position: absolute;
	right: 0px;
}

div.container h1 img {
	transform: translateY(-5px);
}

ul.dcc {
	display: flex;
	background: blanchedalmond;
	padding: 0;
	border: 2px solid black;
	padding: 20px;
}

ul.dcc img {
	max-width: 100%;
}

div.price1 {
	display: inline-block;
	/* right: 400%; */
	margin-right: 30px;
	width: 400px;
}

div.count1 {
	display: inline-block;
	color: rebeccapurple;
	margin-right: 110px;
	width: 50px;
	/* margin: 10px 70px; */
}

div.amount1 {
	display: inline-block;
	color: rebeccapurple;
	margin-right: 80px;
	width: 50px;
	/* margin: 10px 20px; */
}

div.amount2 {
	display: inline-block;
	color: rebeccapurple;
	width: 50px;
	/* margin: 10px 50px; */
}

div.pic-class {
	display: inline-block;
}

li.price1, li.count1, li.amount1, li.amount2, li.pic-class {
	display: inline-block;
	margin: 0 auto 10px auto;
}

.a {
	text-align: center;
	margin-top: 20px;
	margin-bottom: 20px;
}

.b {
	text-align: center;
}

.c {
	margin-left: 60px;
}

.table {
	background-color: #e9ecef;
	padding: 10px;
}

div.frame {
	border: rgb(65, 65, 64);
}

.table td {
	vertical-align: middle;
}
.image {
width:120px;
height:80px;
}



</style>
<!-- css -->
<link
	href="<%=request.getContextPath()%>/css/header.css"
	rel="stylesheet" />		
<title>TOMATO - 讓你分分鐘鐘都在學習的平台</title>
</head>

<body>
	<!-- header -->
	<%@ include file="header.jsp"%>

	<!-- container -->

	<div class="container">
		<!-- 標題 -->
		<div class="jumbotron jumbotron-fluid">
			<div class="container">
				<h1>
					<img src="<%=request.getContextPath()%>/img/icon/car.png" alt="XX"
						style="height: 30px; margin-right: 20px;">購物車
				</h1>
			</div>
		</div>
		<!-- 內容 -->
		<div id="content"></div>
			<div class="row"></div>
				<div class="col-9"></div>
					<table style="text-align: center">
							<table style="height: 50" class="table">
								<td WIDTH=300px>課程名稱</td>
								<td WIDTH=200px></td>
								<td WIDTH=400px>作業課程狀態</td>
								<td width=150px><div align="center">定價</td>
								<td WIDTH=250px>付款金額</td>
								<td WIDTH=250px></td>
							</thead>
					</table>
					<img style="width: 400px;" src="<%=request.getContextPath()%>/img/NoResult/CarNoItem.jpg">

				</div>
				<div class="col-3">

					<div class="total-amount">
						<div class="a">訂單明細</div>
						<div class="b"><b>小計$:<%=amount%></b></div>
						<br>
						<a href="<%=request.getContextPath()%>/front-end/order_info/Checkout.jsp" class="c  btn btn-danger">來去結帳</a>
<!-- 						<input type="submit" class="c  btn btn-danger" value="來去結帳"> -->
					</div>
				</div>


			</div>
		</div>

	<!--  聊天室-->
	<img id="talk"
				src="<%=request.getContextPath()%>/img/icon/chat.png">
	</div>
	<!-- Footer -->
	<%@ include file="footer.jsp"%>

	<!-- JavaScript -->
	<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
<!-- 	<script src="./購物車.js"></script> -->
	
	
	
	
	<c:forEach var="class_infoVO" items="${shoppingcart}" varStatus="loop">
	
	
	</c:forEach>
	
	
	<script>
	
	var status1=document.getElementById("status0").innerText;
	
	if(status1==0){
      document.getElementById("status0").textContent="待審核"
    }
    if(status1==1){
      document.getElementById("status0").textContent="募資中"
    }
    if(status1==2){
      document.getElementById("status0").textContent="待審核"
    }
    if(status1==3){
      document.getElementById("status0").textContent="準備開課"
    }
    if(status1==4){
      document.getElementById("status0").textContent="已開課"
    }
    if(status1==5){
      document.getElementById("status0").textContent="已下架"
    }
    if(status1==6){
      document.getElementById("status0").textContent="退件"
    }
    
	var status2=document.getElementById("status1").innerText;
	
	if(status2==0){
      document.getElementById("status1").textContent="待審核"
    }
    if(status2==1){
      document.getElementById("status1").textContent="募資中"
    }
    if(status2==2){
      document.getElementById("status1").textContent="待審核"
    }
    if(status2==3){
      document.getElementById("status1").textContent="準備開課"
    }
    if(status2==4){
      document.getElementById("status1").textContent="已開課"
    }
    if(status2==5){
      document.getElementById("status1").textContent="已下架"
    }
    if(status2==6){
      document.getElementById("status1").textContent="退件"
    }
	</script>
    
</body>

</html>