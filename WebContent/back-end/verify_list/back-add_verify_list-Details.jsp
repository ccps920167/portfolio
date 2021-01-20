<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.class_info.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@page import="java.sql.Timestamp"%>
<!DOCTYPE html>
<%
	Class_infoVO class_infoVO = (Class_infoVO) request.getAttribute("Class_infoVO");
	pageContext.setAttribute("class_infoVO", class_infoVO);
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

	<div style="margin-left: 250px; margin-right: 20px;">
		<nav aria-label="breadcrumb" style="margin-top: 30px;">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Home</a></li>
				<li class="breadcrumb-item active" aria-current="page">課程管理</li>
				<li class="breadcrumb-item active" aria-current="page">課程列表</li>
				<li class="breadcrumb-item active" aria-current="page">【<%=class_infoVO.getClass_name()%>】</li>
			</ol>
		</nav>
		<br>
		<div class="container">
		<h1>【<%=class_infoVO.getClass_name()%>】 詳情</h1>
		<div class="btn-group" role="group" aria-label="Basic example" style="margin-bottom: 50px">
   				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forward/backClassSrevlet" style="margin-bottom: 0px;">
					<input class="btn btn-danger" type="submit" value="章節及單元"> 
					<input type="hidden" name="class_id" value="${ciVO.class_id}">
					<input type="hidden" name="action" value="chapterAndUnit">
				</FORM>
   				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forward/backClassSrevlet" style="margin-bottom: 0px;">
					<input class="btn btn-danger" type="submit" value="隨堂測驗"> 
					<input type="hidden" name="class_id" value="${ciVO.class_id}">
					<input type="hidden" name="action" value="Test">
				</FORM>
   				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forward/backClassSrevlet" style="margin-bottom: 0px;">
					<input class="btn btn-danger" type="submit" value="測驗及作業"> 
					<input type="hidden" name="class_id" value="${ciVO.class_id}">
					<input type="hidden" name="action" value="homework">
				</FORM>
  				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forward/backClassSrevlet" style="margin-bottom: 0px;">
					<input class="btn btn-danger" type="submit" value="會員資料"> 
					<input type="hidden" name="class_id" value="${ciVO.class_id}">
					<input type="hidden" name="action" value="studentsAndTeacher">
				</FORM>
  				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forward/backClassSrevlet" style="margin-bottom: 0px;">
					<input class="btn btn-danger" type="submit" value="課程頁面留言"> 
					<input type="hidden" name="class_id" value="${ciVO.class_id}">
					<input type="hidden" name="action" value="ClassMsg">
				</FORM>
  				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forward/backClassSrevlet" style="margin-bottom: 0px;">
					<input class="btn btn-danger" type="submit" value="學習頁面留言"> 
					<input type="hidden" name="class_id" value="${ciVO.class_id}">
					<input type="hidden" name="action" value="LearnMsg">
				</FORM>

			</div>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/verify_list/verify_listServlet"
				style="margin-bottom: 0px;" enctype="multipart/form-data">
				<table class="table table-striped" style="border-style:dotted; border-width:0.5px">
				    <tr>
						<td>課程編號:</td>
						<td><input readonly class="form-control" type="text" name="class_id" size="45"
							value="${class_infoVO.class_id}" /></td>
					</tr>
					<tr>
						<td>課程名稱:</td>
						<td><input readonly class="form-control" type="TEXT" name="class_name" size="45"
							value="<%=class_infoVO.getClass_name()%>" /></td>
					</tr>
					<tr>
						<td>會員編號:</td>
						<td><input readonly class="form-control" type="TEXT" name="member_id" size="45"
							value="<%=class_infoVO.getMember_id()%>" /></td>
					</tr>
					<tr>
						<td>副類別課程編號:</td>
						<td><input readonly class="form-control" type="TEXT" name="subclass_id" size="45"
							value="<%=class_infoVO.getSubclass_id()%>" /></td>
					</tr>
					<tr>
						<td>開始募資日期:</td>
						<td>
						<% 
						Timestamp Startfund_date= class_infoVO.getStartfund_date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						if (Startfund_date == null){
							Startfund_date = new Timestamp(new Date().getTime());
						}else{
							sdf.format(Startfund_date);
						}
						
						%>
						<input readonly class="form-control" type="TEXT" name="startfund_date" id="f_date1"
							value="<%=Startfund_date%>" />
						</td>
					</tr>
					<tr>
						<td>開課日期:</td>
						
						<td>
						<% 
						Timestamp Startclass_time= class_infoVO.getStartclass_time();
						
						if (Startclass_time == null){
							Startclass_time = new Timestamp(new Date().getTime());
						}else{
							sdf.format(Startclass_time);
						}
						
						%>
						
						<input readonly class="form-control" type="TEXT" name="startclass_time" id="f_date2"
							value="<%=Startclass_time%>" /></td>
					</tr>
					<tr>
						<td>課程描述:</td>
						<td><textarea readonly class="form-control" rows="5" cols="50" name="class_description"><%=class_infoVO.getClass_description()%></textarea>
						</td>
					</tr>
					<tr>
						<td>募資售價:</td>
						<td><input readonly class="form-control" type="TEXT" name="startfund_price" size="45"
							value="<%=class_infoVO.getStartfund_price()%>" /></td>
					</tr>
					<tr>
						<td>定價:</td>
						<td><input readonly class="form-control" type="TEXT" name="original_price" size="45"
							value="<%=class_infoVO.getOriginal_price()%>" /></td>
					</tr>
					<tr>
						<td>門檻人數:</td>
						<td><input readonly class="form-control" type="TEXT" name="people_threshold" size="45"
							value="<%=class_infoVO.getPeople_threshold()%>" /></td>
					</tr>
					<tr>
						<td>課程長度:</td>
						<td><input readonly class="form-control" type="TEXT" name="class_length" size="45"
							value="<%=class_infoVO.getClass_length()%>" /></td>
					</tr>
					<tr>
						<td>課程封面圖片:</td>
						<td> <img style="border-style:dotted; border-width:0.5px; width: 30%" src="<%=request.getContextPath()%>/class_info/Class_infoImgAndVideo?action=class_pic&class_id=<%=class_infoVO.getClass_id()%>">
						</td>
					</tr>
					<tr>
						<td>募資影片:</td>
						<td>
						<c:if test="${ not empty class_infoVO.video_fundraising}">
							<video id="video"
								data-id="${class_unitsVO.unit_id}"
								class="video-js vjs-fluid " controls
								preload="auto" data-setup="{}">
								<source id="video_url"
									src="<%=request.getContextPath()%>/class_info/Class_infoImgAndVideo?action=class_video&class_id=<%=class_infoVO.getClass_id()%>"
									type="video/mp4" />
							</video>
						</c:if>
						<c:if test="${ empty class_infoVO.video_fundraising}">
							<input readonly class="form-control" type="TEXT" name="class_length" size="45"
							value="無影片" />
						</c:if>
						</td>
					</tr>
					<tr>
						<td>審核紀錄:(若通過審核則無需填寫)</td>
						<td><textarea class="form-control" rows="5" cols="50" name="class_verify"></textarea>
						</td>
					</tr>

				</table>
							<input class="btn btn-danger" type="submit" value="未通過">
							<input type="hidden" name="action" value="fail">
				</FORM>
				
				<FORM>
					<input class="btn btn-danger" type="submit" value="通過">
					<input class="form-control" type="hidden" name="class_id" size="45"
							value="${class_infoVO.class_id}" />
					<input type="hidden" name="action" value="success">
				</FORM>
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
</body>

<%
	//  	Timestamp upload_time = null;

	//  	try {
	//  		upload_time = Class_infoVO.getUpload_time();
	//  	} catch (Exception e) {
	//  		upload_time = new Timestamp(System.currentTimeMillis());
	// 	}
%>



</html>