/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdministradorSucursal.Viajes;

import AdministradorSucursal.AlquilerPrivadoObj;
import AdministradorSucursal.Bus;
import AdministradorSucursal.Chofer;
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
@WebServlet(name = "AlquilerPrivado", urlPatterns = {"/AdministradorSucursal/Viajes/AlquilerPrivado"})
public class AlquilerPrivado extends HttpServlet {

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
        AlquilerPrivadoLogica logica = new AlquilerPrivadoLogica();
        ViajeLogica logicaViaje = new ViajeLogica();
        inicarViajeAlquiler(request, logica, logicaViaje);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Frontend/AdministradorSucursal/Viajes/ViajesPrivados.jsp");
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
        AlquilerPrivadoLogica logica = new AlquilerPrivadoLogica();
        ViajeLogica logicaViaje = new ViajeLogica();
        String busSeleccionado = request.getParameter("busSeleccionado");
        String choferSeleccionad = request.getParameter("choferSeleccionado");
        String precioFinal = request.getParameter("precioFinal");
        String resultado = "";
        if (busSeleccionado != null && choferSeleccionad != null) {
            resultado = logica.crearViajeAlquiler(request);
            request.setAttribute("resultado", resultado);

        } else{
            request.setAttribute("resultado", "NO SE PUDO INICIAR EL VIAJE, SIN BUS O CHOFER SELECCIONADO");
        } 
            if (precioFinal != null) {
            resultado = logica.confirmarPrecio(request);
            request.setAttribute("resultado", resultado);

        }
        inicarViajeAlquiler(request, logica, logicaViaje);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Frontend/AdministradorSucursal/Viajes/ViajesPrivados.jsp");
        dispatcher.forward(request, response);

    }

    public void inicarViajeAlquiler(HttpServletRequest request, AlquilerPrivadoLogica alquiler, ViajeLogica logicaViaje) {
        ArrayList<AlquilerPrivadoObj> aprobados = alquiler.alquileresPrivadosAprobados(request);
        ArrayList<AlquilerPrivadoObj> porAprobar = alquiler.alquileresPrivadosPorAprobar(request);
        ArrayList<Bus> buses = logicaViaje.busesDisponibles(request);
        ArrayList<Chofer> choferes = logicaViaje.choferesDisponibles(request);
        request.setAttribute("alquileresPrivadosAprobados", aprobados);
        request.setAttribute("alquileresPrivadosPorAprobar", porAprobar);
        request.setAttribute("buses", buses);
        request.setAttribute("choferes", choferes);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
}
