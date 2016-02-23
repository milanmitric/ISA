<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prijatelji za rezervaciju</title>

	<script type="text/javascript">
	function rezervisi() {
		var prijatelji = [];
		
		prijatelji.push("skd");
		for (var i = 0; i < $("input[type='checkbox']").length; i++) {
	    	if ($("input[type='checkbox']")[i].checked) {
				var id = $("input[type='checkbox']")[i].value;
		       	prijatelji.push(id);
			}
		}
		
		$.post("ReserveController", {
			'prijatelji[]' : prijatelji
		},function(){
			alert("Uspjesna rezervacija!");
			window.location = "welcomeUser.jsp";
		});

	}
	
	</script>
</head>
<body>
	<jsp:include page="headerUser.jsp"/>
	<c:if test="${sessionScope.korisnik==null}">
			<c:redirect url="./login.jsp" />
	</c:if>
	
	



	<c:forEach items="${prijatelji}" var="p">
	        ${p.imeKorisnika} ${p.prezimeKorisnika}
	        <input type="checkbox" value="${p.id}"><br>
	</c:forEach>
	<form method="POST" action="./CancelReservationController">
		<input class="btn btn-primary pull-left"type ="submit" value="Otkazi rezervaciju">
	</form>
	
	
	<input class="btn btn-primary pull-right"type ="button" value="Zavrsi rezervaciju" onclick="rezervisi()">

</body>
</html>