<!-- 設定JSP編碼 -->
<%@page import="com.video_record.model.Video_recordVO"%>
<%@page import="com.video_record.model.Video_recordService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 設定標籤庫 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.class_info.model.*"%>

<%
session.removeAttribute("verifyClass_id");
session.removeAttribute("verifyClass_name");
session.removeAttribute("verifyOriginal_price");
session.removeAttribute("verifyStartfund_price");
session.removeAttribute("verifyPeople_threshold");
session.removeAttribute("verifyClass_length");
session.removeAttribute("verifySub_class");
session.removeAttribute("verifyClass_description");
%>

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
<link href="<%=request.getContextPath()%>/css/index.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/header.css"
	rel="stylesheet" />
	

	<!-- <script src=""></script> -->

<!-- <link href="https//cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css" rel="stylesheet"> -->
<title>TOMATO - 讓你分分鐘鐘都在學習的平台</title>
</head>
<style>

.page-item.active .page-link{
background-color: #DC3545;
border-color
}   

</style>
<body>
	<%@ include file="header.jsp"%>
	
	<div class="container">
	
	<!-- 標題 -->
        <div class="jumbotron jumbotron-fluid">
            <div class="container" style="text-align:center">
                <nav aria-label="...">
                    <ul class="pagination pagination-lg justify-content-center">
                        <li class="page-item"><a class="page-link" href="">1</a></li>
                        <li class="page-item"><a class="page-link" href="">2</a></li>
                        <li class="page-item"><a class="page-link" href="">3</a></li>
                        <li class="page-item"><a class="page-link" href="">4</a></li>
                        <li class="page-item"><a class="page-link" href="">5</a></li>
                        <li class="page-item active" aria-current="page">
                            <span class="page-link">
                                6
                                <span class="sr-only">(current)</span>
                            </span>
                        </li>
                    </ul>
                </nav>
                <h2>您已成功將影片上傳並送出審核</h2>
                <h2>如通過募資條件將另行向您回報!</h2>
            </div>
        </div>
		<div id="content">
            <div class="row">
                <div class="col-12">
                
		        </div>
				<a href="<%=request.getContextPath()%>/index.jsp"><button class="btn btn-danger" style="width:120px;height:40px;position: relative;left:500px;margin-bottom:18px">回到首頁</button></a>
		    </div>
		</div>
	</div>
	
	<%@ include file="footer.jsp"%>
	<!-- JavaScript -->
	<script
		src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>

    
</body>

</html>