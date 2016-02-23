<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

	<jsp:include page="headerUser.jsp"/>
	<c:if test="${sessionScope.korisnik==null}">
			<c:redirect url="./login.jsp" />
	</c:if>
	
	<table class="table table-striped sortable">
		<thead>
			<tr>
				<td>Ime</td>
				<td>Prezime</td>
				<td>Korisnicko ime</td>
				<td>Dodaj</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${korisnici}" var="prijatelj">
			<tr>
				<td>${prijatelj.imeKorisnika}</td>
				<td>${prijatelj.prezimeKorisnika}</td>
				<td>${prijatelj.korisnickoImeKorisnika}</td>
				<td>
					<form method="POST" action="./AddFriendController">
						<input type="hidden" name="prijateljId" value="${prijatelj.id}">
						<input type="hidden" name="korisnikId" value="${sessionScope.korisnik.id}">
						<input class="form-control" type="submit" value="Dodaj">
					</form>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	

</body>
</html>