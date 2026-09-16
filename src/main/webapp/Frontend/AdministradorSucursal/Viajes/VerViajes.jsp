<%-- 
    Document   : VerViajes
    Created on : 14/09/2026, 7:04:13 p. m.
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
        <script src="${pageContext.request.contextPath}/resources/AdministradorSucursal/modal.js?v=1.1"></script>
        <title>CONFIGURAR VIAJES</title>


    </head>
    <body >

        <jsp:include page="/includes/headerAdminSucursal.jsp"/>
        <main>
            <div class="container mt-4" >
                <form class="row g-3" method="GET" action="${pageContext.request.contextPath}/AdministradorSucursal/Viajes/VerViajes">

                    <div class="col-12"">
                        <label class="form-label"> Id viaje </label>
                        <input class ="form-control" name="idViaje" />
                    </div>

                    <div class="col-md-6">
                        <label class="form-label">Estado del viaje:</label>

                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" name="estadoViaje"value="ESPERA">
                            <label class="form-check-label" >En Espera</label>
                        </div>

                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" name="estadoViaje"  value="INICIADO">
                            <label class="form-check-label" >Iniciado</label>
                        </div>

                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" name="estadoViaje"  value="FINALIZADO">
                            <label class="form-check-label" >Finalizado</label>
                        </div>
                    </div>

                    <div class="col-md-4">
                        <label class="form-label ">Tipo de viaje:</label>

                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" name="tipoViaje" value="REGULAR" >
                            <label class="form-check-label" >Regular</label>
                        </div>

                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" name="tipoViaje" id="tipoPrivado" value="PRIVADO">
                            <label class="form-check-label" >Privado</label>
                        </div>
                    </div>

                    <div class="col-md-2">

                        <button class="btn btn-dark w-100 mb-2" type="submit" >buscar</button>
                    </div>




                </form>
                <c:if test="${not empty error}">
                    <div class =" alert alert-danger">${error}</div>
                </c:if>
                <c:if test="${not empty informacion}">
                    <div class =" alert alert-danger">${informacion}</div>
                </c:if>




                <c:if test="${not empty viajes}">
                    <div class="fw-extrabold text-uppercase tracking-wide text-black-gradient mb-3"><b> RESULTADOS </b> </div>
                    <c:forEach items="${viajes}" var="Viaje">
                        <div class="card">
                            <div class="card-header bg-black text-white p-3 d-flex justify-content-between align-items-center">
                                <div> ID Viaje : ${Viaje.idViaje}</div>
                                <div> Tipo : ${Viaje.tipo}</div>
                            </div>
                            <div class="card-body">
                                <div class="row g-3">


                                    <div class="col-md-6">
                                        <label class="form-label">Bus encargado : ${Viaje.idBus}</label>
                                    </div>

                                    <div class="col-md-6">
                                        <label class="form-label">chofer encargado : ${Viaje.idChofer}</label>
                                    </div>


                                    <div class="col-md-6">
                                        <label class="form-label">estado : ${Viaje.estado}</label>  
                                    </div>

                                    <div class="col-md-6">
                                        <label class="form-label"> Fecha y hora de salida: ${Viaje.salida} </label>
                                    </div>
                                    <div class="col-md-6">
                                        <label class="form-label"> Fecha y hora de llegada ${Viaje.llegada} </label>
                                    </div>

                                    <c:choose>
                                        <c:when test="${Viaje.tipo eq 'REGULAR'}">

                                            <div class="col-md-6">
                                                <label class="form-label"> ruta  : ${Viaje.idRuta}</label>
                                            </div>

                                            <div class="col-md-6">
                                                <label class="form-label"> boletos vendidos ${Viaje.cantidadBoletosVendidos} </label>
                                            </div>


                                        </c:when>
                                        <c:when test="${Viaje.tipo eq 'PRIVADO'}">
                                            <div class="col-md-6">
                                                <label class="form-label"> id viaje alquiler  : ${Viaje.idViajeAlquiler}</label>
                                            </div>

                                        </c:when>

                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${Viaje.estado eq 'ESPERA'}">

                                            <c:if test="${Viaje.tipo eq 'REGULAR'}">                                         
                                                <div class="col mb-6">
                                                    <form method="POST" action="${pageContext.request.contextPath}/AdministradorSucursal/Viajes/VerViajes?eliminar=eliminarViaje&idViaje=${Viaje.idViaje}">
                                                        
                                                        <button type="submit"class="btn btn-dark w-100" > ELIMINAR</button>
                                                    </form>


                                                </div>

                                            </c:if>
                                            <div class="col mb-4">


                                                <button type="button"   class="btn btn-dark w-100"  data-bs-toggle="modal"   data-bs-target="#modalViaje"  onclick="cargarDatosModal('${Viaje.idViaje}', 'iniciarViaje')">
                                                    INICIAR VIAJE
                                                </button>


                                            </div>

                                            <div class="col mb-2">
                                                <a class="btn btn-dark w-100" href="${pageContext.request.contextPath}/AdministradorSucursal/Viajes/EditarViaje?idViaje=${Viaje.idViaje}"> Editar</a>
                                       

                                            </div>

                                        </c:when>

                                        <c:when test="${Viaje.estado eq ('INICIADO' )}">
                                            <div class="col-12 mt-4">
                                                <button type="button"   class="btn btn-dark w-100"  data-bs-toggle="modal"   data-bs-target="#modalViaje"  onclick="cargarDatosModal('${Viaje.idViaje}', 'finalizarViaje')">
                                                    FINALIZAR
                                                </button>

                                            </div>

                                        </c:when>

                                        <c:when test="${Viaje.estado eq 'FINALIZADO'}">
                                            <c:if test="${not empty Viaje.detalle}">
                                                <div class="alert alert-success fw-bold">DETALLE VIAJE</div>

                                                <div class="row g-3">
                                                    <div class="col-md-6">
                                                        <label class="form-label">Kilometraje inicial: ${Viaje.detalle.kilometrajeInicialBus}</label>
                                                    </div>

                                                    <div class="col-md-6">
                                                      
                                                        <label class="form-label">Kilometraje final: ${Viaje.detalle.kilometrajeFinal}</label>
                                                    </div>

                                                    <div class="col-md-6">
                                                        <label class="form-label">Fecha real de salida: ${Viaje.detalle.fechaSalida}</label>
                                                    </div>

                                                    <div class="col-md-6">
                                                        
                                                        <label class="form-label">Fecha real de llegada: ${Viaje.detalle.fechaLlegada}</label>
                                                    </div>

                                                    <div class="col-md-6">
                                                        <label class="form-label">Gasto total de combustible: ${Viaje.detalle.gastoTotalCombustible}</label>
                                                    </div>
                                                </div>
                                            </c:if>
                                        </c:when>




                                    </c:choose>


                                </div>

                            </div>
                        </div>      
                    </c:forEach>
                </c:if>

            </div>


            <<div class="modal fade" id="modalViaje" tabindex="-1" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">

                        <div class="modal-header">
                            <h5 class="modal-title" id="tituloModal">Iniciar viaje</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>

                        <form method="POST" action="${pageContext.request.contextPath}/AdministradorSucursal/Viajes/VerViajes">
                            <div class="modal-body">
                                <input type="hidden" name="idViaje" id="iniciarViajeIdViaje" />
                                <input type="hidden" name="accion" id="accionViaje"  />

                                <div class="row g-3">

                                    <div id="seccionIniciar"  class="row g-3">
                                        <div class="col-md-6 ">
                                            <label class="form-label">Placa Bus:</label>
                                            <input type="text" class="form-control" name="idBus" />
                                        </div>
                                        <div class="col-md-6">
                                            <label class="form-label">Número de licencia:</label>
                                            <input type="text" class="form-control " name="idChofer" />
                                        </div>
                                        <div class="col-md-6">
                                            <label class="form-label">Fecha y hora salida real:</label>
                                            <input type="datetime-local" class="form-control" name="salida" />
                                        </div>
                                        <div class="col-md-6">
                                            <label class="form-label">Kilometraje inicial:</label>
                                            <input class="form-control " name="kilometraje" />
                                        </div>
                                    </div>

                                    <div id="seccionFinalizar"  class="row g-3">
                                        <div class="col-md-6">
                                            <label class="form-label">Gasto total combustible:</label>
                                            <input class="form-control " name="gastoCombustible" />
                                        </div>
                                        <div class="col-md-6">
                                            <label class="form-label">Fecha y hora llegada real:</label>
                                            <input type="datetime-local" class="form-control " name="llegada" />
                                        </div>
                                        <div class="col-md-6">
                                            <label class="form-label">Kilometraje final:</label>
                                            <input class="form-control " name="kilometrajeFinal" />
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-dark w-100" data-bs-dismiss="modal">Cancelar</button>
                                <button type="submit" class="btn btn-dark w-100" id="btnSubmitModal">Aceptar</button>
                            </div>
                        </form>

                    </div>
                </div>
            </div>






        </div>

    </main>
</body>
</html>