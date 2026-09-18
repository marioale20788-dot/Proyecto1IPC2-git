/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ValidarEntradas.Bus.Chofer;

import AdministradorSucursal.Chofer;
import AdministradorSucursal.ChoferJdbc;
import AdministradorSucursal.Conector;
import jakarta.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author mario
 */
public class ValidarChoferLogica {

    public String crearChoferEnBaseDeDatos(HttpServletRequest request) {
        String error = "";
        boolean formatosCorrectos = true;
        String numeroLicencia = request.getParameter("numeroLicencia");
        String nombreCompleto = request.getParameter("nombreCompleto");
        String foto = request.getParameter("foto");
        Date fecha = null;
        String numeroTelefono = "";
        double salarioBase = 0.0;

        String idSucursal = (String) request.getSession().getAttribute("idSucursal");
        String tipoLicencia = request.getParameter("tipoLicencia");
        boolean estado = Boolean.valueOf(request.getParameter("estado"));

        if (numeroLicencia.isEmpty() || nombreCompleto.isEmpty() || foto.isEmpty() || foto.isEmpty() || idSucursal.isEmpty()) {
            formatosCorrectos = false;
            error = "Entrada vacia";
        }
        try {
            int numeroDeTelefotoInt = Integer.valueOf(request.getParameter("numeroDeTelefono"));
            salarioBase = Double.valueOf(request.getParameter("salarioBase"));
            fecha = Date.valueOf(request.getParameter("fechaVencimiento"));
            if ((salarioBase <= 0) || numeroDeTelefotoInt < 0) {
                formatosCorrectos = false;
                error = error + "- entrada de digito negativa";
            } else {
                numeroTelefono = String.valueOf(numeroDeTelefotoInt);
            }

        } catch (Exception e) {
            formatosCorrectos = false;
            error = error + "- formato de entrada incorrecta";
        }

        if (formatosCorrectos == false) {
            return error;
        } else {

            Chofer chofer = new Chofer(nombreCompleto, numeroTelefono, numeroLicencia, foto, tipoLicencia, estado, salarioBase, idSucursal, fecha);
            ChoferJdbc baseDeDatos = new ChoferJdbc(Conector.getInstance().getConnection(), chofer);
            String resultado = baseDeDatos.crearChofer();
            return resultado;
        }

    }

    public ArrayList<Chofer> buscarChofer(HttpServletRequest request) {
        String numeroLicencia = request.getParameter("numeroLicencia");
        String idSucursal = (String) request.getSession().getAttribute("idSucursal");
        if (numeroLicencia != null && idSucursal != null) {

            ChoferJdbc choferJdbc = new ChoferJdbc(Conector.getInstance().getConnection(), null);

            ArrayList<Chofer> choferes = new ArrayList<>();
            if (!numeroLicencia.isEmpty() && !idSucursal.isEmpty()) {

                choferes = choferJdbc.buscarChoferes(numeroLicencia, idSucursal);
                return choferes;

            } else if (!idSucursal.isEmpty() && numeroLicencia.isEmpty()) {
                choferes = choferJdbc.buscarChoferes(null, idSucursal);
                return choferes;
            }

        }
        return null;

    }

    public ArrayList<Chofer> buscarChoferesReportes(HttpServletRequest request) {
        String idSucursal = (String) request.getSession().getAttribute("idSucursal");
        ArrayList<Chofer> choferes = new ArrayList<>();
        ChoferJdbc choferJdbc = new ChoferJdbc(Conector.getInstance().getConnection(), null);
        choferes = choferJdbc.buscarChoferes(null, idSucursal);
        return choferes;
    }

    public Chofer buscarChoferEditar(HttpServletRequest request) {

        ChoferJdbc choferJdbc = new ChoferJdbc(Conector.getInstance().getConnection(), null);
        String idSucursal = (String) request.getSession().getAttribute("idSucursal");
        ArrayList<Chofer> chofer = choferJdbc.buscarChoferes(request.getParameter("numeroLicencia"), idSucursal);
        if (chofer.isEmpty()) {
            return null;
        } else {
            return chofer.get(0);
        }
    }

    public String updateChofer(HttpServletRequest request) {
        boolean formatosCorrectos = true;
        String error = "";
        String itemAcambiar = request.getParameter("itemSeleccionado");
        String numeroLicencia = request.getParameter("numeroLicencia");
        String nuevoValor;
        Object valorReal = "";

        ChoferJdbc choferBaseDeDatos = new ChoferJdbc(Conector.getInstance().getConnection(), null);
        ArrayList<Chofer> chofer = choferBaseDeDatos.buscarChoferes(numeroLicencia, (String) request.getSession().getAttribute("idSucursal"));
        if (chofer.isEmpty()) {
            error = "no se pudo editar el bus";
            return error;
        }

        if (choferBaseDeDatos.verificarViajes(numeroLicencia)) {
            error = "ESTE CHOFER SE ENCUENTRA EN UN VIAJE NO SE PUEDE EDITAR";
            return error;
        }

        if (itemAcambiar.equalsIgnoreCase("estado_operativo")) {
            nuevoValor = request.getParameter("itemEstado");
            valorReal = nuevoValor;

        } else if (itemAcambiar.equalsIgnoreCase("tipo_licencia")) {
            nuevoValor = request.getParameter("itemTipoLicencia");
            valorReal = nuevoValor;

        } else if (itemAcambiar.equalsIgnoreCase("fecha_vencimiento_licencia")) {
            nuevoValor = request.getParameter("fechaVencimiento");

            try {
                Date fechaTemp = Date.valueOf(nuevoValor);
                valorReal = nuevoValor;
            } catch (Exception e) {
                formatosCorrectos = false;
                error = "El valor ingresado no tiene un formato  válido";
            }

        } else {
            nuevoValor = request.getParameter("entradaNuevoItem");

            try {
                if (itemAcambiar.equalsIgnoreCase("salario_base")) {

                    double temporal = Double.parseDouble(nuevoValor);

                    if (temporal <= 0) {
                        formatosCorrectos = false;
                        error = " Valor ingresado es numero negativo";
                    } else {
                        valorReal = temporal;
                    }
                } else if (itemAcambiar.equalsIgnoreCase("numero_telefono")) {
                    try {
                        int temporal = Integer.valueOf(nuevoValor);
                        if (temporal < 0) {
                            formatosCorrectos = false;
                            error = " Valor ingresado es numero negativo";
                        } else {
                            valorReal = temporal;
                        }
                    } catch (NumberFormatException e) {
                        formatosCorrectos = false;
                        error = "El valor ingresado no tiene un formato  válido";
                    }

                } else {
                    valorReal = nuevoValor;
                }

            } catch (NumberFormatException e) {
                formatosCorrectos = false;
                error = "El valor ingresado no tiene un formato  válido";
            }

        }

        if (formatosCorrectos == true) {

            String resultado = choferBaseDeDatos.updateChofer(itemAcambiar, valorReal, numeroLicencia);
            return resultado;

        } else {
            return error;

        }

    }

}
