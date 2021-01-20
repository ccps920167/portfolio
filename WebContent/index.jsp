<!-- 設定JSP編碼 -->
<%@page import="com.video_record.model.Video_recordVO"%>
<%@page import="com.video_record.model.Video_recordService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 設定標籤庫 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.class_info.model.*"%>


<%
	Class_infoService Srv = new Class_infoService();
	List<Class_infoVO> list = Srv.get_ROWNUM_8();
	pageContext.setAttribute("list", list);
	
	List<Class_infoVO> list2=Srv.getTop8();
	pageContext.setAttribute("list2", list2);
	
	List<Class_infoVO> list3=Srv.getFund();
	pageContext.setAttribute("list3", list3);
	
	List<String> listtest=(List<String>)request.getAttribute("listtest"); //民哲
	Member_infoVO member_infoVOLogin = (Member_infoVO)request.getAttribute("member_infoVO");
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

<link
	href="<%=request.getContextPath()%>/css/index_page.css"
	rel="stylesheet" />	
<link
	href="<%=request.getContextPath()%>/css/header.css"
	rel="stylesheet" />		




<title>TOMATO - 讓你分分鐘鐘都在學習的平台</title>

<style>



[type=radio] {
display: none;
}

#slider {
height: 35vw;
position: relative;
perspective: 1000px;
transform-style: preserve-3d;
}

#slider label {
margin: auto;
    width: 600px;
    height: 450px;
    border-radius: 25px;
position: absolute;
left: 0; right: 0;
cursor: pointer;
transition: transform 0.7s ease;
}

#s0:checked ~ #slide4,#s1:checked ~ #slide5,
#s2:checked ~ #slide6,#s3:checked ~ #slide0, 
#s4:checked ~ #slide1,#s5:checked ~ #slide2,
#s6:checked ~ #slide3{
box-shadow: 0 1px 4px 0 rgba(0,0,0,.5);
transform: translate3d(-45%,0,-300px);
}


#s0:checked ~ #slide5,#s1:checked ~ #slide6,
#s2:checked ~ #slide0,#s3:checked ~ #slide1, 
#s4:checked ~ #slide2,#s5:checked ~ #slide3,
#s6:checked ~ #slide4 {
box-shadow: 0 1px 4px 0 rgba(0,0,0,.37);
transform: translate3d(-30%,0,-200px);
}

#s0:checked ~ #slide6,#s1:checked ~ #slide0,
#s2:checked ~ #slide1,#s3:checked ~ #slide2, 
#s4:checked ~ #slide3,#s5:checked ~ #slide4,
#s6:checked ~ #slide5 {
box-shadow: 0 6px 10px 0 rgba(0,0,0,.3), 0 2px 2px 0 rgba(0,0,0,.2);
transform: translate3d(-15%,0,-100px);
}

#s0:checked ~ #slide0,#s1:checked ~ #slide1,
#s2:checked ~ #slide2,#s3:checked ~ #slide3, 
#s4:checked ~ #slide4,#s5:checked ~ #slide5,
#s6:checked ~ #slide6 {
box-shadow: 0 13px 25px 0 rgba(0,0,0,.3), 0 11px 7px 0 rgba(0,0,0,.19);
transform: translate3d(0,0,0);
}

#s0:checked ~ #slide1,#s1:checked ~ #slide2,
#s2:checked ~ #slide3,#s3:checked ~ #slide4, 
#s4:checked ~ #slide5,#s5:checked ~ #slide6,
#s6:checked ~ #slide0 {
box-shadow: 0 6px 10px 0 rgba(0,0,0,.3), 0 2px 2px 0 rgba(0,0,0,.2);
transform: translate3d(15%,0,-100px);
}

#s0:checked ~ #slide2,#s1:checked ~ #slide3,
#s2:checked ~ #slide4,#s3:checked ~ #slide5, 
#s4:checked ~ #slide6,#s5:checked ~ #slide0,
#s6:checked ~ #slide1 {
box-shadow: 0 1px 4px 0 rgba(0,0,0,.37);
transform: translate3d(30%,0,-200px);
}



#s0:checked ~ #slide3,#s1:checked ~ #slide4,
#s2:checked ~ #slide5,#s3:checked ~ #slide6, 
#s4:checked ~ #slide0,#s5:checked ~ #slide1,
#s6:checked ~ #slide2 {
box-shadow: 0 1px 4px 0 rgba(0,0,0,.5);
transform: translate3d(45%,0,-300px);
}
.chatarea{
border: 1px solid;
width:400px;
height:400px;
    position: fixed;
    right: 53px;
    bottom: 69px;
}

 .chatarea{
            z-index: 10;
        }
    </style>
</head>
<body>

<c:if test="${not empty listtest}">
	<script>
		alert('購買完成');
	</script>
</c:if> 
<c:if test="${not empty member_infoVOLogin}">
	<script>
	 	alert('登入成功');
	</script>
</c:if>
	<%@ include file="header.jsp"%>
	<!-- 首頁圖 -->
	<div class="container-fluid sidebar"">
<!-- 		<div id="search"> -->
<%--             <form class="form-inline my-2 my-lg-0" style="position:left" action="<%=request.getContextPath()%>/Class_info/Class_Introduction"> --%>
<!-- 				<input name="action" value="search" type="hidden"> -->
<!--                 <input name="class_status" value="4" type="hidden"> -->
<!--                 <input name="Category" value="class_list_search" type="hidden"> -->
<!--                 <input class="form-control mr-sm-2" name="class_name" type="search" placeholder="輸入你想要的課程" aria-label="Search"> -->
<!--                 <button class="btn btn-outline-danger my-2 my-sm-0" type="submit">Search</button> -->
<!-- 			</form> -->
<!-- 		</div> -->
<div>
 <section id="slider">
        <input type="radio" name="slider" id="s0" value="0">
        <input type="radio" name="slider" id="s1" value="1">
        <input type="radio" name="slider" id="s2" value="2">
        <input type="radio" name="slider" id="s3" value="3" checked>
        <input type="radio" name="slider" id="s4" value="4">
        <input type="radio" name="slider" id="s5" value="5">
        <input type="radio" name="slider" id="s6" value="6">
      
        
        <c:forEach var="class_infoVO" items="${list2}" varStatus="loop" begin="0" end="6">
        <label for="s${loop.index}" id="slide${loop.index}">
            <img src="<%=request.getContextPath()%>/Class_info/Class_Introduction?action=class_pic_lg&class_id=${class_infoVO.class_id}" height="100%" width="100%">
        	<a href="<%=request.getContextPath()%>/Class_info/Class_learnServlet?action=class_Introduction&class_id=${class_infoVO.class_id}">
	            <span style="font-size:30px;position:relative;top:-50px;color:white;text-shadow: 0.1em 0.1em 0.2em black;text-decoration:none" ><b>${class_infoVO.class_name}</b></span>
            </a>
        </label>
        
         </c:forEach>
      </section>

	</div>


	</div>
	<!-- container -->
	
	
	
	
	<div class="container" style="margin-bottom: 50px;">
	<!--********************************************************************* 課程卡片 熱門課程-->


<!--********************************************************************* 課程卡片 猜你想學-->
 <div class="class-card" style="height: 50px; margin-top: 50px;">
  <h2>猜你想學</h2>
  <h6>依您的興趣${slistname}推薦相關課程</h6>
 </div>
 <div class="row">
  <div>
   <div class="col-sm-12" style="margin-top: 20px;">
    <div class="card-deck">
<%--      <c:forEach var="class_infoVO" items="${memer_infoVO != null ? ROWNUM_8_Bysub_class : list}" varStatus="status" --%>
     <c:forEach var="class_infoVO" items="${not empty ROWNUM_8_Bysub_class ? ROWNUM_8_Bysub_class : list}" varStatus="status"
      begin="0" end="3">
      <div class="card shadow rounded">
       <a
        href="<%=request.getContextPath()%>/Class_info/Class_learnServlet?action=class_Introduction&class_id=${class_infoVO.class_id}">

        <img class="card-img-top shadow-sm rounded"
        src="<%=request.getContextPath()%>/Class_info/Class_Introduction?action=class_pic_sm&class_id=${class_infoVO.class_id}"
        alt="Card image cap">
        <div class="card-body">
         <h6 class="card-title" style="height: 30px">${class_infoVO.class_name}</h6>
       </a>
       <p class="card-text" style="line-height: 1">
        <small class="text-muted"> 授課老師：<a
         href="<%=request.getContextPath()%>/guest/guestMember_Servlet?action=member_infoPage&member_id=${class_infoVO.member_id}">
         ${class_infoVO.member_id}</a><br>
         課程售價：${class_infoVO.original_price}<br>
         課程時數：${class_infoVO.class_length}<br>
         開課時間：<fmt:formatDate value="${class_infoVO.startfund_date}" pattern="yyyy-MM-dd HH:mm:ss"/><br>
        </small>
       </p>
      </div>
    </div>
    </c:forEach>
   </div>
  </div>
  <div class="col-sm-12" style="margin-top: 20px;">
   <div class="card-deck">
    <c:forEach var="class_infoVO" items="${not empty ROWNUM_8_Bysub_class ? ROWNUM_8_Bysub_class : list}" varStatus="status"
     begin="4" end="7">
     <div class="card shadow rounded">
      <a
       href="<%=request.getContextPath()%>/Class_info/Class_learnServlet?action=class_Introduction&class_id=${class_infoVO.class_id}">

       <img class="card-img-top shadow-sm rounded"
       src="<%=request.getContextPath()%>/Class_info/Class_Introduction?action=class_pic_sm&class_id=${class_infoVO.class_id}"
       alt="Card image cap">
       <div class="card-body">
        <h6 class="card-title" style="height: 30px">${class_infoVO.class_name}</h6>
      </a>
      <p class="card-text" style="line-height: 1">
       <small class="text-muted"> 授課老師：<a
        href="<%=request.getContextPath()%>/guest/guestMember_Servlet?action=member_infoPage&member_id=${class_infoVO.member_id}">
         ${class_infoVO.member_id}</a><br>
        課程售價：${class_infoVO.original_price}<br>
        課程時數：${class_infoVO.class_length}<br>
        開課時間：<fmt:formatDate value="${class_infoVO.startfund_date}" pattern="yyyy-MM-dd HH:mm:ss"/><br>
       </small>
      </p>
     </div>
   </div>
   </c:forEach>
  </div>
 </div>
 </div>
 </div>
<!--********************************************************************* 課程卡片 募資課程-->
		<div class="class-card" style="height: 50px; margin-top: 50px;">
			<h2 style="display: inline-block;">募資課程</h2> 
		  	<h6>現在募資中的課程</h6>
		</div>
		<div class="row">
			<div>
					<jsp:useBean id="orderSvc" scope="page" class="com.order_list.model.Order_listService" />
				<div class="col-sm-12" style="margin-top: 20px;">
					<div class="card-deck">
						<c:forEach var="class_infoVO" items="${list3}" varStatus="status" begin="0" end="3">
								<div class="card shadow rounded">
									<a href="<%=request.getContextPath()%>/Class_info/Class_learnServlet?action=class_Introduction&class_id=${class_infoVO.class_id}">
										<img class="card-img-top shadow-sm rounded" src="<%=request.getContextPath()%>/Class_info/Class_Introduction?action=class_pic_sm&class_id=${class_infoVO.class_id}" alt="Card image cap">
										<h6 class="card-title" style="height: 30px ; padding: 20px">${class_infoVO.class_name}</h6>
									</a>
									<div class="card-body">
										<p class="card-text" style="line-height:1">
											<small class="text-muted" >
												
												募資價格:${class_infoVO.startfund_price}<br>
												
												<c:set var="now" value="${class_infoVO.startfund_date}" />
											            <c:set target="${now}" property="time" value="${now.time + 86400000 * 30}" />
											            到期日 :<span id="date"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/></span><br>
												
												
												購買人數：${orderSvc.getNumClass(class_infoVO.class_id).size()}<br>
												距離募資還差${class_infoVO.people_threshold-orderSvc.getNumClass(class_infoVO.class_id).size()}人<br>
												
											</small>
											<div class="progress">
  													<div class="progress-bar progress-bar-striped bg-warning" role="progressbar" style="width:${orderSvc.getNumClass(class_infoVO.class_id).size()/class_infoVO.people_threshold*100}%" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>
											</div>
										</p>
									</div>
								
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="col-sm-12" style="margin-top: 20px;">
					<div class="card-deck">
						<c:forEach var="class_infoVO" items="${list3}" varStatus="status" begin="4" end="7">
								<div class="card shadow rounded">
									<a href="<%=request.getContextPath()%>/Class_info/Class_learnServlet?action=class_Introduction&class_id=${class_infoVO.class_id}">
										<img class="card-img-top shadow-sm rounded" src="<%=request.getContextPath()%>/Class_info/Class_Introduction?action=class_pic_sm&class_id=${class_infoVO.class_id}" alt="Card image cap">
										<h6 class="card-title" style="height: 30px ; padding: 20px">${class_infoVO.class_name}</h6>
									</a>
									<div class="card-body">
										<p class="card-text" style="line-height:1">
											<small class="text-muted" >
												
												募資價格:${class_infoVO.startfund_price}<br>
												
												<c:set var="now" value="${class_infoVO.startfund_date}" />
											            <c:set target="${now}" property="time" value="${now.time + 86400000 * 30}" />
											            到期日 :<span id="date"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/></span><br>
												
												
												購買人數：${orderSvc.getNumClass(class_infoVO.class_id).size()}<br>
												距離募資還差${class_infoVO.people_threshold-orderSvc.getNumClass(class_infoVO.class_id).size()}人<br>
												
											</small>
											<div class="progress">
  													<div class="progress-bar progress-bar-striped bg-warning" role="progressbar" style="width:${orderSvc.getNumClass(class_infoVO.class_id).size()/class_infoVO.people_threshold*100}%" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>
											</div>
										</p>
									</div>
								
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
			</div>

		
		<!-- container end -->
		
		<!--  聊天室-->
	

	<c:if test="${not empty member_infoVO }">
		<br> <img id="talk" src="<%=request.getContextPath()%>/img/icon/chat.png">
		<div id="msgwindow"><%@ include file="member_chat.jsp"%></div>
	</c:if>

	<%@ include file="footer.jsp"%>

	<!-- JavaScript -->
	<script
		src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/jquery/jquery-timers.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/video-js/js/video.js"></script>

<script>
		$("#talk").on("click",function(){
			$("#msgwindow").toggle()
		})
		
		$('#message').on('keyup click', function(event) {
		
			if(event.which == 13 || event.which == 1){
			$(".column").trigger("click");
			}
		
		})

	</script>


</body>

</html>