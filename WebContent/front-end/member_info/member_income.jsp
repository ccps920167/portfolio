<%@page import="java.sql.Time"%>
<%@page import="java.sql.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.member_info.model.*"%>
<%@ page import="com.view_class_income.model.*"%>
<%@page import="com.order_list.model.*"%>
<%@page import="com.order_info.model.*"%>

<%
	Map<View_class_incomeVO , Map<Order_infoVO,Order_listVO>>  income_list = (Map<View_class_incomeVO , Map<Order_infoVO,Order_listVO>>) request.getAttribute("income");
	Set<View_class_incomeVO> class_list = income_list.keySet();
	pageContext.setAttribute("class_list", class_list);
	pageContext.setAttribute("income_list", income_list);

%>

<html lang="en">

<head>

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
<!-- css -->
<link href="<%=request.getContextPath()%>/css/header.css"
	rel="stylesheet" />



<style>

/* ������ */
#content {
	margin-top: 10px;
	margin-bottom: 50px;
}

/* The switch - the box around the slider */
.switch {
	position: relative;
	display: inline-block;
	width: 60px;
	height: 34px;
}

/* Hide default HTML checkbox */
.switch input {
	opacity: 0;
	width: 0;
	height: 0;
}

/* The slider */
.slider {
	position: absolute;
	cursor: pointer;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background-color: #ccc;
	-webkit-transition: .4s;
	transition: .4s;
}

.slider:before {
	position: absolute;
	content: "";
	height: 26px;
	width: 26px;
	left: 4px;
	bottom: 4px;
	background-color: white;
	-webkit-transition: .4s;
	transition: .4s;
}

input:checked+.slider {
	background-color: #2196F3;
}

input:focus+.slider {
	box-shadow: 0 0 1px #2196F3;
}

input:checked+.slider:before {
	-webkit-transform: translateX(26px);
	-ms-transform: translateX(26px);
	transform: translateX(26px);
}

/* Rounded sliders */
.slider.round {
	border-radius: 34px;
}

.slider.round:before {
	border-radius: 50%;
}
</style>



<title>TOMATO - ���A�����������b�ǲߪ����x</title>

</head>
<body>

	<%@ include file="header.jsp"%>
	<!-- container -->

	<div class="container" >
		<!-- ���D -->
		<div class="jumbotron jumbotron-fluid shadow-sm rounded">
			<div class="container">
				<h1>���q�d��</h1>
			</div>
		</div>
		<!-- ���e -->
		<div class="container" style="margin-bottom: 50px">
			<c:if test="${empty class_list}">
				<img src="<%=request.getContextPath()%>/img/NoResult/NoResult.jpg">
			</c:if>
			<!--�ҵ{���` -->
			<div id="accordion">
				<!--���`�j��     Start-->
				<c:forEach var="income" items="${class_list}"
					varStatus="loop">
					<div class="card">
						<div class="card-header" id="heading_${loop.index}">
							<h5 class="mb-0">
								<table class="table table-striped" style="border-style:dotted; border-width:0.5px">
									<tr>
										<th scope="col">#</th>
										<th scope="col">�ҵ{�s��</th>
										<th scope="col">�ҵ{�W��</th>
										<th scope="col">�ҵ{���q</th>
										<th scope="col">�ҵ{�W�[�ɶ�</th>
									</tr>
									<tr>
											<td>
												<button  class="btn btn-danger collapsed" data-toggle="collapse"
												data-target="#collapse_${loop.index}" aria-expanded="false"
												aria-controls="collapse_${loop.index}" style="display: inline-block;"
												data-chapterID="${Class_chapterVO_list.chapter_id}">�i�}</button>
											</td>
											<td>${income.class_id}</td>
											<td>${income.class_name}</td>
											<td>${income.price}</td>
											<td><fmt:formatDate value="${income.startfund_date}"
													pattern="yyyy-MM-dd HH:mm:ss" /></td>
										</tr>	
									</table>
								
							</h5>
						</div>
						
						<!--�椸�j�� Start-->
						
							<div id="collapse_${loop.index}" class="collapse"
								aria-labelledby="heading_${loop.index}" data-parent="#accordion">
								<div class="card-body" style="padding: 0%;">
									<table class="table table-striped">
										<tr>
											<th scope="col">#</th>
											<th scope="col">�q�ʮɶ�</th>
											<th scope="col">�q�ʤ��</th>
											<th scope="col">���B</th>
										</tr>
										<!--�j����X�Ҧ����� -->
										<c:forEach var="order" items="${income_list[income]}" varStatus="status1">
												<tr>
												<th scope="row">${ status1.index + 1}</th>
												<td>${order.time}</td>
												<td>${order.purchase_plan}</td>
												<td>${order.price}</td>
												</tr>
										</c:forEach>
									</table>
								</div>
							</div>
						<!--�椸�j��END-->
					</div>
						<br>
				</c:forEach>
				<!--���`�j��     END-->
			</div>
		</div>
	</div>

	<%@ include file="footer.jsp"%>



	<!-- JavaScript -->
	<script
		src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>

	<script type="text/javascript">
	
	// �}�� Modal �u������
	$("table").on("click", "button.btn_modal", function () {
		$(this).closest("td").find("div#exampleModal").addClass("show");
		$(this).closest("td").find("div#exampleModal").attr("style","display:block;padding-right: 15px");
		$(this).closest("td").find("div#exampleModal").attr("aria-modal","true");
		$(this).closest("td").find("div#exampleModal").attr("role","diolog");
	});


	// ���� Modal
	$("table").on("click", "button.btn_modal_close", function () {
		$(this).closest("td").find("div#exampleModal").removeClass("show");
		$(this).closest("td").find("div#exampleModal").attr("style","display:none");
	});

	
	</script>
</body>
</html>





