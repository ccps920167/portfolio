<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="UTF-8"%>
<%@ page import="com.order_info.model.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%	Order_infoVO order_infoVO=(Order_infoVO)request.getAttribute("order_infoVO");
%>


<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
<body>
<table>
	<tr>
		<th>訂單編號</th>
		<th>會員編號</th>
		<th>訂單時間</th>
		<th>付款時間</th>
		<th>付款方式/匯款/信用卡</th>
		<th>訂單狀態</th>
		<th>優惠卷編號</th>
		
	</tr>
	<tr>
		<td><%=order_infoVO.getOrder_ID()%></td>
		<td><%=order_infoVO.getMember_id()%></td>
		<td><%=order_infoVO.getOrder_time()%></td>
		<td><%=order_infoVO.getPayment_time()%></td>
		<td><%=order_infoVO.getPay_way()%></td>
		<td><%=order_infoVO.getOrder_status()%></td>
		<td><%=order_infoVO.getCoupon_ID()%></td>
		
	
	</tr>
	
</table>
<a href="<%=request.getContextPath()%>/back-end/order_info/select-page.jsp">回上一步</a>
</body>
</html>