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
<!-- friendchat CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/adminchat.css" type="text/css" />
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
<style type="text/css">




</style>
<title>Tomato客服聊天室</title>
</head>
<body onload="connect();" onunload="disconnect();">
<%@ include file="header-back.jsp"%>
<!-- 	<div class="row"> -->
<div class="all">
<!-- 	<div class="chatrow "> -->
	
		<div id="chatrow" class="chatdiv1">
	<!-- 		<div> -->
	<!-- 			<p class="select">請點選聊天對象</p> -->
	<!-- 		</div> -->
			<c:forEach var="memberlist" items="${memberlist }">
				<div id="${memberlist.member_email}" class="column pointer displaynone" name="friendName"
					value="${memberlist.member_email }">
					<input class="column" type="hidden" value="${memberlist.member_email }">
					<h5 class="inner">名稱:${memberlist.member_name }</h5>
				<h9 class="inner">帳號:${memberlist.member_email }</h9>
				</div>
			</c:forEach>
			<%-- 	<div id="" class="column" name="friendName" value="${serviceUser }"><h5>${serviceUserName }</h5></div> --%>
		</div>
	<!-- 	<div id="row2"> -->
		<div class="chatdiv2">
<!-- 			<div class="chatrow"> -->
			
				<h6 id="statusOutput" class="statusOutput">選擇聊天對象</h6>
<%-- 				<a id ="xx" href="<%=request.getContextPath()%>/back-end/index-back.jsp"><img class="pic" src="<%=request.getContextPath()%>/img/PIC/RedCross.png" border="0"></a> --%>
<!-- 			</div> -->
			<div id="messagesArea" class="panel message-area chatbkpic"></div>
			<div class="panel input-area">
				<input id="message" class="text-field" type="text"
					placeholder="Message"
					onkeydown="if (event.keyCode == 13) sendMessage();" /> <input
					type="submit" id="sendMessage" class="button" value="送出"
					onclick="sendMessage();" /> 
		<!-- 			<input type="button" id="connect" -->
		<!-- 			class="button" value="Connect" onclick="connect();" /> <input -->
		<!-- 			type="button" id="disconnect" class="button" value="Disconnect" -->
		<!-- 			onclick="disconnect();" /> -->
				<div>
					<h6 class="font-bold">現在登入的是: ${adminName}</h6>
				</div>
			</div>
		</div>
<!-- 	</div> -->
</div>
<!-- 	</div> -->
<!-- 	</div> -->
	<input type="hidden" class="hiddeninput" value="">
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

		<!-- jquery.datetimepicker -->
		<script
			src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.full.js"></script>

		<!---------------------------------------- script ---------------------------------------->

</body>
<script>

// $('#message').on('keyup click', function(event) {
// // 	console.log(event);
// 	if(event.which == 13 || event.which == 1){
// 	$(".column").trigger("click");
// 	}
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
			
		};
		webSocket.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);
			console.log(jsonObj)
			// foreach拿username
			$("input.column").each(function(index,item){
// 				console.log("offline users = " + $(this).val() );
				let user = $(this).val();
// 				console.log("user = " + user)
// 				console.log(jsonObj[user])
				if(jsonObj[user]){
					//改變CSS
// 					console.log("find you!");
// 					console.log(document.getElementById(user));
					let element = document.getElementById(user);
// 					element.style.background = 'red';
// 					element.style.cssText = 'background-color: red;';
					element.classList.remove("displaynone");
// 					element.innerText += "未讀";
// 					element.innerText += jsonObj[user];
// 					element.innerText += "則訊息";
				}
			})
			
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
	function refreshFriendList(jsonObj) {
		var friends = jsonObj.users;
		var row = document.getElementById("chatrow");
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
		var container = document.getElementById("chatrow");
		// 		container.addEventListener("click", function(e) {
		// 			var friend = e.srcElement.textContent
		$('.column')
				.click(
						function(e) {
							var friend = e.currentTarget.attributes.value.textContent;
							$(".hiddeninput").val(friend);
// 							var friend_name = e.currentTarget.children[0].childNodes[0].textContent;
							var friend_name = e.currentTarget.attributes.value.textContent;
// 							var friend_name = e.currentTarget.$("input[class='column']").val();
							console.log(e);
// 							console.log(e.currentTarget);
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