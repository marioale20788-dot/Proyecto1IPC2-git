/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ValidarEntradas.Bus;

import ValidarEntradas.Bus.validarBusLogica;
import AdministradorSucursal.Bus;
import AdministradorSucursal.BusJdbc;
import AdministradorSucursal.Conector;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author mario
 */
@WebServlet(name = "validarBusEdtiarBus", urlPatterns = {"/ValidarEntradas/Bus/validarBusEditarBus"})
public class validarBusEdtiarBus extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        validarBusLogica validar = new validarBusLogica();
        Bus bus = validar.buscarBusEditar(request);
        if (bus == null) {
            request.setAttribute("error", "NO SE ENCONTRO NINGUN BUS ASOCIADO A ESA PLACA EN ESTA SUCURSAL");
        } else {
            request.setAttribute("bus", bus);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Frontend/AdministradorSucursal/Buses/cambiarDatosBus.jsp");
        dispatcher.forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BusJdbc busBaseDeDatos = new BusJdbc(Conector.getInstance().getConnection(), null);
        validarBusLogica logica = new validarBusLogica();
        String resultado = logica.updateBus(request);
        String placaBus = (String) request.getParameter("placaBus");
        ArrayList<Bus> bus = busBaseDeDatos.BuscarBuses((String)request.getSession().getAttribute("idSucursal"), placaBus);
        request.setAttribute("resultado", resultado);
       
            if(bus.isEmpty()){
                 request.setAttribute("error", "NO SE ENCONTRO NINGUN BUS ASOCIADO A ESA PLACA EN ESTA SUCURSAL");
            }else{
                request.setAttribute("bus", bus.get(0));
            }
        
           RequestDispatcher dispatcher = request.getRequestDispatcher("/Frontend/AdministradorSucursal/Buses/cambiarDatosBus.jsp");
        dispatcher.forward(request, response);



    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
}
