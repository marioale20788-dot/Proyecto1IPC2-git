<%-- 
    Document   : Login
    Created on : 13/09/2026, 11:21:48 a. m.
    Author     : mario
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/includes/resources.jsp"/>
        <script src="${pageContext.request.contextPath}/resources/index/Js.js?v=2"></script>
        <title>LGOIN</title>
    </head>
    <body>

        <c:if test="${not empty usuario}">

            <div> BIENVENIDO ${usuario.nombre}</div>

            <div>  SELECCIONE UNA SUCURSAL </div>

            <br>

            <c:if test="${empty sucursales}">

                <div>  NO HAY SUCURSALES DISPONIBLES </div>
                <a ref="${pageContext.request.contextPath}/index.jsp" > ACEPTAR </a>
            </c:if> 
            <c:if test="${not empty sucursales}">
                <c:forEach items="${sucursales}" var="Sucursal">
                    <form method="POST" action="${pageContext.request.contextPath}/AdministradorSucursal/Login/validarLogin?idSucursal=${Sucursal.idSucursal}" >

                        <label class="badge bg-secondary me-2" name="nombreSucursal">🏢 nombre sucursal: ${Sucursal.nombre}</label>
                        <label class="badge bg-secondary me-2" name="idSucursal ">🏢 nombre sucursal: ${Sucursal.idSucursal}</label>    
                        <button type="submit"> SELECCIONAR</button>


                    </form>


                </c:forEach>



            </c:if> 








        </c:if>




    </body>
</html>
