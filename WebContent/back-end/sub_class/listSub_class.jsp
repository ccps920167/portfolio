<!-- 設定JSP編碼 -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<!-- 設定標籤庫 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- 設定import資料 -->
<%@ page import="java.util.*"%>
<%@ page import="com.sub_class.model.*"%>
<%@ page import="com.main_class.model.*"%>


<%
	Main_classService main_classSvc = new Main_classService();
	Sub_classService sub_classSvc = new Sub_classService();
	List<Sub_classVO> list = sub_classSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>

<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="/TEA102G5/vendors/bootstrap/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<link href="/TEA102G5/vendors/video-js/css/video-js.css"
	rel="stylesheet" />
<style>
/* 套用全部 */
* {
	box-sizing: border-box;
}
</style>
<title>所有副課程類別 - listAll.jsp</title>
</head>

<body bgcolor='white'>
	<div class="container">
		<h1>所有副課程類別</h1>
		<a href="<%=request.getContextPath()%>/back-end/sub_class/select_page.jsp">SubClass: Home</a>
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


		<table class="table table-striped">
			<tr>
				<th scope="col">#</th>
				<th scope="col">副課程編號</th>
				<th scope="col">副課程名稱</th>
				<th scope="col">副課程狀態</th>
				<th scope="col">所屬主課程</th>
				<th scope="col">查看詳情</th>
				<th scope="col">修改</th>
				<th scope="col">刪除</th>
			</tr>

			<!--分頁 -->
			<%@ include file="page1.file"%>

			<!--迴圈取出所有物件 -->
			<c:forEach var="sub_classVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>" varStatus="status">

				<tr>
					<th scope="row">${ status.index + 1}</th>
					<td>${sub_classVO.subClass_id}</td>
					<td>${sub_classVO.subClass_name}</td>
					<!--顯示取代 -->
					<c:if test="${fn:contains(sub_classVO.subClass_status, '1')}">
						<td>顯示</td>
					</c:if>
					<c:if test="${fn:contains(sub_classVO.subClass_status, '0')}">
						<td>隱藏</td>
					</c:if>
					<td>
						${sub_classVO.mainClass_id}
<%-- 						${main_classSvc.getOneMain_class(sub_classVO.mainClass_id).mainClass_name} --%>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="#"
							style="margin-bottom: 0px;">
							<input class="btn btn-primary " type="submit" value="查看詳情">
							<input type="hidden" name="subClass_id"
								value="${sub_classVO.subClass_id}"> <input
								type="hidden" name="action" value="#">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/Sub_class/Sub_classServlet"
							style="margin-bottom: 0px;">
							<input class="btn btn-primary " type="submit" value="修改">
							<input type="hidden" name="subClass_id"
								value="${sub_classVO.subClass_id}"> <input
								type="hidden" name="action" value="update_subClass_id">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/Sub_class/Sub_classServlet"
							"
							style="margin-bottom: 0px;">
							<input class="btn btn-primary " type="submit" value="刪除">
							<input type="hidden" name="subClass_id"
								value="${sub_classVO.subClass_id}"> <input
								type="hidden" name="action" value="delete_all">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>

		<%@ include file="page2.file"%>
	</div>
</body>

</html>