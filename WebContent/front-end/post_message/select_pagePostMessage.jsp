<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>

<title>���i�T��</title>
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
   <tr><td><h3>���i�T��</h3><h4>( MVC )</h4></td></tr>
</table>

<h3>���i�T���d��:</h3>

<%-- ���~��C --%>
 <c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
 </c:if>

<jsp:useBean id="post_messageSvc" scope="page" class="com.post_message.model.Post_messageService" />

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/post_message/listAllPostMessage.jsp'>��ܥ����T������</a> all post_message  <br><br></li>

<li>
    <FORM METHOD="post" ACTION="/TEA102G5/Post_message/Post_messageServlet" >
        <b>��J���i�s�� (�pPI00001):</b>
        <input type="text" name="post_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
 </li>  

<li>
    <FORM METHOD="post" ACTION="/TEA102G5/Post_message/Post_messageServlet" >
        <b>��J���i���e :</b>
        <input type="text" name="post_content">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
 </li>

<li>
    <FORM METHOD="post" ACTION="/TEA102G5/Post_message/Post_messageServlet" >
        <b>��J���i�H(�޲z���s��) (�pAI00001):</b>
        <input type="text" name="admin_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
 </li>
 
 <li>
     <FORM METHOD="post" ACTION="/TEA102G5/Post_message/Post_messageServlet" >
           <b>���i��H����:</b>
               <input type="radio" name="target_type" size="45" value="o">�Ѯv
			   <input type="radio" name="target_type" size="45" value="1">�ǥ� 
		  
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
      </FORM>
 </li>
</ul>

 <ul>
  <li><a href='<%=request.getContextPath()%>/back-end/post_message/addPostMessage.jsp'>�W�[���i�T��</a> a new post_message</li>
 </ul>

        
</body>
</html>