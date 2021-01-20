<%@page import="com.class_unit.model.Class_unitVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.class_unit.*"%>
<%@ page import="java.util.*"%>

<%
	List<String> errorMsgs = (List) request.getAttribute("errorMsgs");
	Class_unitVO class_unitVO = (Class_unitVO) request.getAttribute("class_unitVO");
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
<link
	href="<%=request.getContextPath()%>/vendors/video-js/css/video-js.css"
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
<title>�椸�ק� - update_Class_unit_input.jsp</title>

</head>

<body>
	<!-- container -->
	<div class="container">
		<h1>
			�ק� �i<%=class_unitVO.getUnit_name()%>�j�椸
		</h1>

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
			action="<%=request.getContextPath()%>/Class_unit/Class_unitServlet"
			enctype="multipart/form-data">
			<table>
				<tr>
					<td>�椸�s��:</td>
					<td><input class="form-control" name="unit_id"
						value="<%=class_unitVO.getUnit_id()%>" type="text"
						placeholder="Readonly input here�K" readonly></td>
				</tr>
				<tr>
					<td>�椸�W��:</td>
					<td><input class="form-control" name="unit_name"
						value="<%=class_unitVO.getUnit_name()%>" type="text"
						placeholder="Readonly input here�K"></td>
				</tr>
				<tr>
					<td>���ݳ��`:</td>
					<td><input class="form-control" name="chapter_id"
						value="<%=class_unitVO.getChapter_id()%>" type="text"
						placeholder="Readonly input here�K"></td>
				</tr>
				<tr>
					<td>��s�ɮ�:</td>
					<td><input id="fileInput" name="video" type="file"></td>
				</tr>
				<tr>
					<%
						java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					%>

					<td>��s�ɶ�:</td>
					<td><input class="form-control" name="video_updateTime"
						type="text" " readonly
						value="<%=(class_unitVO == null) ? "" : df.format(class_unitVO.getVideo_updatetime())%>">
				</tr>
				<tr>
					<input id="sp" type="hidden" name="video_long" value="">
					<td><input type="hidden" name="action" value="update"></td>
					<td><input class="btn btn-primary " type="submit" value="�e�X�ק�"></td>
				</tr>
			</table>
		</FORM>
	</div>
	<!-- JavaScript -->
	<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/video-js/js/video.js"></script>
	<script>
		var fileInput = document.getElementById('fileInput');
		var sp = document.getElementById('sp');
		fileInput.onchange = function() {
			var file = fileInput.files[0];
			var reader = new FileReader();
			reader.onload = function() {
				var aud = new Audio(reader.result);

				aud.onloadedmetadata = function() {

					console.log(aud.duration)
					var vlen = aud.duration;
					document.getElementById("sp").value = vlen;

				};
			};
			reader.readAsDataURL(file);
		};
	</script>
</body>

</html>