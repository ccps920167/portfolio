<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.main_message.model.*"%>

<% 
   Main_messageService main_messageSvc = new Main_messageService();
   List<Main_messageVO> list = main_messageSvc.getAll();
   pageContext.setAttribute("list", list);
 %>

<!DOCTYPE html>
<html>
<head>

<title>主留言</title>

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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>

<body bgcolor='white'>
<table id="table-1">
	<tr><td>
		 <h3>所有主留言</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/main_message/select_pageMainMessage.jsp"><img src="<%=request.getContextPath()%>/back-end/main_message/images/SeaOtterWait.jpg" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
<tr>
		<th>大留言編號</th>
		<th>小留言編號</th>
		<th>會員編號</th>
		<th>時間</th>
		<th>留言文字</th>
		<th>留言來源</th>
		<th>訊息顯示狀態</th>
		
</tr>
<tr>
			<td>${main_messageVO.mainmsg_id}</td>
			<td>${main_messageVO.class_id}</td>
			<td>${main_messageVO.member_id}</td>
			<td>${main_messageVO.mainmsg_time}</td>
			<td>${main_messageVO.mainmsg_text}</td>
			<td>${main_messageVO.msg_source}</td> 
 	        <td>${main_messageVO.msg_statusword}</td>			
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Main_message/Main_messageServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="mainmsg_id"  value="${main_messageVO.mainmsg_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Main_message/Main_messageServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="mainmsg_id"  value="${main_messageVO.mainmsg_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
</table>



</body>
</html>