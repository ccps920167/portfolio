<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ page import="java.util.*"%>
<%@ page import= "com.sub_message.model.*" %>



<!DOCTYPE html>
<html>
<head>

<title> �l�d�� </title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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
 
</head>
<body bgcolor='white'>
<table id="table-1">
   <tr><td><h3>���d��Sub_message</h3><h4>( MVC )</h4></td></tr>
</table>

<h3>���d���d��:</h3>

<%-- ���~��C --%>
 <c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
 </c:if>
 
 <jsp:useBean id="sub_messageSvc" scope="page" class="com.sub_message.model.Sub_messageService" />
 
 <ul>
  <li><a href='<%=request.getContextPath()%>/back-end/sub_message/listAllSubMessage.jsp'>��ܥ����p�d��</a> all sub_message  <br><br></li>

  <li>
    <FORM METHOD="post" ACTION="/TEA102G5/Sub_message/Sub_messageServlet" >
        <b>��J�p�d���s�� (�pSM00001):</b>
        <input type="text" name="submsg_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
    <FORM METHOD="post" ACTION="/TEA102G5/Sub_message/Sub_messageServlet" >
        <b>��J�j�d���s�� (�pMM00001):</b>
        <input type="text" name="mainmsg_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
 </li>
 
  <li>
    <FORM METHOD="post" ACTION="/TEA102G5/Sub_message/Sub_messageServlet" >
        <b>��J�|���s�� (�pMEM00001):</b>
        <input type="text" name="member_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
 </li> 
  
  <li>
    <FORM METHOD="post" ACTION="/TEA102G5/Sub_message/Sub_messageServlet" >
        <b>���d�����e :</b>
        <input type="text" name="submsg_text">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
 </li>
  
  <li>
     <FORM METHOD="post" ACTION="/TEA102G5/Sub_message/Sub_messageServlet" >
       <b>�T����ܪ��A:</b>
             <input type="radio" name="submsg_status" size="45" value="o">����
			 <input type="radio" name="submsg_status" size="45" value="1">��� 
       
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li> 
  
</ul>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/sub_message/addSubMessage.jsp'>�W�[�p�d��</a> a new sub_message</li>
</ul>
 
</head>
<body>

</body>
</html>