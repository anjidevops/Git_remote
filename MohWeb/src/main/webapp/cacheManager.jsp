<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cache Manager</title>
</head>
<body>
<div>
<form action="login/clearCache" method="post">
<input type="text" name="cacheName"/> <input type="submit" name="clearCache" value="Clear Cache" />
<br /><br />
<input type="submit" name="clearAllCache" value="Clear All Cache" />
</form>
</div>
</body>
</html>