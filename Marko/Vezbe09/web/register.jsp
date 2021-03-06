<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link href="./bootstrap.min.css" rel="stylesheet">
	<link href="./login.css" rel="stylesheet">
	
	<script src = "jquery-1.11.0.js"> 	
	</script>
	
	<title>Registracija</title>
	
	<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
	<meta HTTP-EQUIV="Expires" CONTENT="-1">
</head>
<body>
			
		
		<div id="adduslugu_window">
			
			<div class="modal-dialog">
			<div class="modal-content">
			
			<c:if test="${not empty errormessage}">
				<div id="login_error_message">
			  		<button type="button" class="close">×</button>
			  		<p><h4><font color="red"> ${errormessage}  </font></h4></p>
				</div>
			</c:if>
		
			
			<form class="form-horizontal" id="register_form" role="form" action="./RegisterController" method="post"  accept-charset="UTF-8">
			
			<div class="modal-header">
				<h4 align="center"> Dodavanje novog korisnika</h4>
			</div>
			
			<div class="modal-body">
			
				<div class="form-group">
					<label for="usluga-name" class="col-lg-2 control-label">Ime:</label>
					<div class="col-lg-10">
						<input type="text" name="ime" class="form-control" placeholder="Ime" required/>
					</div>
				</div>
				
				<div class="form-group">
					<label for="usluga-prezime" class="col-lg-2 control-label">Prezime:</label>
					<div class="col-lg-10">
						<input type="text" name="prezime" class="form-control" placeholder="Prezime" required/>
					</div>
				</div>
				
				<div class="form-group">
					
					<label for="usluga-korisnicko_ime" class="col-lg-2 control-label">Korisnicko ime:</label>
					<div class="col-lg-10">
						<input type="text"  id ="korisnicko_ime" name="korisnicko_ime" class="form-control" placeholder="Korisnicko ime" required/>
					</div>
				</div>
				
			
				<div class="form-group">
					<label for="usluga-lozinka" class="col-lg-2 control-label">Lozinka:</label>
					<div class="col-lg-10">
						<input type="password"  name="lozinka" class="form-control" placeholder="Lozinka" required/>
					</div>	
				</div>
				
				<div class="form-group">
					<label for="usluga-lozinka" class="col-lg-2 control-label">Ponovite lozinku:</label>
					<div class="col-lg-10">
						<input type="password"  name="lozinka2" class="form-control" placeholder="Ponovite lozinku" required/>
					</div>	
				</div>
			</div>
			
			<div class="modal-footer">
				<a href="./login.jsp" class="pull-left">Nazad na logovanje</a>
				<input class="submit_adding_korisnika btn btn-primary pull-right" type="submit" name="submit" value="Potvrdi" />
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