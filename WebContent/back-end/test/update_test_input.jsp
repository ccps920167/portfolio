<%@page import="java.sql.Time"%>
<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.test.model.*"%>

<%
	TestVO testVO = (TestVO) request.getAttribute("testVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>考試修改</title>

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
				<h3>考試修改</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/test/select_page.jsp"><img
						src="<%=request.getContextPath()%>/back-end/test/images/tomato.gif" width="100"
						height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/test/testServlet" name="form1">
		<table>
			<tr>
				<td>測驗編號:<font color=red><b>*</b></font></td>
				<td><%=testVO.getTest_id()%></td>
			</tr>
			<jsp:useBean id="class_unitSvc" scope="page"
				class="com.class_unit.model.Class_unitService" />


			<tr>
				<td>測驗單元編號:<font color=red><b>*</b></font></td>
				<td><select size="1" name="unit_id">
						<c:forEach var="class_unitVO" items="${class_unitSvc.all}">
							<option value="${class_unitVO.unit_id}"
								${(testVO.unit_id==class_unitVO.unit_id)?'selected':'' }>${class_unitVO.unit_id}
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<td>測驗答案:</td>
				<td><input type="TEXT" name="test_answer" size="45"
					value="<%=testVO.getTest_answer()%>" /></td>
			</tr>
			<tr>
				<td>測驗內容:</td>
				<td><input type="TEXT" name="test_content" size="45"
					value="<%=testVO.getTest_content()%>" /></td>
			</tr>
			<tr>
				<td>測驗選項A:</td>
				<td><input type="TEXT" name="opta" size="45"
					value="<%=testVO.getOpta()%>" /></td>
			</tr>
			<tr>
				<td>測驗選項B:</td>
				<td><input type="TEXT" name="optb" size="45"
					value="<%=testVO.getOptb()%>" /></td>
			</tr>
			<tr>
				<td>測驗選項C:</td>
				<td><input type="TEXT" name="optc" size="45"
					value="<%=testVO.getOptc()%>" /></td>
			</tr>
			<tr>
				<td>測驗選項D:</td>
				<td><input type="TEXT" name="optd" size="45"
					value="<%=testVO.getOptd()%>" /></td>
			</tr>




		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="test_id" value="<%=testVO.getTest_id()%>">
		<input type="submit" value="送出修改">
	</FORM>
</body>

</html>