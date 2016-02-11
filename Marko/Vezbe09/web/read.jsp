<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<jsp:useBean id="vozila" type="java.util.List" scope="request"/>

<fmt:setBundle basename="messages.messages"/>

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
	
	<c:if test="${sessionScope.admin==null}">
		<c:redirect url="./login.jsp" />
	</c:if>
	
	<body>
	<div class="jumbotron">
		<div class ="container">
		
		
		<div id="restorani_table">
		
			<div class="container">
				<div class="panel panel-primary">	
				<div class="panel-heading" align="center">Restorani:</div>
	
				<table class="table table-striped table-hover table-bordered table-condensed" >
					<tr class="tableRestorani_heading">
						
						<th align="center"> Naziv</th>
						<th align="center"> Adresa </th>
						<th align="center"> Mail </th>
						<th align="center"> Telefon </th>
						<th align="center"> Udaljenost </th>
						<th align="center"> Jelovnik </th>
						
					</tr>	
					
					<c:forEach items="${restorani}" var="restoran">
						<tr>
							<td align="center">${restoran.nazivRestorana}</td>
							<td align="center">${restoran.adresaRestoran}</td>
							<td align="center">${restoran.mailRestoran}</td>
							<td align="center">${restoran.telefonRestoran}</td>
							<td align="center">${restoran.udaljenostRestoran}</td>
							<td align="center">${restoran.jelovnik.nazivJelovnika}</td>	
						</tr>
					</c:forEach>
				</table>
				</div>
			</div>
		</div>
		[<a href="./PrepareCreateController"><fmt:message key="dodavanjeNovogVozila"/></a>]<br/>
		[<a href="./LogoutController"><fmt:message key="odjava"/></a>]<br/>
		[<a href="./Stavka.jsp">Stavka</a>]<br/>
		[<a href="./Restoran.jsp">Restoran</a>]<br/>
		</div>
	</div>
	</body>	
</html>