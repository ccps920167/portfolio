<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.login_history.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    Login_historyService login_historySvc = new Login_historyService();
    List<Login_historyVO> list = login_historySvc.getAll();
    pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>


<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
<title>listAllClass_info</title>

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

	<div style="margin-left: 250px; margin-right: 20px; margin-top: 100px" >
		<nav aria-label="breadcrumb" style="margin-top: 30px;">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Home</a></li>
				<li class="breadcrumb-item active" aria-current="page">會員管理</li>
				<li class="breadcrumb-item active" aria-current="page">會員登入狀況</li>
				
			</ol>
		</nav>
		<br>
		<div class="container">
		<h1>所有登入狀況</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>登入歷史紀錄編號</th>
					<th>登入時間</th>
					<th>登入裝置</th>
					<th>登入IP</th>
					<th>會員名稱</th>
					<th>刪除</th>
				</tr>
			</thead>
			<%@ include file="page1.file"%>
			<c:forEach var="login_historyVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
				<tbody>
					<tr>
						<td>${login_historyVO.login_id}</td>
						<td><fmt:formatDate value="${login_historyVO.login_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>			
						<td>${login_historyVO.login_arrange}</td>
						<td>${login_historyVO.login_ip}</td>
						<td>${login_historyVO.member_id}</td>
						
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/login_history/Login_historyServlet"
								style="margin-bottom: 0px;">
								<input type="submit" value="刪除"> 
								<input type="hidden" name="login_id" value="${login_historyVO.login_id}"> 
								<input type="hidden" name="action" value="delete">
							</FORM>
						</td>
					</tr>


				</tbody>
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

		

