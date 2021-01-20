<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.teacher_homework.model.*"%>
<%@ page import="com.student_homework.model.*"%>
<%@ page import="com.class_chapter.model.*"%>
<%@ page import="com.class_unit.model.*"%>
<%@ page import="com.member_info.model.*"%> 

<%	
	//�ѨMinclude�s�X���~���D
	request.setCharacterEncoding("UTF-8") ;

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	String class_id = request.getParameter("class_id");
	
	Teacher_homeworkService trhwService = new Teacher_homeworkService();
	List<Map> trList = trhwService.selectByClassId(class_id);
	pageContext.setAttribute("trList", trList);
	
	List<Map<String,Object>> sthwList = (List<Map<String,Object>>)request.getSession().getAttribute("sthwList");//pattern�s�i�h��
	pageContext.setAttribute("sthwList", sthwList);
%>

<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
	<!-- ���ѨMbug -->
	<link rel="shortcut icon" href="#"/>
    <!-- video.js CSS -->
    <link href="<%=request.getContextPath()%>/vendors/video-js/css/video-js.css" rel="stylesheet" />
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">

	<title>teacher_workList</title>
</head>
<body>
	<!-- �@�~�椸��ܡA�U�Ԧ���� & �picon -->
    <div class="row">
		<!-- �U�Կ�� -->
		<div class="col-3 pl-0">
			<div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">		      
				<c:if test="${ empty trList}">
					<img src="<%=request.getContextPath() %>/img/NoResult/NoResult.jpg">
				</c:if>
				<c:if test="${ not empty trList}">
					<c:forEach var="map" items="${trList}">
						<a class="nav-link btn btn-light" data-toggle="pill" href="#work_${ map.teacherhw_id }" role="tab" aria-controls="v-pills-profile" aria-selected="false">
			      			${ map.get("chapter_name") } > ${ map.get("unit_name") } > ${ map.get("hw_name") }
						</a>
			      	</c:forEach>
				</c:if>
    		</div>
		</div>
		<div class="col-8 p-1 mx-2 mt-2">
	    <!-- �@�~�d�� -->
			<div class="tab-content">
	<!--	�o�ӽҵ{�Ҧ��@�~���@�~	-->
				<div class="tab-pane" id="allworks">
	        		<div class="row m-0 justify-content-around">
	        			<c:forEach var="sthw" items="${ sthwList }">	        			
	        				<jsp:include page='/front-end/hw/common/work_card.jsp' flush='true'>
								<jsp:param value="${sthw.studenthw_id}" name="studenthw_id"/>
								<jsp:param value="${sthw.st_hw_updatetime}" name="st_hw_updatetime"/>
								<jsp:param value="${sthw.tr_hw_name}" name="hw_name"/>
								<jsp:param value="${sthw.member_id}" name="member_id"/>
								<jsp:param value="${sthw.member_name}" name="member_name"/>
							</jsp:include>	
	        			</c:forEach>
					</div>
				</div>
	<!--	�U�ӧ@�~���@�~	-->
				<c:forEach var="trmap" items="<%=trList %>">
					<div class="tab-pane" id="work_${ trmap.teacherhw_id }">
						<div class='row m-0 d-flex justify-content-around'>
							<c:forEach var="sthw" items="${ sthwList }">
								<c:if test="${ sthw.teacherhw_id==trmap.teacherhw_id }">								
									<jsp:include page='/front-end/hw/common/work_card.jsp' flush='true'>
										<jsp:param value="${ sthw.studenthw_id }" name="studenthw_id"/>
										<jsp:param value="${ sthw.st_hw_updatetime }" name="st_hw_updatetime"/>
										<jsp:param value="${ sthw.tr_hw_name }" name="hw_name"/>
										<jsp:param value="${ sthw.member_id }" name="member_id"/>
										<jsp:param value="${ sthw.member_name }" name="member_name"/>
									</jsp:include>
								</c:if>
							</c:forEach>
	        			</div>
					</div>
	       		</c:forEach>
	    	</div>
		</div>
		
    </div>
</body>
</html>