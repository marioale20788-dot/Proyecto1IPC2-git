/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Cliente.Viajes;

import AdministradorSucursal.Conector;
import AdministradorSucursal.Usuario;
import Cliente.Boleto;
import Cliente.BoletosJdbc;
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
@WebServlet(name = "BuscarBoletos", urlPatterns = {"/Cliente/Viajes/BuscarBoletos"})
public class BuscarBoletos extends HttpServlet {

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
        BoletosJdbc boletoDb = new BoletosJdbc(Conector.getInstance().getConnection());
        String tipo = request.getParameter("tipo");
        Usuario  usuario = (Usuario)request.getSession().getAttribute("usuario");
        ArrayList<Boleto> boletos = boletoDb.obtenerBoletos(tipo, usuario.getDpi());
        if(boletos.isEmpty()){
            request.setAttribute("error", "NO SE ENCONTRO NINGUN");
        }else{
            request.setAttribute("boletosViajes", boletos);
        }
           RequestDispatcher dispatcher = request.getRequestDispatcher("/Frontend/Cliente/Cliente/VerBoletosComprados.jsp");
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
    
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
  
}
