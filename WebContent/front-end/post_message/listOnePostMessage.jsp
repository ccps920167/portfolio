<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.post_message.model.*" %>

<!DOCTYPE html>
<% Post_messageVO post_messageVO = (Post_messageVO) request.getAttribute("post_messageVO"); %>

<html>
<head>

<title>���i�T��</title>
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
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>���i�T��</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/post_message/select_pagePostMessage.jsp"><img src="<%=request.getContextPath()%>/back-end/post_message/images/shout.gif" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>
<table>
	<tr>
	    <th>���i�s��</th>
		<th>���i���e</th>
		<th>���i�ɶ�</th>
		<th>���i�H��</th>
		<th>���i��H����</th>
	</tr>
	<tr>
	    <td><%=post_messageVO.getPost_id() %></td>
		<td><%=post_messageVO.getPost_content() %></td>
		<td><%=post_messageVO.getSend_time() %></td>
		<td><%=post_messageVO.getAdmin_id() %></td>
		<td><%=post_messageVO.getTarget_type() %></td>
	</tr>
</table>

<body>

</body>
</html>