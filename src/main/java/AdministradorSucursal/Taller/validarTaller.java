/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdministradorSucursal.Taller;

import AdministradorSucursal.Bus;
import AdministradorSucursal.BusJdbc;
import AdministradorSucursal.Conector;
import ValidarEntradas.Bus.validarBusLogica;
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
@WebServlet(name = "validarTaller", urlPatterns = {"/AdministradorSucursal/Taller/validarTaller"})
public class validarTaller extends HttpServlet {

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
        
        tallerLogica logica = new tallerLogica();
        request.setAttribute("buses", logica.buses(request));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Frontend/AdministradorSucursal/Taller/Taller.jsp");
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
        
        tallerLogica logica = new tallerLogica();
        request.setAttribute("buses", logica.buses(request));
        request.setAttribute("resultado", logica.mantenimiento(request));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Frontend/AdministradorSucursal/Taller/Taller.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
}
