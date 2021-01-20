<%@page import="com.class_info.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.allClass_info.model.*"%>
<%@ page import="com.class_chapter.model.*"%>
<%@ page import="com.class_unit.model.*"%>
<!DOCTYPE html>
<%
	AllClass_infoVO AllClass_infoVO = (AllClass_infoVO) session.getAttribute("AllClass_infoVO");
	Map<Class_chapterVO, List<Class_unitVO>> chapterAndUnit = AllClass_infoVO.getChapterAndUnit();
	Set<Class_chapterVO> keys = (Set<Class_chapterVO>)chapterAndUnit.keySet();
	List<Class_chapterVO> list = (List<Class_chapterVO>)new ArrayList();
	pageContext.setAttribute("chapterAndUnit", chapterAndUnit);
	pageContext.setAttribute("keys", keys);
%>


<html>
<head>
<meta charset="BIG5">
<title>TOMATO後台管理平台</title>


<!---------------------------------------- css ---------------------------------------->

<!-- jquery.datetimepicker -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.css" />


<!-- jquery.datetimepicker -->
<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>
<!---------------------------------------- css ---------------------------------------->



</head>
<body>
	<%@ include file="header-back.jsp"%>

	<div style="margin-left: 250px; margin-right: 20px; margin-top: 50px" >
		<nav aria-label="breadcrumb" style="margin-top: 30px;">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Home</a></li>
				<li class="breadcrumb-item active" aria-current="page">課程管理</li>
				<li class="breadcrumb-item active" aria-current="page">課程列表</li>
				<li class="breadcrumb-item active" aria-current="page">章節及單元</li>
			</ol>
		</nav>
		<br>
		<div class="container">
			<h1>所有單元類別</h1>
			<c:if test="${empty chapterAndUnit}">
				<img src="<%=request.getContextPath()%>/img/NoResult/NoResult.jpg">
			</c:if>
			<!--課程章節 -->
			<div id="accordion">
				<!--章節迴圈     Start-->
				<c:forEach var="Class_chapterVO_list" items="${keys}"
					varStatus="loop">
					<div class="card">
						<div class="card-header" id="heading_${loop.index}">
							<h5 class="mb-0">
								<button class="btn btn-link collapsed" data-toggle="collapse"
									data-target="#collapse_${loop.index}" aria-expanded="false"
									aria-controls="collapse_${loop.index}"
									data-chapterID="${Class_chapterVO_list.chapter_id}">
									${Class_chapterVO_list.chapter_id} : ${Class_chapterVO_list.chapter_name}</button>
							</h5>
						</div>
						<!--單元迴圈 Start-->
						

							
							<div id="collapse_${loop.index}" class="collapse"
								aria-labelledby="heading_${loop.index}" data-parent="#accordion">
								<div class="card-body" style="padding: 0%;">
									<table class="table table-striped">
										<tr>
											<th scope="col">#</th>
											<th scope="col">單元編號</th>
											<th scope="col">單元名稱</th>
											<th scope="col">所屬章節</th>
											<th scope="col">影片時間</th>
											<th scope="col">影片觀看</th>
											<th scope="col">影片狀態</th>
											<th scope="col">更新時間</th>
											<th scope="col">查看詳情</th>
											<th scope="col">修改</th>
										</tr>
										<!--迴圈取出所有物件 -->
							<c:forEach var="Class_unit" items="${chapterAndUnit[Class_chapterVO_list]}" >

											<tr>
												<th scope="row">${ status.index + 1}</th>
												<td>${Class_unit.unit_id}</td>
												<td>${Class_unit.unit_name}</td>
												<td>${Class_unit.chapter_id}</td>
												<td>${Class_unit.video_long}</td>
												<td>
													<!-- Modal -->
													<div class="modal fade" id="exampleModal" tabindex="-1"
														role="dialog" aria-labelledby="exampleModalLabel"
														aria-hidden="true">
														<div class="modal-dialog" role="document">
															<div class="modal-content">
																<div class="modal-header">
																	<h5 class="modal-title" id="exampleModalLabel">${Class_unit.unit_id}
																		： ${Class_unit.unit_name}</h5>
																	<button type="button" class="close btn_modal_close"
																		data-dismiss="modal" aria-label="Close">
																		<span aria-hidden="true">&times;</span>
																	</button>
																</div>
																<div class="modal-body">
																	<video width="320" height="240" id="video" data-id="${Class_unit.unit_id}"
																		class="video-js vjs-fluid " controls preload="auto"
																		data-setup="{}">
																		<source id="video_url"
																			src="<%=request.getContextPath()%>/Class_unit/Class_unitServlet?unit_id=${Class_unit.unit_id}&action=many_video"
																			type="video/mp4" />
																	</video>
																</div>
															</div>
														</div>
													</div> <c:if test="${  empty Class_unit.video}">
														<button type="button" disabled
															class="btn_modal btn btn-danger ">無影片</button>
													</c:if> <c:if test="${not empty Class_unit.video}">
														<!-- Button trigger modal -->
														<button type="button" class="btn btn-danger btn_modal"
															data-toggle="modal" data-target="#exampleModal">
															觀看影片</button>
													</c:if>


												</td>
												<td><c:if
														test="${fn:contains(Class_unit.video_status, '0')}">
														<button type="button" data-id="${class_unitsVO.unit_id}"
															class="btn btn-danger btn_status btn_status_0"
															value="${Class_unit.video_status}">下架</button>
													</c:if> <c:if test="${fn:contains(Class_unit.video_status, '1')}">
														<button type="button" data-id="${Class_unit.unit_id}"
															class="btn btn-danger btn_status"
															value="${Class_unit.video_status}">上架</button>
													</c:if></td>
												<td><fmt:formatDate
														value="${Class_unit.video_updatetime}"
														pattern="yyyy-MM-dd HH:mm:ss" /></td>


												<td>
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/Class_unit/Class_unitServlet"
														style="margin-bottom: 0px;">
														<input class="btn btn-danger " type="submit" value="修改">
														<input type="hidden" name="unit_id"
															value="${class_unitsVO.unit_id}"> <input
															type="hidden" name="action" value="update_unit_id">
													</FORM>
												</td>
												<td>
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/Class_unit/Class_unitServlet"
														style="margin-bottom: 0px;">
														<input class="btn btn-danger " type="submit" value="刪除">
														<input type="hidden" name="unit_id"
															value="${class_unitsVO.unit_id}	"> <input
															type="hidden" name="action" value="delete">
													</FORM>
												</td>
											</tr>
										</c:forEach>
									</table>
								</div>
							</div>
						<!--單元迴圈END-->
					</div>
				</c:forEach>
				<!--章節迴圈     END-->
			</div>
		</div>

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

		<script>
			$.datetimepicker.setLocale('zh');
			$('#f_date1').datetimepicker({
				theme : '', //theme: 'dark',
				timepicker : false, //timepicker:true,
				step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
				format : 'Y-m-d H:i:s', //format:'Y-m-d H:i:s',
				// 		value : new Date(), // value:   new Date(),
			//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
			//startDate:	            '2017/07/10',  // 起始日
			//minDate:               '-1970-01-01', // 去除今日(不含)之前
			//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
			});

			$.datetimepicker.setLocale('zh');
			$('#f_date2').datetimepicker({
				theme : '', //theme: 'dark',
				timepicker : false, //timepicker:true,
				step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
				format : 'Y-m-d H:i:s', //format:'Y-m-d H:i:s',
				// 		value : new Date(), // value:   new Date(),
			//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
			//startDate:	            '2017/07/10',  // 起始日
			//minDate:               '-1970-01-01', // 去除今日(不含)之前
			//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
			});

			var somedate1 = new Date();
			$('#f_date1')
					.datetimepicker(
							{
								beforeShowDay : function(date) {
									if (date.getYear() < somedate1.getYear()
											|| (date.getYear() == somedate1
													.getYear() && date
													.getMonth() < somedate1
													.getMonth())
											|| (date.getYear() == somedate1
													.getYear()
													&& date.getMonth() == somedate1
															.getMonth() && date
													.getDate() < somedate1
													.getDate())) {
										return [ false, "" ]
									}
									return [ true, "" ];
								}
							});

			var somedate1 = new Date('2017-06-15');
			$('#f_date2')
					.datetimepicker(
							{
								beforeShowDay : function(date) {
									if (date.getYear() < somedate1.getYear()
											|| (date.getYear() == somedate1
													.getYear() && date
													.getMonth() < somedate1
													.getMonth())
											|| (date.getYear() == somedate1
													.getYear()
													&& date.getMonth() == somedate1
															.getMonth() && date
													.getDate() < somedate1
													.getDate())) {
										return [ false, "" ]
									}
									return [ true, "" ];
								}
							});
		</script>


		<script>



		$(function () {

			
			$("table").on("click", "button.btn_status", function () {
				var data_updata;
				var that = $(this);
				if ($(this).closest("td").find("button").hasClass("btn_status_0")) {
					data_updata={
						"action" : "video_status_update",
						"unit_id" : $(this).closest("td").find("button").attr("data-id"),
						"video_status" :"1"
					}
				} else {
					data_updata={
						"action" : "video_status_update",
						"unit_id" : $(this).closest("td").find("button").attr("data-id"),
						"video_status" :"0"
					}
				}
				$.ajax({
					url: "<%=request.getContextPath()%>/Class_unit/Class_unitServlet", // 資料請求的網址
												type : "GET", // GET | POST | PUT | DELETE | PATCH
												data : data_updata, // 傳送資料到指定的 url
												success : function(data) { // request 成功取得回應後執行
													var btn_html = "<button type=\"button\"class=\"btn btn-primary btn_status\"value=\"${class_unitsVO.video_status}\">XXX</button>"
													if (that
															.closest("td")
															.find("button")
															.hasClass(
																	"btn_status_0")) {
														that
																.closest("td")
																.find("button")
																.removeClass(
																		"btn_status_0")
														that.closest("td")
																.find("button")
																.text("上架")
													} else {
														that
																.closest("td")
																.find("button")
																.addClass(
																		"btn_status_0")
														that.closest("td")
																.find("button")
																.text("下架")
													}
												}
											});
								});

				// 開啟 Modal 彈跳視窗
				$("table")
						.on(
								"click",
								"button.btn_modal",
								function() {
									$(this).closest("td").find(
											"div#exampleModal")
											.addClass("show");
									$(this)
											.closest("td")
											.find("div#exampleModal")
											.attr("style",
													"display:block;padding-right: 15px");
									$(this).closest("td").find(
											"div#exampleModal").attr(
											"aria-modal", "true");
									$(this).closest("td").find(
											"div#exampleModal").attr("role",
											"diolog");
								});

				// 關閉 Modal
				$("table").on(
						"click",
						"button.btn_modal_close",
						function() {
							$(this).closest("td").find("div#exampleModal")
									.removeClass("show");
							$(this).closest("td").find("div#exampleModal")
									.attr("style", "display:none");
							$(this).closest("td").find("div#exampleModal")
									.removeattr("role");
						});

				$("button.btn_modal_close").on("click", function() {
					$(".vjs-playing").click();
				});
			});
		</script>
</body>

</html>