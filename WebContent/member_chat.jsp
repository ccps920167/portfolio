<%@page import="com.member_info.model.Member_infoVO"%>
<%@page import="com.member_info.model.Member_infoService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>


<!-- friendchat CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/friendchat.css" type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">

<%
	Member_infoService member_infoSrv = new Member_infoService();
	List<Member_infoVO> memberlist = (List<Member_infoVO>) member_infoSrv.getAll();
	pageContext.setAttribute("memberlist", memberlist);
%>


<style type="text/css">
body {

	background-color:#f3f1f1;
}
.inner {
	background-color: #d5e1a3;
	display: flex;
	height: 20px;
	align-items: center;
	font-size: 14px;
	margin: 0;
	border: 0;
	padding: 0;
	width: 67px;
}

.pointer {
	cursor: pointer;
}

.box {
	　　border: 1px solid #cccccc;
	　　width: 300px;
	　　height: 100px;
	　　background-color: #FFFFFF;
	　　padding: 5px;
	　　overflow: auto;
}

.all {
	    margin-top: 80px;
    position: fixed;
    right: 15px;
    bottom: 0px;
    top: 65px;
    height:360px;
/*     top: 319px; */
}
}
.hide{
	display:none;
}
}
</style>

<body onload="connect();" onunload="disconnect();">

	<div class="all row">
		<div id="row">

			<div id="friendName" class="column" name="friendName"
				value="${not empty loginAdminID ? loginAdminID : "AI00010"}">
				<div style="display:none;font-size:2px">${not empty loginAdminName ? loginAdminName : "客服小幫手"}</div>
				<form action="<%=request.getContextPath()%>/chat/NameServlet" method="POST">
				<input type="hidden" name="action" value="member-chat">
				<input type="submit" id="goconnect" value="-連接客服-">
				</form>
			</div>
		</div>
		<!-- 	<div id="row2"> -->
		<div id="statusOutput-out">

		<h6 id="statusOutput" class="statusOutput"></h6>
		<div id="messagesArea" class="panel message-area"></div>
		<div class="panel input-area">
			<input id="message" class="text-field" type="text"
				placeholder="Message"
				onkeydown="if (event.keyCode == 13) sendMessage();" /> <input
				type="submit" id="sendMessage" class="button" value="Send"
				onclick="sendMessage();" />

			<div>
				<h6>現在登入的是: ${member_infoVO.member_name}</h6>
			</div>
		</div>
</div>
	</div>

	<input type="hidden" class="hiddeninput" value="">
<!-- 	<!-- JavaScript --> 
<!-- 	<script -->
<%-- 		src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script> --%>
<!-- 	<script -->
<%-- 		src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script> --%>
<!-- 	<script -->
<%-- 		src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script> --%>

</body>

<script>

// $(window).on("click",function(e) {
// 	$("div.column").click();
// });

// $("#sendMessage").on("click",function(){
// 	$("div.column").click()
// // 	$("#friendName").click();
// })
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
// 			document.getElementById('connect').disabled = true;
// 			document.getElementById('disconnect').disabled = false;
			init()
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
				"time" : Date.now(),
			};
			webSocket.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}

	function refreshFriendList(jsonObj) {
		var friends = jsonObj.users;
		var row = document.getElementById("row");

		addListener();
	}
	// 註冊列表點擊事件並抓取好友名字以取得歷史訊息
	function addListener() {
		var container = document.getElementById("row");

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
	
	function init(){
			var friend_name = '${not empty loginAdminID ? loginAdminID : "AI00010"}';
			console.log("friend_name=" + friend_name);
			updateFriendName(friend_name);
			var jsonObj = {
				"type" : "history",
				"sender" : self,
				"receiver" : friend_name,
				"message" : ""
			};
			console.log("jsonObj = " + jsonObj)
			console.log("JSON.stringify(jsonObj) = " + JSON.stringify(jsonObj))
			webSocket.send(JSON.stringify(jsonObj));
	}
	
</script>
