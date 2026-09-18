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
public class Chofer {
    private String nombreCompleto;
    private String numeroDeTelefono;
    private String numeroDeLicencia;
    private String foto;
    private String tipoLicencial;
    private boolean estadoOpertaivo;
    private double salarioBase;
    private String idSucursal;
    private Date fechaVencimiento;
    private int viajes;

    public Chofer(String nombreCompleto, String numeroDeTelefono, String numeroDeLicencia, String foto, String tipoLicencial, boolean estadoOpertaivo, double salarioBase, String idSucursal,Date fecha) {
        this.nombreCompleto = nombreCompleto;
        this.numeroDeTelefono = numeroDeTelefono;
        this.numeroDeLicencia = numeroDeLicencia;
        this.foto = foto;
        this.tipoLicencial = tipoLicencial;
        this.estadoOpertaivo = estadoOpertaivo;
        this.salarioBase = salarioBase;
        this.idSucursal = idSucursal;
        this.fechaVencimiento=fecha;
    }

    public int getViajes() {
        return viajes;
    }

    public void setViajes(int viajes) {
        this.viajes = viajes;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNumeroDeTelefono() {
        return numeroDeTelefono;
    }

    public void setNumeroDeTelefono(String numeroDeTelefono) {
        this.numeroDeTelefono = numeroDeTelefono;
    }

    public String getNumeroDeLicencia() {
        return numeroDeLicencia;
    }

    public void setNumeroDeLicencia(String numeroDeLicencia) {
        this.numeroDeLicencia = numeroDeLicencia;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTipoLicencia() {
        return tipoLicencial;
    }

    public void setTipoLicencial(String tipoLicencial) {
        this.tipoLicencial = tipoLicencial;
    }

      public boolean estado() {
        return estadoOpertaivo;
    }
    public String getEstado(){
        if(estadoOpertaivo==true){
            return "Activo";
        }else{
            return "Inactivo";
        }
    }

    public void setEstadoOpertaivo(boolean estadoOpertaivo) {
        this.estadoOpertaivo = estadoOpertaivo;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public String getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }
    
    
    
    
}
