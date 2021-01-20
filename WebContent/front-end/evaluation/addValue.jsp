<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.evaluation.model.*"%>

<%
	EvaluationVO evaluationVO = (EvaluationVO) request.getAttribute("evaluationVO");
%>

<!DOCTYPE HTML>
<html>

<head>
<meta charset="UTF-8">
<title>留言</title>

<style type="text/css">
* {
	margin-left: 0;
	padding: 0;
}

body, input {
	font-size: 14px;
	line-height: 24px;
	color: #333;
	font-family: Microsoft yahei, Song, Arial, Helvetica, Tahoma, Geneva;
}

h1 {
	/* 1px 的框線、實線、黑色 */
	border: 1px solid black;
	/* 內容與框線的上、右、下、左，距離都是 100px */
	padding: 10px 20px;
	/* 框線與上、右、下、左，*/
	margin: 50px 60px 30px 60px;
}

#content #post, #comment p {
	zoom: 1;
}

#content #post:after, #comment p:after {
	display: block;
	height: 0;
	clear: both;
	visibility: hidden;
	overflow: hidden;
	content: '.';
}

.transition {
	-webkit-transition: all 0.5s linear;
	-moz-transition: all 0.5s linear;
	-o-transition: all 0.5s linear;
	-ms-transition: all 0.5s linear;
	transition: all 0.5s linear;
}

#content {
	margin: 0 auto;
	width: 960px;
	overflow: hidden;
}

#content #post {
	margin-bottom: 15px;
	padding-bottom: 15px;
	border-bottom: 1px #d4d4d4 dashed;
	height: 556px;
}

#content #post textarea {
	display: block;
	margin-bottom: 10px;
	padding: 5px;
	width: 948px;
	height: 390px;
	border: 1px #d1d1d1 solid;
	border-radius: 5px;
	resize: none;
	outline: none;
}

#content #post textarea:hover {
	border: 1px #9bdf70 solid;
	background: #f0fbeb;
}

#content #post #postBt, #content #post #clearBt {
	margin-left: 5px;
	padding: 3px;
	float: right;
}

#comment {
	overflow: hidden;
}

#comment p {
	margin-bottom: 10px;
	padding: 10px;
	border-radius: 5px;
}

#comment p:nth-child(odd) {
	border: 1px solid #e3e197;
	background: #ffd;
}

#comment p:nth-child(even) {
	border: 1px solid #adcd3c;
	background: #f2fddb;
}

/*#comment p span{display:inline; float:left;}*/
#comment p .right {
	text-align: right;
}

#comment p .msg {
	width: 738px;
}

#comment p .datetime {
	width: 200px;
	color: #999;
	text-align: right;
}
</style>

</head>

<body>
	<h1>評價留言</h1>

	<div id="content">
		<div id="comment">
			<!-- 資料位置 -->


		</div>


		<div id="post">
			<div style="background: #3EADC5; height: 30px;">
				<td><input type="submit" id="shangtian" name="Submit3"
					style="border: none; background-color: #3EADC5; color: white;"
					value="請輸入文字" onclick="prom()" /></td>

			</div>



			<div>
				<input type="text" id="test">
			</div>
			<input id="postBt" type="button"
				style="border: none; background-color: #3EADC5; color: white; border-radius: 5px; width: 80px; height: 30px;"
				value="發表留言" /> <input id="clearBt" type="button"
				style="border: none; background-color: #3EADC5; color: white; border-radius: 5px; width: 80px; height: 30px;"
				value="清空" />
		</div>




	</div>
	
  
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script>
        var named;

       	
        
      	//AJAX 取出資料
      	window.onload=function(){
            var data = {
                    "action": "getAll",
                    "class_id": "CLA00001"
                };
            
            let json_data = JSON.stringify(data);
            
            $.ajax({
                url: "<%=request.getContextPath()%>/Evaluation/EvaluationServlet",
                type: "POST",                  // GET | POST | PUT | DELETE | PATCH
                data: json_data,               // 傳送資料到指定的 url
                dataType:"json",
                success: function (data) {      // request 成功取得回應後執行
                	
               	$.each(data, function(index, item){

                    var dataHtml = ""
                    dataHtml +="<span style>"
                    dataHtml +=    "<span style=\"float:left\">"+item["member_id"]+"</span>"
                    dataHtml +=    "<span style=\"float:right\">"+item["evaluation_time"]+"</span>"
                    dataHtml +=        "文字"
                   	dataHtml +=   	 "<p>"
                    dataHtml +=    		"<span class=\"msg\">"          
                    dataHtml +=     	item["evaluation_class"]+"</span>"
                    dataHtml +=    	"</p>"
                    dataHtml += "</span>"
                   	$("#comment").append(dataHtml);
                    
               	})
              }
            })
		}
      	
      	
		/****************************************************************************/	
        //AJAX 新增
        document.getElementById("postBt").addEventListener("click", function () {
            //取輸入資料
            var evaluation_class = document.getElementById("test").value;
            console.log("成功送出");
            var data = {
                "action": "insert",
                "class_id": "CLA00001",  //${class_info.class_id}
                "member_id": "MEM00001",
                "evaluation_class": evaluation_class,
                "evaluation_score": "1",
                "evaluation_status":"1"
            };
            
			let json_data = JSON.stringify(data);

            $.ajax({
                url: "<%=request.getContextPath()%>/Evaluation/EvaluationServlet",
                type: "POST",                  // GET | POST | PUT | DELETE | PATCH
                data: json_data,               // 傳送資料到指定的 url
                dataType:"json",
                success: function (e) {      // request 成功取得回應後執行

                    var dataHtml = ""
                    dataHtml +="<span style>"
                    dataHtml +=    "<span style=\"float:left\">"+e["member_id"]+"</span>"
                    dataHtml +=    "<span style=\"float:right\">"+e["evaluation_time"]+"</span>"
                    dataHtml +=        "暱稱"
                   	dataHtml +=   	 "<p>"
                    dataHtml +=    		"<span class=\"msg\">"      
                    dataHtml +=     	e["evaluation_class"]	+"</span>"
                    dataHtml +=    	"</p>"
                    dataHtml +=	"</span>"
                    dataHtml +="</span>"
                    
                    $("#comment").append(dataHtml);
                    $("#test").val("");// 留言的資料送出後清空
                }
            })

        });
        
//              清除鍵         
        $("#clearBt").on("click", function(){
                 $("#test").val("");
         });


    </script>
</body>

</html>