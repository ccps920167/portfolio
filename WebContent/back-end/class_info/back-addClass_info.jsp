<%@page import="java.sql.Timestamp"%>
<%@page import="com.class_info.model.*"%>
<%@page import="com.sub_class.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%
	Class_infoVO class_infoVO = (Class_infoVO) request.getAttribute("Class_infoVO");
	Map<String, List<Sub_classVO>> MainSubClass = (Map<String, List<Sub_classVO>>)application.getAttribute("MainSubClass");	
	Set key = MainSubClass.keySet();
	pageContext.setAttribute("key", key);
	pageContext.setAttribute("MainSubClass", MainSubClass);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>TOMATO��x�޲z���x</title>
<!---------------------------------------- css ---------------------------------------->

<!-- bootstrap -->
<link 
	rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
	
<!-- video-js -->
<link
	href="<%=request.getContextPath()%>/vendors/video-js/css/video-js.css"
	rel="stylesheet" />

<!-- ���CSS icon.css public.css -->
<link href="<%=request.getContextPath()%>/css/back-end/icon.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/back-end/public.css"
	rel="stylesheet" type="text/css" />

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
</style>
<!---------------------------------------- css ---------------------------------------->

</head>
<body>

	<%-- ���~��C --%>
	<c:if test="${not empty erMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${erMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<%@ include file="header-back.jsp"%>

	<div style="margin-left: 250px; margin-right: 20px; margin-top: 50px" >
		<nav aria-label="breadcrumb" style="margin-top: 30px;">
			<ol class="breadcrumb">
				<li class="breadcrumb-item">Home</a></li>
				<li class="breadcrumb-item active" aria-current="page">�ҵ{�޲z</li>
				<li class="breadcrumb-item active" aria-current="page">�s�W�ҵ{</li>
			</ol>
		</nav>
		<br>
		<div class="container">
		<h1>�s�W�ҵ{</h1>
		<br>
		<form method="POST" action="<%=request.getContextPath()%>/class_info/class_infoServlet" enctype="multipart/form-data">
		<table class="table table-striped" style="border-style:dotted; border-width:0.5px">
			<tr>
				<td>�ҵ{�W��:</td>
				<td><input class="form-control" type="TEXT" name="class_name" size="45"
					value="<%=(class_infoVO == null) ? "�ҵ{�W��" : class_infoVO.getClass_name()%>" /></td>
			</tr>
			<tr>
				<td>�|���s��:</td>
				<td><input class="form-control" type="TEXT" name="member_id" size="45"
					value="<%=(class_infoVO == null) ? "�|���s��" : class_infoVO.getMember_id()%>" /></td>
			</tr>
			<tr>
				<td>�����O�ҵ{�s��:</td>
				<td>
				<select class="form-control" size="1" name="mainClass_id">
					<c:forEach var="keys" items="${key}">
					<c:forEach var="subClassVO" items="${MainSubClass[keys]}">
						<option value="${subClassVO.subClass_id}">${subClassVO.subClass_name}
						</option>
					</c:forEach>
					</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<td>�}�l�Ҹ���:</td>
				<td><input class="form-control" name="startfund_date" id="f_date1" type="text" ></td>
			</tr>
			<tr>
				<td>�}�Ҥ��:</td>
				<td><input class="form-control" type="TEXT" name="startclass_time" id="f_date2" /></td>
			</tr>
			<tr>
				<td>�ҵ{�y�z:</td>
				<td><textarea class="form-control" rows="5" cols="50" name="class_description"><%=(class_infoVO == null) ? "�ҵ{�y�z" : class_infoVO.getClass_description()%></textarea>
				</td>
			</tr>
			<tr>
				<td>�Ҹ���:</td>
				<td><input class="form-control" type="TEXT" name="startfund_price" size="45"
					value="<%=(class_infoVO == null) ? "�Ҹ���" : class_infoVO.getStartfund_price()%>" /></td>
			</tr>
			<tr>
				<td>�w��:</td>
				<td><input class="form-control" type="TEXT" name="original_price" size="45"
					value="<%=(class_infoVO == null) ? "�w��" : class_infoVO.getOriginal_price()%>" /></td>
			</tr>
			<tr>
				<td>���e�H��:</td>
				<td><input class="form-control" type="TEXT" name="people_threshold" size="45"
					value="<%=(class_infoVO == null) ? "���e�H��" : class_infoVO.getPeople_threshold()%>" /></td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>�ҵ{����:</td> -->
<!-- 				<td><input class="form-control" type="TEXT" name="class_length" size="45" -->
<%-- 					value="<%=(class_infoVO == null) ? "�ҵ{����" : class_infoVO.getClass_length()%>" /></td> --%>
<!-- 			</tr> -->
					<tr>
				<td>�ҵ{�ʭ��Ϥ�:</td>
				<td><input class="form-control" type="FILE" name="class_picture" 
					value="<%=(class_infoVO == null) ? "null" : class_infoVO.getClass_picture()%>" /></td>
			</tr> 
			<tr>
				<td>�Ҹ�v��:</td>
				<td><input class="form-control" type="FILE" name="video_fundraising" 
					value="<%=(class_infoVO == null) ? "null" : class_infoVO.getVideo_fundraising()%>" /></td>
			</tr>
		
		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="�e�X�s�W">
	</form>
</div>
<!---------------------------------------- script ---------------------------------------->

<!-- jquery-->
<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>

<!-- popper-->
<script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>

<!-- bootstrap -->
<script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>

<!-- public.js-->
<script src="<%=request.getContextPath()%>/js/back-end/public.js"></script>

<!-- jquery.datetimepicker -->
<script src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.full.js"></script>

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
         $('#f_date1').datetimepicker({
             beforeShowDay: function(date) {
           	  if (  date.getYear() <  somedate1.getYear() || 
    		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
    		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                 ) {
                      return [false, ""]
                 }
                 return [true, ""];
         }});
         
              var somedate1 = new Date('2017-06-15');
              $('#f_date2').datetimepicker({
                  beforeShowDay: function(date) {
                	  if (  date.getYear() <  somedate1.getYear() || 
         		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
         		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                      ) {
                           return [false, ""]
                      }
                      return [true, ""];
              }});
</script>

</html>