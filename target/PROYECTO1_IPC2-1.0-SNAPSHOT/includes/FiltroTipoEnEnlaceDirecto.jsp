<%-- 
    Document   : FiltroTipoEnEnlaceDirecto
    Created on : 13/09/2026, 1:22:52 p. m.
    Author     : mario
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<c:if test="${not empty sessionScope.usuario and (sessionScope.usuario.tipo eq 'CLIENTE' or sessionScope.usuario.tipo eq 'ADMIN_SISTEMA')}">
    <c:remove var="usuario" scope="session"/>
    <c:redirect url="/index.jsp" />
</c:if>