<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/scrolling-nav.css" rel="stylesheet">
    <title>Plan De Vuelo</title>
</head>
<body id="page-top">
	
	<!-- NAVIGATION -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <div class="container">
      <a class="navbar-brand js-scroll-trigger" href="#page-top">Plan De Vuelo</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a class="nav-link" href="register">Registrar Tripulante</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="tripulantes">Ver Tripulantes</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#services">Services</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" href="#contact">Contact</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="login">Salir</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

	<div class="container-fluid">
		<div class="text-center my-5">
			<h2>${listaUsuarios}</h2>
		</div>

		<div class="row mt-5">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<table class="table table-bordered text-center">
					<thead>
						<tr>
							<th scope="col">Posicion</th>
							<th scope="col">Nombre</th>
							<th scope="col">Edad</th>
							<th scope="col">Peso</th>
							<th scope="col">Altura</th>
							<th scope="col">Modificar</th>
							<th scope="col">Eliminar</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${jugadores}" var="jugador">
							<tr>
							<td>${jugador.posicion}</td>
							<td>${jugador.nombre}</td>
							<td>${jugador.edad}</td>
							<td>${jugador.peso}</td>
							<td>${jugador.altura}</td>
							<td><form action="modificarJugador" method="POST" modelAttribute = "jugador">
								<input type="hidden" name="id" value="${jugador.id}" id="id">
								<button class="btn btn-lg btn-primary font-weight-bold mb-2" type="submit">Editar</button>
								</form>
							</td>
							<td><form action="eliminarJugador" method="POST" modelAttribute = "jugador">
								<input type="hidden" name="id" value="${jugador.id}" id="id">
								<button class="btn btn-lg btn-primary  font-weight-bold mb-2" type="submit">Eliminar</button>
								</form>
							</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
					<a class="btn btn-lg btn-primary float-right font-weight-bold mb-2" href="crearEquipo2" role="button">Agregar Jugador</a>
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>
   	
	<script src="js/jquery-3.4.1.min.js"></script>
	<script src="js/jquery.easing.min.js"></script>
   	<script src="js/bootstrap.min.js" type="text/javascript"></script>
	<script src="js/scrolling-nav.js" type="text/javascript"></script>
</body>
</html>