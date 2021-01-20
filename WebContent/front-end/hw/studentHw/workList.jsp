<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.stream.Collectors"%>
<%@ page import="com.teacher_homework.model.*"%>
<%@ page import="com.student_homework.model.*"%>
<%@ page import="com.class_chapter.model.*"%>
<%@ page import="com.class_unit.model.*"%>
<%@ page import="com.member_info.model.*"%> 

<%	
	String class_id = request.getParameter("class_id");
	pageContext.setAttribute("class_id", class_id);

	String member_id = request.getParameter("member_id");
	pageContext.setAttribute("member_id", member_id);
	
	Teacher_homeworkService trhwService = new Teacher_homeworkService();
	List<Map> trList = trhwService.selectByClassId(class_id);
	request.getSession().setAttribute("trList", trList);
	
	//這個課程所有作品
	Student_homeworkService sthwService = new Student_homeworkService();
	List<Map<String,Object>> sthwList = sthwService.worklist(class_id);
	pageContext.setAttribute("sthwList", sthwList);
	request.getSession().setAttribute("sthwList", sthwList);
	//這個課程我所有的作品
	List<Map<String,Object>> my_sthwList = 
			sthwList
			.stream()
			.filter(map -> map.get("member_id").equals(member_id))
			.collect(Collectors.toList());
	pageContext.setAttribute("my_sthwList", my_sthwList);
	
	//解決include編碼錯誤問題
	request.setCharacterEncoding("UTF-8") ;
%>

<!DOCTYPE html>
<html>
<head>
    <!-- 為解決bug -->
	<link rel="shortcut icon" href="#"/>
	<!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
    
    <meta http-equiv="Content-Type" content="text/html; utf-8">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
	<title>student_workList</title>
</head>
<body>
<%-- ${ sthwList }有 --%>
<%-- ${ my_sthwList }有 --%>
<%-- ${ trList }有  --%>
<!-- 作業單元選擇，下拉式選單 & 小icon -->
	<div class="container row mx-auto m-3 p-0">
<!-- 下拉選單 -->
		<div class="col-3 pl-0">
			<div class="nav flex-column nav-pills" role="tablist" aria-orientation="vertical">		      
				<c:if test="${ empty trList}">
					<img src="<%=request.getContextPath() %>/img/NoResult/NoResult.jpg">
				</c:if>
				<c:if test="${ not empty trList}">
					<a class="nav-link btn btn-light" data-toggle="pill" href="#works" role="tab" aria-controls="v-pills-profile" aria-selected="false">
		      			所有作品
					</a>
					<c:forEach var="map" items="${trList}">
					
						<a class="nav-link btn btn-light" data-toggle="pill" href="#trwork_${ map.teacherhw_id }" role="tab" aria-controls="v-pills-profile" aria-selected="false">
						<input name="trwork_${ map.teacherhw_id }" type="hidden"/>
			      			${ map.get("chapter_name") } > ${ map.get("unit_name") } > ${ map.get("hw_name") }
						</a>
			      	</c:forEach>
				</c:if>
    		</div>
		</div>
		<div class="tab-content col-8 border border-muted py-2 mx-auto">
		        <div id="works" class="work_package tab-content tab-pane w-100 mb-4 active show">
	<!--	選擇顯示自己作品或所有人作品	-->
			        <div class="d-flex ml-auto mb-4">
			            <a class="allWork btn btn-secondary ml-auto my-auto hidden" role="tab" 
			            	data-toggle="tab" href="#all_works">
			                	Goto所有作品
			            </a>
			            <a class="myWork btn btn-secondary ml-auto my-auto" role="tab" 
			            	data-toggle="tab" href="#my_works">
			                	Goto我的作品
			            </a>
			        </div>
	<!--	這個課程所有作業的作品	-->
		            <div id="all_works" class="allstWork tab-pane p-0 active">
		            	<div class="row d-flex justify-content-around">
		                    <c:forEach var="sthwMap" items="${ sthwList }">
		                    	<c:if test="${ sthwMap.member_homework!=0 || sthwMap.member_id==member_id }">
		<%-- 	                    ${ sthwMap.st_hw_name } --%>
			        				<jsp:include page='/front-end/hw/common/work_card.jsp' flush='true'>
										<jsp:param value="${sthwMap.studenthw_id}" name="studenthw_id"/>
										<jsp:param value="${sthwMap.st_hw_updatetime}" name="st_hw_updatetime"/>
										<jsp:param value="${sthwMap.st_hw_name}" name="hw_name"/>
										<jsp:param value="${sthwMap.member_id}" name="member_id"/>
										<jsp:param value="${sthwMap.member_name}" name="member_name"/>
									</jsp:include>	
		                    	</c:if>
		        			</c:forEach>
	        			</div>
	                </div>
	<!--	所有作業的我的作品	-->				
	                <div id="my_works" class="mystWork tab-pane p-0">
	                	<div class="row d-flex justify-content-around">
	                    <c:forEach var="my_sthwMap" items="${ my_sthwList }">		
	        				<jsp:include page='/front-end/hw/common/work_card.jsp' flush='true'>
								<jsp:param value="${my_sthwMap.studenthw_id}" name="studenthw_id"/>
								<jsp:param value="${my_sthwMap.st_hw_updatetime}" name="st_hw_updatetime"/>
								<jsp:param value="${my_sthwMap.st_hw_name}" name="hw_name"/>
								<jsp:param value="${my_sthwMap.member_id}" name="member_id"/>
								<jsp:param value="${my_sthwMap.member_name}" name="member_name"/>
							</jsp:include>	
	        			</c:forEach>
	        			</div>
		            </div>
		        </div>
	<!-- --------------------------------------- -->
		        <c:forEach var="trmap" items="${ trList }">
		        	<div id="trwork_${ trmap.teacherhw_id }"  role="tabpanel" 
		        	class="work_package tab-content tab-pane w-100 mb-4">
	<!--	選擇顯示自己作品或所有人作品	-->
						<div class="d-flex ml-auto mb-4">
				        	<a class="allWork btn btn-secondary ml-auto hidden" role="tab" 
				        		data-toggle="tab" href="#all_trwork_${ trmap.teacherhw_id }">
				        		Goto所有作品
				        	</a>
				        	<a class="myWork btn btn-secondary ml-auto" role="tab" 
				        		data-toggle="tab" href="#my_trwork_${ trmap.teacherhw_id }">
				        		Goto我的作品
				        	</a>
			        	</div>
	<!--	各個作業裡所有人的作品	-->
						<div id="all_trwork_${ trmap.teacherhw_id }" role="tabpanel"  
							class="allstWork tab-pane p-0 active">
							<div class="row d-flex justify-content-around">
								<c:forEach var="sthwMap" items="${ sthwList }">
									<c:if test="${ sthwMap.teacherhw_id==trmap.teacherhw_id && (sthwMap.member_homework!=0 || sthwMap.member_id==member_id)}">
										<jsp:include page='/front-end/hw/common/work_card.jsp' flush='true'>
											<jsp:param value="${ sthwMap.studenthw_id }" name="studenthw_id"/>
											<jsp:param value="${ sthwMap.st_hw_updatetime }" name="st_hw_updatetime"/>
											<jsp:param value="${ sthwMap.st_hw_name }" name="hw_name"/>
											<jsp:param value="${ sthwMap.member_id }" name="member_id"/>
											<jsp:param value="${ sthwMap.member_name }" name="member_name"/>
										</jsp:include>										
									</c:if>
								</c:forEach>
							</div>
						</div>
	<!--	各個作業裡我的作品	-->
						<div id="my_trwork_${ trmap.teacherhw_id }" role="tabpanel"
							class="mystWork tab-pane p-0">
							<div class="row d-flex justify-content-around">
								<c:forEach var="my_sthwMap" items="${ my_sthwList }">
									<c:if test="${ my_sthwMap.teacherhw_id==trmap.teacherhw_id}">
										<jsp:include page='/front-end/hw/common/work_card.jsp' flush='true'>
											<jsp:param value="${ my_sthwMap.studenthw_id }" name="studenthw_id"/>
											<jsp:param value="${ my_sthwMap.st_hw_updatetime }" name="st_hw_updatetime"/>
											<jsp:param value="${ my_sthwMap.st_hw_name }" name="hw_name"/>
											<jsp:param value="${ my_sthwMap.member_id }" name="member_id"/>
											<jsp:param value="${ my_sthwMap.member_name }" name="member_name"/>
										</jsp:include>										
									</c:if>
								</c:forEach>
							</div>
				        </div>
	<!-- ================================================================ -->
					</div>
	       		</c:forEach>
		</div>
	</div>
<!--		-->            
	<!-- JavaScript -->
    <script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
    
</body>
</html>