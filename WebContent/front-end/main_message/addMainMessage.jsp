<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.main_message.model.*"%>

<%
   Main_messageVO main_messageVO = (Main_messageVO) request.getAttribute("main_messageVO");
%>

<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
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
		 <h3>�D�d���s�W </h3></td><td>
		 <h4><a href="select_pageMainMessage.jsp"><img src="images/SeaOtterCute.jpg" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<h1>�ҵ{�o��</h1>

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
     <td>�ҵ{�s��:</td>
     <td><input type="TEXT" name="class_id" size="45"
			   value="<%= (main_messageVO==null)? "CLA00000" : main_messageVO.getClass_id()%>" /></td>
   </tr>		 
   
   <tr>
     <td>�|���s��:</td>
     <td><input type="TEXT" name="member_id" size="45"
		        value="<%= (main_messageVO==null)? "MEM00000" : main_messageVO.getMember_id()%>" /></td>
   </tr>

    <tr>
     <td>�d�����e:</td>
     <td><input type="TEXT" name="mainmsg_text" size="45"
			    value="<%= (main_messageVO==null)? "" : main_messageVO.getMainmsg_text()%>" /></td>
    </tr>		 
	
    <tr>			 
      <td>�d���ӷ�:</td>
      <td><input type="radio" name="msg_source" size="45" value="�ҵ{����">�ҵ{����
	      <input type="radio" name="msg_source" size="45" value="�ǲ߭���">�ǲ߭��� </td>
	
    </tr>		 
	
     <tr>
      <td>�T����ܪ��A:</td>
      <td><input type="radio" name="msg_status" size="45" value="0">����
          <input type="radio" name="msg_status" size="45" value="1">���
          
      </td>
     </tr>	 
	
 
 </table>
  <br>
    <input type="hidden" name="action" value="insert">
    <input type="submit" value="�e�X�s�W"></FORM>
 			 
</body>
</html>