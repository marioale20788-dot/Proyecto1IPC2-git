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

                        <form class="row g-3" id ="crearBus" method="POST" action="${pageContext.request.contextPath}/AdministradorSucursal/Taller/validarTaller">
                            <div class="col-md-6">
                                <label class="form-label"> Seleccione bus </label>
                                <select class ="form-control"name="busSeleccionado">
                                    <c:if test="${not empty buses}">
                                        <c:forEach items="${buses}" var="Bus">
                                            
                                              <option value="${Bus.numeroPlaca}">Placa: ${Bus.numeroPlaca}</option>
                                   </c:forEach>
                                        
                                    </c:if>
                                  
                                </select>
                            </div>
                            <div class="col-md-6">
                                <label  class="form-label" > Fecha mantenimiento </label>
                                <input  type="date"class ="form-control"name="fechaMantenimiento"/>
                            </div>
                            <div class="col-md-6">
                                <label class="form-label"  > Monto repuestos </label>
                                <input class ="form-control"name="montoRepuestos"/>  
                            </div>
                            <div class="col-md-4">

                                <label  class="form-label" > Monto mano de obra </label>
                                <input class ="form-control"name="montoManoDeObra"/>
                            </div>
                            <div class="col-md-4">

                                <label  class="form-label" > Id recibo </label>
                                <input class ="form-control"name="idRecibo"/>
                            </div>
         
                            <br>
                            <button class="btn btn-dark w-100 mb-2" type="submit" > ACEPTAR </button>
                        </form>

                    </div>
                </div>
                        <c:if test="${not empty resultado}">
                            <div class="alert alert-danger"> ${resultado}</div>
                        </c:if>
                            

            </div>



        </main>


    </body>
</html>
