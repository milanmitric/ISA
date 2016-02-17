<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link href="./bootstrap.min.css" rel="stylesheet">
		<script src = "jquery-1.11.0.js"> </script>
		<title>Lista restorana</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<meta HTTP-EQUIV="Expires" CONTENT="-1">
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	</head>
<body>
	<c:if test="${sessionScope.admin==null}">
			<c:redirect url="./login.jsp" />
	</c:if>
	<jsp:include page="headerAdmin.jsp"/>
	<div class="container">  		
  		<table class="table table-striped" border=0>
    		<thead>
     			<tr>
        			<th>Naziv</th>
        			<th>Adresa</th>
        			<th>Email</th>
        			<th>Telefon</th>
        			<th>Udaljenost</th>
        			<th>Kapacitet</th>
        			<th>Promjena restorana</th>
					<th>Obrisi restoran</th>
					<th>Prikazi stolove</th>
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
						<td>${restoran.kapacitet}</td>
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
    					<td>
							<form action="./PrepareAddTablesController" method="POST" accept-charset="UTF-8">
								<input type="hidden" name="restoranId" value="${restoran.id}">
    							<input class="btn btn-primary pull-right" type="submit" name="submit" value="Prikazi" /> 
    						</form>
    					</td>	
					</tr>
				</c:forEach>
				
    		</tbody>
  		</table>
  	</div>
  	
	
	<script type="text/javascript">
	
	
	</script>
</body>
</html>