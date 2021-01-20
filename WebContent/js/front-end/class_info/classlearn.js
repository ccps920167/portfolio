
$(function () {
	var $li = $('ul.tab-title li');
	$($li.eq(0).addClass('active').find('a').attr('href')).siblings('.tab-inner').hide();

	$li.click(function () {
		$($(this).find('a').attr('href')).show().siblings('.tab-inner').hide();
		$(this).addClass('active').siblings('.active').removeClass('active');
	});
});


init_evaluation();

window.document.body.onload = function () {
	var url_video = $("div#accordion").find("button").attr("data-unitID");
	$("#video_url").closest("video").attr("src", "<%=request.getContextPath()%>/Class_unit/Class_unitServlet?unit_id=" + url_video + "&action=many_video");
	$("#video_url").attr("src", "<%=request.getContextPath()%>/Class_unit/Class_unitServlet?unit_id=" + url_video + "&action=many_video");
}
//按下列表按鈕時，轉換影片路徑
$("div#accordion").on("click", "button.class-btn", function () {
	var url_video = $(this).attr("data-unitID");
	$("#video_url").closest("video").attr("src", "<%=request.getContextPath()%>/Class_unit/Class_unitServlet?unit_id=" + url_video + "&action=many_video");
	$("#video_url").attr("src", "<%=request.getContextPath()%>/Class_unit/Class_unitServlet?unit_id=" + url_video + "&action=many_video");
});

//離開時抓影片時間點
window.document.body.onbeforeunload = function () {
	var myPlayer = videojs('my-video');
	var whereYouAt = myPlayer.currentTime();
	var unit_id = $("video").attr("src");
	var data_updata = {
		"action": "getNewTime",
		"record_id": "",
		"member_id": "${member_infoVO.member_id}",
		"unit_id": unit_id,
		"class_last": whereYouAt
	};
	var that = $(this);
	$.ajax({
		url: "/TEA102G5/Video_record/Video_recordServlet", // 資料請求的網址
		type: "GET", // GET | POST | PUT | DELETE | PATCH
		data: data_updata, // 傳送資料到指定的 url
	});
}

$("div.mainpic-body").on("click", "span.star", function (e) {
	let star;
	var star_but = $(this);
	if (star_but.closest("span.star").hasClass("-on")) {
		star_but.nextAll("span.star").removeClass("-on");
		star_but.nextAll("span.star").children("i").attr("style", "font-size: 1rem; color: gray")
		// 				        star_but.closest("li").attr("star", star_but.closest("span.star").children("i").attr("style","font-size: 1rem; color: gray"));
		let star = star_but.closest("span.star").attr("data-star");
		$("#task_name").attr("data-star", star_but.closest("span.star").attr("data-star"));

		console.log(star)
	} else {
		// 點擊星號的時候，該星號加上 -on 這個 class，然後該筆待辦事項
		star_but.prevAll("span.star").addClass("-on");
		star_but.closest("span.star").addClass("-on");
		star_but.prevAll("span.star").children("i").attr("style", "font-size: 1rem; color: yellow");
		star_but.closest("span.star").children("i").attr("style", "font-size: 1rem; color: yellow");
		star_but.closest("li").attr("star", star_but.closest("span.star").attr("data-star"));
		$("#task_name").attr("data-star", star_but.closest("span.star").attr("data-star"));
		console.log(star)
	}

});



//AJAX 新增
document.getElementById("btn_send").addEventListener("click", function () {
	//取輸入資料
	var evaluation_class = document.getElementById("task_name").value;
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
		"evaluation_class": evaluation_class,
		"evaluation_score": $("#task_name").attr("data-star"),
		"evaluation_status": "1"
	};
	let json_data = JSON.stringify(data);
	$.ajax({
		url: "<%=request.getContextPath()%>/Evaluation/putEvaluation",
		type: "POST",                  // GET | POST | PUT | DELETE | PATCH
		data: json_data,               // 傳送資料到指定的 url
		dataType: "json",
		success: function (data) {      // request 成功取得回應後執行
			console.log(data);
			var dataHtml = ""
			dataHtml += "<div class=\"media shadow-sm p-3 mb-5 bg-white rounded\">"
			dataHtml += "    <img style=\"width: 80px; display: inline-block;\" src=\"<%=request.getContextPath()%>/member_info/Member_info_frontServlet?action=member_pic&member_id=" + data.member_id + "\" class=\"mr-3\" alt=\"...\">"
			dataHtml += "    <div class=\"media-body\">"
			dataHtml += "	<div style=\"margin-right: 100px\" >"
			dataHtml += "      <div style=\"display: inline-block; class=\"mt-0\">" + "${member_infoVO.member_email}" + "</div> "

			switch ($("#task_name").attr("data-star")) {
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
			dataHtml += "      <div style=\"display: inline-block; class=\"mt-0 text-right\">" + newDate.getFullYear() + "-" + Month + "-" + TodayDate + " " + newDate.getHours() + ":" + Minutes + ":" + Second + "   </div> "

			dataHtml += " <div>" + data.evaluation_class + "    </div>"
			dataHtml += "    </div>"
			dataHtml += "</div>"

			$(".Evaluation_context").prepend(dataHtml);
			$("#task_name").val("");// 留言的資料送出後清空

		} //success
	}) //ajax
}) //click

//   清除鍵         
$("btn_delete").on("click", function () {
	$("task_name").val("");
	console.log("清除")
})

//自動讀取評價
function init_evaluation() {
	var data = {
		"action": "getAll",
		"class_id": "${class_infoVO.class_id}",  //${class_info.class_id}
	};

	let json_data = JSON.stringify(data);
	console.log(json_data);
	$.ajax({
		url: "<%=request.getContextPath()%>/Evaluation/putEvaluation",
		type: "POST",                  // GET | POST | PUT | DELETE | PATCH
		data: json_data,               // 傳送資料到指定的 url
		dataType: "json",
		success: function (data) {      // request 成功取得回應後執行
			var count = 0;
			while (true) {
				console.log(data[count].member_id);
				var dataHtml = ""
				dataHtml += "<div class=\"media shadow-sm p-3 mb-5 bg-white rounded\">"
				dataHtml += "    <img style=\"width: 80px; display: inline-block;\" src=\"<%=request.getContextPath()%>/member_info/Member_info_frontServlet?action=member_pic&member_id=" + data[count].member_id + "\" class=\"mr-3\" alt=\"...\">"
				dataHtml += "    <div class=\"media-body\">"
				dataHtml += "	<div style=\"margin-right: 100px\" >"
				dataHtml += "      <div style=\"display: inline-block; class=\"mt-0\">" + data[count].member_email + "   </div> "
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
				dataHtml += "      <div style=\"display: inline-block; class=\"mt-0 text-right\">" + data[count].evaluation_time + "   </div> "

				dataHtml += " <div>" + data[count].evaluation_class + "    </div>"
				dataHtml += "    </div>"
				dataHtml += "</div>"

				$(".Evaluation_context").prepend(dataHtml);
				count++;
			}
			console.log(data);


		} //success
	}) //ajax

}

