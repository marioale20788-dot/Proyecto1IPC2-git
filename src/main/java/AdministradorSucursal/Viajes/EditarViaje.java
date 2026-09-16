/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdministradorSucursal.Viajes;

import AdministradorSucursal.Bus;
import AdministradorSucursal.Chofer;
import AdministradorSucursal.Ruta;
import AdministradorSucursal.ViajeObj;
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
@WebServlet(name = "EditarViaje", urlPatterns = {"/AdministradorSucursal/Viajes/EditarViaje"})
public class EditarViaje extends HttpServlet {

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
        
        ViajeLogica viaje = new ViajeLogica();
        mostrarViaje(request, viaje);
        
           RequestDispatcher dispatcher = request.getRequestDispatcher("/Frontend/AdministradorSucursal/Viajes/editarViaje.jsp");
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
        ViajeLogica viaje = new ViajeLogica();
       
        String update = viaje.editarViaje(request);
        request.setAttribute("resultado", update);
        
         mostrarViaje(request, viaje);
          RequestDispatcher dispatcher = request.getRequestDispatcher("/Frontend/AdministradorSucursal/Viajes/editarViaje.jsp");
        dispatcher.forward(request, response);

       
    }
    
    public void mostrarViaje(HttpServletRequest request, ViajeLogica viaje){
         ViajeObj viajeAEditara = viaje.viajeAEditar(request);
        ArrayList<Bus> buses = viaje.busesDisponibles(request);
        ArrayList<Chofer> choferes = viaje.choferesDisponibles(request);
        ArrayList<Ruta> rutas= viaje.rutasDisponibles(request);
        
        request.setAttribute("viaje", viajeAEditara);
        request.setAttribute("buses", buses);
        
        request.setAttribute("choferes", choferes);
        
        request.setAttribute("rutas", rutas);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
 

}
