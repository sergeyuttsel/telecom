<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Login</title>
	<link href="<c:url value="/resources/bootstrap4/css/bootstrap.css"/>" media="screen" type= "text/css" rel="stylesheet">
	<script src="<c:url value="/resources/bootstrap4/js/bootstrap.js" />"></script>
</head>
<body>
	<form action="/telecom/loginhandler" method="POST">
		<div class="container">
			Email bredp@gmail.com sergeyu@gmail.com qwer<br> <input type="text" name="email"> <br>
			Password <br> <input type="text" name="password"> <br>
			<button type="submit">Apply</button>
		</div>
	</form>
</body>
</html>