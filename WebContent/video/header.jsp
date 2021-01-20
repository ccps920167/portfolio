
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


<% 
Member_infoVO member_infoVO = (Member_infoVO)session.getAttribute("member_infoVO");
Map<String, List<String>> MainSubClass = (Map<String, List<String>>) application.getAttribute("MainSubClass"); //取出ServletContext屬性
Set<String> keys = MainSubClass.keySet();
pageContext.setAttribute("keys", keys);
pageContext.setAttribute("MainSubClass", MainSubClass);
%>

 <header>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">TOMATO</a>
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
                        <a class="nav-link" href="#">我要開課</a>
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
                        alt="XX" style="height: 30px; margin-right:20px ;">
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="<%=request.getContextPath()%>/member_info/Member_Servlet?action=member_getinfoPage">帳號設定</a>
                        <a class="dropdown-item" href="<%=request.getContextPath()%>/member_info/Member_Servlet?action=member_infoPage">個人檔案</a>
                        <a class="dropdown-item" href="#">訂單紀錄</a>
                        <a class="dropdown-item" href="#">我的優惠</a>
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
                    style="height: 30px; margin-right:20px ;">
				</a>
				<a>
                <img src="<%=request.getContextPath()%>/img/icon/bell.webp" alt="XX"
                    style="height: 30px; margin-right:20px ;">
				</a>
            </div>
        </nav>
    </header>