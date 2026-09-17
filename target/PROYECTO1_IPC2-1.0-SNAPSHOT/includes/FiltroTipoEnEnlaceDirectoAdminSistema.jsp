<%-- 
    Document   : FiltroTipoEnEnlaceDirectoAdminSistema
    Created on : 13/09/2026, 4:13:09 p. m.
    Author     : mario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty sessionScope.usuario and (sessionScope.usuario.tipo eq 'CLIENTE' or sessionScope.usuario.tipo eq 'ADMIN_SUCURSAL')}">
    <c:remove var="usuario" scope="session"/>
    <c:redirect url="/index.jsp" />
</c:if>