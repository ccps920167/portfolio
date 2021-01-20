<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.allClass_info.model.*" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member_info.model.*"%>
<%@ page import="com.class_info.model.*"%>
<%
AllClass_infoVO AllClass_infoVO = (AllClass_infoVO) session.getAttribute("AllClass_infoVO");
Member_infoVO getTeacher = AllClass_infoVO.getTeacher();
Set<String> Student = (Set<String>)AllClass_infoVO.getStudent();
List<Member_infoVO> MemeberInfo = new Member_infoService().getAll();
pageContext.setAttribute("MemeberInfo", MemeberInfo);
pageContext.setAttribute("getTeacher", getTeacher);
pageContext.setAttribute("Student", Student);
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>TOMATO後台管理平台</title>
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
		
		<h1>老師資訊</h1>

			<table class="table table-striped">
				<thead>
					<tr>
						<th>會員編號</th>
						<th>會員名稱</th>
						<th>會員信箱</th>
						<th>會員身分</th>
						<th>會員性別</th>
						<th>會員生日</th>
						<th>查看詳情</th>
						<th>刪除</th>
					</tr>
				</thead>

					<tbody>
						<tr>
							<td>${getTeacher.member_id}</td>
							<td>${getTeacher.member_name}</td>
							<td>${getTeacher.member_email}</td>

							<td>${getTeacher.member_role}</td>
							<td>${getTeacher.member_gender}</td>
							<td><fmt:formatDate value="${getTeacher.member_birthday}"
									pattern="yyyy-MM-dd" /></td>


							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/member_info/Member_infoServlet"
									style="margin-bottom: 0px;">
									<input class="btn btn-danger" type="submit" value="詳細資料"> <input type="hidden"
										name="member_id" value="${getTeacher.member_id}"> <input
										type="hidden" name="action" value="getOne_For_Display">
								</FORM>
							</td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/member_info/Member_infoServlet"
									style="margin-bottom: 0px;">
									<input class="btn btn-danger" type="submit" value="刪除"> <input type="hidden"
										name="member_id" value="${getTeacher.member_id}"> <input
										type="hidden" name="action" value="delete">
								</FORM>
							</td>
						</tr>
					</tbody>
			</table>
			<c:if test="${empty getTeacher}">
				<img src="<%=request.getContextPath()%>/img/NoResult/NoResult.jpg">
			</c:if>
		
			<h1>所有學生列表</h1>

			<table class="table table-striped">
				<thead>
					<tr>
						<th>會員編號</th>
						<th>會員名稱</th>
						<th>會員信箱</th>
						<th>會員身分</th>
						<th>會員性別</th>
						<th>會員生日</th>
						<th>查看詳情</th>
						<th>刪除</th>
					</tr>
				</thead>
				<c:forEach var="member_infoVO" items="${MemeberInfo}">
				<c:forEach var="student" items="${Student}">
				<c:if test="${fn:contains(student, member_infoVO.member_id)}">
					<tbody>
						<tr>
							<td>${member_infoVO.member_id}</td>
							<td>${member_infoVO.member_name}</td>
							<td>${member_infoVO.member_email}</td>

							<td>${member_infoVO.member_role}</td>
							<td>${member_infoVO.member_gender}</td>
							<td><fmt:formatDate value="${member_infoVO.member_birthday}"
									pattern="yyyy-MM-dd" /></td>


							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/member_info/Member_infoServlet"
									style="margin-bottom: 0px;">
									<input class="btn btn-danger" type="submit" value="詳細資料"> <input type="hidden"
										name="member_id" value="${member_infoVO.member_id}"> <input
										type="hidden" name="action" value="getOne_For_Display">
								</FORM>
							</td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/member_info/Member_infoServlet"
									style="margin-bottom: 0px;">
									<input class="btn btn-danger" type="submit" value="刪除"> <input type="hidden"
										name="member_id" value="${member_infoVO.member_id}"> <input
										type="hidden" name="action" value="delete">
								</FORM>
							</td>
						</tr>


					</tbody>
					</c:if>
				</c:forEach>
					</c:forEach>
			</table>
			<c:if test="${empty Student}">
				<img src="<%=request.getContextPath()%>/img/NoResult/NoResult.jpg">
			</c:if>
		</div>
	</div>
	<!---------------------------------------- script ---------------------------------------->

	<!-- jquery-->
	<script
		src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>

	<!-- popper-->
	<script
		src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>

	<!-- bootstrap -->
	<script
		src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>

	<!-- jquery.datetimepicker -->
	<script
		src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.full.js"></script>

	<!-- public.js-->
	<script src="<%=request.getContextPath()%>/js/back-end/public.js"></script>

	<!---------------------------------------- script ---------------------------------------->



</body>

</html>