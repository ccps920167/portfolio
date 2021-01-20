<%@page import="com.verify_list.model.Verify_listVO"%>
<%@page import="com.verify_list.model.Verify_listService"%>
<%@page import="com.class_info.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<%
	Class_infoVO class_infoVO = (Class_infoVO) request.getAttribute("Class_infoVO");
	pageContext.setAttribute("class_infoVO", class_infoVO);
	
    Verify_listVO verify_listVO = (Verify_listVO)request.getAttribute("ClassCheckVO");
    pageContext.setAttribute("verify_listVO", verify_listVO);
    
%>


<html>
<head>
<meta charset="BIG5">
<title>update_VerifyClass_info_input</title>


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
				<li class="breadcrumb-item active" aria-current="page">�ҵ{�޲z</li>
				<li class="breadcrumb-item active" aria-current="page">�ҵ{�C��</li>
				<li class="breadcrumb-item active" aria-current="page">�i<%=class_infoVO.getClass_name()%>�j</li>
			</ol>
		</nav>
		<br>
		<div class="container">
		<h1>�i<%=class_infoVO.getClass_name()%>�j �Ա�</h1>
				<div class="btn-group" role="group" aria-label="Basic example" style="margin-bottom: 50px">
   				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forward/backClassSrevlet" style="margin-bottom: 0px;">
					<input class="btn btn-danger" type="submit" value="���`�γ椸"> 
					<input type="hidden" name="class_id" value="${ciVO.class_id}">
					<input type="hidden" name="action" value="chapterAndUnit">
				</FORM>
   				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forward/backClassSrevlet" style="margin-bottom: 0px;">
					<input class="btn btn-danger" type="submit" value="�H�����"> 
					<input type="hidden" name="class_id" value="${ciVO.class_id}">
					<input type="hidden" name="action" value="Test">
				</FORM>
   				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forward/backClassSrevlet" style="margin-bottom: 0px;">
					<input class="btn btn-danger" type="submit" value="����Χ@�~"> 
					<input type="hidden" name="class_id" value="${ciVO.class_id}">
					<input type="hidden" name="action" value="homework">
				</FORM>
  				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forward/backClassSrevlet" style="margin-bottom: 0px;">
					<input class="btn btn-danger" type="submit" value="�|�����"> 
					<input type="hidden" name="class_id" value="${ciVO.class_id}">
					<input type="hidden" name="action" value="studentsAndTeacher">
				</FORM>
  				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forward/backClassSrevlet" style="margin-bottom: 0px;">
					<input class="btn btn-danger" type="submit" value="�ҵ{�����d��"> 
					<input type="hidden" name="class_id" value="${ciVO.class_id}">
					<input type="hidden" name="action" value="ClassMsg">
				</FORM>
  				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forward/backClassSrevlet" style="margin-bottom: 0px;">
					<input class="btn btn-danger" type="submit" value="�ǲ߭����d��"> 
					<input type="hidden" name="class_id" value="${ciVO.class_id}">
					<input type="hidden" name="action" value="LearnMsg">
				</FORM>

			</div>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/verify_list/verify_listServlet"
				style="margin-bottom: 0px;" enctype="multipart/form-data">
				<table class="table table-striped" style="border-style:dotted; border-width:0.5px">
				    <tr>
						<td>�ҵ{�s��:</td>
						<td><input readonly class="form-control" type="text" name="class_id" size="45"
							value="${class_infoVO.class_id}" /></td>
					</tr>
					<tr>
						<td>�ҵ{�W��:</td>
						<td><input readonly class="form-control" type="TEXT" name="class_name" size="45"
							value="<%=class_infoVO.getClass_name()%>" /></td>
					</tr>
					<tr>
						<td>�|���s��:</td>
						<td><input readonly class="form-control" type="TEXT" name="member_id" size="45"
							value="<%=class_infoVO.getMember_id()%>" /></td>
					</tr>
					<tr>
						<td>�����O�ҵ{�s��:</td>
						<td><input readonly class="form-control" type="TEXT" name="subclass_id" size="45"
							value="<%=class_infoVO.getSubclass_id()%>" /></td>
					</tr>
					<tr>
						<td>�}�l�Ҹ���:</td>
						<td><input readonly class="form-control" type="TEXT" name="startfund_date" id="f_date1"
							value="<%=class_infoVO.getStartfund_date()%>" /></td>
					</tr>
					<tr>
						<td>�}�Ҥ��:</td>
						<td><input readonly class="form-control" type="TEXT" name="startclass_time" id="f_date2"
							value="<%=class_infoVO.getStartclass_time()%>" /></td>
					</tr>
					<tr>
						<td>�ҵ{�y�z:</td>
						<td><textarea readonly class="form-control" rows="5" cols="50" name="class_description"><%=class_infoVO.getClass_description()%></textarea>
						</td>
					</tr>
					<tr>
						<td>�Ҹ���:</td>
						<td><input readonly class="form-control" type="TEXT" name="startfund_price" size="45"
							value="<%=class_infoVO.getStartfund_price()%>" /></td>
					</tr>
					<tr>
						<td>�w��:</td>
						<td><input readonly class="form-control" type="TEXT" name="original_price" size="45"
							value="<%=class_infoVO.getOriginal_price()%>" /></td>
					</tr>
					<tr>
						<td>���e�H��:</td>
						<td><input readonly class="form-control" type="TEXT" name="people_threshold" size="45"
							value="<%=class_infoVO.getPeople_threshold()%>" /></td>
					</tr>
					<tr>
						<td>�ҵ{����:</td>
						<td><input readonly class="form-control" type="TEXT" name="class_length" size="45"
							value="<%=class_infoVO.getClass_length()%>" /></td>
					</tr>
					<tr>
						<td>�ҵ{�ʭ��Ϥ�:</td>
						<td> <img style="border-style:dotted; border-width:0.5px; width: 30%" src="<%=request.getContextPath()%>/class_info/Class_infoImgAndVideo?action=class_pic&class_id=<%=class_infoVO.getClass_id()%>">
						</td>
					</tr>
					<tr>
						<td>�Ҹ�v��:</td>
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
							value="�L�v��" />
						</c:if>
						</td>
					</tr>
					<tr>
						<td>�ҵ{���A:</td>
						<td>
						    <select name="class_status" id="adm_status">
						    <option value="0">�ݼf��</option>
						    <option value="1">�Ҹꤤ</option>
						    <option value="4">�w�}��</option>
						    <option value="5">�w�U�[</option>
						    <option value="6">�h��</option>
				            </select>
						</td>
					</tr>
					<tr>
					    <td>�f�֬���:</td>
					    <td><textarea readonly class="form-control" rows="5" cols="50" name="class_check" 
				              	    id="class_check"><%=(verify_listVO == null) ? "" : verify_listVO.getClass_check()%></textarea></td>
					</tr>

				</table>

<!-- 				<FORM METHOD="post" -->
<%-- 							ACTION="<%=request.getContextPath()%>/verify_list/verify_listServlet" --%>
<!-- 							style="margin-bottom: 0px;"> -->
							<input class="btn btn-danger" type="submit" value="�x�s">
<!-- 							 <input --> 
<%--  								type="hidden" name="class_id" value="${ciVO.class_id}"> --%> 
							<input type="hidden" name="action" value="changeClassStatus">
<!-- 				</FORM> -->

<!-- 							<input class="btn btn-danger" type="submit" value="�q�L">  -->
<!-- 							<input -->
<%-- 								type="hidden" name="class_id" value="${ciVO.class_id}"> --%>
<!-- 							<input type="hidden" name="action" value="success"> -->
				</FORM>
				
				<input class="btn btn-danger" type="button" value="��^�Ҧ��ҵ{�C��" onclick="location.href='<%=request.getContextPath()%>/back-end/verify_list/back-verify_listAll.jsp'">
				
<!-- 				<FORM>			 -->
<!-- 					<input class="btn btn-danger" type="submit" value="�q�L"> -->
<!-- 					<input class="form-control" type="hidden" name="class_id" size="45" -->
<%-- 							value="${class_infoVO.class_id}" /> --%>
<!-- 					<input type="hidden" name="action" value="success"> -->
<!-- 				</FORM> -->
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
	var adminDataExist =<%=class_infoVO != null%>;
    <%Integer adm_status = 0;
			if (class_infoVO != null) {
				adm_status = (class_infoVO.getClass_status() != null) ? class_infoVO.getClass_status() : 0;
			}%>
	// �p�G����� set data(js)
	if (adminDataExist) {
		setSelectorValue("adm_status",<%=adm_status%>);
	}

	</script>

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