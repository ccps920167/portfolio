<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.sql.Timestamp"%>
<%@ page import="java.util.*"%>
<%@ page import="com.teacher_homework.model.*"%>
<%@ page import="com.student_homework.model.*"%>
<%@ page import="com.member_info.model.*"%> 


<!DOCTYPE html>
<html>
<head>

<title>Insert title here</title>
</head>
<body>
	<div class="pb-2 mb-2">
	    <div class="row mb-3">
	        <div class="col-3 d-flex justify-content-center">
	            <img class="head-img rounded-circle img-fluid" src="<%= request.getContextPath()%>/member_info/Member_infoServlet?action=member_pic&member_id=MEM00001" alt="學生頭像">
	        </div>
	        <div class="col p-0 mr-3">
	            <div class="d-flex justify-content-between mb-1">
	                <span>我是一隻法鬥</span>
	                <span class="mr-3">2020/01/11 12:20:03</span>
	            </div>
	            <div class="table-responsive" style="max-height: 100px;">
	                <span>文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字</span>
	            </div>
	        </div>
	    </div>
	    <div class="row w-75 mb-2 ml-auto mr-0">
	        <div class="col-3 d-flex justify-content-center">
	            <img class="head-img rounded-circle img-fluid" src="<%= request.getContextPath()%>/member_info/Member_infoServlet?action=member_pic&member_id=MEM00002" alt="學生頭像">
	        </div>
	        <div class="col p-0  mr-3">
	            <div class="d-flex justify-content-between mb-1">
	                <span>我是一隻法鬥</span>
	                <span>2020/01/11 12:20:03</span>
	            </div>
	            <div class="table-responsive" style="max-height: 100px;">
	                <span>文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字</span>
	            </div>
	        </div>
	    </div>
	    <div class="w-75 border boerder-muted ml-auto p-1">
	        <form class="p-1" ACTION="<%=request.getContextPath()%>/Sub_message/Sub_messageServlet">
	            <textarea class="form-control px-2 py-0" name="submsg_text" cols="" rows="" placeholder="在此回覆"></textarea>
	            <div class="mt-2 d-flex justify-content-end">
	                <button>清除</button>
		            <input type="hidden" name="action" value="insert">
					<button type="submit">送出</button>
	            </div>
	            <input type="hidden" name="class_id" value="">
	            <input type="hidden" name="member_id" value="">
	            <input type="hidden" name="msg_source" value="">
	        </form>
	    </div>
	</div>
    <div class="border boerder-muted p-2">
        <form class="p-2" METHOD="post" ACTION="<%=request.getContextPath()%>/Main_message/MainMsgServlet">
            <textarea class="form-control p-2" name="mainmsg_text" id="" cols="" rows="5" placeholder="在此回覆"></textarea>
            <div class="mt-2 d-flex justify-content-end">
                <button>清除</button>
		        <input type="hidden" name="action" value="insert">
				<button type="submit">送出</button>
            </div>
            <input type="hidden" name="class_id" value="">
            <input type="hidden" name="member_id" value="">
            <input type="hidden" name="msg_source" value="">
        </form>
    </div>
</body>
</html>