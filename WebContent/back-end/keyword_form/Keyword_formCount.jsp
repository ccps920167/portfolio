<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.keyword_form.model.*"%>
<!DOCTYPE html>
<%
Map<String,Integer>map= (Map<String,Integer>)request.getAttribute("map");

pageContext.setAttribute("map", map);

// Keyword_formService keyword_formSvc = new Keyword_formService();
// List<Keyword_formVO> list = (List<Keyword_formVO>)keyword_formSvc.getAll();
// pageContext.setAttribute("list", list);
%>


<html>
<head>
<meta charset="BIG5">
<title>listAllVerifyClass_info</title>

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

	<div style="margin-left: 250px; margin-right: 20px;">
		<nav aria-label="breadcrumb" style="margin-top: 30px;">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Home</a></li>
				<li class="breadcrumb-item active" aria-current="page">數據管理</li>
				<li class="breadcrumb-item active" aria-current="page"><a href="<%=request.getContextPath()%>/back-end/keyword_form/Keyword_formAll.jsp">關鍵字分析</a></li>
<%-- 				<li class="breadcrumb-item active" aria-current="page"><a href="<%=request.getContextPath()%>/back-end/keyword_form/Keyword_formbydate.jsp">關鍵字區間查詢結果</a></li> --%>
<%-- 				<li class="breadcrumb-item active" aria-current="page"><a href="<%=request.getContextPath()%>/back-end/keyword_form/Keyword_formCount.jsp">關鍵字次數統計</a></li> --%>
			</ol>
		</nav>
		<br>
		<div class="container">
		<h1>關鍵字次數統計</h1>
		<table class="table table-striped">
			<tr>
				<th>關鍵字</th>
				<th>搜尋次數</th>
			</tr>
			<%@ include file="page1_keywordbycount.file"%>
			<c:forEach var="keyword_formVO" items="${map}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">

				<tr>
					<td>${keyword_formVO.key}</td>
					<td>${keyword_formVO.value}</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2_keywordbycount.file"%>

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