<%-- 
    Document   : CrearUsuario
    Created on : 16/09/2026, 11:08:22 p. m.
    Author     : mario
--%>


<<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/includes/resources.jsp"/>
        <title>INICIO</title>


    </head>
    <body >

        <main>
            <div class="container" >

                <div class="card">
                    <div class="card-header bg-black text-white p-3 d-flex justify-content-between align-items-center">
                     CREAR NUEVO USUARIO
                    </div>
                    <div class="card-body">

                        <form class="row g-3" id ="crearBus" method="POST" action="${pageContext.request.contextPath}/AdministradorSucursal/Login/CrearUsuario">
                            <div class="col-md-6">
                                <label class="form-label">Dpi usuario</label>
                                <input class ="form-control"name="dpiUsuario"/>
                            </div>
                            <div class="col-md-6">
                                <label  class="form-label" > nombre usuario </label>
                                <input class ="form-control"name="nombreUsuario"/>
                            </div>
                            <div class="col-md-6">
                                <label class="form-label"  > nit usuario </label>
                                <input class ="form-control"name="nitUsuario"/>  
                            </div>
                            <div class="col-md-4">

                                <label  class="form-label" > numero de telefono </label>
                                <input class ="form-control"name="numeroDeTelefono"/>
                            </div>
                            <div class="col-md-2">
                                <label  class="form-label"  > direccion </label>
                                <input class ="form-control" name="direccion"/> 
                            </div>                   

                            <div class="col-md-6">
                                <label  class="form-label" > CONTRASENA </label>
                                <input  type="password"class ="form-control"name="contrasena"/>
                            </div>

                            <br>
                            <button class="btn btn-dark w-100 mb-2" type="submit" > Crear usuario</button>
                        </form>





                    </div>
                </div>
                            <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-dark w-100 mb-2" > REGRESAR </a>
                        <c:if test="${not empty resultado}">
                             <div class="alert alert-danger">
                              ${resultado}
                            </div>
                        </c:if>
                            


            </div>



        </main>


    </body>
</html>
