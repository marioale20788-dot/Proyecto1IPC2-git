/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdministradorSucursal.Leaflet;

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
@WebServlet(name = "MostarRutas", urlPatterns = {"/AdministradorSucursal/Leaflet/MostarRuta"})
public class MostarRutas extends HttpServlet {

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
        buscarRutas(request, buscar);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Frontend/AdministradorSucursal/Rutas/BuscarElminiarRuta.jsp");
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

        BuscarRutas buscar = new BuscarRutas();
        String resultado = buscar.eliminarRuta(request);
        request.setAttribute("resultadoEliminarRuta", resultado);
        buscarRutas(request, buscar);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Frontend/AdministradorSucursal/Rutas/BuscarElminiarRuta.jsp");
        dispatcher.forward(request, response);

    }

    public void buscarRutas(HttpServletRequest request, BuscarRutas buscar) {
        Sucursal origen = buscar.sucursalOrigen(request);

        ArrayList<Ruta> rutas = buscar.buscarRutas(request);
        ArrayList<Sucursal> destinos = buscar.sucursalesDestino(request, rutas);

        if (origen == null || destinos.isEmpty()) {
            request.setAttribute("error", "NO SE ENCONTRARON RUTAS");
        } else {
            request.setAttribute("sucursalOrigen", origen);
            request.setAttribute("sucursalesDestino", destinos);
            request.setAttribute("rutas", rutas);

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
}
