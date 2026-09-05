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
        <script src="../resources/AdministradorSucursal/Js.js"></script>
        <link rel="stylesheet" href="../resources/AdministradorSucursal//Styles.css"/>
        <title>Administrador de sucursal</title>       
    </head>
    <body>
        <header>
            <div id ="header">
                <button id ="administrarBuses" class="botton"type ="button"onclick="adminsitrarBuses()"> BUSES </button>
                <button id ="adminitrarChoferes"class="botton"type ="button"onclick="administrarChoferes()"> CHOFERES </button>
                <button id ="administrarRutas"type ="button"class="botton"onclick="administrarRutas()()()"> RUTAS </button>
                <button id ="administrarViajes"type ="button"class="botton"onclick="adminsitrarViajes()()"> VIAJES </button>
                <button id ="administrarTaller"type ="button"class="botton"onclick="adminsitrarTaller()()">TALLER</button>
                <button id ="administrarReportes"type ="button"class="botton"onclick="adminsitrarReportes()()">REPORTES</button>
                <div id ="nombreUsuario">
                    <label id ="labelUsuario"> nombre Usuario</label>
                    <br>
                    <label id="sucursal">nombre sucursal </label>
                    
                </div>
            </div>
        </header>
        <div id ="requestResultado"></div>
    </body>
</html>
