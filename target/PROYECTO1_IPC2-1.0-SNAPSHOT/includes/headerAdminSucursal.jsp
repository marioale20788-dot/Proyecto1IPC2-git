<%-- 
    Document   : headerAdminSucursal
    Created on : 3/09/2026, 12:35:21 p. m.
    Author     : mario
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="${pageContext.request.contextPath}/resources/AdministradorSucursal/Js.js?v=2.1"></script>
        <title>Administrador de sucursal</title>       
    </head>
    <body>
        <header class="position-fixed top-0 start-0 p-3 bg-secondary text-white" style="width: 200px; min-height: 100vh;">
            <div class="nav nav-pills flex-column mb-auto w-100 gap-2">

                <c:if test="${sessionScope.usuario.tipo eq 'CLIENTE'}">
                    <label class="badge bg-secondary me-2">👤${sessionScope.usuario.nombre}</label>
                    <br>

                    <label class="badge bg-secondary me-2">🏢 ${sessionScope.usuario.tipo}</label>
                    <br>

                    <a id="editarBus" href="${pageContext.request.contextPath}/Frontend/Cliente/Cliente/InicioUsuario.jsp" class="btn btn-close-white w-100 mb-2">
                       Inicio
                    </a>




                </c:if>


                <c:if test="${sessionScope.usuario.tipo eq 'ADMIN_SUCURSAL'}">

                    <label class="badge bg-secondary me-2">👤${sessionScope.usuario.nombre}</label>
                    <br>

                    <label class="badge bg-secondary me-2">🏢 ${sessionScope.idSucursal}</label>
                    <br>

                    <a href="${pageContext.request.contextPath}/Frontend/AdministradorSucursal/Inicio.jsp" class="btn btn-dark w-100 mb-2">
                        INICIO
                    </a>

                    <button id="administrarBuses" class="btn btn-dark w-100 mb-2" type="button" onclick="adminsitrarBuses()">BUSES</button>

                    <a id="crearBusForm" href="${pageContext.request.contextPath}/Frontend/AdministradorSucursal/Buses/CrearBus.jsp" class="btn btn-close-white w-100 mb-2">
                        CREAR BUS
                    </a>

                    <a id="editarBus" href="${pageContext.request.contextPath}/Frontend/AdministradorSucursal/Buses/editarBus.jsp" class="btn btn-close-white w-100 mb-2">
                        BUSCAR/EDITAR  BUS
                    </a>


                    <button id="administrarChoferes" class="btn btn-dark w-100 mb-2" type="button" onclick="administrarChoferes()">CHOFERES</button>


                    <a id="crearChofer" href="${pageContext.request.contextPath}/Frontend/AdministradorSucursal/Chofer/CrearChofer.jsp" class="btn btn-close-white w-100 mb-2">
                        CREAR CHOFER

                    </a>
                    <a id="buscarChofer" href="${pageContext.request.contextPath}/Frontend/AdministradorSucursal/Chofer/MostrarChofer.jsp" class="btn btn-close-white w-100 mb-2">
                        BUS/EDITAR CHOFER

                    </a>


                    <button id="administrarRutas" class="btn btn-dark w-100 mb-2" type="button" onclick="administrarRutas()">RUTAS</button>

                    <a id="buscarRuta" href="${pageContext.request.contextPath}/AdministradorSucursal/Leaflet/MostarRuta" class="btn btn-close-white w-100 mb-2">
                        buscar/elimnar Ruta

                    </a>
                        
                    <a id="crearRuta" href="${pageContext.request.contextPath}/AdministradorSucursal/Leaflet/CrearRuta" class="btn btn-close-white w-100 mb-2">
                        Crear ruta

                    </a>
                        

                        <button id="administrarViajes" class="btn btn-dark w-100 mb-2" type="button" onclick="administrarViajes()">VIAJES</button>
                    
                     <a id="crearViaje" href="${pageContext.request.contextPath}/AdministradorSucursal/Viaje/Viaje" class="btn btn-close-white w-100 mb-2">
                        Crear viaje

                    </a>
                  <a id="verViaje" href="${pageContext.request.contextPath}/Frontend/AdministradorSucursal/Viajes/VerViajes.jsp" class="btn btn-close-white w-100 mb-2">
                        Ver viajes

                    </a>
                         <a id="viajePrivado" href="${pageContext.request.contextPath}/AdministradorSucursal/Viajes/AlquilerPrivado" class="btn btn-close-white w-100 mb-2">
                       viajes privados

                    </a>
                        
                    
                    
                    
                    
                    <button id="administrarTaller" class="btn btn-dark w-100 mb-2" type="button" onclick="administrarTaller()">TALLER</button>
                    <button id="administrarReportes" class="btn btn-dark w-100 mb-2" type="button" onclick="administrarReportes()">REPORTES</button>


                </c:if>
                    <form method="POST"action="${pageContext.request.contextPath}/AdministradorSucursal/Login/CerrarSesion"">
                   <button class="btn btn-dark w-100 mb-2" type="submit"> CERRAR SESION</button>
                </form>



            </div>
        </header>



    </body>
</html>