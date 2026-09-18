/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdministradorSucursal.Leaflet;

import AdministradorSucursal.Conector;
import AdministradorSucursal.Ruta;
import AdministradorSucursal.RutaJdbc;
import AdministradorSucursal.Sucursal;
import AdministradorSucursal.SucursalJdbc;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 *
 * @author mario
 */
public class BuscarRutas {

    public Sucursal sucursalOrigen(HttpServletRequest request) {
        String idSucursalOrigen = (String) request.getSession().getAttribute("idSucursal");
        SucursalJdbc sucursalBaseDeDatos = new SucursalJdbc(Conector.getInstance().getConnection());
        Sucursal sucursal = sucursalBaseDeDatos.buscarSucursalPorId(idSucursalOrigen);
        return sucursal;

    }
    public Sucursal sucursalOrigenReprte(HttpServletRequest request){
        String idSucursalOrigen =request.getParameter("idSucursalOrigen");
        SucursalJdbc sucursalBaseDeDatos = new SucursalJdbc(Conector.getInstance().getConnection());
        Sucursal sucursal = sucursalBaseDeDatos.buscarSucursalPorId(idSucursalOrigen);
        return sucursal;
        
    }

    public ArrayList<Sucursal> sucursalesDestino(HttpServletRequest request, ArrayList<Ruta> rutas) {;
        SucursalJdbc sucursalBaseDeDatos = new SucursalJdbc(Conector.getInstance().getConnection());

        ArrayList<Sucursal> sucursales = sucursalBaseDeDatos.buscarSucursalesDestinoRutas(rutas);
        return sucursales;

    }

    public ArrayList<Ruta> buscarRutas(HttpServletRequest request) {
        String idSucursalOrigen = (String) request.getSession().getAttribute("idSucursal");
        RutaJdbc rutaBaseDeDatos = new RutaJdbc(Conector.getInstance().getConnection());
        ArrayList<Ruta> rutas = new ArrayList<>();
        try {
            String nombreRuta = request.getParameter("nombreRuta");
            if (nombreRuta.isEmpty() || nombreRuta==null) {
                rutas = rutaBaseDeDatos.BuscarRutas(idSucursalOrigen, null);
            } else {
                rutas = rutaBaseDeDatos.BuscarRutas(idSucursalOrigen, nombreRuta);
            }

        } catch (NullPointerException e) {
            rutas = rutaBaseDeDatos.BuscarRutas(idSucursalOrigen, null);
        }

        return rutas;

    }
      public ArrayList<Ruta> buscarRutasReporte(String idSucursal) {
   
        RutaJdbc rutaBaseDeDatos = new RutaJdbc(Conector.getInstance().getConnection());
        ArrayList<Ruta> rutas = new ArrayList<>();
   
                rutas = rutaBaseDeDatos.BuscarRutas(idSucursal, null);
            
        return rutas;

    }

    public ArrayList<Sucursal> sucursalesCrearRuta(HttpServletRequest request) {
        String idSucursalOrigen = (String) request.getSession().getAttribute("idSucursal");
        SucursalJdbc sucursalBaseDeDatos = new SucursalJdbc(Conector.getInstance().getConnection());

        ArrayList<Sucursal> sucursales = sucursalBaseDeDatos.buscarSucursalesDestino(idSucursalOrigen);
        return sucursales;

    }

    public String eliminarRuta(HttpServletRequest request) {

        String nombreRuta = (String) request.getParameter("nombreRutaEliminar");
        String idSucursalOrigen = (String) request.getSession().getAttribute("idSucursal");

        RutaJdbc rutaBaseDeDatos = new RutaJdbc(Conector.getInstance().getConnection());
        String resultado = rutaBaseDeDatos.eliminarRuta(nombreRuta, idSucursalOrigen);
        return resultado;

    }

    public String crearRuta(HttpServletRequest request) {

        String nombre = request.getParameter("nombreRuta");
        String idSucursalOrigen = (String) request.getSession().getAttribute("idSucursal");
        String idSucursalDestino = request.getParameter("sucursalDestino");
        RutaJdbc rutaBaseDeDatos = new RutaJdbc(Conector.getInstance().getConnection());
        double distanciaKm = 0.0;
        boolean datosValidos = true;
        String error = "";
        String correcto = "";
        
  
        if (nombre.isEmpty()) {
            datosValidos = false;
            error = " DEBE INGRESAR UN NOMBRE";

        }
        try {
            distanciaKm = Double.valueOf(request.getParameter("distanciaKm"));
            if (distanciaKm <= 0) {
                datosValidos = false;
                error = " DISTANCIA NEGATIVA";
            }

        } catch (NumberFormatException e) {
            datosValidos = false;
            error = "FORMATO DE DIGITO INCORRECTO";
        }

        if (datosValidos == true) {
           
                Ruta ruta = new Ruta(nombre, idSucursalOrigen, idSucursalDestino, distanciaKm);
                correcto = rutaBaseDeDatos.crearRuta(ruta);
                return correcto;

            

        } else {
            return error;
        }

    }

}
