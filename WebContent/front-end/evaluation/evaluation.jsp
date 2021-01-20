<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.evaluation.model.*" %>

<% EvaluationVO evaulationVO=(EvaluationVO) request.getAttribute("evaulationVO"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>評價</title>

   <style>
   /* 留言的框線 */
    .messageline {
            border: 1px solid black;
            padding: 10px 30px;
            margin: 50px 60px 30px 60px;
        }
   
   .messageline1 {       
            border: 1px solid black;
            padding: 10px 20px;           
            margin: 20px 20px 30px 20px;
        }
   
    

    div.cr2-btn {
            padding-left: 840px;
        }
        
    div.task_add_block input.task_name{
		    width: calc(100% - 50px);
		    border: 1px solid lightgray;
		    border-radius: 3px 0 0 3px;
		    height: 40px;
		    font-size: 2rem;
		    padding: 5px 10px;
		    outline: none;
		    display: inline-block;
		    vertical-align: top;
  }  
   
   /* ===== 重要性的星號 ===== */
		div.star_block{
		  display: inline-block;
		  position: relative;
		}
		div.temp_loading{
		  position: absolute;
		  top: 0;
		  left: 0;
		  width: 100%;
		  height: 100%;
		  background-color: hsla(0, 0%, 0%, .1);
		  text-align: center;
		  z-index: 2;
		  color: white;
		}
		div.temp_loading > span{
		  display: inline-block;
		  position: absolute;
		  left: 50%;
		  top: 50%;
		  transform: translate(-50%, -50%);
		}
		div.star_block > span.star{
		  cursor: pointer;
		  display: inline-block;
		  margin-right: 3px;
		}
		div.star_block > span.star.-on{
		  color: yellow;
		}
   
   
   
   
   </style>
                





</head>
<body>

	<div class="messageline">
		<h1>評價留言</h1>
		<div class="messageline1">
			<div class="mainpic">
				<img src="./img/樹懶.jpg" class="mr-3" alt="...">
				<div class="mainpic-body">
					<h5 class="mt-0">會員暱稱</h5>
					<p>顯示留言</p>
					<div class="task_add_block">
						<input type="text" id="task_name" placeholder="輸入評價留言…">

					</div>

				</div>
			</div>
		</div>
		<div class=cr2-btn>
			<button type="button" class="btn_delete">清除</button>
			<button type="button" id="btn_send">發送</button>

		</div>

	</div>

	<script
		src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/video-js/js/video.js"></script>
	<script>
		   //AJAX 新增
		     document.getElementById("btn_send").addEventListener("click", function () {
		         //取輸入資料
		         var evaluation_class = document.getElementById("task_name").value;
		         var named;
		         var data = {
		             "action": "insert",
		             "class_id": "${evaluationVO.class_id}",  //${class_info.class_id}
		             "member_id": "${evaluationVO.member_id}",
		             "evaluation_class": "${evaluationVO.evaluation_class}",
		             "evaluation_score": "課程分數",
		             "evaluation_status":"${evaluationVO.evaluation_status}"
		         };
				let json_data = JSON.stringify(data);
	             $.ajax({
	                 url: "<%=request.getContextPath()%>/Evaluation/EvaluationServlet",
	                 type: "POST",                  // GET | POST | PUT | DELETE | PATCH
	                 data: json_data,               // 傳送資料到指定的 url
	                 dataType:"json",
	                 success: function (data) {      // request 成功取得回應後執行
	                     console.log(data);
	          //            console.log("xxxxx");
	                     var dataHtml = ""
	                    
	                     dataHtml +="<span style>"
	                     dataHtml +=    "<span style=\"float:left\">"+data.member_id+"</span>"
	                     dataHtml +=    "<span style=\"float:right\">"+data.evaluation_time+"</span>"
	                     dataHtml +=        "文字"
	                    	dataHtml +=   	 "<p>"
	                     dataHtml +=    		"<span class=\"msg\">"          
	                     dataHtml +=        	"<input style=\"float : right; border : none; border-radius : 5px;\" id=\"clearBt\"type=\"button\" value=\"刪除\">"
	                     dataHtml +=     	evaluation_class+"</span>"
	                     dataHtml +=    	"</p>"
	                     dataHtml +=	"</span>"
	                     dataHtml +="</span>"
	                     
	                     $("btn_delete").append(dataHtml);
	                     $("#test").val("");// 留言的資料送出後清空
	
	                 } //success
	             }) //ajax
	     }) //click
		//   清除鍵         
		     $("btn_delete").on("click", function(){
		              $("task_name").val("");
		      })

   </script>
</body>
</html>