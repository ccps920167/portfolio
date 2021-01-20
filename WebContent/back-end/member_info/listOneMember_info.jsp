<%@page import="java.sql.Time"%>
<%@page import="java.sql.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member_info.model.*"%>

<%
	Member_infoVO member_infoVO = (Member_infoVO) request.getAttribute("member_infoVO");
%>
<html lang="en">

<head>

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
<!-- Bootstrap CSS -->

<!---------------------------------------- css ---------------------------------------->

<!-- jquery.datetimepicker -->	
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.css" />
	
		
<!-- jquery.datetimepicker -->		
<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
  <!-- �Ϥ� -->		
  /* ������ */
	#content {
		margin-top: 10px;
		margin-bottom: 50px;
		border: 1px rgb(204, 204, 204) solid;
		padding: 20px 10px;
	}
	
	#preview {
		border: 1px solid lightgray;
		display: inline-block;
		/* width: 100%;
	            height: auto; */
		width: 150px;
		height: 200px;
		position: relative;
	}
	
	#preview span.text {
		position: absolute;
		display: inline-block;
		left: 50%;
		top: 50%;
		transform: translate(-50%, -50%);
		z-index: -1;
		color: lightgray;
	}
	
	#output_image {
		width: 150px;
		height: 200px;
	}
</style>
<!---------------------------------------- css ---------------------------------------->

</head>
<body>
	<%@ include file="header-back.jsp"%>

	<div style="margin-left: 250px; margin-right: 20px; margin-top: 100px" >
		<nav aria-label="breadcrumb" style="margin-top: 30px;">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Home</a></li>
				<li class="breadcrumb-item active" aria-current="page">�|���޲z</li>
				<li class="breadcrumb-item active" aria-current="page">�|���C��</li>
				<li class="breadcrumb-item active" aria-current="page"><%=member_infoVO.getMember_id()%></li>
			</ol>
		</nav>
		<br>
		<div class="container">
		<%-- ���~��C --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">�Эץ��H�U���~:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<div style="margin-bottom: 50px">
			<a href="<%=request.getContextPath()%>/Order_info/backOrder_DetailsSelevet?action=getOne_For_Display&member_id=<%=member_infoVO.getMember_id()%>"><button class="btn btn-danger">�d�ݷ|���q��</button></a>
			</div>
			<FORM class="g-3" METHOD="post"
				ACTION="<%=request.getContextPath()%>/member_info/Member_infoServlet"
				name="form1" enctype="multipart/form-data">
				<div class="row">
				<div class="col-3">
					<div class="card">
						<img
							src="<%=request.getContextPath()%>/member_info/Member_info_frontServlet?action=member_pic&member_id=${member_infoVO.member_id}"
							class="card-img-top" alt="...">
						<div class="card-body">
							<p style="text-align: center;"><%=member_infoVO.getMember_name()%></p>
						</div>
					</div>
						<input type="file" id="file" name="member_pic"
							value="<%=member_infoVO.getMember_pic()%>"
							onchange="preview_image(event)" style="display: none"> 
							<span>�|���s�� :<%=member_infoVO.getMember_id()%></span>
					</div>
					<div class="col-lg-9 col-sm-8 col-12">
						<div class="col-12 row">

							<div class="col-5">
								<label for="inputPassword1" class="form-label">�|���W��</label> <input
									type="text" class="form-control" id="inputPassword1"
									name="member_name" value="<%=member_infoVO.getMember_name()%>" disabled>
							</div>
							<div class="col-5">
								<label for="inputEmail4" class="form-label">Email</label> <input
									type="TEXT" class="form-control" id="inputEmail4"
									name="member_email"
									value="<%=member_infoVO.getMember_email()%>" disabled>
							</div>
						</div>

						<div class="col-12 row">

							<div class="col-5">
								<label for="inputPassword2" class="form-label">�|���K�X</label> <input
									type="TEXT" class="form-control" id="inputPassword2"
									name="member_password"
									value="<%=member_infoVO.getMember_password()%>" disabled>
							</div>
							<div class="col-5">
								<div>
									<label for="mem-role">�|������</label>
								</div>
								<select name="member_role" id="mem-role" class="form-control" disabled>
									<option value="0">�Ⱦǥͨ���</option>
									<option value="1">���Ѯv����</option>
								</select>
							</div>
						</div>
						<!-- �ʧO�B�ͤ� -->
						<div class="col-12 row">
							<div class="col-5">
								<div>
									<label for="mem-gender">�ʧO</label>
								</div>
								<select name="member_gender" id="mem-gender"
									class="form-control" disabled>
									<option value="0">�k��</option>
									<option value="1">�k��</option>
									<option value="2">�h���ʧO</option>
								</select>
							</div>
							<div class="col-5">
								<div>
									<label for="f_date2">�ͤ�</label>
								</div>
								<input name="member_birthday" id="f_date2" type="text"
									class="form-control" disabled>

							</div>
						</div>
						<!-- ¾�~ -->
						<div class="col-5">
							<div>

								<label for="job">¾�~</label>
							</div>
							<div>
								<input type="TEXT" name="member_occupation" id="job"
									class="form-control"
									value="<%=member_infoVO.getMember_occupation()%>" disabled>
							</div>
						</div>
						<!-- �a�} -->
						<div class="col-12">
							<div>
								<label for="addr">�a�}</label>
							</div>

							<input type="text" id="addr" name="member_address"
								class="form-control"
								value="<%=member_infoVO.getMember_address()%>" disabled>
						</div>

						<!-- �״ڱb�� ���� -->
						<div class="col-12 row">
							<div class="col-4">
								<div>
									<label for="code">����</label>
								</div>
								<input type="text" id="code" name="member_invoice"
									class="form-control"
									value="<%=member_infoVO.getMember_invoice()%>" disabled>
							</div>
							<div class="col-4">
								<div>
									<label for="bank">�Ȧ�N��</label>
								</div>
								<input type="text" id="bank" name="bank_code"
									class="form-control" value="<%=member_infoVO.getBank_code()%>"  disabled/>

							</div>
							<div class="col-4">
								<div>
									<label for="account">�״ڱb��</label>
								</div>
								<input type="text" id="account" name="traccount"
									class=" form-control" value="<%=member_infoVO.getTraccount()%>" disabled>
							</div>

						</div>


						<div class="col-12 row">
							<div class="col-4">
								<label for="teach-on">�}��}�ҹw��</label> <select
									name="teachclass_on" id="teach-on" class="form-control" disabled>
									<option value="0">����</option>
									<option value="1">�}��</option>

								</select>

							</div>
							<div class="col-4">
								<label for="">�}��׽ҹw��</label> <select name="learnclass_on"
									id="learn-on" class="form-control" disabled>
									<option value="0">����</option>
									<option value="1">�}��</option>

								</select>

							</div>
							<div class="col-4">
								<label for="">�}��@�~�w��</label> <select name="member_homework"
									id="homework" class="form-control" disabled>
									<option value="0">����</option>
									<option value="1">�}��</option>

								</select>
							</div>
						</div>
						<div class="col-12 row">
							<% 
							String good_for = member_infoVO.getMember_good_for();
							if(good_for == null){
								good_for ="�L���";
							}
							%>
							<div class="col-6">
								<label for="goodfor">�M��</label> <input type="text" id="goodfor"
									name="member_good_for" class="form-control"
									value="<%=good_for%>" disabled>
							</div>
							<% 
							String about = member_infoVO.getMember_about();
							if(about == null){
								about ="�L���";
							}
							%>
							<div class="col-6">
								<label for="about">�����</label>
								<textarea id="about" class="form-control" name="member_about" disabled><%=about%>
                             </textarea>
							</div>
						</div>
						<div class="col-12 row">
							<div class="col-6">
								<label for="f_date1">���U�ɶ�</label> <input name="register_time"
									id="f_date1" type="text" class="form-control" disabled>

							</div>
							<div class="col-6">
								<label for="f_date3">��Ƨ�s�ɶ�</label> <input name="member_update"
									id="f_date3" type="text" class="form-control" disabled>

							</div>

						</div>
						
						
						<div class="col-12" style="margin-top: 50px ; margin-bottom: 50px">
							<input type="hidden" name="action" value="getOne_For_Update"> 
							<input type="hidden" name="member_id" value="<%=member_infoVO.getMember_id()%>">
							<button id="submit-btn" type="submit"
								class="btn btn-danger col-4">�ק���</button>
						</div>
						

					</div>
				</div>
			</FORM>
			
			
			
			

		</div>
	</div>
</body>
<%-- <% 
	Date member_birthday = null;
	try {
		member_birthday = member_infoVO.getMember_birthday();
	} catch (Exception e) {
		member_birthday = new Date(System.currentTimeMillis());
	}
%>--%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.css" />
<!---------------------------------------- script ---------------------------------------->

<!-- jquery-->
<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>

<!-- popper-->
<script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>

<!-- bootstrap -->
<script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>

<!-- jquery.datetimepicker -->
<script src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.full.js"></script>

<!-- public.js-->
<script src="<%=request.getContextPath()%>/js/back-end/public.js"></script>

<!---------------------------------------- script ---------------------------------------->

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
	$.datetimepicker.setLocale('zh'); // kr ko ja en
	$('#f_date1').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : true, //timepicker: false,
		step : 1, //step: 60 (�o�Otimepicker���w�]���j60����)
		format : 'Y-m-d H:i:s',
		value : new Date(),
	});
	$('#f_date2').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker: false,
		//	step : 1440, //step: 60 (�o�Otimepicker���w�]���j60����)
		format : 'Y-m-d',
		value : new Date(),
	});
	$('#f_date3').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : true, //timepicker: false,
		step : 1, //step: 60 (�o�Otimepicker���w�]���j60����)
		format : 'Y-m-d H:i:s',
		value : new Date(),
	});
</script>
<script>
	// �U�Ԧ�����ܾ�(selector Id, data �̭�����)
	function setSelectorValue(target, targetValue) {
		var targetSelctor = document.getElementById(target);
		for (var i = 0; i < targetSelctor.options.length; i++) {
			if (parseInt(targetSelctor.options[i].getAttribute("value"), 10) === targetValue) {
				targetSelctor.options[i].setAttribute("selected", true);
			} else {
				targetSelctor.options[i].removeAttribute("selected");
			}
		}
	}
	// �ˬd�O�_�����(java)
	var memberDataExist1 =
<%=member_infoVO != null%>
	;
<%Integer mem_role = 0;
			if (member_infoVO != null) {
				mem_role = (member_infoVO.getMember_role() != null) ? member_infoVO.getMember_role() : 0;
			}%>
	// �p�G����� set data(js)
	if (memberDataExist1) {
		setSelectorValue("mem-role",
<%=mem_role%>
	);
	}

	// �ˬd�O�_�����(java)
	var memberDataExist2 =
<%=member_infoVO != null%>
	;
<%Integer mem_gender = 0;
			if (member_infoVO != null) {
				mem_gender = (member_infoVO.getMember_gender() != null) ? member_infoVO.getMember_gender() : 0;
			}%>
	// �p�G����� set data(js)
	if (memberDataExist2) {
		setSelectorValue("mem-gender",
<%=mem_gender%>
	);
	}

	// �ˬd�O�_�����(java)
	var memberDataExist3 =
<%=member_infoVO != null%>
	;
<%Integer teach_on = 0;
			if (member_infoVO != null) {
				teach_on = (member_infoVO.getTeachclass_on() != null) ? member_infoVO.getTeachclass_on() : 0;
			}%>
	// �p�G����� set data(js)
	if (memberDataExist3) {
		setSelectorValue("teach-on",
<%=teach_on%>
	);
	}

	// �ˬd�O�_�����(java)
	var memberDataExist4 =
<%=member_infoVO != null%>
	;
<%Integer learn_on = 0;
			if (member_infoVO != null) {
				learn_on = (member_infoVO.getLearnclass_on() != null) ? member_infoVO.getLearnclass_on() : 0;
			}%>
	// �p�G����� set data(js)
	if (memberDataExist4) {
		setSelectorValue("learn-on",
<%=learn_on%>
	);
	}
	// �ˬd�O�_�����(java)
	var memberDataExist5 =
<%=member_infoVO != null%>
	;
<%Integer mem_homework = 0;
			if (member_infoVO != null) {
				mem_homework = (member_infoVO.getMember_homework() != null) ? member_infoVO.getMember_homework() : 0;
			}%>
	// �p�G����� set data(js)
	if (memberDataExist5) {
		setSelectorValue("homework",
<%=mem_homework%>
	);
	}
	function preview_image(event) {
		var reader = new FileReader();
		reader.onload = function() {
			var output = document.getElementById('output_image');
			output.src = reader.result;
		}
		reader.readAsDataURL(event.target.files[0]);
	}
</script>


</html>