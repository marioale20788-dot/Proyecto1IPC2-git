/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ValidarEntradas.Bus.Chofer;

import AdministradorSucursal.Chofer;
import AdministradorSucursal.ChoferJdbc;
import AdministradorSucursal.Conector;
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
@WebServlet(name = "valdarChoferEditarChofer", urlPatterns = {"/ValidarEntradas/Chofer/ValidarChoferEditarChofer"})
public class valdarChoferEditarChofer extends HttpServlet {

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
     
        ValidarChoferLogica validar = new ValidarChoferLogica();
        Chofer chofer = validar.buscarChoferEditar(request);
     if (chofer == null) {
            request.setAttribute("error", "NO SE ENCONTRO NINGUN CHOFER ASOCIADO A ESE NUMERO DE LICENCIA EN ESTA SUCURSAL");
        } else {
            request.setAttribute("chofer", chofer);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Frontend/AdministradorSucursal/Chofer/EditarChofer.jsp");
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
        
           
        ChoferJdbc choferBaseDeDatos = new ChoferJdbc(Conector.getInstance().getConnection(), null);
        ValidarChoferLogica logica = new ValidarChoferLogica();
        String resultado = logica.updateChofer(request);
        String licenciaChofer = (String) request.getParameter("numeroLicencia");
        String sucursal =(String)request.getSession().getAttribute("idSucursal");
        ArrayList<Chofer> chofer = choferBaseDeDatos.buscarChoferes(licenciaChofer, sucursal);
        request.setAttribute("resultado", resultado);
       
            if(chofer.isEmpty()){
                 request.setAttribute("error", "NO SE ENCONTRO NINGUN BUS ASOCIADO A ESA PLACA EN ESTA SUCURSAL");
            }else{
                request.setAttribute("chofer", chofer.get(0));
            }
        
           RequestDispatcher dispatcher = request.getRequestDispatcher("/Frontend/AdministradorSucursal/Chofer/EditarChofer.jsp");
        dispatcher.forward(request, response);
        
        
        
    }
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
   

}
