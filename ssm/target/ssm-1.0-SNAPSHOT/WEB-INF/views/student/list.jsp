<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>列表</title>
    <%--<script src="${pageContext.request.contextPath}/statics/js/jquery.min.js"></script>--%>
    <script src="${pageContext.request.contextPath}/statics/js/axios.min.js"></script>
</head>
<body>
<div >
    <ul id="list">

    </ul>
</div>
<script>
    axios.get('${pageContext.request.contextPath}/s/list-ajax').then(function (response) {
            console.log(response);
        }).catch(function (error) {
            console.log(error);
        });
</script>
</body>
</html>
