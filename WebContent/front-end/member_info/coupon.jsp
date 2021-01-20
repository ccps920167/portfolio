<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coupon.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	List<String> coupon=(List<String>)request.getAttribute("coupon");
	CouponService Cou = new CouponService();
	List<CouponVO> list = Cou.getAll();
	CouponVO[] sixList = new CouponVO[6];
	for(CouponVO couponVO:list){
		if(sixList[0]==null){
			sixList[0]=couponVO;
		}else if(sixList[1]==null){
			sixList[1]=couponVO;
		}else if(sixList[2]==null){
			sixList[2]=couponVO;
		}else if(sixList[3]==null){
			sixList[3]=couponVO;
		}else if(sixList[4]==null){
			sixList[4]=couponVO;
		}else if(sixList[5]==null){
			sixList[5]=couponVO;
		}else {}		
	}
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("sixList", sixList);
%>
<html lang="en">

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

/* header  */
header {
	width: 100%;
	box-shadow: 0px 1px 10px rgb(202, 202, 197);
	z-index: 9999;
	position: sticky;
	top: 0;
}

#talk {
	position: fixed;
	right: 20px;
	bottom: 30px;
	height: 60px;
}

/* 內部框 */
#content {
	margin-top: 10px;
	margin-bottom: 50px;
	border: 1px rgb(204, 204, 204) solid;
}

.check {
	border: 2px solid black;
	padding: 10px;
	transform: translateX(0%);
}
.card-img-top{
flex-shrink: 0;
width: 100%;
}
</style>
<title>TOMATO - 讓你分分鐘鐘都在學習的平台</title>
</head>

<body>
	<!-- header -->
	<div>
		<div class="col-2"></div>
		<div class="col-10"></div>
	</div>
<c:forEach var="couponVO" items="${sixList}">
<%
	CouponVO couponVO = (CouponVO) pageContext.getAttribute("couponVO");
%>

</c:forEach>

<%@ include file="header.jsp"%>
	<!-- container -->

	<div class="container">
		<!-- 標題 -->
		<div class="jumbotron jumbotron-fluid shadow-sm rounded">
			<div class="container">
				<h1>優惠卷</h1>
			</div>
		</div>
		<!-- 內容 -->
		<div id="content">
			<div class="row">
				
				<div class="col-12" >
					<div class="card-deck">
						<div class="col-12 row">
						<c:forEach var="couponVO" items="${sixList}" begin="0" end="6">
						<%	CouponVO couponVO = (CouponVO) pageContext.getAttribute("couponVO");%>
								<div class="col-4">
									<div class="card">
										<img class="card-img-top"
											src="<%=request.getContextPath()%>/img/icon/coupon2.png" class="card-img-top" alt="Card image cap">
										<div class="card-body">
											<div class="alert  shadow text-white " role="alert"
											style="margin: 10px; background-color: tomato;">
											<b>折扣金額:${couponVO.coupon_amount}</b>
											</div>
											<div class="alert  shadow " role="alert"
											style="margin: 10px; background-color: white;">
											<p><input type="hidden" name="coupon_code" value="${couponVO.coupon_code}">優惠卷代碼:${couponVO.coupon_code}</p>
											<p>有效期限:<fmt:formatDate value="${couponVO.coupon_expiry}" pattern="yyyy-MM-dd"/></p>
											</div>
										</div>
										<c:if test="${not empty coupon}">
										<div class="card-footer">
											<small class="text-muted"><a href="<%=request.getContextPath()%>/coupon/VoucherServlet?action=coupon&coupon_amount=${couponVO.coupon_amount}">使用此優惠卷</a></small>
										</div>
										</c:if>
									</div>	
								</div>
						</c:forEach>
						</div>
					</div>
				</div>
				<%list.remove(0);%>
			</div>
		</div>
	</div>
	<!-- Footer -->
	<%@ include file="footer.jsp"%>

	<!-- JavaScript -->
	<script
		src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/video-js/js/video.js"></script>


</body>

</html>