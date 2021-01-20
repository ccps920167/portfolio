<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>江明哲首頁</title>
</head>
<body bgcolor='white'>
	<table id=table-1">
		<tr>
			<td><h3>44444</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>


	<c:if test="${not empty error}">
		<font style="color: red"></font>
		<ul>
			<c:forEach var="message" items="${error}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<ul>
		<li><a href='listAllCoupon.jsp'>List</a>查詢全部</li>
		<li>
			<FORM method="post" action="<%=request.getContextPath()%>/coupon/CouponServlet">
				<b>輸入優惠卷編號:</b> <input type="text" name="couponVO1"> <input
					type="hidden" name="action" value="getOne_For_Display">ex:COUXXXXX
				<input type="submit" value="送出">
			</FORM>
		</li>


		<jsp:useBean id="Couponn" class="com.coupon.model.CouponService" />
		<li>
			<FORM method="post" action="<%=request.getContextPath()%>/coupon/CouponServlet">
				<b>選擇優惠卷編號</b> <select size="1" name="couponVO1">
					<c:forEach var="CouponVO" items="${Couponn.all}">
						<option value="${CouponVO.coupon_id}">${CouponVO.coupon_id}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>
	<ul>
		<li><a href='addCoupon.jsp'>新增資料點這裡</a></li>
	</ul>
</body>
</html>