<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/s/register" method="post">
    姓名：<input type="text" name="name">
    邮箱：<input type="text" name="email">
    年龄：<input type="text" name="age">
    <input type="submit" value="提交">
</form>
</body>
</html>
