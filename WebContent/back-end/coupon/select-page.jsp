<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>����������</title>
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
		<li><a href='listAllCoupon.jsp'>List</a>�d�ߥ���</li>
		<li>
			<FORM method="post" action="<%=request.getContextPath()%>/coupon/CouponServlet">
				<b>��J�u�f���s��:</b> <input type="text" name="couponVO1"> <input
					type="hidden" name="action" value="getOne_For_Display">ex:COUXXXXX
				<input type="submit" value="�e�X">
			</FORM>
		</li>


		<jsp:useBean id="Couponn" class="com.coupon.model.CouponService" />
		<li>
			<FORM method="post" action="<%=request.getContextPath()%>/coupon/CouponServlet">
				<b>����u�f���s��</b> <select size="1" name="couponVO1">
					<c:forEach var="CouponVO" items="${Couponn.all}">
						<option value="${CouponVO.coupon_id}">${CouponVO.coupon_id}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="�e�X">
			</FORM>
		</li>
	</ul>
	<ul>
		<li><a href='addCoupon.jsp'>�s�W����I�o��</a></li>
	</ul>
</body>
</html>