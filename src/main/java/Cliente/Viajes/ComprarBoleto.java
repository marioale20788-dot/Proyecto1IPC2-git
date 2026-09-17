/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Cliente.Viajes;

import AdministradorSucursal.ViajeObj;
import Cliente.BusViaje;
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
@WebServlet(name = "ComprarBoleto", urlPatterns = {"/AdministradorSucusal/Cliente/ComprarBoleto"})
public class ComprarBoleto extends HttpServlet {

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

        comprarBoleto(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Frontend/Cliente/ComprarBoleto/ComprarBoleto.jsp");
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

        ComprarBoletoLogica comprarBoleto = new ComprarBoletoLogica();
        String resultado = comprarBoleto.commprarBoleto(request);
        request.setAttribute("resultado", resultado);

        comprarBoleto(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Frontend/Cliente/ComprarBoleto/ComprarBoleto.jsp");
        dispatcher.forward(request, response);

    }

    public void comprarBoleto(HttpServletRequest request) {
        ComprarBoletoLogica comprarBoleto = new ComprarBoletoLogica();
        ViajeObj viaje = comprarBoleto.obtenerViaje(request);
        BusViaje busAsientos = comprarBoleto.obtenerBusViaje(request, viaje);
        request.setAttribute("viaje", viaje);
        request.setAttribute("busViaje", busAsientos);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
