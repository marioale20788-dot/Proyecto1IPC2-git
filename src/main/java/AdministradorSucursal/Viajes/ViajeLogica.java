/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdministradorSucursal.Viajes;

import AdministradorSucursal.Bus;
import AdministradorSucursal.BusJdbc;
import AdministradorSucursal.Chofer;
import AdministradorSucursal.ChoferJdbc;
import AdministradorSucursal.Conector;
import AdministradorSucursal.DepreciacionJdbc;
import AdministradorSucursal.DetalleViaje;
import AdministradorSucursal.DetalleViajeJdbc;
import AdministradorSucursal.Ruta;
import AdministradorSucursal.RutaJdbc;
import AdministradorSucursal.Sucursal;
import AdministradorSucursal.SucursalJdbc;
import AdministradorSucursal.ViajeJdbc;
import AdministradorSucursal.ViajeObj;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 *
 * @author mario
 */
public class ViajeLogica {

    public String generarIdViaje() {
        ViajeJdbc viajeBaseDeDatos = new ViajeJdbc(Conector.getInstance().getConnection());
        return viajeBaseDeDatos.generarIdAleatorio();

    }

    public ArrayList<Bus> busesDisponibles(HttpServletRequest request) {
        BusJdbc busBaseDeDatos = new BusJdbc(Conector.getInstance().getConnection(), null);
        String idSucursal = (String) request.getSession().getAttribute("idSucursal");
        return busBaseDeDatos.BuscarBusesDisponibles(idSucursal);

    }

    public ArrayList<Chofer> choferesDisponibles(HttpServletRequest request) {
        ChoferJdbc choferBaseDeDatos = new ChoferJdbc(Conector.getInstance().getConnection(), null);
        String idSucursal = (String) request.getSession().getAttribute("idSucursal");
        return choferBaseDeDatos.choferesDisponibles(idSucursal);

    }

    public ArrayList<Ruta> rutasDisponibles(HttpServletRequest request) {
        RutaJdbc rutaBaseDeDaots = new RutaJdbc(Conector.getInstance().getConnection());
        String idSucursal = (String) request.getSession().getAttribute("idSucursal");
        return rutaBaseDeDaots.BuscarRutas(idSucursal, null);

    }

    public ViajeObj viajeAEditar(HttpServletRequest request) {
        String idViaje = request.getParameter("idViaje");
        String idSucursal = (String) request.getSession().getAttribute("idSucursal");
        ViajeJdbc viajeBaseDeDatos = new ViajeJdbc(Conector.getInstance().getConnection());
        ViajeObj viaje = null;
        viaje = viajeBaseDeDatos.obtenerViaje(idViaje, idSucursal);
        return viaje;

    }

    public boolean validarBus(String idNuevoBus, String idSucursal) {
        BusJdbc bus = new BusJdbc(Conector.getInstance().getConnection(), null);
        ArrayList<Bus> busEnOrgrigen = bus.BuscarBuses(idSucursal, idNuevoBus);
        if (!busEnOrgrigen.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validarBoletosBus(String idViaje, String idNuevoBus, String idSucursal) {
        ViajeJdbc viaje = new ViajeJdbc(Conector.getInstance().getConnection());
        BusJdbc bus = new BusJdbc(Conector.getInstance().getConnection(), null);
        ArrayList<Bus> busEnOrgrigen = bus.BuscarBuses(idSucursal, idNuevoBus);
        int boletosVendidos = viaje.boletosVendidos(idViaje);
        if (boletosVendidos > 0) {
            return false;
        }
        return true;

    }

    public boolean validarChofer(String idChofer, String idSucursal) {
        ChoferJdbc chofer = new ChoferJdbc(Conector.getInstance().getConnection(), null);
        ArrayList<Chofer> choferEnOrigen = chofer.buscarChoferes(idChofer, idSucursal);
        if (!choferEnOrigen.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validarRuta(String idRuta, String idSucursal) {
        RutaJdbc ruta = new RutaJdbc(Conector.getInstance().getConnection());
        ArrayList<Ruta> rutas = ruta.BuscarRutas(idSucursal, idRuta);
        if (!rutas.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public String editarViaje(HttpServletRequest request) {

        String itemSeleccionado = request.getParameter("itemSeleccionado");
        String idViaje = request.getParameter("idViaje");
        String idSucursal = (String) request.getSession().getAttribute("idSucursal");
        ViajeJdbc viajeDb = new ViajeJdbc(Conector.getInstance().getConnection());
        Object nuevoValor = null;
        boolean datosCorrectos = false;
        String error;

        if (itemSeleccionado.equalsIgnoreCase("id_bus")) {
            String idBus = request.getParameter("busSeleccionado");
            if (validarBus(idBus, idSucursal) == true) {
                if (validarBoletosBus(idViaje, idBus, idSucursal)) {
                    nuevoValor = idBus;
                    datosCorrectos = true;
                }

            }

        } else if (itemSeleccionado.equalsIgnoreCase("id_chofer")) {
            String idChofer = request.getParameter("choferSeleccionado");
            if (validarChofer(idChofer, idSucursal)==true) {
                nuevoValor = idChofer;
                datosCorrectos = true;
            }

        } else if (itemSeleccionado.equalsIgnoreCase("id_ruta")) {
            String nuevaRuta = request.getParameter("rutasSeleccionadas");
            if (validarRuta(nuevaRuta, idSucursal)==true) {
                nuevoValor = nuevaRuta;
                datosCorrectos = true;
            }

        } else if (itemSeleccionado.equalsIgnoreCase("precio_boleto")) {
            try {

                double nuevoPrecio = Double.valueOf(request.getParameter("entradaTexto"));
                if (nuevoPrecio > 0) {
                    nuevoValor = nuevoPrecio;
                    datosCorrectos = true;
                }
            } catch (NumberFormatException | NullPointerException e) {
                datosCorrectos = false;
            }

        } else {
            try {

                LocalDateTime nuevaFecha = LocalDateTime.parse(request.getParameter("fecha"));
                nuevoValor = nuevaFecha;
                datosCorrectos = true;

            } catch (DateTimeParseException e) {
                datosCorrectos = false;
            }

        }

        if (datosCorrectos == true) {
            return viajeDb.editarViaje(idViaje, itemSeleccionado, nuevoValor);

        } else {
            return "NO SE PUDO EDITAR EL VIAJE";
        }

    }

    public String crearViaje(HttpServletRequest request, String tipo) {
        String idSucursal = (String) request.getSession().getAttribute("idSucursal");
        String idViaje = request.getParameter("idViaje");
        String idBusSeleccionado = request.getParameter("busSeleccionado");
        String idChoferSeleccionado = request.getParameter("choferSeleccionado");
        String idRutaSeleccionada = request.getParameter("rutaSeleccionada");
        String idViajeAlquiler = request.getParameter("idViajeAlquiler");
        LocalDateTime HoraFechaSalida = null;
        LocalDateTime HoraFechaLlegada = null;
        double precioBoleto = 0.0;
        boolean datosValidos = true;
        String error = "";

        if (!validarBus(idBusSeleccionado, idSucursal) || !validarChofer(idChoferSeleccionado, idSucursal) || !validarRuta(idRutaSeleccionada, idSucursal)) {
            return "ELEMENTO INGRESADO NO ENCOTRADO";

        }

        try {
            precioBoleto = Double.valueOf(request.getParameter("precioBoleto"));
            HoraFechaLlegada = LocalDateTime.parse(request.getParameter("llegada"));
            HoraFechaSalida = LocalDateTime.parse(request.getParameter("salida"));

        } catch (NumberFormatException | DateTimeParseException e) {
            datosValidos = false;
            error = " FORMATO DE ENTRADA NO VALIDO";
        }
        if (datosValidos == true) {
            ViajeObj viaje = new ViajeObj(idViaje, tipo, idBusSeleccionado, "ESPERA", HoraFechaSalida,
                    HoraFechaLlegada, precioBoleto, idChoferSeleccionado, idRutaSeleccionada, idViajeAlquiler);

            ViajeJdbc crearViaje = new ViajeJdbc(Conector.getInstance().getConnection());
            return crearViaje.crearViaje(viaje, idSucursal);

        } else {
            return error;

        }

    }

    public ArrayList<ViajeObj> buscarViajes(HttpServletRequest request) {
        String idSucursal = (String) request.getSession().getAttribute("idSucursal");
        String[] estados = request.getParameterValues("estadoViaje");
        String[] tipoViaje = request.getParameterValues("tipoViaje");
        String idViaje = request.getParameter("idViaje");
        ViajeJdbc buscarViaje = new ViajeJdbc(Conector.getInstance().getConnection());
        ArrayList<ViajeObj> viajes = new ArrayList<>();

        if (estados == null) {
            return viajes;
        }
        if (tipoViaje == null) {
            return viajes;
        }

        viajes = buscarViaje.obtenerViajes(estados, tipoViaje, idViaje, idSucursal);
        return viajes;

    }

    public String eliminarViaje(HttpServletRequest request) {
        String idSucursal = (String) request.getSession().getAttribute("idSucursal");
        String idViaje = request.getParameter("idViaje");
        ViajeJdbc viajeBaseDeDatos = new ViajeJdbc(Conector.getInstance().getConnection());
        String resultado = viajeBaseDeDatos.eliminarViaje(idSucursal, idViaje);
        return resultado;

    }

    public String iniciarViaje(HttpServletRequest request) {
        String idSucursal = (String) request.getSession().getAttribute("idSucursal");
        String idViaje = request.getParameter("idViaje");
        String idBus = request.getParameter("idBus");
        String idChofer = request.getParameter("idChofer");
        LocalDateTime horaFechaSalida = null;
        double kilometraje = 0.0;
        boolean datosValidos = true;
        String error = "";

        DetalleViajeJdbc detalleBaseDeDatos = new DetalleViajeJdbc(Conector.getInstance().getConnection());
        ViajeJdbc viajejdbc = new ViajeJdbc(Conector.getInstance().getConnection());
        BusJdbc busjdbc = new BusJdbc(Conector.getInstance().getConnection(), null);

        ViajeObj viajeObj = viajejdbc.obtenerViaje(idViaje, idSucursal);

        if (viajeObj == null) {
            return " NO SE PUDO INICIAR EL VIAJE ";
        } else {
            if (!idBus.equalsIgnoreCase(viajeObj.getIdBus()) || !idChofer.equalsIgnoreCase(viajeObj.getIdChofer())) {
                return " NO SE PUDO INICIAR EL VIAJE --- NUMEROS DE PLACA O LICENCIA CHOFER NO COINCIDEN";
            }
        }

        try {
            horaFechaSalida = LocalDateTime.parse(request.getParameter("salida"));
            kilometraje = Double.valueOf(request.getParameter("kilometraje"));
            Bus bus = busjdbc.BuscarBuses(idSucursal, idBus).get(0);
            if (bus != null) {
                if (kilometraje != bus.getKilometrajeActual()) {
                    datosValidos = false;
                    error = " KILOMETRAJE ACTUAL NO CONCUERDA CON DATO REAL ---- NO SE PUDO INICIAR EL VIAJE";
                }

            }

        } catch (NumberFormatException | DateTimeParseException e) {
            datosValidos = false;
            error = " FORMATO DE ENTRADA NO VALIDO -- NO SE PUDO INICIAR EL VIAJE";
        }

        if (datosValidos == true) {
            DetalleViaje detalle = new DetalleViaje(idViaje, 0.0, kilometraje, idChofer, idBus, horaFechaSalida, null, 0.0);
            return detalleBaseDeDatos.crearDetalleViaje(detalle);

        } else {
            return error;
        }

    }

    public String finalizarViaje(HttpServletRequest request) {
        String idSucursal = (String) request.getSession().getAttribute("idSucursal");
        double gastoTotalCombustible = 0.0;
        double kilometrajeFinal = 0.0;
        LocalDateTime llegada = null;
        boolean datosCorrectos = true;
        String error = "";
        String idViaje = request.getParameter("idViaje");

        DetalleViajeJdbc delleBaseDeDatos = new DetalleViajeJdbc(Conector.getInstance().getConnection());
        BusJdbc busBaseDeDatos = new BusJdbc(Conector.getInstance().getConnection(), null);
        ViajeJdbc viajeBaseDeDatos = new ViajeJdbc(Conector.getInstance().getConnection());
        DepreciacionJdbc depreciacion = new DepreciacionJdbc(Conector.getInstance().getConnection());
        SucursalJdbc sucursalBaseDeDatos = new SucursalJdbc(Conector.getInstance().getConnection());
        ArrayList<Bus> bus = new ArrayList<>();
        ViajeObj viaje = viajeBaseDeDatos.obtenerViaje(idViaje, idSucursal);
        if (viaje == null) {
            return " NO SE PUDO FINALIZAR EL VIAJE";

        }

        try {

            gastoTotalCombustible = Double.valueOf(request.getParameter("gastoCombustible"));
            kilometrajeFinal = Double.valueOf(request.getParameter("kilometrajeFinal"));
            llegada = LocalDateTime.parse(request.getParameter("llegada"));
            if (gastoTotalCombustible <= 0 || kilometrajeFinal <= 0) {
                datosCorrectos = false;
                error = "DIGITOS DE ENTRADA NEGATIVOS";

            } else {
                bus = busBaseDeDatos.BuscarBuses(idSucursal, viaje.getIdBus());
                if (bus.isEmpty()) {
                    datosCorrectos = false;
                    error = " NO SE PUDO FINALIZAR EL VIAJE";
                } else {
                    if (kilometrajeFinal <= bus.get(0).getKilometrajeActual()) {
                        datosCorrectos = false;
                        error = " KILOMETRAJE FINAL NO PUEDE SER IGUAL O MENOR A KM INICIAL";

                    }

                }
            }

        } catch (NumberFormatException | DateTimeParseException e) {
            datosCorrectos = false;
            error = "FORMATO DE ENTRADA INCORRECTO";
        }

        if (datosCorrectos == true) {
            boolean finalizarViaje = delleBaseDeDatos.terminarDetalleViaje(gastoTotalCombustible, kilometrajeFinal, llegada, idViaje);
            if (finalizarViaje == true) {

                String updateBus = busBaseDeDatos.updateBus("kilometraje_actual", kilometrajeFinal, viaje.getIdBus());
                boolean updateEstado = viajeBaseDeDatos.updateStadoViaje("FINALIZADO", idViaje);
                if (updateEstado && updateBus.equalsIgnoreCase("1 elemento editado correctamente")) {
                    double kmInicial = bus.get(0).getKilometrajeActual();
                    double kmFinal = kilometrajeFinal - kmInicial;
                    Sucursal origen = sucursalBaseDeDatos.buscarSucursalPorId(idSucursal);
                    return depreciacion.insertarDepreciacion(idViaje, origen.getDepreciacion(), kmFinal);

                }
                return " NO SE PUDO INICAR CORRECTAMENTE EL VIAJE";

            }
            return " NO SE PUDO INICAR CORRECTAMENTE EL VIAJE";

        } else {
            return error;
        }

    }

}
