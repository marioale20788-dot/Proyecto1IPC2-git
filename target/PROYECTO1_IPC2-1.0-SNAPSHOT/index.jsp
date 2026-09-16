<%-- 
    Document   : index
    Created on : 2/09/2026, 12:09:33 p. m.
    Author     : mario
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="resources/index/Js.js"></script>
        <jsp:include page="/includes/resources.jsp"/>

        <title>ADMINISTRADOR DE BUSES</title>
    </head>
    <body>

        <div class="container d-flex justify-content-center align-items-center vh-100">
            <div class="card shadow-lg p-4 rounded-4 text-bg-secondary" style="max-width: 400px; width: 100%;">
                <div class="card-body">
                    <h1 class="card-title text-center mb-4 fw-bold">Iniciar Sesión</h1>
                    <form method="GET" action="${pageContext.request.contextPath}/AdministradorSucursal/Login/validarLogin">
                        <div class="mb-3">
                            <label class="form-label">Usuario</label>
                            <input type="text" name="nombreUsuario" class="form-control" placeholder="Ingrese su usuario">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Contraseña</label>
                            <input name="contrasena" type="password" class="form-control" placeholder="••••••••">
                        </div>
                        <button type="submit" class="btn btn-primary w-100 py-2" >Ingresar</button>
                        <c:if test="${not empty error}">
                            <b> ${error}</b>

                        </c:if>
                    </form>
                </div>
            </div>
        </div>







    </body>
</html>