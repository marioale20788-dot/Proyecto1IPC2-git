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
import java.sql.Date;

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

    public String commprarBoleto(HttpServletRequest request) {
        BoletosJdbc boletoDb = new BoletosJdbc(Conector.getInstance().getConnection());
        String idviaje = request.getParameter("idViaje");
        String boletosCom = request.getParameter("boletosComprados");
        String[] boletosComprados = boletosCom.split(",");
        Usuario usuarioCompra = (Usuario) request.getSession().getAttribute("usuario");

        Date fechaCompra = null;
        boolean datosCorrectos = true;
        String error = "";
        String resultado = "NO SE PUDO REALIZAR LA COMPRA, CREDITOS INSUFICIENTES";
        int cont = 0;
        try {
            fechaCompra = Date.valueOf(request.getParameter("fechaCompra"));

        } catch (IllegalArgumentException | NullPointerException e) {
            error = " FORMATO DE ENTRADA INCORRECTO";
            datosCorrectos = false;

        }
        if (datosCorrectos == true) {

            for (String boletosComprado : boletosComprados) {
                try {
                    int numero = Integer.valueOf(boletosComprado);
                    Boleto boleto = new Boleto(usuarioCompra.getDpi(), idviaje, fechaCompra, numero, null);
                    if (boletoDb.asientoOcupado(idviaje, numero) == false) {

                        if (validarPrecio(request, boletoDb, usuarioCompra) == true) {
                            cont++;
                            boletoDb.comprarBoleto(boleto);

                            resultado = cont + " BOLETOS COMPRADOS";
                        }
                    }else{
                     resultado = " ASIENTO OCUPADO";   
                    }

                } catch (NumberFormatException e) {
                    return "NO SE PUDO REALIZAR ESTA ACCION ";
                }

            }

            return resultado;

        } else {
            return error;
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
