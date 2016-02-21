<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script src = "jquery-1.11.0.js"> </script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Dodavanje stolova</title>
	<link href="./table.css" rel="stylesheet">
	


</head>
<body>
	<jsp:include page="headerManager.jsp"/>
	<c:if test="${sessionScope.menadzer==null}">
			<c:redirect url="./login.jsp" />
	</c:if>
	
	<form method = "POST" action = "PrepareAddTablesController">
		<input class="form-control" type="text" name="red" placeholder="Unesite broj redova" >
		<input class="form-control" type="text" name="kolona" placeholder="Unesite broj kolona" >
		<input type="hidden" name = "restoranId" value = "${restoran.id}">
		<input class="form-control" type="submit" value="Posalji">
	</form>
	
	


</body>
</html>