<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>login_success.jsp</title>
</head>
<body>

	<br>
	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='orange' align='center' valign='middle' height='20'>
			 <td>   
			       <h3> �n�J���\������ - login_success.jsp           </h3> 
				     <h3> �w��:<font color=red> ${account} </font>�z�n</h3>
			 </td>
		</tr>
		<tr>
		<td>
		<h4>
		<a href="<%=request.getContextPath()%>/front-end/member_info/student.jsp">(0)�I���i�J�ǥͭ���</a>
		</h4>
		</td>
		</tr>
		<tr>
		<td>
		<h4>
		<a href="<%=request.getContextPath()%>/front-end/member_info/teacher.jsp">(1)�I���i�J�Ѯv����</a>
		</h4>
		</td>
		</tr>
		<tr>
		<td>
		<h4>
		<a href="<%=request.getContextPath()%>/front-end/member_info/allmember.jsp">(all)�I���i�J�Ҧ��|������</a><br>
		</h4>
		</td>
		</tr>
		<tr>
		<td>�ثe�n�J������:${judgement}</td>
		</tr>
	</table>
	<b><br>
	<br>               
	
	
	</b>
	
</body>
</html>
