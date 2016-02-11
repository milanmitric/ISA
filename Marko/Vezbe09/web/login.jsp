<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link href="./bootstrap.min.css" rel="stylesheet">
	<link href="./login.css" rel="stylesheet">
	
	<script src = "jquery-1.11.0.js"> </script>
	
	<title>Login</title>
	
	<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
	<meta HTTP-EQUIV="Expires" CONTENT="-1">
	
</head>
<body>
	<div class="container">

		<form class="form-signin" action="./LoginController" method="post" class="form-signin" accept-charset="ISO-8859-1">
	        <h1 class="form-signin-heading">Dobrodošli</h1>
	        <h4 class="form-signin-heading">Molimo Vas da se ulogujete</h4>
	        
	        <c:if test="${not empty errormessage}">
				<div id="login_error_message">
			  		<button type="button" class="close">×</button>
			  		<p> ${errormessage} </p>
				</div>
			</c:if>
				
	        <input type="text" name="korisnickoIme" id="username" class="form-control" placeholder="Korisničko ime" required autofocus>
	        <input type="password" name="lozinka" id="password" class="form-control" placeholder="Šifra" required>
	        <button class="btn btn-lg btn-primary btn-block" type="submit" name="submit">Ulogujte se</button>
      </form>
      
      <form class="form-signin" action="./register.jsp" method="post" class="form-signin" accept-charset="ISO-8859-1">
      		<button class="btn btn-lg btn-primary btn-block" type="submit" name="submitRegistration">Registrujte se</button>
      </form>

    </div>

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