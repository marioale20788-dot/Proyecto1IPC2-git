/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

/**
 *
 * @author mario
 */
public class Asiento {
    
    int numero;
    boolean ocupado;//ocupado, libre

    public Asiento(int numero, boolean estado) {
        this.numero = numero;
        this.ocupado = estado;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean getOcupado() {
        return ocupado;
    }

    public void setEstado(boolean estado) {
        this.ocupado = estado;
    }
    
    
}
