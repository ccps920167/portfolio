
//讀取評價
init_evaluation();
//讀取留言
intiQuestion();

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


window.document.body.onload = function () {
	var url_video = $("div#accordion").find("button").attr("data-unitID");
	$("#video_url").closest("video").attr(
		"src",
		"<%=request.getContextPath()%>/Class_unit/Class_unitServlet?unit_id="
		+ url_video + "&action=many_video");
	$("#video_url").attr(
		"src",
		"<%=request.getContextPath()%>/Class_unit/Class_unitServlet?unit_id="
		+ url_video + "&action=many_video");
}


/*****************************************留言方法************************************/
// 觸發點開小留言
$("div.question_context")
	.on(
		"click",
		"button.subMessage",
		function () {
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
							// 成功取得回應後執行
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
				console.log(data);
				$(".question_context").empty();   //清除圖片
				var count = 0;
				while (true) {
					var dataHtml = ""
					dataHtml += "<div class=\"media shadow-sm p-3 mb-5 bg-white rounded\" data-mainMsg=\""
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
				console.log(data);
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
						+ data[count].member_email + "   </div> "
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
						+ data[count].evaluation_time + "   </div> "

					dataHtml += " <div>" + data[count].evaluation_class
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