/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdministradorSucursal.Viajes;

import AdministradorSucursal.Bus;
import AdministradorSucursal.Chofer;
import AdministradorSucursal.Ruta;
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
@WebServlet(name = "Viaje", urlPatterns = {"/AdministradorSucursal/Viaje/Viaje"})
public class Viaje extends HttpServlet {

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

        ViajeLogica logica = new ViajeLogica();
        iniciarCrearViaje(request, logica);
        
          RequestDispatcher dispatcher = request.getRequestDispatcher("/Frontend/AdministradorSucursal/Viajes/CrearViajeRegular.jsp");
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

         ViajeLogica logica = new ViajeLogica();
         String resultado = logica.crearViaje(request, "REGULAR");
         request.setAttribute("resultado", resultado);
         iniciarCrearViaje(request, logica);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Frontend/AdministradorSucursal/Viajes/CrearViajeRegular.jsp");
        dispatcher.forward(request, response);

    }
    
    
    public void iniciarCrearViaje(HttpServletRequest request, ViajeLogica logica){
          String idViaje = logica.generarIdViaje();
        ArrayList<Bus> buses = logica.busesDisponibles(request);

        ArrayList<Chofer> choferes = logica.choferesDisponibles(request);
        ArrayList<Ruta> rutas = logica.rutasDisponibles(request);
        request.setAttribute("idViaje", idViaje);
        request.setAttribute("busesDisponibles", buses);
        request.setAttribute("rutas", rutas);
        request.setAttribute("choferesDisponibles", choferes);
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
}
