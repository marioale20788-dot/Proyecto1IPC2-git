/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Cliente;

import AdministradorSucursal.Conector;
import AdministradorSucursal.Usuario;
import AdministradorSucursal.UsuarioJdbc;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author mario
 */
@WebServlet(name = "InicioCliente", urlPatterns = {"/Cliente/InicioCliente"})
public class InicioCliente extends HttpServlet {

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
        UsuarioJdbc usuarioDb = new UsuarioJdbc(Conector.getInstance().getConnection());
        String monto = request.getParameter("montoARecargar");
        double montoReal = 0.0;
        Usuario cliente = (Usuario) request.getSession().getAttribute("usuario");
        try {
            montoReal = Double.valueOf(monto);
            if (montoReal <= 0) {
                request.setAttribute("resultado", "ENTRADA NEGATIVA");
            } else {
                double montoCliente = cliente.getCredito() + montoReal;
                String resultado = usuarioDb.recargarCreditos(montoReal, cliente.getDpi());
                cliente.setCredito(montoCliente);
                request.getSession().setAttribute("usuario", cliente);
                request.setAttribute("resultado", resultado);

            }

        } catch (NumberFormatException| NullPointerException e) {
            request.setAttribute("resultado", "FORMATO INCORRECTO");
        }
           RequestDispatcher dispatcher = request.getRequestDispatcher("/Frontend/Cliente/Cliente/InicioUsuario.jsp");
        dispatcher.forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
}
