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


<%

	List<Class_chapterVO> Class_chapterVO_list = (List)request.getAttribute("class_infoVO");
	pageContext.setAttribute("Class_chapterVO_list", Class_chapterVO_list);

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

<link
	href="<%=request.getContextPath()%>/css/header.css"
	rel="stylesheet" />		
<title>TOMATO - 讓你分分鐘鐘都在學習的網站</title>
</head>

<body>
	<%@ include file="header.jsp"%>
	<!-- container -->
	<!-- 標題 -->
	<div class="jumbotron jumbotron-fluid" style="padding-bottom: 0;">
<!-- 	分類標題名稱 -->
		<div class="container">
			<h1>查詢結果</h1>
		</div>
	</div>
<!-- 課程卡 -->
	<div class="container">

		<div class="row">
			<div>
				<div class="col-12" style="margin-top: 20px;">
					<div class="row col-12">
						<c:forEach var="class_infoVO" items="${Class_chapterVO_list}" varStatus="status">
							<div class="card col-3"  style="flex: 100%; margin-right: 15px; margin-bottom: 0; margin-left: 15px; padding: 0%;" >
								<a
									href="<%=request.getContextPath()%>/Class_info/Class_learnServlet?action=class_Introduction&class_id=${class_infoVO.class_id}">

									<img class="card-img-top"
									src="<%=request.getContextPath()%>/Class_info/Class_Introduction?action=class_pic_sm&class_id=${class_infoVO.class_id}"
									alt="Card image cap">
									<div class="card-body">
										<h6 class="card-title" style="height: 30px">${class_infoVO.class_name}</h6>
										<p class="card-text">
											<small class="text-muted">
												 授課老師：<a
        href="<%=request.getContextPath()%>/guest/guestMember_Servlet?action=member_infoPage&member_id=${class_infoVO.member_id}">
         ${class_infoVO.member_id}</a><br>
        課程售價：${class_infoVO.original_price}<br>
        課程時數：${class_infoVO.class_length}<br>
        開課時間：<fmt:formatDate value="${class_infoVO.startfund_date}" pattern="yyyy-MM-dd HH:mm:ss"/><br>
											</small>
										</p>
									</div>
								</a>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		
	</div>
			<!--  聊天室-->
		<img id="talk"
				src="<%=request.getContextPath()%>/img/icon/chat.png">



		</div>
	<%@ include file="footer.jsp"%>



		<!-- JavaScript -->
		<script
			src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/vendors/video-js/js/video.js"></script>
		<script>
		
			//按下列表按鈕時，轉換影片路徑
				$("div#accordion").on("click", "button.class-btn", function () {
					var url_video = $(this).attr("data-unitID");
					$("#video_url").closest("video").attr("src", "<%=request.getContextPath()%>/Class_unit/Class_unitServlet?unit_id=" + url_video + "&action=many_video");
					$("#video_url").attr("src", "<%=request.getContextPath()%>/Class_unit/Class_unitServlet?unit_id="+ url_video + "&action=many_video");
					});

			//離開時抓影片時間點
			window.document.body.onbeforeunload = function() {
				var myPlayer = videojs('my-video');
				var whereYouAt = myPlayer.currentTime();
				var data_updata = {
					"action" : "getNewTime",
					"record_id" : "",
					"member_id" : "MEM00001",
					"unit_id" : "UNI00001",
					"class_last" : whereYouAt
				};
				var that = $(this);
				$.ajax({
					url : "/TEA102G5/Video_record/Video_recordServlet", // 資料請求的網址
					type : "GET", // GET | POST | PUT | DELETE | PATCH
					data : data_updata, // 傳送資料到指定的 url
				});
			}
		</script>
</body>

</html>