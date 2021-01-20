<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>

<title>公告訊息</title>
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
   <tr><td><h3>公告訊息</h3><h4>( MVC )</h4></td></tr>
</table>

<h3>公告訊息查詢:</h3>

<%-- 錯誤表列 --%>
 <c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
 </c:if>

<jsp:useBean id="post_messageSvc" scope="page" class="com.post_message.model.Post_messageService" />

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/post_message/listAllPostMessage.jsp'>顯示全部訊息頁面</a> all post_message  <br><br></li>

<li>
    <FORM METHOD="post" ACTION="/TEA102G5/Post_message/Post_messageServlet" >
        <b>輸入公告編號 (如PI00001):</b>
        <input type="text" name="post_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
 </li>  

<li>
    <FORM METHOD="post" ACTION="/TEA102G5/Post_message/Post_messageServlet" >
        <b>輸入公告內容 :</b>
        <input type="text" name="post_content">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
 </li>

<li>
    <FORM METHOD="post" ACTION="/TEA102G5/Post_message/Post_messageServlet" >
        <b>輸入公告人(管理員編號) (如AI00001):</b>
        <input type="text" name="admin_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
 </li>
 
 <li>
     <FORM METHOD="post" ACTION="/TEA102G5/Post_message/Post_messageServlet" >
           <b>公告對象身份:</b>
               <input type="radio" name="target_type" size="45" value="o">老師
			   <input type="radio" name="target_type" size="45" value="1">學生 
		  
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
      </FORM>
 </li>
</ul>

 <ul>
  <li><a href='<%=request.getContextPath()%>/back-end/post_message/addPostMessage.jsp'>增加公告訊息</a> a new post_message</li>
 </ul>

        
</body>
</html>