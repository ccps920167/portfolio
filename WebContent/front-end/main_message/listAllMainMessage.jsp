<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.main_message.model.*"%>

<% 
   Main_messageService main_messageSvc = new Main_messageService();
   List<Main_messageVO> list = main_messageSvc.getAll();
   pageContext.setAttribute("list", list);
 %>

<!DOCTYPE html>
<html>
<head>

<title>�D�d��</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
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

<body bgcolor='white'>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ��D�d��</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/main_message/select_pageMainMessage.jsp"><img src="<%=request.getContextPath()%>/back-end/main_message/images/SeaOtterWait.jpg" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
<tr>
		<th>�j�d���s��</th>
		<th>�p�d���s��</th>
		<th>�|���s��</th>
		<th>�ɶ�</th>
		<th>�d����r</th>
		<th>�d���ӷ�</th>
		<th>�T����ܪ��A</th>
		
</tr>
<%@ include file="page1.file" %>
  <c:forEach var="main_messageVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
<tr>
			<td>${main_messageVO.mainmsg_id}</td>
			<td>${main_messageVO.class_id}</td>
			<td>${main_messageVO.member_id}</td>
			<td>${main_messageVO.mainmsg_time}</td>
			<td>${main_messageVO.mainmsg_text}</td>
			<td>${main_messageVO.msg_source}</td> 
 	        <td>${main_messageVO.msg_statusword}</td>			
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Main_message/Main_messageServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="mainmsg_id"  value="${main_messageVO.mainmsg_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Main_message/Main_messageServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="mainmsg_id"  value="${main_messageVO.mainmsg_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>



</body>
</html>