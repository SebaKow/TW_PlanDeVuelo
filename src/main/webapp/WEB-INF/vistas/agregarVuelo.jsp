<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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

	<div class="container-fluid">
        <div class="row no-gutter">
            <div class="d-none d-md-flex col-md-4 col-lg-4 bg-image-agregar"></div>
            <div class="col-md-8 col-lg-8">
                <div class="main d-flex align-items-center py-5">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-9 col-lg-8 mx-auto">
                                <h2 class="main-heading text-primary text-center mb-4">Agregar Vuelo</h2>
                                <form action="agregarVuelo" method="POST" modelAttribute="vuelo">
                                	<div class="form-label-group">
                                        <input name="origen" type="text" id="origenAg" class="form-control" placeholder="Origen" required autofocus>
                                        <label for="origenAg">Origen</label>
                                    </div>
                                    <div class="form-label-group">
                                        <input name="destino" type="text" id="destinoAg" class="form-control" placeholder="Destino" required>
                                        <label for="destinoAg">Destino</label>
                                    </div>
                                    <div class="form-label-group">
                                        <input name="duracion" type="time" id="duracionAg" class="form-control" placeholder="Duración" required>
                                        <label for="duracionAg">Duración</label>
                                    </div>
                                    <button class="btn btn-lg btn-primary btn-block font-weight-bold mb-2" type="submit">Agregar</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
   	</div>
   	
	<script src="js/jquery-3.4.1.min.js"></script>
   	<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>