<%@page import="com.class_info.model.Class_infoService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.class_info.model.*"%>
<%@ page import="java.util.*"%>

<% 
	Class_infoService srv = new Class_infoService();
	List<Class_infoVO> list = srv.getAll(); 

%>


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
<title>Video_record: Home</title>

</head>
<body bgcolor='white'>
	<div class="container">
	<h1>video_record: Home</h1>
	<a href="<%=request.getContextPath()%>/back-end/video_record/select_page.jsp">video_record: Home</a>
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
		<form action="<%=request.getContextPath()%>/Video_record/Video_recordServlet" method="POST">
			<button type="button" class="btn btn-secondary" >�d�߳浧</button>
			<button type="submit" class="btn btn-secondary" name="action" value="getALL">�d�ߥ���</button>
			<button type="submit" class="btn btn-secondary" name="action" value="addnew">�s�W���</button>
		</form>
		</div>
		<form action="<%=request.getContextPath()%>/Video_record/Video_recordServlet" method="POST">
			<h2>�d�߳浧���</h2>
			<Label for="record_id">�v�������s��</Label>
			<input name="record_id" type="text" placeholder="��J�v�������s��" >
			<br>
			<Label for="member_id">�|���s��</Label>
			<input name="member_id" type="text" placeholder="��J�|���s��" >
			<br>
<!--    			�n�אּajex �ʺA���		 -->
			<Label for="mainClass_id">�D�ҵ{���O</Label>
			<select class="mainClass_id" name=mainClass_id>
			</select>
			<br>
			<Label for="subClass_id">�ƽҵ{���O</Label>
			<select class="subClass_id" name=subClass_id>
			</select>
			<br>
			<Label for="class_id">�ҵ{�W��</Label>
			<select class="class_id" name=class_id>
			</select>
			<br>
			<Label for="unit_id">�ҵ{�椸�s��</Label>
			<input name="unit_id" type="text" placeholder="��J�椸�s��" >
			<br>
			<input type="hidden" name="action" value="listOneVideo_record">
			<input class="btn btn-primary" type="submit" value="�e�X�d��">
		</form>
	</div>
	<div></div>


</body>
</html>