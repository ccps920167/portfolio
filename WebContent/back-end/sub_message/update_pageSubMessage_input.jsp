<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import= "com.sub_message.model.*" %>

<% Sub_messageVO sub_messageVO= (Sub_messageVO) request.getAttribute("sub_messageVO");%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

<title>�p�d���s���ק�</title>

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
		 <h3>�p�d����ƭק� </h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/sub_message/select_pageSubMessage.jsp"><img src="<%=request.getContextPath()%>/back-end/sub_message/images/SeaOtterWashface.gif" width="100" height="100" border="0">�^����</a></h4>
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

<FORM METHOD="post" ACTION="/TEA102G5/Sub_message/Sub_messageServlet" name="form1">
<table>
    <tr>
		<td>�p�d���s��:</td>
		<td><input type="TEXT" name="submsg_id" size="45" value="<%=sub_messageVO.getSubmsg_id()%>" readonly/>
		</td>
	</tr>
	<tr>
	    <td>�j�d���s��:</td>
	    <td><input type="TEXT" name="mainmsg_id" size="45" value="<%=sub_messageVO.getMainmsg_id()%>" readonly/>
		</td>
	</tr>
	<tr>
		<td>�|���s��:</td>
		<td><input type="TEXT" name="member_id" size="45" value="<%=sub_messageVO.getMember_id()%>" /></td>
	</tr>
	<tr>
		<td>���d�����e:</td>
		<td><input type="TEXT" name="submsg_text" size="45" value="<%=sub_messageVO.getSubmsg_text()%>" /></td>
	</tr>
	<tr>
		<td>�T����ܪ��A:</td>
		<td>
		<input type="TEXT" name="submsg_status" size="45" value="<%=sub_messageVO.getSubmsg_status()%>" />
		</td>
	</tr>
</table>
<br>
     <input type="hidden" name="action" value="update">
     <input type="hidden" name="submsg_id" value="<%=sub_messageVO.getSubmsg_id()%>">
     <input type="submit" value="�e�X�ק�"></FORM>
</body>
</html>