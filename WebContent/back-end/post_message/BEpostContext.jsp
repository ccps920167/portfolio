<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.post_message.model.*"%>
<%@ page import="java.util.*"%>

<%
	Post_messageService post_messageSvc = new Post_messageService();
	List<Post_messageVO> list = post_messageSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
<!-- video.js CSS -->
<link
	href="<%=request.getContextPath()%>/vendors/video-js/css/video-js.css"
	rel="stylesheet" />
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
<!-- css -->
<link href="<%=request.getContextPath()%>/css/index.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/header.css"
	rel="stylesheet" />
	
	<title>TOMATO後台管理平台</title>
</head>

<body>
	<%@ include file="header-back.jsp"%>
	<div style="margin-left: 250px; margin-right: 20px;">
		<nav aria-label="breadcrumb" style="margin-top: 30px;">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Home</a></li>
				<li class="breadcrumb-item active" aria-current="page">訊息管理</li>
				<li class="breadcrumb-item active" aria-current="page">公告管理</li>
			</ol>
		</nav>
		</div></body>
		
		
		
<body onload="connect();" onunload="disconnect();">
<form>
	<P>
<div style="margin-left: 250px; margin-right: 20px;">
	<div class="container">
		發送內容:<input type="text" class="text-field" id="message"
			name="post_content" onkeydown="if (event.keyCode == 13);"> 
			<input type="submit" value="送出" onClick="sendMessage()"> 
			<input type="reset" value="清除">
		</div></div>

   <P>
</form>

			
	<div style="margin-left: 250px; margin-right: 20px;">
				<div class="container">
			<h1>所有公告列表</h1>
			<table class="table table-striped">
				<tr>
					<th>公告編號</th>
					<th>公告內容</th>
					<th>公告時間</th>
					<th>公告人員</th>
					<th>公告對象身份</th>
				</tr>
				<%@ include file="page1.file"%>
				<c:forEach var="post_messageVO" items="${list}"
					begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr>
						<td>${post_messageVO.post_id}</td>
						<td>${post_messageVO.post_content}</td>
						<td><fmt:formatDate value="${post_messageVO.send_time}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>${post_messageVO.admin_id}</td>
						<td>${post_messageVO.target_typeword}</td>
					</tr>
				</c:forEach>
			</table>
			<%@ include file="page2.file"%>
		</div>
		</div>
</body>



<!---------------------------------------- script ---------------------------------------->

	<!-- jquery-->
	<script
		src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>

	<!-- popper-->
	<script
		src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>

	<!-- bootstrap -->
	<script
		src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>

	<!-- public.js-->
	<script src="<%=request.getContextPath()%>/js/back-end/public.js"></script>

	<!---------------------------------------- script ---------------------------------------->




<script>

var MyPoint = "/Notification/{post_content}";
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

//connect()
	function connect() {
		// create a websocket
		webSocket = new WebSocket(endPointURL);
	console.log(endPointURL);
		webSocket.onopen = function(event) {
			console.log("Connect Success!");
			
		};
	}







//sendMessage()
	function sendMessage() {
		var inputMessage = document.getElementById("message");
	//	var friend = statusOutput.textContent;
		var message = inputMessage.value.trim();
		console.log(message);
		if (message === "") {
			alert("Input a message");
			inputMessage.focus();
			
		} else {
			var jsonObj = {
				"type" : "1",
				"admin_id":"${admin_id}",
				"send_time": Date.now,
				"post_content" : message,
				"target_type":"1"
			
			};
			
 			console.log(jsonObj);
			
			webSocket.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}
	
	
	
</script>


<!-- JavaScript -->
		<script	src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
		<script	src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
		<script	src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
		<script	src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.js"></script>
		
	</body>	
</html>