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
	
	<c:if test="${(empty admin) and (empty menadzer) and (empty korisnik)}">
				<c:redirect url="./login.jsp" />
	</c:if>
	
	<body>
	<div class="jumbotron">
		<div class ="container">
				<h2 align = "center">Uspešno ste izmenili vaše podatke.</h2>
				<h3 align = "center">Molimo prijavite se ponovo.</h3>
				<a href="./LogoutController" align = "center">Prijava</a>	
		</div>
	</div>

</body>
</html>