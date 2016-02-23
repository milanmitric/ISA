<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Jelovnik restorana</title>
</head>
<body>

	<jsp:include page="headerUser.jsp"/>
	<c:if test="${sessionScope.korisnik==null}">
			<c:redirect url="./login.jsp" />
	</c:if>
	
	<table class="table table-striped sortable" >
		<caption>Jelovnik</caption>
		<thead>
			<th>Naziv</th>
			<th>Opis</th>
			<th>Cena</th>
		</thead>
		<tbody>
			<c:forEach items="${jela}" var="jelo">
				<tr>
					<td>${jelo.naziv}</td>
					<td>${jelo.opis}</td>
					<td>${jelo.cena}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>