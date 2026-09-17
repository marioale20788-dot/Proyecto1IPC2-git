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
                    <label class="badge bg-secondary me-2 p-2">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-square mb-1" viewBox="0 0 16 16">
                        <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
                        <path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zm12 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1v-1c0-1-1-4-6-4s-6 3-6 4v1a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1z"/>
                        </svg> 
                        Nombre: ${sessionScope.usuario.nombre}
                    </label>

                    <label class="badge bg-success p-2">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cash-stack mb-1" viewBox="0 0 16 16">
                        <path d="M1 3a1 1 0 0 1 1-1h12a1 1 0 0 1 1 1zm7 8a2 2 0 1 0 0-4 2 2 0 0 0 0 4"/>
                        <path d="M0 5a1 1 0 0 1 1-1h14a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1H1a1 1 0 0 1-1-1zm3 0a2 2 0 0 1-2 2v4a2 2 0 0 1 2 2h10a2 2 0 0 1 2-2V7a2 2 0 0 1-2-2z"/>
                        </svg> 
                        Créditos: Q${sessionScope.usuario.credito}
                    </label>

                    <a id="inicioCliente" href="${pageContext.request.contextPath}/Frontend/Cliente/Cliente/InicioUsuario.jsp" class="btn btn-dark w-100 mb-2">
                        Inicio
                    </a>
                    <a id="inicioCliente" href="${pageContext.request.contextPath}/AdministradorSucusal/Cliente/ClienteVerViajes" class="btn btn-dark w-100 mb-2"">
                        Viajes 
                    </a>
                    <a id="inicioCliente" href="${pageContext.request.contextPath}/AdministradorSucursal/Clientes/Viajes/SolicitarViajePrivado"" class="btn btn-dark w-100 mb-2"">
                        Alquileres 
                    </a>

                    <a id="inicioCliente" href="${pageContext.request.contextPath}/Frontend/Cliente/Cliente/VerBoletosComprados.jsp"" class="btn btn-dark w-100 mb-2"">
                        Boletos comprados
                    </a>





                </c:if>


                <c:if test="${sessionScope.usuario.tipo eq 'ADMIN_SUCURSAL'}">

                    <label class="badge bg-secondary me-2 p-2">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-building-slash mb-1" viewBox="0 0 16 16">
                        <path d="M13.879 10.414a2.501 2.501 0 0 0-3.465 3.465zm.707.707-3.465 3.465a2.501 2.501 0 0 0 3.465-3.465m-4.56-1.096a3.5 3.5 0 1 1 4.949 4.95 3.5 3.5 0 0 1-4.95-4.95Z"/>
                        <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                        <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                        </svg>
                        Sucursal: ${sessionScope.idSucursal}
                    </label>


                    <label class="badge bg-secondary me-2 p-2">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-square mb-1" viewBox="0 0 16 16">
                        <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
                        <path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zm12 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1v-1c0-1-1-4-6-4s-6 3-6 4v1a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1z"/>
                        </svg>
                        Administrador: ${sessionScope.usuario.nombre}
                    </label>

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

                    </a>
                    <a id="adminTaller" href="${pageContext.request.contextPath}/AdministradorSucursal/Taller/validarTaller" class="btn btn-dark w-100 mb-2" >
                        Taller

                    </a>




                    <button id="administrarReportes" class="btn btn-dark w-100 mb-2" type="button" onclick="administrarReportes()">REPORTES</button>


                </c:if>


                <c:if test="${sessionScope.usuario.tipo eq 'ADMIN_SISTEMA'}">
                    <label class="badge bg-dark text-wrap w-100 text-start p-2">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-square" viewBox="0 0 16 16">
                        <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
                        <path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zm12 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1v-1c0-1-1-4-6-4s-6 3-6 4v1a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1z"/>
                        </svg>
                        Administrador: ${sessionScope.usuario.nombre}
                    </label>

                    <a id="crearViaje" href="${pageContext.request.contextPath}/Frontend/AdministradorSistema/CrearAdministradorDeSucursal.jsp?" class="btn btn-dark w-100 mb-2">
                        Crear administrador de sucursal

                    </a>
                    <a id="crearViaje" href="${pageContext.request.contextPath}/AdministrarSucursal/sucursales/CrearSucursal" class="btn btn-dark w-100 mb-2">
                        Crear administrador de sucursal

                    </a>
                    <a id="crearViaje" href="${pageContext.request.contextPath}/AdministradorSistema/AdministrarSucursal" class="btn btn-dark w-100 mb-2">
                        Administrar sucursales
                    </a>
                    <a id="crearViaje" href="${pageContext.request.contextPath}/AdministradorSistema/Sucursales/AdministrarUsuarios" class="btn btn-dark w-100 mb-2">
                        Administrar clientes
                    </a>




                </c:if>
                <form method="POST"action="${pageContext.request.contextPath}/AdministradorSucursal/Login/CerrarSesion">
                    <button class="btn btn-dark w-100 mb-2" type="submit"> CERRAR SESION</button>
                </form>



            </div>
        </header>



    </body>
</html>