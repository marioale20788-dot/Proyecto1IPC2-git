<%-- 
    Document   : cambiarDatosBus
    Created on : 9/09/2026, 11:22:17 p. m.
    Author     : mario
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="AdministradorSucursal.BusJdbc"%>
<%@page import="AdministradorSucursal.Conector"%>
<%@page import="AdministradorSucursal.Bus"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CAMBIAR DATOS</title>
        <script src="${pageContext.request.contextPath}/resources/AdministradorSucursal/JsAdministrarBuses.js?v=1.1"></script>
        <jsp:include page="/includes/resources.jsp"/>

    </head>
    <body >

        <jsp:include page="/includes/headerAdminSucursal.jsp"/>
        <main>
            <div class="container mt-4">
                <div class="card">
                    <div class="card-header bg-black text-white p-3 d-flex justify-content-between align-items-center"> 

                        <c:choose>
                            <c:when  test="${not empty error}">
                                <div class="alert alert-danger" role="alert"> ${error}</div>
                            </c:when>                        
                            <c:when  test="${not empty bus}">

                                <span>DETALLES VEHICULO</span>         
                                <div>
                                    <b>Estado:</b> 
                                    <span class="badge bg-success">${bus.estado}</span>
                                </div>
                            </div>
                            <div class="card-body">
                             <div class="row g-3 align-items-center">
                                 <div class="col-md-8">
                                        <div class="row g-2">
                                            <div class="col-md-4 p-3 bg-light border rounded">
                                                <b>Placa:</b> ${bus.numeroPlaca}  </div>
                                            <div class="col-md-4 p-3 bg-light border rounded">
                                                <b>Marca:</b> ${bus.marca} </div>
                                            <div class="col-md-4 p-3 bg-light border rounded">
                                                <b>Modelo:</b> ${bus.modelo} </div>
                                            <div class="col-md-4 p-3 bg-light border rounded">
                                                <b>Fabricación:</b> ${bus.fechaDeFabricacion}</div>
                                            <div class="col-md-4 p-3 bg-light border rounded">
                                                <b>Km Actual:</b>  ${bus.kilometrajeActual}  </div>
                                            <div class="col-md-4 p-3 bg-light border rounded">
                                                <b>Pasajeros:</b> ${bus.capacidadPasajeros}  </div>
                                            <div class="col-md-12 p-3 bg-light border rounded">
                                                <b>Sucursal Asociada:</b> ${bus.idSucursalAsociada}  </div>
                                        </div>
                                    </div>

                                    <div class="col-md-4 text-center">
                                        <img src="${bus.foto}" class="img-thumbnail w-100" style="max-height: 220px;"">
                                    </div>

                                </div>
                            </div>
                        </div>
                        <form id ="crearBus" method="POST" action="${pageContext.request.contextPath}/ValidarEntradas/validarBusEditarBus?placaBus=${bus.numeroPlaca}" >
                            <div class="col-12">
                                <label  class="form-label" > Editar </label>
                                <select id ="selectItem"class ="form-control"name="itemSeleccionado" onchange="cambiarItem()">
                                    <option value="estado_operativo">Estado</option>
                                    <option value="marca">Marca</option>
                                    <option value="modelo">Modelo</option>
                                    <option value="foto">Foto</option>
                                    <option value="fabricacion">año fabricacion </option>                          
                                    <option value="kilometraje_actual">kilometraje actual</option>
                                    <option value="capacidad_pasajeros">pasajeros</option>
                                    <option value="id_sucursal">sucursal asocidada</option>

                                </select>

                            </div>
                            <div id="editarBus" class="col-12">
                                <label id="itemEditar" class ="form-label"> ITEM A EDITAR</label>
                                <input id="inputNuevoItem" class="form-control" name="entradaNuevoItem" />
                                <select class ="form-control" id="estadoItem"name="itemEstado" >
                                    <option value="true" >Activo</option>
                                    <option value="false">Inactivo</option>

                                </select>
                                <button class="btn btn-dark w-100 mb-2" type="submit" >Editar</button>

                            </div>



                        </form> 
                        <c:if test="${not empty resultado}">
                             <div class="alert alert-danger" role="alert"> ${resultado}</div>
                        </c:if>
                                         
                    </c:when> 

                </c:choose>




            </div>
        </main>

    </body>
</html>
