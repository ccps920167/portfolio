<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coupon.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	CouponService Cou = new CouponService();
	List<CouponVO> list = Cou.getAll();
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
			<th>優惠卷編號</th>
			<th>優惠卷代碼</th>
			<th>優惠卷金額</th>
			<th>優惠卷發送時間</th>
			<th>使用有效期限</th>
		</tr>

		<c:forEach var="coupon" items="${list}">
			<tr>
				<td>${coupon.coupon_id}</td>
				<td>${coupon.coupon_code}</td>
				<td>${coupon.coupon_amount}</td>
				<td><fmt:formatDate value="${coupon.coupon_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${coupon.coupon_expiry}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/coupon/CouponServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="coupon"  value="${coupon.coupon_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			  </FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/coupon/CouponServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="coupon"  value="${coupon.coupon_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
			</tr>
		</c:forEach>

	</table>
	<br>
	<div><a href="<%=request.getContextPath()%>/back-end/coupon/select-page.jsp">回上一步</a></div>
</body>
</html>