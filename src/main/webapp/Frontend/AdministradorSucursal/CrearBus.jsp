<%-- 
    Document   : CrearBus
    Created on : 7/09/2026, 11:05:44 a. m.
    Author     : mario
--%>

<%@page import="AdministradorSucursal.Bus"%>
<%@page import="java.sql.SQLException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="AdministradorSucursal.Conector" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


        <title>CREAR BUS</title>
    </head>
    <body >
        <jsp:include page="../AdministradorSucursal/headerAdminSucursal.jsp"/>
        <div id ="contenido" style="margin-left: 200px ; margin-bottom: 75px">
            <div id ="row g-3 align-items-center">
                <form id ="crearBus" method="GET" action="../../Backend/Validaciones/validarBus.jsp">
                    <label class="form-label" > Numero de placa </label>
                    <input class ="form-control"name="numeroPlaca"/>
                    <label  class="form-label" > Marca </label>
                    <input class ="form-control"name="marca"/>
                    <label class="form-label"  > modelo </label>
                    <input class ="form-control"name="modelo"/>
                    <label  class="form-label" > foto </label>
                    <input class ="form-control"name="foto"/>
                    <label  class="form-label"  > fabricacion </label>
                    <input class ="form-control" name="yearFabricacion"/>
                    <label  class="form-label" > kilometraje actual </label>
                    <input class ="form-control"name="kilometrajeActual"/>
                    <label  class="form-label" > capacidad de pasajeros </label>
                    <input class ="form-control"name="capacidadPasajeros"/>
                    <label  class="form-label" > Estado </label>
                    <select class ="form-control"name="estado">
                        <option value="true">activo</option>
                        <option value="false">desactivado</option>
                    </select>
                    <label  class="form-label" > id sucursal asociada  </label>
                    <input class ="form-control"name="idSucursal"/>

                    <br>
                    <button class="btn btn-primary w-100 mb-2" type="submit" > Crear bus</button>
                </form>
                <%
                    String texto = (String) request.getAttribute("Errores");
                    String resultado = (String) request.getAttribute("resultado");
                    if (texto != null) {%>

                <d> ERRORES - <%=texto%></d>

                <% }
                    if (resultado != null) {%>

                <d> RESULTADO - <%=resultado%></d>

                <%}%>

            </div>

        </div>
    </body>
</html>
