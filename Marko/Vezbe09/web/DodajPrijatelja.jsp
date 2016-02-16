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
	
	<script src = "jquery-1.11.0.js"> 	
	</script>
	
	<title>Dodaj prijatelja</title>
	
	<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
	<meta HTTP-EQUIV="Expires" CONTENT="-1">
</head>
	<c:if test="${(empty korisnik)}">
				<c:redirect url="./login.jsp" />
	</c:if>

<body>
<jsp:include page="header.jsp"/>
	<div class="jumbotron">
		<div class ="container">
		<c:if test="${(empty listaPrijatelja)}">
			<h3 align = "center"> <font color="blue">Molimo vas unesite podatke za pretragu korisnika </font></h3>
		</c:if>
	
	<c:if test="${not empty imamessage}">
		<div id="login_error_message">
			<button type="button" class="close" align="right" pull-right>×</button>
			<p> <h4 align="center"> ${imamessage}</h4></p>
		</div>
	</c:if>
	
	<c:if test="${not empty errormessage}">
		<div id="error_message">
			<button type="button" class="closes" align="right" pull-right>×</button>
			<p> <h4 align="center"> ${errormessage}</h4></p>
		</div>
	</c:if>

	<div class="modal-dialog">
		<div class="modal-content">
			<form class="form-horizontal" id="addprijatelja_form" action="./PretraziPrijateljaController" accept-charset="UTF-8">
				<div class="modal-header">
					<h4> Pretrazi korisnika</h4>
				</div>
					
				<div class="modal-body">
					
					<div class="form-group">
						<label class="col-lg-2 control-label">Ime:</label>
						<div class="col-sm-8">
							<input class="form-control" type="text" name="ime" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-2 control-label">Prezime:</label>
						<div class="col-sm-8">
							<input class="form-control" type="text" name="prezime" />
						</div>
					</div>
	
				</div>
				
				<div class="input_submit_group modal-footer">
					<input class="submit_adding_jelovnik btn btn-primary pull-left" type="submit" name="submit" value="Pretrazi" />
				</div>	
					
			
			</form>
		</div>
				
	</div>

	
	<c:if test="${(not empty listaPrijatelja)}">
			<div class="container">
				<div class="panel panel-primary">	
				<div class="panel-heading" align="center">Pronadjeni korisnici</div>
	
				<table class="table table-striped table-hover table-bordered table-condensed" >
					<tr class="tablePrijatelji_heading">
						
						<th align="center"> Ime</th>
						<th align="center"> Prezime</th>
						
					</tr>	
					
					<c:forEach items="${listaPrijatelja}" var="p">
						<tr>
							<td align="center">${p.imeKorisnika}</td>
							<td align="center">${p.prezimeKorisnika}</td>
							<td align="center">
								 <form action="./DodajPrijateljaController" method="post">
								 	<input class="submit_go btn btn-primary" type="submit" name="submit" value="Dodaj" />	
              					 	<input type="hidden" name="itemId" value="${p.id}">
              					 </form>
              				</td>
						</tr>
					</c:forEach>
				</table>
				
				</div>
			</div>
	</c:if>
	</div>
	</div>

	<script>
	$(document).ready(function() {
		$('#input_admin_username_group input').focus();
		$('button.close').click(
			function() {
				$('#login_error_message').fadeOut('slow');
			});
	});
	
	$(document).ready(function() {
		$('#input_admin_username_group input').focus();
		$('button.closes').click(
			function() {
				$('#error_message').fadeOut('slow');
			});
	});
	</script>
</body>
</html>