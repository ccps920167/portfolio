<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.evaluation.model.*" %>

<% EvaluationVO evaulationVO=(EvaluationVO) request.getAttribute("evaulationVO"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>����</title>

   <style>
   /* �d�����ؽu */
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
   
   /* ===== ���n�ʪ��P�� ===== */
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
		<h1>�����d��</h1>
		<div class="messageline1">
			<div class="mainpic">
				<img src="./img/���i.jpg" class="mr-3" alt="...">
				<div class="mainpic-body">
					<h5 class="mt-0">�|���ʺ�</h5>
					<p>��ܯd��</p>
					<div class="task_add_block">
						<input type="text" id="task_name" placeholder="��J�����d���K">

					</div>

				</div>
			</div>
		</div>
		<div class=cr2-btn>
			<button type="button" class="btn_delete">�M��</button>
			<button type="button" id="btn_send">�o�e</button>

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
		   //AJAX �s�W
		     document.getElementById("btn_send").addEventListener("click", function () {
		         //����J���
		         var evaluation_class = document.getElementById("task_name").value;
		         var named;
		         var data = {
		             "action": "insert",
		             "class_id": "${evaluationVO.class_id}",  //${class_info.class_id}
		             "member_id": "${evaluationVO.member_id}",
		             "evaluation_class": "${evaluationVO.evaluation_class}",
		             "evaluation_score": "�ҵ{����",
		             "evaluation_status":"${evaluationVO.evaluation_status}"
		         };
				let json_data = JSON.stringify(data);
	             $.ajax({
	                 url: "<%=request.getContextPath()%>/Evaluation/EvaluationServlet",
	                 type: "POST",                  // GET | POST | PUT | DELETE | PATCH
	                 data: json_data,               // �ǰe��ƨ���w�� url
	                 dataType:"json",
	                 success: function (data) {      // request ���\���o�^�������
	                     console.log(data);
	          //            console.log("xxxxx");
	                     var dataHtml = ""
	                    
	                     dataHtml +="<span style>"
	                     dataHtml +=    "<span style=\"float:left\">"+data.member_id+"</span>"
	                     dataHtml +=    "<span style=\"float:right\">"+data.evaluation_time+"</span>"
	                     dataHtml +=        "��r"
	                    	dataHtml +=   	 "<p>"
	                     dataHtml +=    		"<span class=\"msg\">"          
	                     dataHtml +=        	"<input style=\"float : right; border : none; border-radius : 5px;\" id=\"clearBt\"type=\"button\" value=\"�R��\">"
	                     dataHtml +=     	evaluation_class+"</span>"
	                     dataHtml +=    	"</p>"
	                     dataHtml +=	"</span>"
	                     dataHtml +="</span>"
	                     
	                     $("btn_delete").append(dataHtml);
	                     $("#test").val("");// �d������ưe�X��M��
	
	                 } //success
	             }) //ajax
	     }) //click
		//   �M����         
		     $("btn_delete").on("click", function(){
		              $("task_name").val("");
		      })

   </script>
</body>
</html>