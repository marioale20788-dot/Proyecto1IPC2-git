<%-- 
    Document   : CrearRuta
    Created on : 12/09/2026, 8:29:46 p. m.
    Author     : mario
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/includes/FiltroSession.jsp" %>
<%@ include file="/includes/FiltroTipoEnEnlaceDirecto.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/includes/resourcesLeaflet.jsp"/>
        <jsp:include page="/includes/resources.jsp"/>
        <script src="${pageContext.request.contextPath}/resources/Leaflet/Rutas/CrearRutas.js?v=2"></script>


        <title>CREAR RUTA</title>

    </head>
    <body >

        <jsp:include page="/includes/headerAdminSucursal.jsp"/>
        <main>
            <div class="container mt-4" >
                <div class="card">
                   
                    <div class="card-header bg-black text-white p-3 d-flex justify-content-between align-items-center">
                        CREAR RUTAS
                    </div>
                    
                     <div class="card-body">
                                <form class="row g-3" method="POST" action="${pageContext.request.contextPath}/AdministradorSucursal/Leaflet/CrearRuta">

                    <div class="col-md-6">
                        <label class="form-label fw-bold">Nombre Ruta</label>
                        <input class="form-control" name="nombreRuta" />
                    </div>

                    <div class="col-md-6">
                        <label class="form-label fw-bold">Distancia en km</label>
                        <input class="form-control" name="distanciaKm" />
                    </div>

                    <div class="col-md-12">
                        <label class="form-label fw-bold">Sucursal Destino</label>
                        <select class="form-select" name="sucursalDestino" >
                            <c:forEach items="${sucursales}" var="sucursal">
                                <option value="${sucursal.idSucursal}">  ${sucursal.idSucursal} --- ${sucursal.nombre}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-12 mt-4">
                        <button type="submit" class="btn btn-dark w-100">Guardar Ruta</button>
                    </div>

                </form>
                        
                    </div>
                    
                    
                </div>

             
        
                <c:if test="${not empty crearRuta}">
                    
                    <div class="alert alert-danger" > ${crearRuta}</div>
                </c:if>



            </div>



        </main>


    </body>
</html>
