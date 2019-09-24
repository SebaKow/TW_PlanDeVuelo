<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/scrolling-nav.css" rel="stylesheet">
    <title>Plan De Vuelo</title>
</head>
<body>

	<div class="container-fluid">
        <div class="row no-gutter">
            <div class="d-none d-md-flex col-md-4 col-lg-4 bg-image-register"></div>
            <div class="col-md-8 col-lg-8">
                <div class="main d-flex align-items-center py-5">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-9 col-lg-8 mx-auto">
                                <h2 class="main-heading text-primary text-center mb-4">Plan De Vuelo</h2>
                                <form action="registrar-usuario" method="POST" modelAttribute="usuario">
                                    <div class="form-label-group">
                                        <input name="email" type="email" id="emailRegistro" class="form-control" placeholder="Email" required>
                                        <label for="emailRegistro">Email</label>
                                    </div>
                                    <div class="form-label-group">
                                        <input name="password" type="password" id="passwordRegistro" class="form-control" placeholder="Contraseña" required>
                                        <label for="passwordRegistro">Contraseña</label>
                                    </div>
                                    <div class="form-label-group">
                                        <input name="nombre" type="text" id="nombreRegistro" class="form-control" placeholder="Nombre" required>
                                        <label for="passwordRegistro">Nombre</label>
                                    </div>
                                    <div class="form-label-group">
                                        <input name="apellido" type="text" id="apellidoRegistro" class="form-control" placeholder="Apellido" required>
                                        <label for="passwordRegistro">Apellido</label>
                                    </div>
                                    <div class="form-label-group">
                                        <input name="dni" type="number" id="dniRegistro" class="form-control" placeholder="DNI" required>
                                        <label for="passwordRegistro">DNI</label>
                                    </div>
                                    <button class="btn btn-lg btn-primary btn-block font-weight-bold mb-2" type="submit">Registrar</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
   	</div>
   	
	<script src="js/jquery-3.4.1.min.js"></script>
	<script src="js/jquery.easing.min.js"></script>
   	<script src="js/bootstrap.min.js" type="text/javascript"></script>
	<script src="js/scrolling-nav.js" type="text/javascript"></script>
</body>
</html>