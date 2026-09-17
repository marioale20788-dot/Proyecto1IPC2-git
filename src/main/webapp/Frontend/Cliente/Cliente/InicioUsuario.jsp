<%-- 
    Document   : InicioUsuario
    Created on : 13/09/2026, 2:02:54 p. m.
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
                <div class="row g-4">


                    <div class="col-md-6">
                        <div class="card ">
                            <div class="card-body">
                                <h1> RECARGAR </h1>
                                <form method="POST" action="${pageContext.request.contextPath}/Cliente/InicioCliente">
                                    <label class="form-label"> MONTO a recargar </label>
                                    <input class="form-control" name="montoARecargar" />
                                    <button class="btn btn-dark w-100" type="submit"> ACEPTAR </button>
                                </form>

                                <c:if test="${not empty resultado}">
                                    <div class="alert alert-info mt-3">${resultado}</div>
                                </c:if>
                            </div>
                        </div>
                    </div>


                    <div class="col-md-6">
                        <div class="card">
                            <div class="card-body">

                            </div>
                        </div>
                    </div>


                    <div class="col-md-6">
                        <div class="card ">
                            <div class="card-body">

                            </div>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <div class="card h-100 p-3">
                            <div class="card-body">


                            </div>
                        </div>
                    </div>

                </div>
            </div>


        </main>


    </body>
</html>
