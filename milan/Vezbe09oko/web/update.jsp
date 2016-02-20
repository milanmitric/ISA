<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<fmt:setBundle basename="messages.messages"/>

<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	</head>
	<c:if test="${sessionScope.admin==null}">
		<c:redirect url="./login.jsp" />
	</c:if>
	<body>
	<c:if test="${sessionScope.admin==null}">
		<c:redirect url="./login.jsp" />
	</c:if>
	<jsp:include page="headerAdmin.jsp"/>
		<form method = "POST" action ="./UpdateController">
			<div class="container">  		
				<table class="table table-striped">
					<tr>
						<td>Naziv <input  class="form-control" name="nazivRestorana" type="text" value = "${sessionScope.restoran.nazivRestorana}"></td>
					</tr>
					<tr>
						<td>Adresa <input  class="form-control" name="adresaRestorana" type="text" value = "${sessionScope.restoran.adresaRestorana}"></td>
					</tr>
						<td>Email <input  class="form-control" type="text" name="mailRestorana" value = "${sessionScope.restoran.mailRestorana}"></td>
					<tr>
						<td>Telefon <input  class="form-control" type="text" name="telefonRestorana" value = "${sessionScope.restoran.telefonRestorana}"></td>
					</tr>
					<tr>
						<td>Udaljenost <input  class="form-control" type="text" name="udaljenostRestorana" value = "${sessionScope.restoran.udaljenostRestorana}"></td>
					</tr>
					<tr>
						<td>Vrsta restorana<input  class="form-control" type="text" name = "vrsta" value = "${sessionScope.restoran.vrstaRestorana}"> </td>
					</tr>
					<tr>
						<td><input type="hidden" name="restoranId" value = "${sessionScope.restoran.id}"></td>
						<td><input class="form-control" type="submit" value = "Izmjeni"></td>
					</tr>
				</table>
			</div>
		</form>
	
	
	
	</body>	
</html>