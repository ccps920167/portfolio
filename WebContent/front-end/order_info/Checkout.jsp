<%@page import="com.sub_class.model.Sub_classVO"%>
<%@page import="com.class_unit.model.Class_unitVO" %>
<%@page import="com.class_unit.model.Class_unitService" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="java.util.*" %>
<%@page import="com.class_info.model.*" %>

<% 
List<String>amounttest=(List<String>) session.getAttribute("amounttest");
List<String>errory=(List<String>)session.getAttribute("errory");
String amount=(String)session.getAttribute("amount");
LinkedList<Class_infoVO>shoppingcart=(LinkedList<Class_infoVO>)session.getAttribute("shoppingcart");
Integer amount3=(Integer)session.getAttribute("amount3");
%>

<head>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
<style>

/* 套用全部 */
* {
	box-sizing: border-box;
}



ul.car {
	background-color: rgb(235, 235, 235);
	background-clip: content-box;
	/* border:  solid black; */
}

.total-amount {
	display: inline-block;
	padding: 0;
	right: 80px;
	bottom: 130px;
	border: 2px rgb(175, 175, 175) solid;
	position: fixed;
	background-color: white;
	width: 200px;
	height: 200px;
	border-radius:10px;
}

ul.car li.item_detail {
	display: inline;
	margin-left: 10px;
}

ul.car li.price {
	padding-left: 50%;
	display: inline;
}

ul.car li.count {
	padding-left: 12%;
	display: inline;

	/* width: 30%; */
}

ul.car li.amount {
	display: inline;
	/* margin-left: 50px; */
	margin-right: 20px;
	position: absolute;
	right: 0px;
}

div.container h1 img {
	transform: translateY(-5px);
}

ul.dcc {
	display: flex;
	background: blanchedalmond;
	padding: 0;
	border: 2px solid black;
	padding: 20px;
}
li.price1, li.count1, li.amount1, li.amount2, li.pic-class {
	display: inline-block;
	margin: 0 auto 10px auto;
}

.a {
	text-align: center;
	margin-top: 20px;
	margin-bottom: 20px;
	background-color: rgba(241, 173, 94, 0.897);
	width: 100px;
	margin-left: 50px;
	border-radius: 3px;
	
}

.b {
	text-align: center;
}

.c {
	margin-left: 50px;
	background-color: rgba(245, 108, 84, 0.897);
}

.table {
	background-color: #e9ecef;
	padding: 10px;
}

div.frame {
	border: rgb(65, 65, 64);
}

.table td {
	vertical-align: middle;
}
.image {
width:120px;
height:80px;
}
li{
list-style:none;
}
div.container1{
border: 2px rgb(175, 175, 175) solid;
background-color: rgba(241, 173, 94, 0.897);
} 
div.container2{
border: 2px rgb(175, 175, 175) solid;
background-color: rgba(241, 173, 94, 0.897);
} 




</style>
<!-- css -->
<link
	href="<%=request.getContextPath()%>/css/header.css"
	rel="stylesheet" />		
<title>TOMATO - 讓你分分鐘鐘都在學習的平台</title>
</head>
<body>
	<!-- header -->
	<%@ include file="header.jsp"%>

	<!-- container -->

	<div class="container">
		<!-- 標題 -->
		<FORM method="post" action="<%=request.getContextPath()%>/order_info/CheckoutServlet">
		<div class="jumbotron ">
			<div class="container1">
				<ul>
		<c:if test="${not empty error}">
		<font style="color: red"></font>
		<ul>
			<c:forEach var="message" items="${error}">
				<li style="color: red"><b>${message}</b></li>
			</c:forEach>
		</ul>
		</c:if>
					<li><font size=5 margin-right=30px; >付款方式:</font></li>
					
					<li>
						<select id="selected" name="pay_way" style="width: 300px" onchange="show()">
							<option  value="信用卡">信用卡</option>
				 			<option  value="匯款">匯款</option>
						</select>
						<img src="<%=request.getContextPath()%>/img/icon/cash1.png" >
						<img src="<%=request.getContextPath()%>/img/icon/cash2.png" style="height:30px">
						<img src="<%=request.getContextPath()%>/img/icon/cash3.png" style="height:35px">
						<img src="<%=request.getContextPath()%>/img/icon/cash4.png" style="height:30px">			
					</li>
				</ul>
				<ul id="card">
					<li>信用卡卡號:      
				   		<input type=text name="pan_no1" size=4 value="<%=(errory==null)?"":3878%>" maxlength=4 onKeyUp="setBlur(this,'pan_no2');" style="margin-left:16px">-
    					<input type=text name="pan_no2" size=4 value="<%=(errory==null)?"":2938%>" maxlength=4 onKeyUp="setBlur(this,'pan_no3');">-
    					<input type=text name="pan_no3" size=4 value="<%=(errory==null)?"":8373%>" maxlength=4 onKeyUp="setBlur(this,'pan_no4');">-
    					<input type=text name="pan_no4" size=4 value="<%=(errory==null)?"":5637%>" maxlength=4>
    				</li>
					<li> </li>
    				<li>卡片有校期限:   
    					<input type=text  name="period" value="<%=(errory==null)?"MM":12%>" size=1>   
    					<input type=text  name="period1" value="<%=(errory==null)?"YYYY":2021%>" size=1>
    				</li>  
    				<li>信用卡安全碼:  
    					<input type=text size=3 name="Securityc" value="<%=(errory==null)?"":305%>">   
    					<img src="<%=request.getContextPath()%>/img/icon/cash5.png">
    				</li>
				</ul>
				<ul id="bank" hidden>
    				<li>銀行代號 匯款帳號:</li>
    				<li>						
    					<select  class="form-control col-3">
	                        <option>-請選擇-</option>
	                        <option value="004">004 - 臺灣銀行</option>
	                        <option value="005">005 - 土地銀行</option>
	                        <option value="006">006 - 合作商銀</option>
	                        <option value="007">007 - 第一銀行</option>
	                        <option value="008">008 - 華南銀行</option>
	                        <option value="009">009 - 彰化銀行</option>
	                        <option value="011">011 - 上海商業銀行</option>
	                        <option value="012">012 - 台北富邦銀行</option>
	                        <option value="013">013 - 國泰世華銀行</option>
	                        <option value="016">016 - 高雄銀行</option>
	                        <option value="017">017 - 兆豐商業銀行</option>
	                        <option value="018">018 - 農業金庫</option>
	                        <option value="021">021 - 花旗商業銀行</option>
	                        <option value="025">025 - 首都銀行</option>
	                        <option value="039">039 - 澳商澳盛銀行</option>
	                        <option value="040">040 - 中華開發工業銀行</option>
	                        <option value="050">050 - 臺灣企銀</option>
	                        <option value="052">052 - 渣打國際商業銀行</option>
	                        <option value="053">053 - 台中商業銀行</option>
	                        <option value="054">054 - 京城商業銀行</option>
	                        <option value="072">072 - 德意志銀行</option>
	                        <option value="075">075 - 東亞銀行</option>
	                        <option value="081">081 - 匯豐商業銀行</option>
	                        <option value="085">085 - 新加坡華僑銀行</option>
	                        <option value="101">101 - 大台北銀行</option>
	                        <option value="102">102 - 華泰銀行</option>
	                        <option value="103">103 - 臺灣新光商銀</option>
	                        <option value="104">104 - 台北五信</option>
	                        <option value="106">106 - 台北九信</option>
	                        <option value="108">108 - 陽信商業銀行</option>
	                        <option value="114">114 - 基隆一信</option>
	                        <option value="115">115 - 基隆二信</option>
	                        <option value="118">118 - 板信商業銀行</option>
	                        <option value="119">119 - 淡水一信</option>
	                        <option value="120">120 - 淡水信合社</option>
	                        <option value="124">124 - 宜蘭信合社</option>
	                        <option value="127">127 - 桃園信合社</option>
	                        <option value="130">130 - 新竹一信</option>
	                        <option value="132">132 - 新竹三信</option>
	                        <option value="146">146 - 台中二信</option>
	                        <option value="147">147 - 三信商業銀行</option>
	                        <option value="158">158 - 彰化一信</option>
	                        <option value="161">161 - 彰化五信</option>
	                        <option value="162">162 - 彰化六信</option>
	                        <option value="163">163 - 彰化十信</option>
	                        <option value="165">165 - 鹿港信合社</option>
	                        <option value="178">178 - 嘉義三信</option>
	                        <option value="179">179 - 嘉義四信</option>
	                        <option value="188">188 - 台南三信</option>
	                        <option value="204">204 - 高雄三信</option>
	                        <option value="215">215 - 花蓮一信</option>
	                        <option value="216">216 - 花蓮二信</option>
	                        <option value="222">222 - 澎湖一信</option>
	                        <option value="223">223 - 澎湖二信</option>
	                        <option value="224">224 - 金門信合社</option>
	                        <option value="512">512 - 雲林區漁會</option>
	                        <option value="515">515 - 嘉義區漁會</option>
	                        <option value="517">517 - 南市區漁會</option>
	                        <option value="518">518 - 南縣區漁會</option>
	                        <option value="520">520 - 高雄區漁會</option>
	                        <option value="521">521 - 永安區漁會</option>
	                        <option value="523">523 - 琉球區漁會</option>
	                        <option value="524">524 - 新港區漁會</option>
	                        <option value="525">525 - 澎湖區漁會</option>
	                        <option value="605">605 - 高雄市農會</option>
	                        <option value="612">612 - 豐原市農會</option>
	                        <option value="613">613 - 名間農會</option>
	                        <option value="614">614 - 彰化地區農會</option>
	                        <option value="616">616 - 雲林地區農會</option>
	                        <option value="617">617 - 嘉義地區農會</option>
	                        <option value="618">618 - 台南地區農會</option>
	                        <option value="619">619 - 高雄地區農會</option>
	                        <option value="620">620 - 屏東地區農會</option>
	                        <option value="621">621 - 花蓮地區農會</option>
	                        <option value="622">622 - 台東地區農會</option>
	                        <option value="624">624 - 澎湖農會</option>
	                        <option value="625">625 - 台中市農會</option>
	                        <option value="627">627 - 連江縣農會</option>
	                        <option value="700">700 - 中華郵政</option>
	                        <option value="803">803 - 聯邦商業銀行</option>
	                        <option value="805">805 - 遠東銀行</option>
	                        <option value="806">806 - 元大銀行</option>
	                        <option value="807">807 - 永豐銀行</option>
	                        <option value="808">808 - 玉山銀行</option>
	                        <option value="809">809 - 萬泰銀行</option>
	                        <option value="810">810 - 星展銀行</option>
	                        <option value="812">812 - 台新銀行</option>
	                        <option value="814">814 - 大眾銀行</option>
	                        <option value="815">815 - 日盛銀行</option>
	                        <option value="816">816 - 安泰銀行</option>
	                        <option value="822">822 - 中國信託</option>
	                        <option value="901">901 - 大里市農會</option>
	                        <option value="903">903 - 汐止農會</option>
	                        <option value="904">904 - 新莊農會</option>
	                        <option value="912">912 - 冬山農會</option>
	                        <option value="916">916 - 草屯農會</option>
	                        <option value="922">922 - 台南市農會</option>
	                        <option value="928">928 - 板橋農會</option>
	                        <option value="951">951 - 北農中心</option>
	                        <option value="954">954 - 中南部農漁會</option>
						</select>                         
                        <input type="text" placeholder="輸入匯款帳號"  name="account1" class="col-4 form-control">
                  	</li>                  
				</ul>	
						
			</div><br>
			
			<div class=container1>
			<ul><font size=5>發票類型:</font><br>
			以下資訊只用於開立發票，並不會在其他頁面顯示。發票一經開立後不可更改，請確認資訊是否都填寫正確喔！</ul>
			<ul>
				<li>姓名: <input type="text" name="name1" value="<%=(errory==null)?"":"江明哲"%>">  聯絡信箱:<input type="text" name="email" value="<%=(errory==null)?"":"aa556699aa@yaho.com.tw"%>"></li>
				
				
				<br>
				<li>發票類型:</li>
				<li>
					<select style="width:225px">
					 <option>電子發票</option>
				 	 <option>捐贈發票</option>
					</select>
					<select style="width:250px">
					 <option>儲存在TOMOTO,中獎後通知</option>
				 	 <option>手機條碼</option>
					</select>
				</li>
				</ul>
			</div>
		
		</div>
				<div class="col-3">

					<div class="total-amount">
						<div class="a">訂單明細</div>
						<div class="b"><b>小計$:<%=(amounttest == null)?amount:amount3%></b></div>
						<c:remove var="amounttest" />
						<div class="b"><a href="<%=request.getContextPath()%>/coupon/VoucherServlet?action=VoucherServlet">使用優惠卷</a></div>
						<br>
						<input type="hidden" name="action" value="ADD">
						<input type="submit" class="c  btn btn-danger" value="同意並送出">
				</FORM>		
				</div>
				</div>
				<FORM method="post" action="<%=request.getContextPath()%>/order_info/CheckoutServlet">
						<div>
						<input type="hidden" name="action" value="oneadd">
						<input type="submit" name="action" value="一鍵新增">
						</div>
				</FORM>
			</div>
		</div>
	</div>
	<!-- Footer -->
	<%@ include file="footer.jsp"%>

	<!-- JavaScript -->
	<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.5.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
<script>
function show(){

    var i=document.getElementById("selected").value;
    if(i=="信用卡"){
        console.log("信用卡");
        document.getElementById("card").hidden=false;
        document.getElementById("bank").hidden=true;

    }
    if(i=="匯款"){
        console.log("匯款");
        document.getElementById("card").hidden=true;
        document.getElementById("bank").hidden=false;
        
    }
}


</script>
	
    
</body>

</html>