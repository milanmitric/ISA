<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Poruka korisniku</title>
</head>
<body>
	
	<c:if test="${sessionScope.korisnik!=null && korisnikId == sessionScope.korisnik.id}">
			<jsp:include page="headerUser.jsp"/>
	</c:if>
	<c:if test="${not empty poruka }">
		${poruka}
	</c:if>
	<c:if test="${sessionScope.korisnik ==null}">
		<a href="login.jsp">Prijavite se na sistem</a>
	</c:if>
</body>
</html>