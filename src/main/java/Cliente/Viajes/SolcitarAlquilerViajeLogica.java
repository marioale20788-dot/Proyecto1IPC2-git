/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente.Viajes;

import AdministradorSucursal.AlquilerPrivadoJdbc;
import AdministradorSucursal.AlquilerPrivadoObj;
import AdministradorSucursal.Conector;
import AdministradorSucursal.Sucursal;
import AdministradorSucursal.SucursalJdbc;
import AdministradorSucursal.Usuario;
import AdministradorSucursal.UsuarioJdbc;
import AdministradorSucursal.Viajes.AlquilerPrivado;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author mario
 */
public class SolcitarAlquilerViajeLogica {

    public ArrayList<Sucursal> sucursalesDisponibles() {
        ArrayList<Sucursal> sucursales = new ArrayList<>();
        SucursalJdbc sucursaldB = new SucursalJdbc(Conector.getInstance().getConnection());
        sucursales = sucursaldB.retornarTodasSucursales();
        return sucursales;

    }

    public ArrayList<AlquilerPrivadoObj> alquileresPrivados(HttpServletRequest request) {
        ArrayList<AlquilerPrivadoObj> alquileresPrivados = new ArrayList<>();
        Usuario cliente = (Usuario) request.getSession().getAttribute("usuario");
        AlquilerPrivadoJdbc alquilerDb = new AlquilerPrivadoJdbc(Conector.getInstance().getConnection());
        alquileresPrivados = alquilerDb.alquileresPrivadosClientes(cliente.getDpi(), false);
        return alquileresPrivados;

    }

    public String crearViajePrivado(HttpServletRequest request) {
        Usuario cliente = (Usuario) request.getSession().getAttribute("usuario");
        Date fechaSalida = null;
        Date fechaRetorno = null;
        String referenciaOrigen = request.getParameter("referenciaOrigen");
        String referenciaDestino = request.getParameter("referenciaDestino");
        double distanciaKm = 0;
        int numeroPasajeros = 0;
        String sucursalSeleccionada = request.getParameter("sucursalSeleccionada");
        boolean entradasCorrectas = true;
        String error = "";
        if (referenciaOrigen.isEmpty() | referenciaDestino.isEmpty()) {
            return "DEBE INGRESAR UNA REFERENCIA DE ORIGEN Y DESTINO";
        }
        try {
            fechaSalida = Date.valueOf(request.getParameter("fechaSalida"));
            fechaRetorno = Date.valueOf(request.getParameter("fechaRetorno"));
            distanciaKm = Double.valueOf(request.getParameter("distanciaKm"));
            numeroPasajeros = Integer.valueOf(request.getParameter("numeroPasajeros"));
            if(distanciaKm<=0 || numeroPasajeros<=0 ){
                entradasCorrectas = false;
                error =" ENTRADAS DEBEN SER MAYOR A CERO";
            }

        } catch (IllegalArgumentException | NullPointerException e) {
            entradasCorrectas = false;
            error = " FORMATO DE ENTRADA INCORRECTO";
        }

        if (entradasCorrectas == true) {
            AlquilerPrivadoJdbc alquilerDb = new AlquilerPrivadoJdbc(Conector.getInstance().getConnection());
            double precioEstimado = 5 * distanciaKm;
            AlquilerPrivadoObj alquiler = new AlquilerPrivadoObj(alquilerDb.generarIdAleatorio(), cliente.getDpi(), false,
                    fechaSalida, fechaRetorno, referenciaOrigen, referenciaDestino, precioEstimado,
                    0, sucursalSeleccionada, numeroPasajeros, distanciaKm, "ESPERANDO");
            return alquilerDb.crearAlquilerPrivado(alquiler);

        } else {
            return error;
        }

    }

    public String confirmarCancelarViaje(HttpServletRequest request) {
        String opcion = request.getParameter("opcionAceptarEliminar");
        String idAlquilerPrivado = request.getParameter("idViajePrivado");
        Usuario cliente = (Usuario) request.getSession().getAttribute("usuario");
        AlquilerPrivadoJdbc alquilerDb = new AlquilerPrivadoJdbc(Conector.getInstance().getConnection());
        UsuarioJdbc usuarioDb = new UsuarioJdbc(Conector.getInstance().getConnection());
        String resultado = "";

        if (opcion.equalsIgnoreCase("eliminar")) {
            resultado = alquilerDb.eliminarAlquilerPrivado(idAlquilerPrivado, cliente.getDpi());

        } else if (opcion.equalsIgnoreCase("aceptar")) {
            AlquilerPrivadoObj alquiler = alquilerDb.alquileresPrivadosClientes(cliente.getDpi(), false).getFirst();
            if (cliente.getCredito() >= alquiler.getPrecioFinal()) {

                resultado = alquilerDb.confirmarAlquilerPrivado(true, idAlquilerPrivado, cliente.getDpi());
                
                double CreditoFinal = cliente.getCredito() - alquiler.getPrecioFinal();

                cliente.setCredito(CreditoFinal);
                usuarioDb.comprarBoletos(CreditoFinal, cliente.getDpi());

                request.getSession().setAttribute("usuario", cliente);

            } else {
                resultado = "INSUFICIENTE CREDITO PARA PAGAR";
            }

        }
        return resultado;
    }

}
