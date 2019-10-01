<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	          <a class="nav-link" href="login">Salir</a>
	        </li>
	      </ul>
	    </div>
	  </div>
	</nav>
  
  	<!-- EDITAR TRIPULANTE -->
	<div class="container-fluid my-5">
		<div class="text-center pt-5">
			<h2>Editar Tripulante</h2>
		</div>
		
		<div class="row mt-5">
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
				    </tr>
				  </thead>
				  <tbody>
				    <form action="editarTripulante" method= "POST" modelAttribute="tripulante">
				    <tr>
				      <td><input name="nombre" type="text" id="nombre" class="form-control" value="${tripulante.nombre}" required ></td>
				      <td><input name="apellido" type="text" id="apellido" class="form-control" value="${tripulante.apellido}" required></td>
				      <td><input name="dni" type="text" id="dni" class="form-control" value="${tripulante.dni}" required></td>
				      <td><input name="email" type="text" id="email" class="form-control" value="${tripulante.email}" required></td>
				      <td><input name="password" type="text" id="password" class="form-control" value="${tripulante.password}" required></td>					   
				    </tr>
				  </tbody>
				</table>
					<input name="id" type="hidden" id="id" value="${tripulante.id}">
					<button class="btn btn-lg btn-primary float-right font-weight-bold mb-2" type="submit">Editar</button>
				</form>
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>
	
	<script src="js/jquery-3.4.1.min.js"></script>
   	<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>