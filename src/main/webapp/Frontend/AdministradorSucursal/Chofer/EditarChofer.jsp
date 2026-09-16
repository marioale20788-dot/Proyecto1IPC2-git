<%-- 
    Document   : EditarChofer
    Created on : 12/09/2026, 12:31:54 p. m.
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
        <script src="${pageContext.request.contextPath}/resources/AdministradorSucursal/JsAdministrarBuses.js?v=1.1"></script>
        <title> AGREGAR CHOFER </title>
    </head>
    <body>
        <jsp:include page="/includes/headerAdminSucursal.jsp"/>
        <main
            <main>
                <div class="container mt-4">
                    <div class="card">
                        <div class="card-header bg-black text-white p-3 d-flex justify-content-between align-items-center"> 

                            <c:choose>
                                <c:when  test="${not empty error}">
                                    <div class="alert alert-danger" role="alert"> ${error}</div>
                                </c:when>                        
                                <c:when  test="${not empty chofer}">

                                    <b>DETALLES CHOFER</b>         
                                    <div>

                                        <b>Estado:</b> 
                                        <b class="badge bg-success">${chofer.estado}</b>
                                    </div>
                                </div>

                                <div class="card-body">
                                    <div class="row g-3 align-items-center">
                                        <div class="col-md-8">
                                            <div class="row g-2">
                                                <div class="col-md-4 p-3 bg-light border rounded">
                                                    <b>Numero de licencia:</b> ${chofer.numeroDeLicencia}</div>
                                                <div class="col-md-4 p-3 bg-light border rounded">
                                                    <b>Nombre:</b> ${chofer.nombreCompleto}</div>
                                                <div class="col-md-4 p-3 bg-light border rounded">
                                                    <b>tipo de licencia:</b> ${chofer.tipoLicencia} </div>
                                                <div class="col-md-4 p-3 bg-light border rounded">
                                                    <b>Numero de telefono:</b> ${chofer.numeroDeTelefono}</div>
                                                <div class="col-md-4 p-3 bg-light border rounded">
                                                    <b>Salario base:</b>  ${chofer.salarioBase}  </div>
                                                <div class="col-md-4 p-3 bg-light border rounded">
                                                    <b>Sucursal Asociada:</b> ${chofer.idSucursal}</div>
                                                <div class="col-md-12 p-3 bg-light border rounded">
                                                    <b>Fecha vencimiento licencia:</b> ${chofer.fechaVencimiento}  </div>
                                            </div>
                                        </div>

                                        <div class="col-md-4 text-center">
                                            <img src="${chofer.foto}" class="img-thumbnail w-100" style="max-height: 220px;"">
                                        </div>

                                    </div>
                                </div>
                            </div>

                            <form method="POST"action="${pageContext.request.contextPath}/ValidarEntradas/Chofer/ValidarChoferEditarChofer?numeroLicencia=${chofer.numeroDeLicencia}">
                                <div class="col-12">
                                    <label  class="form-label" > Editar </label>
                                    <select id ="selectItem"class ="form-control"name="itemSeleccionado" onchange="cambiarItemChofer()">
                                        <option value="nombre_completo">nombre</option>
                                        <option value="foto">foto</option>
                                        <option value="tipo_licencia">tipo de licencia</option>
                                        <option value="estado_operativo">Estado</option>
                                        <option value="numero_telefono">Numero de telefono </option>                          
                                        <option value="salario_base"> salario base</option>
                                        <option value="fecha_vencimiento_licencia">fecha vencimiento licencia</option>
                                        <option value="id_sucursal">sucursal asocidada</option>
                                    </select>
                                </div>
                                <div id="editarBus" class="col-12">
                                    <label id="itemEditar" class ="form-label"> ITEM A EDITAR</label>
                                    <input id="inputNuevoItem" class="form-control" name="entradaNuevoItem" />
                                    <select class ="form-control" id="estadoItem"name="itemEstado" >
                                        <option value="true" >Activo</option>
                                        <option value="false">Inactivo</option>

                                    </select>

                                    <select class ="form-control" id="tipoLicenciaItem"name="itemTipoLicencia" >
                                        <option value="A" >A</option>
                                        <option value="B">B</option>
                                        <option value="C">C</option>
                                        <option value="M">M</option>
                                        <option value="E">E</option>

                                    </select>
                                    <input type="date" id="fechaVencimientoItem" name="fechaVencimiento"/>
                                    <br>
                                    <button class="btn btn-dark w-100 mb-2" type="submit" >Editar</button>

                                </div>

                            </form>    


                            <c:if test="${not empty resultado}">
                                <div class="alert alert-danger" role="alert"> ${resultado}</div>
                            </c:if>
                        </c:when>

                    </c:choose>

                </div>
            </main>

    </body>