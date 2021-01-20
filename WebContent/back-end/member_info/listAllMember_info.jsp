<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member_info.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	Member_infoService member_infoSvc = new Member_infoService();
	List<Member_infoVO> list = member_infoSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>��x�޲z-�Ҧ��|���C��</title>
</head>
<body>
	<%@ include file="header-back.jsp"%>
	<div style="margin-left: 250px; margin-right: 20px; margin-top: 100px" >
		<nav aria-label="breadcrumb" style="margin-top: 30px;">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Home</a></li>
				<li class="breadcrumb-item active" aria-current="page">�|���޲z</li>
				<li class="breadcrumb-item active" aria-current="page">�|���C��</li>
			</ol>
		</nav>
		<br>
		<div class="container">
			<h1>�Ҧ��|���C��</h1>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>�|���s��</th>
						<th>�|���W��</th>
						<th>�|���H�c</th>
						<th>�|������</th>
						<th>�|���ʧO</th>
						<th>�|���ͤ�</th>
						<th>�d�ݸԱ�</th>
						<th>�R��</th>
					</tr>
				</thead>
				<%@ include file="page1.file"%>
				<c:forEach var="member_infoVO" items="${list}"
					begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tbody>
						<tr>
							<td>${member_infoVO.member_id}</td>
							<td>${member_infoVO.member_name}</td>
							<td>${member_infoVO.member_email}</td>

							<td>
							<c:if test="${member_infoVO.member_role == 1}">
							�Ѯv
							</c:if>
							<c:if test="${member_infoVO.member_role == 0}">
							�ǥ�
							</c:if>
							</td>
							<td>
							<c:if test="${member_infoVO.member_gender == 1}">
							�k��
							</c:if>
							<c:if test="${member_infoVO.member_gender == 0}">
							�k��
							</c:if>
							<c:if test="${member_infoVO.member_gender == 2}">
							�h���ʧO
							</c:if>
</td>
							<td><fmt:formatDate value="${member_infoVO.member_birthday}"
									pattern="yyyy-MM-dd" /></td>


							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/member_info/Member_infoServlet"
									style="margin-bottom: 0px;">
									<input class="btn btn-danger" type="submit" value="�ԲӸ��"> <input type="hidden"
										name="member_id" value="${member_infoVO.member_id}"> <input
										type="hidden" name="action" value="getOne_For_Display">
								</FORM>
							</td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/member_info/Member_infoServlet"
									style="margin-bottom: 0px;">
									<input class="btn btn-danger" type="submit" value="�R��"> <input type="hidden"
										name="member_id" value="${member_infoVO.member_id}"> <input
										type="hidden" name="action" value="delete">
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