<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@page import="java.util.stream.Collectors"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.sql.Timestamp"%>
<%@ page import="java.util.*"%>
<%@ page import="com.teacher_homework.model.*"%>
<%@ page import="com.student_homework.model.*"%>
<%@ page import="com.member_info.model.*"%> 

<% String locatoin = (String)request.getRequestURI(); %>

<%
	String studenthw_id = request.getParameter("studenthw_id");//從上個work_card來的
// 	out.print(studenthw_id);
	List<Map<String,Object>> sthwList = (List<Map<String,Object>>)request.getSession().getAttribute("sthwList");//work_List存進去的
// 	out.print(sthwList);
	List<Map<String,Object>> list = sthwList.stream().filter(sthw -> sthw.get("studenthw_id").equals(studenthw_id)).collect(Collectors.toList());//剩下這個作品
// 	out.print(list);
	Map sthw = null;
	for(Map temp_shtw:list){
		sthw = temp_shtw;
	}
// 	這個作品的資訊map
	pageContext.setAttribute("sthw", sthw);
%>

<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
    <!-- video.js CSS -->
    <link href="<%=request.getContextPath()%>/vendors/video-js/css/video-js.css" rel="stylesheet" />
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
    <style>
        /* 套用全部 */
        * {
            box-sizing: border-box;
        }
        /* header  */
        header {
            width: 100%;
            box-shadow: 0px 1px 10px rgb(202, 202, 197);
            z-index: 9999;
            position: sticky;
            top: 0;
        }
        /* 影片底圖 */
        .sidebar {
            background: linear-gradient(rgba(255, 255, 255, 0.466), rgba(255, 255, 255, 0.5)), url(https://www.twcode01.com/images/demo/demo2.jpg);
            border: 1px #ccc solid;
            padding: 10px;
            /*去掉固定高度，以內容撐高度*/
            /* font-size: 0; */
        }
        #talk {
            position: fixed;
            right: 20px;
            bottom: 30px;
            height: 60px;
        }
        /* 內部框 */
        #content {
            margin-top: 10px;
            margin-bottom: 50px;
            border: 1px rgb(204, 204, 204) solid;
        }
        #content-class {
            margin-top: 10px;
            margin-bottom: 20px;
            /* border: 1px rgb(204, 204, 204) solid; */
        }
        .video-js {
            position: relative;
            width: 100%;
            top: 1%;
        }
        /* 自訂按鈕- */
        .class-btn {
            background-color: rgba(255, 255, 255);
            color: black;
            border: 1px rgb(204, 204, 204) solid;
            padding: 15px;
            border-radius: .25rem;
            /*增加了圓框*/
        }
    </style>
	<title>Insert title here</title>
</head>
<body>

<!-- 	include header -->
	<jsp:include page="/header.jsp"></jsp:include>

	<!-- title -->
    <div class="py-3 border-bottom border-muted">
        <h2 class="col col-2 mx-auto my-0 text-center text-muted">
            <a class="text-dark" href="<%=request.getHeader("referer")%>">學生作品</a>
        </h2>
    </div>
    <!-- 內容 -->
    <div class="container d-flex justify-content-center p-3">
        <div class="col-3">
            <img class="rounded-circle img-fluid img-thumbnail" src="<%= request.getContextPath()%>/member_info/Member_infoServlet?action=member_pic&member_id=${ sthw.member_id }" alt="學生頭像">
        </div>
        <div class="col-6 py-2 px-4 border border-dark">
            <div>
                <span>${ sthw.tr_hw_name }</span>
                <div class="d-flex justify-content-between">
                    <h3>${ sthw.st_hw_name }</h3>
                    <span class="py-2"><fmt:formatDate value="${ sthw.st_hw_updatetime }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                </div>
                <div class="my-2">
                	<c:if test="${ sthw.st_file_data != null }">
	                	<img class="card-img-top" src="<%= request.getContextPath()%>/studenthwServlet?action=display_pic&studenthw_id=${ sthw.studenthw_id }" alt="作品圖">
                	</c:if>
                </div>
                <div >
                    <h4 class="text-left border-bottom border-muted mb-0">作品說明</h4>
                    <p class="text-left p-2">${ sthw.st_hw_content }</p>
                </div>
            </div>
<!--             include 留言 -->
<%--             <jsp:include page="message.jsp"></jsp:include> --%>
        </div>
    </div>
	
<!-- 	include chat -->	
<!-- 	include footer -->
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>