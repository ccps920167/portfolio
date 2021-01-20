<%@page import="com.video_record.model.Video_recordVO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.video_record.model.*" %>
<%@ page import="java.util.*" %>
<% 
Video_recordVO video_recordVO=(Video_recordVO) request.getAttribute("video_recordVO");
List<String> errorMsgs = (List) request.getAttribute("errorMsgs");
%>


<!doctype html>
<html lang="en">

<head>

	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css"
		integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
		crossorigin="anonymous">
	<link href="<%=request.getContextPath()%>/vendors/video-js/css/video-js.css"
		rel="stylesheet" />
	<style>
		/* 套用全部 */
		* {
			box-sizing: border-box;
		}

		.btn_del {
			width: 50px;
			padding: 3px;
			display: inline-block;
		}

		.form-group {
			border: 1px rgb(204, 204, 204) solid;
		}
	</style>
	<title>主課程類別 新增</title>
</head>

<body>
	<!-- container -->
	<div class="container">
		<h1>更新觀看紀錄</h1>
		<a href="<%=request.getContextPath()%>/back-end/video_record/select_page.jsp">video_record:
			Home</a> <br>
		<%-- 錯誤表列 --%>
			<%--<c:if test="條件式"> --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>

				<form method="POST"
					action="<%=request.getContextPath()%>/Video_record/Video_recordServlet">
					
					<Label for="record_id">紀錄編號</Label>
					<input name="record_id" type="text"  readonly
					value="<%= (video_recordVO==null)? "" : video_recordVO.getRecord_id()%>"> <br>
					<Label for="member_id">會員編號</Label>
					<input name="member_id" type="text" readonly
					value="<%= (video_recordVO==null)? "" : video_recordVO.getMember_id()%>"> <br>
					<!--   要改為ajex 動態選擇		 -->
					<Label for="mainClass_id">主課程類別</Label>
					<select class="mainClass_id" name=mainClass_id>
					</select>
										<br>
					<Label for="subClass_id">副課程類別</Label>
					<select class="subClass_id" name=subClass_id>
					</select>
										<br>
					<Label for="class_id">課程名稱</Label>
					<select class="class_id" name=class_id>
					</select>
										<br>
					<Label for="unit_id">課程單元編號</Label>
					<input name="unit_id" type="text" readonly
					value="<%= (video_recordVO==null)? "" : video_recordVO.getunit_id()%>"> <br>
					<Label for="class_last">最後一次觀看紀錄</Label>
					<input name="class_last" type="text" placeholder="輸入最後一次觀看紀錄"
					value="<%= (video_recordVO==null)? "" : video_recordVO.getClass_last()%>"> <br>
					
<% 
         java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>
					
					<Label for="video_updateTime">最後更新時間</Label>
					<input name="video_updateTime" type="text" " readonly
					value="<%= (video_recordVO==null)? "" : df.format(video_recordVO.getVideo_updateTime())%>"> 
					
					
					<br>
					<br>
					<input type="hidden" name="action" value="update">
					<button type="submit" class="btn btn-primary">Submit</button>
				</form>
	</div>
	<!-- JavaScript -->
	<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/video-js/js/video.js"></script>
</body>

</html>