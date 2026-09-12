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

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"/>
       <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

         <script src="${pageContext.request.contextPath}/resources/AdministradorSucursal/Js.js?v=2"></script>
        <title>Administrador de sucursal</title>       
 </head>
<body>
    <header class="position-fixed top-0 start-0 p-3 bg-primary text-white" style="width: 200px; min-height: 100vh;">
    <div class="nav nav-pills flex-column mb-auto w-100 gap-2">
            <label class="badge bg-secondary me-2">👤 nombre Usuario</label>
              <br>

            <label class="badge bg-info me-3">🏢 nombre sucursal</label>
            <br>

            <form id="inicio" method="GET" action="../../Frontend/AdministradorSucursal/Inicio.jsp" class="w-100">
                <button class="btn btn-primary w-100 mb-2" type="submit">INICIO</button>
            </form>

            <button id="administrarBuses" class="btn btn-primary w-100 mb-2" type="button" onclick="adminsitrarBuses()()">BUSES</button>
    
            <form id="crearBusForm" method="GET" action="../../Frontend/AdministradorSucursal/CrearBus.jsp" class="w-100">
                <button class="btn btn-primary w-100 mb-2" type="submit">CREAR BUS</button>
            </form>

            <form id="editarBus" method="GET" action="../AdministradorSucursal/editarBus.jsp" class="w-100">
                <button class="btn btn-primary w-100 mb-2" type="submit">BUSCAR BUS/EDITAR</button>
            </form>

       
            <button id="administrarChoferes" class="btn btn-primary w-100 mb-2" type="button" onclick="administrarChoferes()">CHOFERES</button>
            <button id="administrarRutas" class="btn btn-primary w-100 mb-2" type="button" onclick="administrarRutas()">RUTAS</button>
            <button id="administrarViajes" class="btn btn-primary w-100 mb-2" type="button" onclick="administrarViajes()">VIAJES</button>
            <button id="administrarTaller" class="btn btn-primary w-100 mb-2" type="button" onclick="administrarTaller()">TALLER</button>
            <button id="administrarReportes" class="btn btn-primary w-100 mb-2" type="button" onclick="administrarReportes()">REPORTES</button>

        </div>
    </header>

   
 
</body>
</html>