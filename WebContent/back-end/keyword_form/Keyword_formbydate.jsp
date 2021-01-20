<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.keyword_form.model.*"%>
<!DOCTYPE html>
<%
List<Keyword_formVO> list = (List<Keyword_formVO>) request.getAttribute("list");
// pageContext.setAttribute("list", list);
request.setAttribute("list", list);
%>


<html>
<head>
<meta charset="BIG5">
<title>listKeyword_formbydate</title>

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
		<h1>日期區間關鍵字清單</h1>
		<table class="table table-striped">
			<tr>
				<th>關鍵字編號</th>
			    <th>搜尋時間</th>
			    <th>輸入關鍵字</th>
<!-- 			    <th>修改</th> -->
			    <th>刪除</th>
			</tr>
			<%@ include file="page1_keywordbydate.file"%>
			
			<c:forEach var="list" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${list.keyword_id}</td>
				<td><fmt:formatDate value="${list.search_date}"
						pattern="yyyy-MM-dd" /></td>
				<td>${list.search_word}</td>
<!-- 				<td> -->
<!-- 					<FORM METHOD="post" -->
<%-- 						ACTION="<%=request.getContextPath()%>/keyword_form/Keyword_formServlet" --%>
<!-- 						style="margin-bottom: 0px;"> -->
<!-- 						<input type="submit" value="修改"> <input type="hidden" -->
<%-- 							name="keyword_id" value="${list.keyword_id}"> <input --%>
<!-- 							type="hidden" name="action" value="getOne_For_Update"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/keyword_form/Keyword_formServlet"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="keyword_id" value="${list_date.keyword_id}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
					
				</tr>
			</c:forEach>
			<td>
							<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/keyword_form/Keyword_formServlet">
				<h4>
					<input	type="hidden" name="action" value="getcount"> 
						<input type="submit" value="次數統計">
				</h4>
					</FORM>
					</td>
		</table>
		<%@ include file="page2_keywordbydate.file"%>

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