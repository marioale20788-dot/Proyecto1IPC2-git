<%-- 
    Document   : ListaDeChoferes
    Created on : 17/09/2026, 9:43:37 p. m.
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
        <script src="${pageContext.request.contextPath}/includes/ExportarAHtml.js?v=2.1"></script>
        <title>BUSES</title>


    </head>
    <body >

        <jsp:include page="/includes/headerAdminSucursal.jsp"/>
        <main>
            <div class="container" >
                <c:choose>
                    <c:when test ="${not empty error}">
                        <div class="alert alert-danger" role="alert"> ${error}</div>
                    </c:when>
                    <c:when test="${not empty choferes}">    
                        <form id="exportar" action="${pageContext.request.contextPath}/ExportarHTMLServlet" method="POST">


                            <input type="hidden" name="contenidoHtml" id="contenido">

                            <button type="button" class="btn btn-success mb-3" onclick="descargar()">
                                Exportar a HTML
                            </button>
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
                                                        <th class="py-3 px-4">Viajes hechos</th>
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
                                                            <td  class="py-3 px-4 fw-bold text-secondary"> ${Chofer.viajes}</td>
                                                        </tr>
                                                    </tbody>
                                                </c:forEach> 
                                            </table>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            </form>
                        </c:when>
                    </c:choose>

            </div>
            


        </main>


    </body>
</html>
