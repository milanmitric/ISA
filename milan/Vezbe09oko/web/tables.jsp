<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Stolovi</title>
</head>
<body>
	<jsp:include page="headerManager.jsp"/>
	<c:if test="${sessionScope.menadzer==null}">
			<c:redirect url="./login.jsp" />
	</c:if>

	<table class="table table-striped">
	<tbody>
	
		<c:forEach var="i" begin="1" end ="${restoran.brojRedovaRestorana}">
			<tr>
				<c:forEach var="j" begin ="1" end="${restoran.brojKolonaRestorana}">
					<c:forEach items="${stolovi}" var="sto" >

						<c:if test="${sto.red == i && sto.kolona == j}">
							<td>
								<c:if test="${sto.slobodan == true }">
										<form method="POST" action="AddTableController">
						 	   				<input type="hidden" name="restoranId" value="${sessionScope.restoran.id}" >
						 	   				<input type="hidden" name="red" value="${i}" >
						 	   				<input type="hidden" name="kolona" value="${j}" >
						 	   				<input type="submit" value="Slobodan"  onclick="doFunction(this)" >
					 	   				</form>
								</c:if>
								<c:if test="${sto.slobodan == false}">
									<form method="POST" action="AddTableController">
						 	   				<input type="hidden" name="restoranId" value="${sessionScope.restoran.id}" >
						 	   				<input type="hidden" name="red" value="${i}" >
						 	   				<input type="hidden" name="kolona" value="${j}" >
						 	   				<input type="submit" value="Nema stola"  onclick="doFunction(this)" >
				 	   				</form>
								</c:if>
							</td>
						</c:if>
				
					</c:forEach>
				</c:forEach>
			</tr>
		</c:forEach>
	</tbody>
	</table>
	
	<script type="text/javascript">
 	
 	function doFunction(element,i,j){
 		if (element.value == "Slobodan"){
 			element.value = "Nema stola";
 		} else {
 			element.value = "Slobodan";
 		}
 	};
 	</script>
</body>
</html>