<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.sub_message.model.*" %>


<!DOCTYPE html>

<% Sub_messageVO sub_messageVO= (Sub_messageVO) request.getAttribute("sub_messageVO"); %>
<html>
<head>

<title>���d��</title>


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
		 <h3>���d��</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/sub_message/select_pageSubMessage.jsp"><img src="<%=request.getContextPath()%>/back-end/sub_message/images/SeaOtterShock.jpg" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>
<table>
	<tr>
	    <th>�p�d���s��</th>
		<th>�j�d���s��</th>
		<th>�|���s��</th>
		<th>�ɶ�</th>
		<th>���d�����e</th>
		<th>�T����ܪ��A</th>
	</tr>
	<tr>
		<td><%=sub_messageVO.getSubmsg_id()%></td> 
		<td><%=sub_messageVO.getMainmsg_id()%></td>
		<td><%=sub_messageVO.getMember_id()%></td>
		<td><%=sub_messageVO.getSubmsg_time()%></td>
		<td><%=sub_messageVO.getSubmsg_text()%></td>
		<td><%=sub_messageVO.getSubmsg_status()%></td>
		
	</tr>
</table>

</body>
</html>