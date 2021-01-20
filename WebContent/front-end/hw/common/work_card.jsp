<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.sql.Timestamp"%>
   
<%
	String studenthw_id = request.getParameter("studenthw_id");

	String hw_name = request.getParameter("hw_name");
	pageContext.setAttribute("hw_name", hw_name);
	
	String updatetime = request.getParameter("st_hw_updatetime");
	Timestamp hw_updatetime = Timestamp.valueOf(updatetime);
	pageContext.setAttribute("hw_updatetime", hw_updatetime);
	
	String member_id = request.getParameter("member_id");

	String member_name = request.getParameter("member_name");

%>

<!DOCTYPE html>
<html>
<head>
</head>
<body>
<%-- ${ hw_name } --%>
		<div class="card my-2" style="width: 20rem;">
			<a href="<%= request.getContextPath() %>/front-end/hw/common/work.jsp?studenthw_id=<%= studenthw_id%>">
				<!-- 	下個頁面要給studenthw_id		 -->
				<img class="card-img-top" src="<%= request.getContextPath()%>/studenthwServlet?action=display_pic&studenthw_id=<%= studenthw_id %>" alt="作品圖">
			    <div class="row-3 text-center border-bottom border-dark m-1">
					<!--	要給對應trVO的hw_name	 -->
					<h4><%= hw_name%></h4>
					<!-- 	studenthwVO的updatetime -->
			        <span class="card-text"><fmt:formatDate value="${ hw_updatetime }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
			    </div>
			    <div class="card-text d-flex align-items-center mb-1">
			        <div class="col col-3 pr-0">
						<!-- 	利用studenthwVO找member_infoid，再用member_infoid印圖 -->
			            <img class="rounded-circle img-fluid" src="<%= request.getContextPath()%>/member_info/Member_infoServlet?action=member_pic&member_id=<%= member_id%>" alt="學生頭像">
			        </div>
			        <div class="col col-9 p-0">
						<!-- 	利用studenthwVO找member_infoid，再用member_infoid找VO，再用VO找member_name -->
			            <p class=" text-center m-0"><%= member_name%></p>
			        </div>
			    </div>
			</a>
		</div>
</body>
</html>