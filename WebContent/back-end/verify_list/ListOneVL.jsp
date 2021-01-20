<%@page import="com.verify_list.model.*"%>
<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
Verify_listVO verify_listVO = (Verify_listVO) request.getAttribute("verify_listVO"); 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>ListOneVL</title>

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

<table>
	<tr>
		<th>審核編號</th>
		<th>課程編號</th>
		<th>審核人員</th>
		<th>審核紀錄</th>
		<th>上傳時間</th>

	</tr>
	<tr>
		<td><%=verify_listVO.getVerify_id()%></td>
		<td><%=verify_listVO.getClass_id()%></td>
		<td><%=verify_listVO.getAdmin_id()%></td>
		<td><%=verify_listVO.getClass_check()%></td>
		<td><%=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(verify_listVO.getUpload_time())%></td>

	</tr>
	</table>

	<h2>
		<a href="/TEA102G5/back-end/verify_list/Select_pageVL.jsp">回到首頁</a>
	</h2>
</body>

</body>
</html>