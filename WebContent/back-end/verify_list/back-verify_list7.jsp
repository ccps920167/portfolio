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
	List<Class_infoVO> list = ciSvc.getAllVerifyStatus7();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<meta charset="BIG5">
<title>TOMATO��x�޲z���x</title>

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
  .page-item.active .page-link{
background-color: #DC3545;
border-color: #DC3545
}   
</style>
<!---------------------------------------- css ---------------------------------------->



</head>
<body>
	<%@ include file="header-back.jsp"%>

	<div style="margin-left: 250px; margin-right: 20px;">
		<nav aria-label="breadcrumb" style="margin-top: 30px;">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Home</a></li>
				<li class="breadcrumb-item active" aria-current="page">�ҵ{�޲z</li>
				<li class="breadcrumb-item active" aria-current="page">�ҵ{�C��</li>
			</ol>
		</nav>
		<br>
		<div class="container">
		<style>
.page-item.active .page-link{
	background-color: #DC3545;
	border-color: #DC3545
}   
</style>
		<div class="jumbotron jumbotron-fluid">
            <div class="container" style="text-align:center">
                <nav aria-label="...">
                <h1>�ҵ{�C��</h1>
                    <ul class="pagination pagination-lg justify-content-center">
                        <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/back-end/verify_list/back-verify_listAll.jsp">�Ҧ��ҵ{</a></li>
                        <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/back-end/verify_list/back-verify_list0.jsp">�ݼf��</a></li>
                        <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/back-end/verify_list/back-verify_list1.jsp">�Ҹꤤ</a></li>
                        <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/back-end/verify_list/back-verify_list4.jsp">�}�Ҥ�</a></li>
                        <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/back-end/verify_list/back-verify_list5.jsp">�w�U�[</a></li>
                        <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/back-end/verify_list/back-verify_list6.jsp">�w�h��</a></li>
                        <li class="page-item active" aria-current="page">
                            <span class="page-link">
                               ���e�f
                                <span class="sr-only">(current)</span>
                            </span>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
		
		
		
		<h1>���e�f�ҵ{�C��</h1>
		<table class="table table-striped">
			<tr>
				<th>�ҵ{�s��</th>
				<th>�ҵ{�W��</th>
				<th>�}�ҷ|��</th>
				<th>���A</th>
				<th>���O�s��</th>
				<th>��s�ɶ�</th>
				<th>��s�H��</th>
				<th>�Ա�</th>
			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="ciVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">

				<tr>
					<td>${ciVO.class_id}</td>
					<td>${ciVO.class_name}</td>
					<td>${ciVO.member_id}</td>
				<c:if test="${ciVO.class_status==0}">
			        <td>�Ю׫ݼf��</td>
			    </c:if>
			    <c:if test="${ciVO.class_status==1}">
			        <td>�Ҹꤤ </td>
			    </c:if>
			    <c:if test="${ciVO.class_status==2}">
			        <td>�v���ݼf��</td>
			    </c:if>
			    <c:if test="${ciVO.class_status==3}">
			        <td>�ǳƶ}��</td>
			    </c:if>
			    <c:if test="${ciVO.class_status==4}">
			        <td>�}�Ҥ�</td>
			    </c:if>
			    <c:if test="${ciVO.class_status==5}">
			        <td>�w�U�[</td>
			    </c:if>
			    <c:if test="${ciVO.class_status==6}">
			        <td>�h��</td>
			    </c:if>
			    <c:if test="${ciVO.class_status==7}">
			        <td>���e�f</td>
			    </c:if>
					
					<td>${ciVO.subclass_id}</td>
					<td><fmt:formatDate value="${ciVO.update_time}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${ciVO.admin_id}</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/verify_list/verify_listServlet"
							style="margin-bottom: 0px;">
							<input class="btn btn-danger" type="submit" value="�d��"> 
							<input type="hidden" name="class_id" value="${ciVO.class_id}">
							<input type="hidden" name="action" value="updateClassStatus">
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