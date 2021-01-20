<%@page import="java.sql.Time"%>
<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.admin_list.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	Admin_listVO admin_listVO = (Admin_listVO) request.getAttribute("admin_listVO");
%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�޲z���s�W</title>
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
				<h3>�޲z���s�W</h3>
			</td>
			<td>
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/admin_list/select_page.jsp"><img src="<%=request.getContextPath()%>/back-end/admin_list/images/tomato.gif"
						width="100" height="100" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>��Ʒs�W:</h3>


	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>


	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/admin_list/admin_listServlet" name="form1">
		<table>
		
			<tr>
				<td>�޲z���W��:</td>
				<td><input type="TEXT" name="admin_name" size="45"
					value="<%=(admin_listVO == null) ? "������" : admin_listVO.getAdmin_name()%>" /></td>
			</tr>

			<tr>
				<td>�޲z���K�X:</td>
				<td><input type="TEXT" name="admin_pwd" size="45"
					value="<%=(admin_listVO == null) ? "123456" : admin_listVO.getAdmin_pwd()%>" /></td>
			</tr>
			<tr>
				<td>�޲z�����A:</td>
				<td><select name="admin_status" id="adm_status">
						<option value="0">����</option>
						<option value="1">�}��</option>

				</select></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="�e�X�s�W">
	</FORM>
</body>
<script>
	// �U�Ԧ�����ܾ�(selector Id, data �̭�����)
	function setSelectorValue(target, targetValue) {
		var targetSelctor = document.getElementById(target);
		for (var i = 0; i < targetSelctor.options.length; i++) {
			if (parseInt(targetSelctor.options[i].getAttribute("value"), 10) === targetValue) {
				targetSelctor.options[i].setAttribute("selected", true);
			} else {
				targetSelctor.options[i].removeAttribute("selected");
			}
		}
	}
	// �ˬd�O�_�����(java)
	var adminDataExist =<%=admin_listVO != null%>;
    <%Integer adm_status = 0;
			if (admin_listVO != null) {
				adm_status = (admin_listVO.getAdmin_status() != null) ? admin_listVO.getAdmin_status() : 0;
			}%>
	// �p�G����� set data(js)
	if (adminDataExist) {
		setSelectorValue("adm_status",<%=adm_status%>);
	}

	</script>



</body>
</html>