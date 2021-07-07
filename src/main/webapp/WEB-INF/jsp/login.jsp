<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <title>Login</title>
        <link href="<c:url value="/css/common.css"/>" rel="stylesheet" type="text/css">
    </head>
    <body>
    <form:form method="POST" action="/login/signin" modelAttribute="UserCredentials">
        <input type="text" placeholder="Username" name="username"/><br>
        <input type="password" placeholder="Password" name="password"/><br>
        <input type="submit" value="Sign in"/><br>
    </form:form>
    </body>
</html>