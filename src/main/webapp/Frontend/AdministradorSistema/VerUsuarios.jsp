<%-- 
    Document   : VerUsuarios
    Created on : 16/09/2026, 10:48:46 p. m.
    Author     : mario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <div class="container">

                <div class="row g-3 align-items-center">
                    <form class="row g-3 align-items-end" id="editarBus" method="GET" action="${pageContext.request.contextPath}/AdministradorSistema/Sucursales/AdministrarUsuarios">

                        <div class="col-md-5"> 
                            <label class="form-label"> Nombre usuario </label>
                            <input class="form-control" name="nombreUsuario" />
                        </div>

                        <div class="col-md-4 d-flex gap-3 pb-2">
                            <div class="form-check m-0">
                                <input class="form-check-input" type="radio" name="tipoUsuario" value="ADMIN_SUCURSAL"/>
                                <label class="form-check-label" > Admin sucursal </label>
                            </div>

                            <div class="form-check m-0">
                                <input class="form-check-input" type="radio" name="tipoUsuario" value="CLIENTE" id="tipoCliente" />
                                <label class="form-check-label" > Cliente </label>
                            </div>
                        </div>

                        <div class="col-md-3"> 
                            <button class="btn btn-dark w-100" type="submit"> buscar </button>
                        </div>

                    </form>

                    <c:if test="${not empty usuarios}">
                        <c:forEach items="${usuarios}" var="usuario">
                            <div class="row g-2 align-items-center mb-2">
                                <div class="col-md-4">
                                    <label class="form-label "> Usuario dpi: ${usuario.dpi}</label>  
                                </div>
                                <div class="col-md-4">
                                    <label class="form-label "> Nombre: ${usuario.nombre}</label>  
                                </div>
                                <div class="col-md-4">
                                    <label class="form-label "> Nit ${usuario.nit}</label>  
                                </div>

                                <div class="col-md-4">
                                    <label class="form-label "> Direccion ${usuario.direccion}</label>  
                                </div>

                                <div class="col-md-4">
                                    <label class="form-label "> Tipo: ${usuario.tipo}</label>  
                                </div>
                                <div class="col-md-4">
                                    <c:choose>
                                        <c:when test="${usuario.estado eq 'true'}">
                                            <label class="form-label "> Estado: activo</label>
                                        </c:when>
                                        <c:otherwise>
                                            <label class="form-label "> Estado: inactivo</label>
                                        </c:otherwise>
                                    </c:choose>
                                </div>

                                <div class="col-md-4">
                                    <form method="POST" action="${pageContext.request.contextPath}/AdministradorSistema/Sucursales/AdministrarUsuarios?idUsuario=${usuario.dpi}&estado=${usuario.estado}&tipo=${usuario.tipo}">
                                        <button type="submit" class="btn btn-dark w-100">Desactivar/activar</button>
                                    </form>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>

                    <c:if test="${not empty resultado}">
                        <div class="alert alert-warning" role="alert"> ${resultado}</div>
                    </c:if>

                </div>

            </div>



        </main>


    </body>
</html>
