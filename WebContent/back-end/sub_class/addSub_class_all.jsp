<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sub_class.model.*"%>
<%@ page import="com.main_class.model.*"%>

<!--引入傳過來的物件 -->
<%
	List<String> errorMsgs = (List) request.getAttribute("errorMsgs");
	Main_classVO main_classVO = (Main_classVO) request.getAttribute("main_classVO");
%>

<!-- HTML語法 -->
<!doctype html>
<html lang="en">

<head>

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<link href="<%=request.getContextPath()%>/vendors/video-js/css/video-js.css"
	rel="stylesheet" />
<style>
/* 套用全部 */
* {
	box-sizing: border-box;
}

.btn_del {
	width: 50px;
	padding: 3px;
	display: inline-block;
}

.form-group {
	border: 1px rgb(204, 204, 204) solid;
}
</style>
<title>副課程類別 新增</title>
</head>

<body>
	<!-- container -->
	<div class="container">
		<h1>副課程類別 新增</h1>
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
		<form method="POST" action="<%=request.getContextPath()%>/Sub_class/Sub_classServlet">

			<div class="form-group ">
				<div class="row">
					<div class="col-sm-6" style="margin-top: 10px;">

						<jsp:useBean id="mainClassSvc" scope="page"
							class="com.main_class.model.Main_classService" />
							
						<ul class="sub_class_list">
							<li>副課程名稱<br> <input name="subClass_name" type="text"
								class="form-control subClass_name"
								style="display: inline-block; width: 200px;">
								<div style="display: inline-block; width: 50px;"></div> <br>
								<label for="subClass_status">副課程類別狀態</label> <select
								class="form-control" name="subClass_status">
									<option value="0">隱藏</option>
									<option value="1">顯示</option>
							</select> 主課程類別: <select class="form-control" size="1" name="mainClass_id">
									<c:forEach var="mainClassVO"
										items="${mainClassSvc.main_classAll}">
										<option value="${mainClassVO.mainClass_id}">${mainClassVO.mainClass_name}
									</c:forEach>
							</select>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<input type="hidden" name="action" value="insert_all">
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
	<!-- JavaScript -->
	<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/video-js/js/video.js"></script>



</body>

</html>