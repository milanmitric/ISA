<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link href="./bootstrap.min.css" rel="stylesheet">
		<script src = "jquery-1.11.0.js"> </script>
		<script src = "sortable.js"> </script>
		<title>Lista restorana</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<meta HTTP-EQUIV="Expires" CONTENT="-1">
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
		
		
	<script type="text/javascript">
	$(document).on("click", ".open-ReserveDialog", function () {
	     var myBookId = $(this).data('id');
	     var input = document.getElementById("restoranIdModal").value = myBookId;
	});
	</script>
	</head>
<body>
	<c:if test="${sessionScope.admin==null && sessionScope.korisnik == null}">
			<c:redirect url="./login.jsp" />
	</c:if>
	<c:if test="${sessionScope.admin != null }">
		<jsp:include page="headerAdmin.jsp"/>
	</c:if>
	<c:if test="${sessionScope.korisnik != null }">
		<jsp:include page="headerUser.jsp"/>
	</c:if>
	<div class="container">  		
  		<table class="table table-striped sortable" border=0>
    		<thead>
     			<tr>
        			<th>Naziv</th>
        			<th>Adresa</th>
        			<th>Email</th>
        			<th>Telefon</th>
        			<th>Udaljenost</th>
        			<th>Vrsta</th>
        			<th>ProsecnaOcena</th>
        			
        			<c:if test="${sessionScope.admin != null}">
		       			<th>Promjena restorana</th>
						<th>Obrisi restoran</th>
					</c:if>
					<c:if test="${sessionScope.korisnik != null}">
						<th>Pogledaj Jelovnik</th>
						<th>Prosjecna ocjena prijatelja</th>
						<th>Prikazi stolove</th>
					</c:if>
					
      			</tr>
    		</thead>
    		<tbody>
				<c:forEach items="${restorani}" var="restoran">
					<tr>
						<td>${restoran.nazivRestorana}</td>
						<td>${restoran.adresaRestorana}</td>
						<td>${restoran.mailRestorana}</td>
						<td>${restoran.telefonRestorana}</td>
						<td>${restoran.udaljenostRestorana}</td>
						<td>${restoran.vrstaRestorana}</td>
						<c:if test="${restoran.prosecnaOcenaRestorana == -1.0}">
							<td>Nije jos ocjenjen</td>
						</c:if>
						<c:if test="${restoran.prosecnaOcenaRestorana != -1.0}">
							<td>${restoran.prosecnaOcenaRestorana}</td>
						</c:if>
						
						<c:if test="${sessionScope.admin != null}">
							<td>
								<form action="./PrepareUpdateController" method="POST" accept-charset="UTF-8">
									<input type="hidden" name="restoranId" value="${restoran.id}">
	    							<input class="btn btn-primary pull-right" type="submit" name="submit" value="Izmeni" /> 
	    						</form>
							</td>
						
							<td>
								<form action="./DeleteController" method="POST" accept-charset="UTF-8">
									<input type="hidden" name="restoran" value="${restoran.id}">
	    							<input class="btn btn-primary pull-right" type="submit" name="submit" value="Obrisi" /> 
	    						</form>
	    					</td>
    					</c:if>
    					<c:if test="${sessionScope.korisnik != null}">
    						<td>
    							<form method = "POST" action = "./PrepareMenuController">
    								<input type = "hidden" name = "restoran" value="${restoran.id}">
    								<input class = "btn btn-primary pull-right" type = "submit" value = "Pogledaj">
    							</form>
    						</td>
    						<c:if test="${restoran.prosecnaOcenaPrijateljaRestorana == -1.0}">
								<td>Nije jos ocjenjen</td>
							</c:if>
							<c:if test="${restoran.prosecnaOcenaPrijateljaRestorana != -1.0}">
								<td>${restoran.prosecnaOcenaPrijateljaRestorana}</td>
							</c:if>
	    					<td>
	    						<button type="button" data-id = "${restoran.id}" class="open-ReserveDialog btn btn-primary pull-right" data-toggle="modal" data-target="#myModal">Rezervisi sto</button>
	    					</td>	
    					</c:if>
					</tr>
				</c:forEach>
				
    		</tbody>
  		</table>
  	</div>
  	
	
	<c:if test="${sessionScope.admin != null}">
		   <button type="button" class="open-ReserveDialog btn btn-primary pull-right" data-toggle="modal" data-target="#myModal1">Dodaj restoran</button>    		
	</c:if>
	
	<!-- Dodavanje restorana-->
	<div id="myModal1" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Dodavanje restorana</h4>
	      </div>
	      <div class="modal-body">
	        <form method = "POST" action ="./CreateController">
	        	<input class="form-control" name="naziv" type="text"  placeholder="Naziv" required/><br>
	        	<input class="form-control" name="adresa" type="text"  placeholder="Adresa" required/><br>
	        	<input class="form-control" name="mail" type="text"  placeholder="Email" pattern ="[a-zA-Z0-9._\-]+@[a-zA-Z0-9.\-]+\.[a-zA-Z]{2,4}" required/><br>
	        	<input class="form-control" name="telefon" type="text"  placeholder="Telefon" required/><br>
	        	<input class="form-control" name="vrsta" type="text"  placeholder="Vrsta restorana"/><br>
	        	<input class="form-control" name="udaljenost" type="number"  placeholder="Udaljenost"  pattern="[0-9]+" required/><br>
	        	<input class="form-control" type="submit" class = "btn bnt-default">
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	
	  </div>
	</div>
	
	
	<!-- Rezervacija stolova -->
	<div id="myModal" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Rezervacija stolova</h4>
	      </div>
	      <div class="modal-body">
	        <form method = "POST" action ="./PrepareReservationController">
	        	<input class="form-control" name="datum" type="date"  placeholder="Datum" required/><br>
	        	<input class="form-control" name="vrijeme" type="text" pattern="[0-9]{1,2}" placeholder="Vrijeme" required/><br>
	        	<input class="form-control" name="trajanje" type="number"  placeholder="Trajanje" required/><br>
	        	<input class="form-control" name="first" type="hidden" value="postoji"/><br>
	        	<input class="form-control" name = "restoranId" id="restoranIdModal" type="hidden" value = "">
	        	<input class="form-control" type="submit" class = "btn bnt-default">
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	
	  </div>
	</div>
	
</body>
</html>