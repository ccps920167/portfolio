<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.main_class.model.*"%>

<%
	String mainClass_id = (String) request.getAttribute("mainClass_id");
	String mainClass_name = (String) request.getAttribute("mainClass_name");
%>


<!doctype html>
<html lang="en">

<head>

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">

<!-- Bootstrap CSS -->
<!-- <link rel="stylesheet" -->
<%-- 	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css" --%>
<!-- 	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" -->
<!-- 	crossorigin="anonymous"> -->
<%-- <link href="<%=request.getContextPath()%>/vendors/video-js/css/video-js.css" --%>
<!-- 	rel="stylesheet" /> -->
	
<!-- 列表CSS -->	
<link href="<%=request.getContextPath()%>/css/back-end/icon.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/back-end/public.css" rel="stylesheet" type="text/css" />

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




</style>
<title>主課程類別 新增</title>
</head>

<body>
	<%@ include file="header-back.jsp"%>
	<!-- container -->

		<h1>新增主課程類別</h1>
		<a
			href="<%=request.getContextPath()%>/back-end/main_class/select_page.jsp">MainClass:
			Home</a> <br>
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

		<form method="POST" action="<%=request.getContextPath()%>/Main_class/Main_classServlet">
			<div class="form-group">
				<label for="exampleInputEmail1">主課程類別名稱</label>
				<button type="button" class="btn btn-primary mainClass_add"
					style="padding: 3px;">+</button>
				<input type="text" class=" mainClass_name"
					placeholder="輸入主課程類別名稱">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">副課程類別名稱</label>
				<button type="button" class="btn btn-primary subClass_add"
					style="padding: 3px;">+</button>
				<input type="text" name="subClass_name"
					class=" subClass_name" id="exampleInputPassword1"
					placeholder="輸入副課程類別名稱">
			</div>
			<ul class="main_class_list">
			
			</ul>
			<ul class="sub_class_list">
			
			</ul>
			<input type="hidden" name="action" value="insert">
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>


  </body>
	<!-- JavaScript -->
	<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/video-js/js/video.js"></script>
	<script src="<%=request.getContextPath()%>/js/back-end/public.js" language="javascript" type="text/javascript" ></script>
	
	
	<script>
		$("button.mainClass_add").on("click ", function(event) {
			$(".mainClass_add").attr("disabled", true);
		});

		$("button.mainClass_add").on(
				"click ",
				function(event) {
					if ($("input.mainClass_name").val() != "") {
						input($("input.mainClass_name"),
								$("ul.main_class_list"), "mainClass_name");
					}
					;
				});
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
		$("ul.main_class_list").on("click", "button.btn_del", function() {
			$(this).closest("li").remove();
		});
		// 按下「移除」按鈕
		$("ul.sub_class_list").on("click", "button.btn_del", function() {
			$(this).closest("li").remove();
		});

		function input(e, list, name) {
			var li = "<li>"
					+ "<input name=\""
					+ name
					+ "\" type=\"text\" class=\"form-control mainClass_name \" value=\""
					+ e.val().trim()
					+ "\" style=\"display: inline-block; width: 200px;\">"
					+ "<div  style=\"display: inline-block;  width: 50px;\">"
					+ "<button type=\"button\" class=\"btn_del btn btn-primary \"\" >移除</button>"
					+ "</div>" + "</li>";
			list.append(li);
			e.val("");
		}
	</script>

</body>

</html>