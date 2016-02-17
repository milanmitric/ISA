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
			
			pretragaRestorana();
		});
		
		function pretragaRestorana(){
			$("#pretraga_restorana").click(
					function() {
					
						$("#pretraga_form").slideToggle("fast");
						
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
	<div class="jumbotron">
		<div class ="container">
		
		
		<div id="pretraga_restorana" class=" btn btn-primary pull-right">
			Pretraga restorana <img src="./search.png" />
		</div>
		<br/>
		<br/>
			
		<div id="pretraga_form" hidden>
			<div class="container">		
				<div class="row">
					<div class="well well-sm">
				<form method="post" class="form-inline" action="./PretragaRestoranaController" method="post"  accept-charset="UTF-8">
						
					<div class="form-group">
						<div id="input_name_group" >
							<label for="pretragaNaziv"> Naziv: </label>
							<input type="text" name="naziv" class="form-control" placeholder="naziv"/>
						</div>
					</div>
						
					<div class="form-group">
						<div id="input_tip_group">
							<label for="pretragaNamestajaBoja"> Tip: </label>
							<input type="text" name="tip" class="form-control" placeholder="tip"/>
						</div>
					</div>

					<div class="form-group">
						<div id="input_tip_group">
						<input type="submit" name="submit" value="Pretraži" class="btn btn-primary "/>
						</div>
					</div>
						
					
				</form>
				</div>
			</div>
			</div>
		</div>
		
		<div id="restorani_table">
		
			<div class="container">
				<div class="panel panel-primary">	
				<div class="panel-heading" align="center">Restorani</div>
	
				<table class="table table-striped table-hover table-bordered table-condensed" >
					<tr class="tableRestorani_heading">
						
						<th align="center"> Naziv</th>
						<th align="center"> Tip </th>
						<th align="center"> Adresa </th>
						<th align="center"> Mail </th>
						<th align="center"> Telefon </th>
						<th align="center"> Udaljenost </th>
						<th align="center"> Sajt  </th> 
						
					</tr>	
					
					<c:forEach items="${restorani}" var="restoran">
						<tr>
							<td align="center">${restoran.nazivRestorana}</td>
							<td align="center">${restoran.tipRestoran}</td>
							<td align="center">${restoran.adresaRestoran}</td>
							<td align="center">${restoran.mailRestoran}</td>
							<td align="center">${restoran.telefonRestoran}</td>
							<td align="center">${restoran.udaljenostRestoran}</td>
							<td align="center">
								 <form action="PrikazRestoranaController" method="post">
								 	<input class="submit_go btn btn-primary" type="submit" name="submit" value="Poseti sajt" />	
              					 	<input type="hidden" name="itemId" value="${restoran.id}">
              					 </form>
              				</td>
						</tr>
					</c:forEach>
				</table>
				</div>
			</div>
		</div>
		<c:if test="${not empty admin}"> 
			[<a href="./Restoran.jsp">Dodaj restoran</a>]<br/>
			[<a href="./DodajMenadzera.jsp">Dodaj menadzera</a>]<br/>
		</c:if>

		</div>
	</div>
	</body>	
</html>