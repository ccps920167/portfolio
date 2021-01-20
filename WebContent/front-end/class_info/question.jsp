<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
/* 留言的框線 */
.messageline {
	padding: 10px 30px;
	margin: 20px 40px 20px 40px;
}

.messageline1 {
	border: 1px solid black;
	padding: 10px 20px;
	margin: 20px 20px 30px 20px;
}

div.cr2-btn {
	padding-left: 840px;
}

div.task_add_block input.task_name {
	width: calc(100% - 50px);
	border: 1px solid lightgray;
	border-radius: 3px 0 0 3px;
	height: 40px;
	font-size: 2rem;
	padding: 5px 10px;
	outline: none;
	display: inline-block;
	vertical-align: top;
}

/* ===== 重要性的星號 ===== */
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

div.star_block>span.star {
	cursor: pointer;
	display: inline-block;
	margin-right: 3px;
}

div.star_block>span.star.-on {
	color: yellow;
}
</style>

	<div class="messageline">
			<div class="mainpic">
				<div style="height: 70px; margin-top: 10px; margin-bottom: 10px">
					<div style="display: inline-block;">
						<img class="shadow-sm  rounded"
							style="width: 80px; display: inline-block;"
							src="<%=request.getContextPath()%>/member_info/Member_info_frontServlet?action=member_pic&member_id=${member_infoVO.member_id}"
							class="mr-3" alt="...">
					</div>
					<div class="mainpic-body" style="display: inline-block;">
						<div style="display: inline-block;">
							<h5 class="mt-0">${member_infoVO.member_email}</h5>
						</div>
					</div>

				</div>
				<div class="task_add_block">
					<textarea id="question_name" class="shadow-sm p-3 bg-white rounded"
						style="resize: none; width: 700px; height: 100px;"></textarea>
				</div>
				<div>
					<button type="button" class="btn btn-danger" id="MainMsg_send">發送</button>
				</div>

			</div>
		</div>
		<script type="text/javascript">

		MainMsg_send
		
		//新增評價
		document.getElementById("MainMsg_send").addEventListener("click", function () {
			//取輸入資料
			var mainmsg_text = document.getElementById("question_name").value;
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

			var named;
			var data = {	
				"action": "insert",
				"class_id": "${class_infoVO.class_id}",  //${class_info.class_id}
				"member_id": "${member_infoVO.member_id}",
				"mainmsg_text": mainmsg_text,
				"msg_source": "課程頁面",
				"msg_status": "1"
			};
			let json_data = JSON.stringify(data);
			console.log(json_data);
			$.ajax({
				url: "<%=request.getContextPath()%>/PutMessage/PutMessage",
				type: "POST",                  // GET | POST | PUT | DELETE | PATCH
				data: json_data,               // 傳送資料到指定的 url
				dataType: "json",
				success: function (data) {      // request 成功取得回應後執行
					console.log(data);
					var dataHtml = ""
						dataHtml += "<div class=\"media shadow-sm p-3 mb-5 bg-white rounded data-mainMsg\" data-mainMsg=\""
							+ data.mainmsg_id + "\">"
						dataHtml += "    <img style=\"width: 80px; display: inline-block;\" src=\"<%=request.getContextPath()%>/member_info/Member_info_frontServlet?action=member_pic&member_id="
							+ "${member_infoVO.member_id}"
							+ "\" class=\"mr-3\" alt=\"...\">"
						dataHtml += "    <div class=\"media-body\">"
						dataHtml += "	<div style=\"margin-right: 100px\" >"
						dataHtml += "      <div style=\"display: inline-block; class=\"mt-0\"><h6>"
							+ "${member_infoVO.member_email}" + "</h6>   </div> "
						dataHtml += "	</div>"
						dataHtml += "      <div style=\"display: inline-block; class=\"mt-0 text-right\"><p class=\"text-secondary\">" + newDate.getFullYear() + "-" + Month + "-" + TodayDate + " " + newDate.getHours() + ":" + Minutes + ":" + Second 
							+ "</p></div> "

						dataHtml += " <div>" + data.mainmsg_text
							+ "    </div>"
						dataHtml += " <div>"
							+ "<button class=\"btn btn-sm subMessage\" type=\"button\" style=\"text-align: right;background-color: white; border: 1px tomato solid;\"><b style=\"color: rgb(252, 86, 86);\">查看回覆</b></button>"
							+ "    </div>"
						dataHtml += " <div class=\"subMessageContent\">"
							+ "    </div>"

						dataHtml += "    </div>"
						dataHtml += "</div>"

						$(".question_context").prepend(dataHtml);
						$("#question_name").val("");// 留言的資料送出後清空

				} //success
			}) //ajax
		}) //click
		
		
		
		
		
		
		
		</script>