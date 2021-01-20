
<%@page import="com.class_info.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<%
	Class_infoVO class_infoVO = (Class_infoVO) request.getAttribute("Class_infoVO");
%>


<html>
<head>
<meta charset="BIG5">
<title>TOMATO��x�޲z���x</title>


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
				<li class="breadcrumb-item active" aria-current="page">�ҵ{�޲z</li>
				<li class="breadcrumb-item active" aria-current="page">�ҵ{�C��</li>
				<li class="breadcrumb-item active" aria-current="page">${class_info.class_id} : ${class_info.class_name} </li>
				<li class="breadcrumb-item active" aria-current="page">�ǲ߯d��</li>
			</ol>
		</nav>
		<br>
		<div class="container">
		<h1>�i<%=class_infoVO.getClass_name()%>�j �Ա�</h1>
			<form method="post"
				action="<%=request.getContextPath()%>/class_info/class_infoServlet"
				name="form1" enctype="multipart/form-data">
				<table class="table table-striped">
					<tr>
						<td>�ҵ{�W��:</td>
						<td><input class="form-control" type="TEXT" name="class_name" size="45"
							value="<%=class_infoVO.getClass_name()%>" /></td>
					</tr>
					<tr>
						<td>�|���s��:</td>
						<td><input class="form-control" type="TEXT" name="member_id" size="45"
							value="<%=class_infoVO.getMember_id()%>" /></td>
					</tr>
					<tr>
						<td>�����O�ҵ{�s��:</td>
						<td><input class="form-control" type="TEXT" name="subclass_id" size="45"
							value="<%=class_infoVO.getSubclass_id()%>" /></td>
					</tr>
					<tr>
						<td>�}�l�Ҹ���:</td>
						<td><input class="form-control" type="TEXT" name="startfund_date" id="f_date1"
							value="<%=class_infoVO.getStartfund_date()%>" /></td>
					</tr>
					<tr>
						<td>�}�Ҥ��:</td>
						<td><input class="form-control" type="TEXT" name="startclass_time" id="f_date2"
							value="<%=class_infoVO.getStartclass_time()%>" /></td>
					</tr>
					<tr>
						<td>�ҵ{�y�z:</td>
						<td><textarea class="form-control" rows="5" cols="50" name="class_description"><%=class_infoVO.getClass_description()%></textarea>
						</td>
					</tr>
					<tr>
						<td>�Ҹ���:</td>
						<td><input class="form-control" type="TEXT" name="startfund_price" size="45"
							value="<%=class_infoVO.getStartfund_price()%>" /></td>
					</tr>
					<tr>
						<td>�w��:</td>
						<td><input class="form-control" type="TEXT" name="original_price" size="45"
							value="<%=class_infoVO.getOriginal_price()%>" /></td>
					</tr>
					<tr>
						<td>���e�H��:</td>
						<td><input class="form-control" type="TEXT" name="people_threshold" size="45"
							value="<%=class_infoVO.getPeople_threshold()%>" /></td>
					</tr>
					<tr>
						<td>�ҵ{����:</td>
						<td><input class="form-control" type="TEXT" name="class_length" size="45"
							value="<%=class_infoVO.getClass_length()%>" /></td>
					</tr>
					<tr>
						<td>�ҵ{�ʭ��Ϥ�:</td>
						<td><input class="form-control" type="FILE" name="class_picture"
							value="<%=class_infoVO.getClass_picture()%>" /></td>
					</tr>
					<tr>
						<td>�Ҹ�v��:</td>
						<td><input class="form-control" type="FILE" name="video_fundraising"
							value="<%=class_infoVO.getVideo_fundraising()%>" /></td>
					</tr>

				</table>

				<br> <input type="hidden" name=class_id
					value="<%=class_infoVO.getClass_id()%>"> <input
					type="hidden" name="action" value="update"> <input
					type="submit" value="�e�X�ק�">
			</form>

			<h2>
				<a
					href="<%=request.getContextPath()%>/back-end/class_info/select_pageClass_info.jsp">�^�쭺��</a>
			</h2>
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
				step : 1, //step: 60 (�o�Otimepicker���w�]���j60����)
				format : 'Y-m-d H:i:s', //format:'Y-m-d H:i:s',
				// 		value : new Date(), // value:   new Date(),
			//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
			//startDate:	            '2017/07/10',  // �_�l��
			//minDate:               '-1970-01-01', // �h������(���t)���e
			//maxDate:               '+1970-01-01'  // �h������(���t)����
			});

			$.datetimepicker.setLocale('zh');
			$('#f_date2').datetimepicker({
				theme : '', //theme: 'dark',
				timepicker : false, //timepicker:true,
				step : 1, //step: 60 (�o�Otimepicker���w�]���j60����)
				format : 'Y-m-d H:i:s', //format:'Y-m-d H:i:s',
				// 		value : new Date(), // value:   new Date(),
			//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
			//startDate:	            '2017/07/10',  // �_�l��
			//minDate:               '-1970-01-01', // �h������(���t)���e
			//maxDate:               '+1970-01-01'  // �h������(���t)����
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