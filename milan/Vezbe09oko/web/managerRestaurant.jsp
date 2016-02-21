<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		</thead>
		<tbody>
			<form method="POST" action="ManagerUpdateRestaurantController">
				<tr>
					<td><input class = "form-control" type="text" name="naziv" value = "${restoran.nazivRestorana}" required></td>
					<td><input class = "form-control" type="text" name="vrsta" value = "${restoran.vrstaRestorana}" required></td>
					<input type = "hidden" name="restoranId" value = "${restoran.id}">
					<td><input class="form-control" type="submit" value="Izvrsi Izmjene"></td>
				</tr>
			</form>
		</tbody>
	</table>
	
	<table class="table table-striped" >
		<caption>Jelovnik</caption>
		<thead>
			<th>Naziv</th>
			<th>Opis</th>
			<th>Cena</th>
			<th>Izmeni</th>
			<th>Obrisi</th>
		</thead>
		<tbody>
			<c:forEach items="${jela}" var="jelo">
				<tr>
					<td>${jelo.naziv}</td>
					<td>${jelo.opis}</td>
					<td>${jelo.cena}</td>
					<td>Izmeni</td>
					<td>
						<form  method="POST" action="DeleteController" accept-charset="UTF-8">
							<input type="hidden" name="jelo" value="${jelo.id}">
  							<input class="btn btn-primary pull-right" type="submit" name="submit" value="Obrisi" /> 
	    				</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


<button type="button" class="btn btn-primary pull-right" data-toggle="modal" data-target="#myModal">Dodaj Jelo</button>
	</div>
  	
	<!-- Dodavanje jela -->
	<div id="myModal" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Dodavanje jela</h4>
	      </div>
	      <div class="modal-body">
	        <form method = "POST" action ="./CreateMealController">
	        	<input class="form-control" name="naziv" type="text"  placeholder="Naziv" required/><br>
	        	<input class="form-control" name="opis" type="text"  placeholder="Opis"/><br>
	        	<input class="form-control" name="cena" type="text"  placeholder="Cena" required/><br>
	        	<input type="hidden" name="restoran" value="${restoran.id}">
	        	<input class="form-control" type="submit" class = "btn bnt-default">
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	
	  </div>
	</div>
			
	<form method="POST" action="PrepareAddTablesController">
		<input class="btn btn-primary pull-left"type ="submit" value="Prikazi stolove">
		<input  type="hidden" name="restoranId" value = "${restoran.id}">
	</form>
				
	
	
	<c:if test="${errorMessage != null}">
		<h1>${errorMessage}</h1>
	</c:if>
	

</body>
</html>