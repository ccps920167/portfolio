<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.class_info.model.*"%>
<%@page import="com.sub_class.model.*"%>
<%@ page import="java.util.*"%>

<%
	Map<String, List<Sub_classVO>> MainSubClass = (Map<String, List<Sub_classVO>>)application.getAttribute("MainSubClass");	
	Set key = MainSubClass.keySet();
	pageContext.setAttribute("key", key);
	pageContext.setAttribute("MainSubClass", MainSubClass);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>listAllClass_info</title>

<!---------------------------------------- css ---------------------------------------->

<!-- jquery.datetimepicker -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.css" />

<!-- jquery.datetimepicker -->
<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>
<!---------------------------------------- css ---------------------------------------->



</head>
<body>
	<%@ include file="header-back.jsp"%>

	<div style="margin-left: 250px; margin-right: 20px; margin-top: 50px" >
		<nav aria-label="breadcrumb" style="margin-top: 30px;">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Home</a></li>
				<li class="breadcrumb-item active" aria-current="page">課程管理</li>
				<li class="breadcrumb-item active" aria-current="page">課程列表</li>
			</ol>
		</nav>
		<br>
		<div class="container">

			<h1>條件查詢課程資料</h1>
			<form method="post"
				action="<%=request.getContextPath()%>/class_info/class_infoServlet"
				name="form1">
				<table class="table table-striped" style="border-style:dotted; border-width:0.5px">
					<tr>
						<td>課程名稱:</td>
						<td><input class="form-control" type="TEXT" name="class_name"
							size="45" /></td>
					</tr>
					<tr>
						<td>會員編號:</td>
						<td><input class="form-control" type="TEXT" name="member_id"
							size="45" /></td>
					</tr>
					<tr>
						<td>副類別課程編號:</td>
						<td>
						<select class="form-control" size="1" name="mainClass_id">
							<c:forEach var="keys" items="${key}">
							<c:forEach var="subClassVO" items="${MainSubClass[keys]}">
								<option value="${subClassVO.subClass_id}">${subClassVO.subClass_name}
								</option>
							</c:forEach>
							</c:forEach>
						</select>
						</td>
					</tr>
					<tr>
						<td>開始募資日期:</td>
						<td>
							<input class="form-control" type="TEXT" name="startfund_date" id="f_date1" />
						</td>
					</tr>
					<tr>
						<td>開課日期:</td>
						<td>
							<input name="startclass_time" id="f_date2" type="text" class="form-control" >
						</td>	
					</tr>
					<tr>
						<td>募資售價:</td>
						<td><input class="form-control" type="TEXT"
							name="startfund_price" size="45" /></td>
					</tr>
					<tr>
						<td>定價:</td>
						<td><input class="form-control" type="TEXT"
							name="original_price" size="45" /></td>
					</tr>
					<tr>
						<td>門檻人數:</td>
						<td><input class="form-control" type="TEXT"
							name="people_threshold" size="45" /></td>
					</tr>
					<tr>
						<td>課程長度:</td>
						<td><input class="form-control" type="TEXT"
							name="class_length" size="45" /></td>
					</tr>
					<tr>
						<td>更新人員:</td>
						<td><input class="form-control" type="TEXT" name="admin_id"
							size="45" /></td>
					</tr>

				</table>
				<div class="col-12" style="margin-top: 50px; margin-bottom: 50px">
					<input type="hidden" name="action" value="SearchOne"> <input
						type="submit" class="btn btn-danger" value="送出查詢">
				</div>
			</form>
		</div>
	</div>
	<!---------------------------------------- script ---------------------------------------->

<!-- jquery-->
<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>

<!-- popper-->
<script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>

<!-- bootstrap -->
<script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>

<!-- jquery.datetimepicker -->
<script src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.full.js"></script>

<!-- public.js-->
<script src="<%=request.getContextPath()%>/js/back-end/public.js"></script>

<!---------------------------------------- script ---------------------------------------->
</body>
</html>