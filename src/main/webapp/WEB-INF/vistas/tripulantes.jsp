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
	          <a class="nav-link" href="planesdevuelo">Ver Planes de Vuelo</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="login">Salir</a>
	        </li>
	      </ul>
	    </div>
	  </div>
	</nav>

	<!-- LISTA DE TRIPULANTES -->
	<div class="container-fluid my-5">
		<div class="text-center pt-5">
			<h2>Lista de Tripulantes</h2>
		</div>

		<div class="row mt-4">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<table class="table table-bordered text-center">
					<thead>
						<tr>
							<th scope="col">Nombre</th>
							<th scope="col">Apellido</th>
							<th scope="col">DNI</th>
							<th scope="col">Email</th>
							<th scope="col">Contraseña</th>
							<th scope="col">Editar</th>
							<th scope="col">Eliminar</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listaTripulantes}" var="tripulante">
							<tr>
							<td>${tripulante.nombre}</td>
							<td>${tripulante.apellido}</td>
							<td>${tripulante.dni}</td>
							<td>${tripulante.email}</td>
							<td>${tripulante.password}</td>
							<td><form action="modificarTripulante" method="POST" modelAttribute="tripulante">
								<input type="hidden" name="id" value="${tripulante.id}" id="id">
								<button class="btn btn-lg btn-primary font-weight-bold mb-2" type="submit">Editar</button>
								</form>
							</td>
							<td><form action="eliminarTripulante" method="GET">
								<input type="hidden" name="idTripulante" value="${tripulante.id}" id="idTripulante">
								<button class="btn btn-lg btn-primary font-weight-bold mb-2" type="submit">Eliminar</button>
								
								</form>
							</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
					<a class="btn btn-lg btn-primary float-right font-weight-bold mb-2" href="agregar-tripulante" role="button">Agregar</a>
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>
   	
	<script src="js/jquery-3.4.1.min.js"></script>
   	<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>