<!-- 設定JSP編碼 -->
<%@page import="java.sql.Timestamp"%>
<%@page import="com.video_record.model.Video_recordVO"%>
<%@page import="com.video_record.model.Video_recordService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 設定標籤庫 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.class_info.model.*"%>
<%@page import="com.sub_class.model.*"%>
<%@ page import="com.main_class.model.*"%>

<%
	Class_infoVO class_infoVO = (Class_infoVO) request.getAttribute("Class_infoVO");
    Class_infoVO verClass_info = (Class_infoVO) session.getAttribute("class_infoVer1");
    String verifyClass_name = (String) session.getAttribute("verifyClass_name");
    Timestamp verifyStartfund_date = (Timestamp) session.getAttribute("verifyStartfund_date");
    Integer verifyOriginal_price = (Integer)session.getAttribute("verifyOriginal_price");
    Integer verifyStartfund_price = (Integer)session.getAttribute("verifyStartfund_price");
    Integer verifyPeople_threshold = (Integer)session.getAttribute("verifyPeople_threshold");
    String verifyClass_length = (String)session.getAttribute("verifyClass_length");
    String verifyClass_description = (String)session.getAttribute("class_description");
    
	Map<String, List<Sub_classVO>> MainSubClass2 = (Map<String, List<Sub_classVO>>)application.getAttribute("MainSubClass");	
	Set key = MainSubClass2.keySet();
	pageContext.setAttribute("key", key);
	pageContext.setAttribute("MainSubClass", MainSubClass2);
%>

<%
	Class_infoService Srv = new Class_infoService();
	List<Class_infoVO> list = Srv.get_ROWNUM_8();
	pageContext.setAttribute("list", list);
%>

<!doctype html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
<!-- video.js CSS -->
<link
	href="<%=request.getContextPath()%>/vendors/video-js/css/video-js.css"
	rel="stylesheet" />
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
<!-- css -->
<link href="<%=request.getContextPath()%>/css/index.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/header.css"
	rel="stylesheet" />

<link href="https//cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css" rel="stylesheet">
<title>TOMATO - 讓你分分鐘鐘都在學習的平台</title>

<style>
div.col-3{
border: 1px rgb(0, 0, 0) solid;
RIGHT:13PX;
}

.page-item.active .page-link{
background-color: #DC3545;
border-color: #DC3545
}   


</style>

</head>

<body>
	<%@ include file="header.jsp"%>
	
	
	
	<div class="container">
	
	
	
	<!-- 標題 -->
        <div class="jumbotron jumbotron-fluid">
            <div class="container" style="text-align:center">
                <nav aria-label="...">
                    <ul class="pagination pagination-lg justify-content-center">
                        <li class="page-item"><a class="page-link" href="">1</a></li>
                        <li class="page-item active" aria-current="page">
                            <span class="page-link">
                                2
                                <span class="sr-only">(current)</span>
                            </span>
                        </li>
                        <li class="page-item"><a class="page-link" href="">3</a></li>
                        <li class="page-item"><a class="page-link" href="">4</a></li>
                        <li class="page-item"><a class="page-link" href="">5</a></li>
                        <li class="page-item"><a class="page-link" href="">6</a></li>
                    </ul>
                </nav>
                <h1>好的標題和詳細的課程敘述很重要！</h1>
                <h4>仔細地介紹能吸引學生上課的意願！</h4>
            </div>
        </div>

		<div id="content">
            <div class="row">
                <div class="col-9">
                    <h1>新增課程</h1>
		            <br>
		            <div>
		                    <%-- 錯誤表列 --%>
	                    <c:if test="${not empty erMsgs}">
		                    <font style="color: red">請修正以下錯誤:</font>
		                    <ul>
			                    <c:forEach var="message" items="${erMsgs}">
				                    <li style="color: red">${message}</li>
			                    </c:forEach>
		                    </ul>
	                    </c:if>
		            </div>
		            <form method="POST" action="<%=request.getContextPath()%>/class_info/class_infoServlet" enctype="multipart/form-data">
		                <table class="table table-striped" style="border-style:dotted; border-width:0.5px">
			                <tr>
				                <td>課程名稱:</td>
				                <td><input class="form-control" type="TEXT" name="class_name" size="45" id="class_name"
					            value="<%=(verifyClass_name == null) ? "" : verifyClass_name%>" placeholder="請輸入課程名稱" /></td>
			                </tr>
			                <tr>
			                  	<td>副類別課程名稱:</td>
				              	<td>
				              	<select class="form-control" size="1" name="subClass_id" id="sub_class">
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
				              	<td>課程描述:</td>
				              	<td><textarea class="form-control" placeholder="請描述課程" rows="5" cols="50" name="class_description" 
				              	    id="class_description"><%=(class_infoVO == null) ? "" : class_infoVO.getClass_description()%></textarea>
				              	</td>
			              	</tr>
			              	<tr>
				              	<td>募資售價:</td>
				              	<td><input class="form-control" type="TEXT" name="startfund_price" size="45" id="startfund_price"
				              		value="<%=(verifyStartfund_price == null) ? "100" : verifyStartfund_price%>" placeholder="請輸入募資售價"/></td>
			              	</tr>
			              	<tr>
				              	<td>定價:</td>
				              	<td><input class="form-control" type="TEXT" name="original_price" size="45" id="original_price"
					              	value="<%=(verifyOriginal_price == null) ? "200" : verifyOriginal_price%>" placeholder="請輸入定價"/></td>
			              	</tr>
			              	<tr>
				              	<td>門檻人數:</td>
				              	<td><input class="form-control" type="TEXT" name="people_threshold" size="45" id="people_threshold"
					              	value="<%=(verifyPeople_threshold == null) ? "50" : verifyPeople_threshold%>" placeholder="請輸入門檻人數"/></td>
			              	</tr>
			              	<tr>
			              		<td>課程長度:</td>
			              		<td><input class="form-control" type="TEXT" name="class_length" size="45" id="class_length"
			              			value="<%=(verifyClass_length == null) ? "12:00:00" : verifyClass_length%>" placeholder="請輸入課程長度"/></td>
			              	</tr>
		              	</table>
		              	<br> <input class="btn btn-danger" type="hidden" name="action" value="insert"> <input class="btn btn-danger"
		              		type="submit" value="送出新增">
	              	</form>
	        	</div>
		        <div class="col-3">
                   <div class="row">
                       <img  src="<%=request.getContextPath()%>/img/icon/class1.png"
                        alt="XX" style="height: 50px;"> <div style="margin-top: 18px;">該怎麼定價？</div>
		           </div>
		           <div>每個領域的專業與銷售方式都不同，我們建議可以多多詢問課程的目標族群，找出雙方都可以接受的價格！</div><br>
		               <div class="row">
                           <img  src="<%=request.getContextPath()%>/img/icon/class1.png"
                               alt="XX" style="height: 50px;"> <div style="margin-top: 18px;">人數門檻是什麼？</div>
		               </div>
		               <div>TOMATO 是透過募資機制來開課的，因此人數門檻就是開課的標準。課程必須等待報名學生超過人數門檻後，才能正式開課！</div><br>
                       <div class="row">
                           <img  src="<%=request.getContextPath()%>/img/icon/class1.png"
                               alt="XX" style="height: 50px;"> <div style="margin-top: 18px;">我在 TOMATO的收入怎麼算？</div>                   
                       </div>
                       <div>假設學生透過 TOMATO一般網址購買，老師將會得到課程售價的五成。若當月份有 50 人透過 TOMATO一般網址以 100 元的價格購買時，老師可以得到 50 x 100 x 0.5 = 2,500 元的收入（此計算公式不包含雙方共同承擔的營業稅部分）</div><br>
	     		       <div class="row">
                           <img  src="<%=request.getContextPath()%>/img/icon/class1.png"
                               alt="XX" style="height: 50px;"> <div style="margin-top: 18px;">備課天數是做什麼用的？</div>                   
                       </div>
                       <div>這是讓學生在募資期間可以先知道預計課程開始的日期。老師可以提前開課，但不能晚於預計時間開課！</div><br>
                 </div>
	     	</div>
		</div>

	</div>

	<%@ include file="footer.jsp"%>
	
	<!-- JavaScript -->
		<script
			src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
		<script>
		<!-- ajax -->
	<script>
     
	//AJAX 送出資料(文字)
  	 window.document.body.onbeforeunload =function(){
        var data = {
                "action": "class2Form",
                "class_name": $("#class_name").val(),
                "original_price": $("#original_price").val(),
                "startfund_price": $("#startfund_price").val(),
                "people_threshold": $("#people_threshold").val(),
                "class_length": $("#class_length").val(),
                "sub_class":$("#sub_class").val(),
                "class_description":$("#class_description").val()
            };
        $.ajax({
            url: "<%=request.getContextPath()%>/class_info/class_infoServlet",
            type: "POST",                  // GET | POST | PUT | DELETE | PATCH
            data: data,               // 傳送資料到指定的 url
        })
	}
	</script>

</body>

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
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
// 		value : new Date(), // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	minDate:               '-1970-01-01', // 去除今日(不含)之前
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