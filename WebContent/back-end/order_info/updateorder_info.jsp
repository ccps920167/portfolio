<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order_info.model.*"%>
<%@page import="java.sql.Timestamp"%>

<%
	Order_infoVO order_infoVO = (Order_infoVO) request.getAttribute("order_infoVO");
%>
<html>
<head>

<title>Insert title here</title>
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
	<FORM method="post"
		action="<%=request.getContextPath()%>/order_info/Order_infoServlet">
		<table>
			<tr>
				<td><b>訂單編號:</b></td>
				<td><%=order_infoVO.getOrder_ID()%></td>
			</tr>

			<tr>
				<td><b>會員編號:</b></td>
				<td><input type="text" name="member_id"
					value=<%=order_infoVO.getMember_id()%>></td>
			</tr>

			<tr>
				<td><b>訂單時間:</b></td>
				<td><input  name="order_time" id="f_date1" type="text"
					value=<%=order_infoVO.getOrder_time()%>></td>
			</tr>

			<tr>
				<td><b>付款時間:</b></td>
				<td><input id="f_date2" type="text" name="payment_time"
					value=<%=order_infoVO.getPayment_time()%>></td>
			</tr>

			<tr>
				<td><b>付款方式/匯款/信用卡:</b></td>
				<td><input type="text" name="pay_way"
					value=<%=order_infoVO.getPay_way()%>></td>
			</tr>

			<tr>
				<td><b>訂單狀態:</b></td>
				<td><input type="text" name="order_status"
					value=<%=order_infoVO.getOrder_status()%>></td>
			</tr>

			<tr>
				<td><b>優惠卷編號:</b></td>
				<td><input type="text" name="coupon_ID"
					value=<%=order_infoVO.getCoupon_ID()%>></td>
			</tr>
		</table>
		<input type="hidden" name="action" value="update"> <input
			type="hidden" name="order_ID" value=<%=order_infoVO.getOrder_ID()%>>
		<input type="submit" value="送出修改">
	</FORM>
</body>
<%
	Timestamp order_time = null;
	try {
		order_time = order_infoVO.getOrder_time();
	} catch (Exception e) {
		order_time = new Timestamp(System.currentTimeMillis());
	}
%>

<%
	Timestamp payment_time = null;
	try {
		payment_time = order_infoVO.getPayment_time();
	} catch (Exception e) {
		payment_time = new Timestamp(System.currentTimeMillis());
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
		   value: '<%=order_time%>', // value:  new Date(),
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
		   value: '<%=payment_time%>', // value:  new Date(),
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