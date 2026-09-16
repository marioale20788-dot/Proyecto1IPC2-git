/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdministradorSucursal.Login;

import AdministradorSucursal.Sucursal;
import AdministradorSucursal.Usuario;
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
@WebServlet(name = "validarLogin", urlPatterns = {"/AdministradorSucursal/Login/validarLogin"})
public class validarLogin extends HttpServlet {

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
        Login login = new Login();
        Usuario usuario = login.buscarUsuario(request);
        if (usuario == null) {
            request.setAttribute("error", "NO SE PUDO REALIZAR EL LOGIN");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        } else {
            request.getSession().setAttribute("usuario", usuario);
            if (usuario.getTipo().equalsIgnoreCase("CLIENTE") || usuario.getTipo().equalsIgnoreCase("ADMIN_SISTEMA")) {

                RequestDispatcher dispatcher = request.getRequestDispatcher("/Frontend/Cliente/Cliente/InicioUsuario.jsp");
                dispatcher.forward(request, response);

            } else {

                ArrayList<Sucursal> sucursales = login.buscarSucursalesAsociadasAAdmi(usuario.getDpi());
                request.setAttribute("usuario", usuario);
                request.setAttribute("sucursales", sucursales);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Frontend/Login/Login.jsp");
                dispatcher.forward(request, response);
            }

        }

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

        String idSucursal = request.getParameter("idSucursal");
        request.getSession().setAttribute("idSucursal", idSucursal);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Frontend/AdministradorSucursal/Inicio.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
}
