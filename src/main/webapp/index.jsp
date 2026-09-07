<%-- 
    Document   : index
    Created on : 2/09/2026, 12:09:33 p. m.
    Author     : mario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="resources/index/Js.js"></script>
        <link rel="stylesheet" href="resources/index/Styles.css"/>
        <title>ADMINISTRADOR DE BUSES</title>
    </head>
    <body>
        <h1 id="tituloInicio">ADMINISTRADOR DE BUSES</h1>
        <br>
       
        <div id="iniciarSesion" class="centrar"> INICIAR SESION
            <br>
            <label id="labelDpi">DPI: </label>
            <input type="text" class="ingresarTexto"id ="textDpi"/>
            <br>
            <label id="labelTipo">Tipo: </label>
            <input type="text" class="ingresarTexto", id ="textTipo"/>
            <br>
            <button type="button" onclick="buscarUsuario()">ACEPTAR</button>
            <a href="AdministradorSucursal/Inicio.jsp">admin sucursal</a>
        </div>

    </body>
</html>