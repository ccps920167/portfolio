<%@page import="com.class_info.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<%
	List<String> erMsgs = (List) request.getAttribute("erMsgs");
	Class_infoService ciSvc = new Class_infoService();
	List<Class_infoVO> list = ciSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<meta charset="BIG5">
<title>TOMATO後台管理平台</title>

<!---------------------------------------- css ---------------------------------------->

<!-- jquery.datetimepicker -->	
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.css" />
	
		
<!-- jquery.datetimepicker -->		
<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
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
				<li class="breadcrumb-item active" aria-current="page">學習留言</li>
			</ol>
		</nav>
		<br>
		<div class="container">
		<h1>所有課程列表</h1>
		<table class="table table-striped">
			<tr>
				<th>課程編號</th>
				<th>課程名稱</th>
				<th>開課會員</th>
				<th>狀態</th>
				<th>類別編號</th>
				<th>更新時間</th>
				<th>更新人員</th>
				<th>詳情</th>
				<th>修改</th>
				<th>刪除</th>
			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="ciVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">

				<tr>
					<td>${ciVO.class_id}</td>
					<td>${ciVO.class_name}</td>
					<td>${ciVO.member_id}</td>
					<td>${ciVO.class_status}</td>
					<td>${ciVO.subclass_id}</td>
					<td><fmt:formatDate value="${ciVO.update_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${ciVO.admin_id}</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/forward/backSrevlet"
							style="margin-bottom: 0px;">
							<input class="btn btn-danger" type="submit" value="查看"> 
							<input type="hidden" name="class_id" value="${ciVO.class_id}">
							<input type="hidden" name="action" value="back-listAllClass_info-Details">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/class_info/class_infoServlet"
							style="margin-bottom: 0px;">
							<input class="btn btn-danger" type="submit" value="修改"> <input
								type="hidden" name="class_id" value="${ciVO.class_id}">
							<input type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/class_info/class_infoServlet"
							style="margin-bottom: 0px;">
							<input class="btn btn-danger" type="submit" value="刪除"> <input
								type="hidden" name="class_id" value="${ciVO.class_id}">
							<input type="hidden" name="action" value="delete">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file"%>

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