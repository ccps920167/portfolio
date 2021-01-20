<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.post_message.model.*"%>

<%
	Post_messageService post_messageSvc = new Post_messageService();
	List<Post_messageVO> list = post_messageSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html>
<head>

<title>公告訊息</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 800px;
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
	<%@ include file="header-back.jsp"%>
	<div style="margin-left: 250px; margin-right: 20px;">
		<nav aria-label="breadcrumb" style="margin-top: 30px;">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Home</a></li>
				<li class="breadcrumb-item active" aria-current="page">課程管理</li>
				<li class="breadcrumb-item active" aria-current="page">課程列表</li>
			</ol>
		</nav>
		<br>
		<div class="container">
			<h1>所有公告列表</h1>
			<a href='<%=request.getContextPath()%>/back-end/post_message/addPostMessage.jsp'>
				增加公告訊息
			</a> 
			<table class="table table-striped">
				<tr>
					<th>公告編號</th>
					<th>公告內容</th>
					<th>公告時間</th>
					<th>公告人員</th>
					<th>公告對象身份</th>
				</tr>
				<%@ include file="page1.file"%>
				<c:forEach var="post_messageVO" items="${list}"
					begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr>
						<td>${post_messageVO.post_id}</td>
						<td>${post_messageVO.post_content}</td>
						<td><fmt:formatDate value="${post_messageVO.send_time}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>${post_messageVO.admin_id}</td>
						<td>${post_messageVO.target_typeword}</td>
					</tr>
				</c:forEach>
			</table>
			<%@ include file="page2.file"%>
		</div>
	</div>
	<!---------------------------------------- script ---------------------------------------->

	<!-- jquery-->
	<script
		src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>

	<!-- popper-->
	<script
		src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>

	<!-- bootstrap -->
	<script
		src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>

	<!-- jquery.datetimepicker -->
	<script
		src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.full.js"></script>

	<!-- public.js-->
	<script src="<%=request.getContextPath()%>/js/back-end/public.js"></script>

	<!---------------------------------------- script ---------------------------------------->


</body>
</html>