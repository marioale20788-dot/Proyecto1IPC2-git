/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

import java.sql.Date;

/**
 *
 * @author mario
 */
public class Boleto {
    private String idUsuario;
    private String idViaje;
    private Date fechaDePago;
    private int numeroAsiento;
    private String idBoleto;

    public Boleto(String idUsuario, String idViaje, Date fechaDePago, int numeroAsiento, String idBoleto) {
        this.idUsuario = idUsuario;
        this.idViaje = idViaje;
        this.fechaDePago = fechaDePago;
        this.numeroAsiento = numeroAsiento;
        this.idBoleto = idBoleto;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(String idViaje) {
        this.idViaje = idViaje;
    }

    public Date getFechaDePago() {
        return fechaDePago;
    }

    public void setFechaDePago(Date fechaDePago) {
        this.fechaDePago = fechaDePago;
    }

    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    public void setNumeroAsiento(int numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    public String getIdBoleto() {
        return idBoleto;
    }

    public void setIdBoleto(String idBoleto) {
        this.idBoleto = idBoleto;
    }
    
    
}
