<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.evaluation.model.*"%>

<%
	EvaluationVO evaulationVO = (EvaluationVO) request.getAttribute("evaulationVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>評價</title>

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

</head>
<body>
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
							<span class="star" data-star="1"><i
								class="bi bi-star-fill" style="font-size: 1rem; color: gray;"></i></span>
							<span class="star" data-star="2"><i
								class="bi bi-star-fill" style="font-size: 1rem; color: gray;"></i></span>
							<span class="star" data-star="3"><i
								class="bi bi-star-fill" style="font-size: 1rem; color: gray;"></i></span>
							<span class="star" data-star="4"><i
								class="bi bi-star-fill" style="font-size: 1rem; color: gray;"></i></span>
							<span class="star" data-star="5"><i
								class="bi bi-star-fill" style="font-size: 1rem; color: gray;"></i></span>
						</div>
					</div>

				</div>
				<div class="task_add_block">
					<textarea id="task_name" class="shadow-sm p-3 bg-white rounded"
						style="resize: none; width: 700px; height: 100px;"></textarea>
				</div>
				<div>
					<button type="button" class="btn btn-danger" id="btn_send">發送</button>
					<button type="button" class=" btn btn-danger btn_delete">清除</button>
				</div>

			</div>
		</div>

</body>
</html>