<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.keyword_form.model.*" %>
<%@ page import="java.util.List" %>


<html>
<head>
<meta charset="BIG5">
<title>TEA102G5 keyword_form: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>TEA102G5 keyword_form: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for TEA102G5 keyword_form: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/keyword_form/Keyword_formServlet" >
<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/keyword_form/Keyword_formAll.jsp'>List</a> all keyword_forms.  <br><br></li>
  
  
  <li>
        <b>輸入開始日期:</b>
        <input name="startdate" id="startdate" type="text" autocomplete="off">
  </li>
  <li>
        <b>輸入結束日期:</b>
        <input name="enddate" id="enddate" type="text" autocomplete="off">
        <input type="hidden" name="action" value="get_by_date">
        <input type="submit" value="送出">
  </li>

  <jsp:useBean id="keyword_formSvc" scope="page" class="com.keyword_form.model.Keyword_formService" />
   
<!--   <li> -->
<%--      <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/keyword_form/Keyword_formServlet" > --%>
<!--        <b>選擇會員編號:</b> -->
<!--        <select size="1" name="member_id"> -->
<%--          <c:forEach var="keyword_formVO" items="${keyword_formSvc.all}" >  --%>
<%--           <option value="${keyword_formVO.member_id}">${keyword_formVO.member_id} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="送出"> -->
<!--     </FORM> -->
<!--   </li> -->
  
<!--   <li> -->
<%--      <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/keyword_form/Keyword_formServlet" > --%>
<!--        <b>選擇興趣:</b> -->
<!--        <select size="1" name="keyword_id"> -->
<%--          <c:forEach var="keyword_formVO" items="${keyword_formSvc.all}" >  --%>
<%--           <option value="${keyword_formVO.keyword_id}">${keyword_formVO.subclass_id} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="送出"> -->
<!--      </FORM> -->
<!--   </li> -->
</ul>
    </FORM>


<h3>關鍵字管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/keyword_form/addKeyword_form.jsp'>Add</a> a new Keyword_form.</li>
</ul>

</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<!-- 參考網站: https://xdsoft.net/jqplugins/datetimepicker/ -->
<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function(){
	 $('#startdate').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
	   this.setOptions({
	    maxDate:$('#enddate').val()?$('#enddate').val():false
	   })
	  },
	  timepicker:false,
	  step: 1
	 });
	 
	 $('#enddate').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
	   this.setOptions({
	    minDate:$('#startdate').val()?$('#startdate').val():false
	   })
	  },
	  timepicker:false,
	  step: 1
	 });
});
</script>
</html>