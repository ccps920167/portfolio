<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>通知</title>
</head>
<body>
    Welcome<br/><input id="text" type="text"/>
    <button οnclick="send()">發送消息</button>
    <hr/>
   
    發送人:<div id="admin_id">AI00001</div>
    接收人：<input type="text" id="member_id"><br>
    <button οnclick="closeWebSocket()">關閉WebSocket連接</button>
    <hr/>
    <div id="message"></div>



<script type="text/javascript">
    var websocket = null;
    var userno=document.getElementById('admin_id').innerHTML;
    //判断當前瀏覽器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8081/TEA102G5/back-end/post_message/index");
    } 
    else {
        alert('當前瀏覽器 不支援 websocket')
    }


    
    websocket.onerror = function () {
        setMessageInnerHTML("WebSocket連接發生错誤");
    };


    
    websocket.onopen = function () {
        setMessageInnerHTML("WebSocket連接成功");
    }


    
    websocket.onmessage = function (event) {
        setMessageInnerHTML(event.data);
    }


    
    websocket.onclose = function () {
        setMessageInnerHTML("WebSocket連接關閉");
    }


   
    window.onbeforeunload = function () {
        closeWebSocket();
    }


    //將消息顯示在網頁上
    function setMessageInnerHTML(sendMessage) {
        document.getElementById('message').innerHTML += sendMessage + '<br/>';
    }


    //關閉WebSocket連接
    function closeWebSocket() {
        websocket.close();
    }


    //發送消息
    function send() {
        var message = document.getElementById('text').value;//要發送的消息内容
        var now=getNowFormatDate();//得到目前时间
        document.getElementById('message').innerHTML += (now+"發送人："+admin_id+'<br/>'+"---"+message) + '<br/>';
        document.getElementById('message').style.color="red";
        var ToSendUserno=document.getElementById('member_id').value; 
        message=message+"|"+ToSendadmin_id//將要發送的信息和內容，讓服務端知道消息要發给誰
        websocket.send(message);
    }
    //得到目前时間
    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
                + " " + date.getHours() + seperator2 + date.getMinutes()
                + seperator2 + date.getSeconds();
        return currentdate;
} 
</script>
</body>
</html>