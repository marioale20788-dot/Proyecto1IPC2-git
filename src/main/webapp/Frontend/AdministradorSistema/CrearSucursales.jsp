<%-- 
    Document   : CrearSucursales
    Created on : 16/09/2026, 10:44:49 p. m.
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
        <jsp:include page="/includes/resourcesLeaflet.jsp"/>

        <title>CREAR SUCURSAL</title>
        <script src="${pageContext.request.contextPath}/resources/Leaflet/Rutas/CrearSucursal.js"></script>

    </head>
    <body >

        <jsp:include page="/includes/headerAdminSucursal.jsp"/>
        <main>
            <div class="container" >

                <div class="card">
                    <div class="card-header bg-black text-white p-3 d-flex justify-content-between align-items-center">

                        CREAR NUEVA SUCURSAL

                    </div>
                    <div class="card-body">

                        <form class="row g-3" id ="crearBus" method="POST" action="${pageContext.request.contextPath}/AdministrarSucursal/sucursales/CrearSucursal">
                            <div class="col-md-6">
                                <label class="form-label">id Sucursal</label>
                                <input class ="form-control"name="idSucursal"/>
                            </div>
                            <div class="col-md-6">
                                <label  class="form-label" > nombre sucursal </label>
                                <input class ="form-control"name="nombreSucursal"/>
                            </div>
                            <div class="col-md-6">
                                <label class="form-label"  > depreciacion </label>
                                <input class ="form-control"name="montoDepreciacion"/>  
                            </div>
                              <div class="col-12">
                                <label  class="form-label" > Administrador </label>
                                <select class ="form-control"name="adminSeleccionado">
                                    <c:forEach items="${administradores}" var="admin">
                                          <option value="${admin.dpi}">${admin.dpi} --- ${admin.nombre}</option>
                                    </c:forEach>
                                
                                  
                                </select>
                            </div>

                            
                            <div class="col-12 mt-3">
                                <h1> SELCCIONE UN PUNTO PARA LA SUCURSAL</h1>

                                <div id="mapa" style="height: 400px; width: 100%;" class="rounded-3 border"></div>
                                <input type="hidden"name="latitudSucursal" id="latitud" value="0"/>

                                <input type="hidden"name="longitudSucursal" id="longitud" value="0" />
                                <br>

                            </div>
                            <button class="btn btn-dark w-100 mb-2" type="submit" > Crear sucursal</button>
                        </form>
                    </div>

                    <c:if test="${not empty resultado}">
                        <div class="alert alert-danger">
                            ${resultado}
                        </div>
                    </c:if>


                </div>



        </main>


    </body>
</html>
