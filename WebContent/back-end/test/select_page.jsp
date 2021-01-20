<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>

<title>考試:首頁</title>
<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
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

</head>
<body bgcolor='white'>
	<table id="table-1">
		<tr>
			<td><h3>Test:Home</h3></td>
		</tr>
	</table>

	<p>這是考試的首頁</p>

	<h3>資料查詢:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='listAllTest.jsp'>列出全部考試</a> <br>
		<br></li>


		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/test/testServlet">
				<b>輸入考試編號 (如TU00001):</b> <input type="text" name="test_id">
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="testSvc" scope="page"
			class="com.test.model.TestService" />

		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/test/testServlet">
				<b>選擇考試編號:</b> <select size="1" name="test_id">
					<c:forEach var="testVO" items="${testSvc.all}">
						<option value="${testVO.test_id}">${testVO.test_id}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

	</ul>


	<h3>考試管理</h3>

	<ul>
		<li><a href='<%=request.getContextPath()%>/back-end/test/addTest.jsp'>新增考試</a></li>
	</ul>



</body>
</html>