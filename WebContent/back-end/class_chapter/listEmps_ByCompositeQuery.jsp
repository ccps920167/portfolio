<!-- �]�wJSP�s�X -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<!-- �]�w���Үw -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- �]�wimport��� -->
<%@ page import="java.util.*"%>
<%@ page import="com.main_class.model.*"%>


<%
	List<Main_classVO> list = (List) request.getAttribute("listEmps_ByCompositeQuery");
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

<title>�D�ҵ{���O��� - listOneMain_class.jsp</title>

</head>
<body bgcolor='white'>
	<div class="container">
		<h1>MainClass: listOneMain_class</h1>
	<a href="<%=request.getContextPath()%>/back-end/class_chapter/select_page.jsp">class_chapter: Home</a>
		<br>
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
			<!--���� -->
<%-- 			<%@ include file="page1.file"%> --%>

			<!--�j����X�Ҧ����� -->
			<c:forEach var="main_classVO" items="${list}" >

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
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/Main_class/Main_classServlet"
							"
							style="margin-bottom: 0px;">
							<input class="btn btn-primary " type="submit" value="�d�ݸԱ�">
							<input type="hidden" name="mainClass_name"
								value="${main_classVO.mainClass_name}"> <input
								type="hidden" name="mainClass_id"
								value="${main_classVO.mainClass_id}"> <input
								type="hidden" name="action" value="mainclass_id_sub_class">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/Main_class/Main_classServlet"
							style="margin-bottom: 0px;">
							<input class="btn btn-primary " type="submit" value="�ק�">
							<input type="hidden" name="mainClass_id"
								value="${main_classVO.mainClass_id}"> <input
								type="hidden" name="action" value="update_mainClass_id">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/Main_class/Main_classServlet"
							"
							style="margin-bottom: 0px;">
							<input class="btn btn-primary " type="submit" value="�R��">
							<input type="hidden" name="mainClass_id"
								value="${main_classVO.mainClass_id}"> <input
								type="hidden" name="action" value="delete">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>
<%-- 		<%@ include file="page2.file"%> --%>
	</div>
</body>
</html>