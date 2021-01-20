<%@page import="com.member_info.model.Member_infoVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%
	String class_id = request.getParameter("class_id");
// 	class_id = "CLA00002";
	pageContext.setAttribute("class_id", class_id);

	Member_infoVO memberVO = (Member_infoVO)request.getSession().getAttribute("member_infoVO");
	String member_id = memberVO.getMember_id();
// 	String member_id = "MEM00004";
	pageContext.setAttribute("member_id", member_id);
%>
<!DOCTYPE html>
<html>
<head>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
	<title>student_pattern</title>
	<style>
		.hidden{
			display: none!important;
		}
		.icon{
		width:30px;
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
	        width: 400px;
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
	    <div class="tab-content">
<!-- 作品集 -->
	        <div class="tab-pane fade" id="workList" role="tabpanel" aria-labelledby="home-tab">
	        	<jsp:include page="/front-end/hw/studentHw/workList.jsp" flush="true">
	        		<jsp:param value="${ class_id }" name="class_id"/>
	        		<jsp:param value="${ member_id }" name="member_id"/>
	        	</jsp:include>   
	        </div>
<!-- 作業題目 -->
	        <div class="tab-pane fade" id="homeworkList" role="tabpanel" aria-labelledby="profile-tab">
	        	<jsp:include page="/front-end/hw/studentHw/doHomework.jsp" flush="false">
	        		<jsp:param value="${ class_id }" name="class_id"/>
	        		<jsp:param value="${ member_id }" name="member_id"/>
	        	</jsp:include>
	        </div>
	    </div>
	</div>      
	    <!-- JavaScript -->
	<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
    <script>
// ----------------------------------------------------------------------  
	    
	    //點擊作業or新作業
		$("a.dropdown-item").on("click",function(e){
			//處理不能再次點選問題
			//讓所有作業不反白
			$("a.dropdown-item").removeClass("active");
		});
		
// ======================dohomework=====================================		
		
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
	            $(that).closest("div.dohomework").find("div.drop_zone").empty();
	            $(that).closest("div.dohomework").find("div.drop_zone").append(img_node);
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
	        e.preventDefault();
            
            let form = this.closest("form");
            let input_target = form.getElementsByClassName("file");
            let input = input_target[0];
            input.files = e.originalEvent.dataTransfer.files;
	        preview_img(e.originalEvent.dataTransfer.files[0],target);
	    });
    
// ----------------------------------------------------------------------
		
		$("a.chooseQ").on("click",function(e){
    		let chooseQ = $(this);
    		let chooseA = $(this).closest("div.choose_father").find("a.chooseA");
    		let trHomework = $(this).closest("form").find("div.trHomework");
    		let stHomework = $(this).closest("form").find("div.stHomework");
    		chooseQ.addClass("hidden");
    		chooseA.removeClass("hidden");
    		stHomework.addClass("hidden");
    		trHomework.removeClass("hidden");
    		$("div.stHomework").removeClass("active");
    	});
    	$("a.chooseA").on("click",function(e){
    		let chooseA = $(this);
    		let chooseQ = $(this).closest("div.choose_father").find("a.chooseQ");
    		let trHomework = $(this).closest("form").find("div.trHomework");
    		let stHomework = $(this).closest("form").find("div.stHomework");    		
    		chooseA.addClass("hidden");
    		chooseQ.removeClass("hidden");
    		trHomework.addClass("hidden");
    		stHomework.removeClass("hidden");
    		$("div.trHomework").removeClass("active");
    	});
    		//同層其他按鈕都拿掉active
    	$("a.drop-choose").on("click",function(e){
    		let id = $(this).find("span").attr("name");
    		//所有作業
    		let hw_window = $("#"+id).closest("div.tab-content").find("div.tab-pane");
    		$("div.tab-content").removeClass("active");
    		$("#"+id).addClass("active");
    		
    		$("a.drop-choose").removeClass("active");
    		$(this).addClass("active");
    	});
		
// ========================================================================	
	
// ============================worklist====================================	
		
		$("a.allWork").on("click",function(e){
			let allWork = $(this);
			let myWork = $(this).closest("div.work_package").find("a.myWork");
			myWork.removeClass("hidden active");
			allWork.addClass("hidden active");
			
			let allPage = $(this).closest("div.work_package").find("div.allstWork");
			let myPage = $(this).closest("div.work_package").find("div.mystWork");
			myPage.removeClass("active show");
			myPage.addClass("hidden");
			allPage.removeClass("hidden");
			allPage.addClass("active show");
		});
		$("a.myWork").on("click",function(e){
			let myWork = $(this);
			let allWork = $(this).closest("div.work_package").find("a.allWork");
			myWork.addClass("hidden active");
			allWork.removeClass("hidden active");
			
			let myPage = $(this).closest("div.work_package").find("div.mystWork");
			let allPage = $(this).closest("div.work_package").find("div.allstWork");
			allPage.removeClass("active show");
			allPage.addClass("hidden");
			myPage.removeClass("hidden");
			myPage.addClass("active show");
		});
		
// 		$("a.drop-work").on("click",function(e){
// 			let drop_item = $("a.dropdown-item").closest("div.dropdown-menu").find("a.dropdown-item");
// // 			同層其他按鈕都拿掉active
// 			drop_item.removeClass("active");
// 			$(this).addClass("active");
			
// 			//所有頁面隱藏
// 			let work_package = $("div.work_package");
// 			work_package.removeClass("active show");
// 			work_package.addClass("hidden");
// 			//對應id頁面出現
// 			let id = $(this).find("input").attr("name");
// 			let page = $("#"+id);
// 			page.removeClass("hidden");
// 			page.addClass("active show");
// 		});
		
// ========================================================================
		
		
    </script>
</body>
</html>