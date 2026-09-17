/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdministradorSucursal.Viajes;

import AdministradorSucursal.AlquilerPrivadoJdbc;
import AdministradorSucursal.AlquilerPrivadoObj;
import AdministradorSucursal.BusJdbc;
import AdministradorSucursal.ChoferJdbc;
import AdministradorSucursal.Conector;
import AdministradorSucursal.ViajeJdbc;
import AdministradorSucursal.ViajeObj;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author mario
 */
public class AlquilerPrivadoLogica {

    public ArrayList<AlquilerPrivadoObj> alquileresPrivadosAprobados(HttpServletRequest request) {
        String idSucursal = (String) request.getSession().getAttribute("idSucursal");
        AlquilerPrivadoJdbc baseDeDatos = new AlquilerPrivadoJdbc(Conector.getInstance().getConnection());
        return baseDeDatos.alquileresPrivados(idSucursal, true);

    }

    public ArrayList<AlquilerPrivadoObj> alquileresPrivadosPorAprobar(HttpServletRequest request) {
        String idSucursal = (String) request.getSession().getAttribute("idSucursal");
        AlquilerPrivadoJdbc baseDeDatos = new AlquilerPrivadoJdbc(Conector.getInstance().getConnection());
        return baseDeDatos.alquileresPrivados(idSucursal, false);

    }

    public String crearViajeAlquiler(HttpServletRequest request) {
        String idBus = request.getParameter("busSeleccionado");
        String idChofer = request.getParameter("choferSeleccionado");
        String idViajeAlquiler = request.getParameter("idViajePrivado");
        String idSucursal = (String) request.getSession().getAttribute("idSucursal");
        ViajeJdbc viajeDb = new ViajeJdbc(Conector.getInstance().getConnection());
        ChoferJdbc choferDb = new ChoferJdbc(Conector.getInstance().getConnection(), null);
        BusJdbc busDb = new BusJdbc(Conector.getInstance().getConnection(), null);
        AlquilerPrivadoJdbc alquilerDb = new AlquilerPrivadoJdbc(Conector.getInstance().getConnection());
        ViajeLogica validar = new ViajeLogica();

        if (validar.validarBus(idBus, idSucursal) && validar.validarChofer(idChofer, idSucursal)) {
            if (choferDb.verificarViajes(idChofer) == false && busDb.buscarBusEnViaje(idBus) == false) {
                AlquilerPrivadoObj alquiler = alquilerDb.buscarAlquilerPrivado(idViajeAlquiler, idSucursal);
                String idNuevoViaje = viajeDb.generarIdAleatorio();
                LocalDateTime salida = alquiler.getFechaSalida().toLocalDate().atTime(LocalTime.MIN);
                LocalDateTime llegada = alquiler.getFechaRetorno().toLocalDate().atTime(LocalTime.MIN);
                ViajeObj viaje = new ViajeObj(idNuevoViaje, "PRIVADO", idBus, "ESPERA", salida, llegada, 0.0, idChofer, null, idViajeAlquiler);
                String resultado = viajeDb.crearViaje(viaje, idSucursal);
                alquilerDb.confirmarViajeAlquiler(idViajeAlquiler,alquiler.getIdCliente());
                return resultado;

            } else {
                return " NO SE PUDO INICIAR EL VIAJE";
            }

        } else {
            return " NO SE PUDO INICIAR EL VIAJE";
        }

    }

    public String confirmarPrecio(HttpServletRequest request) {
        
        String precioFinal = request.getParameter("precioFinal");
        String idViajeAlquiler = request.getParameter("idViajePrivado");
        String idSucursal = (String) request.getSession().getAttribute("idSucursal");
        boolean datoCorrecto = true;
        AlquilerPrivadoJdbc alquilerDb = new AlquilerPrivadoJdbc(Conector.getInstance().getConnection());
        String error = "";
        double precioFinalNumero = 0.0;
        
        try {
            precioFinalNumero = Double.parseDouble(precioFinal);
            if (precioFinalNumero <= 0) {
                datoCorrecto = false;
                error = " PRECIO NEGATIVO";
            }

        } catch (NumberFormatException e) {
            datoCorrecto = false;
            error = "FORMATO DE ENTRADA INCORRECTO";
        }
        if (datoCorrecto == true) {
            return alquilerDb.caonfirmarPrecioFinal(idViajeAlquiler, precioFinalNumero, idSucursal);

        } else {
            return error;
        }

    }

}
