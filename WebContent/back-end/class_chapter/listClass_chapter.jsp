<!-- 設定JSP編碼 -->
<%@page import="com.class_chapter.model.Class_chapterVO"%>
<%@page import="com.class_chapter.model.Class_chapterService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<!-- 設定標籤庫 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- 設定import資料 -->
<%@ page import="java.util.*"%>
<%@ page import="com.class_chapter.*"%>


<%
	Class_chapterService class_classSvc = new Class_chapterService();
	List<Class_chapterVO> list = class_classSvc.getAll();
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
<title>所有章節 - listAll.jsp</title>
</head>

<body bgcolor='white'>
	<div class="container">
		<h1>所有章節</h1>
	<a href="<%=request.getContextPath()%>/back-end/class_chapter/select_page.jsp">class_chapter: Home</a>
		<br>
		<!--如果req屬性內有存在errorMsgs，引進屬性內資料 -->
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
		<!-- ========================錯誤列表結束============================= -->


		<table class="table table-striped">
			<tr>
				<th scope="col">#</th>
				<th scope="col">章節編號</th>
				<th scope="col">章節名稱</th>
				<th scope="col">所屬課程</th>
				<th scope="col">查看詳情</th>
				<th scope="col">修改</th>
				<th scope="col">刪除</th>
			</tr>

			<!--分頁 -->
			<%@ include file="page1.file"%>

			<!--迴圈取出所有物件 -->
			<c:forEach var="class_chapterVO" items="${list}"
				begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"
				varStatus="status">

				<tr>
					<th scope="row">${ status.index + 1}</th>
					<td>${class_chapterVO.chapter_id}</td>
					<td>${class_chapterVO.chapter_name}</td>
					<td>${class_chapterVO.class_id}</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/Class_chapter/Class_chapterServlet"
							"
							style="margin-bottom: 0px;">
							<input class="btn btn-primary " type="submit" value="查看詳情">
							<input type="hidden" name="chapter_name"
								value="${class_chapterVO.chapter_name}"> <input
								type="hidden" name="class_id"
								value="${class_chapterVO.chapter_id}"> <input
								type="hidden" name="action" value="mainclass_id_sub_class">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/Class_chapter/Class_chapterServlet"
							style="margin-bottom: 0px;">
							<input class="btn btn-primary " type="submit" value="修改">
							<input type="hidden" name="chapter_id"
								value="${class_chapterVO.chapter_id}"> <input
								type="hidden" name="action" value="update_chapter_id">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/Class_chapter/Class_chapterServlet"
							"
							style="margin-bottom: 0px;">
							<input class="btn btn-primary " type="submit" value="刪除">
							<input type="hidden" name="chapter_id"
								value="${class_chapterVO.chapter_id}"> <input
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