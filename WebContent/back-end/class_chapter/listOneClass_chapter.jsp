<!-- �]�wJSP�s�X -->
<%@page import="com.class_chapter.model.Class_chapterVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<!-- �]�w���Үw -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- �]�wimport��� -->
<%@ page import="java.util.*"%>
<%@ page import="com.class_chapter.*"%>


<%
	Class_chapterVO main_classVo = new Class_chapterVO();
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
/* �M�Υ��� */
* {
	box-sizing: border-box;
}
</style>

<title>�D�ҵ{���O��� - listOneMain_class.jsp</title>

</head>
<body bgcolor='white'>
	<div class="container">
		<h1>MainClass: listOneMain_class</h1>
		<a
			href="<%=request.getContextPath()%>/back-end/class_chapter/select_page.jsp">class_chapter:
			Home</a> <br>
		<table class="table table-striped">
			<tr>
				<th scope="col">#</th>
				<th scope="col">���`�s��</th>
				<th scope="col">���`�W��</th>
				<th scope="col">���ݽҵ{</th>
				<th scope="col">�d�ݸԱ�</th>
				<th scope="col">�ק�</th>
				<th scope="col">�R��</th>
			</tr>


			<tr>
				<th scope="row">1</th>
				<td>${class_chapterVO.chapter_id}</td>
				<td>${class_chapterVO.chapter_name}</td>
				<td>${class_chapterVO.class_id}</td>
				<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/Class_chapter/Class_chapterServlet"
							"
							style="margin-bottom: 0px;">
							<input class="btn btn-primary " type="submit" value="�d�ݸԱ�">
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
							<input class="btn btn-primary " type="submit" value="�ק�">
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
							<input class="btn btn-primary " type="submit" value="�R��">
							<input type="hidden" name="chapter_id"
								value="${class_chapterVO.chapter_id}"> <input
								type="hidden" name="action" value="delete">
						</FORM>
					</td>
			</tr>

		</table>
	</div>
</body>
</html>