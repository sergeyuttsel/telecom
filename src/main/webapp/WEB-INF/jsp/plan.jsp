<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.io.*, java.util.*, com.telecom.dao.model.Option,
	com.telecom.dao.model.Plan"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
<title>Plan</title>
	<link href="<c:url value="/resources/bootstrap4/css/bootstrap.css"/>" media="screen" type= "text/css" rel="stylesheet">
	<script src="<c:url value="/resources/bootstrap4/js/bootstrap.js" />"></script>
</head>
<body>

	<div class="container">
		<%-- Plan --%>
		<div class="row">
			<div class="col-sm-12" align="center">
				<h2>Plan</h2>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">

				<table style="width: 100%"
					class='table table-bordered table-condensed table-striped table-hover'>

					<thead>
						<tr>
							<th>Name</th>
							<th>Price</th>
							<th>Archive</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><c:out value="${plan.name}" /></td>
							<td><c:out value="${plan.price}" /></td>
							<c:if test="${plan.archival == true}">
								<td>archival</td>
							</c:if>
							<c:if test="${plan.archival == false}">
								<td>not archival</td>
							</c:if>
						</tr>
					</tbody>
				</table>
				<%-- End plan --%>
			</div>
		</div>
		<%-- Available options --%>
		<div class="row">
			<div class="col-sm-12" align="center">
				<h4>Available options</h4>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<table style="width: 100%"
					class='table table-bordered table-condensed table-striped table-hover'>

					<thead>
						<tr>
							<th>№</th>
							<th>Name</th>
							<th>Price</th>
							<th>Price connect</th>
							<th>Archive</th>
						</tr>
					</thead>
					<%
					    int indexRow = 0;
					%>
					<tbody>
						<c:forEach items="${plan.availableOptions}" var="iOption">
								<tr>
									<%
									    ++indexRow;
									%>
									<td><%=indexRow%></td>
									<td><c:out value="${iOption.name}" /></td>
									<td><c:out value="${iOption.price}" /></td>
									<td><c:out value="${iOption.priceConnect}" /></td>
									<c:if test="${iOption.archival == true}">
										<td>archival</td>
									</c:if>
									<c:if test="${iOption.archival == false}">
										<td>not archival</td>
									</c:if>
								</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<%-- End available options --%>

		<%-- Edit button --%>
		<div class="row">
			<div class="col-sm-12" align="center">
				<form action="/telecom/editplan" method="GET">
					<button type="submit" name="idPlan" value="${plan.id}">Edit</button>
				</form>
			</div>
		</div>
		<%-- End edit button --%>
	</div>

</body>
</html>