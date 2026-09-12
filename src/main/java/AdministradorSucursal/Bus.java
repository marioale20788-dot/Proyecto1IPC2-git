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
public class Bus {
    private String numeroPlaca;
    private String modelo;
    private String marca;
    private String foto;
    private int fechaDeFabricacion;
    private Double kilometrajeActual;
    private int capacidadPasajeros;
    private boolean estado;
    private String idSucursalAsociada;
  

    public Bus(String numeroPlaca, String modelo, String marca, String foto, int fechaDeFabricacion, Double kilometrajeActual, int capacidadPasajeros, boolean estado, String idSucursalAsociada) {
        this.numeroPlaca = numeroPlaca;
        this.modelo = modelo;
        this.marca = marca;
        this.foto = foto;
        this.fechaDeFabricacion = fechaDeFabricacion;
        this.kilometrajeActual = kilometrajeActual;
        this.capacidadPasajeros = capacidadPasajeros;
        this.estado = estado;
        this.idSucursalAsociada = idSucursalAsociada;
    }

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getFechaDeFabricacion() {
        return fechaDeFabricacion;
    }

    public void setFechaDeFabricacion(int fechaDeFabricacion) {
        this.fechaDeFabricacion = fechaDeFabricacion;
    }

    public Double getKilometrajeActual() {
        return kilometrajeActual;
    }

    public void setKilometrajeActual(Double kilometrajeActual) {
        this.kilometrajeActual = kilometrajeActual;
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public void setCapacidadPasajeros(int capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public boolean estado() {
        return estado;
    }
    public String getEstado(){
        if(estado==true){
            return "Activo";
        }else{
            return "Inactivo";
        }
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getIdSucursalAsociada() {
        return idSucursalAsociada;
    }

    public void setIdSucursalAsociada(String idSucursalAsociada) {
        this.idSucursalAsociada = idSucursalAsociada;
    }
    
    
    
    
}
