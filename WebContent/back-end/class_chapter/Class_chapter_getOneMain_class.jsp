<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main_class_搜尋</title>
    <style>


    </style>
</head>
<body>
	<h1>MainClass: 搜尋</h1>
    <a href="<%=request.getContextPath()%>/back-end/main_class/select_page.jsp">MainClass: Home</a>
    	<br>
    <div class="search">
        <form action="<%=request.getContextPath()%>/Main_class/Main_classServlet" method="GET">
        <p>選擇 </p>
        <p>時間區間 <input type="text" name="main_class_ID"></p>
        <p>課程分類 <input type="text" name="main_class_ID"></p>
        <p><input type="text" name="main_class_ID"></p>

    </div>
    <div class="right_block">
        <input type="submit" value="搜尋" >
    </div>




    </form>
    
</body>
</html>