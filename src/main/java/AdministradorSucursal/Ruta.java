/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdministradorSucursal;

/**
 *
 * @author mario
 */
public class Ruta {
    
    private String nombreRutal;
    private String idSucursalOrigen;
    private String idSucursalDestino;
    private Double distanciaEnKm;
    private Sucursal sucursalOrigen;
    private Sucursal sucursalDestino;

    public Ruta(String nombreRutal, String idSucursalOrigen, String idSucursalDestino, Double distanciaEnKm) {
        this.nombreRutal = nombreRutal;
        this.idSucursalOrigen = idSucursalOrigen;
        this.idSucursalDestino = idSucursalDestino;
        this.distanciaEnKm = distanciaEnKm;
    }
    

    public String getNombreRutal() {
        return nombreRutal;
    }

    public Sucursal getSucursalOrigen() {
        return sucursalOrigen;
    }

    public void setSucursalOrigen(Sucursal sucursalOrigen) {
        this.sucursalOrigen = sucursalOrigen;
    }

    public Sucursal getSucursalDestino() {
        return sucursalDestino;
    }

    public void setSucursalDestino(Sucursal sucursalDestino) {
        this.sucursalDestino = sucursalDestino;
    }

    public void setNombreRutal(String nombreRutal) {
        this.nombreRutal = nombreRutal;
    }

    public String getIdSucursalOrigen() {
        return idSucursalOrigen;
    }

    public void setIdSucursalOrigen(String idSucursalOrigen) {
        this.idSucursalOrigen = idSucursalOrigen;
    }

    public String getIdSucursalDestino() {
        return idSucursalDestino;
    }

    public void setIdSucursalDestino(String idSucursalDestino) {
        this.idSucursalDestino = idSucursalDestino;
    }

    public Double getDistanciaEnKm() {
        return distanciaEnKm;
    }

    public void setDistanciaEnKm(Double distanciaEnKm) {
        this.distanciaEnKm = distanciaEnKm;
    }
    
    
            
}
