<!-- �]�wJSP�s�X -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<!-- �]�w���Үw -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- �]�wimport��� -->
<%@ page import="java.util.*"%>
<%@ page import="com.sub_class.model.*"%>
<%@ page import="com.main_class.model.*"%>


<%
	Main_classService main_classSvc = new Main_classService();
	Sub_classService sub_classSvc = new Sub_classService();
	List<Sub_classVO> list = sub_classSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>

<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="/TEA102G5/vendors/bootstrap/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<link href="/TEA102G5/vendors/video-js/css/video-js.css"
	rel="stylesheet" />
<style>
/* �M�Υ��� */
* {
	box-sizing: border-box;
}
</style>
<title>�Ҧ��ƽҵ{���O - listAll.jsp</title>
</head>

<body bgcolor='white'>
	<div class="container">
		<h1>�Ҧ��ƽҵ{���O</h1>
		<a href="<%=request.getContextPath()%>/back-end/sub_class/select_page.jsp">SubClass: Home</a>
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


		<table class="table table-striped">
			<tr>
				<th scope="col">#</th>
				<th scope="col">�ƽҵ{�s��</th>
				<th scope="col">�ƽҵ{�W��</th>
				<th scope="col">�ƽҵ{���A</th>
				<th scope="col">���ݥD�ҵ{</th>
				<th scope="col">�d�ݸԱ�</th>
				<th scope="col">�ק�</th>
				<th scope="col">�R��</th>
			</tr>

			<!--���� -->
			<%@ include file="page1.file"%>

			<!--�j����X�Ҧ����� -->
			<c:forEach var="sub_classVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>" varStatus="status">

				<tr>
					<th scope="row">${ status.index + 1}</th>
					<td>${sub_classVO.subClass_id}</td>
					<td>${sub_classVO.subClass_name}</td>
					<!--��ܨ��N -->
					<c:if test="${fn:contains(sub_classVO.subClass_status, '1')}">
						<td>���</td>
					</c:if>
					<c:if test="${fn:contains(sub_classVO.subClass_status, '0')}">
						<td>����</td>
					</c:if>
					<td>
						${sub_classVO.mainClass_id}
<%-- 						${main_classSvc.getOneMain_class(sub_classVO.mainClass_id).mainClass_name} --%>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="#"
							style="margin-bottom: 0px;">
							<input class="btn btn-primary " type="submit" value="�d�ݸԱ�">
							<input type="hidden" name="subClass_id"
								value="${sub_classVO.subClass_id}"> <input
								type="hidden" name="action" value="#">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/Sub_class/Sub_classServlet"
							style="margin-bottom: 0px;">
							<input class="btn btn-primary " type="submit" value="�ק�">
							<input type="hidden" name="subClass_id"
								value="${sub_classVO.subClass_id}"> <input
								type="hidden" name="action" value="update_subClass_id">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/Sub_class/Sub_classServlet"
							"
							style="margin-bottom: 0px;">
							<input class="btn btn-primary " type="submit" value="�R��">
							<input type="hidden" name="subClass_id"
								value="${sub_classVO.subClass_id}"> <input
								type="hidden" name="action" value="delete_all">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>

		<%@ include file="page2.file"%>
	</div>
</body>

</html>