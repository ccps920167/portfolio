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
<title>TOMATO後台管理平台</title>
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

<!-- 後端CSS icon.css public.css -->
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

	<%-- 錯誤表列 --%>
	<c:if test="${not empty erMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
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
				<li class="breadcrumb-item active" aria-current="page">課程管理</li>
				<li class="breadcrumb-item active" aria-current="page">新增課程</li>
			</ol>
		</nav>
		<br>
		<div class="container">
		<h1>新增課程</h1>
		<br>
		<form method="POST" action="<%=request.getContextPath()%>/class_info/class_infoServlet" enctype="multipart/form-data">
		<table class="table table-striped" style="border-style:dotted; border-width:0.5px">
			<tr>
				<td>課程名稱:</td>
				<td><input class="form-control" type="TEXT" name="class_name" size="45"
					value="<%=(class_infoVO == null) ? "課程名稱" : class_infoVO.getClass_name()%>" /></td>
			</tr>
			<tr>
				<td>會員編號:</td>
				<td><input class="form-control" type="TEXT" name="member_id" size="45"
					value="<%=(class_infoVO == null) ? "會員編號" : class_infoVO.getMember_id()%>" /></td>
			</tr>
			<tr>
				<td>副類別課程編號:</td>
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
				<td>開始募資日期:</td>
				<td><input class="form-control" name="startfund_date" id="f_date1" type="text" ></td>
			</tr>
			<tr>
				<td>開課日期:</td>
				<td><input class="form-control" type="TEXT" name="startclass_time" id="f_date2" /></td>
			</tr>
			<tr>
				<td>課程描述:</td>
				<td><textarea class="form-control" rows="5" cols="50" name="class_description"><%=(class_infoVO == null) ? "課程描述" : class_infoVO.getClass_description()%></textarea>
				</td>
			</tr>
			<tr>
				<td>募資售價:</td>
				<td><input class="form-control" type="TEXT" name="startfund_price" size="45"
					value="<%=(class_infoVO == null) ? "募資售價" : class_infoVO.getStartfund_price()%>" /></td>
			</tr>
			<tr>
				<td>定價:</td>
				<td><input class="form-control" type="TEXT" name="original_price" size="45"
					value="<%=(class_infoVO == null) ? "定價" : class_infoVO.getOriginal_price()%>" /></td>
			</tr>
			<tr>
				<td>門檻人數:</td>
				<td><input class="form-control" type="TEXT" name="people_threshold" size="45"
					value="<%=(class_infoVO == null) ? "門檻人數" : class_infoVO.getPeople_threshold()%>" /></td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>課程長度:</td> -->
<!-- 				<td><input class="form-control" type="TEXT" name="class_length" size="45" -->
<%-- 					value="<%=(class_infoVO == null) ? "課程長度" : class_infoVO.getClass_length()%>" /></td> --%>
<!-- 			</tr> -->
					<tr>
				<td>課程封面圖片:</td>
				<td><input class="form-control" type="FILE" name="class_picture" 
					value="<%=(class_infoVO == null) ? "null" : class_infoVO.getClass_picture()%>" /></td>
			</tr> 
			<tr>
				<td>募資影片:</td>
				<td><input class="form-control" type="FILE" name="video_fundraising" 
					value="<%=(class_infoVO == null) ? "null" : class_infoVO.getVideo_fundraising()%>" /></td>
			</tr>
		
		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
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