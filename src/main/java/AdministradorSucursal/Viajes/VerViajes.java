/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdministradorSucursal.Viajes;

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
@WebServlet(name = "VerViajes", urlPatterns = {"/AdministradorSucursal/Viajes/VerViajes"})
public class VerViajes extends HttpServlet {

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
          
          ViajeLogica viajesLogica = new ViajeLogica();
          ArrayList<ViajeObj> viajes = viajesLogica. buscarViajes(request);
          if(viajes.isEmpty()){
              request.setAttribute("error", "NO SE ENCONTRO NINGUN VIAJE");
          }else{
              request.setAttribute("viajes", viajes);
          }
             RequestDispatcher dispatcher = request.getRequestDispatcher("/Frontend/AdministradorSucursal/Viajes/VerViajes.jsp");
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
         
          ViajeLogica viajesLogica = new ViajeLogica();
          String eliminar = request.getParameter("eliminar");
          String accion = request.getParameter("accion");
        
          if(eliminar!=null){
              String eliminarViaje = viajesLogica.eliminarViaje(request);
              request.setAttribute("informacion", eliminarViaje);
          }
          if(accion!=null && accion.equalsIgnoreCase("iniciar")){
              String iniciarViaje = viajesLogica.iniciarViaje(request);
                request.setAttribute("informacion", iniciarViaje);
          }
          if(accion!=null && accion.equalsIgnoreCase("finalizar")){
              String finalizarViaje = viajesLogica.finalizarViaje(request);
                request.setAttribute("informacion", finalizarViaje);
          }
          
          
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Frontend/AdministradorSucursal/Viajes/VerViajes.jsp");
        dispatcher.forward(request, response);

       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
   
}
