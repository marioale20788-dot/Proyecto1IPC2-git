<%-- 
    Document   : validarBus
    Created on : 9/09/2026, 11:20:34 a. m.
    Author     : mario
--%>

<%@page import="AdministradorSucursal.BusJdbc"%>
<%@page import="AdministradorSucursal.Bus"%>
<%@page import="AdministradorSucursal.Conector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    <body>
        <%

            Conector conector = new Conector();
            conector.connect();
            boolean formatosCorrectos = true;
            String error = "";
            String numeroPlaca = request.getParameter("numeroPlaca");
            String marca = request.getParameter("marca");
            String modelo = request.getParameter("modelo");
            String foto = request.getParameter("foto");
            String idSucursal = request.getParameter("idSucursal");
            int fabricacion = 0;
            double kmActual = 0.0;
            int capacidad = 0;
            if (numeroPlaca.isEmpty() || marca.isEmpty() || modelo.isEmpty() || foto.isEmpty() || idSucursal.isEmpty()) {
                formatosCorrectos = false;
                error = "Entrada vacia";
            }

            try {
                fabricacion = Integer.valueOf(request.getParameter("yearFabricacion"));
                kmActual = Double.valueOf(request.getParameter("kilometrajeActual"));
                capacidad = Integer.valueOf(request.getParameter("capacidadPasajeros"));
                if ((fabricacion <= 0) || kmActual < 0 || capacidad <= 0) {
                    formatosCorrectos = false;
                    error = error + "- entrada de digito negativa";
                }

            } catch (NumberFormatException e) {
                formatosCorrectos = false;
                error = error + "- formato de digito incorrecta";
            }

            if (formatosCorrectos == false) {

                request.setAttribute("Errores", error);
            } else {
                boolean estado = Boolean.valueOf(request.getParameter("estado"));

                Bus bus = new Bus(numeroPlaca, modelo, marca, foto, fabricacion, kmActual, capacidad, estado, idSucursal);
                BusJdbc busBaseDeDatos = new BusJdbc(conector.getConnection(), bus);
                String resultado= busBaseDeDatos.CrearNuevoBus();
                request.setAttribute("resultado", resultado);
                
                
                
                

            }
              conector.close();
           request.getRequestDispatcher("../../Frontend/AdministradorSucursal/CrearBus.jsp").forward(request, response);
          
        %>
    </body>
</html>
