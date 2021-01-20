<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.keyword_form.model.*"%>
<!DOCTYPE html>
<%
Keyword_formService keyword_formSvc = new Keyword_formService();
List<Keyword_formVO> list = (List<Keyword_formVO>)keyword_formSvc.getAll();
pageContext.setAttribute("list", list);
%>


<html>
<head>
<meta charset="BIG5">
<title>TOMATO後台管理平台</title>s

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
		<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
		<div class="container">
		<h1>所有關鍵字搜尋列表</h1>
		<table class="table table-striped">
		<tr>
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/keyword_form/Keyword_formServlet" >
<ul>
  
  <li>
        <b>輸入開始日期:</b>
        <input name="startdate" id="startdate" type="text" autocomplete="off">
  </li>
  <li>
        <b>輸入結束日期:</b>
        <input name="enddate" id="enddate" type="text" autocomplete="off">
       
  </li>
  <br>
  <li>
   <input type="hidden" name="action" value="get_by_date">
        <input class="btn btn-danger" type="submit" value="查詢">
  </li>

<%--   <jsp:useBean id="keyword_formSvc" scope="page" class="com.keyword_form.model.Keyword_formService" /> --%>
		</ul>
		</FORM>
		</tr>
			<tr>
				<th>關鍵字編號</th>
			    <th>搜尋時間</th>
			    <th>輸入關鍵字</th>
			    <th>刪除</th>
			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="keyword_formVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">

				<tr>
				<td>${keyword_formVO.keyword_id}</td>
				<td><fmt:formatDate value="${keyword_formVO.search_date}"
						pattern="yyyy-MM-dd" /></td>
				<td>${keyword_formVO.search_word}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/keyword_form/Keyword_formServlet"
						style="margin-bottom: 0px;">
						<input class="btn btn-danger" type="submit" value="刪除"> <input type="hidden"
							name="keyword_id" value="${keyword_formVO.keyword_id}"> <input
							type="hidden" name="action" value="delete">
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
<!--  datetimepicker -->

<script>
$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function(){
	 $('#startdate').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
	   this.setOptions({
	    maxDate:$('#enddate').val()?$('#enddate').val():false
	   })
	  },
	  timepicker:false,
	  step: 1
	 });
	 
	 $('#enddate').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
	   this.setOptions({
	    minDate:$('#startdate').val()?$('#startdate').val():false
	   })
	  },
	  timepicker:false,
	  step: 1
	 });
});
</script>
<!---------------------------------------- script ---------------------------------------->

</body>
</html>