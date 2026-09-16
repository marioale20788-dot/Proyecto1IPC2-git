<%-- 
    Document   : editarViaje
    Created on : 15/09/2026, 1:14:40 p. m.
    Author     : mario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/includes/FiltroSession.jsp" %>
<%@ include file="/includes/FiltroTipoEnEnlaceDirecto.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/includes/resources.jsp"/>
        <script src="${pageContext.request.contextPath}/resources/AdministradorSucursal/JsAdministrarViaje.js?v=1.1"></script>
        <title> AGREGAR CHOFER </title>
    </head>
    <body>
        <jsp:include page="/includes/headerAdminSucursal.jsp"/>
        <main
            <main>
                <div class="container mt-4">
                    <div class="card">
                        <div class="card-header bg-black text-white p-3 d-flex justify-content-between align-items-center"> 

                            <c:choose>
                                <c:when  test="${not empty error}">
                                    <div class="alert alert-danger" role="alert"> ${error}</div>
                                </c:when>                        
                                <c:when  test="${not empty viaje}">

                                    <b> VIAJE</b>         
                                    <div>

                                        <b>Estado:</b> 
                                        <b class="badge bg-success">${viaje.estado}</b>
                                    </div>
                                </div>

                                <div class="card-body">
                                    <div class="row g-3 align-items-center">
                                        <div class="col-md-8">
                                            <div class="row g-2">
                                                <div class="col-md-4 p-3 bg-light border rounded">
                                                    <b>id: </b> ${viaje.idViaje}</div>
                                                <div class="col-md-4 p-3 bg-light border rounded">
                                                    <b>Tipo:</b> ${viaje.tipo}</div>
                                                <div class="col-md-4 p-3 bg-light border rounded">
                                                    <b>placa bus:</b> ${viaje.idBus} </div>
                                                <div class="col-md-4 p-3 bg-light border rounded">
                                                    <b>Salida estimada:</b> ${viaje.salida}</div>
                                                <div class="col-md-4 p-3 bg-light border rounded">
                                                    <b>Llegada estimada </b>  ${viaje.llegada}  </div>

                                                <div class="col-md-12 p-3 bg-light border rounded">
                                                    <b>Licencia chofer:</b> ${viaje.idChofer}  </div>
                                            </div>
                                            <c:if test="${not empty viaje.idRuta}">
                                                <div class="col-md-12 p-3 bg-light border rounded">
                                                    <b>nombre ruta: </b> ${viaje.idRuta}  </div>
                                            </div>
                                            <div class="col-md-4 p-3 bg-light border rounded">
                                                <b>Precio boleto:</b> ${viaje.precioBoleto}</div>

                                        </c:if>

                                        <c:if test="${not empty viaje.idViajeAlquiler}">
                                            <div class="col-md-12 p-3 bg-light border rounded">
                                                <b> viaje alquiler:  </b> ${viaje.idViajeAlquiler}  </div>
                                        </div>

                                    </c:if>
                                    <div>


                                    </div>
                                </div>



                            </div>

                            <form  class=""method="POST"action="${pageContext.request.contextPath}/AdministradorSucursal/Viajes/EditarViaje?idViaje=${viaje.idViaje}">
                                <div class="col-12">
                                    <label  class="form-label" >Editar</label>
                                    <select id ="selectItem"class ="form-control"name="itemSeleccionado" onchange="cambiarItemViaje()">
                                        <option value="id_bus">bus encargado</option>
                                        <option value="id_chofer">chofer encargado</option>
                                        <option value="fecha_salida_programada">fecha/hora salida</option>
                                        <option value="fecha_llegada_estimada">fecha/hora llegada</option>

                                        <c:if test="${not empty viaje.idRuta}">
                                            <option value="precio_boleto">Precio boleto</option>     
                                            <option value="id_ruta">Ruta</option>     

                                        </c:if>


                                    </select>

                                    <label id="itemEditar" class ="form-label"> ITEM A EDITAR</label>

                                    <input id="inputText" class="form-control" name="entradaTexto" />

                                    <select class ="form-control" id="Buses"name="busSeleccionado" >

                                        <c:forEach items="${buses}" var="Bus">

                                            <option value="${Bus.numeroPlaca}" >Placa: ${Bus.numeroPlaca} ----- capacidad: ${Bus.capacidadPasajeros}</option>
                                        </c:forEach>


                                    </select>

                                    <select class ="form-control" id="choferes"name="choferSeleccionado" >
                                        <c:forEach items="${choferes}" var="chofer">

                                            <option value="${chofer.numeroDeLicencia}" >Licencia: ${chofer.numeroDeLicencia} ----- Nombre: ${chofer.nombreCompleto}---------- ${chofer.salarioBase} </option>
                                        </c:forEach>


                                    </select>


                                    <input type="datetime-local" id="fechaHora" name="fecha"/>

                                    <c:if test="${not empty viaje.idRuta}">

                                        <select class ="form-control" id="rutas"name="rutasSeleccionadas" >
                                            <c:forEach items="${rutas}" var="ruta">

                                                <option value="${ruta.nombreRutal}" >Nombre: ${ruta.nombreRutal} ----- Distancia:${ruta.distanciaEnKm} </option>
                                            </c:forEach>
                                        </select>
                                    </c:if>

                                    <br>


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