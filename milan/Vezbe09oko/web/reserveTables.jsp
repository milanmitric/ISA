<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Stolovi</title>
</head>
<body>
	<jsp:include page="headerUser.jsp"/>
	<c:if test="${sessionScope.korisnik==null}">
			<c:redirect url="./login.jsp" />
	</c:if>


		
	Datum<input type = "text" value="${sessionScope.datum}" disabled><br>
	Vrijeme<input type = "text" value="${sessionScope.vrijeme}" disabled><br>
	Trajanje<input type = "text" value="${sessionScope.trajanje}" disabled><br>
	<table class="table table-striped sortable">
	<tbody>
	
		<c:forEach var="i" begin="1" end ="${restoran.brojRedovaRestorana}">
			<tr>
				<c:forEach var="j" begin ="1" end="${restoran.brojKolonaRestorana}">
					<c:forEach items="${stolovi}" var="sto" >

						<c:if test="${sto.red == i && sto.kolona == j}">
							<td>
								<c:if test="${sto.slobodan == true }">
									<c:if test="${sto.rezervisan == 0 }">
										<form method="POST" action="./AddTableToReservedController">
											<input type="hidden" name="restoranId" value="${restoran.id}" >
						 	   				<input type="hidden" name="red" value="${i}" >
						 	   				<input type="hidden" name="kolona" value="${j}" >
											<input type="submit" value="Slobodan">
					 	   				</form>
				 	   				</c:if>
									<c:if test="${sto.rezervisan == 1 }">
										<form method="POST" action="./AddTableToReservedController">
											<input type="hidden" name="restoranId" value="${restoran.id}" >
						 	   				<input type="hidden" name="red" value="${i}" >
						 	   				<input type="hidden" name="kolona" value="${j}" >
											<input type="submit" value="Rezervisao">
					 	   				</form>
				 	   				</c:if>
				 	   				<c:if test="${sto.rezervisan == 2}">
				 	   					<input type="button" value="Zauzet" disabled>
				 	   				</c:if>
								</c:if>
								<c:if test="${sto.slobodan == false}">
					 	   				<input type="button" disabled value="Nema stola">
								</c:if>
							</td>
						</c:if>
				
					</c:forEach>
				</c:forEach>
			</tr>
		</c:forEach>
	</tbody>
	</table>
	
	<form method="POST" action="./CancelReservationController">
		<input class="btn btn-primary pull-left"type ="submit" value="Otkazi rezervaciju">
	</form>
	
	<c:if test="${empty stoloviZaRezervaciju }">
		<form method="POST" action="./PrepareFriendReservationController">
		<input type="hidden" name="reservation" value = "reservation">
		<input class="btn btn-primary pull-right"type ="submit" value="Izaberi prijatelje" disabled>
	</form>
	</c:if>
	<c:if test="${not empty stoloviZaRezervaciju }">
		<form method="POST" action="./PrepareFriendReservationController">
		<input type="hidden" name="reservation" value = "reservation">
		<input class="btn btn-primary pull-right"type ="submit" value="Izaberi prijatelje">
	</form>
	</c:if>
	
</body>
</html>