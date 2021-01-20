<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css"
		integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	<link href="<%=request.getContextPath()%>/vendors/video-js/css/video-js.css" rel="stylesheet" />
	<style>
		/* 套用全部 */
		* {
			box-sizing: border-box;
		}
	</style>
<title>MainClass: Home</title>

</head>
<body bgcolor='white'>
	<div class="container">
	<h1>MainClass: Home</h1>
	<a href="<%=request.getContextPath()%>/back-end/main_class/select_page.jsp">MainClass: Home</a>
		<br>
	<!--如果req屬性內有存在errorMsgs，引進屬性內資料 -->
	<%-- 錯誤表列 --%>
	<%--<c:if test="條件式"> --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<!-- ========================錯誤列表結束============================= -->
	<div>
		<div class="btn-group" role="group" aria-label="Basic example" style="margin-bottom: 50px;">
		<form action="<%=request.getContextPath()%>/Main_class/Main_classServlet" method="POST">
			<button type="button" class="btn btn-secondary" >查詢單筆</button>
			<button type="submit" class="btn btn-secondary" name="action" value="getALL">查詢全部</button>
			<button type="submit" class="btn btn-secondary" name="action" value="addnew">新增資料</button>
		</form>
		</div>
		<form action="<%=request.getContextPath()%>/Main_class/Main_classServlet" method="POST">
			<h2>查詢單筆資料</h2>
			<Label for="mainClass_id">主課程類別編號</Label>
			<input name="mainClass_id" type="text" placeholder="輸入主課程類別編號" >
			<br>
			<Label for="mainClass_name">主課程類別名稱</Label>
			<input name="mainClass_name" type="text" placeholder="輸入主課程類別名稱" >
			<br>
			<Label for="mainClass_status">輸入主課程類別狀態</Label>
			<input name="mainClass_status" type="text" placeholder="輸入主課程類別狀態" >
			<br>
			<input type="hidden" name="action" value="listEmps_ByCompositeQuery">
			<input class="btn btn-primary" type="submit" value="送出查詢">
		</form>
	</div>
	<div></div>


</body>
</html>