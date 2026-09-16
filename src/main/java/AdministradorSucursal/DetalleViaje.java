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
public class DetalleViaje {

    private String idViaje;
    private double kilometrajeFinal;
    private double kilometrajeInicialBus;
    private String idChofer;
    private String idBus;
    private LocalDateTime fechaSalida;
    private LocalDateTime fechaLlegada;
    private double gastoTotalCombustible;

    public DetalleViaje(String idViaje, double kilometrajeFinal, double kilometrajeInicialBus, String idChofer, String idBus, LocalDateTime fechaSalida, LocalDateTime fechaLlegada, double gastoTotalCombustible) {
        this.idViaje = idViaje;
        this.kilometrajeFinal = kilometrajeFinal;
        this.kilometrajeInicialBus = kilometrajeInicialBus;
        this.idChofer = idChofer;
        this.idBus = idBus;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.gastoTotalCombustible = gastoTotalCombustible;
    }

    
    
    
    public String getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(String idViaje) {
        this.idViaje = idViaje;
    }

    public double getKilometrajeFinal() {
        return kilometrajeFinal;
    }

    public void setKilometrajeFinal(double kilometrajeFinal) {
        this.kilometrajeFinal = kilometrajeFinal;
    }

    public double getKilometrajeInicialBus() {
        return kilometrajeInicialBus;
    }

    public void setKilometrajeInicialBus(double kilometrajeInicialBus) {
        this.kilometrajeInicialBus = kilometrajeInicialBus;
    }

    public String getIdChofer() {
        return idChofer;
    }

    public void setIdChofer(String idChofer) {
        this.idChofer = idChofer;
    }

    public String getIdBus() {
        return idBus;
    }

    public void setIdBus(String idBus) {
        this.idBus = idBus;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public LocalDateTime getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDateTime fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public double getGastoTotalCombustible() {
        return gastoTotalCombustible;
    }

    public void setGastoTotalCombustible(double gastoTotalCombustible) {
        this.gastoTotalCombustible = gastoTotalCombustible;
    }
    
    
    
}
