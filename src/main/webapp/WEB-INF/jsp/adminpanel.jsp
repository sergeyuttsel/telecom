<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.io.*, java.util.*, com.telecom.dao.model.Option,
	com.telecom.dao.model.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
<title>Admin panel</title>
	<link href="<c:url value="/resources/bootstrap4/css/bootstrap.css"/>" media="screen" type= "text/css" rel="stylesheet">
	<script src="<c:url value="/resources/bootstrap4/js/bootstrap.js" />"></script>
</head>
<body>
	<form>
	<div class="container">
		<button type="submit" formaction="/telecom/allclients" formmethod="GET">Clients</button>
		<button type="submit" formaction="/telecom/allplans" formmethod="GET">Plans</button>
		<button type="submit" formaction="/telecom/alloptions" formmethod="GET">Options</button>
		<button type="submit" formaction="/telecom/createplan" formmethod="GET">Create plan</button>
		<button type="submit" formaction="/telecom/createoption" formmethod="GET">Create option</button>
	</div>
	</form>

</body>
</html>