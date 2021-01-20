<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.student_homework.model.Student_homeworkService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	String class_id = request.getParameter("class_id");
// 	class_id = "CLA00002";���ե�
	Student_homeworkService sthwService = new Student_homeworkService();
	List<Map<String,Object>> sthwList = sthwService.worklist(class_id);
	request.getSession().setAttribute("sthwList", sthwList);
%>

<!doctype html>
<html lang="en">
<head>
    <!-- ���ѨMbug -->
	<link rel="shortcut icon" href="#"/>
	<!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
    <title>TOMATO - ���A�����������b�ǲߪ����x</title>
    
	<style>
/* 	homework�Ψ� */
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
    <!-- ���e -->
    <div class="container">
        <!-- �T��button -->
    	<ul class="nav d-flex justify-content-around" role="tablist">
	        <li class="nav-item col-3" role="presentation">
	            <a class="nav-link btn class-btn" data-toggle="tab" href="#workList" role="tab" aria-controls="home" aria-selected="true">�@�~��</a>
	        </li>
	        <li class="nav-item col-3" role="presentation">
	            <a class="nav-link btn class-btn" data-toggle="tab" href="#homeworkList" role="tab" aria-controls="profile" aria-selected="false">�@�~�D��</a>
	        </li>
	        <li class="nav-item col-3" role="presentation">
	            <a class="nav-link btn class-btn" data-toggle="tab" href="#makehomework" role="tab" aria-controls="contact" aria-selected="false">�s��@�~</a>
	        </li>
	    </ul>
	    <hr>
<%-- ���~��C --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">�Эץ��H�U���~:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
	    <div class="tab-content" id="myTabContent">
<!-- �@�~�� -->
	        <div class="tab-pane fade active" id="workList" role="tabpanel" aria-labelledby="home-tab">
	        	<jsp:include page="/front-end/hw/teacherHw/workList.jsp" flush="true">
	        		<jsp:param value="<%= class_id %>" name="class_id"/>
	        	</jsp:include>   
	        </div>
<!-- �@�~�D�� -->
	        <div class="tab-pane fade" id="homeworkList" role="tabpanel" aria-labelledby="profile-tab">
	        	<jsp:include page="/front-end/hw/teacherHw/homework.jsp" flush="false">
	        		<jsp:param value="<%= class_id %>" name="class_id"/>
	        	</jsp:include>
	        </div>
<!-- �s��@�~ -->
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
	    
	    //�I���@�~or�s�@�~
		$("a.dropdown-item").on("click",function(e){
			//�B�z����A���I����D
			//���Ҧ��@�~���ϥ�
			$("a.dropdown-item").removeClass("active");
		});

	    // ----------------------------------------------------------------------
	    
//�w���\��
	    function preview_img(file,target) {
	    	var img_node = document.createElement("img");
		    var reader = new FileReader();
		    var that = target;
		    reader.readAsDataURL(file);
		    reader.onload = function () {
		    	console.log("Ĳ�o�Ӥ�");
	            img_node.src= reader.result;
	            img_node.setAttribute("class", "preview_img");
	            $(that).closest("form").find("div.drop_zone").empty();
	            $(that).closest("form").find("div.drop_zone").append(img_node);
		    }
	    }
		
//�I��Ϥ��w���A�|Ĳ�oinput����
		$("input.file").on("change",function(event){
			let target = this;
			preview_img(event.target.files[0],target);
		});
	
	    // ----------------------------------------------------------------------
		var drop_zone = $(".drop_zone");

		// drop_zone �϶����\�䥦�����즲�ܦ�
        drop_zone.on("dragover", function (e) {
            e.preventDefault();
        });
        // ���J drop_zone ��
        drop_zone.on("dragenter", function (e) {
            drop_zone.addClass("enter"); // �[�W class
            drop_zone.addClass("-on"); // �[�W class
        });
        // ���X drop_zone ��
        drop_zone.on("dragleave", function (e) {
            drop_zone.removeClass("enter"); // ���� class
            drop_zone.removeClass("-on"); // ���� class
        });
        // �b drop_zone ��}�ƹ�
        drop_zone.on("drop", function (e) {
            drop_zone.removeClass("enter"); // ���� class
            drop_zone.removeClass("-on"); // ���� class
            
            let target = this;
            let form = this.closest("form");
            let input_target = form.getElementsByClassName("file");
            let input = input_target[0];
            e.preventDefault();
            input.files = e.originalEvent.dataTransfer.files;
	        preview_img(e.originalEvent.dataTransfer.files[0],target);
        });

        // ----------------------------------------------------------------------
        

		//�I���椸
	    $("button.unit_btn").on("click",function(e){
			var this_plus_btn = $(this).closest("div.card-header").find("button.plus_btn");
			//���éҦ�plus_icon
			$("button.plus_btn").addClass("hidden");
			//��ܸӼh��plus_icon
			this_plus_btn.removeClass("hidden");
			//�Ҧ��@�~���ϥաA�B�z����A���I����D
			$("a.dropdown-item").removeClass("active");
		});	
		//�I���@�~
		$("a.trhw").on("click",function(e){
			//���éҦ�new_hw
			$("a.new_hw").addClass("hidden");
		});
		//�I���[��
		$("button.plus_btn").on("click",function(e){
			//�Ӽh���s�@�~
			var this_new_hw = $(this).closest("div.unit_col").find("a.new_hw");
			//���éҦ�new_hw
			$("a.new_hw").addClass("hidden");	
			//��ܸӼh�s�@�~
			this_new_hw.removeClass("hidden");
			//�Ӽh�s�@�~active
			this_new_hw.addClass("active");

			//���éҦ�plus_icon
			$("button.plus_btn").addClass("hidden");
			//��ܸӼh��plus_icon
			$(this).removeClass("hidden");
			//�B�z����A���I����D
			$("a.dropdown-item").removeClass("active");
		});
      
	</script>
</body>

</html>