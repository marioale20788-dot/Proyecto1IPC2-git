<%-- 
    Document   : CrearBus
    Created on : 7/09/2026, 11:05:44 a. m.
    Author     : mario
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="AdministradorSucursal.Bus"%>
<%@page import="java.sql.SQLException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="AdministradorSucursal.Conector" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/includes/resources.jsp"/>

        <title>CREAR BUS</title>
    </head>
    <body >
        <jsp:include page="/includes/headerAdminSucursal.jsp"/>
        <main>
            <div class ="container mt-5" >
                <div class ="row g-3 align-items-center">
                    <form class="row g-3" id ="crearBus" method="POST" action="${pageContext.request.contextPath}/ValidarEntradas/validarBus">
                        <div class="col-md-6">
                            <label class="form-label"> Numero de placa </label>
                            <input class ="form-control"name="numeroPlaca"/>
                        </div>
                        <div class="col-md-6">
                            <label  class="form-label" > Marca </label>
                            <input class ="form-control"name="marca"/>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label"  > modelo </label>
                            <input class ="form-control"name="modelo"/>  
                        </div>
                        <div class="col-md-4">

                            <label  class="form-label" > kilometraje actual </label>
                            <input class ="form-control"name="kilometrajeActual"/>
                        </div>
                        <div class="col-md-2">
                            <label  class="form-label"  > fabricacion </label>
                            <input class ="form-control" name="yearFabricacion"/> 
                        </div>


                        <div class="col-12">
                            <label  class="form-label" > foto </label>
                            <input class ="form-control"name="foto"/>

                        </div>

                        <div class="col-12">
                            <label  class="form-label" > Estado </label>
                            <select class ="form-control"name="estado">
                                <option value="true">activo</option>
                                <option value="false">desactivado</option>
                            </select>
                        </div>

                        <div class="col-md-6">
                            <label  class="form-label" > capacidad de pasajeros </label>
                            <input class ="form-control"name="capacidadPasajeros"/>
                        </div>
                        <div class="col-md-6">
                            <label  class="form-label" > id sucursal asociada  </label>
                            <input class ="form-control"name="idSucursal"/>
                        </div>
                <br>
                        <button class="btn btn-dark w-100 mb-2" type="submit" > Crear bus</button>
                    </form>
                        
                    <c:if test="${not empty resultado}">
                        
                          <d class="alert alert-danger"> MENSAJE:${resultado}</d
                        
                    </c:if>
                 
                </div>

            </div>


        </main>

    </body>
</html>
