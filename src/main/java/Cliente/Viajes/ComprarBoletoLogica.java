/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente.Viajes;

import AdministradorSucursal.Bus;
import AdministradorSucursal.BusJdbc;
import AdministradorSucursal.Conector;
import AdministradorSucursal.Usuario;
import AdministradorSucursal.UsuarioJdbc;
import AdministradorSucursal.ViajeJdbc;
import AdministradorSucursal.ViajeObj;
import Cliente.Boleto;
import Cliente.BoletosJdbc;
import Cliente.BusViaje;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author mario
 */
public class ComprarBoletoLogica {

    public ViajeObj obtenerViaje(HttpServletRequest request) {
        String idViaje = request.getParameter("idViaje");

        ViajeObj viajeCompra;
        ViajeJdbc viajeDb = new ViajeJdbc(Conector.getInstance().getConnection());
        viajeCompra = viajeDb.obtenerViajePorId(idViaje);
        return viajeCompra;
    }

    public BusViaje obtenerBusViaje(HttpServletRequest request, ViajeObj viaje) {

        String idViaje = request.getParameter("idViaje");
        BusJdbc busDb = new BusJdbc(Conector.getInstance().getConnection(), null);

        String idSucursal = busDb.buscarSucursalAsociada(viaje.getIdBus());
        Bus bus = busDb.BuscarBuses(idSucursal, viaje.getIdBus()).getFirst();

        BusViaje busViaje = new BusViaje(bus);
        busViaje.crearMatriz(idViaje);;
        return busViaje;

    }

    public String comprarBoleto(HttpServletRequest request) {
        Connection conn = null;
        try {
            Date fechaCompra = Date.valueOf(request.getParameter("fechaCompra"));
            String idviaje = request.getParameter("idViaje");
            String[] boletosComprados = request.getParameter("boletosComprados").split(",");
            Usuario usuarioCompra = (Usuario) request.getSession().getAttribute("usuario");

            conn = Conector.getInstance().getConnection();
            conn.setAutoCommit(false);
            BoletosJdbc boletoDb = new BoletosJdbc(conn);
            int cont = 0;

            for (String boleto : boletosComprados) {
                int numero = Integer.parseInt(boleto.trim());

                if (boletoDb.asientoOcupado(idviaje, numero)) {
                    conn.rollback();
                    return "Asiento ocupado, se cancelo la compra.";
                }

                if (!validarPrecio(request, boletoDb, usuarioCompra)) {
                    conn.rollback();
                    return "creditos insuficientes,se cancelo la compra.";
                }

                boletoDb.comprarBoleto(new Boleto(usuarioCompra.getDpi(), idviaje, fechaCompra, numero, null));
                cont++;
            }

            conn.commit();
            return cont + " BOLETOS COMPRADOS";

        } catch (IllegalArgumentException e) {
            return "FORMATO DE ENTRADA INCORRECTO";
        } catch (Exception e) {
            if (conn != null) {
            try { 
                conn.rollback(); 
            } catch (SQLException ex) {
            }
        }
            return "NO SE PUDO REALIZAR LA COMPRA";
        } finally {
            try {
                if(conn!=null){
                     conn.setAutoCommit(true);
                }
               
            } catch (SQLException e) {
            }

        }
    }

    public boolean validarPrecio(HttpServletRequest request, BoletosJdbc boletoDb, Usuario us) {
        ViajeObj viaje = obtenerViaje(request);
        UsuarioJdbc usDb = new UsuarioJdbc(Conector.getInstance().getConnection());
        double precio = viaje.getPrecioBoleto();
        double creditos = us.getCredito();
        if (creditos < precio) {
            return false;
        }
        double nuevosCreditos = creditos - precio;

        usDb.comprarBoletos(nuevosCreditos, us.getDpi());

        us.setCredito(nuevosCreditos);

        request.getSession().setAttribute("usuario", us);
        return true;

    }

}
