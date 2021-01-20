<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.post_message.model.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
<!-- video.js CSS -->
<link
	href="<%=request.getContextPath()%>/vendors/video-js/css/video-js.css"
	rel="stylesheet" />
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
<!-- css -->
<link
	href="<%=request.getContextPath()%>/css/index.css"
	rel="stylesheet" />	
<link
	href="<%=request.getContextPath()%>/css/header.css"
	rel="stylesheet" />		
</head>
<body>

<i class="bi bi-bell"></i>

<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-bell" viewBox="0 0 16 16">
  <path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zM8 1.918l-.797.161A4.002 4.002 0 0 0 4 6c0 .628-.134 2.197-.459 3.742-.16.767-.376 1.566-.663 2.258h10.244c-.287-.692-.502-1.49-.663-2.258C12.134 8.197 12 6.628 12 6a4.002 4.002 0 0 0-3.203-3.92L8 1.917zM14.22 12c.223.447.481.801.78 1H1c.299-.199.557-.553.78-1C2.68 10.2 3 6.88 3 6c0-2.42 1.72-4.44 4.005-4.901a1 1 0 1 1 1.99 0A5.002 5.002 0 0 1 13 6c0 .88.32 4.2 1.22 6z"/>
</svg>

<!-- 選項 -->
<select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example">
  <option selected>通知</option>
  <option value="1">叮叮噹~聖誕優惠卷</option>
  <option value="2">惡靈退散~Happy牛year</option>
  <option value="3">指定課程全面9折</option>
</select>

<table class="table table-hover table-bordered border-primary">
  <thead>
    <tr>
      <th scope="col">通知</th>
      <th scope="col">發布時間</th>
      <th scope="col">公告對象</th>
      <th scope="col">公佈內容</th>
      <th scope="col">發布人員</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>時間</td>
      <td>全部會員</td>
      <td>叮叮噹~聖誕優惠卷</td>
      <td>員工編號</td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>時間</td>
      <td>老師</td>
      <td>惡靈退散~Happy牛year</td>
      <td>發布人員</td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td>時間</td>
      <td>老師</td>
      <td>指定課程全面9折</td>
      <td>發布人員</td>
    </tr>
  </tbody>
</table>





<!-- JavaScript -->
	<script src="./vendors/jquery/jquery-3.5.1.min.js"></script>
	<script src="./vendors/popper/popper.min.js"></script>
	<script src="./vendors/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>