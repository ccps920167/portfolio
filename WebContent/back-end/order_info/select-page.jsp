<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="BIG5">
<title>購物車</title>
</head>
<body>
	<div>
		<a
			href="<%=request.getContextPath()%>/back-end/order_info/listAllorder_info.jsp">查詢全部購物</a>
		<c:if test="${not empty error}">
			<font style="color: red"></font>
			<ul>
				<c:forEach var="message" items="${error}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
	</div>
	<ul>
		<li>
			<FORM method="post"
				action="<%=request.getContextPath()%>/order_info/Order_infoServlet">
				<b>訂單編號:</b> <input type="text" name=orderinfoVO1> <input
					type="hidden" name="action" value="getOne_For_Display"> <input
					type="submit" value="送出">
		</li>
		</FORM>
	</ul>
	<ul>
		<li><a href="<%=request.getContextPath()%>/back-end/order_info/addorder_info.jsp">新增資料點這裡</a></li>
	</ul>
</body>
</html>