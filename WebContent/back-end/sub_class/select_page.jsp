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
<title>SubClass: Home</title>

</head>
<body bgcolor='white'>
	<div class="container">
	<h1>SubClass: Home</h1>
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
		<form action="<%=request.getContextPath()%>/Sub_class/Sub_classServlet" method="POST">
			<button type="button" class="btn btn-secondary" >�d�߳浧</button>
			<button type="submit" class="btn btn-secondary" name="action" value="getALL">�d�ߥ���</button>
			<button type="submit" class="btn btn-secondary" name="action" value="addnew">�s�W���</button>
		</form>
			
		</div>
		<form action="<%=request.getContextPath()%>/Sub_class/Sub_classServlet" method="POST">
			<h2>�d�߳浧���</h2>
			<Label for="subClass_id">�ƽҵ{���O�s��</Label>
			<input name="subClass_id" type="text" placeholder="��J�ƽҵ{���O�s��" >
			<br>
			<Label for="subClass_name">�ƽҵ{���O�W��</Label>
			<input name="subClass_name" type="text" placeholder="��J�ƽҵ{���O�W��" >
			<br>
			<select class="form-control" name="subClass_status">
					<option value="0">����</option>
					<option value="1">���</option>
			</select>
			<br>
			<input type="hidden" name="action" value="listOneSub_class">
			<input class="btn btn-primary" type="submit" value="�e�X�d��">
		</form>
	</div>
	<div></div>


</body>
</html>