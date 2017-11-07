<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Welcome to Windmsvy Discussion Zone</title>
</head>
<body>
<c:if test="${!empty error}">
    <font color="red"><c:out value="${error}" /></font>
</c:if>
<form action="<c:url value="loginCheck.html"/>" method="post">
    Username：
    <input type="text" name="userName">
    <br>
    PassWord:
    <input type="password" name="passWord">
    <br>
    <input type="submit" value="Login" />
    <input type="reset" value="Reset" />
</form>
</body>
</html>
