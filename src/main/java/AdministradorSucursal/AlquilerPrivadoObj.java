/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdministradorSucursal;

import java.sql.Date;

/**
 *
 * @author mario
 */
public class AlquilerPrivadoObj {
        
    private String idAlquilerPrivado;
    private String idCliente;
    private boolean estado;
    private Date fechaSalida;
    private Date fechaRetorno;
    private String origen;
    private String destino;
    private double precioEstimado;
    private double precioFinal;
    private String idSucursal;
    private int pasajeros;
    private double distancia;
    private String estadoDeViaje;

    public AlquilerPrivadoObj(String idAlquilerPrivado, String idCliente, boolean estado, Date fechaSalida, Date fechaRetorno, String origen, String destino, double precioEstimado, double precioFinal, String idSucursal, int pasajeros, double distancia, String estadoDeViaje) {
        this.idAlquilerPrivado = idAlquilerPrivado;
        this.idCliente = idCliente;
        this.estado = estado;
        this.fechaSalida = fechaSalida;
        this.fechaRetorno = fechaRetorno;
        this.origen = origen;
        this.destino = destino;
        this.precioEstimado = precioEstimado;
        this.precioFinal = precioFinal;
        this.idSucursal = idSucursal;
        this.pasajeros = pasajeros;
        this.distancia = distancia;
        this.estadoDeViaje = estadoDeViaje;
    }

    public String getEstadoDeViaje() {
        return estadoDeViaje;
    }

    public void setEstadoDeViaje(String estadoDeViaje) {
        this.estadoDeViaje = estadoDeViaje;
    }

 
    
    

    public String getIdAlquilerPrivado() {
        return idAlquilerPrivado;
    }

    public void setIdAlquilerPrivado(String idAlquilerPrivado) {
        this.idAlquilerPrivado = idAlquilerPrivado;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getFechaRetorno() {
        return fechaRetorno;
    }

    public void setFechaRetorno(Date fechaRetorno) {
        this.fechaRetorno = fechaRetorno;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getPrecioEstimado() {
        return precioEstimado;
    }

    public void setPrecioEstimado(double precioEstimado) {
        this.precioEstimado = precioEstimado;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }

    public String getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }

    public int getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(int pasajeros) {
        this.pasajeros = pasajeros;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
    
    
}


