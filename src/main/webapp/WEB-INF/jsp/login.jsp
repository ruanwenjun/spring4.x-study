<%--
  Created by IntelliJ IDEA.
  User: ruanwenjun
  Date: 2018/11/17
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>小春论坛登陆</title>
</head>
<body>
<c:if test="${!empty error}">
    <p style="color: red"><c:out value="${error}"/></p>
</c:if>
<form action="<c:url value="/loginCheck.html"/>" method="post">
    用户名：<input type="text" name="userName">
    密码：<input type="password" name="password">
    <br>
    <input type="submit" value="登陆">
    <input type="reset" value="重置">
</form>
</body>
</html>