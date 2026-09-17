<%-- 
    Document   : AlquilerPrivado
    Created on : 16/09/2026, 3:50:44 p. m.
    Author     : mario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/includes/FiltroSession.jsp" %>
<%@ include file="/includes/FiltroTipoEnEnlaceDirectoUsuario.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/includes/resources.jsp"/>
        <title>INICIO</title>


    </head>
    <body >

        <jsp:include page="/includes/headerAdminSucursal.jsp"/>
        <main>
            <div class="container mt-4">
    <div class="row">
   
        <div class="col-md-8 col-lg-9">
            <div class="card">
                <div class="card-header bg-black text-white p-3 d-flex justify-content-between align-items-center">
                    Solicitar viaje
                </div>

                <div class="card-body">
                    <form id="crearBus" class="row g-3" method="POST" action="${pageContext.request.contextPath}/AdministradorSucursal/Clientes/Viajes/SolicitarViajePrivado">

                        <div class="col-md-6">
                            <label class="form-label">Fecha salida:</label>
                            <input type="date" class="form-control" name="fechaSalida" />
                        </div>

                        <div class="col-md-6">
                            <label class="form-label"> Fecha retorno: </label>
                            <input type="date" class="form-control" name="fechaRetorno" /> 
                        </div>

                        <div class="col-md-6">
                            <label class="form-label"> Referencia origen </label>
                            <input class="form-control" name="referenciaOrigen" />
                        </div>

                        <div class="col-md-6">
                            <label class="form-label"> Referencia destino </label>
                            <input class="form-control" name="referenciaDestino" />
                        </div>

                        <div class="col-md-6">
                            <label class="form-label"> distancia km </label>
                            <input class="form-control" name="distanciaKm" />
                        </div>

                        <div class="col-md-6">
                            <label class="form-label"> Numero de pasajeros </label>
                            <input class="form-control" name="numeroPasajeros" />
                        </div>

                        <div class="col-md-6">
                            <label class="form-label"> Sucursal a la que desea solicitar </label>
                            <select class="form-select" name="sucursalSeleccionada">
                                <c:forEach items="${sucusalesDisponibles}" var="sucursal">
                                    <option value="${sucursal.idSucursal}"> ID: ${sucursal.idSucursal} -------- Nombre: ${sucursal.nombre}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-12 mt-4">
                            <button class="btn btn-dark w-100" type="submit"> Solicitar viaje </button>
                        </div>

                    </form>
                </div>
            </div>
        </div>

      
        <aside class="col-md-4 col-lg-3">
            <div class="card position-sticky">
                <div class="card-header bg-black text-white p-3 d-flex justify-content-between align-items-center">
                    ESPERANDO CONFIRMACION.......       
                </div>

                <div class="card-body">
                    <c:if test="${not empty alquileresPrivadosPorAprobar}">
                        <c:forEach items="${alquileresPrivadosPorAprobar}" var="AlquilerPrivado"> 
                            <div class="card text-bg-secondary mb-3">
                                <div class="card-header">id: ${AlquilerPrivado.idAlquilerPrivado} </div>
                                <div class="card-body">
                                    <p class="mb-1"> origen: ${AlquilerPrivado.origen}</p>
                                    <p class="mb-1"> destino: ${AlquilerPrivado.destino}</p>
                                    <p class="mb-1"> distancia : ${AlquilerPrivado.distancia} km</p>
                                    <p class="mb-2"> Precio estimado: ${AlquilerPrivado.precioEstimado}</p>
                                    <c:choose>
                                        <c:when test="${not empty AlquilerPrivado.precioFinal and AlquilerPrivado.precioFinal > 0}">
                                            <form method="POST" action="${pageContext.request.contextPath}/AdministradorSucursal/Clientes/Viajes/SolicitarViajePrivado?idViajePrivado=${AlquilerPrivado.idAlquilerPrivado}">

                                                <label class="form-label"> Precio final: ${AlquilerPrivado.precioFinal} </label>
                                                
                                                <select class="form-select mb-2" name="opcionAceptarEliminar">
                                                    <option value="eliminar"> ELIMINAR </option>
                                                    <option value="aceptar"> PAGAR </option> 
                                                </select>
                                                <button type="submit" class="btn btn-dark w-100 mb-2"> ACEPTAR</button>

                                            </form>  
                                        </c:when>

                                        <c:otherwise>
                                            <label class="form-label"> Precio final: ESPERANDO AUTORIZACION </label>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </aside>
    </div>

    <c:if test="${not empty resultado}">
        <div class="alert alert-danger mt-3">${resultado}</div>
    </c:if>
</div>




        </main>


    </body>
</html>
