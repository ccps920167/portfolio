<!-- �]�wJSP�s�X -->
<%@page import="com.video_record.model.Video_recordVO"%>
<%@page import="com.video_record.model.Video_recordService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<!-- �]�w���Үw -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- �]�wimport��� -->
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
/* �M�Υ��� */
* {
	box-sizing: border-box;
}
</style>
<title>�d�ߨϥΪ��[�ݬ������G - listAll.jsp</title>
</head>

<body bgcolor='white'>
	<div class="container">
		<h1>�d�ߨϥΪ��[�ݬ������G</h1>
		<a
			href="<%=request.getContextPath()%>/back-end/video_record/select_page.jsp">video_record:
			Home</a> <br>

		<table class="table table-striped">
			<tr>
				<th scope="col">#</th>
				<th scope="col">�s�������s��</th>
				<th scope="col">�|���s��</th>
				<th scope="col">�ҵ{�D���O</th>
				<th scope="col">�ҵ{�����O</th>
				<th scope="col">�ҵ{�W��</th>
				<th scope="col">�ҵ{���`</th>
				<th scope="col">�ҵ{�椸</th>
				<th scope="col">�̫�@���[�ݬ���</th>
				<th scope="col">�s��������s�ɶ�</th>
				<th scope="col">�ק�</th>
				<th scope="col">�R��</th>
			</tr>

			<!--���� -->
			<%@ include file="page1.file"%>

			<!--�j����X�Ҧ����� -->
			<c:forEach var="video_recordVO" items="${list}"
				begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"
				varStatus="status">
				<tr>
					<th scope="row">${ status.index + 1}</th>
					<!-- �s�������s��-->
					<td>${video_recordVO.record_id}</td>
					<!-- �|���s�� -->
					<td>${video_recordVO.member_id}</td>
					<!-- �ҵ{�D���O-->
					<td>�ݾ�X</td>
					<!-- �ҵ{�����O -->
					<td>�ݾ�X</td>
					<!-- �ҵ{�W��-->
					<td>�ݾ�X</td>
					<!-- �ҵ{���`-->
					<td>�ݾ�X</td>
					<!-- �ҵ{�椸-->
					<td>${video_recordVO.unit_id}</td>
					<!-- �̫�@���[�ݬ���-->
					<td>${video_recordVO.class_last}</td>
					<!-- �s��������s�ɶ�-->
					<td><fmt:formatDate
							value="${video_recordVO.video_updateTime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>

					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/Video_record/Video_recordServlet"
							style="margin-bottom: 0px;">
							<input class="btn btn-primary " type="submit" value="�ק�">
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
							<input class="btn btn-primary " type="submit" value="�R��">
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