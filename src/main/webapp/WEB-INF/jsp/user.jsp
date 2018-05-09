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
<title>User</title>
	<link href="<c:url value="/resources/bootstrap4/css/bootstrap.css"/>" media="screen" type= "text/css" rel="stylesheet">
	<script src="<c:url value="/resources/bootstrap4/js/bootstrap.js" />"></script>
</head>
<body>

	<div class="container">
		<%-- User --%>
		<div class="row">
			<div class="col-sm-12" align="center">
				<h2>User</h2>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">

				<table style="width: 100%"
					class='table table-bordered table-condensed table-striped table-hover'>

					<thead>
						<tr>
							<th>First name</th>
							<th>Last name</th>
							<th>Email</th>
							<th>Passport</th>
							<th>Adress</th>
							<th>Birthday</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><c:out value="${user.firstName}" /></td>
							<td><c:out value="${user.lastName}" /></td>
							<td><c:out value="${user.email}" /></td>
							<td><c:out value="${user.passport}" /></td>
							<td><c:out value="${user.adress}" /></td>
							<td><fmt:formatDate type="date"
									value="${user.getBirthday().getTime()}" /></td>
							
						</tr>
					</tbody>
				</table>
				<%-- End user --%>
			</div>
		</div>

		<%-- Edit button --%>
		<div class="row">
			<div class="col-sm-12" align="center">
				<form action="/telecom/edituser" method="GET">
					<button type="submit" name="idUser" value="${user.id}">Edit</button>
				</form>
			</div>
		</div>
		<%-- End edit button --%>
	</div>

</body>
</html>