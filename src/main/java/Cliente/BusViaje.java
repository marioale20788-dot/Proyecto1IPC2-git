/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

import AdministradorSucursal.Bus;
import AdministradorSucursal.Conector;
import java.util.ArrayList;

/**
 *
 * @author mario
 */
public class BusViaje {

    private Asiento[][] matrizAsientos;
    private Bus bus;

    public BusViaje(Bus bus) {
        this.bus = bus;
    }

    public void crearMatriz(String idViaje) {
        int capacidadBus = bus.getCapacidadPasajeros();
        int columas = 4;
        int filas;

        filas = (capacidadBus + 3) / columas;
        matrizAsientos = new Asiento[filas][columas];
        Asiento[] asientosBus = devolverAsientos(capacidadBus, idViaje);
        int contadorAsiento = 0;
        for (int f = 0; f < matrizAsientos.length; f++) {
            for (int c = 0; c < matrizAsientos[f].length; c++) {
               if (contadorAsiento < capacidadBus) {
                    this.matrizAsientos[f][c] = asientosBus[contadorAsiento];
                    contadorAsiento++;
                } else {
                    this.matrizAsientos[f][c] = null;
                }

            }
        }

    }

    public Asiento[] devolverAsientos(int capacidadPasajeros, String idViaje) {
        Asiento[] asientos = new Asiento[capacidadPasajeros];
        BoletosJdbc boletoDb = new BoletosJdbc(Conector.getInstance().getConnection());
        for (int i = 0; i < capacidadPasajeros; i++) {
            boolean ocupado = boletoDb.asientoOcupado(idViaje, i + 1);
            Asiento asiento = new Asiento(i + 1, ocupado);
            asientos[i] = asiento;

        }
        return asientos;

    }
    
    
    public Asiento[][] getMatrizAsientos() {
        return matrizAsientos;
    }

    public void setAsientos(Asiento[][] asientos) {
        this.matrizAsientos = asientos;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }


}
