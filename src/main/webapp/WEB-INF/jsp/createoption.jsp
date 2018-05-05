<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.io.*, java.util.*, com.telecom.dao.model.Option"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="./bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="./bootstrap/css/bootstrap-theme.min.css">
<meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
<title>Create option</title>
<link href="<c:url value="/bootstrap4/css/bootstrap.css"/>" rel="stylesheet">
</head>
<body>
	<form action="/telecom/createoptionhandler" method="POST">
		<div class="container">
			<%-- Option --%>
			<div class="row">
				<div class="col-sm-12" align="center">
					<h2>Option</h2>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">

					Name <br> <input type="text" name="name"> <br>
					Price <br> <input type="text" name="price"> <br>
					Price connect <br> <input type="text" name="priceConnect">
					<br> <input type="checkbox" name="archival" value="true">Archived
					<br>

					<%-- End option --%>
				</div>
			</div>
			<%-- Required options --%>
			<div class="row">
				<div class="col-sm-12" align="center">
					<h4>Required options</h4>
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
								<th>Required</th>
							</tr>
						</thead>
						<%
						    int indexRow = 0;
						%>
						<tbody>
							<c:forEach items="${optionList}" var="iOption">
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
									<td><input type="checkbox" name="required"
										value="${iOption.id}"></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<%-- End required options --%>

			<%-- Incompatible options --%>
			<div class="row">
				<div class="col-sm-12" align="center">
					<h4>Incompatible options</h4>
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
								<th>Incompatible</th>
							</tr>
						</thead>
						<%
						    indexRow = 0;
						%>
						<tbody>
							<c:forEach items="${optionList}" var="iOption">
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
									<td><input type="checkbox" name="incompatible"
										value="${iOption.id}"></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<%-- End incompatible options --%>
		</div>
		<div class="row">
			<div class="col-sm-12" align="center">
				<button type="submit">Apply</button>
			</div>
		</div>
	</form>
	<script type="text/javascript" src="./js/jquery-3.0.0.min.js"></script>
	<script type="text/javascript" src="./bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="./bootstrap/js/npm.js"></script>
</body>
</html>