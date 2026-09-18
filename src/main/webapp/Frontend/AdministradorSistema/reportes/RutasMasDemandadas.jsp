<%-- 
    Document   : RutasMasDemandadas
    Created on : 17/09/2026, 10:33:49 p. m.
    Author     : mario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/includes/FiltroSession.jsp" %>
<%@ include file="/includes/FiltroTipoEnEnlaceDirectoAdminSistema.jsp" %>
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
            
            <div class="container mt-4" >
                <form class="row g-3" method="GET" action="${pageContext.request.contextPath}/Reportes/buscarRutas"">

                    <div class="col-md-6">
                        <label class="form-label"> fecha incio </label>
                        <input type="date" class ="form-control" name="fechaInicio" />
                    </div>
                     <div class="col-md-6">
                        <label class="form-label"> fecha final </label>
                        <input  type="date" class ="form-control" name="fechaFinal" />
                    </div>

                    <div class="col-md-2">
                        <label class="form-label"> Realizar busqueda </label>
                        <button class="btn btn-dark w-100 mb-2" type="submit" >buscar</button>
                    </div>




                </form>
                <c:if test="${not empty ruta}">

                <h1 class="fw-extrabold text-uppercase tracking-wide text-black-gradient mb-3"><b>RUTAS DISPONIBLES</b> </h1>
                <div class="container mt-4">
                    <div class="card shadow-sm border-0 rounded-3">

                        <table class="table table-hover table-striped align-middle mb-0">
                            <thead class="table-dark text-uppercase small">
                                <tr>
                                    <th  class="py-3 px-4"> ruta </th>
                                    <th  class="py-3 px-4"> id sucursal origen </th>
                                    <th  class="py-3 px-4"> id sucursal destino </th>
                                    <th  class="py-3 px-4"> distancia en km </th>


                                </tr>
                            </thead>

                            <c:forEach items="${rutas}" var="Ruta">  
                                <tbody>
                                    <tr>



                                        <td class="py-3 px-4 fw-bold text-secondary">${Ruta.nombreRutal}</td>
                                        <td class="py-3 px-4 fw-bold text-secondary">${Ruta.idSucursalOrigen}</td>
                                        <td class="py-3 px-4 fw-bold text-secondary"> ${Ruta.idSucursalDestino}</td>                              
                                        <td class="py-3 px-4 fw-bold text-secondary">${Ruta.distanciaEnKm} km</td>

                                    </tr>
                                </tbody>


                            </c:forEach>

                        </c:if>

                    </table>
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger"> ${error}</div>
                    </c:if>







                </div>
            </div>

        </div>



    </main>


</body>
</html>
