<%@page import="java.sql.Timestamp"%>
<%@page import="com.verify_list.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%
	Verify_listVO verify_listVO = (Verify_listVO) request.getAttribute("verify_listVO"); 
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>addVL</title>

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
<body>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty erMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${erMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<form method="post" action="/TEA102G5/verify_list/verify_listServlet"
		name="form1">
		<table>
			<tr>
				<td>課程編號:</td>
				<td><input type="TEXT" name="class_id" size="45"
					value="<%=(verify_listVO == null) ? "課程編號" : verify_listVO.getClass_id()%>" /></td>
			</tr>
			<tr>
				<td>審核人員:</td>
				<td><input type="TEXT" name="admin_id" size="45"
					value="<%=(verify_listVO == null) ? "審核人員" : verify_listVO.getAdmin_id()%>" /></td>
			</tr>
			<tr>
				<td>審核紀錄:</td>
				<td><textarea rows="5" cols="50" name="class_check"><%=(verify_listVO == null) ? "審核紀錄" : verify_listVO.getClass_check()%></textarea>
				</td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>上傳時間:</td> -->
<!-- 				<td><input type="TEXT" name="upload_time" id="f_date1" /></td> -->
<!-- 			</tr> -->

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</form>

	<h2>
		<a href="<%=request.getContextPath()%>/back-end/verify_list/Select_pageVL.jsp">回到首頁</a>
	</h2>
</body>
<%
 	Timestamp upload_time = null;

 	try {
 		upload_time = verify_listVO.getUpload_time();
 	} catch (Exception e) {
 		upload_time = new Timestamp(System.currentTimeMillis());
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
	$.datetimepicker.setLocale('zh');
	$('#f_date1').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : true, //timepicker:true,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d H:i:s', //format:'Y-m-d H:i:s',
		value : new Date(), // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
</script>

</html>