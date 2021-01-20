<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.test.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    TestService testSvc = new TestService();
    List<TestVO> list = testSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<html>
<head>

<title>所有考試</title>
<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>

img{
	height: 50px;
}
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}

</style>
</head>
<body bgcolor='white'>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有考試資料</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/test/select_page.jsp"><img src="<%=request.getContextPath()%>/back-end/test/images/tomato.gif"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<table>
		<tr>
			<th>考試編號編號</th>
			<th>課程單元編號</th>
			<th>題目解答</th>
			<th>題目內容</th>
			<th>測驗選項A</th>
			<th>測驗選項B</th>
			<th>測驗選項C</th>
			<th>測驗選項D</th>

		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="testVO" items="${list}"
			begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${testVO.test_id}</td>
				<td>${testVO.unit_id}</td>
				<td>${testVO.test_answer}</td>
				<td>${testVO.test_content}</td>
				<td>${testVO.opta}</td>
				<td>${testVO.optb}</td>
				<td>${testVO.optc}</td>
				<td>${testVO.optd}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/test/testServlet"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> 
						<input type="hidden" name="test_id" value="${testVO.test_id}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/test/testServlet"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> 
						<input type="hidden" name="test_id" value="${testVO.test_id}"> 
						<input type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>
</body>




</html>