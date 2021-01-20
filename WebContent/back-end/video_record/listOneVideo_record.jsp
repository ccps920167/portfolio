<!-- 設定JSP編碼 -->
<%@page import="com.video_record.model.Video_recordVO"%>
<%@page import="com.video_record.model.Video_recordService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<!-- 設定標籤庫 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 設定import資料 -->
<%@ page import="java.util.*"%>
<%@ page import="com.main_class.model.*"%>


<%
	Video_recordVO video_recordVO = new Video_recordVO();
	List<Video_recordVO> list = (List) request.getAttribute("video_recordVO_list");
	pageContext.setAttribute("list", list);
%>


<html>

<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">

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
</style>
<title>查詢使用者觀看紀錄結果 - listAll.jsp</title>
</head>

<body bgcolor='white'>
	<div class="container">
		<h1>查詢使用者觀看紀錄結果</h1>
		<a
			href="<%=request.getContextPath()%>/back-end/video_record/select_page.jsp">video_record:
			Home</a> <br>

		<table class="table table-striped">
			<tr>
				<th scope="col">#</th>
				<th scope="col">瀏覽紀錄編號</th>
				<th scope="col">會員編號</th>
				<th scope="col">課程主類別</th>
				<th scope="col">課程副類別</th>
				<th scope="col">課程名稱</th>
				<th scope="col">課程章節</th>
				<th scope="col">課程單元</th>
				<th scope="col">最後一次觀看紀錄</th>
				<th scope="col">瀏覽紀錄更新時間</th>
				<th scope="col">修改</th>
				<th scope="col">刪除</th>
			</tr>

			<!--分頁 -->
			<%@ include file="page1.file"%>

			<!--迴圈取出所有物件 -->
			<c:forEach var="video_recordVO" items="${list}"
				begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"
				varStatus="status">
				<tr>
					<th scope="row">${ status.index + 1}</th>
					<!-- 瀏覽紀錄編號-->
					<td>${video_recordVO.record_id}</td>
					<!-- 會員編號 -->
					<td>${video_recordVO.member_id}</td>
					<!-- 課程主類別-->
					<td>待整合</td>
					<!-- 課程副類別 -->
					<td>待整合</td>
					<!-- 課程名稱-->
					<td>待整合</td>
					<!-- 課程章節-->
					<td>待整合</td>
					<!-- 課程單元-->
					<td>${video_recordVO.unit_id}</td>
					<!-- 最後一次觀看紀錄-->
					<td>${video_recordVO.class_last}</td>
					<!-- 瀏覽紀錄更新時間-->
					<td><fmt:formatDate
							value="${video_recordVO.video_updateTime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>

					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/Video_record/Video_recordServlet"
							style="margin-bottom: 0px;">
							<input class="btn btn-primary " type="submit" value="修改">
							<input type="hidden" name="record_id"
								value="${video_recordVO.record_id}"> <input
								type="hidden" name="action" value="update_record_id">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/Video_record/Video_recordServlet"
							"
							style="margin-bottom: 0px;">
							<input class="btn btn-primary " type="submit" value="刪除">
							<input type="hidden" name="record_id"
								value="${video_recordVO.record_id}"> <input
								type="hidden" name="action" value="delete">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>

		<%@ include file="page2.file"%>
	</div>
</body>

</html>