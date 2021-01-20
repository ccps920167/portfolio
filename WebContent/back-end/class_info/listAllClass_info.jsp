<%@page import="com.class_info.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<% 
List <String> erMsgs = (List)request.getAttribute("erMsgs");
Class_infoService ciSvc = new Class_infoService();
List <Class_infoVO> list = ciSvc.getAll();
pageContext.setAttribute("list", list);
%>


<html>
<head>
<meta charset="BIG5">
<title>listAllClass_info</title>

<style>

img{
	width: 50px;
}
table {
	width: 600px;
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
<body>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty erMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${erMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

<table>
	<tr>
		<th>課程編號</th>
		<th>課程名稱</th>
		<th>會員編號</th>
		<th>課程狀態</th>
		<th>副課程類別編號</th>
		<th>開始募資時間</th>
		<th>開課時間</th>
		<th>課程描述</th>
		<th>課程封面圖片</th>
		<th>募資售價</th>
		<th>定價</th>
		<th>門檻人數</th>
		<th>課程長度</th>
		<th>募資影片</th>
		<th>更新時間</th>
		<th>更新人員</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
		<c:forEach var="ciVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${ciVO.class_id}</td>
			<td>${ciVO.class_name}</td>
			<td>${ciVO.member_id}</td>
			<td>${ciVO.class_status}</td>
			<td>${ciVO.subclass_id}</td>
			<td><fmt:formatDate value="${ciVO.startfund_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td><fmt:formatDate value="${ciVO.startclass_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${ciVO.class_description}</td>
			<td><img src="<%=request.getContextPath()%>/class_info/class_infoServlet?action=class_pic&class_id=${ciVO.class_id}"></td>
			<td>${ciVO.startfund_price}</td>
			<td>${ciVO.original_price}</td>
			<td>${ciVO.people_threshold}</td>
			<td>${ciVO.class_length}</td>
			<td>
			<video id="video"
				data-id="${class_unitsVO.unit_id}"
				class="video-js vjs-fluid " controls
				preload="auto" data-setup="{}" style="width: 200px;" >
				<source id="video_url"
					src="<%=request.getContextPath()%>/class_info/class_infoServlet?action=class_video&class_id=${ciVO.class_id}"
					type="video/mp4" />
			</video>
			</td>
			<td><fmt:formatDate value="${ciVO.update_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${ciVO.admin_id}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/class_info/class_infoServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="class_id"  value="${ciVO.class_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			  </FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/class_info/class_infoServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="class_id"  value="${ciVO.class_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

	<h2>
		<a href="<%=request.getContextPath()%>/back-end/class_info/select_pageClass_info.jsp">回到首頁</a>
	</h2>

</body>
</html>