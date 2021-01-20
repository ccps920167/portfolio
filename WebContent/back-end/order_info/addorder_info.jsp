<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="Big5"%>
<%@page import="java.sql.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order_info.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%     
Order_infoVO order_infoVO=(Order_infoVO)request.getAttribute("order_infoVO");
%>
<html>
<head>
<title>Insert title here</title>
</head>
<c:if test="${not empty error}">
		<font style="color: red"></font>
		<ul>
			<c:forEach var="message" items="${error}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
<body>
	<FORM method="post" action="<%=request.getContextPath()%>/order_info/Order_infoServlet">
		<table>
			<tr>
				<td><b>�|���s��:</b></td>
				<td><input type="TEXT" name="member_id"
				value="<%=(order_infoVO==null)?"":order_infoVO.getMember_id() %>"></td>
			</tr>
			<tr>	
				<td><b>�q��ɶ�:</b></td>
				<td><input type="TEXT" name="Order_time"
				value="<%=(order_infoVO==null)?"":order_infoVO.getOrder_time() %>"></td>
			</tr>
			<tr>
				<td><b>�I�ڮɶ�:</b></td>
				<td><input type="TEXT" name="payment_time"
				value="<%=(order_infoVO==null)?"":order_infoVO.getPayment_time() %>"></td>
			</tr>
			<tr>
				<td><b>�I�ڤ覡:</b></td>
				<td><select size="1" name="pay_way">
					<option value="�״�">�״�</option>
					<option value="�H�Υd">�H�Υd</option>
				</select></td>
			</tr>
			<tr>
				<td><b>�q�檬�A:</b></td>
				<td><input type="TEXT" name="order_status"
				value="<%=(order_infoVO==null)?"":order_infoVO.getOrder_status() %>"></td></td>
			</tr>
			<tr>
				<td><b>�u�f��s��:</b></td>
				<td><input type="TEXT" name="coupon_ID"
				value="<%=(order_infoVO==null)?"":order_infoVO.getCoupon_ID() %>"></td></td>		
			</tr>
		</table>
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="�s�W"> 
	</FORM>
</body>
</html>