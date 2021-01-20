<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
 

<html>
<head>

 <title>主留言</title>
 
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

<h3>資料查詢:</h3>

<%-- 錯誤表列 --%>
 <c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
 </c:if>
<jsp:useBean id="main_messageSvc" scope="page" class="com.main_message.model.Main_messageService" />
                                                      
<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/main_message/listAllMainMessage.jsp'>顯示全部課程頁面留言</a> all main_message  <br><br></li>

<li>
    <FORM METHOD="post" ACTION="/TEA102G5/Main_message/Main_messageServlet" >
        <b>輸入大留言編號 (如MM00001):</b>
        <input type="text" name="mainmsg_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
 </li>   
 
 
 <li>
    <FORM METHOD="post" ACTION="/TEA102G5/Main_message/Main_messageServlet" >
        <b>輸入小留言編號 (如CLA00001):</b>
        <input type="text" name="class_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
 </li>  
    
 <li>
    <FORM METHOD="post" ACTION="/TEA102G5/Main_message/Main_messageServlet" >
        <b>輸入會員編號 (如MEM00001):</b>
        <input type="text" name="member_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
 </li>  
 
 <li>
    <FORM METHOD="post" ACTION="/TEA102G5/Main_message/Main_messageServlet" >
        <b>留言文字 :</b>
        <input type="text" name="mainmsg_text">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
 </li>  
 
 
 <li>
 
       <FORM METHOD="post" ACTION="/TEA102G5/Main_message/Main_messageServlet" >
           <b>留言來源:</b>
               <input type="radio" name="msg_source" size="45" value="學習頁面">學習頁面
			   <input type="radio" name="msg_source" size="45" value="課程頁面">課程頁面
       
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
 </li>  
 
  <li>
       
       <FORM METHOD="post" ACTION="/TEA102G5/Main_message/Main_messageServlet" >
           <b>公告對象身份:</b>
               <input type="radio" name="msg_status" size="45" value="o">隱藏
			   <input type="radio" name="msg_status" size="45" value="1">顯示 
       
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>   
</ul>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/main_message/addMainMessage.jsp'>增加課程頁面留言</a> a new main_message</li>
</ul>


</body>
</html>