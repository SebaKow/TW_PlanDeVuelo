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
		<div class="text-center pt-5">
			<h2>Planes de Vuelo</h2>
		</div>
		
		<div class="row mt-4 mx-4">
			<c:forEach items="${listaPlanesDeVuelo}" var="plandevuelo">
				<div class="col-md-4">
					<div class="card w-100 mb-3">
						<div class="card-body">
							<h5 class="card-title text-center">${plandevuelo.descripcion}</h5>
							<form action="plandevueloseleccionado" method="GET" class="text-center">
								<input type="hidden" name="idPlanDeVuelo" id="idPlanDeVuelo" value="${plandevuelo.id}">
								<button class="btn btn-lg btn-primary font-weight-bold my-2" type="submit">Ver</button>
							</form>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
   	
	<script src="js/jquery-3.4.1.min.js"></script>
   	<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>