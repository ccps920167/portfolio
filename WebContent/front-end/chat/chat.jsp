<%@page import="com.member_info.model.Member_infoVO"%>
<%@page import="com.member_info.model.Member_infoService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%
	Member_infoService member_infoSrv = new Member_infoService();
	List<Member_infoVO> memberlist = (List<Member_infoVO>) member_infoSrv.getAll();
	pageContext.setAttribute("memberlist", memberlist);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
<style type="text/css">
.inner {
	background-color: #d5e1a3;
	display: flex; /* ★ */
	height: 20px;
	align-items: center; /* ★ */
	font-size:14px;
	margin:0;
	border:0;
	padding:0;
	width:67px;
}

.pointer {
	cursor: pointer;
}

.box {
　　border:1px solid #cccccc;
　　width:300px;
　　height:100px;
　　background-color:#FFFFFF;
　　padding:5px;
　　overflow:auto;
}
.all{
width:360px;
height:400px;
position:fixed;
}


</style>
<title>Tomato客服聊天室</title>
</head>
<body onload="connect();" onunload="disconnect();">
<!-- 	<div class="row"> -->
<div class="all">
	<div id="row">
<!-- 		<div> -->
<!-- 			<p class="select">請點選聊天對象</p> -->
<!-- 		</div> -->
		<c:forEach var="memberlist" items="${memberlist }">
			<div id="" class="column pointer" name="friendName"
				value="${memberlist.member_email }">
				<h5 class="inner">${memberlist.member_name }</h5>
			</div>
		</c:forEach>
		<%-- 	<div id="" class="column" name="friendName" value="${serviceUser }"><h5>${serviceUserName }</h5></div> --%>
	</div>
<!-- 	<div id="row2"> -->
<a id ="xx" href="<%=request.getContextPath()%>/index.jsp"><img class="pic" src="<%=request.getContextPath()%>/img/PIC/RedCross.png" border="0"></a>
	<h6 id="statusOutput" class="statusOutput">聊天對象</h6>
	<div id="messagesArea" class="panel message-area"></div>
	<div class="panel input-area">
		<input id="message" class="text-field" type="text"
			placeholder="Message"
			onkeydown="if (event.keyCode == 13) sendMessage();" /> <input
			type="submit" id="sendMessage" class="button" value="Send"
			onclick="sendMessage();" /> 
<!-- 			<input type="button" id="connect" -->
<!-- 			class="button" value="Connect" onclick="connect();" /> <input -->
<!-- 			type="button" id="disconnect" class="button" value="Disconnect" -->
<!-- 			onclick="disconnect();" /> -->
		<div>
			<h6>現在登入的是: tomato客服
			</h6>
		</div>
	</div>
	</div>
<!-- 	</div> -->
<!-- 	</div> -->
	<input type="hidden" class="hiddeninput" value="">
	<!-- JavaScript -->
	<script
		src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
	<!-- <script src=""></script> -->
</body>
<script>
	var MyPoint = "/FriendWS/${userName}";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
	var statusOutput = document.getElementById("statusOutput");
	var messagesArea = document.getElementById("messagesArea");
	var self = '${userName}';
	var webSocket;
	function connect() {
		// create a websocket
		webSocket = new WebSocket(endPointURL);
		webSocket.onopen = function(event) {
			console.log("Connect Success!");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
		};
		webSocket.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);
			if ("open" === jsonObj.type) {
				refreshFriendList(jsonObj);
			} else if ("history" === jsonObj.type) {
				messagesArea.innerHTML = '';
				var ul = document.createElement('ul');
				ul.id = "area";
				messagesArea.appendChild(ul);
				// 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
				var messages = JSON.parse(jsonObj.message);
				for (var i = 0; i < messages.length; i++) {
					var historyData = JSON.parse(messages[i]);
					var showMsg = historyData.message;
					var longTime = historyData.time;
					var time = new Date(longTime);
					console.log(time.toLocaleString());
					var li = document.createElement('li');
					var liTime = document.createElement('li');
					
					// 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
					historyData.sender === self ? li.className += 'me'
							: li.className += 'friend';
					historyData.sender === self ? liTime.className += 'time_me'
							: liTime.className += 'time_friend';
					li.innerHTML = showMsg;
					liTime.innerHTML = time.toLocaleString();
					ul.appendChild(liTime);
					ul.appendChild(li);
// 					ul.appendChild(liTime);
				}
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("chat" === jsonObj.type) {
				var li = document.createElement('li');
				var liTime = document.createElement('li');
				// 				jsonObj.sender === self ? li.className += 'me'
				// 						: li.className += 'friend';
				if (jsonObj.sender === self) {
					li.className += 'me'
					liTime.className += 'me'
				}
				if ($(".hiddeninput").val() == jsonObj.sender) {
					li.className += 'friend'
					liTime.className += 'friend'
				}
				var time = new Date(jsonObj.time);
				console.log(time.toLocaleString());

				li.innerHTML = jsonObj.message;
				liTime.innerHTML = time;
				console.log(li);
				console.log(liTime);
				
				document.getElementById("area").appendChild(li);
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("close" === jsonObj.type) {
				refreshFriendList(jsonObj);
			}
		};
		webSocket.onclose = function(event) {
			console.log("Disconnected!");
		};
	}
	function sendMessage() {
		var inputMessage = document.getElementById("message");
		// 		var friend = statusOutput.textContent;
		var friend = $(".hiddeninput").val();
		var message = inputMessage.value.trim();
		if (message === "") {
			alert("Input a message");
			inputMessage.focus();
		} else if (friend === "") {
			alert("Choose a friend");
		} else {
			var jsonObj = {
				"type" : "chat",
				"sender" : self,
				"receiver" : friend,
				"message" : message,
				"time" : Date.now()
			};
			webSocket.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}
	// 有好友上線或離線就更新列表
	// 	function refreshFriendList(jsonObj) {
	// 		var friends = jsonObj.users;
	// 		var friends = jsonObj.memberlist;
	// 		var row = document.getElementById("row");
	// 		row.innerHTML = '';
	// 		for (var i = 0; i < 10; i++) {
	// 			if (friends[i] === self) { continue; }
	// 			row.innerHTML +='<div id=' + i + ' class="column" name="friendName" value=' + friends[i] + ' ><h2>' + friends[i] + '</h2></div>';
	// 		}
	// 		addListener();
	// 	}
	function refreshFriendList(jsonObj) {
		var friends = jsonObj.users;
		var row = document.getElementById("row");
		// 				var div =
		// 		row.innerHTML = '';
		// 		for (var i = 0; i < friends.length(); i++) {
		// 			if (friends[i] === self) { continue; }
		// 			row.innerHTML +='<div id=' + i + ' class="column" name="friendName" value=' + friends[i] + ' ><h2>' + friends[i] + '</h2></div>';
		// 		}
		addListener();
	}
	// 註冊列表點擊事件並抓取好友名字以取得歷史訊息
	function addListener() {
		var container = document.getElementById("row");
		// 		container.addEventListener("click", function(e) {
		// 			var friend = e.srcElement.textContent
		$('.column')
				.click(
						function(e) {
							var friend = e.currentTarget.attributes.value.textContent;
							$(".hiddeninput").val(friend);
							var friend_name = e.currentTarget.children[0].childNodes[0].textContent;
							console.log(e);
							console.log(friend_name);
							updateFriendName(friend_name);
							var jsonObj = {
								"type" : "history",
								"sender" : self,
								"receiver" : friend,
								"message" : ""
							};
							console.log(jsonObj)
							webSocket.send(JSON.stringify(jsonObj));
						});
	}
	// 	function addListener() {
	// 		var container = document.getElementById("row");
	// 		container.addEventListener("click", function(e) {
	// 			var friend = e.srcElement.textContent;
	// 			updateFriendName(friend);
	// 			var jsonObj = {
	// 				"type" : "history",
	// 				"sender" : self,
	// 				"receiver" : friend,
	// 				"message" : ""
	// 			};
	// 			webSocket.send(JSON.stringify(jsonObj));
	// 		});
	// 	}
	function getId(str) {
		var id = document.getElementById(str).id;
	}

	function disconnect() {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}
	function updateFriendName(name) {
		statusOutput.innerHTML = name;
	}
</script>
</html>