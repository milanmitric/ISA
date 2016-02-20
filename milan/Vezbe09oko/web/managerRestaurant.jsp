<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<jsp:include page="headerManager.jsp"/>
	<c:if test="${sessionScope.menadzer==null}">
			<c:redirect url="./login.jsp" />
	</c:if>
	
	<table class="table table-striped" border=0>
		<thead>
			<th>Naziv</th>
			<th>Vrsta restorana</th>
			<th>Izmjeni</th>
			<th>Jelovnik</th>
			<th>Stolovi</th>
		</thead>
		<tbody>
			<tr>
				<form method="POST" action="ManagerUpdateRestaurantController">
					<td><input class = "form-control" type="text" name="naziv" value = "${restoran.nazivRestorana}" required></td>
					<td><input class = "form-control" type="text" name="vrsta" value = "${restoran.vrstaRestorana}" required></td>
					<input type = "hidden" name="restoranId" value = "${restoran.id}">
					<td><input class="form-control" type="submit" value="Izvrsi Izmjene"></td>
				</form>
				<td>
					<form method="POST" action="PrepareJelovnikController">
						<input class="form-control" type ="button" value="Prikazi jelovnik">
						<input type="hidden" name="restoranId" value = "${restoran.id}">
					</form>
				</td>
				<td>
					<form method="POST" action="AddTableController">
						<input class="form-control" type ="button" value="Prikazi stolove">
						<input  type="hidden" name="restoranId" value = "${restoran.id}">
					</form>
				</td>
			</tr>
		</tbody>
	</table>
	
	
	
	
	<c:if test="${errorMessage != null}">
		<h1>${errorMessage}</h1>
	</c:if>
	

</body>
</html>