<!-- 設定JSP編碼 -->
<%@page import="com.video_record.model.Video_recordVO"%>
<%@page import="com.video_record.model.Video_recordService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 設定標籤庫 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!---------------------------------------- css ---------------------------------------->

<!-- bootstrap -->
<link 
	rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
	
<!-- 後端CSS icon.css public.css -->
<link href="<%=request.getContextPath()%>/css/back-end/icon.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/back-end/public.css"
	rel="stylesheet" type="text/css" />

<!---------------------------------------- css ---------------------------------------->


</head>
<body lang="zh-tw">
  <input type='checkbox' id='menu_ckb' >
  <input type='checkbox' id='info_ckb' >

 

    <div id='menu'>
      

    	<h4 style="color: white;">TOMATO後台管理</h4>
 	    <a  href="<%=request.getContextPath()%>/back-end/index-back.jsp"><span>回首頁</span></a>
 	    <a  href="<%=request.getContextPath()%>/admin_list/AdminLoginServlet?action=adminLoginOut"><span>登出</span></a>
    	
      <a  style="margin-left: 20px"><span>訊息管理</span></a>
      <div class='sub'>
        <a class='icon-home' href="<%=request.getContextPath()%>/back-end/post_message/BEpostContext.jsp"><span>公告管理</span></a>
        <a class='icon-home' href="<%=request.getContextPath()%>/forward/backSrevlet?action=back-chat"><span>聊天室</span></a>
      </div>

      
      
      <a  style="margin-left: 20px"><span>課程管理</span></a>
      <div class='sub'>
<%--         <a class='icon-home' href="<%=request.getContextPath()%>/forward/backSrevlet?action=back-addClass_info"><span>新增課程</span></a> --%>
        <a class='icon-home' href="<%=request.getContextPath()%>/forward/backSrevlet?action=back-listAllClass_info"><span>課程列表</span></a>
<%--         <a class='icon-home' href="<%=request.getContextPath()%>/forward/backSrevlet?action=select_pageClass_info"><span>課程查詢</span></a> --%>
        <a class='icon-home' href="<%=request.getContextPath()%>/forward/backSrevlet?action=listMain_class"><span>類別列表</span></a>
      </div>
      
      
      <a  style="margin-left: 20px"><span>會員資料</span></a>
      <div class='sub'>
        <a class='icon-home' href="<%=request.getContextPath()%>/forward/backSrevlet?action=listAllMember_info"><span>會員列表</span></a>
        <a class='icon-home' href="<%=request.getContextPath()%>/forward/backSrevlet?action=listAllLogin_history"><span>會員登入歷程</span></a>
      
      </div>
      
      
      <a  style="margin-left: 20px"><span>費用管理</span></a>
      <div class='sub'>
<%--         <a class='icon-home' href="<%=request.getContextPath()%>/forward/backSrevlet?action=back-addClass_info"><span>優惠券</span></a> --%>
        <a class='icon-home' href="<%=request.getContextPath()%>/forward/backSrevlet?action=listAllorder_info"><span>訂單管理</span></a>
      </div>
      
      
      <a  style="margin-left: 20px"><span>數據管理</span></a>
      <div class='sub'>
        <a class='icon-home' href="<%=request.getContextPath()%>/forward/backSrevlet?action=listSell_info"><span>銷售分析</span></a>
	    <a class='icon-home' href="<%=request.getContextPath()%>/forward/backSrevlet?action=back-Keyword_formAll"><span>廣告與關鍵字</span></a>
     	
      </div>
      
      
<!--       <a  style="margin-left: 20px"><span>權限管理</span></a> -->
<!--       <div class='sub'> -->
<%--         <a class='icon-home' href="<%=request.getContextPath()%>/forward/backSrevlet?action=back-addClass_info"><span>管理員列表</span></a> --%>
<!--       </div> -->
    </div>


  </body>
  </html>
