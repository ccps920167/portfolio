<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.teacher_homework.model.*"%>
<%@ page import="com.student_homework.model.*"%>
<%@ page import="com.class_chapter.model.*"%>
<%@ page import="com.class_unit.model.*"%>

<%
	String class_id = request.getParameter("class_id");
	
	Class_chapterService chapterService = new Class_chapterService();
	List<Class_chapterVO> chapterList = chapterService.getClassAll(class_id);
	
	Class_unitService unitService = new Class_unitService();
	List<Class_unitVO> unitList = unitService.getAll();
	
	Teacher_homeworkService trhwService = new Teacher_homeworkService();
	List<Map> trList = trhwService.selectByClassId(class_id);
	pageContext.setAttribute("trList", trList);
%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
    <!-- 為解決bug -->
	<link rel="shortcut icon" href="#"/>
    <!-- video.js CSS -->
    <link href="<%=request.getContextPath()%>/vendors/video-js/css/video-js.css" rel="stylesheet" />
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">

	<title>Insert title here</title>
</head>
<body>
	<!-- 作業題目 -->
    <div id="content-class" class="mx-auto">
        <!-- title -->
        <div class="border-bottom border-muted py-3 mb-2">
            <div class="d-flex align-items-center col-3">
                <img class="mx-2 icon" src="<%=request.getContextPath()%>/img/icon/homework.png" alt="作業小icon">
                <span class="h2 mr-0 my-0 text-muted" style=" white-space:nowrap">作業練習</span>
            </div>
        </div>
        <div class="row">
			<div class="col-3 pl-0">
				<div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">		      
					<c:if test="${ empty trList}">
						<img src="<%=request.getContextPath() %>/img/NoResult/NoResult.jpg">
					</c:if>
					<c:if test="${ not empty trList}">
						<c:forEach var="map" items="${trList}">
							<a class="nav-link btn btn-light" data-toggle="pill" href="#homework_${ map.teacherhw_id }" role="tab" aria-controls="v-pills-profile" aria-selected="false">
				      			${ map.get("chapter_name") } > ${ map.get("unit_name") } > ${ map.get("hw_name") }
							</a>
				      	</c:forEach>
					</c:if>
	    		</div>
			</div>
	        
			<!-- 作業內容 -->
			<div class="col-8 mx-auto">
			    <div class="tab-content">
			        <hr>
			        <c:forEach var="trhw" items="${ trList }">
			        	<div class='tab-pane' id="homework_${ trhw.teacherhw_id }">
				        	<div class='p-2 border border-muted'>
					        	<div class='border-bottom border-dark mx-2 my-5'>
						        	<h2 id='hw_name' class='mr-0 my-3 text-center text-muted'>
						        		${ trhw.hw_name }
						        	</h2>
					        	</div>
					        	<div class='border border-muted mx-auto my-2 p-2'>
					        		<p>${ trhw.hw_content }</p>
					        	</div>
					        	<div class='mx-auto my-2 py-2'>
						        	<c:if test="${ trhw.file_data != null }">
						        		<img class='container-fluid' src="<%= request.getContextPath()%>/HwServlet?action=display_pic&teacherhw_id=${ trhw.teacherhw_id }" alt="作業附圖">
						        	</c:if>
					        	</div>
				        	</div>
			        	</div>
			        </c:forEach>
	       		 </div>
			</div>
        </div>
    </div>

</body>
</html>