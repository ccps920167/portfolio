<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ page import="com.coupon.model.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<% CouponVO couponVO=(CouponVO)request.getAttribute("couponVO");%>


<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr>
		<th>�u�f���s��</th>
		<th>�u�f���N�X</th>
		<th>�u�f�����B</th>
		<th>�u�f���o�e�ɶ�</th>
		<th>�ϥΦ��Ĵ���</th>
		
	</tr>
	<tr>
		<td><%=couponVO.getCoupon_id()%></td>
		<td><%=couponVO.getCoupon_code()%></td>
		<td><%=couponVO.getCoupon_amount()%></td>
		<td><fmt:formatDate value="${couponVO.coupon_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		<td><fmt:formatDate value="${couponVO.coupon_expiry}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		
	</tr>
	
</table>
<div><a href="<%=request.getContextPath()%>/back-end/coupon/select-page.jsp">�^�W�@�B</a></div>
</body>
</html>