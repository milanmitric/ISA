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
	
	<title>Izmena profila</title>
	
	<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
	<meta HTTP-EQUIV="Expires" CONTENT="-1">
</head>
	<c:if test="${(empty menadzer) and (empty admin)}">
				<c:redirect url="./login.jsp" />
	</c:if>

<body>
		<jsp:include page="header.jsp"/>	
		
		<div id="izmeniMenadzera_window">
			
			<div class="modal-dialog">
			<div class="modal-content">
			
			<c:if test="${not empty errormessage}">
				<div id="login_error_message">
			  		<button type="button" class="close">×</button>
			  		<p><h4><font color="red"> ${errormessage}  </font></h4></p>
				</div>
			</c:if>
		
			
			<form class="form-horizontal" id="register_form" role="form" action="./IzmeniMenadzeraController" method="post"  accept-charset="UTF-8">
			
			<div class="modal-header">
				<h4 align="center"> Izmena profila</h4>
			</div>
			
			<div class="modal-body">
			
				<c:if test="${not empty menadzer}">
				
				<div class="form-group">
					<label class="col-lg-2 control-label">Ime:</label>
					<div class="col-lg-10">
						<input type="text" name="ime" value ="${menadzer.imeMenadzera}" class="form-control" placeholder="Ime" required/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-lg-2 control-label">Prezime:</label>
					<div class="col-lg-10">
						<input type="text" name="prezime" value ="${menadzer.prezimeMenadzera}" class="form-control" placeholder="Prezime" required/>
					</div>
				</div>
				
				<div class="form-group">
					
					<label class="col-lg-2 control-label">Korisnicko ime:</label>
					<div class="col-lg-10">
						<input type="text"  id ="korisnicko_ime" value ="${menadzer.korisnickoImeMenadzera}" disabled name="korisnicko_ime" class="form-control" placeholder="Korisnicko ime" required/>
					</div>
				</div>
				</c:if>
				
				
				<c:if test="${not empty admin}">
				
				<div class="form-group">
					<label class="col-lg-2 control-label">Ime:</label>
					<div class="col-lg-10">
						<input type="text" name="ime" value ="${admin.imeMenadzera}" class="form-control" placeholder="Ime" required/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-lg-2 control-label">Prezime:</label>
					<div class="col-lg-10">
						<input type="text" name="prezime" value ="${admin.prezimeMenadzera}" class="form-control" placeholder="Prezime" required/>
					</div>
				</div>
				
				<div class="form-group">
					
					<label class="col-lg-2 control-label">Korisnicko ime:</label>
					<div class="col-lg-10">
						<input type="text"  id ="korisnicko_ime" value ="${admin.korisnickoImeMenadzera}" disabled name="korisnicko_ime" class="form-control" placeholder="Korisnicko ime" required/>
					</div>
				</div>
				</c:if>

			
				<div class="form-group">
					<label class="col-lg-2 control-label">Trenutna lozinka:</label>
					<div class="col-lg-10">
						<input type="password"  name="lozinka" class="form-control" placeholder="Lozinka"/>
					</div>	
				</div>
				
				<div class="form-group">
					<label class="col-lg-2 control-label">Nova loznika: </label>
					<div class="col-lg-10">
						<input type="password"  name="novalozinka" class="form-control" placeholder="Ponovite lozinku"/>
					</div>	
				</div>
				
				<div class="form-group">
					<label class="col-lg-2 control-label">Ponovite lozinku:</label>
					<div class="col-lg-10">
						<input type="password"  name="lozinka2" class="form-control" placeholder="Ponovite lozinku"/>
					</div>	
				</div>
				
			</div>
			
			<div class="modal-footer">
				<input class="btn btn-primary pull-right" type="submit" name="submit" value="Potvrdi" />
			</div>
						
				
		</form>
		</div>
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

	function validateEmail() {
	    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	    var selectField = document.getElementsById("korisnicko_ime");
	    return re.test(email);
	}
	</script>

</body>
</html>