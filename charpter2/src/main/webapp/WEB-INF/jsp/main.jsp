<%--
  Created by IntelliJ IDEA.
  User: ruanwenjun
  Date: 2018/11/17
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>小春论坛</title>
</head>
<body>
    ${user.userName},欢迎您进入小春论坛，您当前积分是${user.credits}
    登陆地址:${user.lastIp},登陆时间:<fmt:formatDate value="${user.lastVisit}" pattern="yyyy-MM-ss HH:mm:ss"/>
</body>
</html>
