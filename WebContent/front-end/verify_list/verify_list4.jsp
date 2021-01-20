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
	Class_infoVO class_infoVO = (Class_infoVO) request.getAttribute("Class_infoVO");
%>

<%
	Class_infoService Srv = new Class_infoService();
	List<Class_infoVO> list = Srv.get_ROWNUM_8();
	pageContext.setAttribute("list", list);
%>

<!doctype html>
<html lang="en">

<head>
<style>
div.col-3{
border: 1px rgb(0, 0, 0) solid;
RIGHT:13PX;
}
</style>
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

<style >
	.preview1{
	
		width:50%;
		height:50%;
	}

.page-item.active .page-link{
background-color: #DC3545;
border-color: #DC3545
}   
 
</style>
</head>

<body>
	<%@ include file="header.jsp"%>
 <!-- container -->
 <div class="container">
	
	<!-- 標題 -->
        <div class="jumbotron jumbotron-fluid">
            <div class="container" style="text-align:center">
                <nav aria-label="...">
                    <ul class="pagination pagination-lg justify-content-center">
                        <li class="page-item"><a class="page-link" href="">1</a></li>
                        <li class="page-item"><a class="page-link" href="">2</a></li>
                        <li class="page-item"><a class="page-link" href="">3</a></li>
                        <li class="page-item active" aria-current="page">
                            <span class="page-link">
                                4
                                <span class="sr-only">(current)</span>
                            </span>
                        </li>
                        <li class="page-item"><a class="page-link" href="">5</a></li>
                        <li class="page-item"><a class="page-link" href="">6</a></li>
                    </ul>
                </nav>
                <h1>好的封面和募資影片很重要!</h1>
                <h4>流利的口條和特別的封面，能吸引學生的目光！</h4>
            </div>
        </div>
        
        <!-- container -->
        
        <%-- 錯誤表列 --%>
	<c:if test="${not empty erMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${erMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
		<div id="content">
		<h1>新增封面及介紹影片</h1>
            <div class="row">
                <div class="col-9">
        <form method="post" action="<%=request.getContextPath()%>/class_info/class_infoServlet?action=class4form" name="form1" enctype="multipart/form-data">
		            <div>
		                <div>
		                <h4>課程封面圖片</h4>
					    <div><input type="file" name="class_picture" id="class_picture" /></div>
					    <div id="preview"></div>
			    	 </div>
			    	 <div style="margin-top: 20px ">
					    <h4>上傳募資影片</h4>
		                <div><input type="file" name="video_fundraising" id="video_fundraising" /></div>		               
		                </div>
		            </div>
            <div style="margin-top: 10px">
            <button class="btn btn-danger" type="button" value="上一步" onclick="location.href='<%=request.getContextPath()%>/front-end/verify_list/verify_list3.jsp'">上一頁</button>
		    <input class="btn btn-danger" type="submit" value="送出審核">
			</div>	
	</form>
		        </div>
		        <div class="col-3">
                    <div class="row">
                        <img  src="<%=request.getContextPath()%>/img/icon/class1.png"
                            alt="XX" style="height: 50px;"> <div style="margin-top: 18px;">影片製作提醒？</div>
		            </div>
		            <div>募資影片須附上字幕，並且注意影像畫面清晰、有流暢的口條、無噴 Mic 及背景雜音。 而影片中須包含課程目標，簡短內容介紹、自我介紹以及教學方式，並且注意畫面素材的使用版權。 送出審核前請確認內容符合『提案審核標準 - 影片』。</div><br>
		            <div class="row">
                        <img  src="<%=request.getContextPath()%>/img/icon/class1.png"
                            alt="XX" style="height: 50px;"> <div style="margin-top: 18px;">沒有拍攝經驗怎麼辦？</div>
		            </div>
		            <div>不同的課程領域有不同的影像呈現方式，選擇你最適合的影片拍攝方式吧！！</div><br>
		            <div class="row">
                        <img  src="<%=request.getContextPath()%>/img/icon/class1.png"
                            alt="XX" style="height: 50px;"> <div style="margin-top: 18px;">需要攝影協助？</div>
		            </div>
		            <div>若你不擅長攝影，希望向 TOMOTO申請課程製作服務，請詳看課程製作服務申請辦法，完成基本資料後向TOMOTO提出申請。</div><br>
                </div>
	     	</div>
		</div>
  </div>

<!-- JavaScript -->
	<script
		src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
			
			
<script>
  
//預覽功能
  function preview_img(file) {
      var img_node = document.createElement("img");
      var reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = function () {
          // 方便觀察
          console.log("change 事件觸發");
          img_node.src = reader.result;
          img_node.setAttribute("class","preview1");
          $("#preview").empty();
          $("#preview").append(img_node);
      };
  }

  //點選圖片預覽
  $("#class_picture").on("change", function (e) {
	  console.log("change");
	  preview_img(this.files[0]);
  });

  
 </script>
	
	<%@ include file="footer.jsp"%>


</body>

</html>