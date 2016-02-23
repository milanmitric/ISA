<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Ocene korisnika</title>
	<script src = "sortable.js"> </script>
</head>
<body>
	<jsp:include page="headerUser.jsp"/>
	<c:if test="${sessionScope.korisnik==null}">
			<c:redirect url="./login.jsp" />
	</c:if>
	
	<table   class="table table-striped sortable">
	<thead>
		<th>Restoran</th>
		<th>Datum</th>
		<th>Ocena</th>
		<th>Opcije</th>
	</thead>
		<tbody>
		
		<c:forEach items="${rezervacije}" var="rez">
		
			<tr>
				<td>${rez.restoran.nazivRestorana}</td>
				<td>${rez.datum}</td>
				<c:if test="${rez.ocena == -1}">
					<form method = "POST" action = "./AddReviewController">
						<input type = "hidden" name = "korisnik" value = "${sessionScope.korisnik.id}">
						<input type = "hidden" name = "rezervacija" value = "${rez.id}" >
						<td><input type = "text" name = "ocena" pattern="[1-5]"></td>
						<td><input type = "submit" value = "Oceni"></td>
					</form>
				</c:if>
				<c:if test="${rez.ocena != -1}">
					<td>${rez.ocena}</td>
				</c:if>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
</body>
</html>