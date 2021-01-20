<%@page import="com.verify_list.model.Verify_listVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	Verify_listVO verify_listVO = (Verify_listVO) request.getAttribute("verify_listVO"); 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
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
	
	<form method="post" action="/TEA102G5/verify_list/verify_listServlet" name="form1">
		<table>
		    <tr>
		        <td>審核編號:</td>
		        <td><%=verify_listVO.getVerify_id()%></td>
		    </tr>
			<tr>
				<td>課程編號:</td>
				<td><input type="TEXT" name="class_id" size="45"
					value="<%=verify_listVO.getClass_id()%>" /></td>
			</tr>
			<tr>
				<td>審核人員:</td>
				<td><input type="TEXT" name="admin_id" size="45"
					value="<%=verify_listVO.getAdmin_id()%>" /></td>
			</tr>
			<tr>
				<td>審核紀錄:</td>
				<td><textarea rows="5" cols="50" name="class_check"><%=verify_listVO.getClass_check()%></textarea>
				</td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>上傳時間:</td> -->
<%-- 				<td><%=verify_listVO.getUpload_time()%></td> --%>
<%-- 				<td><input type="TEXT" name="upload_time" id="f_date1" value="<fmt:formatDate value="<%=verify_listVO.getUpload_time()%>" pattern="yyyy-MM-dd HH:mm:ss"/>"></td> --%>
<!-- 			</tr> -->

		</table>
<br>
<input type="hidden" name="action" value="updateOne">
<input type="hidden" name="verify_id" value="<%=verify_listVO.getVerify_id()%>">
<input type="submit" value="送出修改">
	</form>

	<h2>
		<a href="/TEA102G5/back-end/verify_list/Select_pageVL.jsp">回到首頁</a>
	</h2>

</body>
</html>