<%-- 
    Document   : MostrarChofer
    Created on : 12/09/2026, 12:31:48 p. m.
    Author     : mario
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
  <%@ include file="/includes/FiltroSession.jsp" %>
  <%@ include file="/includes/FiltroTipoEnEnlaceDirecto.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/includes/resources.jsp"/>
    
        <title> CHOFERES </title>
    </head>
    <body>
        <jsp:include page="/includes/headerAdminSucursal.jsp"/>

        <main>
            <div class="container mt-4">

                <div name ="row g-3 align-items-center">
                    <form  class="row g-3"id ="editarBus" method="GET"action="${pageContext.request.contextPath}/ValidarEntradas/Chofer/ValidarChofer" >

                        <div class="col-md-6"> 

                            <label class="form-label"> numero licencia </label>
                            <input class ="form-control"name="numeroLicencia" />
                        </div>
                 

                        <div class="col-md-2"> 
                            <label class="form-label"> Realizar busqueda </label>
                            <button class="btn btn-dark w-100 mb-2" type="submit" > buscar </button>
                        </div>
                    </form>
                    <c:choose>
                        <c:when test ="${not empty error}">
                            <div class="alert alert-danger" role="alert"> ${error}</div>
                        </c:when>
                        <c:when test="${not empty choferes}">                            
                            <div class="container mt-4">
                                <div class="card shadow-sm border-0 rounded-3">
                                    <div class="card-body p-0 overflow-hidden">
                                        <div class="table-dark">
                                            <table class="table table-hover table-striped align-middle mb-0">
                                                <thead class="table-dark text-uppercase small"> 
                                                    <tr>
                                                        <th class="py-3 px-4">Numero licencia</th>
                                                        <th class="py-3 px-4">nombre completo</th>
                                                        <th class="py-3 px-4">Foto</th>
                                                        <th class="py-3 px-4">Tipo de licencia</th>
                                                        <th class="py-3 px-4">Estado</th>
                                                        <th class="py-3 px-4"> Numero de telefono</th>
                                                        <th class="py-3 px-4">Salario base</th>
                                                        <th class="py-3 px-4">Id sucursal</th>
                                                        <th class="py-3 px-4"> vencimiento licencia</th>
                                                        <th class="py-3 px-4">editar</th>
                                                    </tr>
                                                </thead>

                                                private String nombreCompleto;
                                                private String numeroDeTelefono;
                                                private String numeroDeLicencia;
                                                private String foto;
                                                private String tipoLicencial;
                                                private boolean estadoOpertaivo;
                                                private double salarioBase;
                                                private String idSucursal;
                                                private Date fechaVencimiento;
                                                <c:forEach items="${choferes}" var="Chofer">
                                                    <tbody>
                                                        <tr>
                                                            <td  class="py-3 px-4 fw-bold text-secondary"> ${Chofer.numeroDeLicencia}</td>
                                                            <td  class="py-3 px-4 fw-bold text-secondary"> ${Chofer.nombreCompleto}</td>

                                                            <td  class="py-3 px-4 fw-bold text-secondary"> 
                                                                <img src="${Chofer.foto}" class="img-thumbnail"/>


                                                            </td>

                                                            <td  class="py-3 px-4 fw-bold text-secondary"> ${Chofer.tipoLicencia}</td>
                                                            <td  class="py-3 px-4 fw-bold text-secondary"> ${Chofer.estado}</td>
                                                            <td  class="py-3 px-4 fw-bold text-secondary"> ${Chofer.numeroDeTelefono}</td>
                                                            <td  class="py-3 px-4 fw-bold text-secondary"> ${Chofer.salarioBase}</td>
                                                            <td  class="py-3 px-4 fw-bold text-secondary"> ${Chofer.idSucursal}</td>
                                                            <td  class="py-3 px-4 fw-bold text-secondary"> ${Chofer.fechaVencimiento}</td>
                                                            <td  class="py-3 px-4 fw-bold text-secondary">

                                                                <a class="btn btn-close-white w-100 mb-2"href="${pageContext.request.contextPath}/ValidarEntradas/Chofer/ValidarChoferEditarChofer?numeroLicencia=${Chofer.numeroDeLicencia}"> EDITAR</a>

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
