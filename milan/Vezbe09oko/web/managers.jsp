<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menadzeri restorana</title>
</head>
<body>
<jsp:include page="headerAdmin.jsp"/>
<c:if test="${sessionScope.admin==null}">
		<c:redirect url="./login.jsp" />
</c:if>

<div class="container">  		
  		<table class="table table-striped" border=0>
    		<thead>
     			<tr>
        			<th>Ime</th>
        			<th>Prezime</th>
        			<th>Korisnicko ime</th>
        			<th>Lozinka</th>
        			<th>Sistem menadzer</th>
        			<th>Restoran</th>
        			<th>Izmjena menadzera</th>
					<th>Obrisi menadzera</th>
      			</tr>
    		</thead>
    		<tbody>
				<c:forEach items="${menadzeri}" var="menadzer">
					<c:if test="${menadzer.id != sessionScope.admin.id}">
						<tr>
							<td>${menadzer.ime}</td>
							<td>${menadzer.prezime}</td>
							<td>${menadzer.korisnickoIme}</td>
							<td>${menadzer.lozinka}</td>
							<c:if test="${menadzer.sistemMenadzer == true }">
								<td>Da</td>
							</c:if>
							<c:if test="${menadzer.sistemMenadzer == false}">
								<td>Ne</td>
							</c:if>
							<td>
								${menadzer.restoran.nazivRestorana} u ${menadzer.restoran.adresaRestorana}
							</td>
							<td>
								<form method="POST" action="PrepareUpdateManagerController" accept-charset="UTF-8">
									<input type="hidden" name="menadzerId" value="${menadzer.id}">
	    							<input class="btn btn-primary pull-right" type="submit" name="submit" value="Izmeni" /> 
	    						</form>
							</td>
							<td>
								<form  method="POST" action="DeleteController" accept-charset="UTF-8">
									<input type="hidden" name="menadzer" value="${menadzer.id}">
	    							<input class="btn btn-primary pull-right" type="submit" name="submit" value="Obrisi" /> 
	    						</form>
	    					</td>
						</tr>
					</c:if>
				</c:forEach>
    		</tbody>
  		</table>
  		

	<button type="button" class="btn btn-primary pull-right" data-toggle="modal" data-target="#myModal">Dodaj menadzera</button>

  	</div>
  	
	<!-- Dodavanje menadzera -->
	<div id="myModal" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Dodavanje menadzera</h4>
	      </div>
	      <div class="modal-body">
	        <form method = "POST" action ="./CreateManagerController">
	        	<input class="form-control" name="ime" type="text"  placeholder="Ime"/><br>
	        	<input class="form-control" name="prezime" type="text"  placeholder="Prezime"/><br>
	        	<input class="form-control" name="korisnickoIme" type="text"  placeholder="Korisnicko ime" required/><br>
	        	<input class="form-control" name="lozinka" type="password"  placeholder="Lozinka" required/><br>
	        	Da li je sistem menadzer<br>
	        	<input class="form-control" name="sistemMenadzer" type="checkbox"  /><br>
	        	Restoran <br>
	        	<select name = "restoran" class="form-control">
		        		<option value ="-1">nema
		        		</option>
	        		<c:forEach items="${restorani}" var="restoran">
	        			<option value="${restoran.id}">
	        			${restoran.nazivRestorana} u ${restoran.adresaRestorana}
	        			</option>
	        		</c:forEach>
	        	</select>
	        	<input class="form-control" type="submit" class = "btn bnt-default">
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	
	  </div>
	</div>
	<c:if test="${errorMessage != null}">
		<h1>${errorMessage}</h1>
	</c:if>
</body>
</html>