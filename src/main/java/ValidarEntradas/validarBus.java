/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ValidarEntradas;

import AdministradorSucursal.Bus;
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
@WebServlet(name = "validarBus", urlPatterns = {"/ValidarEntradas/validarBus"})
public class validarBus extends HttpServlet {

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
       
           validarBusLogica logica = new validarBusLogica();
        ArrayList<Bus> buses = logica.buscarBus(request);
       if(buses==null ||buses.isEmpty()){
        
           request.setAttribute("error", "no se encontro ningun resultado");
       }else{
           request.setAttribute("buses", buses);
       }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Frontend/AdministradorSucursal/Buses/editarBus.jsp");
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
         validarBusLogica logica  = new validarBusLogica();
          String resultado = logica.crearBusEnBaseDeDatos(request);
          request.setAttribute("resultado", resultado);       
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Frontend/AdministradorSucursal/Buses/CrearBus.jsp"); 
        dispatcher.forward(request, response);
        
     
    }

    
 
}
