<%-- 
    Document   : CrearChofer
    Created on : 12/09/2026, 12:26:36 p. m.
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
        <title> AGREGAR CHOFER </title>
    </head>
    <body>
        <jsp:include page="/includes/headerAdminSucursal.jsp"/>
        <main
            <main>
                <div class="container mt-4">

                    <div class="card">
                        <div class="card-header bg-black text-white p-3 d-flex justify-content-between align-items-center">
                            AGREGAR CHOFER
                        </div>
                        <div class="card-body">
                            <form class="row g-3" id ="crearBus" method="POST" action="${pageContext.request.contextPath}/ValidarEntradas/Chofer/ValidarChofer">
                                <div class="col-md-6">
                                    <label class="form-label"> Nombre completo </label>
                                    <input class ="form-control"name="nombreCompleto"/>
                                </div>
                                <div class="col-md-6">
                                    <label  class="form-label" > Numero de licencia </label>
                                    <input class ="form-control"name="numeroLicencia"/>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label"  > foto </label>
                                    <input class ="form-control"name="foto"/>  
                                </div>
                                <div class="col-12">
                                    <label  class="form-label" > Tipo de licencia </label>
                                    <select class ="form-control"name="tipoLicencia">
                                        <option value="A">A</option>
                                        <option value="B">B</option>
                                        <option value="C">C</option>
                                        <option value="M">M</option>
                                        <option value="E">E</option>

                                    </select>
                                </div>

                                <div class="col-12">
                                    <label  class="form-label" > Estado </label>
                                    <select class ="form-control"name="estado">
                                        <option value="true">activo</option>
                                        <option value="false">desactivado</option>
                                    </select>
                                </div>

                                <div class="col-md-2">
                                    <label  class="form-label"  > numero de telefono </label>
                                    <input class ="form-control" name="numeroDeTelefono"/> 
                                </div>


                                <div class="col-12">
                                    <label  class="form-label" > salario base </label>
                                    <input class ="form-control"name="salarioBase"/>

                                </div>


                                <div class="col-md-6   ">
                                    <label  class="form-label"  > Fecha de vencimiento licencia </label>
                                    <input type="date"  name="fechaVencimiento">
                                </div>
                                <br>
                                <button class="btn btn-dark w-100 mb-2" type="submit" > Agregar Chofer</button>
                            </form>


                        </div>
                    </div>






                    <c:if test="${not empty resultado}">

                        <div class="alert alert-danger"> MENSAJE:${resultado} </div>

                    </c:if>
                </div>








            </main>

    </body>
</html>
