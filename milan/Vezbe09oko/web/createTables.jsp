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
	<script type="text/javascript">
	
	function doFunction(element,i,j){
		if (element.value == "Slobodan"){
			element.value = "Nema stola";
		} else {
			element.value = "Slobodan";
		}
	};
	
	function checkStatus(red,kolona){
		var stolovi = [];
		stolovi = ${kreiraniStolovi};
		alert(stolovi.length)
		};
	</script>
	
	<table class="table borderless">
	
	<thead>
		<tr>
			<td>${sessionScope.restoran.nazivRestorana}</td>
		</tr>
	</thead>
    <tbody>
    <c:forEach begin="1" end="5" var="i">
    <tr>
    	<c:forEach begin="1" end="5" var="j">
   			<td>
	   			<form method="POST" action="AddTableController">
	   				<input type="hidden" name="restoranId" value="${sessionScope.restoran.id}" >
	   				<input type="hidden" name="red" value="${i}" >
	   				<input type="hidden" name="kolona" value="${j}" >
	   				<input type="submit" value="Nema stola"  onload="doFunction(this)" >
	   				<script type="text/javascript">
	   					checkStatus(${i},${j});
	   				</script>
	   			</form>
   			</td>
    	</c:forEach>
    </tr>
    </c:forEach>
   	</tbody>
	</table>


</body>
</html>