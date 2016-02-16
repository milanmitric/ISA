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
		
		<title>Izmena restorana</title>
		
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<meta HTTP-EQUIV="Expires" CONTENT="-1">
</head>
	<c:if test="${(empty menadzer)}">
				<c:redirect url="./login.jsp" />
	</c:if>

<body>
<jsp:include page="header.jsp"/>
	<div class="modal-dialog">
		<div class="modal-content">
			<form class="form-horizontal" id="izmenirestoran_form" action="./IzmeniRestoranController" accept-charset="UTF-8">
				<div class="modal-header">
					<h4> Izmenite podatke restorana</h4>
				</div>
					
				<div class="modal-body">
					
					<div class="form-group">
						<label for="restoran-naziv" class="col-lg-2 control-label">Naziv:</label>
						<div class="col-sm-8">
							<input class="form-control" type="text" name="naziv" value="${restoranID.nazivRestorana}" required />
						</div>
					</div>
					
					<div class="form-group">
						<label for="restoran-tip" class="col-lg-2 control-label">Tip:</label>
						<div class="col-sm-8">
							<input class="form-control" type="text" name="tip" value="${restoranID.tipRestoran}" required />
						</div>
					</div>
					
					<div class="form-group">
						<label for="restoran-adresa" class="col-lg-2 control-label">Adresa:</label>
						<div class="col-sm-8">
							<input class="form-control" type="text" name="adresa" value="${restoranID.adresaRestoran}" required />
						</div>
					</div>
					
					<div class="form-group">
						<label for="restoran-mail" class="col-lg-2 control-label">E-mail:</label>
						<div class="col-sm-8">
							<input class="form-control" type="text" name="mail" value="${restoranID.mailRestoran}" required />
						</div>
					</div>
					
					<div class="form-group">
						<label for="restoran-telefon" class="col-lg-2 control-label">Telefon:</label>
						<div class="col-sm-8">
							<input class="form-control" type="text" name="telefon" value="${restoranID.telefonRestoran}" pattern="[0-9]*" title="Samo brojevi su dozvoljeni" required />
						</div>
					</div>
					
					<div class="form-group">
						<label for="restoran-udaljenost" class="col-lg-2 control-label">Udaljenost:</label>
						<div class="col-sm-8">
							<input class="form-control" type="text" name="udaljenost" value="${restoranID.udaljenostRestoran}" pattern="[0-9]*" title="Samo brojevi su dozvoljeni" required/>
						</div>
					</div>

						
				</div>
				
				<div class="input_submit_group modal-footer">
					<input type="hidden" name="itemId" value="${restoranID.id}">
					<input class="submit_adding_restoran btn btn-primary pull-left" type="submit" name="submit" value="Dodaj" />
				</div>	
					
			
			</form>
		</div>
				
	</div>



</body>
</html>