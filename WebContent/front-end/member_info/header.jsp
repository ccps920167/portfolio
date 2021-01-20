
<%@page import="com.member_info.model.Member_infoVO"%>
<%@page import="com.sub_class.model.Sub_classVO"%>
<%@page import="com.class_unit.model.Class_unitVO" %>
<%@page import="com.class_unit.model.Class_unitService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.sub_class.*"%>
<%@ page import="com.member_info.*"%>
<%@ page import="java.util.*"%>


<style>
.cus-badge {
    position: absolute;
    top: -5px;
    right: -5px;
    background-color: #c00;
    color: white;
    border-radius: 50%;
}
</style>

<% 
Member_infoVO member_infoVO = (Member_infoVO)session.getAttribute("member_infoVO");
Map<String, List<String>> MainSubClass = (Map<String, List<String>>) application.getAttribute("MainSubClass"); //取出ServletContext屬性
Set<String> keys = MainSubClass.keySet();
pageContext.setAttribute("keys", keys);
pageContext.setAttribute("MainSubClass", MainSubClass);
%>

 <header>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp">TOMATO</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="<%=request.getContextPath()%>/index.jsp">首頁 <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/member_info/Member_Servlet?action=verify_list">我要開課</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" data-toggle="dropdown">
                        		    課程選單
                        </a>
                        <ul class="dropdown-menu">
                         <c:forEach var="mainclass" items="${keys}">
                            <li class="dropdown">
                            <a class="dropdown-item" href="#" data-toggle="drop">${mainclass}
	                            <img src="<%=request.getContextPath()%>/img/icon/2989988.png" alt="">
	                            </a>
                                <ul class="dropdown-menu">
                                    <c:forEach var="subclass"  items="${MainSubClass[mainclass]}" >
                                    	<a class="dropdown-item" href="<%=request.getContextPath()%>/Class_info/Class_Introduction?action=search&Category=subclass&class_status=4&subclass_id=${subclass.subClass_id}">
                                    	<li>${subclass.subClass_name}</li>
                                    	</a>
                                  	</c:forEach>
                                </ul>
                            </li>
								
                      	</c:forEach>
                       
                        </ul>
                    </li>
                    <li>				
                        <form class="form-inline my-2 my-lg-0" style="position:left" action="<%=request.getContextPath()%>/Class_info/Class_Introduction">
                            <input name="action" value="search" type="hidden">
                            <input name="class_status" value="4" type="hidden">
                            <input name="Category" value="class_list_search" type="hidden">
                            <input class="form-control mr-sm-2" name="class_name" type="search" placeholder="輸入你想要的課程" aria-label="Search">
                            <button class="btn btn-outline-danger my-2 my-sm-0" type="submit">Search</button>
                        </form>
                    </li>
                </ul>
                <!-- 會員下拉選單 -->
                <div class="dropdown">
                    <img data-toggle="dropdown" src="<%=request.getContextPath()%>/img/icon/member.png"
                        alt="XX" style="height: 30px; margin-right:20px ; cursor: pointer;">
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="<%=request.getContextPath()%>/member_info/Member_Servlet?action=member_getinfoPage">帳號設定</a>
                        <a class="dropdown-item" href="<%=request.getContextPath()%>/member_info/Member_Servlet?action=member_infoPage">個人檔案</a>
						<a class="dropdown-item" href="<%=request.getContextPath()%>/member_info/Member_info_frontServlet?action=getOne_For_DisplayOrder">訂單紀錄</a>
                        <a class="dropdown-item" href="<%=request.getContextPath()%>/member_info/Member_Servlet?action=member_coupon">我的優惠</a>
                        <c:if test="${member_infoVO.member_role == 1}">
                           <a class="dropdown-item" href="<%=request.getContextPath()%>/member_info/Member_Servlet?action=member_income">收益查詢</a>
                        </c:if>
                        <c:if test="${empty member_infoVO}">
                           <a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/sign_in/sign_in.jsp">登入</a>
                        </c:if>
                     	<c:if test="${not empty member_infoVO}">
                           <a class="dropdown-item" href="<%=request.getContextPath()%>/member_info/LoginHandler?action=logout">登出</a>
                        </c:if>

                    </div>
                </div>

				<a href="<%=request.getContextPath()%>/Order_info/EShopServlet?action=goToCar">
                <img src="<%=request.getContextPath()%>/img/icon/car.png" alt="XX"
                    style="height: 30px; margin-right:20px ; cursor: pointer;">
				</a>
					
	    		<!-- 鈴鐺下拉選單 -->
				<div class="dropdown">
					<span style=" cursor: pointer;"data-toggle="dropdown" onClick="resetBellCount()">
						<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-bell-fill" viewBox="0 0 16 16">
						  <path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zm.995-14.901a1 1 0 1 0-1.99 0A5.002 5.002 0 0 0 3 6c0 1.098-.5 6-2 7h14c-1.5-1-2-5.902-2-7 0-2.42-1.72-4.44-4.005-4.901z"/>
						</svg>

					</span>
					<span id="bellCount" class="badge cus-badge">0</span>
							
                    <div id= "bellmsg" class="dropdown-menu" style="left: -100px">
                    </div>
                    
                </div>
            </div>
        </nav>
    </header>
    
	<script>
	
		var MyPoint = "/Notification/XXX";
		var host = window.location.host;
		var path = window.location.pathname;
		var webCtx = path.substring(0, path.indexOf('/', 1));
		var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
	
	
		var webSocket;
	
	
		function connect() {
			// create a websocket
			webSocket = new WebSocket(endPointURL);
			console.log(endPointURL);
			
			webSocket.onopen = function(event) {
				console.log("Connect Success!");
				
			};
	
			webSocket.onmessage = function(event) {
				console.log(event.data);
				var jsonObj = JSON.parse(event.data);
				if ('1' == jsonObj.type) {
					var msg = jsonObj.post_content;
					console.log(msg)
					$('#bellmsg').prepend(buildMsgTag(msg));
					var nowCountStr = $('#bellCount').html();
					var nowCount = parseInt(nowCountStr, 10);
					$('#bellCount').html(nowCount+1);
				}
			};
			webSocket.onclose = function(event) {
			};
		}
		
			
			function buildMsgTag(msg) {
			    return '<a class="dropdown-item" href="<%=request.getContextPath()%>/member_info/Member_Servlet?action=member_coupon">' + msg + '</a>';
			  }
			
			
		function disconnect() {
			webSocket.close();
			document.getElementById('sendMessage').disabled = true;
			document.getElementById('connect').disabled = false;
			document.getElementById('disconnect').disabled = true;
		}
		
		function resetBellCount(){
			$("#bellCount").html("0");
		}
		
		window.onload = connect;
	</script>