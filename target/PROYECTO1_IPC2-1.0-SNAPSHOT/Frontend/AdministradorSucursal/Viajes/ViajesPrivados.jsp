<%-- 
    Document   : ViajesPrivados
    Created on : 15/09/2026, 6:34:33 p. m.
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
        <title>INICIO</title>
    </head>
    <body>

        <jsp:include page="/includes/headerAdminSucursal.jsp"/>

        <main>
            <div class="container mt-4">
                <div class="row">

                    <h5 class="mb-0">Gestión de Viajes</h5>

                    <c:choose>
                        <c:when test="${not empty alquileresPrivadosAprobados}">
                            <div class="col-md-8 col-lg-9 mb-4">
                                <c:forEach items="${alquileresPrivadosAprobados}" var="AlquilerPrivado">
                                    <div class="card mb-4">
                                        <div class="card-header bg-black text-white p-3 d-flex justify-content-between align-items-center">
                                            <h5 class="mb-0"> id: ${AlquilerPrivado.idAlquilerPrivado} </h5>
                                        </div>
                                        <div class="card-body">
                                            <div class="row g-3 align-items-center">
                                                <div class="col-md-12">
                                                    <div class="row g-2">
                                                        <div class="col-md-12 p-3 bg-light border rounded">
                                                            <b>Cliente: </b> ${AlquilerPrivado.idCliente}
                                                        </div>
                                                        <div class="col-md-6 p-3 bg-light border rounded">
                                                            <b>Estado: </b> Pagado
                                                        </div>
                                                        <div class="col-md-4 p-3 bg-light border rounded">
                                                            <b>fecha estimada salida :</b> ${AlquilerPrivado.fechaSalida}
                                                        </div>
                                                        <div class="col-md-2 p-3 bg-light border rounded">
                                                            <b>fecha estimada llegada</b> ${AlquilerPrivado.fechaRetorno}
                                                        </div>
                                                        <div class="col-md-6 p-3 bg-light border rounded">
                                                            <b>salida: </b> ${AlquilerPrivado.origen}
                                                        </div>
                                                        <div class="col-md-6 p-3 bg-light border rounded">
                                                            <b>destino:</b> ${AlquilerPrivado.destino}
                                                        </div>
                                                        <div class="col-md-12 p-3 bg-light border rounded">
                                                            <b>Precio final: </b> ${AlquilerPrivado.precioFinal}
                                                        </div>
                                                        <div class="col-md-6 p-3 bg-light border rounded">
                                                            <b>pasajeros </b> ${AlquilerPrivado.pasajeros}
                                                        </div>
                                                        <div class="col-md-6 p-3 bg-light border rounded">
                                                            <b>distancia </b> ${AlquilerPrivado.distancia} Km
                                                        </div>

                                                        <div class="col-md-12">
                                                            <form method="POST" action="${pageContext.request.contextPath}/AdministradorSucursal/Viajes/AlquilerPrivado?idViajePrivado=${AlquilerPrivado.idAlquilerPrivado}">
                                                                <div class="row g-2">
                                                                    <div class="col-md-6 p-3 bg-light border rounded">
                                                                        <label class="form-label"> Seleccione un bus para el viaje: </label>
                                                                        <select class="form-control" id="Buses" name="busSeleccionado">
                                                                            <c:forEach items="${buses}" var="Bus">
                                                                                <option value="${Bus.numeroPlaca}">Placa: ${Bus.numeroPlaca} ----- capacidad: ${Bus.capacidadPasajeros}</option>
                                                                            </c:forEach>
                                                                        </select>
                                                                    </div>

                                                                    <div class="col-md-6 p-3 bg-light border rounded">
                                                                        <label class="form-label"> Seleccione un chofer para el viaje: </label>
                                                                        <select class="form-control" id="choferes" name="choferSeleccionado">
                                                                            <c:forEach items="${choferes}" var="chofer">
                                                                                <option value="${chofer.numeroDeLicencia}">Licencia: ${chofer.numeroDeLicencia} ----- Nombre: ${chofer.nombreCompleto}---------- ${chofer.salarioBase} </option>
                                                                            </c:forEach>
                                                                        </select> 
                                                                    </div>

                                                                    <div class="col-md-12 mt-2">
                                                                        <button type="submit" class="btn btn-dark w-100 mb-2"> ACEPTAR</button>
                                                                    </div>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:when>

                        <c:when test="${empty alquileresPrivadosAprobados}">
                            <div class="col-md-8 col-lg-9 mb-4">
                                <div class="alert alert-danger"> NO HAY VIAJES ALQUILADOS PENDIENTES POR INICIAR</div>
                            </div>
                        </c:when>
                    </c:choose>

                    <aside class="col-md-4 col-lg-3">
                        <div class="card position-sticky">
                            <div class="card-header bg-black text-white p-3 d-flex justify-content-between align-items-center">
                                Notificaciones, confirmar precio        
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

                                                <form method="POST" action="${pageContext.request.contextPath}/AdministradorSucursal/Viajes/AlquilerPrivado?idViajePrivado=${AlquilerPrivado.idAlquilerPrivado}">
                                                    <label class="form-label"> Precio final: </label>
                                                    <input class="form-control mb-2" name="precioFinal"/>
                                                    <button type="submit" class="btn btn-dark w-100 mb-2"> ACEPTAR</button>
                                                </form>                                                
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:if>
                            </div>
                        </div>
                    </aside>

                </div>
            </div>

            <c:if test="${not empty resultado}">
                <div class="container mt-2">
                    <div class="alert alert-danger"> ${resultado}</div>
                </div>
            </c:if>
        </main>

    </body>
</html>