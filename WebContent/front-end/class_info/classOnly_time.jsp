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
<%@ page import="com.member_info.model.*"%>
<%@ page import="com.order_list.model.*"%>


<% 
Class_infoVO class_infoVO=(Class_infoVO)request.getAttribute("class_infoVO");
Map<String,List<Class_chapterVO>> initClass = (Map<String, List<Class_chapterVO>>)application.getAttribute("initClass"); //取出ServletContext屬性
List<Class_chapterVO> Class_chapterVO_list =initClass.get(class_infoVO.getClass_id());
Map<String, List<Class_unitVO>> initChapter = (Map<String,List<Class_unitVO>>)application.getAttribute("initChapter");
//取出ServletContext屬性
pageContext.setAttribute("Class_chapterVO_list",Class_chapterVO_list);
pageContext.setAttribute("initChapter", initChapter);
pageContext.setAttribute("class_infoVO", class_infoVO);
String class_id=class_infoVO.getClass_id();
Class_unitService unitSvc=new Class_unitService();
List<Class_unitVO> class_unitVO=unitSvc.getAllunit_id(class_id);
Order_listService orderSvc=new Order_listService();
List<Order_listVO> listOrder=orderSvc.getNumClass(class_id);


%>


<!doctype html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
<!-- video.js CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/header.css">
<link
	href="<%=request.getContextPath()%>/vendors/video-js/css/video-js.css"
	rel="stylesheet" />
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">


<!-- css -->
<link
	href="<%=request.getContextPath()%>/css/front-end/class-info/classOnly.css"
	rel="stylesheet" />

<link href="<%=request.getContextPath()%>/css/header.css"
	rel="stylesheet" />
<title>TOMATO - 讓你分分鐘鐘都在學習的網站</title>
<style type="text/css">

h1{
	font-size: 4.5rem;
    font-weight: 600;
    color: #B54434;
    text-shadow: 2px 2px 2px #FFFFFF;
}

#tab-demo {
	width: 1000px;
	/* 	position: relative; */
	top: 50px;
	left: 10%
}

#tab-demo>ul {
	display: block;
	margin: 0;
	list-style: none;
}

.tab-title {
	list-style: none;
}

#tab-demo>ul>li {
	display: inline-block;
	font-family: '微軟正黑體';
	font-size: 18px;
	border: 1px solid #bcbcbc34;
	height: 40px;
	line-height: 35px;
	background: #e2e2e2;
	padding: 0 15px;
	list-style: none;
	box-sizing: border-box;
	width: 120px;
	margin-right: 40px;
	margin-left: 60px;
	text-align: center;
	border-radius: 5px 5px 0px 0px;
}

#tab-demo>ul>li a {
	color: rgb(151, 150, 150);
	text-decoration: none;
}

#tab-demo>ul>li.active {
	border-bottom: 1px solid #fff;
	background: #fff;
}

#tab-demo>.tab-inner {
	color: #000;
	border: 1px solid #bcbcbc34;
	clear: both;
}

.tab-inner {
	padding: 15px;
}

.image {
	margin: 10px;
	color: #000;
}

.statement {
	color: rgb(151, 150, 150);
}

h3 {
	color: rgb(163, 226, 182);
}

.classstatement {
	border: 1px solid #bcbcbc34;
	border-radius: 5px;
	color: rgb(151, 150, 150);
}

/* ===== 重要性的星號 ===== */
div.star_block {
	display: inline-block;
}

div.star_block>span.star {
	cursor: pointer;
	display: inline-block;
	margin-right: 3px;
}

div.star_block>span.star.-on {
	color: yellow;
}

div.star_block {
	display: inline-block;
	position: relative;
}

div.temp_loading {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: hsla(0, 0%, 0%, .1);
	text-align: center;
	z-index: 2;
	color: white;
}

div.temp_loading>span {
	display: inline-block;
	position: absolute;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
}
</style>
</head>

<body>
	<%@ include file="header.jsp"%>
	<!-- container -->
	<!-- 標題 -->
	<div class="jumbotron jumbotron-fluid" style="padding-bottom: 0;">
		<div class="container">
			<h1>${class_infoVO.class_name}</h1>
			<br>
			<br>
		</div>
		<div class="container-fluid sidebar" style="position: relative;">
			<div class="container">
				<div class="row">
					<!-- 影片底圖 -->
					<div class="col-9 video embed-responsive embed-responsive-16by9">
						<video id="my-video" class="video-js embed-responsive-item vjs-big-play-centered"
							controls preload="auto" 
							data-setup="{}">
							<source
								src="<%=request.getContextPath()%>/class_info/Class_infoImgAndVideo?action=class_video&class_id=${class_infoVO.class_id}"
								type="video/mp4" />
						</video>
					</div>
					<div class="col-3 video-list">
						<div class="alert shadow text-white" role="alert"
							style="margin: 10px; border-radius: 25px; text-align: center; background-color: rgb(255, 255, 255); border: rgb(255, 102, 102) solid;">
							<h5>
								<b style="color: rgb(255, 102, 102);">募資成功正式開課</b>
							</h5>
						</div>
						<div
							style="background-color: rgba(255, 255, 255, 0.70); height: 420px; border: 1px rgb(204, 204, 204, 50) solid; position: relative;">

							<div style="margin: 20px; text-align: center;">

								<div style="margin-top: 10px;">
									<div class="class-sell">
										<div class="alert  shadow text-white " role="alert"
											style="margin: 10px; background-color: tomato;">
											<b>正式售價</b>
										</div>
										<h4 style="margin: 0px;">
											NT＄${class_infoVO.original_price}</h4>
									</div>

									<div class="class-carbut" style="margin-top: 20px;">
										<button id="carbut" type="button" class="btn"
											style="background-color: white;width: 200px; border: 1px tomato solid;">
											<b style="color: rgb(252, 86, 86);">加入購物車</b>
										</button>
									</div>
									<div class="class-carbut" style="margin-top: 20px;">
										<a
											href=" /TEA102G5/Order_info/EShopServlet?action=carbutNow&class_id=${class_infoVO.class_id}">
											<button id="carbutNow" type="button" class="btn btn-danger" style="width: 200px;">馬上購買</button>
										</a>
									</div>
									<div class="input-group mb-3" style="margin-top: 20px;">
										<input type="text" class="form-control" id="classURL"
											placeholder="Recipient's username"
											aria-label="Recipient's username"
											aria-describedby="button-addon2"
											value="">
										<div class="input-group-append">
											<button class="btn btn-danger" type="button"
												id="button-addon2">分享</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<!-- 頁籤 -->
		<div id="pages" class="col-12 d-flex justify-content-center">

			<div id="tab-demo">
				<ul class="tab-title">
					<li><a href="#tab01"><b>課程簡介</b></a></li>
					<li><a href="#tab02"><b>課程發問</b></a></li>
					<li><a href="#tab03"><b>作品欣賞</b></a></li>
					<li><a href="#tab04"><b>課程評價</b></a></li>
				</ul>
				<!-- 頁籤1開始 -->
				<div id="tab01" class="tab-inner " style="margin-bottom: 150px">
					<%@ include file="class_about.file"%>
					<!-- 頁籤1結束 -->
					<div id="tab02" class="tab-inner" style="margin-bottom: 150px">
						<c:if test="${ empty member_infoVO}">
							<div class="text-center">
								<a
									href="<%=request.getContextPath()%>/front-end/sign_in/sign_in.jsp">請先登入後才可以留言</a>
							</div>
						</c:if>
						<c:if test="${not empty member_infoVO}"><%@ include
								file="question.jsp"%></c:if>
						<div class="question_context">
							<img
								src="<%=request.getContextPath()%>/img/NoResult/noRespone.jpg">
						</div>
					</div>

					<div id="tab03" class="tab-inner" style="margin-bottom: 150px">
						<jsp:include page="/front-end/hw/visitor.jsp/workList.jsp">
							<jsp:param value="${class_infoVO.class_id}" name="class_id" />
						</jsp:include>
					</div>
					<div id="tab04" class="tab-inner" style="margin-bottom: 150px">
						<div class="text-center">
							<a
								href="<%=request.getContextPath()%>/front-end/sign_in/sign_in.jsp">購買後才可評價</a>
						</div>
						<div class="Evaluation_context">
							<img
								src="<%=request.getContextPath()%>/img/NoResult/noRespone.jpg">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Footer -->
	<%@ include file="footer.jsp"%>



	<!-- JavaScript -->
	<script
		src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/jquery/jquery-timers.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/video-js/js/video.js"></script>

	<script>
	
	
	
	//讀取評價
	init_evaluation();
	//讀取留言
	intiQuestion();
	
	/*****************************************複製連結************************************/
		$(document).ready(function () {
			$("#classURL").attr("value",window.location.href);
		});
		
		
		 $("#button-addon2").on('click', function(e) {
			    var URL = $("#classURL");
				URL.select();
			  	document.execCommand("Copy");
			    alert("已成功複製網址");
			  });
	/*****************************************倒數計時器************************************/
	// 開始時間
	var startDate = new Date();
	// 結束時間
	var endDate = new Date('${class_infoVO.startfund_date}');
	endDate = endDate.setDate(endDate.getDate() + 30);
	endDate = new Date(endDate);

	var spantime = (endDate - startDate) / 1000;
	$(document).ready(function () {
		$(this).everyTime('1s', function (i) {
			spantime--;
			var d = Math.floor(spantime / (24 * 3600));
			var h = Math.floor((spantime % (24 * 3600)) / 3600);
			var m = Math.floor((spantime % 3600) / (60));
			var s = Math.floor(spantime % 60);

			if (spantime > 0) {
				$('#day').text(d);
				$('#hour').text(h);
				$('#min').text(m);
				$('#sec').text(s);
			} else { // 避免倒數變成負的
				$('#day').text(0);
				$('#hour').text(0);
				$('#min').text(0);
				$('#sec').text(0);
			}
		});
	});


	/*****************************************加入購物車************************************/
	$("div.class-carbut").on("click", "button#carbut", function () {
		var that = $(this);
		console.log(that);
		var data_updata = {
			"action": "ADD",
			"class_id": "${class_infoVO.class_id}",
			"purchase_plan": "0"
		}

		$.ajax({
			url: "/TEA102G5/Order_info/EShopServlet", // 資料請求的網址
			type: "GET",
			data: data_updata, // 傳送資料到指定的 url

		});

	});

	/*****************************************頁籤************************************/
	$(function () {
		var $li = $('ul.tab-title li');
		$($li.eq(0).addClass('active').find('a').attr('href')).siblings(
			'.tab-inner').hide();

		$li.click(function () {
			$($(this).find('a').attr('href')).show().siblings('.tab-inner').hide();
			$(this).addClass('active').siblings('.active').removeClass('active');
		});
	});

	/*****************************************影片切換************************************/
	window.document.body.onload = function () {
			var url_video = $("div#accordion").find("button").attr("data-unitID");
			$("#video_url").closest("video").attr("src", "<%=request.getContextPath()%>/Class_unit/Class_unitServlet?unit_id=" + url_video + "&action=many_video");
			$("#video_url").attr("src", "<%=request.getContextPath()%>/Class_unit/Class_unitServlet?unit_id=" + url_video + "&action=many_video");
		}


	/*****************************************留言方法************************************/
	
	
	$("div.question_context").on("click","button.sm-problem_btn_send",function () {
			//取輸入資料
			var that = $(this)
			var subMsg_text = that.parents("div.subMessageContent").find("textarea").val();
			var mainmsg_id = that.parents("div.data-mainMsg").attr("data-mainMsg");
			var data = {
					"action": "insert",
					"mainmsg_id": mainmsg_id,  //${class_info.class_id}
					"member_id": "${member_infoVO.member_id}",
					"submsg_text": subMsg_text,
					"submsg_status": "1"
			};
			let json_data = JSON.stringify(data);
			console.log(json_data);
			
			var newDate = new Date();

			if ((newDate.getMonth() + 1) < 10) {
				var Month = "0" + (newDate.getMonth() + 1);
			} else {
				var Month = (newDate.getMonth() + 1);
			}

			if ((newDate.getDate()) < 10) {
				var TodayDate = "0" + (newDate.getDate());
			} else {
				var TodayDate = (newDate.getDate());
			}

			if (newDate.getMinutes() < 10) {
				var Minutes = "0" + (newDate.getMinutes());
			} else {
				var Minutes = (newDate.getMinutes());
			}

			if (newDate.getSeconds() < 10) {
				var Second = "0" + (newDate.getSeconds());
			} else {
				var Second = (newDate.getSeconds());
			}
			
			$.ajax({
				url: "<%=request.getContextPath()%>/PutSubMessage/PutSubMessage",
				type: "POST",                  // GET | POST | PUT | DELETE | PATCH
				data: json_data,               // 傳送資料到指定的 url
				dataType: "json",
				success: function (data) {      // request 成功取得回應後執行
					console.log(data.member_id);
					var dataHtml = ""
					dataHtml += "<div class=\"media shadow-sm p-3 mt-1 bg-white rounded\">"
					dataHtml += "	<a class=\"mr-3\" href=\"#\">"
					dataHtml += "    <img style=\"width: 80px; display: inline-block;\" src=\"<%=request.getContextPath()%>/member_info/Member_info_frontServlet?action=member_pic&member_id="
						+ data.member_id
						+ "\" class=\"mr-3\" alt=\"...\">"
					dataHtml += "	</a>"
					dataHtml += "    <div class=\"media-body\">"
					dataHtml += "	<div style=\"margin-right: 100px\" >"
					dataHtml += "      <div style=\"display: inline-block; class=\"mt-0\"><h6>"
						+ "${member_infoVO.member_email}"
						+ "</h6>   </div> "
					dataHtml += "	</div>"
					dataHtml += "      <div style=\"display: inline-block; class=\"mt-0 text-right\"><p class=\"text-secondary\">" + newDate.getFullYear() + "-" + Month + "-" + TodayDate + " " + newDate.getHours() + ":" + Minutes + ":" + Second 
						+ "</p></div> "
					dataHtml += " <div>"
						+ data.submsg_text
						+ "    </div>"
					dataHtml += "	</div>"
					dataHtml += "</div>"
					console.log(that.parents("div.subMessageContent"))
					that.parents("div.subMessageContent").append(dataHtml);
					that.parents("div.subMessageContent").find("textarea").val("");// 留言的資料送出後清空

				} //success
			}) //ajax		
	});
	
	
	
	
	// 觸發點開小留言
	$("div.question_context").on("click","button.subMessage",function () {
				var that = $(this);
				if (that.hasClass("none")) {
					that.closest("div.media").find("div.subMessageContent")
						.empty();
					$(this).removeClass("none")
				} else {
					$(this).addClass("none")
					var data_updata = {
						"action": "subMessage",
						"mainMsg_id": $(this).closest("div.media").attr(
							"data-mainMsg")
					}
					let json_data = JSON.stringify(data_updata);
					$
						.ajax({
							url: "<%=request.getContextPath()%>/PutMessage/PutMessage",
							type: "POST", // GET | POST | PUT | DELETE
							// | PATCH
							data: json_data, // 傳送資料到指定的 url
							dataType: "json",
							success: function (data) { // request
								var form = "<div class=\"media shadow-sm p-3 mt-2 bg-white rounded\">"
									form +="<div class=\"media-body sm-problem-input\">"
									form +="<textarea id=\"sm-problem\" class=\"shadow-sm p-3 bg-white rounded\""
									form +="	style=\"resize: none; width: 700px; height: 100px;\"></textarea>"
									form +="</div>"
									form +="<button type=\"button\" class=\"btn btn-danger sm-problem_btn_send\" >發送</button></div>"
									that.closest("div.media").find("div.subMessageContent").append(form);

								var count = 0;
								console.log(data);
								while (true) {
									console.log(data[count].member_id);
									var dataHtml = ""
									dataHtml += "<div class=\"media shadow-sm p-3 mt-1 bg-white rounded\">"
									dataHtml += "	<a class=\"mr-3\" href=\"#\">"
									dataHtml += "    <img style=\"width: 80px; display: inline-block;\" src=\"<%=request.getContextPath()%>/member_info/Member_info_frontServlet?action=member_pic&member_id="
										+ data[count].member_id
										+ "\" class=\"mr-3\" alt=\"...\">"
									dataHtml += "	</a>"
									dataHtml += "    <div class=\"media-body\">"
									dataHtml += "	<div style=\"margin-right: 100px\" >"
									dataHtml += "      <div style=\"display: inline-block; class=\"mt-0\"><h6>"
										+ data[count].member_email
										+ "</h6>   </div> "
									dataHtml += "	</div>"
									dataHtml += "      <div style=\"display: inline-block; class=\"mt-0 text-right\"><p class=\"text-secondary\">"
										+ data[count].submsg_time
										+ "</p>   </div> "

									dataHtml += " <div>"
										+ data[count].submsg_text
										+ "    </div>"
									dataHtml += "	</div>"
									dataHtml += "</div>"
									that.closest("div.media").find(
										"div.subMessageContent")
										.append(dataHtml);
									count++;
								}
								console.log(data);
							} // success
						}) // ajax
				} // if
			});

	// 自動讀取留言
	function intiQuestion() {
		var data = {
			"action": "getAll",
			"class_id": "${class_infoVO.class_id}", // ${class_info.class_id}
			"msg_source": "課程頁面", // ${class_info.class_id}
		};

		let json_data = JSON.stringify(data);
		console.log(json_data);
		$
			.ajax({
				url: "<%=request.getContextPath()%>/PutMessage/PutMessage",
				type: "POST", // GET | POST | PUT | DELETE | PATCH
				data: json_data, // 傳送資料到指定的 url
				dataType: "json",
				success: function (data) { // request 成功取得回應後執行
					$(".question_context").empty();   //清除圖片
					
					
					var count = 0;
					while (true) {
						var dataHtml = ""
						dataHtml += "<div class=\"media shadow-sm p-3 mb-5 bg-white rounded data-mainMsg\" data-mainMsg=\""
							+ data[count].mainMsg_id + "\">"
						dataHtml += "    <img style=\"width: 80px; display: inline-block;\" src=\"<%=request.getContextPath()%>/member_info/Member_info_frontServlet?action=member_pic&member_id="
							+ data[count].member_id
							+ "\" class=\"mr-3\" alt=\"...\">"
						dataHtml += "    <div class=\"media-body\">"
						dataHtml += "	<div style=\"margin-right: 100px\" >"
						dataHtml += "      <div style=\"display: inline-block; class=\"mt-0\"><h6>"
							+ data[count].member_email + "</h6>   </div> "
						dataHtml += "	</div>"
						dataHtml += "      <div style=\"display: inline-block; class=\"mt-0 text-right\"><p class=\"text-secondary\">"
							+ data[count].mainmsg_time + "</p>   </div> "

						dataHtml += " <div>" + data[count].mainmsg_text
							+ "    </div>"
						dataHtml += " <div>"
							+ "<button class=\"btn btn-sm subMessage\" type=\"button\" style=\"text-align: right;background-color: white; border: 1px tomato solid;\"><b style=\"color: rgb(252, 86, 86);\">查看回覆</b></button>"
							+ "    </div>"
						dataHtml += " <div class=\"subMessageContent\">"
							+ "    </div>"

						dataHtml += "    </div>"
						dataHtml += "</div>"

						$(".question_context").prepend(dataHtml);
						count++;
					}
					console.log(data);

				} // success
			}) // ajax

	}
	/*****************************************評價************************************/
	// 自動讀取評價
	function init_evaluation() {
		var data = {
			"action": "getAll",
			"class_id": "${class_infoVO.class_id}", // ${class_info.class_id}
		};

		let json_data = JSON.stringify(data);
		console.log(json_data);
		$
			.ajax({
				url: "<%=request.getContextPath()%>/Evaluation/putEvaluation",
				type: "POST", // GET | POST | PUT | DELETE | PATCH
				data: json_data, // 傳送資料到指定的 url
				dataType: "json",
				success: function (data) { // request 成功取得回應後執行
					$(".Evaluation_context").empty();   //清除圖片
					var count = 0;
					while (true) {
						var dataHtml = ""
						dataHtml += "<div class=\"media shadow-sm p-3 mb-5 bg-white rounded\">"
						dataHtml += "    <img style=\"width: 80px; display: inline-block;\" src=\"<%=request.getContextPath()%>/member_info/Member_info_frontServlet?action=member_pic&member_id="
										+ data[count].member_id
										+ "\" class=\"mr-3\" alt=\"...\">"
								dataHtml += "    <div class=\"media-body\">"
								dataHtml += "	<div style=\"margin-right: 100px\" >"
								dataHtml += "      <div style=\"display: inline-block; class=\"mt-0\">"
										+ data[count].member_email
										+ "   </div> "
								switch (data[count].evaluation_score) {
								case "1":
									dataHtml += " <span class=\"star\" data-star=\"1\"><i class=\"bi bi-star-fill\" style=\"font-size: 1rem; color: yellow;\"></i></span>"
											+ " <span class=\"star\" data-star=\"2\"><i class=\"bi bi-star-fill\" style=\"font-size: 1rem; color: gray;\"></i></span>"
											+ " <span class=\"star\" data-star=\"3\"><i class=\"bi bi-star-fill\" style=\"font-size: 1rem; color: gray;\"></i></span>"
											+ " <span class=\"star\" data-star=\"4\"><i class=\"bi bi-star-fill\" style=\"font-size: 1rem; color: gray;\"></i></span>"
											+ " <span class=\"star\" data-star=\"5\"><i class=\"bi bi-star-fill\" style=\"font-size: 1rem; color: gray;\"></i></span>"
									break;
								case "2":
									dataHtml += " <span class=\"star\" data-star=\"1\"><i class=\"bi bi-star-fill\" style=\"font-size: 1rem; color: yellow;\"></i></span>"
											+ " <span class=\"star\" data-star=\"2\"><i class=\"bi bi-star-fill\" style=\"font-size: 1rem; color: yellow;\"></i></span>"
											+ " <span class=\"star\" data-star=\"3\"><i class=\"bi bi-star-fill\" style=\"font-size: 1rem; color: gray;\"></i></span>"
											+ " <span class=\"star\" data-star=\"4\"><i class=\"bi bi-star-fill\" style=\"font-size: 1rem; color: gray;\"></i></span>"
											+ " <span class=\"star\" data-star=\"5\"><i class=\"bi bi-star-fill\" style=\"font-size: 1rem; color: gray;\"></i></span>"
									break;
								case "3":
									dataHtml += " <span class=\"star\" data-star=\"1\"><i class=\"bi bi-star-fill\" style=\"font-size: 1rem; color: yellow;\"></i></span>"
											+ " <span class=\"star\" data-star=\"2\"><i class=\"bi bi-star-fill\" style=\"font-size: 1rem; color: yellow;\"></i></span>"
											+ " <span class=\"star\" data-star=\"3\"><i class=\"bi bi-star-fill\" style=\"font-size: 1rem; color: yellow;\"></i></span>"
											+ " <span class=\"star\" data-star=\"4\"><i class=\"bi bi-star-fill\" style=\"font-size: 1rem; color: gray;\"></i></span>"
											+ " <span class=\"star\" data-star=\"5\"><i class=\"bi bi-star-fill\" style=\"font-size: 1rem; color: gray;\"></i></span>"
									break;
								case "4":
									dataHtml += " <span class=\"star\" data-star=\"1\"><i class=\"bi bi-star-fill\" style=\"font-size: 1rem; color: yellow;\"></i></span>"
											+ " <span class=\"star\" data-star=\"2\"><i class=\"bi bi-star-fill\" style=\"font-size: 1rem; color: yellow;\"></i></span>"
											+ " <span class=\"star\" data-star=\"3\"><i class=\"bi bi-star-fill\" style=\"font-size: 1rem; color: yellow;\"></i></span>"
											+ " <span class=\"star\" data-star=\"4\"><i class=\"bi bi-star-fill\" style=\"font-size: 1rem; color: yellow;\"></i></span>"
											+ " <span class=\"star\" data-star=\"5\"><i class=\"bi bi-star-fill\" style=\"font-size: 1rem; color: gray;\"></i></span>"
									break;
								case "5":
									dataHtml += " <span class=\"star\" data-star=\"1\"><i class=\"bi bi-star-fill\" style=\"font-size: 1rem; color: yellow;\"></i></span>"
											+ " <span class=\"star\" data-star=\"2\"><i class=\"bi bi-star-fill\" style=\"font-size: 1rem; color: yellow;\"></i></span>"
											+ " <span class=\"star\" data-star=\"3\"><i class=\"bi bi-star-fill\" style=\"font-size: 1rem; color: yellow;\"></i></span>"
											+ " <span class=\"star\" data-star=\"4\"><i class=\"bi bi-star-fill\" style=\"font-size: 1rem; color: yellow;\"></i></span>"
											+ " <span class=\"star\" data-star=\"5\"><i class=\"bi bi-star-fill\" style=\"font-size: 1rem; color: yellow;\"></i></span>"
									break;
								}

								dataHtml += "	</div>"
								dataHtml += "      <div style=\"display: inline-block; class=\"mt-0 text-right\">"
										+ data[count].evaluation_time
										+ "   </div> "

								dataHtml += " <div>"
										+ data[count].evaluation_class
										+ "    </div>"
								dataHtml += "    </div>"
								dataHtml += "</div>"

								$(".Evaluation_context").prepend(dataHtml);
								count++;
							}
							console.log(data);

						} // success
					}) // ajax

		}

		//點擊作業or新作業
		$("a.dropdown-item").on("click", function(e) {
			//處理不能再次點選問題
			//讓所有作業不反白
			$("a.dropdown-item").removeClass("active");
		});
	</script>


</body>

</html>