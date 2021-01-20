<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.post_message.model.*"%>

<% Post_messageService post_messageSvc = new Post_messageService();
   List<Post_messageVO> list = post_messageSvc.getAll();
   pageContext.setAttribute("list",list);
%>


<!DOCTYPE html>
<html>
<head>

<title>���i�T��</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
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

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>
</head>

<body bgcolor='white'>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ����i�T��</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/post_message/select_pagePostMessage.jsp"><img src="<%=request.getContextPath()%>/back-end/post_message/images/SeaOtterWTF.png" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
<tr>
		<th>���i�s��</th>
		<th>���i���e</th>
		<th>���i�ɶ�</th>
		<th>���i�H��</th>
		<th>���i��H����</th>
		
</tr>
<%@ include file="page1.file" %>
   <c:forEach var="post_messageVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
      <tr> 
         <td>${post_messageVO.post_id}</td>
          <td>${post_messageVO.post_content} </td>
           <td><fmt:formatDate value="${post_messageVO.send_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td>${post_messageVO.admin_id} </td>
             <td>${post_messageVO.target_typeword} </td>

             <td>
                 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Post_message/Post_messageServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="post_id"  value="${post_messageVO.post_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
             </td>  
       </tr>
   </c:forEach>
</table>  
<%@ include file="page2.file" %>
<body>

</body>
</html>