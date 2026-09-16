<%-- 
    Document   : BuscarElminiarRuta
    Created on : 14/09/2026, 11:09:23 a. m.
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

                <form class="row g-3" method="GET" action="${pageContext.request.contextPath}/AdministradorSucursal/Leaflet/MostarRuta">

                    <div class="col-md-6">
                        <label class="form-label"> Nombre ruta </label>
                        <input class ="form-control" name="nombreRuta" />
                    </div>

                    <div class="col-md-2">
                        <label class="form-label"> Realizar busqueda </label>
                        <button class="btn btn-dark w-100 mb-2" type="submit" >buscar</button>
                    </div>




                </form>


                <div id="mapa" style="height: 400px; width: 100%;" class="rounded-3 border shadow-sm"></div>
                <c:if test="${not empty sucursalOrigen and not empty sucursalesDestino and not empty rutas}">

                    <input type="hidden" id="iniciarRuta" value="true" />
                    <input type="hidden" id="origenLatitud" value="${sucursalOrigen.latitud}" />
                    <input type="hidden" id="origenLongitud" value="${sucursalOrigen.longitud}" />
                    <input type="hidden" id="origenNombre" value="${sucursalOrigen.idSucursal}" />

                    <c:forEach items="${sucursalesDestino}" var="sucursal">
                        <input type="hidden" class="destinoLatitud" value="${sucursal.latitud}" />
                        <input type="hidden" class="destinoLongitud" value="${sucursal.longitud}" />
                        <input type="hidden" class="destinoNombre" value="${sucursal.idSucursal}" />

                    </c:forEach>

                    <br
                        <h1 class="fw-extrabold text-uppercase tracking-wide text-black-gradient mb-3"><b>RUTAS DISPONIBLES</b> </h1>
                    <div class="container mt-4">
                        <div class="card shadow-sm border-0 rounded-3">

                            <table class="table table-hover table-striped align-middle mb-0">
                                <thead class="table-dark text-uppercase small">
                                    <tr>
                                        <th  class="py-3 px-4"> ruta </th>
                                        <th  class="py-3 px-4"> id sucursal origen </th>
                                        <th  class="py-3 px-4"> id sucursal destino </th>
                                        <th  class="py-3 px-4"> distancia en km </th>
                                        <th  class="py-3 px-4"> Eliminar </th>

                                    </tr>
                                </thead>

                                <c:forEach items="${rutas}" var="Ruta">  
                                    <tbody>
                                        <tr>



                                            <td class="py-3 px-4 fw-bold text-secondary">${Ruta.nombreRutal}</td>
                                            <td class="py-3 px-4 fw-bold text-secondary">${Ruta.idSucursalOrigen}</td>
                                            <td class="py-3 px-4 fw-bold text-secondary"> ${Ruta.idSucursalDestino}</td>                              
                                            <td class="py-3 px-4 fw-bold text-secondary">${Ruta.distanciaEnKm} km</td>
                                            <td class="py-3 px-4 fw-bold text-secondary">  

                                                <form method="POST" action="${pageContext.request.contextPath}/AdministradorSucursal/Leaflet/MostarRuta?nombreRutaEliminar=${Ruta.nombreRutal} ">

                                                    <button type="submit" class="btn btn-close-white w-100 mb-2""> ELIMINAR </button>
                                                </form>

                                            </td>
                                        </tr>
                                    </tbody>


                                </c:forEach>

                            </c:if>

                        </table>
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger"> ${error}</div>
                        </c:if>


                        <c:if test="${not empty resultadoEliminarRuta}">

                            <div class="alert alert-danger"> ${resultadoEliminarRuta}</div>
                        </c:if>




                    </div>
                </div>
            </div>
        </main>


    </body>
</html>
