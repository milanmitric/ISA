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
		
		<title>Restoran</title>
		
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<meta HTTP-EQUIV="Expires" CONTENT="-1">
</head>
<body>

	<div class="modal-dialog">
		<div class="modal-content">
			<form class="form-horizontal" id="addrestoran_form" action="./RestoranController" accept-charset="UTF-8">
				<div class="modal-header">
					<h4> Dodavanje novog restorana</h4>
				</div>
					
				<div class="modal-body">
					
					<div class="form-group">
						<label for="restoran-naziv" class="col-lg-2 control-label">Naziv:</label>
						<div class="col-sm-8">
							<input class="form-control" type="text" name="naziv" required />
						</div>
					</div>
					
					<div class="form-group">
						<label for="restoran-adresa" class="col-lg-2 control-label">Adresa:</label>
						<div class="col-sm-8">
							<input class="form-control" type="text" name="adresa" required />
						</div>
					</div>
					
					<div class="form-group">
						<label for="restoran-mail" class="col-lg-2 control-label">E-mail:</label>
						<div class="col-sm-8">
							<input class="form-control" type="text" name="mail" required />
						</div>
					</div>
					
					<div class="form-group">
						<label for="restoran-telefon" class="col-lg-2 control-label">Telefon:</label>
						<div class="col-sm-8">
							<input class="form-control" type="text" name="telefon" pattern="[0-9]*" title="Samo brojevi su dozvoljeni" required />
						</div>
					</div>
					
					<div class="form-group">
						<label for="restoran-udaljenost" class="col-lg-2 control-label">Udaljenost:</label>
						<div class="col-sm-8">
							<input class="form-control" type="text" name="udaljenost" pattern="[0-9]*" title="Samo brojevi su dozvoljeni" required/>
						</div>
					</div>
						
					<div class="input_jelovnik_group form-group">
						<label for="namestaj-jelovnil" class="col-lg-2 control-label">Jelovnik:</label>
						<div class="col-sm-8">
							<select name="jelovnik" class="form-control">
								<c:forEach items="${jelovnici}" var="jelovnik">
									<option value="${jelovnik.id}">"${jelovnik.id}" 
									</option>
								</c:forEach>
							</select>
						</div>
					</div>
						
				</div>
				
				<div class="input_submit_group modal-footer">
					<input class="submit_adding_restoran btn btn-primary pull-left" type="submit" name="submit" value="Dodaj" />
				</div>	
					
			
			</form>
		</div>
				
	</div>



</body>
</html>