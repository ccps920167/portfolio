<!-- �]�wJSP�s�X -->
<%@page import="com.class_unit.model.Class_unitVO"%>
<%@page import="com.class_unit.model.Class_unitService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<!-- �]�w���Үw -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- �]�wimport��� -->
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
/* �M�Υ��� */
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
	/* �w�]�G�����z���F�]�w z-index: -1 �|�b��ӭ��������A�ݤ���A�~���|�ר쭶�������C */
	opacity: 0;
	z-index: -1;
	transition: opacity 1s;
}

div.overlay.-on {
	/* �[�W -on ����AModal �����X�{�A�]�����]�w transition */
	opacity: 1;
	z-index: 1;
}

div.overlay.-opacity-zero {
	/* ���� Modal �ɡA���� opacity�]�w0�A�N�|�C�C�ܦ������z���A���o�ɷ|�ר�᭱�������A�ҥH�n�A���� -on �� -opacity-zero �o��� class */
	opacity: 0;
}

/* ���� article �m���ΰ򥻼˦� */
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
<title>�Ҧ��ƽҵ{���O - listAll.jsp</title>
</head>

<body bgcolor='white'>
	<div class="container">
		<h1>�Ҧ��椸���O</h1>
		<a
			href="<%=request.getContextPath()%>/back-end/class_unit/select_page.jsp">class_unit:
			Home</a> <br>
		<!--�p�Greq�ݩʤ����s�berrorMsgs�A�޶i�ݩʤ���� -->
		���~��C
		<c:if test="����">
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">�Эץ��H�U���~:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
		</c:if>
		<!-- ========================���~�C����============================= -->


		<table class="table table-striped">
			<tr>
				<th scope="col">#</th>
				<th scope="col">�椸�s��</th>
				<th scope="col">�椸�W��</th>
				<th scope="col">���ݳ��`</th>
				<th scope="col">�v���ɶ�</th>
				<th scope="col">�v���[��</th>
				<th scope="col">�v�����A</th>
				<th scope="col">��s�ɶ�</th>
				<th scope="col">�d�ݸԱ�</th>
				<th scope="col">�ק�</th>
				<th scope="col">�R��</th>
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
								<%=class_unitVO.getUnit_id()%>�G
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
							<button type="button" class="btn_modal_close">����</button>
						</article>
					</div> 
					<%
					 	if (class_unitVO.getVideo().length == 0) {
					 		out.write("<button type=\"button\" disabled class=\"btn_modal btn btn-primary\">�L�v��</button>");
					 	} else {
					 		out.write("<button type=\"button\" class=\"btn_modal btn btn-primary\">�[�ݼv��</button>");
					 	}
 					%>
				</td>
				
				<td>
				<% 
					if (class_unitVO.getVideo_status()==0) {
						out.write("<button type=\"button\" data-id=\""+class_unitVO.getVideo_status()+"\" class=\"btn btn-primary btn_status btn_status_0\" value=\"0\">�U�[</button>");
					} else {
						out.write("<button type=\"button\" data-id=\""+class_unitVO.getVideo_status()+"\" class=\"btn btn-primary btn_status \" value=\"1\">�W�[</button>");
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
						<input class="btn btn-primary " type="submit" value="�d�ݸԱ�">
						<input type="hidden" name="unit_id"
							value="<%=class_unitVO.getUnit_id()%>"> <input
							type="hidden" name="action" value="#">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Class_unit/Class_unitServlet"
						style="margin-bottom: 0px;">
						<input class="btn btn-primary " type="submit" value="�ק�">
						<input type="hidden" name="unit_id"
							value="<%=class_unitVO.getUnit_id()%>"> <input
							type="hidden" name="action" value="update_unit_id">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Class_unit/Class_unitServlet"
						style="margin-bottom: 0px;">
						<input class="btn btn-primary " type="submit" value="�R��">
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
					data: data_updata, // �ǰe��ƨ���w�� url
					success: function (data) { // request ���\���o�^�������
						if (that.closest("td").find("button").hasClass("btn_status_0")) {
							that.closest("td").find("button").removeClass("btn_status_0")
							that.closest("td").find("button").text("�W�[")
						} else {
							that.closest("td").find("button").addClass("btn_status_0")
							that.closest("td").find("button").text("�U�[")
						}
					}
				});
			});

			// �}�� Modal �u������
			$("table").on("click", "button.btn_modal", function () {
				$(this).closest("td").find("div").addClass("-on");
			});

			// ���� Modal
			$("table").on("click", "button.btn_modal_close", function () {
				$(this).closest("td").find("div").addClass("-opacity-zero");
				// $("video").pause();
				// �]�w�j�@���A�������� class
				setTimeout(function () {
					$("div.overlay").removeClass("-on -opacity-zero");
				}, 2000);
			});

		});
	</script>
</body>
</html>