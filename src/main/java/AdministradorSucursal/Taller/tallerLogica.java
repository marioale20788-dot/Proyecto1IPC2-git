/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdministradorSucursal.Taller;

import AdministradorSucursal.Bus;
import AdministradorSucursal.BusJdbc;
import AdministradorSucursal.Conector;
import AdministradorSucursal.TallerJdbc;
import AdministradorSucursal.TallerObj;
import AdministradorSucursal.Viajes.ViajeLogica;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.IllegalFormatException;

/**
 *
 * @author mario
 */
public class tallerLogica {

    public ArrayList<Bus> buses(HttpServletRequest request) {
        BusJdbc busDb = new BusJdbc(Conector.getInstance().getConnection(), null);
        ArrayList<Bus> buses = new ArrayList<>();
        String idSucursal = (String) request.getSession().getAttribute("idSucursal");
        return buses = busDb.BuscarBuses(idSucursal, null);

    }

    public String mantenimiento(HttpServletRequest request) {
        TallerJdbc tallerDb = new TallerJdbc(Conector.getInstance().getConnection());
        String busSeleccionado = request.getParameter("busSeleccionado");
        String idSucursal = (String) request.getSession().getAttribute("idSucursal");
        String idRecibo = request.getParameter("idRecibo");
        double montoRepuesto = 0;
        double montoManoDeObra = 0;
        Date fechaManenimiento = null;
        boolean valoresCorrectos = true;
        ViajeLogica verificarBus = new ViajeLogica();
        String error = "";
        if (verificarBus.validarBus(busSeleccionado, idSucursal) == false) {
            return " BUS SELECCIONADO NO ENCONTRADO";
        }
        if (idRecibo.isEmpty()) {
            return " DEBE ESPECIFICAR UN ID RECIBO";
        }

        try {
            montoRepuesto = Double.valueOf(request.getParameter("montoRepuestos"));
            montoManoDeObra = Double.valueOf(request.getParameter("montoManoDeObra"));
            fechaManenimiento = Date.valueOf(request.getParameter("fechaMantenimiento"));
            if (montoRepuesto <= 0 || montoManoDeObra <= 0) {
                valoresCorrectos = false;
                error = " NUMEROS NEGATIVOS - NO SE PUDO CREAR EL MANTENIMIENTO";
            }

        } catch ( IllegalArgumentException e) {
            valoresCorrectos = false;
            error = " FORMATO DE ENTRADA INCORRECTO - NO SE PUDO CREAR EL MANTENIMIENTO";

        }
        

        if (valoresCorrectos == true) {
            TallerObj taller = new TallerObj(busSeleccionado, fechaManenimiento, montoRepuesto, montoManoDeObra, idRecibo.trim());
            return tallerDb.crearMantenimiento(taller);
        } else {
            return error;

        }

    }

}
