<%@page import="java.sql.Time"%>
<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.login_history.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	Login_historyVO login_historyVO = (Login_historyVO) request.getAttribute("login_historyVO");
%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>登入歷史新增</title>
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
				<h3>登入歷史新增</h3>
			</td>
			<td>
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/login_history/select_page.jsp"><img src="<%=request.getContextPath()%>/back-end/login_history/images/tomato.gif"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>


	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>


	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/login_history/login_historyServlet" name="form1">
		<table>
			<tr>
				<td>登入時間:</td>
				<td><input name="login_time" id="f_date1" type="text">
				</td>
			</tr>

			<tr>
				<td>登入裝置:</td>
				<td><input type="TEXT" name="login_arrange" size="45"
					value="<%=(login_historyVO == null) ? "samsung" : login_historyVO.getLogin_arrange()%>" /></td>
			</tr>

			<tr>
				<td>登入IP:</td>
				<td><input type="TEXT" name="login_ip" size="45"
					value="<%=(login_historyVO == null) ? "140.115.19.111" : login_historyVO.getLogin_ip()%>" /></td>
			</tr>

			<jsp:useBean id="member_infoSvc" scope="page"
				class="com.member_info.model.Member_infoService" />
			<tr>

				<td>會員編號:</td>
				<td><select size="1" name="member_id">
						<c:forEach var="member_infoVO" items="${member_infoSvc.all}">

							<option value="${member_infoVO.member_id}"
								${(login_historyVO.member_id==member_infoVO.member_id)? 'selected':'' }>${member_infoVO.member_id}
						</c:forEach>
				</select></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
</body>
<%
	Timestamp login_time = null;
	try {
		login_time = login_historyVO.getLogin_time();
	} catch (Exception e) {
		login_time = new Timestamp(System.currentTimeMillis());
	}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
	$.datetimepicker.setLocale('zh'); // kr ko ja en
	$('#f_date1').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : true, //timepicker: false,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d H:i:s',
		value : new Date(),
	});
</script>
 




</body>
</html>