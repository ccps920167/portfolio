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
	
	//這個課程所有章節
	Class_chapterService chapterService = new Class_chapterService();
	List<Class_chapterVO> chapterList = chapterService.getClassAll(class_id);
	pageContext.setAttribute("chapterList", chapterList);
	
	//所有單元
	Class_unitService unitService = new Class_unitService();
	List<Class_unitVO> unitList = unitService.getAll();
	pageContext.setAttribute("unitList", unitList);
	
	//這個課程所有作業資訊
	Teacher_homeworkService trhwService = new Teacher_homeworkService();
	List<Map> trList = trhwService.selectByClassId(class_id);	
	pageContext.setAttribute("trList", trList);
	
	//這個課程所有作品資訊
	Student_homeworkService sthwService = new Student_homeworkService();
	List<Map<String,Object>> temp_sthwList = sthwService.worklist(class_id);
	List<Map<String,Object>> sthwList = 
			temp_sthwList
			.stream()
			.filter(map -> map.get("member_id").equals(member_id))
			.collect(Collectors.toList());
	pageContext.setAttribute("sthwList", sthwList);
	
	//把teacherhw_id當key，對應map當value
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
	<!-- 作業題目 -->
    <div id="content-class" class="w-100 mx-auto">
        <!-- title -->
        <div class="border-bottom border-muted py-3 mb-2">
            <div class="d-flex align-items-center col-3">
                <img class="mx-2 icon" src="<%=request.getContextPath()%>/img/icon/homework.png" alt="作業小icon">
                <span class="h2 mr-0 my-0 text-muted">作業練習</span>
            </div>
        </div>

        <!-- 下拉選單 -->
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
			<!-- 作業內容 -->
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
		<!-- 問題/填寫 選擇 -->
								<div class="choose_father w-25 d-flex ml-auto active">
						        	<a class="chooseQ nav-item nav-link border border-muted ml-auto hidden" data-toggle="tab" href="#trdoHomework_${ trhw.get('teacherhw_id') }">
						        		顯示題目
						        	</a>
						        	<a class="chooseA nav-item nav-link border border-muted ml-auto" data-toggle="tab" href="#stdoHomework_${ trhw.get('teacherhw_id') }">
						        		填寫作業
						        	</a>
					        	</div>						        	
		<!-- 作業問題 -->
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
								       			<img class='container-fluid' src="<%= request.getContextPath() %>/HwServlet?action=display_pic&teacherhw_id=${ trhw.teacherhw_id }" alt="作業附圖">
								       		</c:if>
										</div>
									</div>
					        	</div>
			<!-- 同學編輯 -->
		<!-- =============================================================================  -->	
								<div class="stHomework tab-pane hidden" id="stdoHomework_${ trhw.teacherhw_id }">
		<!-- 已做作業 -->	
									<c:if test="${ trid_stHw[trhw.teacherhw_id] != null }">
										<div class='dohomework border border-muted p-2'>
											<div class='w-100 border-bottom border-dark p-2'>
									          	<span>${ trhw.get("chapter_name") } > ${ trhw.get("unit_name") } > ${ trhw.get("hw_name") }</span>
									        </div>
									        <div class='w-75 border-bottom border-dark d-flex justify-content-center mx-auto my-3 py-3'>
									          <input name='hw_name' class='w-75 input-group input-group-prepend text-center text-muted my-3' type="text" placeholder="請輸入標題" value="${ trid_stHw[trhw.teacherhw_id].st_hw_name }" />
									        </div>
									        <div class='w-75 d-flex justify-content-center mx-auto py-4'>
									          <textarea name="hw_content" class="input-group input-group-prepend" cols="" rows="5" placeholder="請輸入內容">${ trid_stHw[trhw.teacherhw_id].st_hw_content }</textarea>
									        </div>
									        <div class="preview border border-muted w-75 d-flex justify-content-center mx-auto my-2">
						                		<div class="drop_zone w-100 d-flex align-items-center justify-content-center" style="min-height: 200px;">
							                		<c:if test="${ trid_stHw[trhw.teacherhw_id].st_file_data != null }">
								                		<img class="preview_img" src="<%=request.getContextPath()%>/StHwServlet?action=display_pic&studenthw_id=${ trid_stHw[trhw.teacherhw_id].studenthw_id }">
							                		</c:if>
							                		<c:if test="${ trid_stHw[trhw.teacherhw_id].st_file_data == null }">				                		
							                			<span>拖曳圖片至此預覽</span>
							                		</c:if>
							                	</div>
							                </div>
					                        <input id="file_${ trhw.teacherhw_id }" class="file" type="file" name="file_data" value="${ trid_stHw[trhw.teacherhw_id].st_file_data }" style="display:none">
											<div class="d-flex align-items-center justify-content-center">
											    <label for="file_${ trhw.teacherhw_id }" class="file form-control text-center m-0" style="cursor: pointer; width: 150px;">
											     	 請上傳照片
											     	 <img src="<%=request.getContextPath()%>/img/icon/2089052.png" alt="" width=16 height=16 style="margin-left: 10px; margin-top: -4px;">
											    </label>
											</div>
									        <div class="w-75 d-flex justify-content-center mx-auto py-3">
									          <button class="mx-4">清除</button>
									          <input type="hidden" name="action" value="update">
									          <button type="submit" class="mx-4">送出</button>
									        </div>
										</div>
									</c:if> 
		<!-- 未做作業 -->
									<c:if test="${ trid_stHw[trhw.teacherhw_id] == null }">
										<div class='dohomework border border-muted p-2'>
											<div class='w-100 border-bottom border-dark p-2'>
									          	<span>${ trhw.get("chapter_name") } > ${ trhw.get("unit_name") } > ${ trhw.get("hw_name") }</span>
									        </div>
									        <div class='w-75 border-bottom border-dark d-flex justify-content-center mx-auto my-3 py-3'>
									          <input name="hw_name" class='w-75 input-group input-group-prepend text-center text-muted my-3' type="text" placeholder="請輸入標題">
									        </div>
									        <div class='w-75 d-flex justify-content-center mx-auto py-4'>
									          <textarea name="hw_content" class="input-group input-group-prepend" cols="" rows="5" placeholder="請輸入內容"></textarea>
									        </div>
									        <div class="preview border border-muted w-75 d-flex justify-content-center mx-auto my-2">
						                		<div class="drop_zone w-100 d-flex align-items-center justify-content-center" style="min-height: 200px;">
							                			<span>拖曳圖片至此預覽</span>
							                	</div>
							                </div>
					                        <input id="file_${ trhw.get('teacherhw_id') }" class="file" type="file" name="file_data" value="" style="display:none">
											<div class="d-flex align-items-center justify-content-center">
											    <label for="file_${ trhw.get('teacherhw_id') }" class="file form-control text-center m-0" style="cursor: pointer; width: 150px;">
											     	 請上傳照片
											     	 <img src="<%=request.getContextPath()%>/img/NoResult/2089052.png" alt="" width=16 height=16 style="margin-left: 10px; margin-top: -4px;">
											    </label>
											</div>
									        <div class="w-75 d-flex justify-content-center mx-auto py-3">
									          <button class="mx-4">清除</button>
									          <input type="hidden" name="action" value="insert">
									          <button type="submit" class="mx-4">送出</button>
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