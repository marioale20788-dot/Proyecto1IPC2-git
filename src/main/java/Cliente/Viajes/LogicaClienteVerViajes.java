/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente.Viajes;

import AdministradorSucursal.Conector;
import AdministradorSucursal.Ruta;
import AdministradorSucursal.RutaJdbc;
import AdministradorSucursal.Sucursal;
import AdministradorSucursal.SucursalJdbc;
import AdministradorSucursal.ViajeJdbc;
import AdministradorSucursal.ViajeObj;
import java.util.ArrayList;

/**
 *
 * @author mario
 */
public class LogicaClienteVerViajes {

    public ArrayList<ViajeObj> viajesDisponibles() {
        ViajeJdbc viajDb = new ViajeJdbc(Conector.getInstance().getConnection());
        return viajDb.obtenerTodosViajesRegulares();

    }

    public void completarViaje(ArrayList<ViajeObj> viajes) {
        RutaJdbc rutaDb = new RutaJdbc(Conector.getInstance().getConnection());
        SucursalJdbc sucursalDb = new SucursalJdbc(Conector.getInstance().getConnection());
        for (ViajeObj viaje : viajes) {
            Ruta ruta = rutaDb.buscarRuta(viaje.getIdRuta());
            Sucursal sucursalOrigen = sucursalDb.buscarSucursalPorId(ruta.getIdSucursalOrigen());
            Sucursal sucursalDestino = sucursalDb.buscarSucursalPorId(ruta.getIdSucursalDestino());
            ruta.setSucursalDestino(sucursalDestino);
            ruta.setSucursalOrigen(sucursalOrigen);
            viaje.setRuta(ruta);

        }

    }

    public ArrayList<ViajeObj> viajesCompletos() {
        ArrayList<ViajeObj> viajesCompletos = viajesDisponibles();

        completarViaje(viajesCompletos);
        return viajesCompletos;

    }
    
    
  
    
    

}
