<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="./bootstrap.css" rel="stylesheet">
		<link href="./bootstrap-theme.css" rel="stylesheet">
		
		<script src = "jquery-1.11.0.js"></script>
		
		<title>Jelovnik</title>
		
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<meta HTTP-EQUIV="Expires" CONTENT="-1">
</head>

	<c:if test="${(empty admin) and (empty menadzer)}">
				<c:redirect url="./login.jsp" />
	</c:if>
	
<body>
	<jsp:include page="header.jsp"/>
	<div class="modal-dialog">
		<div class="modal-content">
			<form class="form-horizontal" id="addjelovnik_form" action="./JelovnikController" accept-charset="UTF-8">
				<div class="modal-header">
					<h4> Dodavanje novog jelovnika</h4>
				</div>
					
				<div class="modal-body">
					
					<div class="form-group">
						<label for="jelovnik-naziv" class="col-lg-2 control-label">Naziv:</label>
						<div class="col-sm-8">
							<input class="form-control" type="text" name="naziv" required />
						</div>
					</div>
					
						
				</div>
				
				<div class="input_submit_group modal-footer">
					<input type="hidden" name="itemId" value="${restoranID.id}">
					<input class="submit_adding_jelovnik btn btn-primary pull-left" type="submit" name="submit" value="Dodaj" />
				</div>	
					
			
			</form>
		</div>
				
	</div>



</body>
</html>