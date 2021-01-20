<%@page import="java.sql.Timestamp"%>
<%@page import="com.class_info.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%
	Class_infoVO class_infoVO = (Class_infoVO) request.getAttribute("Class_infoVO");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>listOneClass_info</title>

<style>
table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>
</head>
<body>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty erMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${erMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<form method="post" action="<%=request.getContextPath()%>/class_info/class_infoServlet"
		name="form1">
		<table>
		    <tr>
		        <th>課程編號</th>
		        <th>課程名稱</th>
        	    <th>會員編號</th>
		        <th>課程狀態</th>
		        <th>副課程類別編號</th>
		        <th>開始募資時間</th>
		        <th>開課時間</th>
		        <th>課程描述</th>
		        <th>課程封面圖片</th>
	        	<th>募資售價</th>
	        	<th>定價</th>
		        <th>門檻人數</th>
		        <th>課程長度</th>
        		<th>募資影片</th>
		        <th>更新時間</th>
        		<th>更新人員</th>
		    </tr>
		        <tr>
    		    <td><%=class_infoVO.getClass_id()%></td>
	    		<td><%=class_infoVO.getClass_name()%></td>
	    		<td><%=class_infoVO.getMember_id()%></td>
	    		<td><%=class_infoVO.getClass_status()%></td>
	    		<td><%=class_infoVO.getSubclass_id()%></td>
				<td><fmt:formatDate value="<%=class_infoVO.getStartfund_date()%>" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="<%=class_infoVO.getStartclass_time()%>" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><%=class_infoVO.getClass_description()%></td>
				<td><img src="data:image;base64,<%=class_infoVO.getClass_picture2()%>"></td> 
				<td><%=class_infoVO.getStartfund_price()%></td>
				<td><%=class_infoVO.getOriginal_price()%></td>
				<td><%=class_infoVO.getPeople_threshold()%></td>
				<td><%=class_infoVO.getClass_length()%></td>
				<td><img src="data:image;base64,<%=class_infoVO.getVideo_fundraising2()%>"></td>
				<td><fmt:formatDate value="<%=class_infoVO.getUpdate_time()%>" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><%=class_infoVO.getAdmin_id()%></td>
		    </tr>
		</table>
	</form>

	<h2>
		<a href="<%=request.getContextPath()%>/back-end/class_info/select_pageClass_info.jsp">回到首頁</a>
	</h2>
</body>

</html>