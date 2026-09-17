<%-- 
    Document   : ComprarBoleto
    Created on : 16/09/2026, 8:05:07 a. m.
    Author     : mario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/includes/FiltroSession.jsp" %>
<%@ include file="/includes/FiltroTipoEnEnlaceDirectoUsuario.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="${pageContext.request.contextPath}/resources/Cliente/SeleccionarVije.js"></script>
        <jsp:include page="/includes/resources.jsp"/>
        <title>INICIO</title>
    </head>
    <body>

        <jsp:include page="/includes/headerAdminSucursal.jsp"/>
        <main>
            <div class="container mt-5">

                <c:if test="${not empty viaje}">
                    <div class="card">
                        <div class="card-header"> COMPRAR BOLETO </div>
                        <div class="card-body">
                            <div class="col-md-12 mb-4">
                                <div class="col-md-6">
                                    <div class="row g-3">
                                        <div class="col-md-6">
                                            <label class="form-label">Bus encargado : ${viaje.idBus}</label>
                                        </div>

                                        <div class="col-md-6">
                                            <label clas s="form-label">chofer encargado : ${viaje.idChofer}</label>
                                        </div>

                                        <div class="col-md-6">
                                            <label class="form-label">Precio Boleto: Q${viaje.precioBoleto}</label>
                                        </div>

                                        <div class="col-md-6">
                                            <label class="form-label"> Fecha y hora de salida: ${viaje.salida} </label>
                                        </div>

                                        <div class="col-md-6">
                                            <label class="form-label"> Fecha y hora de llegada ${viaje.llegada} </label>
                                        </div>  

                                        <div class="col-md-6">
                                            <label class="form-label"> ruta:  ${viaje.idRuta} </label>
                                        </div>  
                                    </div>
                                </div>

                                <input type="hidden" id="precioIndividual" value="${viaje.precioBoleto}" />
                            </div>  


                        </div>

                    </div>

                    <c:if test="${not empty busViaje}">
                        <table class="table table-borderless w-auto mx-auto align-middle text-center">
                            <thead>
                                <tr class="text-muted fs-6">
                                    <th class="p-2">-</th>
                                    <th class="p-2">-</th>
                                    <th class="px-3">-</th> 
                                    <th class="p-2">-</th>
                                    <th class="p-2">-</th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach items="${busViaje.matrizAsientos}" var="fila">
                                    <tr>
                                        <c:forEach items="${fila}" var="asiento" varStatus="status">

                                            <c:if test="${status.index == 2}">
                                                <td class="px-3"></td> 
                                            </c:if>

                                            <c:if test="${not empty asiento}">
                                                <td class="p-1">
                                                    <c:choose>
                                                        <c:when test="${asiento.ocupado}">
                                                            <button type="button" class="btn btn-danger " disabled>
                                                                ${asiento.numero}
                                                            </button>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input id="yaDioClick${asiento.numero}" type="hidden" value="false"/>
                                                            <button type="button" class="btn btn-success " id="numero-${asiento.numero}" onclick="seleccionarAsiento(this,${asiento.numero})">
                                                                ${asiento.numero}
                                                            </button>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                            </c:if>

                                        </c:forEach>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                        <div class="col-md-12 mt-4">
                            <label class="form-label" id="precioTotalBoletos">PRECIO TOTAL: Q0.00</label>
                        </div>  


                        <form method="POST" action="${pageContext.request.contextPath}/AdministradorSucusal/Cliente/ComprarBoleto?idViaje=${viaje.idViaje}">
                            <input type="hidden" id="asientosSeleccionados" name="boletosComprados" />
                            
                            <div class="col-md-6">
                                <label  class="form-label" > Fecha de compra </label>
                                <input type="date" class="form-control" name="fechaCompra"/>
                            </div>
                            
                            <button class="btn btn-dark w-100 mb-2" type="submit"> COMPRAR </button>
                        </form>

                    </c:if>





                </c:if>
                
                <c:if test="${not empty resultado}">
                    
                    <div class="alert alert-danger"> ${resultado}</div>
                    
                </c:if>
                    

            </div>
        </main>

    </body>
</html>