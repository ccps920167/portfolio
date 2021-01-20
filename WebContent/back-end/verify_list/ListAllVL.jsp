<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.verify_list.model.*"%>
<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@page import="com.verify_list.model.Verify_listVO"%>
<!DOCTYPE html>
<%
	Verify_listService vlSvc = new Verify_listService(); 
	List<Verify_listVO> list = vlSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>

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

	<table>
		<tr>
			<th>�f�ָ�ƽs��</th>
			<th>�ҵ{�s��</th>
			<th>�f�֤H��</th>
			<th>�f�֬���</th>
			<th>�W�Ǯɶ�</th>
			<th>�ק�</th>
			<th>�R��</th>
			
		</tr>
<% 
//java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//String formatDate = df.format(new java.util.Date());
//out.println(formatDate);
//new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(verify_listVO.getUpload_time()); 
%>
			<%
				for (Verify_listVO verify_listVO : list) {
					
			%>
		<tr>
			<td><%=verify_listVO.getVerify_id()%></td>
			<td><%=verify_listVO.getClass_id()%></td>
			<td><%=verify_listVO.getAdmin_id()%></td>
			<td><%=verify_listVO.getClass_check()%></td>
			<td><%=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(verify_listVO.getUpload_time())%></td>

			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/verify_list/verify_listServlet">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="verify_id"  value="<%=verify_listVO.getVerify_id()%>">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			  </FORM>
			</td>
			 
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/verify_list/verify_listServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="verify_id"  value="<%=verify_listVO.getVerify_id()%>">
			    
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
			<%
				}
			%>
			
		</tr>
	</table>

	<h2>
		<a href="Select_pageVL.jsp">�^�쭺��</a>
	</h2>

</body>
</html>