<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>第一个mvc</h1>
<p><a href="/test/some.c?name=zookao">测试请求</a></p>
<p>
    <form method="post" action="/test/other.c">
    姓名：<input type="text" name="rname">
    年龄：<input type="text" name="rage">
    <input type="submit" value="提交">
    </form>
</p>
<p>
<form method="post" action="/test/object.c">
    姓名：<input type="text" name="name">
    年龄：<input type="text" name="age">
    <input type="submit" value="提交">
</form>
</p>
<p><a href="/test/json.c">json返回</a></p>
<p><a href="/test/jsonString.c">string返回</a></p>
</body>
</html>
