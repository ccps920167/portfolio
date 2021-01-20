<!-- �]�wJSP�s�X -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<!-- �]�w���Үw -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- �]�wimport��� -->
<%@ page import="java.util.*"%>
<%@ page import="com.main_class.model.*"%>
<%@ page import="com.sub_class.model.*"%>


<%
	Main_classService main_classSvc = new Main_classService();
	Main_classVO main_classVO = (Main_classVO) request.getAttribute("main_classVO");
	Set<Sub_classVO> list = (Set) request.getAttribute("Sub_classVO_list");
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
<title>�D�ҵ{���O���ƽҵ{���O - Sub_classVO_list.jsp</title>
</head>

<body bgcolor='white'>
	<div class="container">
		<h1>�i<%=main_classVO.getMainClass_name()%>�j���ƽҵ{���O</h1>
	<a href="<%=request.getContextPath()%>/back-end/class_unit/select_page.jsp">class_unit: Home</a>
		<br>
	
		<FORM METHOD="post"
			ACTION="<%=request.getContextPath()%>/Class_unit/Class_unitServlet"
			style="margin-bottom: 0px;">
			<input class="btn btn-primary " type="submit" value="�s�W"> <input
				type="hidden" name="mainClass_id" value="<%=main_classVO.getMainClass_id()%>"> <input
				type="hidden" name="mainClass_name" value="<%=main_classVO.getMainClass_name()%>">
			<input type="hidden" name="action" value="add_SubClass">
		</FORM>
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
				<th scope="col">�d�ݸԱ����ݽҵ{</th>
				<th scope="col">�ק�</th>
				<th scope="col">�R��</th>
			</tr>



			<%
				int rowsPerPage = 3; //�C��������    
				int rowNumber = 0; //�`����
				int pageNumber = 0; //�`����      
				int whichPage = 1; //�ĴX��
				int pageIndexArray[] = null;
				int pageIndex = 0;

				rowNumber = list.size();
				if (rowNumber % rowsPerPage != 0)
					pageNumber = rowNumber / rowsPerPage + 1;
				else
					pageNumber = rowNumber / rowsPerPage;
			%>

			<b>���� �X �d �� �� �� �p �U �� ��: �@<font color=red><%=rowNumber%></font>��
			</b>
			<!--���� -->
			<%-- 			<%@ include file="page1.file"%> --%>

			<!--�j����X�Ҧ����� -->
			<c:forEach var="sub_classVO" items="${list}" varStatus="status">
				<%
					for (Sub_classVO sub_classVO : list)
				%>
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
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/Class_unit/Class_unitServlet"
							style="margin-bottom: 0px;">
							<input class="btn btn-primary " type="submit" value="�d�ݸԱ�">
							<input type="hidden" name="mainClass_id"
								value="${sub_classVO.subClass_id}"> <input type="hidden"
								name="action" value="mainclass_id_sub_class">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/Class_unit/Class_unitServlet"
							style="margin-bottom: 0px;">
							<input class="btn btn-primary " type="submit" value="�ק�">
							<input type="hidden" name="subClass_id"
								value="${sub_classVO.subClass_id}"> <input type="hidden"
								name="action" value="update_subClass_id">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/Class_unit/Class_unitServlet"
							style="margin-bottom: 0px;">
							<input type="hidden" name="subClass_id"
								value="${sub_classVO.subClass_id}"> 
							<input type="hidden"
								name="mainClass_name" value="<%=main_classVO.getMainClass_name()%>"> <input
								type="hidden" name="mainClass_id" value="<%=main_classVO.getMainClass_id()%>">
							<input type="hidden" name="action" value="delete"> <input
								class="btn btn-primary " type="submit" value="�R��">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>

		<%-- 		<%@ include file="page2.file"%> --%>
	</div>
</body>

</html>