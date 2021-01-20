<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ page import="com.main_message.model.*"%>

<% Main_messageVO main_messageVO = (Main_messageVO)  request.getAttribute("main_messageVO"); %>
<!-- main_messageServlet.java (Controller) 存入req的emain_messageVO物件 (包括幫忙取出的main_messageVO, 也包括輸入資料錯誤時的main_messageVO物件) -->


<!DOCTYPE html>
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

<title>大留言編號修改</title>

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
		 <h3>大留言資料修改 </h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/main_message/select_pageMainMessage.jsp"><img src="<%=request.getContextPath()%>/back-end/main_message/images/SeaOtterWait.jpg" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="/TEA102G5/Main_message/Main_messageServlet" name="form1">
<table>
	<tr>
		<td>大留言編號:<font color=red><b>*</b></font></td>
		<td><%=main_messageVO.getMainmsg_id()%></td>
	</tr>
	<tr>
		<td>員工姓名:</td>
		<td><input type="TEXT" name="class_id" size="45" value="<%=main_messageVO.getClass_id()%>" /></td>
	</tr>
    <tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="member_id" size="45" value="<%=main_messageVO.getMember_id()%>" /></td>
	</tr>
    <%-- <tr>
		<td>留言時間:</td>
		<td><input type="TEXT" name="mainmsg_time" size="45" value="<%=main_messageVO.getMainmsg_time()%>" /></td>
	</tr>--%>
	<tr>
		<td>留言內容:</td>
		<td><input type="TEXT" name="mainmsg_text" size="45" value="<%=main_messageVO.getMainmsg_text()%>" /></td>
	</tr>
	<tr>
		<td>留言來源:</td>
		<td><input type="TEXT" name="msg_source" size="45" value="<%=main_messageVO.getMsg_source()%>" /></td>
	</tr>
	<tr>
		<td>訊息顯示狀態:</td>
		<td><input type="TEXT" name="msg_status" size="45" value="<%=main_messageVO.getMsg_status()%>" /></td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="mainmsg_id" value="<%=main_messageVO.getMainmsg_id()%>">
<input type="submit" value="送出修改"></FORM>
</body>
</html>