<%-- 
    Document   : Login
    Created on : 13/09/2026, 11:21:48 a. m.
    Author     : mario
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/includes/resources.jsp"/>
        <script src="${pageContext.request.contextPath}/resources/index/Js.js?v=2"></script>
        <title>LGOIN</title>
    </head>
    <body>

    <c:if test="${not empty usuario}">
    <div class="container text-center mt-4" style="max-width: 450px;">
        
        <h2>BIENVENIDO ${usuario.nombre}</h2>
        <p>SELECCIONE UNA SUCURSAL</p>

        <c:if test="${empty sucursales}">
            <div class="alert alert-warning mb-2">NO HAY SUCURSALES DISPONIBLES</div>
            <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-secondary btn-sm">ACEPTAR</a>
        </c:if> 

        <c:if test="${not empty sucursales}">
            <c:forEach items="${sucursales}" var="Sucursal">
                <form method="POST" action="${pageContext.request.contextPath}/AdministradorSucursal/Login/validarLogin?idSucursal=${Sucursal.idSucursal}" class="card p-3 mb-3 shadow-sm">
                    
                    <div class="mb-2">
                        <span class="badge bg-secondary p-2 me-1">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-building mb-1" viewBox="0 0 16 16">
                                <path d="M4 2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5zm3 0a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5zm3 0a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5zm-6 3a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5zm3 0a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5zm3 0a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5zm-6 3a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5zm3 0a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5zm3 0a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5z"/>
                                <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v14a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1zm11 0H3v14h3v-2.5a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 .5.5V15h3z"/>
                            </svg>
                            Nombre: ${Sucursal.nombre}
                        </span>

                        <span class="badge bg-dark p-2">
                            ID: ${Sucursal.idSucursal}
                        </span>
                    </div>

                    <button class="btn btn-dark w-100" type="submit">SELECCIONAR</button>
                </form>
            </c:forEach>
        </c:if> 

    </div>
</c:if>



    </body>
</html>
