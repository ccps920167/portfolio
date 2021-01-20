<%@page import="com.class_chapter.model.Class_chapterVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.class_chapter.*"%>

<%
	Class_chapterVO class_chapterVO = (Class_chapterVO) request.getAttribute("class_chapterVO");
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

.btn_del {
	width: 50px;
	padding: 3px;
	display: inline-block;
}

.form-group {
	border: 1px rgb(204, 204, 204) solid;
}
</style>
<title>主課程資料修改 - update_Main_Class_input.jsp</title>

</head>
<body>
	<!-- container -->

	<div class="container">
		<h1>
			修改 【
			<%=class_chapterVO.getChapter_name()%>
			】課程類別
		</h1>
	<a href="<%=request.getContextPath()%>/back-end/class_chapter/select_page.jsp">class_chapter: Home</a>
		<br>
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

		<form method="POST" action="<%=request.getContextPath()%>/Class_chapter/Class_chapterServlet">
			<table>
				<tr>
					<td>章節編號:</td>
					<td><input class="form-control" name="chapter_id"
						value="<%=class_chapterVO.getChapter_id()%>" type="text"
						placeholder="Readonly input here…" readonly></td>
				</tr>
				<tr>
					<td>章節名稱:</td>
					<td><input type="TEXT" name="chapter_name" size="45"
						value="<%=class_chapterVO.getChapter_name()%>"
						class="form-control" /></td>
				</tr>
				<tr>
					<td>所屬課程編號:</td>
					<td><input type="TEXT" name="class_id" size="45"
						value="<%=class_chapterVO.getClass_id()%>"
						class="form-control" /></td>
					</td>
					
			</tr>
			

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
				type="hidden" name="getClass_id"
				value="<%=class_chapterVO.getChapter_id()%>"> <input
				type="submit" value="送出修改">
	</FORM>
	</div>
</body>

</html>


