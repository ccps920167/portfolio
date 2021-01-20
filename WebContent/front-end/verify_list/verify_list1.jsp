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
	Class_infoVO class_infoVO = (Class_infoVO) request.getAttribute("Class_infoVO");
%>

<%
	Class_infoService Srv = new Class_infoService();
	List<Class_infoVO> list = Srv.get_ROWNUM_8();
	pageContext.setAttribute("list", list);
%>

<!doctype html>
<html lang="en">

<head>
<style>
#content {
            margin-top: 10px;
            margin-bottom: 50px;
            border: 1px rgb(204, 204, 204) solid;
        }
        
div.aaa{
text-align:center;
}
div.bbb{
border: 2px rgb(0, 0, 0) solid;
text-align:center;
padding: 10px;
margin-right:400px;
margin-left:400px;
background-color:rgb(230, 230, 230);
}

div.ccc{
width:120px;

}        

.page-item.active .page-link{
background-color: #DC3545;
border-color: #DC3545
}   
    
        
</style>
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

<link href="https//cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css" rel="stylesheet">
<title>TOMATO - 讓你分分鐘鐘都在學習的平台</title>
</head>

<body>
	<%@ include file="header.jsp"%>
	
	<div class="container">
	
	<!-- 標題 -->
        <div class="jumbotron jumbotron-fluid">
            <div class="container" style="text-align:center">
                <nav aria-label="...">
                    <ul class="pagination pagination-lg justify-content-center">
                        <li class="page-item active" aria-current="page">
                            <span class="page-link">
                                1
                                <span class="sr-only">(current)</span>
                            </span>
                        </li>
                        <li class="page-item"><a class="page-link" href="">2</a></li>
                        <li class="page-item"><a class="page-link" href="">3</a></li>
                        <li class="page-item"><a class="page-link" href="">4</a></li>
                        <li class="page-item"><a class="page-link" href="">5</a></li>
                        <li class="page-item"><a class="page-link" href="">6</a></li>
                    </ul>
                </nav>
                <h1>歡迎加入Tomato好老師行列</h1>
                <h4>跟著以下說明一步一步完成提案!</h4>
            </div>
        </div>

        <form method="post" action="<%=request.getContextPath()%>/class_info/class_infoServlet"
		name="form1" enctype="multipart/form-data">
		<div id="content">
            <div class="row">
                <div class="col-12">
		            <div class="aaa"><span style="font-size:22px;">接下來我們會一步步的開始編輯課程，主要的內容包含了</span></div><br>		        
		            <div class="bbb">
		            	<div><b>課程標題</b> - 好的標題是成功的一半</div>
		            	<div><b>課程目標</b> - 哪些學生適合這堂課？</div>
		            	<div><b>預計單元</b> - 課程會涵蓋哪些內容？</div>
		            	<div><b>募資條件</b> - 課程的價格及人數門檻</div>
		            	<div style="padding-right:48px"><b>詳細內容</b> - 簡單的課程介紹</div>
		            	<div style="padding-right:48px"><b>影片上傳 </b>- 3 分鐘募資影片</div>
		            </div><br>
		            <div class="aaa"><span style="font-size:22px;">編輯中若有任何問題，都可以透過右下角的「客服系統」詢問</span></div><br>
		            <input class="btn btn-danger" style="width:120px;height:40px;position: relative;left:500px;margin-bottom:18px" type="button" value="開始編輯" onclick="location.href='<%=request.getContextPath()%>/front-end/verify_list/verify_list2.jsp'">
		        </div>
	     	</div>
		</div>
	</form>
	</div>

	
	
	<%@ include file="footer.jsp"%>
	
	<!-- JavaScript -->
		<script
			src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
		<script>


</body>

</html>