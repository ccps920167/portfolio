<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
 

<html>
<head>

 <title>�D�d��</title>
 
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
   <tr><td><h3>Main_message</h3><h4>( MVC )</h4></td></tr>
</table>

<h3>��Ƭd��:</h3>

<%-- ���~��C --%>
 <c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
 </c:if>
<jsp:useBean id="main_messageSvc" scope="page" class="com.main_message.model.Main_messageService" />
                                                      
<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/main_message/listAllMainMessage.jsp'>��ܥ����ҵ{�����d��</a> all main_message  <br><br></li>

<li>
    <FORM METHOD="post" ACTION="/TEA102G5/Main_message/Main_messageServlet" >
        <b>��J�j�d���s�� (�pMM00001):</b>
        <input type="text" name="mainmsg_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
 </li>   
 
 
 <li>
    <FORM METHOD="post" ACTION="/TEA102G5/Main_message/Main_messageServlet" >
        <b>��J�p�d���s�� (�pCLA00001):</b>
        <input type="text" name="class_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
 </li>  
    
 <li>
    <FORM METHOD="post" ACTION="/TEA102G5/Main_message/Main_messageServlet" >
        <b>��J�|���s�� (�pMEM00001):</b>
        <input type="text" name="member_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
 </li>  
 
 <li>
    <FORM METHOD="post" ACTION="/TEA102G5/Main_message/Main_messageServlet" >
        <b>�d����r :</b>
        <input type="text" name="mainmsg_text">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
 </li>  
 
 
 <li>
 
       <FORM METHOD="post" ACTION="/TEA102G5/Main_message/Main_messageServlet" >
           <b>�d���ӷ�:</b>
               <input type="radio" name="msg_source" size="45" value="�ǲ߭���">�ǲ߭���
			   <input type="radio" name="msg_source" size="45" value="�ҵ{����">�ҵ{����
       
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
 </li>  
 
  <li>
       
       <FORM METHOD="post" ACTION="/TEA102G5/Main_message/Main_messageServlet" >
           <b>���i��H����:</b>
               <input type="radio" name="msg_status" size="45" value="o">����
			   <input type="radio" name="msg_status" size="45" value="1">��� 
       
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>   
</ul>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/main_message/addMainMessage.jsp'>�W�[�ҵ{�����d��</a> a new main_message</li>
</ul>


</body>
</html>