<!-- 設定JSP編碼 -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<!-- 設定標籤庫 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 設定import資料 -->
<%@ page import="java.util.*"%>
<%@ page import="com.main_class.model.*"%>


<%
	Main_classService main_classSvc = new Main_classService();
	List<Main_classVO> list = main_classSvc.getMain_classAll();
	pageContext.setAttribute("list", list);
%>


<html>

<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
<title>listAllClass_info</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<link
	href="<%=request.getContextPath()%>/vendors/video-js/css/video-js.css"
	rel="stylesheet" />
<!-- 列表CSS -->
<link href="<%=request.getContextPath()%>/css/back-end/icon.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/back-end/public.css"
	rel="stylesheet" type="text/css" />

<script
		src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/back-end/public.js"
		language="javascript" type="text/javascript"></script>

</head>
<body>
	<%@ include file="header-back.jsp"%>

	<div style="margin-left: 250px; margin-right: 20px; margin-top: 100px" >
		<nav aria-label="breadcrumb" style="margin-top: 30px;">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Home</a></li>
				<li class="breadcrumb-item active" aria-current="page">課程管理</li>
				<li class="breadcrumb-item active" aria-current="page">課程列表</li>
			</ol>
		</nav>
		<br>
<div class="container">
		<table class="table table-striped">
			<tr>
				<th scope="col">#</th>
				<th scope="col">主課程編號</th>
				<th scope="col">主課程名稱</th>
				<th scope="col">主課程狀態</th>
				<th scope="col">查看詳情</th>
				<th scope="col">修改</th>
				<th scope="col">刪除</th>
			</tr>

			<!--迴圈取出所有物件 -->
			<c:forEach var="main_classVO" items="${list}"  varStatus="status">

				<tr>
					<th scope="row">${ status.index + 1}</th>
					<td>${main_classVO.mainClass_id}</td>
					<td>${main_classVO.mainClass_name}</td>
					<!--顯示取代 -->
					<c:if test="${fn:contains(main_classVO.mainClass_status, '1')}">
						<td>顯示</td>
					</c:if>
					<c:if test="${fn:contains(main_classVO.mainClass_status, '0')}">
						<td>隱藏</td>
					</c:if>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Main_class/Main_classServlet""
							style="margin-bottom: 0px;">
							<input class="btn btn-primary " type="submit" value="查看詳情"> 
							<input type="hidden" name="mainClass_name" value="${main_classVO.mainClass_name}">
							<input type="hidden" name="mainClass_id" value="${main_classVO.mainClass_id}">
							<input type="hidden" name="action" value="mainclass_id_sub_class">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Main_class/Main_classServlet"
							style="margin-bottom: 0px;">
							<input class="btn btn-primary " type="submit" value="修改"> <input type="hidden" name="mainClass_id" value="${main_classVO.mainClass_id}">
							<input type="hidden" name="action" value="update_mainClass_id">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Main_class/Main_classServlet""
							style="margin-bottom: 0px;">
							<input class="btn btn-primary " type="submit" value="刪除"> <input type="hidden" name="mainClass_id" value="${main_classVO.mainClass_id}">
							<input   type="hidden" name="action" value="delete">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	</div>
</body>

</html>