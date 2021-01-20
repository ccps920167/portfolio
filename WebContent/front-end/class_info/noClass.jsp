<!-- 設定JSP編碼 -->
<%@page import="com.class_chapter.model.Class_chapterVO"%>
<%@page import="com.class_unit.model.Class_unitVO"%>
<%@page import="com.class_unit.model.Class_unitService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 設定標籤庫 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>

<!doctype html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
<!-- video.js CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/header.css">
<link
	href="<%=request.getContextPath()%>/vendors/video-js/css/video-js.css"
	rel="stylesheet" />
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
<!-- css -->
<link
	href="<%=request.getContextPath()%>/css/front-end/class-info/classOnly.css"
	rel="stylesheet" />

<link href="<%=request.getContextPath()%>/css/header.css"
	rel="stylesheet" />
<title>TOMATO - 讓你分分鐘鐘都在學習的網站</title>

</head>

<body>
	<%@ include file="header.jsp"%>
	
	<div class="container" style="margin-bottom: 100px">
		<img src="<%=request.getContextPath()%>/img/NoResult/noClass.jpg">
		<a href="<%=request.getContextPath()%>/index.jsp">回到首頁</a>
	</div>
	<!-- Footer -->
	<%@ include file="footer.jsp"%>

	<!-- JavaScript -->
	<script
		src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/jquery/jquery-timers.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/video-js/js/video.js"></script>

</body>

</html>