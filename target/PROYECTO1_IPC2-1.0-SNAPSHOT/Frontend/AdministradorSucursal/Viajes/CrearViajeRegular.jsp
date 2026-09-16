<%-- 
    Document   : CrearViajeRegular
    Created on : 14/09/2026, 3:02:11 p. m.
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
        <title>CREAR VIAJE</title>


    </head>
    <body >

        <jsp:include page="/includes/headerAdminSucursal.jsp"/>
        <main>
            <div class="container mt-4" >
                <div class="card">
                    <div class="card-header bg-black text-white p-3 d-flex justify-content-between align-items-center">
                        CREAR NUEVO VIAJE
                    </div>
                    <div class="card-body">
                        <form id="crearBus" class="row g-3" method="POST" action="${pageContext.request.contextPath}/AdministradorSucursal/Viaje/Viaje?idViaje=${idViaje}">


                            <div class="col-12">
                                <label  class="form-label fw-bold"> ID Viaje: ${idViaje} </label>
                             
                            </div>


                            <div class="col-md-6">
                                <label class="form-label"> Buses disponibles: </label>
                                <select class="form-select" name="busSeleccionado">
                                    <c:forEach items="${busesDisponibles}" var="Bus">
                                        <option value="${Bus.numeroPlaca}"> Placa: ${Bus.numeroPlaca} ------- capacidad: ${Bus.capacidadPasajeros}</option>
                                    </c:forEach>
                                </select>
                            </div>


                            <div class="col-md-6">
                                <label class="form-label"> Choferes disponibles: </label>
                                <select class="form-select" name="choferSeleccionado">
                                    <c:forEach items="${choferesDisponibles}" var="chofer">
                                        <option value="${chofer.numeroDeLicencia}"> Licencia: ${chofer.numeroDeLicencia} -------- Nombre: ${chofer.nombreCompleto} -------- salario base: ${chofer.salarioBase}</option>
                                    </c:forEach>
                                </select>
                            </div>


                            <div class="col-md-6">
                                <label class="form-label"> Ruta: </label>
                                <select class="form-select" name="rutaSeleccionada">
                                    <c:forEach items="${rutas}" var="ruta">
                                        <option value="${ruta.nombreRutal}"> Nombre: ${ruta.nombreRutal} --------- distancia: ${ruta.distanciaEnKm} km</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-md-6">
                                <label class="form-label">Precio por boleto:</label>
                                <input class="form-control" name="precioBoleto" />
                            </div>

                            <div class="col-md-6">
                                <label class="form-label"> Fecha y hora de salida: </label>
                                <input type="datetime-local" class="form-control" name="salida" /> 
                            </div>

                            <div class="col-md-6">
                                <label class="form-label"> Fecha y hora de llegada: </label>
                                <input type="datetime-local" class="form-control" name="llegada" />
                            </div>

                            <div class="col-12 mt-4">
                                <button class="btn btn-dark w-100" type="submit"> Crear viaje </button>
                            </div>

                        </form>
                    </div>
                </div>


                <c:if test="${not empty resultado}">

                    <div class="alert alert-danger"> MENSAJE:${resultado}</div>

                </c:if>


            </div>


        </main>


    </body>
</html>
