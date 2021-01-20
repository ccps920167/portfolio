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
<link
	href="<%=request.getContextPath()%>/vendors/video-js/css/video-js.css"
	rel="stylesheet" />
<!-- 列表CSS -->
<link href="<%=request.getContextPath()%>/css/back-end/icon.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/back-end/public.css"
	rel="stylesheet" type="text/css" />

<c:if test="${not empty Auth_listError}">
	<script>
		alert("${Auth_listError}");
	</script>
</c:if>
<% 

request.removeAttribute("Auth_listError");

%>
</head>
<body>
	<%@ include file="header-back.jsp"%>
	<div class="container"">
		<div>
		<img src="<%=request.getContextPath()%>/img/tomato-logo.png" style="width: 325px ;">
		</div>
		<div>
		<a style="display: inline-block;" href="<%=request.getContextPath()%>/forward/backSrevlet?action=back-chat">
		<button style="width: 200px ;height: 200px" type="button" class="btn btn-primary">聊天室</button>
		</a>
		<a  style="display: inline-block;" href="<%=request.getContextPath()%>/forward/backSrevlet?action=listAllMember_info">
		<button style="width: 200px ;height: 200px" type="button" class="btn btn-success">會員列表</button>
			</a>
		<a  style="display: inline-block;" href="<%=request.getContextPath()%>/forward/backSrevlet?action=back-listAllClass_info">
		<button style="width: 200px ;height: 200px" type="button" class="btn btn-danger">課程列表</button>
		</a>
		</div>
		<div style="margin: 20px">
		<a style="display: inline-block;" href="<%=request.getContextPath()%>/back-end/post_message/BEpostContext.jsp">
		<button style="width: 200px ;height: 200px" type="button" class="btn btn-warning">公告管理</button>
		</a>
		<a  style="display: inline-block;" href="<%=request.getContextPath()%>/forward/backSrevlet?action=listSell_info">
		<button style="width: 200px ;height: 200px" type="button" class="btn btn-info">銷售分析</button>
		</a>
		<a  style="display: inline-block;" href="<%=request.getContextPath()%>/forward/backSrevlet?action=back-Keyword_formAll">
		<button style="width: 200px ;height: 200px" type="button" class="btn btn-secondary">廣告與關鍵字</button>
		</a>
		</div>
	
	</div>
		<!---------------------------------------- script ---------------------------------------->

<!-- jquery-->
<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>

<!-- popper-->
<script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>

<!-- bootstrap -->
<script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>

<!-- jquery.datetimepicker -->
<script src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.full.js"></script>

<!-- public.js-->
<script src="<%=request.getContextPath()%>/js/back-end/public.js"></script>

<!---------------------------------------- script ---------------------------------------->
</body>
</html>