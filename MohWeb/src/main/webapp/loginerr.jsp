<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Login Error</title>
</head>
<body>
	<h4>Login Error:</h4>
	<c:if test="${not empty loginerr}">
		<div style="color: red;">${loginerr}</div>
	</c:if>
	<div><a href="login.jsp">Login Again</a></div>
</body>
</html>