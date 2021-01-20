<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.evaluation.model.*"%>
<!DOCTYPE html>


<% EvaluationVO evaluationVO = (EvaluationVO) request.getAttribute("evaluationVO"); %>




<html>
<head>

<title>評價</title>

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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>

<body bgcolor='white'>

<table id="table-1">
<tr><td>
		 <h3>評價編號</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/evaluation/select_pageEvaluation.jsp"><img src="<%=request.getContextPath()%>/back-end/evaluation/images/SeaOtterWait.jpg" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
	    <th>評價編號</th>
		<th>課程編號</th>
		<th>會員編號</th>
		<th>課程評價</th>
		<th>評價分數</th>
		<th>評價時間</th>
		<th>評價顯示狀態</th>
	</tr>

<tr>
		<td><%=evaluationVO.getEvaluation_id()%></td> 
		<td><%=evaluationVO.getClass_id()%></td>
		<td><%=evaluationVO.getMember_id()%></td>
		<td><%=evaluationVO.getEvaluation_class	()%></td>
		<td><%=evaluationVO.getEvaluation_score()%></td>
		<td><%=evaluationVO.getEvaluation_time()%></td>
		<td><%=evaluationVO.getEvaluation_status()%></td>
	</tr>
	<tr>
          <td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/evaluation/EvaluationServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="evaluation_id"  value="${evaluationVO.evaluation_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/evaluation/EvaluationServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="evaluation_id"  value="${evaluationVO.evaluation_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
	</tr>
</table>
</body>
</html>