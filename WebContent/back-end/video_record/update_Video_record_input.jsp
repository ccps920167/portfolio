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
	<title>�D�ҵ{���O �s�W</title>
</head>

<body>
	<!-- container -->
	<div class="container">
		<h1>��s�[�ݬ���</h1>
		<a href="<%=request.getContextPath()%>/back-end/video_record/select_page.jsp">video_record:
			Home</a> <br>
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

				<form method="POST"
					action="<%=request.getContextPath()%>/Video_record/Video_recordServlet">
					
					<Label for="record_id">�����s��</Label>
					<input name="record_id" type="text"  readonly
					value="<%= (video_recordVO==null)? "" : video_recordVO.getRecord_id()%>"> <br>
					<Label for="member_id">�|���s��</Label>
					<input name="member_id" type="text" readonly
					value="<%= (video_recordVO==null)? "" : video_recordVO.getMember_id()%>"> <br>
					<!--   �n�אּajex �ʺA���		 -->
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
					<input name="unit_id" type="text" readonly
					value="<%= (video_recordVO==null)? "" : video_recordVO.getunit_id()%>"> <br>
					<Label for="class_last">�̫�@���[�ݬ���</Label>
					<input name="class_last" type="text" placeholder="��J�̫�@���[�ݬ���"
					value="<%= (video_recordVO==null)? "" : video_recordVO.getClass_last()%>"> <br>
					
<% 
         java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>
					
					<Label for="video_updateTime">�̫��s�ɶ�</Label>
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