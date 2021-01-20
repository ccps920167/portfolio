<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.Date"%>
<%-- <%@page import="java.sql.Timestamp"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coupon.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	CouponVO couponVO = (CouponVO) request.getAttribute("couponVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>新增資料</title>
</head>
<body>
	<c:if test="${not empty error}">
		<font style="color: red"></font>
		<ul>
			<c:forEach var="message" items="${error}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" action="<%=request.getContextPath()%>/coupon/CouponServlet">
		<table>
			<tr>
				<td><b>優惠卷代碼:</b></td>
				<td><input type="TEXT" name="coupon_code" size="30"
					value="<%=(couponVO == null) ? "" : couponVO.getCoupon_code()%>" /></td>
			</tr>

			<tr>
				<td><b>優惠卷金額:</b></td>
				<td><input type="text" name="coupon_amount" size="30"
					value="<%=(couponVO == null) ? "" : couponVO.getCoupon_amount()%>" />
				</td>
			</tr>
			<tr>
				<td><b>優惠卷發送時間:</b></td>
				<td><input type="text" name="coupon_time" size="30" id="f_date1">
				</td>
			</tr>
			<tr>
				<td><b>使用有效期限:</b></td>
				<td><input type="text" name="coupon_expiry" size="30" id="f_date2"/>
			</tr>
		</table>
		<input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
			
			
	</FORM>

</body>


<%
	Timestamp coupon_time = null;
	try {
		coupon_time = couponVO.getCoupon_time();
	} catch (Exception e) {
		coupon_time = new Timestamp(System.currentTimeMillis());
	}
%>

<%
	Timestamp coupon_expiry = null;
	try {
		coupon_expiry = couponVO.getCoupon_expiry();
	} catch (Exception e) {
		coupon_expiry = new Timestamp(System.currentTimeMillis());
	}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.css"/>
<script
	src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.js"></script>
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
 	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',   //format:'Y-m-d H:i:s',
		   value: '<%=coupon_time%>', // value:  new Date(),
        });
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	$.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',   //format:'Y-m-d H:i:s',
		   value: '<%=coupon_expiry%>', // value:  new Date(),
	});

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.以下為某一天之後的日期無法選擇
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>

</html>