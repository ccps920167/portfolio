<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import= "com.sub_message.model.*" %>

<%  Sub_messageVO sub_messageVO = (Sub_messageVO) request.getAttribute("sub_messageVO");
%>
<!DOCTYPE html>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>次留言</title>

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
		 <h3>次留言新增 </h3></td><td>
		 <h4><a href="<%=request.getContextPath() %>/back-end/sub_message/select_pageSubMessage.jsp"><img src="<%=request.getContextPath() %>/back-end/sub_message/images/SeaOtterWashface.gif" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h1>學習發問</h1>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
 </c:if>
 
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Sub_message/Sub_messageServlet" name="form1">

<table>
 		 

   
   <tr>
     <td>大留言編號:</td>
     <td><input type="TEXT" name="mainmsg_id" size="45"
			   value="<%= (sub_messageVO==null)? "MM0000" : sub_messageVO.getMainmsg_id()%>" /></td>
   </tr>
   
   <tr>
     <td>會員編號:</td>
     <td><input type="TEXT" name="member_id" size="45"
		        value="<%= (sub_messageVO==null)? "MEM0000" : sub_messageVO.getMember_id()%>" /></td>
   </tr>
   
    <tr>
     <td>次留言內容:</td>
     <td><input type="TEXT" name="submsg_text" size="45"
		        value="<%= (sub_messageVO==null)? "" : sub_messageVO.getSubmsg_text()%>" /></td>
   </tr>

   <tr>
     <td>訊息顯示狀態:</td>
     <td><input type="radio" name="submsg_status" size="45" value="0">隱藏
	     <input type="radio" name="submsg_status" size="45" value="1">顯示
   </tr>


</table>
  <br>
    <input type="hidden" name="action" value="insert">
    <input type="submit" value="送出新增"></FORM>
</body>
</html>