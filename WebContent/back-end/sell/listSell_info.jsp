<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member_info.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>TOMATO後台管理平台</title>

<!---------------------------------------- css ---------------------------------------->
<link
	href="<%=request.getContextPath()%>/vendors/chart.js-2.9.4/css/Chart.css"
	rel="stylesheet" type="text/css">

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

<style>
#tab-demo {
            width: 800px;
            position: relative;
            top: 50px;
        }

        #tab-demo>ul {
            display: block;
            margin: 0;
            list-style: none;
        }

        .tab-title {
            list-style: none;
        }

        #tab-demo>ul>li {
            display: inline-block;
            font-family: '微軟正黑體';
            font-size: 18px;
            border: 1px solid #bcbcbc34;
            height: 40px;
            line-height: 35px;
            background: #e2e2e2;
            padding: 0 15px;
            list-style: none;
            box-sizing: border-box;
/*             width: 200px; */
            margin: 10px;
            text-align: center;
            border-radius: 5px 5px 0px 0px;

        }

        #tab-demo>ul>li a {
            color: rgb(151, 150, 150);
            text-decoration: none;
        }

        #tab-demo>ul>li.active {
            border-bottom: 1px solid #fff;
            background: #fff;
        }


        #tab-demo>.tab-inner {
            clear: both;
            color: #000;
            border: 1px solid #bcbcbc34;
        }

        .tab-inner {
            margin: 0px;

        }
        .image{
            margin: 10px;
            color: #000;
        }
        .statement{
            color: rgb(151, 150, 150);
        }
        h3{
            color: rgb(163 226 182);
        }
        .classstatement{
            border: 1px solid #bcbcbc34;
            border-radius: 5px;
            color:  rgb(151, 150, 150);
        }

</style>

<!---------------------------------------- css ---------------------------------------->

</head>
<body>
	<%@ include file="header-back.jsp"%>

	<div style="margin-left: 250px; margin-right: 20px; margin-top: 50px">
		<nav aria-label="breadcrumb" style="margin-top: 30px;">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">home</a></li>
				<li class="breadcrumb-item active" aria-current="page">購買人次趨勢圖</li>
				<li class="breadcrumb-item active" aria-current="page">課程數量分布圖</li>
			</ol>
		</nav>
		<br>
		<div class="container">
			<h1>數據分析</h1>
			<div>
			<div style="display: inline-block; margin-right: 100px">
				<label for="start_dateTime">開始日期時間</label> <input
					class="form-control" name="start_dateTime" id="start_dateTime"
					type="text">
			</div>
			<div style="display: inline-block; margin-right: 100px">
				<label for="end_dateTime">結束日期時間</label> <input class="form-control"
					name="end_dateTime" id="end_dateTime" type="text">
			</div>
			<button id="postBt" class="btn btn-danger" style="vertical-align:bottom;">送出搜尋</button>
			<br>
			</div>
			<div id="tab-demo">
            <ul class="tab-title">
                <li><a href="#tab01"><b>營業額趨勢圖</b></a></li>
                <li><a href="#tab02"><b>購買人次趨勢圖</b></a></li>
                <li><a href="#tab03"><b>會員人數趨勢圖</b></a></li>
                <li><a href="#tab04"><b>課程數量分布圖</b></a></li>
			</ul>
            <div id="tab01" class="tab-inner">
				<!-- 營業額趨勢圖 -->
				<h1 style="margin: 30px">營業額趨勢圖</h1>
				<div
					style="width: 700px; ">
					<canvas id="myChart" width=500 height=200></canvas>
				</div>
				
            </div>
            <div id="tab02" class="tab-inner">
                <h1 style="margin: 30px">購買人次趨勢圖</h1>
				<div
						style="width: 700px; ">
						<canvas id="myChart2" width=500 height=200></canvas>
					</div>
            </div>
            <div id="tab03" class="tab-inner">
                <h1 style="margin: 30px">會員人數趨勢圖</h1>
				<div
						style="width: 700px; ">
						<canvas id="myChart3" width=500 height=200></canvas>
					</div>
            </div>
            <div id="tab04" class="tab-inner">
                <h1 style="margin: 30px">課程數量分布圖</h1>
				<div
						style="width: 700px; ">
						<canvas id="myChart4" width=500 height=200></canvas>
					</div>
            </div>
        </div>

		</div>
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

	<!-- jquery.datetimepicker -->
	<script
		src="<%=request.getContextPath()%>/back-end/sell/jquery.datetimepicker.full.js"></script>

	<!-- public.js-->
	<script src="<%=request.getContextPath()%>/js/back-end/public.js"></script>

	<!-- Chart.js-->
	<script
		src="<%=request.getContextPath()%>/vendors/chart.js-2.9.4/js/Chart.js"></script>

	
	<script>
        $(function () {
            var $li = $('ul.tab-title li');
            $($li.eq(0).addClass('active').find('a').attr('href')).siblings('.tab-inner').hide();

            $li.click(function () {
                $($(this).find('a').attr('href')).show().siblings('.tab-inner').hide();
                $(this).addClass('active').siblings('.active').removeClass('active');
            });
        });
    </script>
	
	<!---------------------------------------- script ---------------------------------------->
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


	<script>
	 
	 document.getElementById("postBt").addEventListener("click", function () {
		 //取輸入資料
         var start_dateTime = document.getElementById("start_dateTime").value;
         var end_dateTime = document.getElementById("end_dateTime").value;
         var data = {
       		 "action": "sell",
             "start_dateTime": start_dateTime,
             "end_dateTime": end_dateTime
         };
         var color = [
             'rgba(255, 99, 132)','rgba(54, 162, 235)','rgba(183, 102, 173)','rgba(128, 214, 0)',
             'rgba(255, 99, 132)','rgba(54, 162, 235)','rgba(183, 102, 173)','rgba(128, 214, 0)',
             'rgba(255, 99, 132)','rgba(54, 162, 235)','rgba(183, 102, 173)','rgba(128, 214, 0)',
             'rgba(255, 99, 132)','rgba(54, 162, 235)','rgba(183, 102, 173)','rgba(128, 214, 0)',
             'rgba(255, 99, 132)','rgba(54, 162, 235)','rgba(183, 102, 173)','rgba(128, 214, 0)',
             'rgba(255, 99, 132)','rgba(54, 162, 235)','rgba(183, 102, 173)','rgba(128, 214, 0)',
             'rgba(255, 99, 132)','rgba(54, 162, 235)','rgba(183, 102, 173)','rgba(128, 214, 0)',
             'rgba(255, 99, 132)','rgba(54, 162, 235)','rgba(183, 102, 173)','rgba(128, 214, 0)',
             'rgba(255, 99, 132)','rgba(54, 162, 235)','rgba(183, 102, 173)','rgba(128, 214, 0)',
             'rgba(255, 99, 132)','rgba(54, 162, 235)','rgba(183, 102, 173)','rgba(128, 214, 0)',
             'rgba(255, 99, 132)','rgba(54, 162, 235)','rgba(183, 102, 173)','rgba(128, 214, 0)',
             'rgba(255, 99, 132)','rgba(54, 162, 235)','rgba(183, 102, 173)','rgba(128, 214, 0)',
             'rgba(255, 99, 132)','rgba(54, 162, 235)','rgba(183, 102, 173)','rgba(128, 214, 0)',
             'rgba(255, 99, 132)','rgba(54, 162, 235)','rgba(183, 102, 173)','rgba(128, 214, 0)',
             'rgba(255, 99, 132)','rgba(54, 162, 235)','rgba(183, 102, 173)','rgba(128, 214, 0)',
             'rgba(255, 99, 132)','rgba(54, 162, 235)','rgba(183, 102, 173)','rgba(128, 214, 0)',
             'rgba(255, 99, 132)','rgba(54, 162, 235)','rgba(183, 102, 173)','rgba(128, 214, 0)',
             'rgba(255, 99, 132)','rgba(54, 162, 235)','rgba(183, 102, 173)','rgba(128, 214, 0)',
             
         ];

         $.ajax({
             url: "<%=request.getContextPath()%>/sell/SellOrderSrevlet",
             type: "POST",                  // GET | POST | PUT | DELETE | PATCH
             data: data,               // 傳送資料到指定的 url
             dataType:"json",
             success: function (e) {      // request 成功取得回應後執行
            	 console.log(e.Purchasers[0].labels)
				 //營業額趨勢圖 
			
			     var myLineChart = new Chart(document.getElementById('myChart'), {
			         type: 'line',
			         data: {
			             labels: e.Turnover[0].labels,
			             datasets: [{
			            	 backgroundColor: color,
			            	 borderColor: color,
				             label: '單日訂單總金額/元',
				             data: e.Turnover[1].data
			             }]
			         }
			         
			     });
				//購買人次趨勢圖
			     var myChart = new Chart(document.getElementById('myChart2'), {
			         type: 'bar',
			         data: {
			             labels: e.Purchasers[0].labels,
			             datasets: [{
			            	backgroundColor: color,
			             	borderWidth: 1,
			             	label: '單日訂單數量/筆',
			             	data: e.Purchasers[1].data
			             }]
			         }
			     });
				
			 	//會員人數趨勢圖
			     var myChart3 = new Chart(document.getElementById('myChart3'), {
			         type: 'bar',
			         data: {
			             labels: e.newMember[0].labels,
			             datasets: [{
			            	 backgroundColor: color,
				             borderWidth: 1,
				             label: '新增會員人數/人',
				             data: e.newMember[1].data
			             }]
			         }
			     });
			 	
			 	//課程數量分布圖
			 	var myChart4 = new Chart(document.getElementById('myChart4'), {
			         type: 'pie',
			         data: {
			             labels: e.subClass[0].labels,
			             datasets: [{
				             backgroundColor: color,
				             borderWidth: 1,
				             label: '課程分布圖',
				             data: e.subClass[1].data
			             }]
			         }
			     });
             	}
         	});
	 	});
    </script>
</body>

</html>