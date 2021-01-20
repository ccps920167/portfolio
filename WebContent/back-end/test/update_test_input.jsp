<%@page import="java.sql.Time"%>
<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.test.model.*"%>

<%
	TestVO testVO = (TestVO) request.getAttribute("testVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�Ҹխק�</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>�Ҹխק�</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/test/select_page.jsp"><img
						src="<%=request.getContextPath()%>/back-end/test/images/tomato.gif" width="100"
						height="100" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>��ƭק�:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/test/testServlet" name="form1">
		<table>
			<tr>
				<td>����s��:<font color=red><b>*</b></font></td>
				<td><%=testVO.getTest_id()%></td>
			</tr>
			<jsp:useBean id="class_unitSvc" scope="page"
				class="com.class_unit.model.Class_unitService" />


			<tr>
				<td>����椸�s��:<font color=red><b>*</b></font></td>
				<td><select size="1" name="unit_id">
						<c:forEach var="class_unitVO" items="${class_unitSvc.all}">
							<option value="${class_unitVO.unit_id}"
								${(testVO.unit_id==class_unitVO.unit_id)?'selected':'' }>${class_unitVO.unit_id}
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<td>���絪��:</td>
				<td><input type="TEXT" name="test_answer" size="45"
					value="<%=testVO.getTest_answer()%>" /></td>
			</tr>
			<tr>
				<td>���示�e:</td>
				<td><input type="TEXT" name="test_content" size="45"
					value="<%=testVO.getTest_content()%>" /></td>
			</tr>
			<tr>
				<td>����ﶵA:</td>
				<td><input type="TEXT" name="opta" size="45"
					value="<%=testVO.getOpta()%>" /></td>
			</tr>
			<tr>
				<td>����ﶵB:</td>
				<td><input type="TEXT" name="optb" size="45"
					value="<%=testVO.getOptb()%>" /></td>
			</tr>
			<tr>
				<td>����ﶵC:</td>
				<td><input type="TEXT" name="optc" size="45"
					value="<%=testVO.getOptc()%>" /></td>
			</tr>
			<tr>
				<td>����ﶵD:</td>
				<td><input type="TEXT" name="optd" size="45"
					value="<%=testVO.getOptd()%>" /></td>
			</tr>




		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="test_id" value="<%=testVO.getTest_id()%>">
		<input type="submit" value="�e�X�ק�">
	</FORM>
</body>

</html>