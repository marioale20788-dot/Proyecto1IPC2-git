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
public class TallerObj {
    
    
    private String idBus;
    private Date fechaMantenimient;
    private double montoRepuestos;
    private double monotManoDeObra;
    private String recibo;

    public TallerObj(String idBus, Date fechaMantenimient, double montoRepuestos, double monotManoDeObra, String recibo) {
        this.idBus = idBus;
        this.fechaMantenimient = fechaMantenimient;
        this.montoRepuestos = montoRepuestos;
        this.monotManoDeObra = monotManoDeObra;
        this.recibo = recibo;
    }

    public String getRecibo() {
        return recibo;
    }

    public void setRecibo(String recibo) {
        this.recibo = recibo;
    }

 

    public String getIdBus() {
        return idBus;
    }

    public void setIdBus(String idBus) {
        this.idBus = idBus;
    }

    public Date getFechaMantenimient() {
        return fechaMantenimient;
    }

    public void setFechaMantenimient(Date fechaMantenimient) {
        this.fechaMantenimient = fechaMantenimient;
    }

    public double getMontoRepuestos() {
        return montoRepuestos;
    }

    public void setMontoRepuestos(double montoRepuestos) {
        this.montoRepuestos = montoRepuestos;
    }

    public double getMonotManoDeObra() {
        return monotManoDeObra;
    }

    public void setMonotManoDeObra(double monotManoDeObra) {
        this.monotManoDeObra = monotManoDeObra;
    }
    
    

    
}
