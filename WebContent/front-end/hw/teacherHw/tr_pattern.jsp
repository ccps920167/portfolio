<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.student_homework.model.Student_homeworkService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	String class_id = request.getParameter("class_id");
// 	class_id = "CLA00002";測試用
	Student_homeworkService sthwService = new Student_homeworkService();
	List<Map<String,Object>> sthwList = sthwService.worklist(class_id);
	request.getSession().setAttribute("sthwList", sthwList);
%>

<!doctype html>
<html lang="en">
<head>
    <!-- 為解決bug -->
	<link rel="shortcut icon" href="#"/>
	<!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
    <title>TOMATO - 讓你分分鐘鐘都在學習的平台</title>
    
	<style>
/* 	homework用到 */
        .icon{
        	width:30px;
        }
		.icon-sm{
        	width:10px;
        	height: 10px;
        }
        .hidden{
        	display: none;
        }
		img.card-img-top{
			height: 200px;
		}
		.drop_zone {
	        border: 1px dashed #ccc;
	        position: relative;
	    }
	            	
	    .drop_zone.enter {
	        background-color: lightgray;
	    }
	    .drop_zone.-on {
	        border: 1px dashed lightblue;
	        box-shadow: 3px 3px 5px lightblue inset, -3px -3px 5px lightblue inset;
	    }
	    .drop_zone span.text {
	        position: absolute;
	        display: inline-block;
	        left: 50%;
	        top: 50%;
	        transform: translate(-50%, -50%);
	        z-index: -1;
	        color: lightgray;
	    }
	    
	    .preview span.text {
	        position: absolute;
	        display: inline-block;
	        left: 50%;
	        top: 50%;
	        transform: translate(-50%, -50%);
	        z-index: -1;
	        color: lightgray;
	    }
	    .preview img.preview_img {
	        width: 500px;
	        height: 500px;
	    }
    </style>
</head>

<body>
    <!-- 內容 -->
    <div class="container">
        <!-- 三個button -->
    	<ul class="nav d-flex justify-content-around" role="tablist">
	        <li class="nav-item col-3" role="presentation">
	            <a class="nav-link btn class-btn" data-toggle="tab" href="#workList" role="tab" aria-controls="home" aria-selected="true">作品集</a>
	        </li>
	        <li class="nav-item col-3" role="presentation">
	            <a class="nav-link btn class-btn" data-toggle="tab" href="#homeworkList" role="tab" aria-controls="profile" aria-selected="false">作業題目</a>
	        </li>
	        <li class="nav-item col-3" role="presentation">
	            <a class="nav-link btn class-btn" data-toggle="tab" href="#makehomework" role="tab" aria-controls="contact" aria-selected="false">編輯作業</a>
	        </li>
	    </ul>
	    <hr>
<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
	    <div class="tab-content" id="myTabContent">
<!-- 作品集 -->
	        <div class="tab-pane fade active" id="workList" role="tabpanel" aria-labelledby="home-tab">
	        	<jsp:include page="/front-end/hw/teacherHw/workList.jsp" flush="true">
	        		<jsp:param value="<%= class_id %>" name="class_id"/>
	        	</jsp:include>   
	        </div>
<!-- 作業題目 -->
	        <div class="tab-pane fade" id="homeworkList" role="tabpanel" aria-labelledby="profile-tab">
	        	<jsp:include page="/front-end/hw/teacherHw/homework.jsp" flush="false">
	        		<jsp:param value="<%= class_id %>" name="class_id"/>
	        	</jsp:include>
	        </div>
<!-- 編輯作業 -->
	        <div class="tab-pane fade" id="makehomework" role="tabpanel" aria-labelledby="contact-tab">
	        	<jsp:include page="/front-end/hw/teacherHw/makeHomework.jsp" flush="false">
	        		<jsp:param value="<%= class_id %>" name="class_id"/>
	        	</jsp:include>
	        </div>
	    </div>           
    </div>
    
	<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
	<script>
	    
	    //點擊作業or新作業
		$("a.dropdown-item").on("click",function(e){
			//處理不能再次點選問題
			//讓所有作業不反白
			$("a.dropdown-item").removeClass("active");
		});

	    // ----------------------------------------------------------------------
	    
//預覽功能
	    function preview_img(file,target) {
	    	var img_node = document.createElement("img");
		    var reader = new FileReader();
		    var that = target;
		    reader.readAsDataURL(file);
		    reader.onload = function () {
		    	console.log("觸發照片");
	            img_node.src= reader.result;
	            img_node.setAttribute("class", "preview_img");
	            $(that).closest("form").find("div.drop_zone").empty();
	            $(that).closest("form").find("div.drop_zone").append(img_node);
		    }
	    }
		
//點選圖片預覽，會觸發input改變
		$("input.file").on("change",function(event){
			let target = this;
			preview_img(event.target.files[0],target);
		});
	
	    // ----------------------------------------------------------------------
		var drop_zone = $(".drop_zone");

		// drop_zone 區塊允許其它元素拖曳至此
        drop_zone.on("dragover", function (e) {
            e.preventDefault();
        });
        // 移入 drop_zone 時
        drop_zone.on("dragenter", function (e) {
            drop_zone.addClass("enter"); // 加上 class
            drop_zone.addClass("-on"); // 加上 class
        });
        // 移出 drop_zone 時
        drop_zone.on("dragleave", function (e) {
            drop_zone.removeClass("enter"); // 移除 class
            drop_zone.removeClass("-on"); // 移除 class
        });
        // 在 drop_zone 放開滑鼠
        drop_zone.on("drop", function (e) {
            drop_zone.removeClass("enter"); // 移除 class
            drop_zone.removeClass("-on"); // 移除 class
            
            let target = this;
            let form = this.closest("form");
            let input_target = form.getElementsByClassName("file");
            let input = input_target[0];
            e.preventDefault();
            input.files = e.originalEvent.dataTransfer.files;
	        preview_img(e.originalEvent.dataTransfer.files[0],target);
        });

        // ----------------------------------------------------------------------
        

		//點擊單元
	    $("button.unit_btn").on("click",function(e){
			var this_plus_btn = $(this).closest("div.card-header").find("button.plus_btn");
			//隱藏所有plus_icon
			$("button.plus_btn").addClass("hidden");
			//顯示該層的plus_icon
			this_plus_btn.removeClass("hidden");
			//所有作業不反白，處理不能再次點選問題
			$("a.dropdown-item").removeClass("active");
		});	
		//點擊作業
		$("a.trhw").on("click",function(e){
			//隱藏所有new_hw
			$("a.new_hw").addClass("hidden");
		});
		//點擊加號
		$("button.plus_btn").on("click",function(e){
			//該層的新作業
			var this_new_hw = $(this).closest("div.unit_col").find("a.new_hw");
			//隱藏所有new_hw
			$("a.new_hw").addClass("hidden");	
			//顯示該層新作業
			this_new_hw.removeClass("hidden");
			//該層新作業active
			this_new_hw.addClass("active");

			//隱藏所有plus_icon
			$("button.plus_btn").addClass("hidden");
			//顯示該層的plus_icon
			$(this).removeClass("hidden");
			//處理不能再次點選問題
			$("a.dropdown-item").removeClass("active");
		});
      
	</script>
</body>

</html>