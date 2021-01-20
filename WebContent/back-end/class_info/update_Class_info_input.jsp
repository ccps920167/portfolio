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
<title>upgate_Class_info_input</title>

<style>
table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>
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

<form method="post" action="<%=request.getContextPath()%>/class_info/class_infoServlet"
		name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>課程名稱:</td>
				<td><input type="TEXT" name="class_name" size="45"
					value="<%=class_infoVO.getClass_name()%>" /></td>
			</tr>
			<tr>
				<td>會員編號:</td>
				<td><input type="TEXT" name="member_id" size="45"
					value="<%=class_infoVO.getMember_id()%>" /></td>
			</tr>
			<tr>
				<td>副類別課程編號:</td>
				<td><input type="TEXT" name="subclass_id" size="45"
					value="<%=class_infoVO.getSubclass_id()%>" /></td>
			</tr>
			<tr>
				<td>開始募資日期:</td>
				<td><input type="TEXT" name="startfund_date" id="f_date1" value="<%=class_infoVO.getStartfund_date()%>" /></td>
			</tr>
			<tr>
				<td>開課日期:</td>
				<td><input type="TEXT" name="startclass_time" id="f_date2" value="<%=class_infoVO.getStartclass_time()%>" /></td>
			</tr>
			<tr>
				<td>課程描述:</td>
				<td><textarea rows="5" cols="50" name="class_description"><%=class_infoVO.getClass_description()%></textarea>
				</td>
			</tr>
					<tr>
				<td>課程封面圖片:</td>
				<td><input type="FILE" name="class_picture" 
					value="<%=class_infoVO.getClass_picture()%>" /></td>
			</tr> 
			<tr>
				<td>募資售價:</td>
				<td><input type="TEXT" name="startfund_price" size="45"
					value="<%=class_infoVO.getStartfund_price()%>" /></td>
			</tr>
			<tr>
				<td>定價:</td>
				<td><input type="TEXT" name="original_price" size="45"
					value="<%=class_infoVO.getOriginal_price()%>" /></td>
			</tr>
			<tr>
				<td>門檻人數:</td>
				<td><input type="TEXT" name="people_threshold" size="45"
					value="<%=class_infoVO.getPeople_threshold()%>" /></td>
			</tr>
			<tr>
				<td>課程長度:</td>
				<td><input type="TEXT" name="class_length" size="45"
					value="<%=class_infoVO.getClass_length()%>" /></td>
			</tr>
			<tr>
				<td>募資影片:</td>
				<td><input type="FILE" name="video_fundraising" 
					value="<%=class_infoVO.getVideo_fundraising()%>" /></td>
			</tr>

		</table>
		
		<br> 
		<input type="hidden" name=class_id value="<%=class_infoVO.getClass_id()%>">
		<input type="hidden" name="action" value="update"> 
		<input type="submit" value="送出修改">
	</form>

	<h2>
		<a href="<%=request.getContextPath()%>/back-end/class_info/select_pageClass_info.jsp">回到首頁</a>
	</h2>

</body>

<%
//  	Timestamp upload_time = null;

//  	try {
//  		upload_time = Class_infoVO.getUpload_time();
//  	} catch (Exception e) {
//  		upload_time = new Timestamp(System.currentTimeMillis());
// 	}
%>


<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

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