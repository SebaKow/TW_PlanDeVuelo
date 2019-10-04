<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
	        <li class="nav-item">
	          <a class="nav-link" href="tripulantes">Ver Tripulantes</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="vuelos">Ver Vuelos</a>
	        </li>
	        <li class="nav-item active">
	          <a class="nav-link" href="planesdevuelo">Ver Planes de Vuelo</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="login">Salir</a>
	        </li>
	      </ul>
	    </div>
	  </div>
	</nav>

	<div class="container-fluid my-5">
		<div class="row mt-4 pt-5">
			<div class="col-md-2"></div>
			<div class="col-md-2">
				<div class="text-left">
					<h4><span class="badge badge-secondary">Plan de Vuelo:</span></h4>
				</div>
			</div>
			<div class="col-md-4"></div>
			<div class="col-md-2">
				<div class="text-right">
					<h4><span class="badge badge-secondary">Fecha:</span></h4>
				</div>
			</div>
			<div class="col-md-2"></div>
		</div>
		
		<div class="row mt-4 pt-5">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<h4>Seleccione los vuelos que desea agregar al plan:</h4>
				<select class="custom-select">
					<option value="1">Vuelo 1</option>
					<option value="1">Vuelo 2</option>
					<option value="1">Vuelo 3</option>
				</select>
				<a class="btn btn-lg btn-primary float-right font-weight-bold my-3" href="#!" role="button">Agregar</a>
			</div>
			<div class="col-md-2"></div>
		</div>
		
		<h3 class="text-center my-3"> -- -- -- -- -- </h3>
		
		<!-- LISTA DE VUELOS DEL PLAN SELECCIONADO -->
		<div class="text-center">
			<h2>Vuelos Agregados</h2>
		</div>

		<div class="row mt-4">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<table class="table table-bordered text-center">
					<thead>
						<tr>
							<th scope="col">Origen</th>
							<th scope="col">Destino</th>
							<th scope="col">Duración</th>
							<th scope="col">Eliminar</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="" var="vuelo">
							<tr>
							<td>${vuelo.origen}</td>
							<td>${vuelo.destino}</td>
							<td>${vuelo.duracion}</td>
							<td><form action="eliminarVuelo" method="POST" modelAttribute="vuelo">
								<input type="hidden" name="id" value="${vuelo.id}" id="id">
								<button class="btn btn-lg btn-primary font-weight-bold mb-2" type="submit">Eliminar</button>
								</form>
							</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
					<a class="btn btn-lg btn-primary float-right font-weight-bold mb-2" href="#!" role="button">Finalizar</a>
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>
   	
	<script src="js/jquery-3.4.1.min.js"></script>
   	<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>