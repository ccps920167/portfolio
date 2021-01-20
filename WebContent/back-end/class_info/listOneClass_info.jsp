<%@page import="java.sql.Timestamp"%>
<%@page import="com.class_info.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%
	Class_infoVO class_infoVO = (Class_infoVO) request.getAttribute("Class_infoVO");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>listOneClass_info</title>

<style>
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

	<%-- ���~��C --%>
	<c:if test="${not empty erMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${erMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<form method="post" action="<%=request.getContextPath()%>/class_info/class_infoServlet"
		name="form1">
		<table>
		    <tr>
		        <th>�ҵ{�s��</th>
		        <th>�ҵ{�W��</th>
        	    <th>�|���s��</th>
		        <th>�ҵ{���A</th>
		        <th>�ƽҵ{���O�s��</th>
		        <th>�}�l�Ҹ�ɶ�</th>
		        <th>�}�Үɶ�</th>
		        <th>�ҵ{�y�z</th>
		        <th>�ҵ{�ʭ��Ϥ�</th>
	        	<th>�Ҹ���</th>
	        	<th>�w��</th>
		        <th>���e�H��</th>
		        <th>�ҵ{����</th>
        		<th>�Ҹ�v��</th>
		        <th>��s�ɶ�</th>
        		<th>��s�H��</th>
		    </tr>
		        <tr>
    		    <td><%=class_infoVO.getClass_id()%></td>
	    		<td><%=class_infoVO.getClass_name()%></td>
	    		<td><%=class_infoVO.getMember_id()%></td>
	    		<td><%=class_infoVO.getClass_status()%></td>
	    		<td><%=class_infoVO.getSubclass_id()%></td>
				<td><fmt:formatDate value="<%=class_infoVO.getStartfund_date()%>" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="<%=class_infoVO.getStartclass_time()%>" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><%=class_infoVO.getClass_description()%></td>
				<td><img src="data:image;base64,<%=class_infoVO.getClass_picture2()%>"></td> 
				<td><%=class_infoVO.getStartfund_price()%></td>
				<td><%=class_infoVO.getOriginal_price()%></td>
				<td><%=class_infoVO.getPeople_threshold()%></td>
				<td><%=class_infoVO.getClass_length()%></td>
				<td><img src="data:image;base64,<%=class_infoVO.getVideo_fundraising2()%>"></td>
				<td><fmt:formatDate value="<%=class_infoVO.getUpdate_time()%>" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><%=class_infoVO.getAdmin_id()%></td>
		    </tr>
		</table>
	</form>

	<h2>
		<a href="<%=request.getContextPath()%>/back-end/class_info/select_pageClass_info.jsp">�^�쭺��</a>
	</h2>
</body>

</html>