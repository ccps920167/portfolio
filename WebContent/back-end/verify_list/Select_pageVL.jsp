
<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>


<html>
<head>
<meta charset="BIG5">
<title>Select_pageVL</title>

</head>
<body>
	<ul>
	<%-- ���~��C --%>
	<c:if test="${not empty erMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${erMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
		<li>
			<h2>�o�OVerify_list������</h2>
		</li>
		<li>
			<h4>��Ƭd��</h4>
		</li>
		<li>
			<h5>
				�C�X�Ҧ��f�֬��� <a href="<%=request.getContextPath()%>/back-end/verify_list/ListAllVL.jsp">ListAll</a>
			</h5>
		</li>
		<li>
			<h5>
				�s�W�f�֬��� <a href="addVL.jsp">�s�W�f�֬���</a>
			</h5>
		</li>
		<li>
			<h5>�d�߳浧���</h5>
			<form method="post" action="/TEA102G5/verify_list/verify_listServlet">
				<p>��J�f�ָ�ƽs��</p>
				<input type="text" name="verify_id"> 
				<input type="hidden" name="action" value="SearchOne"> 
				<input type="submit" value="�e�X">
			</form>
		</li>
	</ul>

</body>
</html>