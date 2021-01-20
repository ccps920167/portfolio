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
// 	class_id = "CLA00002";
	Class_chapterService chapterService = new Class_chapterService();//���ᤣ��
	List<Class_chapterVO> chapterList = chapterService.getClassAll(class_id);//��ɭԭn���sevletcontext����initClass
	
	Class_unitService unitService = new Class_unitService();
	List<Class_unitVO> unitList = unitService.getAll();
	
	Teacher_homeworkService trhwService = new Teacher_homeworkService();
	List<Map> trhwList = trhwService.selectByClassId(class_id);
%>

<!DOCTYPE html>
<html>
<head>
	<!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
    <!-- video.js CSS -->
    <link href="<%=request.getContextPath()%>/vendors/video-js/css/video-js.css" rel="stylesheet" />
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">

    <style>
        .icon-sm{
        	width:10px;
        	height: 10px;
        }
        .hidden{
        	display: none;
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
	<title>�@�~���D</title>
</head>
<body>
	<div class="container p-0">
        <div class="d-flex justify-content-between">
<!-- =============================================   �s���        ========================================================= -->
            <div class="col-8 border border-dark p-3 mb-auto">
                <div class="border-bottom border-muted my-2">
                    <h2 class="text-center">�s��@�~</h2>
                </div>
<!-- =============================================   �@�~���        ========================================================= -->
                <div class="tab-content">
	            	<c:forEach var="trhw" items="<%= trhwList %>">
		            	<div class="tab-pane" id="update_${ trhw.get('teacherhw_id') }">
	            			<form class="input_f" METHOD="post" ACTION="<%= request.getContextPath()%>/HwServlet" enctype="multipart/form-data">
	            				<input type="hidden" name="teacherhw_id" value="${ trhw.get('teacherhw_id') }">
				                <div class="mb-2">
				                    <p>�@�~���D:</p>
				                    <input class="w-100 hw_name" type="text" name="hw_name" value="${ trhw.get('hw_name') }">
				                </div>
				                <div>
				                    <p>�@�~����:</p>
				                    <textarea class="w-100 hw_content" name="hw_content" cols="" rows="5" placeholder="�@�~�y�z">${ trhw.get('hw_content') }</textarea>
				                </div>
				                
			                	<div class="preview" class="border border-muted d-flex my-2">
			                		<div class="drop_zone w-100 d-flex align-items-center justify-content-center  my-2" style="min-height: 200px;">
				                		<c:if test="${ trhw.get('file_data')!=null }">
					                		<img class="preview_img" src="<%=request.getContextPath()%>/HwServlet?action=display_pic&teacherhw_id=${ trhw.get('teacherhw_id') }">
				                		</c:if>
				                		<c:if test="${ trhw.get('file_data')==null }">				                		
				                			<span>�즲�Ϥ��ܦ��w��</span>
				                		</c:if>
				                	</div>
				                </div>
		                        <input id="file_${ trhw.get('teacherhw_id') }" class="file" type="file" name="file_data" value="${ trhw.file_data }" style="display:none">
								<div class="d-flex align-items-center justify-content-between">
								    <label for="file_${ trhw.get('teacherhw_id') }" class="file form-control text-center m-0" style="cursor: pointer; width: 150px;">
								     	 �ФW�ǷӤ�
								     	 <img src="<%=request.getContextPath()%>/img/icon/2089052.png" alt="" width=16 height=16 style="margin-left: 10px; margin-top: -4px;">
								    </label>
								    <input type="hidden" name="action" value="update">
								    <button class="w-25" type="submit" value="�e�X���">
								      	�e�X���
								    </button>
								</div>
		            		</form>
	            		</div>
	            	</c:forEach>
<!-- =============================================   �ŧ@�~        ========================================================= -->
	            	<c:forEach var="chapterVO" items="<%= chapterList %>">
	            		<c:forEach var="unitVO" items="<%= unitList %>">
	            			<c:if test="${ unitVO.chapter_id==chapterVO.chapter_id }">
				            	<div class="tab-pane" id="insert_${ unitVO.unit_id }">
		            				<form METHOD="post" ACTION="<%= request.getContextPath()%>/HwServlet" enctype="multipart/form-data">
		            					<input type="hidden" name="unit_id" value="${ unitVO.unit_id }">
						                <div class="mb-2">
						                    <p>�@�~���D:</p>
						                    <input class="w-100 hw_name" type="text" name="hw_name" value="">
						                </div>
						                <div>
						                    <p>�@�~����:</p>
						                    <textarea class="w-100 hw_content" name="hw_content" cols="" rows="5" placeholder="�@�~�y�z"></textarea>
						                </div>
					                	<div class="preview" class="border border-muted d-flex my-2">
					                		<div class="drop_zone w-100 d-flex align-items-center justify-content-center my-2" style="min-height: 200px;">
						                		<span>�즲�Ϥ��ܦ��w��</span>
						                	</div>
						                </div>
				                        <input id="file_${ unitVO.unit_id }" class="file" type="file" name="file_data" style="display:none">
										<div class="d-flex align-items-center justify-content-between">
										    <label for="file_${ unitVO.unit_id }" class="file form-control text-center m-0" style="cursor: pointer; width: 150px;">
										     	 �ФW�ǷӤ�
										     	 <img src="<%=request.getContextPath()%>/img/icon/2089052.png" alt="" width=16 height=16 style="margin-left: 10px; margin-top: -4px;">
										    </label>
										    <input type="hidden" name="action" value="insert">
										    <button class="w-25" type="submit" value="�e�X�s�W">
										      	�e�X�s�W
										    </button>
										</div>
				            		</form>
			            		</div>
	            			</c:if>
	            		</c:forEach>
	            	</c:forEach>
<!-- =============================================   �ŧ@�~        ========================================================= -->
            	</div>
            </div>
<!-- =============================================   �s���        ========================================================= -->


<!-- =============================================   ������        ========================================================= -->
            <div class="col col-3 px-0 mb-auto border border-dark">
                <div class="border-bottom border-muted">
                	<c:set var="chapter_num" value="0"></c:set>
                	<c:forEach var="chapterVO" items="<%=chapterList %>">
                		<c:set var="chapter_num" value="${chapter_num + 1}" />
		<!-- =========================   CHAPTER���-title       ================================= -->
	                	<div class="card">
					      <div class="card-header py-0">
					        <h2 class="mb-0">
					          <button class="btn btn-block text-left" type="button" data-toggle="collapse" data-target="#update_${ chapterVO.chapter_id }">
					          	���`${ chapter_num } - ${ chapterVO.chapter_name }
					          </button>
					        </h2>
					      </div>
					      <div id="update_${ chapterVO.chapter_id }" class="collapse">
        					<div class="card-body col-11 ml-auto p-0">
        						<c:set var="unit_num" value="0"></c:set>
        						<c:forEach var="unitVO" items="<%= unitList %>">
        							<c:if test="${ unitVO.chapter_id==chapterVO.chapter_id }">        								
		<!-- ========================   UNIT���-title       ================================== -->
										<div class="unit_col">
        								<div class="card-header d-flex align-items-center justify-content-between pr-0 py-0">
										    <button class="unit_btn btn btn-block text-left col-8" type="button" data-toggle="collapse" data-target="#update_${ unitVO.unit_id }">
			        							<c:set var="unit_num" value="${ unit_num+1 }"></c:set>
										     	 �椸${ unit_num } - ${ unitVO.unit_name }
										    </button>
										    <button class="plus_btn btn btn-block col-3 p-0 m-0 hidden" type="button" data-toggle="tab" data-target="#insert_${ unitVO.unit_id }">										     	 
											    <img class="icon-sm" src="<%=request.getContextPath()%>/img/icon/plus.png" alt="�s�Wicon">
										    </button>
										</div>
		<!-- ============================   UNIT���-���e       ===================================== -->
										<div id="update_${ unitVO.unit_id }" class="collapse">
	        								<c:set var="trhw_num" value="0"></c:set>
				        					<div class="card-body col-11 ml-auto p-0">
												<c:forEach var="trhw" items="<%= trhwList %>">
				        							<c:if test="${ unitVO.unit_id==trhw.get('unit_id') }">
				        								<c:set var="trhw_num" value="${trhw_num + 1}" />
				        								<a class="trhw dropdown-item pr-0" data-toggle="tab" href="#update_${ trhw.get('teacherhw_id') }">
				        									<div style="overflow:hidden;">�@�~${ trhw_num } - ${ trhw.get('hw_name') }</div>
				        								</a>
				        							</c:if>
				        						</c:forEach>
				        						<a class="new_hw dropdown-item pr-0 hidden" data-toggle="tab" href="#insert_${ unitVO.unit_id }">
													<c:set var="trhw_num" value="${trhw_num + 1}" />
													<div style="overflow:hidden;">�@�~${ trhw_num } - �s�@�~</div>
				        						</a>
				        					</div>
				        				</div>
										</div>
		<!-- ============================   UNIT���-����       ================================ -->
        							</c:if>
        						</c:forEach>
		<!-- ========================================================================== -->
        					</div>
        				</div>
        			</div>
		<!-- ========================================================================== -->
                	</c:forEach>
                </div>
            </div>
<!-- =============================================   ������        ========================================================= -->
        </div>
    </div>
	<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>