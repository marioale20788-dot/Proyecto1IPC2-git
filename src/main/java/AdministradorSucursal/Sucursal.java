/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdministradorSucursal;

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

    public Sucursal(String idSucursal, String nombre, double depreciacion, double latitud, double longitud) {
        this.idSucursal = idSucursal;
        this.nombre = nombre;
        this.depreciacion = depreciacion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getIdSucursal() {
        return idSucursal;
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
