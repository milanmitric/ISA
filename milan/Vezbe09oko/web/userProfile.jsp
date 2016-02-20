<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<jsp:include page="headerUser.jsp"/>
	<c:if test="${sessionScope.korisnik==null}">
			<c:redirect url="./login.jsp" />
	</c:if>
	
	<form method="POST" action="./UpdateUserController">
		Ime <input name="ime" type="text" class="form-control" value = "${sessionScope.korisnik.imeKorisnika}">
		Prezime <input name = "prezime" type="text" class="form-control" value = "${sessionScope.korisnik.prezimeKorisnika}" >
		Korisnicko ime <input required name ="korisnickoIme" type="text" class="form-control" value = "${sessionScope.korisnik.korisnickoImeKorisnika}" >
		Lozinka <input name="lozinka" required type="text" class="form-control" value = "${sessionScope.korisnik.lozinkaKorisnika}">
		<input type="hidden" name="korisnikId" value= "${sessionScope.korisnik.id}">
		<input type="submit" class="form-control" value="Izmjeni">
	</form>
	
	<c:if test="${errorMessage != null}">
		<h1>${errorMessage}</h1>
	</c:if>
	
<script type="text/javascript">

function enableInputs(){
	var inputs=document.getElementsByTagName('input');
	for(i=0;i<inputs.length;i++){
	    inputs[i].disabled=!inputs[i].disabled;
	}  
	var button = document.getElementById('dugme');
	button.disabled = false;
}
</script>
</body>
</html>