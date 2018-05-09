<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.io.*, java.util.*, com.telecom.dao.model.Option,
	com.telecom.dao.model.Option"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
<title>Create plan</title>
	<link href="<c:url value="/resources/bootstrap4/css/bootstrap.css"/>" media="screen" type= "text/css" rel="stylesheet">
	<script src="<c:url value="/resources/bootstrap4/js/bootstrap.js" />"></script>
</head>
<body>
	<form action="/telecom/createplanhandler" method="POST">
		<div class="container">
			<%-- Plan --%>
			<div class="row">
				<div class="col-sm-12" align="center">
					<h2>Create plan</h2>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">

					Name <br> <input type="text" name="name"> <br>
                    Price <br> <input type="text" name="price">
                    <br> <input type="checkbox" name="archival" value="true">Archived
                    <br>
                </div>
			</div>
			<%-- End plan --%>
			
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
								<th>Availability</th>
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
                                    <td><input type="checkbox" name="available"
                                        value="${iOption.id}"></td>
                                </tr>
                            </c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<%-- End available options --%>

		</div>
		<div class="row">
			<div class="col-sm-12" align="center">
				<button type="submit">Apply</button>
			</div>
		</div>
	</form>
</body>
</html>