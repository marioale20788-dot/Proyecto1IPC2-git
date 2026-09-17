/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdmnistrarSistema.sucursales;

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
@WebServlet(name = "AdministrarSucursal", urlPatterns = {"/AdministradorSistema/AdministrarSucursal"})
public class AdministrarSucursal extends HttpServlet {

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
        iniciarAdministrador(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Frontend/AdministradorSistema/AdministrarSucursales.jsp");
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
        validarSucursal validarSucursal = new validarSucursal();
        String resultado = validarSucursal.AccionAdminSucursal(request);
        request.setAttribute("resultado", resultado);
        iniciarAdministrador(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Frontend/AdministradorSistema/AdministrarSucursales.jsp");
        dispatcher.forward(request, response);
        
    }

    public void iniciarAdministrador(HttpServletRequest request) {
        validarSucursal validarSucursal = new validarSucursal();
        ArrayList<Sucursal> sucursales = validarSucursal.obtenerSucursales();
        request.setAttribute("sucursales", sucursales);
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
}
