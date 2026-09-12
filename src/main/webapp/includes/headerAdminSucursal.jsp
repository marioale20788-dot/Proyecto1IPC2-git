<%-- 
    Document   : headerAdminSucursal
    Created on : 3/09/2026, 12:35:21 p. m.
    Author     : mario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="${pageContext.request.contextPath}/resources/AdministradorSucursal/Js.js?v=2"></script>
        <title>Administrador de sucursal</title>       
    </head>
    <body>
        <header class="position-fixed top-0 start-0 p-3 bg-secondary text-white" style="width: 200px; min-height: 100vh;">
            <div class="nav nav-pills flex-column mb-auto w-100 gap-2">
                <label class="badge bg-secondary me-2">👤 nombre Usuario</label>
                <br>

                <label class="badge bg-secondary me-2">🏢 nombre sucursal</label>
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
                <button id="administrarViajes" class="btn btn-dark w-100 mb-2" type="button" onclick="administrarViajes()">VIAJES</button>
                <button id="administrarTaller" class="btn btn-dark w-100 mb-2" type="button" onclick="administrarTaller()">TALLER</button>
                <button id="administrarReportes" class="btn btn-dark w-100 mb-2" type="button" onclick="administrarReportes()">REPORTES</button>

            </div>
        </header>



    </body>
</html>