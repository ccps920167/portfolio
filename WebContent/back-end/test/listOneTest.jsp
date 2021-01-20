<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.test.model.*"%>


<%
	TestVO testVO = (TestVO) request.getAttribute("testVO");
%>

<html>
<head>

<title>查詢單筆考試</title>
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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>


</head>
<body bgcolor='white'>
	<table id="table-1">
		<tr>
			<td>
				<h3>單筆登入歷史紀錄</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/test/select_page.jsp"><img
						src="<%=request.getContextPath()%>/back-end/test/images/tomato.gif"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>考試編號編號</th>
			<th>課程單元編號</th>
			<th>題目解答</th>
			<th>題目內容</th>
			<th>測驗選項A</th>
			<th>測驗選項B</th>
			<th>測驗選項C</th>
			<th>測驗選項D</th>
		</tr>
		<tr>
			<td><%=testVO.getTest_id()%></td>
			<td><%=testVO.getUnit_id()%></td>
			<td><%=testVO.getTest_answer()%></td>
			<td><%=testVO.getTest_content()%></td>
			<td><%=testVO.getOpta()%></td>
			<td><%=testVO.getOptb()%></td>
			<td><%=testVO.getOptc()%></td>
			<td><%=testVO.getOptd()%></td>
		</tr>
	</table>

</body>
</html>