<%@page import="java.sql.Time"%>
<%@page import="java.sql.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member_info.model.*"%>
<%@ page import="com.class_info.model.*"%>
<%@ page import="com.order_list.model.*"%>
<%@ page import="com.student_homework.model.*"%>
<%@ page import="com.teacher_homework.model.*"%>
<%@ page import="com.member_interest.model.*"%>
<html lang="en">

<head>

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
<!-- css -->
<link href="<%=request.getContextPath()%>/css/header.css"
	rel="stylesheet" />



<style>

/* 內部框 */
#content {
	margin-top: 10px;
	margin-bottom: 50px;
	border: 1px rgb(204, 204, 204) solid;
}

/* The switch - the box around the slider */
.switch {
	position: relative;
	display: inline-block;
	width: 60px;
	height: 34px;
}

/* Hide default HTML checkbox */
.switch input {
	opacity: 0;
	width: 0;
	height: 0;
}

/* The slider */
.slider {
	position: absolute;
	cursor: pointer;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background-color: #ccc;
	-webkit-transition: .4s;
	transition: .4s;
}

.slider:before {
	position: absolute;
	content: "";
	height: 26px;
	width: 26px;
	left: 4px;
	bottom: 4px;
	background-color: white;
	-webkit-transition: .4s;
	transition: .4s;
}

input:checked+.slider {
	background-color: #2196F3;
}

input:focus+.slider {
	box-shadow: 0 0 1px #2196F3;
}

input:checked+.slider:before {
	-webkit-transform: translateX(26px);
	-ms-transform: translateX(26px);
	transform: translateX(26px);
}

/* Rounded sliders */
.slider.round {
	border-radius: 34px;
}

.slider.round:before {
	border-radius: 50%;
}

label {
	vertical-align: middle;
}
.contextcard{
	margin: 8px;
}
</style>



<title>TOMATO - 讓你分分鐘鐘都在學習的平台</title>

</head>
<body>

	<%@ include file="header.jsp"%>

	<%
		String member_id = member_infoVO.getMember_id();
		Class_infoService Srv = new Class_infoService();
		List<Class_infoVO> list = Srv.getTeachAll(member_id);
		pageContext.setAttribute("list", list);
	%>
	
	<%
		Order_listService osrv = new Order_listService();
		List<Order_listVO> list2 = osrv.getMemberClass(member_id);
		pageContext.setAttribute("list2", list2);
	%>
	
	<%
		Teacher_homeworkService sthwsrv = new Teacher_homeworkService();
		List<StuTr_homeworkVO> list3 = sthwsrv.getHwContent(member_id);
		pageContext.setAttribute("list3", list3);
	%>
	
	 <%
  		Member_interestService member_interSvc = new Member_interestService();
 		List<Member_interestVO> list4 = member_interSvc.findBymember_id(member_id);
		pageContext.setAttribute("list4", list4);
 %>
	

	<!-- container -->

	<div class="container">
		<!-- 標題 -->
		<div class="jumbotron jumbotron-fluid shadow-sm rounded">
			<div class="container">
				<h1>個人檔案</h1>
				<p></p>
			</div>
		</div>
		<!-- 內容 -->
		<div id="content">
			<div class="row">
				<div class="col-3">
					<div class="card">
						<img
							src="<%=request.getContextPath()%>/member_info/Member_info_frontServlet?action=member_pic&member_id=${member_infoVO.member_id}"
							class="card-img-top" alt="...">
						<div class="card-body">
							<p style="text-align: center;"><%=member_infoVO.getMember_name()%></p>
						</div>
					</div>
					<div class="card border-dark mb-3" style="max-width: 18rem;">
						<div class="card-header">已參加課程</div>
						<div class="card-body text-dark">
							<h1 class="card-title" id="learnnum"><%=list2.size()%></h1>
						</div>
					</div>
					<div class="card border-dark mb-3" style="max-width: 18rem;">
						<div class="card-header">已開設課程</div>
						<div class="card-body text-dark">
							<h1 class="card-title" id="teachnum"><%=list.size()%></h1>
						</div>
					</div>
					<% 
					String about = member_infoVO.getMember_about();
					if(about == null){
						about ="無資料";
					}
					%>
					<div class="card border-dark mb-3" style="max-width: 18rem;">
						<div class="card-header">關於我</div>
						<div class="card-body text-dark">
							<h9 class="card-title"><%=about%></h9>
						</div>
					</div>
					<jsp:useBean id="sub_classSvc" scope="page" class="com.sub_class.model.Sub_classService" />
					<div class="card border-dark mb-3" style="max-width: 18rem;">
						<div class="card-header">我的興趣</div>
						<div class="card-body text-dark">
						<c:if test="${empty list4}">
							<h9 class="card-title">無資料</h9>
						</c:if>
						<c:forEach var="interestVO" items="${list4}">
							<h9 class="card-title">${sub_classSvc.getOneMain_class(interestVO.subclass_id).subClass_name}</h9>
						</c:forEach>	
							
						</div>
					</div>
					<% 
					String good_for = member_infoVO.getMember_good_for();
					if(good_for == null){
						good_for ="無資料";
					}
					%>
					<div class="card border-dark mb-3" style="max-width: 18rem;">
						<div class="card-header">我的專長</div>
						<div class="card-body text-dark">
							<h9 class="card-title"><%=good_for%></h9>
						</div>
					</div>

				</div>
				<div class="col-7">
					<div class="contextcard" id="tclassdiv">

						<label>開設課程</label> <label class="switch"> <input
							type="checkbox" id="tclass"> <span class="slider round"></span>
						</label>



						<div class=" row">
							<c:forEach var="class_infoVO" items="${list}">
								<div class="card col-6 classPage">
									<img class="card-img-top"
										src="<%=request.getContextPath()%>/Class_info/Class_Introduction?action=class_pic_sm&class_id=${class_infoVO.class_id}"
										alt="Card image cap">
									<div class="card-body">
										<a href="<%=request.getContextPath()%>/Class_info/Class_learnServlet?action=class_Introduction&class_id=${class_infoVO.class_id}"><h5 class="card-title">${class_infoVO.class_name}</h5></a>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>



					<div class="contextcard" id="lclassdiv">
						<label>參加課程</label> <label class="switch"> <input
							type="checkbox" id="lclass"> <span class="slider round"></span>
						</label>
				
						<jsp:useBean id="classSrv" scope="page"
							class="com.class_info.model.Class_infoService" />



						<div class=" row">
							<c:forEach var="order_listVO" items="${list2}">

								<div class="card col-6 classPage">
									<img class="card-img-top"
										src="<%=request.getContextPath()%>/Class_info/Class_Introduction?action=class_pic_sm&class_id=${order_listVO.class_id}"
										alt="Card image cap">
									<div class="card-body">
										<a href="<%=request.getContextPath()%>/Class_info/Class_learnServlet?action=class_Introduction&class_id=${order_listVO.class_id}"><h5 class="card-title">${classSrv.getOneClass_info(order_listVO.class_id).class_name}</h5></a>
									</div>

								</div>

							</c:forEach>
						</div>
					</div>

					<div class="contextcard"  id="stuhwdiv">
						<label>課程作品</label>


						
						
						<label class="switch"> <input type="checkbox" id="stuhw">
							<span id="homework" class="slider round"></span>
						</label>
						<div class=" row">
							<c:forEach var="stutr_homeworkVO" items="${list3}" >

								<div class="card col-6">
									<img class="card-img-top"
										src="<%=request.getContextPath()%>/studenthwServlet?action=display_pic&studenthw_id=${stutr_homeworkVO.studenthw_id}"
										alt="Card image cap">
									<div class="card-body">
										<h5 class="card-title">${stutr_homeworkVO.hw_name}</h5>
										<p class="card-text">${stutr_homeworkVO.hw_content}</p>
									</div>

								</div>

							</c:forEach>
							
						</div>
					</div>

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
		
			
		

		
			if (<%=member_infoVO.getTeachclass_on()%>==1) {
				$("#tclass").attr("checked", true);
			}
			if (<%=member_infoVO.getLearnclass_on()%>==1) {
				$("#lclass").attr("checked", true);
			}
			if (<%=member_infoVO.getMember_homework()%>==1) {
				$("#stuhw").attr("checked", true);
			}
			
			//沒有的就不顯示，側邊沒參加文字敘述
			if(<%=list.size()==0%>){
				$("#tclassbtn").hide();
				$("#teachnum").text("未開設課程")
			}
			if(<%=list2.size()==0%>){
				$("#lclassdiv").hide();	
				$("#learnnum").text("未參加課程")
			}
			if(<%=list3.size()==0%>){
				$("stuhwdiv").hide()
			}
			
			
			$("#tclass, #lclass, #stuhw").change(function() {
				let teachstatus = 0;
				let learnstatus = 0;
				let memberHW = 0;
			    
				console.log("using");
			   
			    if ($("#tclass").prop("checked")) {
			    	teachstatus = 1;
			    	console.log("1")
			    }else{
			    	console.log("0")
			    	teachstatus = 0;
			    }
			    
			    if ($("#lclass").prop("checked")) {
			        learnstatus = 1;
			    	console.log("1")
			    }else{
			    	console.log("0")
			    	learnstatus = 0;
			    }
			     
			    if ($("#stuhw").prop("checked")) {
			    	console.log("1")
			    	memberHW = 1;
			    }else{
			      	memberHW = 0;
			    	console.log("0")
			    }
			    
				    var data= {
				        
				        "member_id": "<%=member_infoVO.getMember_id()%>",
				        "teachclass_on":teachstatus,
				        "learnclass_on":learnstatus,
				        "member_homework":memberHW
				      };
			
				    console.log('here');
				    $.ajax({
					    url: "<%=request.getContextPath()%>/member_info/Member_infoServlet?action=updatestatus",
		                type: "POST",                  // GET | POST | PUT | DELETE | PATCH
		                data: data,               // 傳送資料到指定的 url
		                dataType:"json"
				    })
				    console.log('there');
				    
					console.log(teachstatus);
				    console.log(learnstatus);
				    console.log(memberHW);
			    
			  })
			
			
		</script>
</body>
</html>





