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
	
	<title>Prijatelji</title>
	
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
	<c:if test="${not empty errormessage}">
		<div id="login_error_message">
			<button type="button" class="close">×</button>
			<p> <h4 align="center"> ${errormessage}</h4></p>
		</div>
	</c:if>
	<div id="prijatelji_table">
		
			<div class="container">
				<div class="panel panel-primary">	
				<div class="panel-heading" align="center">Moji prijatelji</div>
	
				<table class="table table-striped table-hover table-bordered table-condensed" >
					<tr class="tablePrijatelji_heading">
						
						<th align="center"> Ime</th>
						<th align="center"> Prezime</th>
						
					</tr>	
					
					<c:forEach items="${prijatelji}" var="p">
						<tr>
							<td align="center">${p.prijatelj.imeKorisnika}</td>
							<td align="center">${p.prijatelj.prezimeKorisnika}</td>
							<td align="center">
								 <form action="ObrisiPrijateljaController" method="post">
								 	<input class="submit_go btn btn-primary" type="submit" name="submit" value="X" />	
              					 	<input type="hidden" name="itemId" value="${p.id}">
              					 </form>
              				</td>
						</tr>
					</c:forEach>
				</table>
					<a href="DodajPrijatelja.jsp"> Dodaj prijatelja</a>
				</div>
			</div>
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
	</script>

</body>
</html>