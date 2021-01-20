<!-- 設定JSP編碼 -->
<%@page import="com.student_homework.model.Student_homeworkVO"%>
<%@page import="com.class_chapter.model.Class_chapterVO"%>
<%@page import="com.class_unit.model.Class_unitVO"%>
<%@page import="com.class_unit.model.Class_unitService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 設定標籤庫 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.class_info.model.*"%>
<%@ page import="com.class_chapter.*"%>
<%@ page import="com.member_info.model.*"%>
<%@ page import="com.order_list.model.*"%>



<!doctype html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">


<!-- video.js CSS -->
<link
	href="<%=request.getContextPath()%>/vendors/video-js/css/video-js.css"
	rel="stylesheet" />
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">

<!-- css -->
<link
	href="<%=request.getContextPath()%>/css/front-end/class-info/classlearn.css"
	rel="stylesheet" />

<link href="<%=request.getContextPath()%>/css/header.css"
	rel="stylesheet" />

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
	
<title>TOMATO - 讓你分分鐘鐘都在學習的網站</title>

</head>

<body>

	<%@ include file="header.jsp"%>
	<!-- container -->
	<div class="container">
		<img  src = <%=request.getContextPath()%>/img/NoResult/500Page.jpg>
	</div>
	<!-- Footer -->
	<%@ include file="footer.jsp"%>



	
	<!-- JavaScript -->
	<script
		src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>

	

</body>

</html>