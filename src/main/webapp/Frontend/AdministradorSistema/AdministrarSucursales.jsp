<%-- 
    Document   : AdministrarSucursales
    Created on : 16/09/2026, 10:46:14 p. m.
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
    <title>ADMINISTRAR SUCURSALES</title>
</head>
<body>

    <jsp:include page="/includes/headerAdminSucursal.jsp"/>
    <main class="py-4">
        <div class="container">
            <c:if test="${not empty sucursales}">
                <c:forEach items="${sucursales}" var="sucursal">

                    <div class="card">
                        <div class="card-header bg-black text-white p-3 ">
                            <div>ID : ${sucursal.idSucursal}</div>
                        </div>
                        <div class="card-body">
                            <div class="row g-3">

                                <div class="col-md-6">
                                    <label class="form-label">Nombre : ${sucursal.nombre}</label>
                                </div>

                                <div class="col-md-6">
                                    <label class="form-label ">Depreciacion : ${sucursal.depreciacion}</label>
                                </div>

                                <div class="col-md-6">
                                    <label class="form-label ">Latitud : ${sucursal.latitud}</label>  
                                </div>

                                <div class="col-md-6">
                                    <label class="form-label">Longitud : ${sucursal.longitud}</label>
                                </div>

                                <hr class="my-3"/>

                              
                                <div class="col-12">
                                    <form method="POST" action="${pageContext.request.contextPath}/AdministradorSistema/AdministrarSucursal?accion=editar&idSucursal=${sucursal.idSucursal}">
                                        <div class="row g-2 align-items-end">
                                            <div class="col-md-5">
                                                <label class="form-label">Item a editar</label>
                                                <select class="form-select" name="itemAEditar">
                                                    <option value="depreciacion">Depreciacion</option>
                                                    <option value="nombre_sucursal">Nombre sucusal</option>
                                                </select>
                                            </div>
                                            <div class="col-md-5">
                                                <label class="form-label">Nuevo valor</label>
                                                <input name="nuevoValor" class="form-control"/>
                                            </div>
                                            <div class="col-md-2">
                                                <button type="submit" class="btn btn-dark w-100">EDITAR</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>

                                <hr class="my-3"/>

                           
                                <div class="col-12">
                                    <label class="form-label">Administradores asociados</label>
                                    <c:forEach items="${sucursal.administradores}" var="usuario">
                                        <div class="row g-2 align-items-center mb-2">
                                            <div class="col-md-8">
                                                <label class="form-label m-0">Administrador :dpi: ${usuario.dpi} ------- Nombre: ${usuario.nombre}</label>  
                                            </div>
                                            <div class="col-md-4">
                                                <form method="POST" action="${pageContext.request.contextPath}/AdministradorSistema/AdministrarSucursal?accion=eliminar&idSucursal=${sucursal.idSucursal}&idUsuario=${usuario.dpi}">
                                                    <button type="submit" class="btn btn-dark w-100">Eliminar</button>
                                                </form>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>

                                <hr class="my-3"/>

                              
                                <div class="col-12">
                                    <label class="form-label">Asociar administrador</label>
                                    <c:forEach items="${sucursal.administradoresFaltantes}" var="usuarioFaltante">
                                        <div class="row g-2 align-items-center mb-2">
                                            <div class="col-md-8">
                                                <label class="form-label m-0">Administrador :dpi: ${usuarioFaltante.dpi} ------- Nombre: ${usuarioFaltante.nombre}</label>  
                                            </div>
                                            <div class="col-md-4">
                                                <form method="POST" action="${pageContext.request.contextPath}/AdministradorSistema/AdministrarSucursal?accion=agregar&idSucursal=${sucursal.idSucursal}&idUsuario=${usuarioFaltante.dpi}">
                                                    <button type="submit" class="btn btn-dark w-100">agregar</button>
                                                </form>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>

                            </div>
                        </div>
                                    <c:if test="${not empty resultado}">
                                        <div class="alert alert-warning" role="alert"> ${resultado}</div>
                                    </c:if>
                    </div>

                </c:forEach>                    
            </c:if>
        </div>
    </main>
</body>
</html>