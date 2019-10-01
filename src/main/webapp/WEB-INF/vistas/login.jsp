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
            <div class="d-none d-md-flex col-md-4 col-lg-4 bg-image-login"></div>
            <div class="col-md-8 col-lg-8">
                <div class="main d-flex align-items-center py-5">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-9 col-lg-8 mx-auto">
                            <c:if test="${not empty error}">
			   					<div class="alert alert-danger m-5" role="alert">${error}</div>
			   			 	</c:if>
                                <h2 class="main-heading text-primary text-center mb-4">Plan De Vuelo</h2>
                                <form action="validar-login" method="POST" modelAttribute="tripulante">
                                    <div class="form-label-group">
                                        <input name="email" type="email" id="email" class="form-control" placeholder="Email" required autofocus>
                                        <label for="email">Email</label>
                                    </div>
                                    <div class="form-label-group">
                                        <input name="password" type="password" id="password" class="form-control" placeholder="Contraseña" required>
                                        <label for="password">Contraseña</label>
                                    </div>
                                    <button class="btn btn-lg btn-primary btn-block font-weight-bold mb-2" type="submit">Entrar</button>
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