<%@ page contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.post_message.model.*"%>

<% Post_messageVO post_messageVO = (Post_messageVO) request.getAttribute("post_messageVO"); %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

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
		 <h3>���i�T���ק� </h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/main_message/select_pageMainMessage.jsp"><img src="<%=request.getContextPath()%>/back-end/main_message/images/SeaOtterWait.jpg" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>���i�T���ק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="/TEA102G5/Post_message/Post_messageServlet" name="form1">
<table>
       <tr>
          <td>���i�s��:<font color=red><b>*</b></font></td>
          <td><%=post_messageVO.getPost_id()%></td>
       </tr>
       <tr>
          <td>���i���e:</td>
          <td><input type="TEXT" name="post_content" size="45" 
               value="<%=post_messageVO.getPost_content()%>" /></td>
       </tr>
       <tr>
          <td>���i�H��:</td>
          <td><input type="TEXT" name="admin_id" size="45"
			   value="<%=post_messageVO.getAdmin_id()%>" /></td>
       </tr>
       <tr>
          <td>���i��H����:</td>
          <td><input type="radio" name="target_type" size="45" value="o">�Ѯv
			  <input type="radio" name="target_type" size="45" value="1">�ǥ� 
		  </td> 
       </tr>
 

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="post_id" value="<%=post_messageVO.getPost_id()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>
</html>