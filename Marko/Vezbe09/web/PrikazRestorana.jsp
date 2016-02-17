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
		
		<script>
		
		$(document).ready(function() {
			
			prikaziStavke();
			definisiRaspored();
			prikaziStolove();

		});	
		function prikaziStavke(){
			
			$('#show_stavke button').click(
					function() {
						$('#stavke_table').slideToggle('fast');
						$('#fade').css('display','block');
					}
				);
				$('#stavke_table button.close').click(
					function() {
		
						$('#stavke_table').fadeOut('fast');

						
					}
				);
			
		}
		
		function definisiRaspored(){
			$("#definisi_raspored").click(
					function() {
					
						$("#raspored_form").slideToggle("fast");
						
					});
		}
		
		function prikaziStolove(){
			$("#prikazi_stolove").click(
					function() {
					
						$("#sto_form").slideToggle("fast");
						
					});
		}
		</script>
		
		
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<meta HTTP-EQUIV="Expires" CONTENT="-1">

	</head>
	
	<c:if test="${(empty admin) and (empty menadzer) and (empty korisnik)}">
				<c:redirect url="./login.jsp" />
	</c:if>
<body>
	<jsp:include page="header.jsp"/>
	<h3 align = "center">Dobrodosli na sajt restorana</h3>
	<h4 align = "center"> ${restoranID.nazivRestorana} </h4>
	<h4 align = "center"> ${restoranID.adresaRestoran} </h4>
	<h4 align = "center"> ${restoranID.telefonRestoran} </h4>
	
	
			
			<c:if test="${not empty errormessage}">
				<div id="login_error_message">
			  		<button type="button" class="close">×</button>
			  		<p> ${errormessage} </p>
				</div>
			</c:if>
		
			<div class="container">
			<c:if test="${not empty restoranID.jelovnik.nazivJelovnika}">
				
				<div id="show_stavke" class="modal-footer">
					<button class="glyphicon glyphicon-plus btn btn-primary pull-left"> Prikazi jelovnik </button>
				</div>
				<div id="stavke_table" hidden>
				<button class="close">×</button>
				<div class="panel panel-primary">	
				<div class="panel-heading" align="center">${restoranID.jelovnik.nazivJelovnika}</div>
	
				<table class="table table-striped table-hover table-bordered table-condensed" >
					<tr class="tableStavke_heading">
						
						<th align="center"> Naziv</th>
						<th align="center"> Tip </th>
						<th align="center"> Opis </th>
						<th align="center"> Cena </th>
						
					</tr>	
					
					<c:forEach items="${stavkaID}" var="stavka">
						<tr>
							<td align="center">${stavka.nazivStavke}</td>
							<td align="center">${stavka.tipStavke}</td>
							<td align="center">${stavka.opisStavke}</td>
							<td align="center">${stavka.cenaStavke}</td>
							<c:if test="${not empty menadzer}">
								<c:if test= "${menadzer.restoran.id == restoranID.id}">
									<td align = "center">
										<form action="ObrisiStavkuController" method="post">
										 	<input class="submit_go btn btn-primary" type="submit" name="submit" value="X" />	
		              					 	<input type="hidden" name="stavkaId" value="${stavka.id}">
		              					 	<input type="hidden" name="itemId" value="${restoranID.id}">
		              					 </form>
	              					 </td>
              					 </c:if>
							</c:if>
						</tr>
					</c:forEach>
				</table>
				</div>
				</div>
			</c:if>
			<c:if test="${empty restoranID.jelovnik.nazivJelovnika}">
				<h3 align="center">Trenutno nemamo podatke o jelovniku restorana.</h3>
			</c:if>
			</div>
		
	
	<c:if test="${not empty menadzer}">
		<c:if test= "${menadzer.restoran.id == restoranID.id}">
			[<a href="./DodajJelovnik.jsp">Dodaj novi jelovnik</a>]<br/>
			<c:if test= "${not empty restoranID.jelovnik}">
				[<a href="./Stavka.jsp">Dodaj stavku</a>]<br/>
			</c:if>
			[<a href="./IzmeniRestoran.jsp" name="itemId" value="${restoranID}"> Izmeni podatke o restoranu</a>]<br/>
		</c:if>
		
		<c:if test="${empty stolovi}">
			<div id="definisi_raspored" class=" btn btn-primary pull-left">
				Definiši raspored sedenja
			</div>
			<br/>
			<br/>
				
			<div id="raspored_form" hidden>
				<div class="container">		
					<div class="row">
						<div class="well well-sm">
					<form method="post" class="form-inline" action="./DefinisiRasporedController" method="post"  accept-charset="UTF-8">
							
						<div class="form-group">
							<div id="input_name_group" >
								<label> Kapacitet: </label>
								<input type="text" name="kapacitet" class="form-control" placeholder="kapacitet" pattern="[0-9]*" title="Samo brojevi su dozvoljeni" required/>
							</div>
						</div>
							
						<div class="form-group">
							<div id="input_tip_group">
								<label> Broj redova: </label>
								<input type="text" name="red" class="form-control" placeholder="red" pattern="[0-9]*" title="Samo brojevi su dozvoljeni" required/>
							</div>
						</div>
						
						<div class="form-group">
							<div id="input_tip_group">
								<label> Broj kolona: </label>
								<input type="text" name="kolona" class="form-control" placeholder="kolona" pattern="[0-9]*" title="Samo brojevi su dozvoljeni" required/>
							</div>
						</div>
	
						<div class="form-group">
							<div id="input_tip_group">
							<input type="submit" name="submit" value="Definiši" class="btn btn-primary "/>
							</div>
						</div>
							
						
					</form>
					</div>
				</div>
				</div>
			</div>
		</c:if>
	</c:if>
	
	<c:if test="${not empty korisnik}">
		<c:if test="${not empty stolovi}">
			[<a href="./RezervisiSto.jsp" name="itemId" value="${restoranID}"> Rezervišite sto</a>]<br/>
		</c:if>
		<c:if test="${empty stolovi}">
			<p>Trenutno ne možete da rezervišete stolove. Molimo pokušajte kasnije.<p>
		</c:if>
	</c:if>
	<c:if test="${not empty menadzer}">
		<c:if test="${not empty redovi}">
			<h3>Svi stolovi su trenutno aktivni. Kliknite na stolove koje želite da ne koristite.</h3>
		</c:if>
		<c:if test="${not empty stolovi}">
		<div id="prikazi_stolove" class=" btn btn-primary pull-left">
					Prikaži raspored stolova
				</div>
				<br/>
				<br/>
					
				<div id="sto_form" hidden>
					<div class="container">
						<div class="panel panel-primary">	
						<div class="panel-heading" align="center">Prikaz stolova</div>	
							<table class="table table-striped table-hover table-bordered table-condensed" >
								<c:forEach items="${stolovi}" var="sto">
									<c:if test= "${sto.kolona % sto.restoran.kolona == 0}">
										<tr>
									</c:if>
									<td align="center">
										<form method="post" class="form-inline" action="./AktivanStoController" method="post"  accept-charset="UTF-8">				
											
	              					 		
	              					 		<c:if test= "${sto.aktivanSto == false}">
											<input class="submit_go btn btn-secondary" type="submit" name="submit" value="${sto.red * sto.restoran.kolona + sto.kolona + 1}" />	
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