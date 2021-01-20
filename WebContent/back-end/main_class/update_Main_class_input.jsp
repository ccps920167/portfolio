<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.main_class.model.*"%>

<%
	Main_classVO main_classVO = (Main_classVO) request.getAttribute("main_classVO");
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

.btn_del {
	width: 50px;
	padding: 3px;
	display: inline-block;
}

.form-group {
	border: 1px rgb(204, 204, 204) solid;
}
</style>
<title>�D�ҵ{��ƭק� - update_Main_Class_input.jsp</title>

</head>
<body>
	<!-- container -->

	<div class="container">
		<h1>�ק� �i <%=main_classVO.getMainClass_name()%> �j�ҵ{���O</h1>
		<a href="<%=request.getContextPath()%>/back-end/main_class/select_page.jsp">MainClass: Home</a>
			<br>
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

		<form method="POST" action="<%=request.getContextPath()%>/Main_class/Main_classServlet">
		<table>
			<tr>
				<td>�D�ҵ{���O�s��:</td>
				<td>
					<input class="form-control"  name="mainClass_id"
					value="<%=main_classVO.getMainClass_id()%>" 
					type="text" placeholder="Readonly input here�K" readonly>
					</td>
			</tr>
			<tr>
				<td>�D�ҵ{���O�W��:</td>
				<td><input type="TEXT" name="mainClass_name" size="45"
					value="<%=main_classVO.getMainClass_name()%>" class="form-control"/></td>
			</tr>
			<tr>
				<td>�D�ҵ{���O���A:</td>
				<td>
					<select class="form-control" name="mainClass_status" >
					<c:if test="${fn:contains(main_classVO.mainClass_status, '1')}">
						<option value="0" > ���� </option>
						<option value="1" selected> ��� </option>
					</c:if>
					<c:if test="${fn:contains(main_classVO.mainClass_status, '0')}">
						<option value="0" selected> ���� </option>
						<option value="1" > ��� </option>
					</c:if>
					</select>
					</td>
			</tr>
			

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="getMainClass_id" value="<%=main_classVO.getMainClass_id()%>"> <input
			type="submit" value="�e�X�ק�">
	</FORM>
	</div>
</body>

</html>


