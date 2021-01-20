<!-- 設定JSP編碼 -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<!-- 設定標籤庫 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- 設定import資料 -->
<%@ page import="java.util.*"%>
<%@ page import="com.sub_class.model.*"%>


<%
	Sub_classVO sub_classVo = (Sub_classVO) request.getAttribute("sub_classVO");
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

<title>副課程類別資料 - listOneMain_class.jsp</title>

</head>
<body bgcolor='white'>
	<div class="container">
		<h1>SubClass: listOneMain_class</h1>
		<table class="table table-striped">
			<tr>
				<th scope="col">#</th>
				<th scope="col">副課程編號</th>
				<th scope="col">副課程名稱</th>
				<th scope="col">副課程狀態</th>
				<th scope="col">所屬主類別</th>
				<th scope="col">查看詳情所屬課程</th>
				<th scope="col">修改</th>
				<th scope="col">刪除</th>
			</tr>

			<tr>
				<th scope="row">1</th>
				<td><%=sub_classVo.getSubClass_id()%></td>
				<td><%=sub_classVo.getSubClass_name()%></td>
				<!--顯示取代 -->
				<td><%=sub_classVo.getSubClass_status()%></td>
<%-- 				<c:if test="${fn:contains(sub_classVo.subClass_status, '1')}"> --%>
<!-- 					<td>顯示</td> -->
<%-- 				</c:if> --%>
<%-- 				<c:if test="${fn:contains(sub_classVo.subClass_status, '0')}"> --%>
<!-- 					<td>隱藏</td> -->
<%-- 				</c:if> --%>
				<td><%=sub_classVo.getMainClass_id()%></td>
				
		

				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Sub_class/Sub_classServlet"
						"
							style="margin-bottom: 0px;">
						<input class="btn btn-primary " type="submit" value="查看詳情">
						<input type="hidden" name="mainClass_id"
							value="${main_classVO.mainClass_id}"> <input
							type="hidden" name="action" value="查看詳情方法">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Sub_class/Sub_classServlet"
						style="margin-bottom: 0px;">
						<input class="btn btn-primary " type="submit" value="修改">
						<input type="hidden" name="mainClass_id"
							value="${main_classVO.mainClass_id}"> <input
							type="hidden" name="action" value="update_mainClass_id">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Sub_class/Sub_classServlet"
						style="margin-bottom: 0px;">
						<input class="btn btn-primary " type="submit" value="刪除">
						<input type="hidden" name="mainClass_id"
							value="${main_classVO.mainClass_id}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>

		</table>
	</div>
</body>
</html>