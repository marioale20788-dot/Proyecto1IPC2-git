/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdmnistrarSistema.sucursales;

import AdministradorSucursal.Conector;
import AdministradorSucursal.Usuario;
import AdministradorSucursal.UsuarioJdbc;
import AdministradorSucursal.ViajeJdbc;
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
@WebServlet(name = "CrearSucursal", urlPatterns = {"/AdministrarSucursal/sucursales/CrearSucursal"})
public class CrearSucursal extends HttpServlet {

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
        UsuarioJdbc Usdb = new UsuarioJdbc(Conector.getInstance().getConnection());
        ArrayList<Usuario> admins = Usdb.usuariosAdminSucursal();
        request.setAttribute("administradores", admins);
          RequestDispatcher dispatcher = request.getRequestDispatcher("/Frontend/AdministradorSistema/CrearSucursales.jsp");
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
        validarSucursal logica = new validarSucursal();
        String resultado = logica.crearSucursal(request);
        request.setAttribute("resultado", resultado);
         UsuarioJdbc Usdb = new UsuarioJdbc(Conector.getInstance().getConnection());
        ArrayList<Usuario> admins = Usdb.usuariosAdminSucursal();
        request.setAttribute("administradores", admins);
          RequestDispatcher dispatcher = request.getRequestDispatcher("/Frontend/AdministradorSistema/CrearSucursales.jsp");
        dispatcher.forward(request, response);
       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
   
}
