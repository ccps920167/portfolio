<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>DateTimePicker.jsp</title>

<!-- jquery.datetimepicker -->	
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.css" />
<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>


</head>
<body>
<%@ include file="header-back.jsp"%>

	<div style="margin-left: 250px; margin-right: 20px; margin-top: 50px" >
		<nav aria-label="breadcrumb" style="margin-top: 30px;">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#"></a></li>
				<li class="breadcrumb-item active" aria-current="page">購買人次趨勢圖</li>
				<li class="breadcrumb-item active" aria-current="page"></li>
				<li class="breadcrumb-item active" aria-current="page">課程數量分布圖</li>
			</ol>
		</nav>
		<br>
		<div class="container">
			<h1>數據分析</h1>
			<br>
				<div style="display: inline-block; margin-right:200px ">
				 	   開始日期時間: <input name="start_dateTime" id="start_dateTime" type="text" >
				</div>
				<div style="display: inline-block; margin-right:100px ">
				 	   結束日期時間: <input name="end_dateTime"   id="end_dateTime"   type="text" >
				</div>
			</div>
		</div>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<!-- 參考網站: https://xdsoft.net/jqplugins/datetimepicker/ -->

	<!-- popper-->
	<script
		src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>

	<!-- bootstrap -->
	<script
		src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>

	<!-- Chart.js-->
	<script src="<%=request.getContextPath()%>/vendors/chart.js-2.9.4/js/Chart.js"></script>
	
	<!-- jquery-->
	<script
		src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<!-- public.js-->
	<script src="<%=request.getContextPath()%>/js/back-end/public.js"></script>
	

	<!-- jquery.datetimepicker -->
	<script
		src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.full.js"></script>

	
	<script>
	$.datetimepicker.setLocale('zh'); // kr ko ja en
	$(function(){
		 $('#start_dateTime').datetimepicker({
		  format:'Y-m-d H:i',
		  onShow:function(){
		   this.setOptions({
		    maxDate:$('#end_dateTime').val()?$('#end_dateTime').val():false
		   })
		  },
		  timepicker:true,
		  step: 1
		 });
		 
		 $('#end_dateTime').datetimepicker({
		  format:'Y-m-d H:i',
		  onShow:function(){
		   this.setOptions({
		    minDate:$('#start_dateTime').val()?$('#start_dateTime').val():false
		   })
		  },
		  timepicker:true,
		  step: 1
		 });
	});
	</script>

</html>