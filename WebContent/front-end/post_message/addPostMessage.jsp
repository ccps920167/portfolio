<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.post_message.model.*"%>

<% 
   Post_messageVO post_messageVO = (Post_messageVO) request.getAttribute("post_message");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

<title>新增公告訊息</title>
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
		 <h3>公告訊息 </h3></td><td>
		 <h4><a href="select_pagePostMessage.jsp"><img src="images/SeaOtterRubTheEyes.gif" width="150" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h1>新增公告訊息</h1>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
 </c:if>
<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/Post_message/Post_messageServlet" name="form1">
<table>

       <tr>
          <td>公告內容:</td>
          <td><input type="TEXT" name="post_content" size="45"
			   value="<%= (post_messageVO==null)? " " : post_messageVO.getPost_content()%>" /></td>
       </tr>
       
<!--        <tr> -->
<!--           <td>公告時間:</td> -->
<!--           <td><input type="TEXT" name="send_time" size="45" -->
<%-- 			   value="<%= (post_messageVO==null)? "" : post_messageVO.getSend_time()%>" /></td> --%>
<!--        </tr> -->
       <tr>
          <td>公告人員:</td>
          <td><input type="TEXT" name="admin_id" size="45"
			   value="<%= (post_messageVO==null)? "AI00000" : post_messageVO.getAdmin_id()%>" /></td>
       </tr>
       <tr>
          <td>公告對象身份:</td>
          <td><input type="radio" name="target_type" size="45" value="0">老師
			  <input type="radio" name="target_type" size="45" value="1">學生 
		  </td> 
       </tr>
 <jsp:useBean id="post_idSvc" scope="page" class="com.post_message.model.Post_messageService" />

</table>
  <br>
    <input type="hidden" name="action" value="insert">
    <input type="submit" value="送出新增"></FORM>

</body>
</html>