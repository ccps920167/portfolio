
<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>


<html>
<head>
<meta charset="BIG5">
<title>Select_pageVL</title>

</head>
<body>
	<ul>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty erMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${erMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
		<li>
			<h2>這是Verify_list的首頁</h2>
		</li>
		<li>
			<h4>資料查詢</h4>
		</li>
		<li>
			<h5>
				列出所有審核紀錄 <a href="<%=request.getContextPath()%>/back-end/verify_list/ListAllVL.jsp">ListAll</a>
			</h5>
		</li>
		<li>
			<h5>
				新增審核紀錄 <a href="addVL.jsp">新增審核紀錄</a>
			</h5>
		</li>
		<li>
			<h5>查詢單筆資料</h5>
			<form method="post" action="/TEA102G5/verify_list/verify_listServlet">
				<p>輸入審核資料編號</p>
				<input type="text" name="verify_id"> 
				<input type="hidden" name="action" value="SearchOne"> 
				<input type="submit" value="送出">
			</form>
		</li>
	</ul>

</body>
</html>