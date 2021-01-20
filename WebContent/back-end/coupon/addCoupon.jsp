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
<title>�s�W���</title>
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
				<td><b>�u�f���N�X:</b></td>
				<td><input type="TEXT" name="coupon_code" size="30"
					value="<%=(couponVO == null) ? "" : couponVO.getCoupon_code()%>" /></td>
			</tr>

			<tr>
				<td><b>�u�f�����B:</b></td>
				<td><input type="text" name="coupon_amount" size="30"
					value="<%=(couponVO == null) ? "" : couponVO.getCoupon_amount()%>" />
				</td>
			</tr>
			<tr>
				<td><b>�u�f���o�e�ɶ�:</b></td>
				<td><input type="text" name="coupon_time" size="30" id="f_date1">
				</td>
			</tr>
			<tr>
				<td><b>�ϥΦ��Ĵ���:</b></td>
				<td><input type="text" name="coupon_expiry" size="30" id="f_date2"/>
			</tr>
		</table>
		<input type="hidden" name="action" value="insert"> <input
			type="submit" value="�e�X�s�W">
			
			
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
	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Y-m-d H:i:s',   //format:'Y-m-d H:i:s',
		   value: '<%=coupon_time%>', // value:  new Date(),
        });
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
	//startDate:	            '2017/07/10',  // �_�l��
	//minDate:               '-1970-01-01', // �h������(���t)���e
	//maxDate:               '+1970-01-01'  // �h������(���t)����
	$.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Y-m-d H:i:s',   //format:'Y-m-d H:i:s',
		   value: '<%=coupon_expiry%>', // value:  new Date(),
	});

	// ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

	//      1.�H�U���Y�@�Ѥ��e������L�k���
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

	//      2.�H�U���Y�@�Ѥ��᪺����L�k���
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

	//      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
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