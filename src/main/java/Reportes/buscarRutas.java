/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Reportes;

import AdministradorSucursal.Leaflet.BuscarRutas;
import AdministradorSucursal.Ruta;
import AdministradorSucursal.Sucursal;
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
@WebServlet(name = "buscarRutas", urlPatterns = {"/Reportes/buscarRutas"})
public class buscarRutas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
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
           BuscarRutas buscar = new BuscarRutas();
        Sucursal origen = buscar.sucursalOrigenReprte(request);

   

        if (origen == null) {
            request.setAttribute("error", "NO SE ENCONTRARON RUTAS");
        } else {
                ArrayList<Ruta> rutas = buscar.buscarRutasReporte(origen.getIdSucursal());
        ArrayList<Sucursal> destinos = buscar.sucursalesDestino(request, rutas);
            request.setAttribute("sucursalOrigen", origen);
            request.setAttribute("sucursalesDestino", destinos);
            request.setAttribute("rutas", rutas);

        }
        
         RequestDispatcher dispatcher = request.getRequestDispatcher("/Frontend/AdministradorSistema/reportes/RutasReporte.jsp");
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
      
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
