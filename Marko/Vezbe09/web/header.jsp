<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	
	<script src = "bootstrap.js"> 
	</script>
	<script src = "jquery.min.js"> 
	</script>

</head>
<body>


<nav class="navbar navbar-default" role="navigation">
<div class="container-fluid">
	
	<div class="navbar-header">
	
		<a class="navbar-brand" href="index.jsp">Saloni nameštaja</a>
	</div>

	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		
		<ul class="nav navbar-nav navbar-right">
			
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown">Login
					<span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li><a href="UserLogin.jsp">User login</a></li>
					<li class="divider"></li>
					<li><a href="AdminLogin.jsp">Admin&Manager login</a></li>
				</ul>
				</li>
				</ul>
	</div>
	
</div>




</body>
</html>