<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import= "com.sub_message.model.*" %>

<!DOCTYPE html>

<% Sub_messageService sub_messageSvc = new Sub_messageService();
   List<Sub_messageVO> list = sub_messageSvc.getAll();
   pageContext.setAttribute("list", list);
%>
<html>
<head>

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
		 <h3>次留言</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/sub_message/select_pageSubMessage.jsp"><img src="<%=request.getContextPath()%>/back-end/sub_message/images/SeaOtterShy.jpg" width="100" height="100" border="0">回首頁</a></h4>
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
	    <th>小留言編號</th>
		<th>大留言編號</th>
		<th>會員編號</th>
		<th>時間</th>
		<th>次留言內容</th>
		<th>訊息顯示狀態</th>
	</tr>
<%@ include file="page1.file" %>
  <c:forEach var="sub_messageVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tr>
		<td>${sub_messageVO.submsg_id}</td> 
		<td>${sub_messageVO.mainmsg_id}</td>
		<td>${sub_messageVO.member_id}</td>
		<td>${sub_messageVO.submsg_time}</td>
		<td>${sub_messageVO.submsg_text}</td>
		<td>${sub_messageVO.submsg_statusword}</td>
		
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Sub_message/Sub_messageServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="submsg_id"  value="${sub_messageVO.submsg_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			  </FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Sub_message/Sub_messageServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="submsg_id"  value="${sub_messageVO.submsg_id}">
			     <input type="hidden" name="action" value="delete">
			  </FORM>
			</td>
	</tr>
   </c:forEach>
</table>
<%@ include file="page2.file" %>

<body>

</body>
</html>