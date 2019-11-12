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
								<input type="hidden" name="id" id="id" value="${tripulante.id}">
								<button class="btn btn-lg btn-primary font-weight-bold mb-2" type="submit">Editar</button>
								</form>
							</td>
							<td><form action="eliminarTripulante" method="GET" id="search_form">
								<input type="hidden" name="id" id="id" value="${tripulante.id}">
								<button class="btn btn-lg btn-primary font-weight-bold mb-2" type="button" data-toggle="modal" data-target="#exampleModalCenter">Eliminar</button>
								</form>
							</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
					<a class="btn btn-lg btn-primary float-right font-weight-bold mb-2" href="agregarTripulante" role="button">Agregar</a>
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>
	
	<!-- ALERTA PARA CONFIRMAR DELETE -->
	<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLongTitle">Eliminar Tripulante</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	¿Está seguro que desea eliminar este tripulante?
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-danger font-weight-bold" data-dismiss="modal">Cancelar</button>
	        <button type="submit" class="btn btn-success font-weight-bold" form="search_form">Aceptar</button>
	      </div>
	    </div>
	  </div>
	</div>
   	
	<script src="js/jquery-3.4.1.min.js"></script>
   	<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>