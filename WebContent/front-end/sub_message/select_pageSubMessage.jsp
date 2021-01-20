<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ page import="java.util.*"%>
<%@ page import= "com.sub_message.model.*" %>



<!DOCTYPE html>
<html>
<head>

<title> 子留言 </title>

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
   <tr><td><h3>次留言Sub_message</h3><h4>( MVC )</h4></td></tr>
</table>

<h3>次留言查詢:</h3>

<%-- 錯誤表列 --%>
 <c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
 </c:if>
 
 <jsp:useBean id="sub_messageSvc" scope="page" class="com.sub_message.model.Sub_messageService" />
 
 <ul>
  <li><a href='<%=request.getContextPath()%>/back-end/sub_message/listAllSubMessage.jsp'>顯示全部小留言</a> all sub_message  <br><br></li>

  <li>
    <FORM METHOD="post" ACTION="/TEA102G5/Sub_message/Sub_messageServlet" >
        <b>輸入小留言編號 (如SM00001):</b>
        <input type="text" name="submsg_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
    <FORM METHOD="post" ACTION="/TEA102G5/Sub_message/Sub_messageServlet" >
        <b>輸入大留言編號 (如MM00001):</b>
        <input type="text" name="mainmsg_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
 </li>
 
  <li>
    <FORM METHOD="post" ACTION="/TEA102G5/Sub_message/Sub_messageServlet" >
        <b>輸入會員編號 (如MEM00001):</b>
        <input type="text" name="member_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
 </li> 
  
  <li>
    <FORM METHOD="post" ACTION="/TEA102G5/Sub_message/Sub_messageServlet" >
        <b>次留言內容 :</b>
        <input type="text" name="submsg_text">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
 </li>
  
  <li>
     <FORM METHOD="post" ACTION="/TEA102G5/Sub_message/Sub_messageServlet" >
       <b>訊息顯示狀態:</b>
             <input type="radio" name="submsg_status" size="45" value="o">隱藏
			 <input type="radio" name="submsg_status" size="45" value="1">顯示 
       
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li> 
  
</ul>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/sub_message/addSubMessage.jsp'>增加小留言</a> a new sub_message</li>
</ul>
 
</head>
<body>

</body>
</html>