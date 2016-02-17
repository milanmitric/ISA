<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link href="./bootstrap.min.css" rel="stylesheet">
	
	<script src = "jquery-1.11.0.js"> 	
	</script>
	
	<title>Stavka jelovnika</title>
	
	<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
	<meta HTTP-EQUIV="Expires" CONTENT="-1">
</head>

	<c:if test="${(empty admin) and (empty menadzer)}">
				<c:redirect url="./login.jsp" />
	</c:if>
	
<body>
	<jsp:include page="header.jsp"/>
	<form class="form-horizontal" id="register_form" role="form" action="./StavkaController" method="post"  accept-charset="UTF-8">
			
			<div class="modal-header">
				<h4 align="center"> Dodavanje nove stavke jelovnika</h4>
			</div>
			
			<div class="modal-body">
			
				<div class="form-group">
					<label for="naziv" class="col-lg-2 control-label">Naziv:</label>
					<div class="col-lg-10">
						<input type="text" name="naziv" class="form-control" placeholder="Naziv" required/>
					</div>
				</div>
				
				<div class="input_stavka_group form-group">
						<label for="stavka-tip" class="col-lg-2 control-label">Tip:</label>
						<div class="col-sm-8">
							<select name="tip" class="form-control">
								<c:forEach items="${tipovi_stavke}" var="tipovi">
									<option value="${tipovi}">"${tipovi}" 
									</option>
								</c:forEach>
							</select>
						</div>
					</div>
				
				<div class="form-group">
					
					<label for="opis" class="col-lg-2 control-label">Opis:</label>
					<div class="col-lg-10">
						<textarea rows="4" name="opis" class="form-control" placeholder="Opis"></textarea>
					</div>
				</div>
				
				<div class="form-group">
					<label for="cena" class="col-lg-2 control-label">Cena:</label>
					<div class="col-lg-10">
						<input type="text" name="cena" class="form-control" placeholder="Cena" pattern="[0-9]*" title="Samo brojevi su dozvoljeni" required/>
					</div>
				</div>
				
			</div>
			
			<div class="modal-footer">
				<a href="./login.jsp" class="pull-left">Nazad na logovanje</a>
				<input type="hidden" name="itemId" value="${restoranID.jelovnik.id}">
				<input class="btn btn-primary pull-right" type="submit" name="submit" value="Potvrdi" />
			</div>
						
				
		</form>

</body>
</html>