<%-- 
    Document   : VerBoletosComprados
    Created on : 16/09/2026, 3:50:08 p. m.
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
                <a class="btn btn-dark w-10" href="${pageContext.request.contextPath}/Cliente/Viajes/BuscarBoletos?tipo=ESPERA"> BOLETOS DE VIAJES PENDIENTES</a>
                <a class="btn btn-dark w-10" href="${pageContext.request.contextPath}/Cliente/Viajes/BuscarBoletos?tipo=FINALIZADO"> BOLETOS DE VIAJES TERMINADOS</a>
                <a class="btn btn-dark w-10" href="${pageContext.request.contextPath}/Cliente/Viajes/BuscarBoletos?tipo=INICIADO"> BOLETOS DE VIAJES INICIADOS</a>

                <c:if test="${not empty boletosViajes}">
                    <c:forEach items="${boletosViajes}" var="boleto">
                        <div class="card">
                            <div class="card-header"> ID: ${boleto.idBoleto}</div>
                            <div class="card-body"> 
                                <div class="row g-3">

                                    <div class="col-md-6">
                                        <label class="form-label">Id usuario : ${boleto.idUsuario}</label>
                                    </div>

                                    <div class="col-md-6">
                                        <label class="form-label">Id viaje: ${boleto.idViaje}</label>
                                    </div>


                                    <div class="col-md-6">
                                        <label class="form-label">Fecha pago: : ${boleto.fechaDePago}</label>  
                                    </div>

                                    <div class="col-md-6">
                                        <label class="form-label"> numero asiento: ${boleto.numeroAsiento} </label>
                                    </div>

                                </div>

                            </div>



                        </div>


                    </c:forEach>



                </c:if>
                <c:if test="${not empty error}">
                    <div class="alert alert-warning"> ${error} </div>
                </c:if>



            </div>


        </main>


    </body>
</html>
