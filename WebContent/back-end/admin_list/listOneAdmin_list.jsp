<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.admin_list.model.*"%>


<%
	Admin_listVO admin_listVO = (Admin_listVO) request.getAttribute("admin_listVO");
%>

<html>
<head>

<title>�浧�޲z�����</title>
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
				<h3>�浧�޲z�����</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/admin_list/select_page.jsp"><img
						src="<%=request.getContextPath()%>/back-end/admin_list/images/tomato.gif"
						width="100" height="100" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>�޲z���s��</th>
			<th>�޲z���W��</th>
			<th>�޲z���K�X</th>
			<th>�޲z�����A</th>
		</tr>
		<tr>
			<td><%=admin_listVO.getAdmin_id()%></td>
			<td><%=admin_listVO.getAdmin_name()%></td>
			<td><%=admin_listVO.getAdmin_pwd()%></td>
			<td><%=admin_listVO.getAdmin_status()%></td>
		</tr>
	</table>

</body>
</html>