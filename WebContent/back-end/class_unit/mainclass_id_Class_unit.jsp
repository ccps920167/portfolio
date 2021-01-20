<!-- 設定JSP編碼 -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<!-- 設定標籤庫 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- 設定import資料 -->
<%@ page import="java.util.*"%>
<%@ page import="com.main_class.model.*"%>
<%@ page import="com.sub_class.model.*"%>


<%
	Main_classService main_classSvc = new Main_classService();
	Main_classVO main_classVO = (Main_classVO) request.getAttribute("main_classVO");
	Set<Sub_classVO> list = (Set) request.getAttribute("Sub_classVO_list");
	pageContext.setAttribute("list", list);
%>


<html>

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
</style>
<title>主課程類別內副課程類別 - Sub_classVO_list.jsp</title>
</head>

<body bgcolor='white'>
	<div class="container">
		<h1>【<%=main_classVO.getMainClass_name()%>】內副課程類別</h1>
	<a href="<%=request.getContextPath()%>/back-end/class_unit/select_page.jsp">class_unit: Home</a>
		<br>
	
		<FORM METHOD="post"
			ACTION="<%=request.getContextPath()%>/Class_unit/Class_unitServlet"
			style="margin-bottom: 0px;">
			<input class="btn btn-primary " type="submit" value="新增"> <input
				type="hidden" name="mainClass_id" value="<%=main_classVO.getMainClass_id()%>"> <input
				type="hidden" name="mainClass_name" value="<%=main_classVO.getMainClass_name()%>">
			<input type="hidden" name="action" value="add_SubClass">
		</FORM>
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
				<th scope="col">查看詳情所屬課程</th>
				<th scope="col">修改</th>
				<th scope="col">刪除</th>
			</tr>



			<%
				int rowsPerPage = 3; //每頁的筆數    
				int rowNumber = 0; //總筆數
				int pageNumber = 0; //總頁數      
				int whichPage = 1; //第幾頁
				int pageIndexArray[] = null;
				int pageIndex = 0;

				rowNumber = list.size();
				if (rowNumber % rowsPerPage != 0)
					pageNumber = rowNumber / rowsPerPage + 1;
				else
					pageNumber = rowNumber / rowsPerPage;
			%>

			<b>●符 合 查 詢 條 件 如 下 所 示: 共<font color=red><%=rowNumber%></font>筆
			</b>
			<!--分頁 -->
			<%-- 			<%@ include file="page1.file"%> --%>

			<!--迴圈取出所有物件 -->
			<c:forEach var="sub_classVO" items="${list}" varStatus="status">
				<%
					for (Sub_classVO sub_classVO : list)
				%>
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
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/Class_unit/Class_unitServlet"
							style="margin-bottom: 0px;">
							<input class="btn btn-primary " type="submit" value="查看詳情">
							<input type="hidden" name="mainClass_id"
								value="${sub_classVO.subClass_id}"> <input type="hidden"
								name="action" value="mainclass_id_sub_class">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/Class_unit/Class_unitServlet"
							style="margin-bottom: 0px;">
							<input class="btn btn-primary " type="submit" value="修改">
							<input type="hidden" name="subClass_id"
								value="${sub_classVO.subClass_id}"> <input type="hidden"
								name="action" value="update_subClass_id">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/Class_unit/Class_unitServlet"
							style="margin-bottom: 0px;">
							<input type="hidden" name="subClass_id"
								value="${sub_classVO.subClass_id}"> 
							<input type="hidden"
								name="mainClass_name" value="<%=main_classVO.getMainClass_name()%>"> <input
								type="hidden" name="mainClass_id" value="<%=main_classVO.getMainClass_id()%>">
							<input type="hidden" name="action" value="delete"> <input
								class="btn btn-primary " type="submit" value="刪除">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>

		<%-- 		<%@ include file="page2.file"%> --%>
	</div>
</body>

</html>