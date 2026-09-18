<%-- 
    Document   : ListaDeBuses
    Created on : 17/09/2026, 9:20:47 p. m.
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
    <body >

        <jsp:include page="/includes/headerAdminSucursal.jsp"/>
        <main>
            <div class="container" >
            <c:choose>

                        <c:when test="${not empty error}">
                            <div class="alert alert-danger" role="alert"> ${error}</div>
                        </c:when>
                        <c:when test="${not empty buses}">
                            <div class="container mt-4">
                                <div class="card shadow-sm border-0 rounded-3">
                                    <div class="card-body p-0 overflow-hidden">
                                        <div class="table-dark">
                                            <table class="table table-hover table-striped align-middle mb-0">
                                                <thead class="table-dark text-uppercase small"> 
                                                    <tr>
                                                        <th class="py-3 px-4">Numero placa</th>
                                                        <th class="py-3 px-4">marca</th>
                                                        <th class="py-3 px-4">modelo</th>
                                                        <th class="py-3 px-4">foto</th>
                                                        <th class="py-3 px-4">fabricacion</th>
                                                        <th class="py-3 px-4">kilometraje actual</th>
                                                        <th class="py-3 px-4">capacidad</th>
                                                        <th class="py-3 px-4">estado</th>
                                                        <th class="py-3 px-4">sucursal Asociada</th>
                                                          <th class="py-3 px-4">Viajes totales</th>
                                                        <th class="py-3 px-4">`</th>
                                                    </tr>
                                                </thead>

                                                <c:forEach items="${buses}" var="Bus">

                                                    <tbody>
                                                        <tr>
                                                            <td class="py-3 px-4 fw-bold text-secondary">${Bus.numeroPlaca}</td>
                                                            <td class="py-3 px-4 fw-bold text-secondary">${Bus.marca}</td>
                                                            <td class="py-3 px-4 fw-bold text-secondary">${Bus.modelo}</td>
                                                            <td class="py-3 px-4 fw-bold text-secondary">
                                                                <img class="img-thumbnail" src="${Bus.foto}"/>
                                                            </td>
                                                            <td class="py-3 px-4 fw-bold text-secondary">${Bus.fechaDeFabricacion}</td>
                                                            <td class="py-3 px-4 fw-bold text-secondary">${Bus.kilometrajeActual}</td>
                                                            <td class="py-3 px-4 fw-bold text-secondary">${Bus.capacidadPasajeros}</td>
                                                            <td class="py-3 px-4 fw-bold text-secondary">${Bus.estado}</td>
                                                            <td class="py-3 px-4 fw-bold text-secondary">${Bus.idSucursalAsociada}</td>
                                                             <td class="py-3 px-4 fw-bold text-secondary">${Bus.viajes}</td>
                                                            <td class="py-3 px-4 fw-bold text-secondary">  
                                                            </td>
                                                        </tr>
                                                    </tbody>


                                                </c:forEach>
                                            </table>
                                        </div>

                                    </div>
                                </div>
                            </div>


                        </c:when>
                    </c:choose>

            </div>



        </main>


    </body>
</html>
