<%--
  Created by IntelliJ IDEA.
  User: Olya
  Date: 29.01.15
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form name="f" action="login" method="post">
        <fieldset>
            <legend>Please Login</legend>
            <label for="username">Username</label>
            <input type="text" id="username" name="loginId"/>
            <label for="password">Password</label>
            <input type="password" id="password" name="password"/>

            <div class="form-actions">
                <button type="submit" class="btn">Log in</button>
            </div>
        </fieldset>
    </form>
</body>
</html>