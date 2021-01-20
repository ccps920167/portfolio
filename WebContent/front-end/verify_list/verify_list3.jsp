<!-- 設定JSP編碼 -->
<%@page import="com.video_record.model.Video_recordVO"%>
<%@page import="com.video_record.model.Video_recordService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 設定標籤庫 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.class_info.model.*"%>

<!doctype html>
<html lang="en">

<head>

<style>
.btncss{
margin-bottom:6px;
}
</style>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
<!-- video.js CSS -->
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/verify_list/index.css"> --%>
<link
	href="<%=request.getContextPath()%>/vendors/video-js/css/video-js.css"
	rel="stylesheet" />
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
<!-- css -->
<link href="<%=request.getContextPath()%>/css/index.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/header.css"
	rel="stylesheet" />
	

<link href="https//cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css" rel="stylesheet">

<title>TOMATO - 讓你分分鐘鐘都在學習的平台</title>
</head>

<body>
	<%@ include file="header.jsp"%>
	
	<div class="container">
	
	<!-- 標題 -->
        <div class="jumbotron jumbotron-fluid">
            <div class="container" style="text-align:center">
                <nav aria-label="...">
                    <ul class="pagination pagination-lg justify-content-center">
                        <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/front-end/verify_list/verify_list1.jsp">1</a></li>
                        <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/front-end/verify_list/verify_list2.jsp">2</a></li>
                        <li class="page-item active" aria-current="page">
                            <span class="page-link">
                                3
                                <span class="sr-only">(current)</span>
                            </span>
                        </li>
                        <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/front-end/verify_list/verify_list4.jsp">4</a></li>
                        <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/front-end/verify_list/verify_list5.jsp">5</a></li>
                        <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/front-end/verify_list/verify_list6.jsp">6</a></li>
                    </ul>
                </nav>
                <h1>建立自己的課程大綱！</h1>
                <h4>向學生介紹您的課程架構！</h4>
            </div>
        </div>
        	
                <div id="content">
                <h1>新增大綱</h1>
            <div class="row allinform">
                <div class="col-12">
                    <form method="post" action="" name="form1" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="exampleInputEmail1"></label>

                            <input type="text" class="mainClass_name clear" placeholder="章節名稱" size="50">
                            <button type="button" class="btn btn-primary mainClass_add btncss"
                                style="padding: 3px;">新增章節</button>
                        </div>

                    </form>

                </div>
                <!-- <div class="col-3">
                    <ul class="main_class_list" id="chapterClass">

                    </ul>
                    <ul class="sub_class_list" id="unitClass">

                    </ul>
                </div> -->
            </div>
        </div>
        
           <div style="margin-top:30px">
               <input class="btn btn-danger" type="button" value="上一頁" onclick="location.href='<%=request.getContextPath()%>/front-end/verify_list/verify_list2.jsp'">
			   <input class="btn btn-danger" type="button" value="下一頁" onclick="location.href='<%=request.getContextPath()%>/front-end/verify_list/verify_list4.jsp'">
		   </div>
	</div>
	
	<%@ include file="footer.jsp"%>
	<!-- JavaScript -->
	<script
		src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>

			
			
		<script>

        $("button.mainClass_add").on("click ", function (event) {
            if ($("input.mainClass_name").val() != "") {
                var addchapter = $("input.mainClass_name").val()
//                 console.log(addchapter);

                
            $(".allinform").append('<div class="lists col-12"><button type="button" class="btn btn-danger chapter_del btncss" style="padding: 3px; margin-right:3px;">刪除章節</button><input disabled id="chapterClass" type="text" value=' + addchapter + ' size="50"> <button type="button" class="btn btn-primary subClass_add btncss" style="padding: 3px;">新增單元</button><button type="button" class="lock btn btn-info btncss" style="padding:3px;margin-left:3px ;">確認送出</button><div class="ch"></div></div>')
            };
            $(".clear").val("");
        });

        $(document).on('click', '.subClass_add', function() {
//             console.log($(this));
            var putunit = "<input type='text'>"
            $(this).parent().children("div").append('<div><input id="unitClass" type="text" size="30" "><button type=\"button\" style=\"height:28px ;padding: 3px; margin-left:5px;\" class=\"btn_del btn btn-primary \"\" >移除</button></div>');
        });			 

        //鎖定按鈕
        
        $(document).on("click", "button.lock", function () {
//             console.log("123");
//             console.log(this);
//             	if ($("input.mainClass_name").val() != "") 
//             if($("input.unitClass").val() != ""){


                $(this).parent().find("input,button").attr("disabled",true)
    
                let chapterClass = $(this).prev().prev().val();
//                 console.log($(this).parent().first().val());
                let unitArray = $(this).parent().find("div input");
                let unitArrayLength = unitArray.length;
//                  let test = $(this).parent().find("div input");
            
                console.log(chapterClass);
//                 console.log(unitArray);
//                 console.log(unitArrayLength);
//                 console.log(test);
           

                var unitClass = ""
                for (var i = 0; i < unitArrayLength; i++) {
//                     console.log(unitArray[i].value);
                    unitClass += unitArray[i].value + "不"
                }
//                 console.log(unitClass);

                var data = {
                     "action": "class3Form",
                     "unitClass_name": unitClass,
                     "chapterClass_name": chapterClass
                };
                $.ajax({
                    url: "<%=request.getContextPath()%>/class_info/class_infoServlet",
                    type: "POST",                  // GET | POST | PUT | DELETE | PATCH
                    data: data               // 傳送資料到指定的 url
                })

//             }
        });
        
        //章節刪除
        $(document).on("click", "button.chapter_del", function () {
            console.log("222");
            console.log(this);
            $(this).parent().remove();
        });
        //單元個別刪除
        $(document).on("click", "button.btn_del", function () {
            console.log("111");
            console.log(this);
            $(this).closest("div").remove();
        });
        
    </script>
			
</body>

</html>