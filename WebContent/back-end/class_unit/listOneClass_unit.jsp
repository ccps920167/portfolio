<!-- 設定JSP編碼 -->
<%@page import="com.class_unit.model.Class_unitVO"%>
<%@page import="com.class_unit.model.Class_unitService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<!-- 設定標籤庫 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 設定import資料 -->
<%@ page import="java.util.*"%>
<%@ page import="com.class_unit.*"%>


<%
	List<String> errorMsgs = (List) request.getAttribute("errorMsgs");
	Class_unitVO class_unitVO = (Class_unitVO) request.getAttribute("class_unitVO");
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
<link
	href="<%=request.getContextPath()%>/vendors/video-js/css/video-js.css"
	rel="stylesheet" />
<style>
/* 套用全部 */
* {
	box-sizing: border-box;
}

div.overlay {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100vh;
	background-color: hsla(0, 0%, 0%, .5);
	/* 預設：完全透明；設定 z-index: -1 會在整個頁面的後方，看不到，才不會擋到頁面元素。 */
	opacity: 0;
	z-index: -1;
	transition: opacity 1s;
}

div.overlay.-on {
	/* 加上 -on 之後，Modal 漸漸出現，因為有設定 transition */
	opacity: 1;
	z-index: 1;
}

div.overlay.-opacity-zero {
	/* 關閉 Modal 時，先讓 opacity設定0，就會慢慢變成完全透明，但這時會擋到後面的元素，所以要再移除 -on 及 -opacity-zero 這兩個 class */
	opacity: 0;
}

/* 元素 article 置中及基本樣式 */
div.overlay>article {
	background-color: white;
	width: 90%;
	max-width: 800px;
	border-radius: 10px;
	box-shadow: 0 0 10px #ddd;
	padding: 10px;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
}
</style>
<title>所有副課程類別 - listAll.jsp</title>
</head>

<body bgcolor='white'>
	<div class="container">
		<h1>所有單元類別</h1>
		<a
			href="<%=request.getContextPath()%>/back-end/class_unit/select_page.jsp">class_unit:
			Home</a> <br>
		<!--如果req屬性內有存在errorMsgs，引進屬性內資料 -->
		錯誤表列
		<c:if test="條件式">
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
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
			<tr>
				<th scope="row">1</th>
				<td><%=class_unitVO.getUnit_id()%></td>
				<td><%=class_unitVO.getUnit_name()%></td>
				<td><%=class_unitVO.getChapter_id()%></td>
				<td><%=class_unitVO.getVideo_long()%></td>
				<td>
					<div class="overlay">
						<article>
							<h2>
								<%=class_unitVO.getUnit_id()%>：
								<%=class_unitVO.getUnit_name()%>
							</h2>
							<video id="<%=class_unitVO.getUnit_id()%>"
								data-id="<%=class_unitVO.getUnit_id()%>"
								class="video-js vjs-fluid " controls preload="auto"
								data-setup="{}">
								<source id="video_url"
									src="<%=request.getContextPath()%>/Class_unit/Class_unitServlet?unit_id=<%=class_unitVO.getUnit_id()%>&action=many_video"
									type="video/mp4" />
							</video>
							<button type="button" class="btn_modal_close">關閉</button>
						</article>
					</div> 
					<%
					 	if (class_unitVO.getVideo().length == 0) {
					 		out.write("<button type=\"button\" disabled class=\"btn_modal btn btn-primary\">無影片</button>");
					 	} else {
					 		out.write("<button type=\"button\" class=\"btn_modal btn btn-primary\">觀看影片</button>");
					 	}
 					%>
				</td>
				
				<td>
				<% 
					if (class_unitVO.getVideo_status()==0) {
						out.write("<button type=\"button\" data-id=\""+class_unitVO.getVideo_status()+"\" class=\"btn btn-primary btn_status btn_status_0\" value=\"0\">下架</button>");
					} else {
						out.write("<button type=\"button\" data-id=\""+class_unitVO.getVideo_status()+"\" class=\"btn btn-primary btn_status \" value=\"1\">上架</button>");
					}
				%>
				
				</td>

				<td>
				<%
					java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				%>
				<p><%=(class_unitVO.getVideo_updatetime() == null) ? "": df.format(class_unitVO.getVideo_updatetime())%></p>
				</td>

				<td>
					<FORM METHOD="post" ACTION="#" style="margin-bottom: 0px;">
						<input class="btn btn-primary " type="submit" value="查看詳情">
						<input type="hidden" name="unit_id"
							value="<%=class_unitVO.getUnit_id()%>"> <input
							type="hidden" name="action" value="#">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Class_unit/Class_unitServlet"
						style="margin-bottom: 0px;">
						<input class="btn btn-primary " type="submit" value="修改">
						<input type="hidden" name="unit_id"
							value="<%=class_unitVO.getUnit_id()%>"> <input
							type="hidden" name="action" value="update_unit_id">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Class_unit/Class_unitServlet"
						style="margin-bottom: 0px;">
						<input class="btn btn-primary " type="submit" value="刪除">
						<input type="hidden" name="unit_id"
							value="<%=class_unitVO.getUnit_id()%>"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</table>
	</div>
	<!-- JavaScript -->
	<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/video-js/js/video.js"></script>
	<script>



		$(function () {
			$("table").on("click", "button.btn_status", function () {
				var data_updata;
				var that = $(this);
				if ($(this).closest("td").find("button").hasClass("btn_status_0")) {
					data_updata = {
						"action": "video_status_update",
						"unit_id": $(this).closest("td").find("button").attr("data-id"),
						"video_status": "1"
					}
				} else {
					data_updata = {
						"action": "video_status_update",
						"unit_id": $(this).closest("td").find("button").attr("data-id"),
						"video_status": "0"
					}
				}
				$.ajax({
					url: "<%=request.getContextPath()%>/Class_unit/Class_unitServlet", 
					type : "GET", // GET | POST | PUT | DELETE | PATCH
					data: data_updata, // 傳送資料到指定的 url
					success: function (data) { // request 成功取得回應後執行
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
				$(this).closest("td").find("div").addClass("-on");
			});

			// 關閉 Modal
			$("table").on("click", "button.btn_modal_close", function () {
				$(this).closest("td").find("div").addClass("-opacity-zero");
				// $("video").pause();
				// 設定隔一秒後，移除相關 class
				setTimeout(function () {
					$("div.overlay").removeClass("-on -opacity-zero");
				}, 2000);
			});

		});
	</script>
</body>
</html>