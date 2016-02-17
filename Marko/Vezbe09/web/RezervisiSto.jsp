<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="./bootstrap.css" rel="stylesheet">
		<link href="./bootstrap-theme.css" rel="stylesheet">
		
		<script src = "jquery-1.11.0.js"></script>
		
		<title>Dobrodošli</title>
		
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<meta HTTP-EQUIV="Expires" CONTENT="-1">

	</head>
	
	<c:if test="${(empty korisnik)}">
				<c:redirect url="./login.jsp" />
	</c:if>
<body>	
	<h3 align="center">Stranica za rezervaciju stolova</h3>
	
	<c:if test="${empty stoloviSlobodni}">
	<h4 align="center">Izaberite datum i vreme</h4>
	</c:if>
	
	
	<div class="modal-dialog">
		<div class="modal-content">
	
	<c:if test="${not empty errormessage}">
		<div id="login_error_message">
			<button type="button" class="close">×</button>
			<p><h3 align = "center" > ${errormessage} </h3></p>
		</div>
	</c:if>
	<c:if test="${empty stoloviSlobodni}">
	<div id="rezervacija_form">
		<form class="form-horizontal" id="sto1_form" action="./RezervisiStoController" accept-charset="UTF-8">
				<div class="modal-header">
					<h4 align="center"> Zakazivanje stola</h4>
					<h5 align="center"><b>${restoranID.nazivRestorana}</b></h5>
				</div>
			<div class="modal-body">
				<div class="form-group">
					<label class="col-lg-2 control-label">Datum:</label>
					<div class="col-sm-8">
						<input class="form-control" type="date" name="datum" required />
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-lg-2 control-label">Vreme:</label>
					<div class="col-sm-8">
						<input class="form-control" type="time" name="vreme" required />
					</div>
				</div>
	
				<div class="form-group">
					<label class="col-lg-2 control-label">Trajanje:</label>
					<div class="col-sm-8">
						<input class="form-control" type="text" pattern="[0-9]*" title="Samo brojevi su dozvoljeni" name="trajanje" required />
					</div>
				</div>
				
			</div>
			<div class="modal-footer">
				<input class="submit_rezervacija btn btn-primary pull-right" type="submit" name="submit" value="Dalje->"/>
			</div>
						
		</form>
	</div>
	</c:if>

	</div>
	</div>
	<c:if test="${not empty stoloviSlobodni}">
			
		<div id="sto_form">
			<div class="container">
				<div class="panel panel-primary">	
				<div class="panel-heading" align="center">Prikaz stolova</div>	
					<table class="table table-striped table-hover table-bordered table-condensed" >
						<c:forEach items="${stoloviSlobodni}" var="sto">
							<c:if test= "${sto.kolona % sto.restoran.kolona == 0}">
								<tr>
							</c:if>
							<td align="center">
								<form method="post" class="form-inline" action="./RezervisiSto2Controller" method="post"  accept-charset="UTF-8">				
									
             					 		
             					 		<c:if test= "${sto.aktivanSto == false}">
									<input class="submit_go btn btn-secondary disabled" type="submit" name="submit" value="${sto.red * sto.restoran.kolona + sto.kolona + 1}" />	
             					 		</c:if>
             					 		
             					 		<c:if test= "${sto.aktivanSto == true}">
             					 		
             					 		<c:if test= "${sto.slobodanSto == false}">
									<input class="submit_go btn btn-danger" type="submit" name="submit" value="${sto.red * sto.restoran.kolona + sto.kolona + 1}" />	
             					 		</c:if>
             					 		
             					 		<c:if test= "${sto.slobodanSto == true}">
									<input class="submit_go btn btn-success" type="submit" name="submit" value="${sto.red * sto.restoran.kolona + sto.kolona + 1}" />	
             					 		</c:if>
             					 		
             					 		</c:if>
             					 		
             					 		
             					 		<input type="hidden" name="stoID" value="${sto.id}">			
								</form>
							</td>
						</c:forEach>	
					</table>
				</div>
			</div>
		</div>
	</c:if>

	<script>
	$(document).ready(function() {
		$('#input_admin_username_group input').focus();
		$('button.close').click(
			function() {
				$('#login_error_message').fadeOut('slow');
			});
	});
	</script>

</body>
</html>