<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.main_message.model.*"%>


<!DOCTYPE html>

<% Main_messageVO main_messageVO = (Main_messageVO) request.getAttribute("main_messageVO"); %>



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
	width: 600px;
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
		 <h3>大留言編號</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/main_message/select_pageMainMessage.jsp"><img src="<%=request.getContextPath()%>/back-end/main_message/images/SeaOtterWait.jpg" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
	    <th>大留言編號</th>
		<th>小留言編號</th>
		<th>會員編號</th>
		<th>時間</th>
		<th>留言內容</th>
		<th>留言來源</th>
		<th>訊息顯示狀態</th>
	</tr>
	<tr>
		<td><%=main_messageVO.getMainmsg_id()%></td> 
		<td><%=main_messageVO.getClass_id()%></td>
		<td><%=main_messageVO.getMember_id()%></td>
		<td><%=main_messageVO.getMainmsg_time()%></td>
		<td><%=main_messageVO.getMainmsg_text()%></td>
		<td><%=main_messageVO.getMsg_source()%></td>
		<td><%=main_messageVO.getMsg_statusword()%></td>
	</tr>
	<tr>
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