/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdministradorSucursal;

/**
 *
 * @author mario
 */
public class Depreciacion {
    
    private String idViaje;
    private double tasaDepreciacion;
    private double kmRecorridos;
    private double montoDepreciacion;

    public Depreciacion(String idViaje, double tasaDepreciacion, double kmRecorridos) {
        this.idViaje = idViaje;
        this.tasaDepreciacion = tasaDepreciacion;
        this.kmRecorridos = kmRecorridos;
    }
    
    public double obtenerDepreciacion(){
        montoDepreciacion =  tasaDepreciacion*kmRecorridos;
      return montoDepreciacion;
    }

    public String getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(String idViaje) {
        this.idViaje = idViaje;
    }

    public double getTasaDepreciacion() {
        return tasaDepreciacion;
    }

    public void setTasaDepreciacion(double tasaDepreciacion) {
        this.tasaDepreciacion = tasaDepreciacion;
    }

    public double getKmRecorridos() {
        return kmRecorridos;
    }

    public void setKmRecorridos(double kmRecorridos) {
        this.kmRecorridos = kmRecorridos;
    }

    public double getMontoDepreciacion() {
        return montoDepreciacion;
    }

    public void setMontoDepreciacion(double montoDepreciacion) {
        this.montoDepreciacion = montoDepreciacion;
    }
    
    
   
    
    
    
    
    
    
}
