<%@page import="java.sql.Time"%>
<%@page import="java.sql.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.member_info.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
	Member_infoVO member_infoVO = (Member_infoVO) request.getAttribute("member_infoVO");
%>


<html lang="en">

<head>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
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


        #preview {
            border: 1px solid lightgray;
            display: inline-block;
            /* width: 100%;
            height: auto; */
            width: 150px;
            height: 200px;
            position: relative;
        }

        #preview span.text {
            position: absolute;
            display: inline-block;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            z-index: -1;
            color: lightgray;
        }

        label {
            margin-top: 10px;
        }

        .form-check-label {
            margin-top: 0;
            padding-left: 0;
        }

        select {
            height: 30px;
            padding: 1px 15px;
        }

        select.form-control,
        input.form-control {
            display: inline-block;
        }

        #submit-btn {
            margin-top: 20px;
        }

        .form-check-input {
            margin-left: -0.9rem;
        }

        #output_image {
            width: 150px;
            height: 200px;
        }
    </style>
    <title>TOMATO - ��O�޲z</title>
</head>

<body>
<header>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">TOMATO</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">���� <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">�ڪ��ҵ{</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            	�ҵ{���
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="#">�y��</a>
                            <a class="dropdown-item" href="#">��v</a>
                            <a class="dropdown-item" href="#">�]�p</a>
                            <a class="dropdown-item" href="#">�H��</a>
                            <a class="dropdown-item" href="#">��P</a>
                            <a class="dropdown-item" href="#">�{��</a>
                        </div>
                    </li>
                    <li>
                        <form class="form-inline my-2 my-lg-0" style="position:left">
                            <input class="form-control mr-sm-2 form-control" type="search" placeholder="��J�A�Q�n���ҵ{"
                                aria-label="Search">
                            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                        </form>
                    </li>
                </ul>
                <!-- �|���U�Կ�� -->
                <div class="dropdown">
                    <img data-toggle="dropdown" src="https://pic.90sjimg.com/design/00/08/16/10/591fa3911d5ee.png"
                        alt="XX" style="height: 30px; margin-right:20px ;">
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">�b���]�w</a>
                        <a class="dropdown-item" href="#">�ӤH�ɮ�</a>
                        <a class="dropdown-item" href="#">�q�����</a>
                        <a class="dropdown-item" href="#">�ڪ��u�f</a>
                        <a class="dropdown-item" href="#">�n�X</a>
                    </div>
                </div>


                <img src="https://i-1.lanrentuku.com/2020/11/9/7d9cf2fd-ed76-466a-9012-51b017c46025.png" alt="XX"
                    style="height: 30px; margin-right:20px ;">

                <img src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-ios7-bell-512.png" alt="XX"
                    style="height: 30px; margin-right:20px ;">

            </div>
        </nav>
    </header>
	
	
	<div class="container">
		<div class="jumbotron jumbotron-fluid">
            <div class="container">
                <h1>�|����Ʒs�W</h1>
                <h4>
					<a href="<%=request.getContextPath()%>/back-end/member_info/select_page.jsp">�|���޲z</a>
				</h4>
            </div>
        </div>
   
	
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">�Эץ��H�U���~:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
	
		<div id="content">
			<FORM class="g-3" METHOD="post" ACTION="<%=request.getContextPath()%>/member_info/Member_infoServlet"
				enctype="multipart/form-data" name="form1">
				<div class="row">
					<div class="col-lg-2 col-sm-4 col-12">
                        <div id="preview"><span class="text"><img id="output_image"></span></div>
                        <input type="file" id="file" name="member_pic" 
                        value="<%=(member_infoVO == null) ? "null" : member_infoVO.getMember_pic()%>"
                        onchange="preview_image(event)" style="display:none">
                        <label for="file" class="form-control" style="cursor: pointer; width: 150px;">
                            	�ФW�ǷӤ�
                            <img src="<%=request.getContextPath()%>/back-end/member_info/images/2089052.png" alt="" width=16 height=16 style="margin-left: 10px; margin-top: -4px;">
                        </label>
                    </div>
                    <div class="col-lg-10 col-sm-8 col-12">

                        <div class="col-12 row">

                            <div class="col-5">
                                <label for="inputPassword1" class="form-label">�|���W��</label>
                                <input type="text" class="form-control" id="inputPassword1" name="member_name" 
                                value="<%=(member_infoVO == null) ? "Nick" : member_infoVO.getMember_name()%>">
                            </div>
                            <div class="col-5">
                                <label for="inputEmail4" class="form-label">Email</label>
                                <input type="TEXT" class="form-control" id="inputEmail4" name="member_email"
                                value="<%=(member_infoVO == null) ? "@gmail.com" : member_infoVO.getMember_email()%>">
                            </div>
                        </div>

                        <div class="col-12 row">

                            <div class="col-5">
                                <label for="inputPassword2" class="form-label">�|���K�X</label>
                                <input type="TEXT" class="form-control" id="inputPassword2" name="member_password"
                                value="<%=(member_infoVO == null) ? "111111111" : member_infoVO.getMember_password()%>">
                            </div>
                            <div class="col-5">
                           		<div>
	                                <label for="mem-role">�|������</label>
                            	</div>
                                <select name="member_role" id="mem-role" class="form-control">
									<option value="0">�Ⱦǥͨ���</option>
									<option value="1">���Ѯv����</option>
								</select>
                            </div>
                        </div>

                        <!-- �ʧO�B�ͤ� -->
                        <div class="col-12 row">
                            <div class="col-5">
                                <div>
                                    <label for="mem-gender">�ʧO</label>
                                </div>
                                <select name="member_gender" id="mem-gender" class="form-control">
                                    <option value="0">�k��</option>
                                    <option value="1">�k��</option>
                                    <option value="2">�h���ʧO</option>
                                </select>
                            </div>
                            <div class="col-5">
                                <div>
                                    <label for="f_date2">�ͤ�</label>
                                </div>
                                <input name="member_birthday" id="f_date2" type="text" class="form-control">
                              
                            </div>
                        </div>

                        <!-- ¾�~ -->
                        <div class="col-5">
                            <div>

                                <label for="job">¾�~</label>
                            </div>
                            <div>
                                <input type="TEXT" name="member_occupation" id="job" class="form-control"
									value="<%=(member_infoVO == null) ? "�ǥ�" : member_infoVO.getMember_occupation()%>">
                            </div>
                        </div>

                        <!-- �a�} -->
                        <div class="col-12">
                            <div>
                                <label for="addr">�a�}</label>
                            </div>
                            
                            <input type="text" id="addr" name="member_address" placeholder="��J�a�}" class="form-control" value="<%=(member_infoVO == null) ? "�x�_�����s��" : member_infoVO.getMember_address()%>">
                        </div>

                        <!-- �״ڱb������ -->
                        <div class="col-12 row">
	                        <div class="col-4">
	                            <div>
	                                <label for="code">����</label>
	                            </div>
	                            <input type="text" id="code"
	                            name="member_invoice" placeholder="��J����" class="form-control" value="<%=(member_infoVO == null) ? "/1234567" : member_infoVO.getMember_invoice()%>">
	                        </div>
                            <div class="col-4">
                                <div>
                                    <label for="bank">�Ȧ�N��</label>
                                </div>
                                <input type="text" id="bank" name="bank_code" class="form-control"
									value="<%=(member_infoVO == null) ? "104" : member_infoVO.getBank_code()%>" />

                            </div>
                            <div class="col-4">
                                <div>
                                    <label for="account">�״ڱb��</label>
                                </div>
                                <input type="text" placeholder="��J�״ڱb��" id="account" name="traccount" class=" form-control" value="<%=(member_infoVO == null) ? "111111111111" : member_infoVO.getTraccount()%>">
                            </div>

                        </div>
                        
                        
                        <div class="col-12 row">
                            <div class="col-4">
                                <label for="teach-on">�}��}�ҹw��</label>
                                <select name="teachclass_on" id="teach-on" class="form-control">
                                    <option value="0">����</option>
                                    <option value="1">�}��</option>
            
                                </select>

                            </div>
                            <div class="col-4">
                                <label for="">�}��׽ҹw��</label>
                                <select name="learnclass_on" id="learn-on" class="form-control">
                                    <option value="0">����</option>
                                    <option value="1">�}��</option>
            
                                </select>

                            </div>
                            <div class="col-4">
                                <label for="">�}��@�~�w��</label>
                                <select name="member_homework" id="homework" class="form-control">
                                    <option value="0">����</option>
                                    <option value="1">�}��</option>
            
                                </select>
                            </div>
                        </div>
                        


                        <div class="col-12 row">

                            <div class="col-6">
                                <label for="goodfor">�M��</label>
                                <input type="text" id="goodfor"name="member_good_for" class="form-control"
                                        value="<%=(member_infoVO == null) ? "JAVA" : member_infoVO.getMember_good_for()%>">
                            </div>
                            <div class="col-6">
                                <label for="about">�����</label>
                                <textarea id ="about" class="form-control" name="member_about"><%=(member_infoVO == null) ? "����ڪ����e" : member_infoVO.getMember_about()%>
                                </textarea>
                            </div>
                        </div>
                        <div class="col-12 row">
                            <div class="col-6">
                                <label for="f_date1">���U�ɶ�</label>
                                <input name="register_time" id="f_date1" type="text" class="form-control">

                            </div>
                            <div class="col-6">
                                <label for="f_date3">��Ƨ�s�ɶ�</label>
                                <input name="member_update" id="f_date3" type="text" class="form-control">

                            </div>

                        </div>

                   

                        <div class="col-12">
                            <input type="hidden" name="action" value="insert">
                            <button id="submit-btn" type="submit" class="btn btn-primary col-4">�e�X�s�W</button>
                        </div>


                    </div>
				
				</div>
		
			</FORM>
			
		</div>
	              
     </div>


</body>
<%
	Date member_birthday = null;
	try {
		member_birthday = member_infoVO.getMember_birthday();
	} catch (Exception e) {
		member_birthday = new Date(System.currentTimeMillis());
	}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.css" />
<script
	src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
	$.datetimepicker.setLocale('zh'); // kr ko ja en
	$('#f_date1').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : true, //timepicker: false,
		step : 1, //step: 60 (�o�Otimepicker���w�]���j60����)
		format : 'Y-m-d H:i:s',
		value : new Date(),
	});
	$('#f_date2').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker: false,
		//	step : 1440, //step: 60 (�o�Otimepicker���w�]���j60����)
		format : 'Y-m-d',
		value : new Date(),
	});
	$('#f_date3').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : true, //timepicker: false,
		step : 1, //step: 60 (�o�Otimepicker���w�]���j60����)
		format : 'Y-m-d H:i:s',
		value : new Date(),
	});

	// �U�Ԧ�����ܾ�(selector Id, data �̭�����)
	function setSelectorValue(target, targetValue) {
		var targetSelctor = document.getElementById(target);
		for (var i = 0; i < targetSelctor.options.length; i++) {
			if (parseInt(targetSelctor.options[i].getAttribute("value"), 10) === targetValue) {
				targetSelctor.options[i].setAttribute("selected", true);
			} else {
				targetSelctor.options[i].removeAttribute("selected");
			}
		}
	}
	// �ˬd�O�_�����(java)
	var memberDataExist1 =<%=member_infoVO != null%>;
    <%Integer mem_role = 0;
			if (member_infoVO != null) {
				mem_role = (member_infoVO.getMember_role() != null) ? member_infoVO.getMember_role() : 0;
			}%>
	// �p�G����� set data(js)
	if (memberDataExist1) {
		setSelectorValue("mem-role",<%=mem_role%>);
	}

	// �ˬd�O�_�����(java)
	var memberDataExist2 =<%=member_infoVO != null%>;
	<%Integer mem_gender = 0;
			if (member_infoVO != null) {
				mem_gender = (member_infoVO.getMember_gender() != null) ? member_infoVO.getMember_gender() : 0;
			}%>
	// �p�G����� set data(js)
	if (memberDataExist2) {
		setSelectorValue("mem-gender",<%=mem_gender%>);
	}

	// �ˬd�O�_�����(java)
	var memberDataExist3 =<%=member_infoVO != null%>;
	<%Integer teach_on = 0;
			if (member_infoVO != null) {
				teach_on = (member_infoVO.getTeachclass_on() != null) ? member_infoVO.getTeachclass_on() : 0;
			}%>
	// �p�G����� set data(js)
	if (memberDataExist3) {
		setSelectorValue("teach-on",<%=teach_on%>);
	}

	// �ˬd�O�_�����(java)
	var memberDataExist4 =<%=member_infoVO != null%>;
	<%Integer learn_on = 0;
			if (member_infoVO != null) {
				learn_on = (member_infoVO.getLearnclass_on() != null) ? member_infoVO.getLearnclass_on() : 0;
			}%>
	// �p�G����� set data(js)
	if (memberDataExist4) {
		setSelectorValue("learn-on",<%=learn_on%>);
	}
	// �ˬd�O�_�����(java)
	var memberDataExist5 =<%=member_infoVO != null%>;
	<%Integer mem_homework = 0;
			if (member_infoVO != null) {
				mem_homework = (member_infoVO.getMember_homework() != null) ? member_infoVO.getMember_homework() : 0;
			}%>
	// �p�G����� set data(js)
	if (memberDataExist5) {
		setSelectorValue("homework",<%=mem_homework%>);
	}
	function preview_image(event) {
        var reader = new FileReader();
        reader.onload = function () {
            var output = document.getElementById('output_image');
            output.src = reader.result;
        }
        reader.readAsDataURL(event.target.files[0]);
    }
	
	
</script>




</body>
</html>