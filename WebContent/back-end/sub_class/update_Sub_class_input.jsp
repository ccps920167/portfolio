<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.sub_class.model.*"%>
<%@ page import="java.util.*"%>

<%
	Sub_classVO sub_classVO = (Sub_classVO) request.getAttribute("sub_classVO");
	List<String> errorMsgs = (List) request.getAttribute("errorMsgs");
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
<title>副課程資料修改 - update_Sub_Class_input.jsp</title>

</head>
<body>
	<!-- container -->
	<div class="container">
		<h1>
			修改 【
			<%=sub_classVO.getSubClass_name()%>】課程類別
		</h1>

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

		<form method="POST" action="<%=request.getContextPath()%>/Sub_class/Sub_classServlet">
			<table>
				<tr>
					<td>副課程類別編號:</td>
					<td><input class="form-control" name="subClass_id"
						value="<%=sub_classVO.getSubClass_id()%>" type="text"
						placeholder="Readonly input here…" readonly></td>
				</tr>
				<tr>
					<td>副課程類別名稱:</td>
					<td><input type="TEXT" name="subClass_name" size="45"
						value="<%=sub_classVO.getSubClass_name()%>" class="form-control" /></td>
				</tr>
				<tr>
					<td>副課程類別狀態:</td>
					<td><select class="form-control" name="subClass_status">
							<c:if test="${fn:contains(sub_classVO.subClass_status, '1')}">
								<option value="0">隱藏</option>
								<option value="1" selected>顯示</option>
							</c:if>
							<c:if test="${fn:contains(sub_classVO.subClass_status, '0')}">
								<option value="0" selected>隱藏</option>
								<option value="1">顯示</option>
							</c:if>
					</select></td>
				</tr>
				<tr>
					<jsp:useBean id="mainClass" scope="page"
						class="com.main_class.model.Main_classService" />
					<td>副課程所屬主課程類別:</td>
					<td><select class="form-control" name="mainClass_id">
							<c:forEach var="mainVO" items="${mainClass.main_classAll}">
								<option value="${mainVO.mainClass_id}">${mainVO.mainClass_name}
							</c:forEach>
					</select></td>
				</tr>

			</table>
			<br> 

			<input type="hidden" name="action" value="update">
			<input type="hidden" name="getSubClass_id"
				value="<%=sub_classVO.getMainClass_id()%>"> 
			<input type="submit" value="送出修改">
		</FORM>
	</div>
</body>

</html>