/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdministradorSucursal;

import java.util.ArrayList;

/**
 *
 * @author mario
 */
public class Sucursal {
    
    private String idSucursal;
    private String nombre;
    private double depreciacion;
    private double latitud;
    private double longitud;
    private ArrayList<Usuario> Administradores;
     private ArrayList<Usuario> AdministradoresFaltantes;

    public Sucursal(String idSucursal, String nombre, double depreciacion, double latitud, double longitud) {
        this.idSucursal = idSucursal;
        this.nombre = nombre;
        this.depreciacion = depreciacion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public ArrayList<Usuario> getAdministradores() {
        return Administradores;
    }

    public void setAdministradores(ArrayList<Usuario> Administradores) {
        this.Administradores = Administradores;
    }

    public String getIdSucursal() {
        return idSucursal;
    }

    public ArrayList<Usuario> getAdministradoresFaltantes() {
        return AdministradoresFaltantes;
    }

    public void setAdministradoresFaltantes(ArrayList<Usuario> AdministradoresFaltantes) {
        this.AdministradoresFaltantes = AdministradoresFaltantes;
    }

    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getDepreciacion() {
        return depreciacion;
    }

    public void setDepreciacion(double depreciacion) {
        this.depreciacion = depreciacion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
    
    
    
}
