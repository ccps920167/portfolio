<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css"
		integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	<link href="<%=request.getContextPath()%>/vendors/video-js/css/video-js.css" rel="stylesheet" />
	<style>
		/* �M�Υ��� */
		* {
			box-sizing: border-box;
		}
	</style>
<title>class_chapter: Home</title>

</head>
<body bgcolor='white'>
	<div class="container">
	<h1>class_chapter: Home</h1>
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
	<div>
		<div class="btn-group" role="group" aria-label="Basic example" style="margin-bottom: 50px;">
		<form action="<%=request.getContextPath() %>/Class_chapter/Class_chapterServlet" method="POST">
			<button type="button" class="btn btn-secondary" >�d�߳浧</button>
			<button type="submit" class="btn btn-secondary" name="action" value="getALL">�d�ߥ���</button>
			<button type="submit" class="btn btn-secondary" name="action" value="addnew">�s�W���</button>
		</form>
		</div>
		<form action="<%=request.getContextPath() %>/Class_chapter/Class_chapterServlet" method="POST">
			<h2>�d�߳浧���</h2>
			<Label for="chapter_id">���`�s��</Label>
			<input name="chapter_id" type="text" placeholder="��J���`�s��" >
			<br>
			<Label for="class_id">�ҵ{�s��</Label>
			<input name="class_id" type="text" placeholder="��J�ҵ{�s��" >
			<br>
			<Label for="chapter_name">���`�W��</Label>
			<input name="chapter_name" type="text" placeholder="��J�D���`�W��" >
			<br>
			<input type="hidden" name="action" value="listOneClass_chapter">
			<input class="btn btn-primary" type="submit" value="�e�X�d��">
		</form>
	</div>
	<div></div>


</body>
</html>