<%-- 
    Document   : editarBus
    Created on : 7/09/2026, 11:14:15 a. m.
    Author     : mario
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="AdministradorSucursal.Bus"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
  <%@ include file="/includes/FiltroSession.jsp" %>
  <%@ include file="/includes/FiltroTipoEnEnlaceDirecto.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/includes/resources.jsp"/>
        <title>EDITAR BUS</title>
    </head>
    <body >
        <jsp:include page="/includes/headerAdminSucursal.jsp"/>
        <main>
            <div class="container mt-5">
                <div name ="row g-3 align-items-center">
                    <form  class="row g-3"id ="editarBus" method="GET"action="${pageContext.request.contextPath}/ValidarEntradas/Bus/validarBus" >

                        <div class="col-md-6"> 

                            <label class="form-label"> numero de placa </label>
                            <input class ="form-control"name="inputNumeroPlaca" />
                        </div>
                   

                        <div class="col-md-2"> 
                            <label class="form-label"> Realizar busqueda </label>
                            <button class="btn btn-dark w-100 mb-2" type="submit" > buscar </button>
                        </div>
                    </form>

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
                                                        <th class="py-3 px-4">editar</th>
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
                                                            <td class="py-3 px-4 fw-bold text-secondary">  
                                                                <a   class="btn btn-close-white w-100 mb-2"href="${pageContext.request.contextPath}/ValidarEntradas/Bus/validarBusEditarBus?placa=${Bus.numeroPlaca}" 
                                                                  >
                                                                    EDITAR
                                                                </a>
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


            </div>


        </main>


    </body>
</html>
