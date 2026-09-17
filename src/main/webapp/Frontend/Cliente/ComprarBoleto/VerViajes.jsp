<%-- 
    Document   : VerViajes
    Created on : 16/09/2026, 2:16:22 a. m.
    Author     : mario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/includes/FiltroSession.jsp" %>
<%@ include file="/includes/FiltroTipoEnEnlaceDirectoUsuario.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/includes/resourcesLeaflet.jsp"/>
        <jsp:include page="/includes/resources.jsp"/>
        <script src="${pageContext.request.contextPath}/resources/Leaflet/Rutas/MostrarRutasComprarBoletos.js?v=2"></script>
        <title>INICIO</title>

    </head>
    <body >

        <jsp:include page="/includes/headerAdminSucursal.jsp"/>
        <main>
            <div class="container mt-4" >
                <c:if test="${not empty viajesDisponibles}">

                    <c:forEach items="${viajesDisponibles}" var="viaje">

                        <div class="card">
                            <div class="card-header bg-black text-white p-3 d-flex justify-content-between align-items-cente"> VIAJE ${viaje.idViaje} </div>                    
                            <div class="card-body">
                                <div class="row g-3">

                               
                                    <div class="col-md-6">
                                        <div class="row g-3">
                                            <div class="col-md-6">
                                                <label class="form-label">Bus encargado : ${viaje.idBus}</label>
                                            </div>

                                            <div class="col-md-6">
                                                <label clas s="form-label">chofer encargado : ${viaje.idChofer}</label>
                                            </div>

                                            <div class="col-md-6">
                                                <label class="form-label">estado : ${viaje.estado}</label>  
                                            </div>

                                            <div class="col-md-6">
                                                <label class="form-label"> Fecha y hora de salida: ${viaje.salida} </label>
                                            </div>

                                            <div class="col-md-6">
                                                <label class="form-label"> Fecha y hora de llegada ${viaje.llegada} </label>
                                            </div>  

                                            <div class="col-md-6">
                                                <label class="form-label"> ruta:  ${viaje.ruta.nombreRutal} </label>
                                            </div>  
                                        </div>
                                    </div>

                               
                                    <div class="col-md-6 d-flex justify-content-end align-items-center">
                                        <div id="mapa${viaje.idViaje}" style="width: 300px; height: 200px; border: 1px solid #ccc;"></div>
                                    </div>

                                    <input type="hidden" class="idViaje" value="${viaje.idViaje}" />
                                    <input type="hidden" class="origenLatitud" value="${viaje.ruta.sucursalOrigen.latitud}" />
                                    <input type="hidden" class="origenLongitud" value="${viaje.ruta.sucursalOrigen.longitud}" />
                                    <input type="hidden" class="origenNombre" value="${viaje.ruta.sucursalOrigen.idSucursal}" />

                                    <input type="hidden" class="destinoLatitud" value="${viaje.ruta.sucursalDestino.latitud}" />
                                    <input type="hidden" class="destinoLongitud" value="${viaje.ruta.sucursalDestino.longitud}" />
                                    <input type="hidden" class="destinoNombre" value="${viaje.ruta.sucursalDestino.idSucursal}" />

                                </div>

                            </div>

                                    <a class="btn btn-dark w-100 mb-2" href="${pageContext.request.contextPath}/AdministradorSucusal/Cliente/ComprarBoleto?idViaje=${viaje.idViaje}"> COMPRAR BOLETOS </a> 

                        </div>








                    </c:forEach>


                </c:if>




            </div>



        </main>


    </body>
</html>
