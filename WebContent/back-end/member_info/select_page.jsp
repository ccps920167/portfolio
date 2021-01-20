<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
<style>
/* �M�Υ��� */
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

#talk {
	position: fixed;
	right: 20px;
	bottom: 30px;
	height: 60px;
}

/* ������ */
#content {
	margin-top: 10px;
	margin-bottom: 50px;
	border: 1px rgb(204, 204, 204) solid;
	padding: 20px 10px;
}

input[disabled] {
	background-color: #eee;
	cursor: not-allowed;
}



label {
	margin-top: 10px;
}

.form-check-label {
	margin-top: 0;
	padding-left: 0;
}

#submit-btn {
	margin-top: 20px;
}

 li{
    list-style: none;
 }
</style>
<title>TOMATO - ��O�޲z</title>
</head>



<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="#">TOMATO</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active"><a class="nav-link" href="#">����
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="#">�ڪ��ҵ{</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> �ҵ{��� </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="#">�y��</a> <a class="dropdown-item"
								href="#">��v</a> <a class="dropdown-item" href="#">�]�p</a> <a
								class="dropdown-item" href="#">�H��</a> <a class="dropdown-item"
								href="#">��P</a> <a class="dropdown-item" href="#">�{��</a>
						</div></li>
					<li>
						<form class="form-inline my-2 my-lg-0" style="position: left">
							<input class="form-control mr-sm-2 form-control" type="search"
								placeholder="��J�A�Q�n���ҵ{" aria-label="Search">
							<button class="btn btn-outline-success my-2 my-sm-0"
								type="submit">Search</button>
						</form>
					</li>
				</ul>
				<!-- �|���U�Կ�� -->
				<div class="dropdown">
					<img data-toggle="dropdown"
						src="https://pic.90sjimg.com/design/00/08/16/10/591fa3911d5ee.png"
						alt="XX" style="height: 30px; margin-right: 20px;">
					<div class="dropdown-menu">
						<a class="dropdown-item" href="#">�b���]�w</a> <a
							class="dropdown-item" href="#">�ӤH�ɮ�</a> <a class="dropdown-item"
							href="#">�q�����</a> <a class="dropdown-item" href="#">�ڪ��u�f</a> <a
							class="dropdown-item" href="#">�n�X</a>
					</div>
				</div>


				<img
					src="https://i-1.lanrentuku.com/2020/11/9/7d9cf2fd-ed76-466a-9012-51b017c46025.png"
					alt="XX" style="height: 30px; margin-right: 20px;"> <img
					src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-ios7-bell-512.png"
					alt="XX" style="height: 30px; margin-right: 20px;">

			</div>
		</nav>
	</header>


 <div class="jumbotron jumbotron-fluid">
            <div class="container">
                <h1>�|���b���޲z</h1>
                
            </div>
        </div>






	<h3>��Ƭd��:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='listAllMember_info.jsp'>�C�X�����|�����</a></li>
		<li><a href="<%=request.getContextPath()%>/back-end/member_info/addMember_info.jsp">�s�W�|�����</a></li>
		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member_info/Member_infoServlet">
				<div class="col-6">
					<label for="inputPassword1" class="form-label">�|���s���j�M</label> 
					<input type="text" class="form-control" id="inputPassword1" name="member_id"> 
					<input type="hidden" name="action" value="getOne_For_Display"> 
					<input type="submit" value="�e�X�d��">
				</div>
			</FORM>
		</li>

		<jsp:useBean id="member_infoSvc" scope="page"
			class="com.member_info.model.Member_infoService" />

		<li>
			<FORM class="row g-3" METHOD="post"
				ACTION="<%=request.getContextPath()%>/member_info/Member_infoServlet">
				<div class="col-6">
				<label for="inputPassword1" class="form-label">��ܷ|���s��</label> 
				<select size="1" name="member_id">
					<c:forEach var="member_infoVO" items="${member_infoSvc.all}">
						<option value="${member_infoVO.member_id}">${member_infoVO.member_id}
					</c:forEach>
				</select> 
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="�e�X">
				</div>
			</FORM>
		</li>


	</ul>


	

	<footer class="page-footer font-small blue pt-4"
		style="background-color: azure;">

		<div class="container-fluid text-center text-md-left">


			<div class="row">


				<div class="col-6 mt-md-0 mt-3">

					<h5 class="text-uppercase">Footer Content</h5>

					<p>Here you can use rows and columns to organize your footer
						content.</p>


				</div>
			</div>
		</div>

		<div class="footer-copyright text-center py-3">
			2020 Copyright: <a href="https://mdbootstrap.com/">
				MDBootstrap.com</a>
		</div>

	</footer>

	<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>