<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="get">
    <input type="text" name="username">
    <input type="text" name="passsword">
    <input type="submit" value="提交">
</form>
</body>
</html>