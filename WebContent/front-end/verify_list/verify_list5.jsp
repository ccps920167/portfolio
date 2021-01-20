<!-- 設定JSP編碼 -->
<%@page import="com.allClass_info.model.*"%>
<%@page import="com.class_chapter.model.Class_chapterService"%>
<%@page import="com.video_record.model.Video_recordVO"%>
<%@page import="com.video_record.model.Video_recordService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 設定標籤庫 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.class_chapter.model.Class_chapterVO"%>
<%@page import="com.class_unit.model.Class_unitVO"%>
<%@page import="com.class_unit.model.Class_unitService"%>
<%@ page import="java.util.*"%>
<%@ page import="com.class_info.model.*"%>
<%@ page import="com.class_chapter.model.*"%>

<%
Class_infoVO class_infoVO = (Class_infoVO) session.getAttribute("class_infoVO");
String class_id = (String)session.getAttribute("verifyClass_id");
AllClass_infoVO Svc = new AllClass_infoService().getAll(class_id);
Map<Class_chapterVO, List<Class_unitVO>> chapterAndUnit = Svc.getChapterAndUnit();
Set chapterAndUnitKeys = chapterAndUnit.keySet();
pageContext.setAttribute("chapterAndUnitKeys", chapterAndUnitKeys);
pageContext.setAttribute("chapterAndUnit", chapterAndUnit);
%>


<!doctype html>
<html lang="en">

<head>

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
                        <li class="page-item"><a class="page-link" href="">2</a></li>
                        <li class="page-item"><a class="page-link" href="">3</a></li>
                        <li class="page-item"><a class="page-link" href="">4</a></li>
                        <li class="page-item active" aria-current="page">
                            <span class="page-link">
                                5
                                <span class="sr-only">(current)</span>
                            </span>
                        </li>
                        <li class="page-item"><a class="page-link" href="">6</a></li>
                    </ul>
                </nav>
                <h1>請將課程上傳！</h1>
                <h4>授課內容越豐富，越能建立好口碑！</h4>
            </div>
        </div>
        
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
            <div class="row">
                <div class="col-9">
                    <form method="post" action="<%=request.getContextPath()%>/class_info/class_infoServlet"
		            name="form2" enctype="multipart/form-data">
		                <div>
							<h2 style="margin: 10px; text-align: center">上傳教課影片列表</h2>
						    <ul class="list-group" style="overflow: auto; height: 420px;">
        <c:forEach items="${chapterAndUnitKeys}" var="classVO2" varStatus="loop">
							    <div class="accordion" id="accordionExample">
          						    <div class="card">
          						      <div class="card-header" id="heading${loop.index}">
           						       <h2 class="mb-0">
           						         <button class="btn btn-block text-left" type="button" data-toggle="collapse" data-target="#collapse${loop.index}" aria-expanded="true" aria-controls="collapse${loop.index}">
           						          	 章節名稱: ${classVO2.chapter_name}
         						           </button>
        						          </h2>
       						         </div>
            <c:forEach items="${chapterAndUnit[classVO2]}" var="unitlistVO">
      						           <div id="collapse${loop.index}" class="collapse" aria-labelledby="heading${loop.index}" data-parent="#accordionExample">
       						              <div class="card-body">
                ${unitlistVO.unit_name}<input type="file" name="${unitlistVO.unit_id}" id="${unitlistVO.unit_id}" />
                						<input type="hidden" name="${unitlistVO.unit_id}" value="${unitlistVO.unit_id}" />
           						          </div>
          						       </div>
             </c:forEach>
        						    </div>
        						 </div>
        
       </c:forEach>
							</ul>
		            	
		                </div>
                    <input class="btn btn-danger" type="button" value="上一步" onclick="location.href='<%=request.getContextPath()%>/front-end/verify_list/verify_list4.jsp'">
		            <input  type="hidden" name="action" value="uploadUnit"> <input
			          class="btn btn-danger"   type="submit" value="送出審核">
	                </form>
		        </div>
		        <div class="col-3">
                    <div class="row">
                        <img  src="<%=request.getContextPath()%>/img/icon/class1.png"
                            alt="XX" style="height: 50px;"> <div style="margin-top: 18px;">課程緣起</div>
		            </div>
		            <li>簡述開課動機或老師對此堂課的目標與期許</li>
		            <div>（請老師閱讀完段落說明文字後，刪除「標題」外的預設文字，再開始進行後續的內容編寫）</div><br>
		            <div class="row">
                        <img  src="<%=request.getContextPath()%>/img/icon/class1.png"
                            alt="XX" style="height: 50px;"> <div style="margin-top: 18px;">課程核心特色</div>
		            </div>
		            <li>清楚介紹課程主題</li>
		            <li>清楚介紹課程目標與學習目的，說明學生能夠習得的技能或實際做出的作品</li>
		            <div>（請老師閱讀完段落說明文字後，刪除「標題」外的預設文字，再開始進行後續的內容編寫）</div><br>
		            <div class="row">
                        <img  src="<%=request.getContextPath()%>/img/icon/class1.png"
                            alt="XX" style="height: 50px;"> <div style="margin-top: 18px;">課程單元規劃</div>
		            </div>
		            <li>簡述課程單元架構，並描述各單元教學重點 </li>
		            <li>清楚說明教學方式（例如：投影片講課、螢幕側錄操作畫面、實拍真人示範 ...等）</li>
		            <li>清楚說明課程內容設計（例如：觀念建立、理論或心法、操作技術、範例剖析、實作練習...等）</li>
		            <li>請依照課程學員所需背景知識，適當地提供專有名詞解釋</li> 
		        <div>（請老師閱讀完段落說明文字後，刪除「標題」外的預設文字，再開始進行後續的內容編寫）</div><br>    
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

</body>

</html>