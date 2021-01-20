<!-- 設定JSP編碼 -->
<%@page import="com.class_chapter.model.Class_chapterVO"%>
<%@page import="com.class_unit.model.Class_unitVO"%>
<%@page import="com.class_unit.model.Class_unitService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 設定標籤庫 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.class_info.model.*"%>
<%@ page import="com.class_chapter.*"%>


<!doctype html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">

<title>TOMATO - 讓你分分鐘鐘都在學習的網站</title>


<style type="text/css">
html, body {
	height: 100%;
}

.sign_in {
	display: -ms-flexbox;
	display: flex;
	-ms-flex-align: center;
	align-items: center;
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.form-signin {
	width: 100%;
	max-width: 330px;
	padding: 15px;
	margin: auto;
}

.form-signin .checkbox {
	font-weight: 400;
}

.form-signin .form-control {
	position: relative;
	box-sizing: border-box;
	height: auto;
	padding: 10px;
	font-size: 16px;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="email"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}
</style>

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
<link href="<%=request.getContextPath()%>/css/header.css"
	rel="stylesheet" />

</head>

<body>
	<%@ include file="header.jsp"%>


	<div class="container sign_in">
		<!-- 內容 -->
		<form class="form-signin"
			action="<%=request.getContextPath()%>/member_info/Member_newServlet?action=insert_user"
			method="post">

			<h1 class="h3 mb-3 font-weight-normal" >建立帳號</h1>
			<br>
			<c:if test="${not empty errorMsgs}">
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
					<li><span class="badge badge-danger" style="color: white;">${message}</span>
					</li>
					</c:forEach>
				</ul>
			</c:if>
			<h6 class=" font-weight-normal" style="display: inline;">Email</h6>
			<span  id=emailError style="color:white;display: inline;" ></span> <input
				name="member_email" type="email" id="inputEmail"
				class="form-control" placeholder="輸入Email" required autofocus
				style="margin: 10px">


			<h6 class=" font-weight-normal" style="display: inline;">密碼</h6>
			<span  id=pwdError style="color: white;display: inline;"></span> <input
				name="member_password" type="password" id="inputPassword"
				class="form-control" placeholder="輸入密碼" required
				style="margin: 10px">
				
			<h6 class=" font-weight-normal" style="display: inline;">再輸入一次密碼</h6>
			<span id=pwdErrorAgain style="color: white;display: inline;"></span> <input
				name="AgainMember_password" type="password" id="inputPasswordAgain"
				class="form-control" placeholder="再輸入一次密碼" required
				style="margin: 10px">
				
			<h6 class=" font-weight-normal" style="display: inline-block;">驗證碼</h6>
			<img
				src="<%=request.getContextPath()%>/front-end/sign_in/newimage.jsp"
				alt="New Image" /> <input type="text" name="insrand" id="insrand"
				maxlength="4" class="form-control"
				style="margin: 10px; display: inline-block;" />
			<button class="btn btn-lg btn-danger btn-block" type="submit"
				style="margin: 10px">建立帳號</button>
			<input type="hidden" name="action" value="login" style="margin: 10px" /><br>
			<!-- 登入歷史紀錄 -->
			<input name="login_time" id="f_date1" type="hidden">
			<input id="device" type="hidden" name="login_arrange">
			<input id="ip" type="hidden" name="login_ip">
		</form>
	</div>

	<%@ include file="footer.jsp"%>
	<!-- JavaScript -->
	<script
		src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>

	<script>
    
    $("#inputEmail").blur(function () {
    	var data = {
			"action": "checkEmail",
			"member_email": $("#inputEmail").val()
		};

		console.log(data);
		$.ajax({
			url: "<%=request.getContextPath()%>/member_info/Member_newServlet",
			type: "POST", // GET | POST | PUT | DELETE | PATCH
			data: data, // 傳送資料到指定的 url
			dataType: "html",
			success: function (data) { // request 成功取得回應後執行
				if(data === ""){
					$("#emailError").removeAttr("class");
					$("#emailError").text("");
				}else{
					$("#emailError").text(data);
					$("#emailError").addClass("badge badge-danger")
					}
				} // success
			}) // ajax
    
	})
	
	
	$("#inputPassword").blur(function () {
    	var data = {
			"action": "checkPWD",
			"member_password": $("#inputPassword").val()
		};

		console.log(data);
		$.ajax({
			url: "<%=request.getContextPath()%>/member_info/Member_newServlet",
										type : "POST", // GET | POST | PUT | DELETE | PATCH
										data : data, // 傳送資料到指定的 url
										dataType : "html",
										success : function(data) { // request 成功取得回應後執行
											
											if(data === ""){
												$("#pwdError").removeAttr("class");
												$("#pwdError").text("");
											}else{
												$("#pwdError").text(data);
												$("#pwdError").addClass("badge badge-danger");
												}

											
										} // success
									}) // ajax

						})
	
	$("#inputPasswordAgain").blur(function () {
		var inputPasswordAgain = $("#inputPasswordAgain").val();
		var inputPassword = $("#inputPassword").val();
    	if(inputPasswordAgain != inputPassword){
    		$("#pwdErrorAgain").text("密碼不相符")
    		$("#pwdErrorAgain").addClass("badge badge-danger")
    	}else{
    		$("#pwdErrorAgain").text("")
    		$("#pwdErrorAgain").removeClass("badge badge-danger")
    	}

    						})				
		
						
	$.getJSON("https://api.ipify.org?format=json",function (data) {
	
	    // Setting text of element P with id gfg 
	    
	    console.log(data.ip);
	    document.getElementById("ip").setAttribute("value",data.ip)
	  })
	const getUA = () => {
	  let device = "Unknown";
	  const ua = {
	    "Generic Linux": /Linux/i,
	    "Android": /Android/i,
	    "BlackBerry": /BlackBerry/i,
	    "Bluebird": /EF500/i,
	    "Chrome OS": /CrOS/i,
	    "Datalogic": /DL-AXIS/i,
	    "Honeywell": /CT50/i,
	    "iPad": /iPad/i,
	    "iPhone": /iPhone/i,
	    "iPod": /iPod/i,
	    "macOS": /Macintosh/i,
	    "Windows": /IEMobile|Windows/i,
	    "Zebra": /TC70|TC55/i,
	  }
	  Object.keys(ua).map(v => navigator.userAgent.match(ua[v]) && (device = v));
	  return device;
	}
	
	console.log(getUA());
	var dev =getUA();
	console.log(dev);
	document.getElementById("device").setAttribute("value",dev)
	// $("#submit").mousedown(function(){
	//     $("#emailtoid").val($("#email").val())
	//    })

		</script>
</body>
</html>