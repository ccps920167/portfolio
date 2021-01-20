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
		<h1>【<%=main_classVO.getMainClass_name() %>】副課程類別 新增</h1>
			<a href="<%=request.getContextPath()%>/back-end/class_unit/select_page.jsp">class_unit: Home</a>
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
		<form method="POST" action="<%=request.getContextPath()%>/Class_unit/Class_unitServlet">
			<div class="form-group">
				<label for="exampleInputPassword1">副課程類別名稱</label>
				<button type="button" class="btn btn-primary subClass_add"
					style="padding: 3px;">+</button>
				<input type="text" class="form-control subClass_name"
					id="exampleInputPassword1" placeholder="輸入副課程類別名稱">
			</div>
			<div class="form-group ">
				<div class="row">
					<div class="col-sm-6" style="margin-top: 50px;">
						副課程類別
						<ul class="sub_class_list"></ul>
					</div>
				</div>
			</div>
			<input type="hidden" name="mainClass_id" value="<%=main_classVO.getMainClass_id() %>">
			<input type="hidden" name="mainClass_name" value="<%=main_classVO.getMainClass_name() %>">
			<input type="hidden" name="action" value="insert">
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
	<!-- JavaScript -->
	<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/video-js/js/video.js"></script>
	<script>
		$("button.subClass_add").on(
				"click ",
				function(event) {
					if ($("input.subClass_name").val() != "") {
						input($("input.subClass_name"), $("ul.sub_class_list"),
								"subClass_name");
					}
					;
				});

		// 按下「移除」按鈕
		$("ul.sub_class_list").on("click", "button.btn_del", function() {
			$(this).closest("li").remove();
		});

		function input(e, list, name) {
			var li = "<li>"
					+ "<input name=\""
					+ name
					+ "\" type=\"text\" class=\"form-control subClass_name \" value=\""
					+ e.val().trim()
					+ "\" style=\"display: inline-block; width: 200px;\">"
					+ "<div  style=\"display: inline-block;  width: 50px;\">"
					+ "<button type=\"button\" class=\"btn_del btn btn-primary \"\" >移除</button>"
					+ "</div>"
					+ "<label for=\"exampleInputPassword1\">副課程類別狀態</label>"
					+ "<select class=\"form-control\" name=\"subClass_status\">"
					+ "<option value=\"0\">隱藏</option>"
					+ "<option value=\"1\">顯示</option>"
					+ "</select>"
					+ "<button type=\"button\" class=\"btn_del btn btn-primary \"\" >移除</button>"
					+ "</li>";
			list.append(li);
			e.val("");
		}
	</script>

</body>

</html>