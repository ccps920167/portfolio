<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ page import="com.main_message.model.*"%>

<% Main_messageVO main_messageVO = (Main_messageVO)  request.getAttribute("main_messageVO"); %>
<!-- main_messageServlet.java (Controller) �s�Jreq��emain_messageVO���� (�]�A�������X��main_messageVO, �]�]�A��J��ƿ��~�ɪ�main_messageVO����) -->


<!DOCTYPE html>
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

<title>�j�d���s���ק�</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>

<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>�j�d����ƭק� </h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/main_message/select_pageMainMessage.jsp"><img src="<%=request.getContextPath()%>/back-end/main_message/images/SeaOtterWait.jpg" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="/TEA102G5/Main_message/Main_messageServlet" name="form1">
<table>
	<tr>
		<td>�j�d���s��:<font color=red><b>*</b></font></td>
		<td><%=main_messageVO.getMainmsg_id()%></td>
	</tr>
	<tr>
		<td>���u�m�W:</td>
		<td><input type="TEXT" name="class_id" size="45" value="<%=main_messageVO.getClass_id()%>" /></td>
	</tr>
    <tr>
		<td>�|���s��:</td>
		<td><input type="TEXT" name="member_id" size="45" value="<%=main_messageVO.getMember_id()%>" /></td>
	</tr>
    <%-- <tr>
		<td>�d���ɶ�:</td>
		<td><input type="TEXT" name="mainmsg_time" size="45" value="<%=main_messageVO.getMainmsg_time()%>" /></td>
	</tr>--%>
	<tr>
		<td>�d�����e:</td>
		<td><input type="TEXT" name="mainmsg_text" size="45" value="<%=main_messageVO.getMainmsg_text()%>" /></td>
	</tr>
	<tr>
		<td>�d���ӷ�:</td>
		<td><input type="TEXT" name="msg_source" size="45" value="<%=main_messageVO.getMsg_source()%>" /></td>
	</tr>
	<tr>
		<td>�T����ܪ��A:</td>
		<td><input type="TEXT" name="msg_status" size="45" value="<%=main_messageVO.getMsg_status()%>" /></td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="mainmsg_id" value="<%=main_messageVO.getMainmsg_id()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>
</html>