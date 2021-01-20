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


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�|�����</title>
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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>
	<table id="table-1">
		<tr>
			<td>
				<h3>�|����Ʒs�W</h3>
			</td>
			<td>
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/member_info/select_page.jsp"><img
						src="<%=request.getContextPath()%>/back-end/member_info/images/tomato.gif" width="100"
						height="100" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>��Ʒs�W:</h3>


	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>


	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member_info/Member_infoServlet"
		enctype="multipart/form-data" name="form1">
		<table>


			<tr>
				<td>�|���W��:</td>
				<td><input type="TEXT" name="member_name" size="45"
					value="<%=(member_infoVO == null) ? "test" : member_infoVO.getMember_name()%>" /></td>
			</tr>

			<tr>
				<td>�|���H�c:</td>
				<td><input type="TEXT" name="member_email" size="45"
					value="<%=(member_infoVO == null) ? "@gmail.com" : member_infoVO.getMember_email()%>" /></td>
			</tr>
			<tr>
				<td>�|���K�X:</td>
				<td><input type="TEXT" name="member_password" size="45"
					value="<%=(member_infoVO == null) ? "" : member_infoVO.getMember_password()%>" /></td>
			</tr>
			<tr style="display: none;">
				<td>���L�Ѯv����:</td>
				<td><select name="member_role" id="mem-role">
						<option value="0">�Ⱦǥͨ���</option>
						<option value="1">���Ѯv����</option>
				</select></td>
			</tr>
			<tr>
				<td>�|���ʧO:</td>
				<td><select name="member_gender" id="mem-gender">
						<option value="0">�k��</option>
						<option value="1">�k��</option>
						<option value="2">�h���ʧO</option>
				</select></td>
			</tr>
			<tr style="display: none;">
				<td>�|���ͤ�:</td>
				<td><input name="member_birthday" id="f_date2" type="text">
				</td>
			</tr>
			<tr style="display: none;">
				<td>�|��¾�~:</td>
				<td><input type="TEXT" name="member_occupation" size="45"
					value="<%=(member_infoVO == null) ? "" : member_infoVO.getMember_occupation()%>" /></td>
			</tr>
			<tr style="display: none;">
				<td>�|���a�}:</td>
				<td><input type="TEXT" name="member_address" size="45"
					value="<%=(member_infoVO == null) ? "" : member_infoVO.getMember_address()%>" /></td>
			</tr>
			<tr style="display: none;">
				<td>����s��:</td>
				<td><input type="TEXT" name="member_invoice" size="45"
					value="<%=(member_infoVO == null) ? "" : member_infoVO.getMember_invoice()%>" /></td>
			</tr>
			<tr style="display: none;">
				<td>�|���Y��:</td>
				<td><input type="file" name="member_pic" size="45"
					value="<%=(member_infoVO == null) ? "null" : member_infoVO.getMember_pic()%>" /></td>
			</tr>
			<tr style="display: none;">
				<td>�}��}�ҹw��:</td>
				<td><select name="teachclass_on" id="teach-on">
						<option value="0">����</option>
						<option value="1">�}��</option>

				</select></td>
			</tr>


			<tr style="display: none;">
				<td>�}��׽ҹw��:</td>
				<td><select name="learnclass_on" id="learn-on">
						<option value="0">����</option>
						<option value="1">�}��</option>

				</select></td>
			</tr>

			<tr style="display: none;">
				<td>�}��@�~�w��:</td>
				<td><select name="member_homework" id="homework">
						<option value="0">����</option>
						<option value="1">�}��</option>

				</select></td>
			</tr>

			<tr style="display: none;">
				<td>�����:</td>
				<td><input type="TEXT" name="member_about" size="45"
					value="<%=(member_infoVO == null) ? "" : member_infoVO.getMember_about()%>" /></td>
			</tr>
			<tr style="display: none;">
				<td>�M��:</td>
				<td><input type="TEXT" name="member_good_for" size="45"
					value="<%=(member_infoVO == null) ? "" : member_infoVO.getMember_good_for()%>" /></td>
			</tr>

			<tr style="display: none;">
				<td>���U�ɶ�:</td>
				<td><input name="register_time" id="f_date1" type="text">
				</td>
			</tr>
			<tr style="display: none;">
				<td>��Ƨ�s�ɶ�:</td>
				<td><input name="member_update" id="f_date3" type="text">
				</td>
			</tr>

			<tr style="display: none;">
				<td>�״ڱb��:</td>
				<td><input type="TEXT" name="traccount" size="45"
					value="<%=(member_infoVO == null) ? "" : member_infoVO.getTraccount()%>" /></td>
			</tr>
			<tr style="display: none;">
				<td>�Ȧ�N��:</td>
				<td><input type="TEXT" name="bank_code" size="45"
					value="<%=(member_infoVO == null) ? "" : member_infoVO.getBank_code()%>" /></td>
			</tr>


		</table>


		<br> <input type="hidden" name="action" value="insert_user"> <input
			type="submit" value="�e�X�s�W">
	</FORM>


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
</script>
<script>
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
	
	
	
</script>




</body>
</html>