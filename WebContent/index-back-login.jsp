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
<%@ page import="com.class_info.model.*"%>
<%@ page import="com.class_chapter.*"%>


<!doctype html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">

<title>TOMATO後台管理平台</title>


<style type="text/css">
html, body {
	height: 100%;
}

.sign_in {
	display: -ms-flexbox;
	display: flex;
	-ms-flex-align: center;
	align-items: center;
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.form-signin {
	width: 100%;
	max-width: 330px;
	padding: 15px;
	margin: auto;
}

.form-signin .checkbox {
	font-weight: 400;
}

.form-signin .form-control {
	position: relative;
	box-sizing: border-box;
	height: auto;
	padding: 10px;
	font-size: 16px;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="email"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}
</style>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">


</head>
<body>



	<div class="container">
	<div class="container sign_in" style="background-color: white;">
		<!-- 內容 -->
		<form class="form-signin" action="<%=request.getContextPath()%>/admin_list/AdminLoginServlet" method="post">
			<img src="<%=request.getContextPath()%>/img/tomato-logo.png" style="width: 325px ;margin: 0%"">
			<br>
			<h6 class=" font-weight-normal">管理員帳號</h6>
			<input
				name="adminAccount" type="text" id="inputEmail" class="form-control"
				placeholder="輸入管理員帳號" required autofocus style="margin: 10px"> 
			<h6 class=" font-weight-normal">管理員密碼</h6>
			<input
				name="adminPassword" type="password" id="inputPassword" class="form-control"
				placeholder="輸入管理員密碼" required style="margin: 10px">	
		
			<button class="btn btn-lg btn-danger btn-block" type="submit" style="margin: 10px">登入</button>
			<input type="hidden" name="action" value="adminlogin" style="margin: 10px"/><br>


		</form>
	</div></div>

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

		<!---------------------------------------- script ---------------------------------------->

</body>
</html>