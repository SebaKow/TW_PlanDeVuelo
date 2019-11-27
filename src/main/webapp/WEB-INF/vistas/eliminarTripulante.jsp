<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
    <title>Plan De Vuelo</title>
</head>
<body>
	
	<!-- NAVIGATION -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top">
	  <div class="container">
	    <a class="navbar-brand" href="homeAdmin">Plan De Vuelo</a>
	    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
	    	<span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarResponsive">
	      <ul class="navbar-nav ml-auto">
	        <li class="nav-item">
	          <a class="nav-link" href="homeAdmin">Inicio</a>
	        </li>
	        <li class="nav-item active">
	          <a class="nav-link" href="tripulantes">Ver Tripulantes</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="vuelos">Ver Vuelos</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="planesDeVuelo">Ver Planes de Vuelo</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="login">Salir</a>
	        </li>
	      </ul>
	    </div>
	  </div>
	</nav>
	
	<!-- ELIMINAR TRIPULANTE -->
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
			  <h5 class="modal-title" id="exampleModalLongTitle">Eliminar Tripulante</h5>
			</div>
			<div class="modal-body">
				¿Está seguro que desea eliminar el tripulante: ${tripulanteAEliminar.nombre} ${tripulanteAEliminar.apellido}?
			</div>
			<div class="modal-footer">
				<a class="btn btn-lg btn-primary font-weight-bold" href="tripulantes" role="button">Cancelar</a>
				<form action="eliminarTripulanteDeLista" method="GET">
					<input type="hidden" name="id" id="id" value="${tripulante.id}">
					<button type="submit" class="btn btn-lg btn-primary font-weight-bold">Aceptar</button>
				</form>
			</div>
		</div>
	</div>
   	
	<script src="js/jquery-3.4.1.min.js"></script>
   	<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>