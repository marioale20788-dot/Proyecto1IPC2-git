<%-- 
    Document   : Taller
    Created on : 16/09/2026, 12:18:47 a. m.
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
            <div class="container mt-4" >
                  <div class="card">
                    <div class="card-header bg-black text-white p-3 d-flex justify-content-between align-items-center">
                        GASTO TALLER
                    </div>
                    <div class="card-body">

                        <form class="row g-3" id ="crearBus" method="POST" action="${pageContext.request.contextPath}/ValidarEntradas/Bus/validarBus">
                            <div class="col-md-6">
                                <label class="form-label"> Seleccione bus </label>
                                <input class ="form-control"name="numeroPlaca"/>
                            </div>
                            <div class="col-md-6">
                                <label  class="form-label" > Marca </label>
                                <input class ="form-control"name="marca"/>
                            </div>
                            <div class="col-md-6">
                                <label class="form-label"  > modelo </label>
                                <input class ="form-control"name="modelo"/>  
                            </div>
                            <div class="col-md-4">

                                <label  class="form-label" > kilometraje actual </label>
                                <input class ="form-control"name="kilometrajeActual"/>
                            </div>
                            <div class="col-md-2">
                                <label  class="form-label"  > fabricacion </label>
                                <input class ="form-control" name="yearFabricacion"/> 
                            </div>


                            <div class="col-12">
                                <label  class="form-label" > foto </label>
                                <input class ="form-control"name="foto"/>

                            </div>

                            <div class="col-12">
                                <label  class="form-label" > Estado </label>
                                <select class ="form-control"name="estado">
                                    <option value="true">activo</option>
                                    <option value="false">desactivado</option>
                                </select>
                            </div>

                            <div class="col-md-6">
                                <label  class="form-label" > capacidad de pasajeros </label>
                                <input class ="form-control"name="capacidadPasajeros"/>
                            </div>

                            <br>
                            <button class="btn btn-dark w-100 mb-2" type="submit" > Crear bus</button>
                        </form>





                    </div>
                </div>
               

            </div>



        </main>


    </body>
</html>
