/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ValidarEntradas;

import AdministradorSucursal.Bus;
import AdministradorSucursal.BusJdbc;
import AdministradorSucursal.Conector;
import jakarta.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.ArrayList;

/**
 *
 * @author mario
 */
public class validarBusLogica {

    public String crearBusEnBaseDeDatos(HttpServletRequest request) {
        boolean formatosCorrectos = true;
        String error = "";
        String numeroPlaca = request.getParameter("numeroPlaca");
        String marca = request.getParameter("marca");
        String modelo = request.getParameter("modelo");
        String foto = request.getParameter("foto");
        String idSucursal = request.getParameter("idSucursal");
        int fabricacion = 0;
        double kmActual = 0.0;
        int capacidad = 0;
        if (numeroPlaca.isEmpty() || marca.isEmpty() || modelo.isEmpty() || foto.isEmpty() || idSucursal.isEmpty()) {
            formatosCorrectos = false;
            error = "Entrada vacia";
        }

        try {
            fabricacion = Integer.valueOf(request.getParameter("yearFabricacion"));
            kmActual = Double.valueOf(request.getParameter("kilometrajeActual"));
            capacidad = Integer.valueOf(request.getParameter("capacidadPasajeros"));
            if ((fabricacion <= 0) || kmActual < 0 || capacidad <= 0) {
                formatosCorrectos = false;
                error = error + "- entrada de digito negativa";
            }

        } catch (NumberFormatException e) {
            formatosCorrectos = false;
            error = error + "- formato de digito incorrecta";
        }
        if (formatosCorrectos == false) {

            return error;
        } else {
            boolean estado = Boolean.valueOf(request.getParameter("estado"));

            Bus bus = new Bus(numeroPlaca, modelo, marca, foto, fabricacion, kmActual, capacidad, estado, idSucursal);
            BusJdbc busBaseDeDatos = new BusJdbc(Conector.getInstance().getConnection(), bus);
            String correcto = "Bus creado, placa: " + bus.getNumeroPlaca();
            return correcto;

        }

    }

    public ArrayList<Bus> buscarBus(HttpServletRequest request) {
        String numeroPlaca = request.getParameter("inputNumeroPlaca");
        String idSucursal = request.getParameter("inputIdSucursal");

        if (numeroPlaca != null && idSucursal != null) {

            BusJdbc busJdbc = new BusJdbc(Conector.getInstance().getConnection(), null);

            ArrayList<Bus> buses = new ArrayList<>();
            if (!idSucursal.isEmpty() && !numeroPlaca.isEmpty()) {

                buses = busJdbc.BuscarBuses(idSucursal, numeroPlaca);
                return buses;

            } else if (!idSucursal.isEmpty() && numeroPlaca.isEmpty()) {
                buses = busJdbc.BuscarBuses(idSucursal, null);
                return buses;
            }

        }
        return null;
    }

    public Bus buscarBusEditar(HttpServletRequest request) {

        BusJdbc busJdbc = new BusJdbc(Conector.getInstance().getConnection(), null);
        ArrayList<Bus> buses = busJdbc.BuscarBuses(request.getParameter("idSucursal"), request.getParameter("placa"));
        if (buses.isEmpty()) {
            return null;
        } else {
            return buses.get(0);
        }
    }

    public String updateBus(HttpServletRequest request) {

        boolean formatosCorrectos = true;
        String error = "";
        String itemAcambiar = request.getParameter("itemSeleccionado");
        String placasBus = request.getParameter("placaBus");
        String nuevoValor;
        Object valorReal = "";

        if (itemAcambiar.equalsIgnoreCase("estado_operativo")) {
            nuevoValor = request.getParameter("itemEstado");

            if (nuevoValor.equalsIgnoreCase("false")) {
                BusJdbc verficarViajes = new BusJdbc(Conector.getInstance().getConnection(), null);
                boolean busEnUso = verficarViajes.verficarBusEnViaje(placasBus);
                if (busEnUso) {
                    error = "Este bus se encuentra en un viaje, no se puede realizar este cambio ";
                } else {
                    valorReal = nuevoValor;
                }

            }

        } else {
            nuevoValor = request.getParameter("entradaNuevoItem");

            try {

                if (itemAcambiar.equalsIgnoreCase("fabricacion") || itemAcambiar.equalsIgnoreCase("capacidad_pasajeros")) {

                    int temporal = Integer.parseInt(nuevoValor);

                    if (temporal <= 0) {
                        formatosCorrectos = false;
                        error = "Numero negativo";
                    } else {
                        valorReal = temporal;
                    }

                } else if (itemAcambiar.equalsIgnoreCase("kilometraje_actual")) {

                    double temporal = Double.parseDouble(nuevoValor);

                    if (temporal <= 0) {
                        formatosCorrectos = false;
                        error = " Numero negativo";
                    } else {
                        valorReal = temporal;
                    }
                } else {
                    valorReal = nuevoValor;
                }

            } catch (NumberFormatException e) {
                formatosCorrectos = false;
                error = "El valor ingresado no tiene un formato numérico válido";
            }

        }

        if (formatosCorrectos == true) {
            BusJdbc cambiarDatos = new BusJdbc(Conector.getInstance().getConnection(), null);
            String resultado = cambiarDatos.updateBus(itemAcambiar, valorReal, placasBus);
            return resultado;

        } else {
            return error;

        }

    }

}
