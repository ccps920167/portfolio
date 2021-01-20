<!-- �]�wJSP�s�X -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<!-- �]�w���Үw -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- �]�wimport��� -->
<%@ page import="java.util.*"%>
<%@ page import="com.main_class.model.*"%>


<%
	Main_classService main_classSvc = new Main_classService();
	List<Main_classVO> list = main_classSvc.getMain_classAll();
	pageContext.setAttribute("list", list);
%>


<html>

<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
<title>listAllClass_info</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<link
	href="<%=request.getContextPath()%>/vendors/video-js/css/video-js.css"
	rel="stylesheet" />
<!-- �C��CSS -->
<link href="<%=request.getContextPath()%>/css/back-end/icon.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/back-end/public.css"
	rel="stylesheet" type="text/css" />

<script
		src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/back-end/public.js"
		language="javascript" type="text/javascript"></script>

</head>
<body>
	<%@ include file="header-back.jsp"%>

	<div style="margin-left: 250px; margin-right: 20px; margin-top: 100px" >
		<nav aria-label="breadcrumb" style="margin-top: 30px;">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Home</a></li>
				<li class="breadcrumb-item active" aria-current="page">�ҵ{�޲z</li>
				<li class="breadcrumb-item active" aria-current="page">�ҵ{�C��</li>
			</ol>
		</nav>
		<br>
<div class="container">
		<table class="table table-striped">
			<tr>
				<th scope="col">#</th>
				<th scope="col">�D�ҵ{�s��</th>
				<th scope="col">�D�ҵ{�W��</th>
				<th scope="col">�D�ҵ{���A</th>
				<th scope="col">�d�ݸԱ�</th>
				<th scope="col">�ק�</th>
				<th scope="col">�R��</th>
			</tr>

			<!--�j����X�Ҧ����� -->
			<c:forEach var="main_classVO" items="${list}"  varStatus="status">

				<tr>
					<th scope="row">${ status.index + 1}</th>
					<td>${main_classVO.mainClass_id}</td>
					<td>${main_classVO.mainClass_name}</td>
					<!--��ܨ��N -->
					<c:if test="${fn:contains(main_classVO.mainClass_status, '1')}">
						<td>���</td>
					</c:if>
					<c:if test="${fn:contains(main_classVO.mainClass_status, '0')}">
						<td>����</td>
					</c:if>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Main_class/Main_classServlet""
							style="margin-bottom: 0px;">
							<input class="btn btn-primary " type="submit" value="�d�ݸԱ�"> 
							<input type="hidden" name="mainClass_name" value="${main_classVO.mainClass_name}">
							<input type="hidden" name="mainClass_id" value="${main_classVO.mainClass_id}">
							<input type="hidden" name="action" value="mainclass_id_sub_class">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Main_class/Main_classServlet"
							style="margin-bottom: 0px;">
							<input class="btn btn-primary " type="submit" value="�ק�"> <input type="hidden" name="mainClass_id" value="${main_classVO.mainClass_id}">
							<input type="hidden" name="action" value="update_mainClass_id">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Main_class/Main_classServlet""
							style="margin-bottom: 0px;">
							<input class="btn btn-primary " type="submit" value="�R��"> <input type="hidden" name="mainClass_id" value="${main_classVO.mainClass_id}">
							<input   type="hidden" name="action" value="delete">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	</div>
</body>

</html>