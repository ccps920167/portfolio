<%@page import="com.class_chapter.model.Class_chapterVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="com.class_chapter.*"%>

<!--�ޤJ�ǹL�Ӫ����� -->
<%
	List<String> errorMsgs = (List) request.getAttribute("errorMsgs");
	Class_chapterVO class_chapterVO = (Class_chapterVO) request.getAttribute("class_chapterVO");
%>

<!-- HTML�y�k -->
<!doctype html>
<html lang="en">

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
/* �M�Υ��� */
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
<title>���`�s�W</title>
</head>

<body>
	<!-- container -->
	<div class="container">
	<a href="<%=request.getContextPath()%>/back-end/class_chapter/select_page.jsp">class_chapter: Home</a>
		<br>
		<!--�p�Greq�ݩʤ����s�berrorMsgs�A�޶i�ݩʤ���� -->
		<%-- ���~��C --%>
		<%--<c:if test="����"> --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">�Эץ��H�U���~:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<!-- ========================���~�C����============================= -->
		<form method="POST"
			action="<%=request.getContextPath()%>/Class_chapter/Class_chapterServlet">

			<div class="form-group ">
				<div class="row">
					<div class="col-sm-6" style="margin-top: 10px;">

						<jsp:useBean id="mainClassSvc" scope="page"
							class="com.main_class.model.Main_classService" />

						<ul class="sub_class_list">
							<li>���`�W��<br> <input name="chapter_name" type="text"
								class="form-control chapter_name"
								style="display: inline-block; width: 200px;">
</li>
							<li>���ݽҵ{<br> <input name="class_id" type="text"
								class="form-control class_id"
								style="display: inline-block; width: 200px;">
						</li></ul>
					</div>
				</div>
			</div>
			<input type="hidden" name="action" value="insert_all">
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