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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">

<title>TOMATO - 後臺管理</title>
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

#preview {
	border: 1px solid lightgray;
	display: inline-block;
	/* width: 100%;
            height: auto; */
	width: 247px;
	height: 247px;
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
</style>
<!---------------------------------------- css ---------------------------------------->

<title>TOMATO後台管理平台</title>

</head>
<body>
	<%@ include file="header-back.jsp"%>

	<div style="margin-left: 250px; margin-right: 20px; margin-top: 100px" >
		<nav aria-label="breadcrumb" style="margin-top: 30px;">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Home</a></li>
				<li class="breadcrumb-item active" aria-current="page">課程管理</li>
				<li class="breadcrumb-item active" aria-current="page">課程列表</li>
			</ol>
		</nav>
		<br>
		<div class="container">
			<h1>修改會員資料</h1>
			<br>
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>


		<div id="content">
			<FORM class="g-3" METHOD="post"
						ACTION="<%=request.getContextPath()%>/member_info/Member_infoServlet"
						name="form1" enctype="multipart/form-data">
				<div class="row">
					<div class="col-lg-3 col-sm-4 col-12">
						<div id="preview"><span class="text"><img style="width: 247px ; height: 247px ; " id="output_image" src="<%=request.getContextPath()%>/member_info/Member_info_frontServlet?action=member_pic&member_id=${member_infoVO.member_id}"></span></div>
                        <input type="file" id="file" name="member_pic" 
                        value="<%=member_infoVO.getMember_pic()%>"
                        onchange="preview_image(event)" style="display:none">
                        <label for="file" class="form-control" style="cursor: pointer; width: 150px;">
                            	請上傳照片
                            <img src="<%=request.getContextPath()%>/img/icon/2089052.png" alt="" width=16 height=16 style="margin-left: 10px; margin-top: -4px;">
                        </label>
                        <span>會員編號 :<%=member_infoVO.getMember_id()%></span>
                    </div>
					<div class="col-lg-9 col-sm-8 col-12">
						<div class="col-12 row">

                            <div class="col-5">
                                <label for="inputPassword1" class="form-label">會員名稱</label>
                                <input type="text" class="form-control" id="inputPassword1" name="member_name" 
                                value="<%=member_infoVO.getMember_name()%>">
                            </div>
                            <div class="col-5">
                                <label for="inputEmail4" class="form-label">Email</label>
                                <input type="TEXT" class="form-control" id="inputEmail4" name="member_email"
                                value="<%=member_infoVO.getMember_email()%>">
                            </div>
                        </div>
					
						<div class="col-12 row">

                     		<div class="col-5">
                             	<label for="inputPassword2" class="form-label">會員密碼</label>
                             	<input type="TEXT" class="form-control" id="inputPassword2" name="member_password"
                                value="<%=member_infoVO.getMember_password()%>">
                       		</div>
                        	<div class="col-5">
                            	<div>
	                             	<label for="mem-role">會員身分</label>
                            	</div>
                            	<select name="member_role" id="mem-role" class="form-control" >
									<option value="0">僅學生身分</option>
									<option value="1">有老師身分</option>
								</select>
                       	 </div>
                     </div>
					<!-- 性別、生日 -->
                     <div class="col-12 row">
                         <div class="col-5">
                             <div>
                                 <label for="mem-gender">性別</label>
                             </div>
                             <select name="member_gender" id="mem-gender" class="form-control">
                                 <option value="0">男性</option>
                                 <option value="1">女性</option>
                                 <option value="2">多元性別</option>
                             </select>
                         </div>
                         <div class="col-5">
                             <div>
                                 <label for="f_date2">生日</label>
                             </div>
                             <input name="member_birthday" id="f_date2" type="text" class="form-control">
                           
                         </div>
                     </div>
					<!-- 職業 -->
                     <div class="col-5">
                         <div>

                             <label for="job">職業</label>
                         </div>
                         <div>
                             <input type="TEXT" name="member_occupation" id="job" class="form-control"
						value="<%=member_infoVO.getMember_occupation()%>">
                         </div>
                     </div>
					<!-- 地址 -->
                    <div class="col-12">
                        <div>
                            <label for="addr">地址</label>
                        </div>
                        
                        	<input type="text" id="addr" name="member_address" class="form-control" value="<%=member_infoVO.getMember_address()%>">
                    	</div>
					
					<!-- 匯款帳號 載具 -->
                    <div class="col-12 row">
	                    <div class="col-4">
	                        <div>
	                            <label for="code">載具</label>
	                        </div>
	                        <input type="text" id="code"
	                        name="member_invoice" class="form-control" value="<%=member_infoVO.getMember_invoice()%>">
	                    </div>	
                        <div class="col-4">
                            <div>
                                <label for="bank">銀行代號</label>
                            </div>
                            <input type="text" id="bank" name="bank_code" class="form-control" value="<%=member_infoVO.getBank_code()%>" />

                        </div>
                        <div class="col-4">
                            <div>
                                <label for="account">匯款帳號</label>
                            </div>
                            <input type="text" id="account" name="traccount" class=" form-control" value="<%=member_infoVO.getTraccount()%>">
                        </div>

                    </div>	
                    
                    
                   <div class="col-12 row">
                        <div class="col-4">
                            <label for="teach-on">開放開課預覽</label>
                            <select name="teachclass_on" id="teach-on" class="form-control">
                                <option value="0">關閉</option>
                                <option value="1">開放</option>
        
                            </select>

                        </div>
                        <div class="col-4">
                            <label for="">開放修課預覽</label>
                            <select name="learnclass_on" id="learn-on" class="form-control">
                                <option value="0">關閉</option>
                                <option value="1">開放</option>
        
                            </select>

                        </div>
                        <div class="col-4">
                            <label for="">開放作品預覽</label>
                            <select name="member_homework" id="homework" class="form-control">
                                <option value="0">關閉</option>
                                <option value="1">開放</option>
        
                            </select>
                        </div>
                    </div> 
           			<div class="col-12 row">
							<% 
							String good_for = member_infoVO.getMember_good_for();
							if(good_for == null){
								good_for ="無資料";
							}
							%>
                         <div class="col-6">
                             <label for="goodfor">專長</label>
                             <input type="text" id="goodfor"name="member_good_for" class="form-control"
                                     value="<%=good_for%>">
                         </div>
							<% 
							String about = member_infoVO.getMember_about();
							if(about == null){
								about ="無資料";
							}
							%>
                         <div class="col-6">
                             <label for="about">關於我</label>
                             <textarea id ="about" class="form-control" name="member_about"><%=about%>
                             </textarea>
                         </div>
                     </div>

					<div class="col-12" style="margin-top: 50px ; margin-bottom: 50px">
						<input type="hidden" name="action" value="update">
						<input type="hidden" name="member_id" value="<%=member_infoVO.getMember_id()%>">
						<input name="register_time" id="f_date1" type="hidden" class="form-control" value="<%=member_infoVO.getRegister_time()%>">
						<button id="submit-btn" type="submit" class="btn btn-danger col-4">確認更新</button>
					</div>
				</div>
				</div>	
			</FORM>
			
		</div>
			</div>
			</div>
</body>
<!---------------------------------------- script ---------------------------------------->

<!-- jquery-->
<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>

<!-- popper-->
<script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>

<!-- bootstrap -->
<script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>

<!-- jquery.datetimepicker -->
<script
	src="<%=request.getContextPath()%>/back-end/member_info/jquery.datetimepicker.full.js"></script>
		
<!-- public.js-->
<script src="<%=request.getContextPath()%>/js/back-end/public.js"></script>

<!---------------------------------------- script ---------------------------------------->


<script>
	$.datetimepicker.setLocale('zh'); // kr ko ja en
	$('#f_date1').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : true, //timepicker: false,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d H:i:s',
		value : new Date(),
	});
	$('#f_date2').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker: false,
		//	step : 1440, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d',
		value : new Date(),
	});
	$('#f_date3').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : true, //timepicker: false,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d H:i:s',
		value : new Date(),
	});
</script>
<script>
	// 下拉式選單選擇器(selector Id, data 裡面的值)
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
	// 檢查是否有資料(java)
	var memberDataExist1 =
<%=member_infoVO != null%>
	;
<%Integer mem_role = 0;
			if (member_infoVO != null) {
				mem_role = (member_infoVO.getMember_role() != null) ? member_infoVO.getMember_role() : 0;
			}%>
	// 如果有資料 set data(js)
	if (memberDataExist1) {
		setSelectorValue("mem-role",
<%=mem_role%>
	);
	}

	// 檢查是否有資料(java)
	var memberDataExist2 =
<%=member_infoVO != null%>
	;
<%Integer mem_gender = 0;
			if (member_infoVO != null) {
				mem_gender = (member_infoVO.getMember_gender() != null) ? member_infoVO.getMember_gender() : 0;
			}%>
	// 如果有資料 set data(js)
	if (memberDataExist2) {
		setSelectorValue("mem-gender",
<%=mem_gender%>
	);
	}

	// 檢查是否有資料(java)
	var memberDataExist3 =
<%=member_infoVO != null%>
	;
<%Integer teach_on = 0;
			if (member_infoVO != null) {
				teach_on = (member_infoVO.getTeachclass_on() != null) ? member_infoVO.getTeachclass_on() : 0;
			}%>
	// 如果有資料 set data(js)
	if (memberDataExist3) {
		setSelectorValue("teach-on",
<%=teach_on%>
	);
	}

	// 檢查是否有資料(java)
	var memberDataExist4 =
<%=member_infoVO != null%>
	;
<%Integer learn_on = 0;
			if (member_infoVO != null) {
				learn_on = (member_infoVO.getLearnclass_on() != null) ? member_infoVO.getLearnclass_on() : 0;
			}%>
	// 如果有資料 set data(js)
	if (memberDataExist4) {
		setSelectorValue("learn-on",
<%=learn_on%>
	);
	}
	// 檢查是否有資料(java)
	var memberDataExist5 =
<%=member_infoVO != null%>
	;
<%Integer mem_homework = 0;
			if (member_infoVO != null) {
				mem_homework = (member_infoVO.getMember_homework() != null) ? member_infoVO.getMember_homework() : 0;
			}%>
	// 如果有資料 set data(js)
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
	
	$("#submit-btn").click(function(){

		$("#mem-role").prop("disabled",false);

		});
</script>


</html>