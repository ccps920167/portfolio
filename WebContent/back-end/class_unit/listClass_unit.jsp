<!-- 設定JSP編碼 -->
<%@page import="com.class_unit.model.Class_unitVO" %>
<%@page import="com.class_unit.model.Class_unitService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<!-- 設定標籤庫 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 設定import資料 -->
<%@ page import="java.util.*" %>
<%@ page import="com.class_unit.*" %>


<% 

List<String> errorMsgs = (List)request.getAttribute("errorMsgs");
Class_unitService class_unitSvc=new Class_unitService(); 
List<Class_unitVO> list =class_unitSvc.getAll();
pageContext.setAttribute("list", list);
%>


<html>

<head>
	<meta name="viewport"
		content="width=device-width, initial-scale=1.0, user-scalable=yes">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css"
		integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
		crossorigin="anonymous">
	<link href="<%=request.getContextPath()%>/vendors/video-js/css/video-js.css"
		rel="stylesheet" />
	<style>
		/* 套用全部 */
		* {
			box-sizing: border-box;
		}

	</style>
	<title>所有副課程類別 - listAll.jsp</title>
</head>

<body bgcolor='white'>
	<div class="container">
		<h1>所有單元類別</h1>
	<a href="<%=request.getContextPath()%>/back-end/class_unit/select_page.jsp">class_unit: Home</a>
		<br>
		<!--如果req屬性內有存在errorMsgs，引進屬性內資料 -->
		<%-- 錯誤表列 --%>
			<%--<c:if test="條件式"> --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<!-- ========================錯誤列表結束============================= -->


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
						<th scope="col">刪除</th>
					</tr>

					<!--分頁 -->
					<%@ include file="page1.file" %>

						<!--迴圈取出所有物件 -->
						<c:forEach var="class_unitsVO" items="${list}"
							begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"
							varStatus="status">

							<tr>
								<th scope="row">${ status.index + 1}</th>
								<td>${class_unitsVO.unit_id}</td>
								<td>${class_unitsVO.unit_name}</td>
								<td>${class_unitsVO.chapter_id}</td>
								<td>${class_unitsVO.video_long}</td>
								<td>
								<!-- Modal -->
								<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
								  <div class="modal-dialog" role="document">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h5 class="modal-title" id="exampleModalLabel">${class_unitsVO.unit_id} ： ${class_unitsVO.unit_name}</h5>
								        <button type="button" class="close btn_modal_close" data-dismiss="modal" aria-label="Close">
								          <span aria-hidden="true">&times;</span>
								        </button>
								      </div>
								      <div class="modal-body">
											<video id="video"
												data-id="${class_unitsVO.unit_id}"
												class="video-js vjs-fluid " controls
												preload="auto" data-setup="{}">
												<source id="video_url"
													src="<%=request.getContextPath()%>/Class_unit/Class_unitServlet?unit_id=${class_unitsVO.unit_id}&action=many_video"
													type="video/mp4" />
											</video>
											</div>
<!-- 									      <div class="modal-footer"> -->
<!-- 									        <button type="button" class="btn btn-secondary btn_modal_close" data-dismiss="modal">Close</button> -->
<!-- 									      </div> -->
									    </div>
									  </div>
									</div>
									<c:if
										test="${  empty class_unitsVO.video}">
										<button type="button" disabled 
											class="btn_modal btn btn-primary ">無影片
										</button>
									</c:if>
									<c:if
										test="${not empty class_unitsVO.video}">
										<!-- Button trigger modal -->
										<button type="button" class="btn btn-primary btn_modal" data-toggle="modal" data-target="#exampleModal">
										  觀看影片
										</button>
									</c:if>


								</td>
								<td >
									<c:if
										test="${fn:contains(class_unitsVO.video_status, '0')}">
										<button type="button" data-id="${class_unitsVO.unit_id}"
											class="btn btn-primary btn_status btn_status_0"
											value="${class_unitsVO.video_status}">下架
										</button>
									</c:if>
									<c:if
										test="${fn:contains(class_unitsVO.video_status, '1')}">
										<button type="button"  data-id="${class_unitsVO.unit_id}"
											class="btn btn-primary btn_status"
											value="${class_unitsVO.video_status}">上架
										</button>
									</c:if>
								</td>
								<td><fmt:formatDate value="${class_unitsVO.video_updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							
								<td>
									<FORM METHOD="post" ACTION="#"
										style="margin-bottom: 0px;">
										<input class="btn btn-primary " type="submit"
											value="查看詳情">
										<input type="hidden" name="unit_id"
											value="${class_unitsVO.unit_id}"> <input
											type="hidden" name="action" value="#">
									</FORM>
								</td>
								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/Class_unit/Class_unitServlet"
										style="margin-bottom: 0px;">
										<input class="btn btn-primary " type="submit"
											value="修改">
										<input type="hidden" name="unit_id"
											value="${class_unitsVO.unit_id}"> <input
											type="hidden" name="action"
											value="update_unit_id">
									</FORM>
								</td>
								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/Class_unit/Class_unitServlet"
										style=" margin-bottom: 0px;">
										<input class="btn btn-primary " type="submit"
											value="刪除">
										<input type="hidden" name="unit_id"
											value="${class_unitsVO.unit_id}	"> <input
											type="hidden" name="action"
											value="delete">
									</FORM>
								</td>
							</tr>


						</c:forEach>
				</table>
				<%@ include file="page2.file" %>
	</div>

	<!-- JavaScript -->
	<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
<%-- 	<script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script> --%>
	<script src="<%=request.getContextPath()%>/vendors/video-js/js/video.js"></script>
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
					url: "<%=request.getContextPath()%>/Class_unit/Class_unitServlet",           // 資料請求的網址
					type: "GET",                  // GET | POST | PUT | DELETE | PATCH
					data: data_updata,               // 傳送資料到指定的 url
					success: function (data) {      // request 成功取得回應後執行
						var btn_html = "<button type=\"button\"class=\"btn btn-primary btn_status\"value=\"${class_unitsVO.video_status}\">XXX</button>"
						if (that.closest("td").find("button").hasClass("btn_status_0")) {
							that.closest("td").find("button").removeClass("btn_status_0")
							that.closest("td").find("button").text("上架")
						} else {
							that.closest("td").find("button").addClass("btn_status_0")
							that.closest("td").find("button").text("下架")
						}
					}
				});
			});

			// 開啟 Modal 彈跳視窗
			$("table").on("click", "button.btn_modal", function () {
				$(this).closest("td").find("div#exampleModal").addClass("show");
				$(this).closest("td").find("div#exampleModal").attr("style","display:block;padding-right: 15px");
				$(this).closest("td").find("div#exampleModal").attr("aria-modal","true");
				$(this).closest("td").find("div#exampleModal").attr("role","diolog");
			});


			// 關閉 Modal
			$("table").on("click", "button.btn_modal_close", function () {
				$(this).closest("td").find("div#exampleModal").removeClass("show");
				$(this).closest("td").find("div#exampleModal").attr("style","display:none");
				$(this).closest("td").find("div#exampleModal").removeattr("role");
			});
			
			 $("button.btn_modal_close").on("click",function(){
				  $(".vjs-playing").click(); 
			 });
		});


	</script>
</body>

</html>