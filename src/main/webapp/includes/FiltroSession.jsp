<%-- 
    Document   : FiltroSession
    Created on : 13/09/2026, 12:49:29 p. m.
    Author     : mario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<c:if test="${empty sessionScope.usuario }">
    <c:redirect url="/index.jsp" />
</c:if>


<c:if test="${not empty sessionScope.usuario and sessionScope.usuario.tipo eq 'ADMIN_SUCURSAL' and empty sessionScope.idSucursal}">
    <c:redirect url="/index.jsp" />
</c:if>
