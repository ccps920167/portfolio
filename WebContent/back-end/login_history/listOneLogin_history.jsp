<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.login_history.model.*"%>


<%
	Login_historyVO login_historyVO = (Login_historyVO) request.getAttribute("login_historyVO");
%>

<html>
<head>

<title>�浧�n�J���v����</title>
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
				<h3>�浧�n�J���v����</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/login_history/select_page.jsp"><img
						src="<%=request.getContextPath()%>/back-end/login_history/images/tomato.gif"
						width="100" height="100" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>�n�J���v�����s��</th>
			<th>�n�J�ɶ�</th>
			<th>�n�J�˸m</th>
			<th>�n�JIP</th>
			<th>�|���W��</th>
		</tr>
		<tr>
			<td><%=login_historyVO.getLogin_id()%></td>


			<td>
				<%
					java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String formatDate = df.format(login_historyVO.getLogin_time());
					out.println(formatDate);
				%>

			</td>


			<td><%=login_historyVO.getLogin_arrange()%></td>
			<td><%=login_historyVO.getLogin_ip()%></td>
			<td><%=login_historyVO.getMember_id()%></td>
		</tr>
	</table>

</body>
</html>