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
	<jsp:include page="headerAdmin.jsp"/>
	<c:if test="${sessionScope.admin==null}">
		<c:redirect url="./login.jsp" />
	</c:if>
		<form method = "POST" action ="./UpdateManagerController">
			<div class="container">  		
				<table class="table table-striped">
					<tr>
						<td>Ime <input  class="form-control" name="ime" type="text" value = "${sessionScope.menadzer.ime}"></td>
					</tr>
					<tr>
						<td>Prezime <input  class="form-control" name="prezime" type="text" value = "${sessionScope.menadzer.prezime}"></td>
					</tr>
					<tr>
						<td>Korisnicko ime <input  class="form-control" type="text" name="korisnickoIme" value = "${sessionScope.menadzer.korisnickoIme}" required></td>
					</tr>
					<tr>
						<td>Lozinka <input  class="form-control" type="text" name="lozinka" value = "${sessionScope.menadzer.lozinka}" required></td>
					</tr>
					<tr>
						<c:if test="${sessionScope.menadzer.sistemMenadzer == true}">
							<td>Da li je sistem menadzer?<input class="form-control" name="sistemMenadzer" type="checkbox" checked/><br>
						</c:if>
						<c:if test="${sessionScope.menadzer.sistemMenadzer == false}">
							<td>Da li je sistem menadzer?<input class="form-control" name="sistemMenadzer" type="checkbox" /><br>
						</c:if>
					</tr>
					<tr>
						<td>
							<select name="restoran" class="form-control">
								<option >nema
		        				</option>
								<c:forEach items="${restorani}" var="restoran">
									<c:if test="${sessionScope.menadzer.restoran.id == restoran.id}">
										<option value="${restoran.id}" selected="selected"> 
				        				${restoran.nazivRestorana} u ${restoran.adresaRestorana}
				        			</option>
									</c:if>
				        			<c:if test="${sessionScope.menadzer.restoran.id != restoran.id}">
										<option value="${restoran.id}"> 
				        				${restoran.nazivRestorana} u ${restoran.adresaRestorana}
				        			</option>
									</c:if>
	        					</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td><input type="hidden" name="menadzerId" value = "${sessionScope.menadzer.id}"></td>
						<td><input class="form-control" type="submit" value = "Izmjeni"></td>
					</tr>
				</table>
			</div>
		</form>
	<c:if test="${errorMessage != null}">
		<h1>${errorMessage}</h1>
	</c:if>
	</body>	
</html>