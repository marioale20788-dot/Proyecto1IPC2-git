/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdministradorSucursal;

import java.time.LocalDateTime;

/**
 *
 * @author mario
 */
public class ViajeObj {
       private String idViaje;
    private String tipo;
    private String idBus;
    private String estado;
    private LocalDateTime  salida;
    private LocalDateTime llegada;
    private Double  precioBoleto;
    private String idChofer;
    private String idRuta;
    private String idViajeAlquiler;
    private int cantidadBoletosVendidos;
    private DetalleViaje detalle;
    
    private Ruta ruta;

    public ViajeObj(String idViaje, String tipo, String idBus, String estado, LocalDateTime salida, LocalDateTime llegada, double precioBoleto, String idChofer, String idRuta, String idViajeAlquiler) {
        this.idViaje = idViaje;
        this.tipo = tipo;
        this.idBus = idBus;
        this.estado = estado;
        this.salida = salida;
        this.llegada = llegada;
        this.precioBoleto = precioBoleto;
        this.idChofer = idChofer;
        this.idRuta = idRuta;
        this.idViajeAlquiler = idViajeAlquiler;
    }

    public DetalleViaje getDetalle() {
        return detalle;
    }

    public void setDetalle(DetalleViaje detalle) {
        this.detalle = detalle;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }
    
    
    

    public int getCantidadBoletosVendidos() {
        return cantidadBoletosVendidos;
    }

    public void setCantidadBoletosVendidos(int cantidadBoletosVendidos) {
        this.cantidadBoletosVendidos = cantidadBoletosVendidos;
    }

    
    
    
    public String getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(String idViaje) {
        this.idViaje = idViaje;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIdBus() {
        return idBus;
    }

    public void setIdBus(String idBus) {
        this.idBus = idBus;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getSalida() {
       return salida;

    }

    public void setSalida(LocalDateTime salida) {
        this.salida = salida;
    }

    public LocalDateTime getLlegada() {
        return llegada;
    }

    public void setLlegada(LocalDateTime llegada) {
        this.llegada = llegada;
    }

    public double getPrecioBoleto() {
        return precioBoleto;
    }

    public void setPrecioBoleto(double precioBoleto) {
        this.precioBoleto = precioBoleto;
    }

    public String getIdChofer() {
        return idChofer;
    }

    public void setIdChofer(String idChofer) {
        this.idChofer = idChofer;
    }

    public String getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(String idRuta) {
        this.idRuta = idRuta;
    }

    public String getIdViajeAlquiler() {
        return idViajeAlquiler;
    }

    public void setIdViajeAlquiler(String idViajeAlquiler) {
        this.idViajeAlquiler = idViajeAlquiler;
    }
    
    
}
