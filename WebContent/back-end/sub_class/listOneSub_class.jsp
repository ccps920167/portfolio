<!-- �]�wJSP�s�X -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<!-- �]�w���Үw -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- �]�wimport��� -->
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
/* �M�Υ��� */
* {
	box-sizing: border-box;
}
</style>

<title>�ƽҵ{���O��� - listOneMain_class.jsp</title>

</head>
<body bgcolor='white'>
	<div class="container">
		<h1>SubClass: listOneMain_class</h1>
		<table class="table table-striped">
			<tr>
				<th scope="col">#</th>
				<th scope="col">�ƽҵ{�s��</th>
				<th scope="col">�ƽҵ{�W��</th>
				<th scope="col">�ƽҵ{���A</th>
				<th scope="col">���ݥD���O</th>
				<th scope="col">�d�ݸԱ����ݽҵ{</th>
				<th scope="col">�ק�</th>
				<th scope="col">�R��</th>
			</tr>

			<tr>
				<th scope="row">1</th>
				<td><%=sub_classVo.getSubClass_id()%></td>
				<td><%=sub_classVo.getSubClass_name()%></td>
				<!--��ܨ��N -->
				<td><%=sub_classVo.getSubClass_status()%></td>
<%-- 				<c:if test="${fn:contains(sub_classVo.subClass_status, '1')}"> --%>
<!-- 					<td>���</td> -->
<%-- 				</c:if> --%>
<%-- 				<c:if test="${fn:contains(sub_classVo.subClass_status, '0')}"> --%>
<!-- 					<td>����</td> -->
<%-- 				</c:if> --%>
				<td><%=sub_classVo.getMainClass_id()%></td>
				
		

				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Sub_class/Sub_classServlet"
						"
							style="margin-bottom: 0px;">
						<input class="btn btn-primary " type="submit" value="�d�ݸԱ�">
						<input type="hidden" name="mainClass_id"
							value="${main_classVO.mainClass_id}"> <input
							type="hidden" name="action" value="�d�ݸԱ���k">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Sub_class/Sub_classServlet"
						style="margin-bottom: 0px;">
						<input class="btn btn-primary " type="submit" value="�ק�">
						<input type="hidden" name="mainClass_id"
							value="${main_classVO.mainClass_id}"> <input
							type="hidden" name="action" value="update_mainClass_id">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Sub_class/Sub_classServlet"
						style="margin-bottom: 0px;">
						<input class="btn btn-primary " type="submit" value="�R��">
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