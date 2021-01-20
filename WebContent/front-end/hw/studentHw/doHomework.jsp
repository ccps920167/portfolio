<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@page import="com.member_info.model.Member_infoVO"%>
<%@page import="java.sql.Timestamp"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.stream.Collector"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="java.util.stream.*"%>
<%@ page import="com.teacher_homework.model.*"%>
<%@ page import="com.student_homework.model.*"%>
<%@ page import="com.class_chapter.model.*"%>
<%@ page import="com.class_unit.model.*"%>

<%
	String class_id = request.getParameter("class_id");
	pageContext.setAttribute("class_id", class_id);
	
	String member_id = request.getParameter("member_id");
	pageContext.setAttribute("member_id", member_id);
	
	//�o�ӽҵ{�Ҧ����`
	Class_chapterService chapterService = new Class_chapterService();
	List<Class_chapterVO> chapterList = chapterService.getClassAll(class_id);
	pageContext.setAttribute("chapterList", chapterList);
	
	//�Ҧ��椸
	Class_unitService unitService = new Class_unitService();
	List<Class_unitVO> unitList = unitService.getAll();
	pageContext.setAttribute("unitList", unitList);
	
	//�o�ӽҵ{�Ҧ��@�~��T
	Teacher_homeworkService trhwService = new Teacher_homeworkService();
	List<Map> trList = trhwService.selectByClassId(class_id);	
	pageContext.setAttribute("trList", trList);
	
	//�o�ӽҵ{�Ҧ��@�~��T
	Student_homeworkService sthwService = new Student_homeworkService();
	List<Map<String,Object>> temp_sthwList = sthwService.worklist(class_id);
	List<Map<String,Object>> sthwList = 
			temp_sthwList
			.stream()
			.filter(map -> map.get("member_id").equals(member_id))
			.collect(Collectors.toList());
	pageContext.setAttribute("sthwList", sthwList);
	
	//��teacherhw_id��key�A����map��value
	Map<String,Map> trid_stHw = new LinkedHashMap<String,Map>();
	for(Map sthw:sthwList){
		trid_stHw.put((String)sthw.get("teacherhw_id"),sthw); 
	}
	pageContext.setAttribute("trid_stHw", trid_stHw);
%>

<!DOCTYPE html>
<html>
<head>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">    
	<title>doHomework</title>
</head>
<body>
	<!-- �@�~�D�� -->
    <div id="content-class" class="w-100 mx-auto">
        <!-- title -->
        <div class="border-bottom border-muted py-3 mb-2">
            <div class="d-flex align-items-center col-3">
                <img class="mx-2 icon" src="<%=request.getContextPath()%>/img/icon/homework.png" alt="�@�~�picon">
                <span class="h2 mr-0 my-0 text-muted">�@�~�m��</span>
            </div>
        </div>

        <!-- �U�Կ�� -->
        <div class="row">
        	<div class="col-3 pl-0">
				<div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">		      
					<c:if test="${ empty trList}">
						<img src="<%=request.getContextPath() %>/img/NoResult/NoResult.jpg">
					</c:if>
					<c:if test="${ not empty trList}">
						<c:forEach var="map" items="${trList}">
							<a class="drop-choose nav-link btn btn-light" data-toggle="pill" href="#doHomework_${ map.get('teacherhw_id') }" role="tab" aria-controls="v-pills-profile" aria-selected="false">
				      			<span name="doHomework_${ map.get('teacherhw_id') }"> ${ map.get("chapter_name") } > ${ map.get("unit_name") } > ${ map.get("hw_name") }</span>
							</a>
				      	</c:forEach>
					</c:if>
	    		</div>
			</div>
			<!-- �@�~���e -->
			<div class="col-8 mx-auto">
			    <div class="tab-content row mx-auto">
					<c:forEach var="trhw" items="<%= trList %>">
						<div class="tab-pane tab-content container-fluid" id="doHomework_${ trhw.get('teacherhw_id') }">
				        	<form METHOD="post" ACTION="<%= request.getContextPath()%>/StHwServlet" enctype="multipart/form-data">  
								<input type="hidden" name="studenthw_id" value="${ sthw.studenthw_id }">
								<input type="hidden" name="teacherhw_id" value="${ trhw.teacherhw_id }">
								<input type="hidden" name="member_id" value="${ member_id }">
								<input type="hidden" name="hw_uploadtime" value="${ sthw.st_hw_uploadtime }">
								<input type="hidden" name="hw_updatetime" value="${ sthw.st_hw_updatetime }">
		<!-- ���D/��g ��� -->
								<div class="choose_father w-25 d-flex ml-auto active">
						        	<a class="chooseQ nav-item nav-link border border-muted ml-auto hidden" data-toggle="tab" href="#trdoHomework_${ trhw.get('teacherhw_id') }">
						        		����D��
						        	</a>
						        	<a class="chooseA nav-item nav-link border border-muted ml-auto" data-toggle="tab" href="#stdoHomework_${ trhw.get('teacherhw_id') }">
						        		��g�@�~
						        	</a>
					        	</div>						        	
		<!-- �@�~���D -->
					        	<div class="trHomework tab-pane mt-3 active" id="trdoHomework_${ trhw.teacherhw_id }">
						        	<div class='p-2 border border-muted'>
						        		<div class='border-bottom border-dark mx-2 my-5'>
						        			<h2 name="hw_name" class='mr-0 my-3 text-center text-muted'>${ trhw.hw_name }</h2>
						        		</div>
						        		<div class='border border-muted mx-auto my-2 p-2'>
						        			<p>${ trhw.hw_content }</p>
						        		</div>
							        	<div class='mx-auto my-2'>
								       		<c:if test="${ trhw.file_data!= null }">
								       			<img class='container-fluid' src="<%= request.getContextPath() %>/HwServlet?action=display_pic&teacherhw_id=${ trhw.teacherhw_id }" alt="�@�~����">
								       		</c:if>
										</div>
									</div>
					        	</div>
			<!-- �P�ǽs�� -->
		<!-- =============================================================================  -->	
								<div class="stHomework tab-pane hidden" id="stdoHomework_${ trhw.teacherhw_id }">
		<!-- �w���@�~ -->	
									<c:if test="${ trid_stHw[trhw.teacherhw_id] != null }">
										<div class='dohomework border border-muted p-2'>
											<div class='w-100 border-bottom border-dark p-2'>
									          	<span>${ trhw.get("chapter_name") } > ${ trhw.get("unit_name") } > ${ trhw.get("hw_name") }</span>
									        </div>
									        <div class='w-75 border-bottom border-dark d-flex justify-content-center mx-auto my-3 py-3'>
									          <input name='hw_name' class='w-75 input-group input-group-prepend text-center text-muted my-3' type="text" placeholder="�п�J���D" value="${ trid_stHw[trhw.teacherhw_id].st_hw_name }" />
									        </div>
									        <div class='w-75 d-flex justify-content-center mx-auto py-4'>
									          <textarea name="hw_content" class="input-group input-group-prepend" cols="" rows="5" placeholder="�п�J���e">${ trid_stHw[trhw.teacherhw_id].st_hw_content }</textarea>
									        </div>
									        <div class="preview border border-muted w-75 d-flex justify-content-center mx-auto my-2">
						                		<div class="drop_zone w-100 d-flex align-items-center justify-content-center" style="min-height: 200px;">
							                		<c:if test="${ trid_stHw[trhw.teacherhw_id].st_file_data != null }">
								                		<img class="preview_img" src="<%=request.getContextPath()%>/StHwServlet?action=display_pic&studenthw_id=${ trid_stHw[trhw.teacherhw_id].studenthw_id }">
							                		</c:if>
							                		<c:if test="${ trid_stHw[trhw.teacherhw_id].st_file_data == null }">				                		
							                			<span>�즲�Ϥ��ܦ��w��</span>
							                		</c:if>
							                	</div>
							                </div>
					                        <input id="file_${ trhw.teacherhw_id }" class="file" type="file" name="file_data" value="${ trid_stHw[trhw.teacherhw_id].st_file_data }" style="display:none">
											<div class="d-flex align-items-center justify-content-center">
											    <label for="file_${ trhw.teacherhw_id }" class="file form-control text-center m-0" style="cursor: pointer; width: 150px;">
											     	 �ФW�ǷӤ�
											     	 <img src="<%=request.getContextPath()%>/img/icon/2089052.png" alt="" width=16 height=16 style="margin-left: 10px; margin-top: -4px;">
											    </label>
											</div>
									        <div class="w-75 d-flex justify-content-center mx-auto py-3">
									          <button class="mx-4">�M��</button>
									          <input type="hidden" name="action" value="update">
									          <button type="submit" class="mx-4">�e�X</button>
									        </div>
										</div>
									</c:if> 
		<!-- �����@�~ -->
									<c:if test="${ trid_stHw[trhw.teacherhw_id] == null }">
										<div class='dohomework border border-muted p-2'>
											<div class='w-100 border-bottom border-dark p-2'>
									          	<span>${ trhw.get("chapter_name") } > ${ trhw.get("unit_name") } > ${ trhw.get("hw_name") }</span>
									        </div>
									        <div class='w-75 border-bottom border-dark d-flex justify-content-center mx-auto my-3 py-3'>
									          <input name="hw_name" class='w-75 input-group input-group-prepend text-center text-muted my-3' type="text" placeholder="�п�J���D">
									        </div>
									        <div class='w-75 d-flex justify-content-center mx-auto py-4'>
									          <textarea name="hw_content" class="input-group input-group-prepend" cols="" rows="5" placeholder="�п�J���e"></textarea>
									        </div>
									        <div class="preview border border-muted w-75 d-flex justify-content-center mx-auto my-2">
						                		<div class="drop_zone w-100 d-flex align-items-center justify-content-center" style="min-height: 200px;">
							                			<span>�즲�Ϥ��ܦ��w��</span>
							                	</div>
							                </div>
					                        <input id="file_${ trhw.get('teacherhw_id') }" class="file" type="file" name="file_data" value="" style="display:none">
											<div class="d-flex align-items-center justify-content-center">
											    <label for="file_${ trhw.get('teacherhw_id') }" class="file form-control text-center m-0" style="cursor: pointer; width: 150px;">
											     	 �ФW�ǷӤ�
											     	 <img src="<%=request.getContextPath()%>/img/NoResult/2089052.png" alt="" width=16 height=16 style="margin-left: 10px; margin-top: -4px;">
											    </label>
											</div>
									        <div class="w-75 d-flex justify-content-center mx-auto py-3">
									          <button class="mx-4">�M��</button>
									          <input type="hidden" name="action" value="insert">
									          <button type="submit" class="mx-4">�e�X</button>
									        </div>
										</div>
									</c:if>
								</div>
		<!-- =============================================================================  -->
				        	</form> 
						</div>
					</c:forEach>
				</div>
			</div>
        </div>
	</div>
	

</body>
</html>