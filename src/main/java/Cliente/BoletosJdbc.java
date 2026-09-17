/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

import AdministradorSucursal.ViajeJdbc;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author mario
 */
public class BoletosJdbc {
    
    
    private Connection conn;

    public BoletosJdbc(Connection conn) {
        this.conn = conn;
    }
    
    public boolean asientoOcupado(String idViaje, int numeroAsiento){
        String sql = "SELECT * FROM boletos WHERE id_viaje = ? AND numero_asiento = ?";
        try {
            PreparedStatement querysPreparedStatement = conn.prepareStatement(sql);
            querysPreparedStatement.setString(1,idViaje);
            querysPreparedStatement.setInt(2, numeroAsiento);
            ResultSet resultado = querysPreparedStatement.executeQuery();
            return resultado.next();
        } catch (SQLException ex) {
            
            return false;
        }
        
        
        
    }
    
    
    public boolean comprarBoleto(Boleto boleto){
        String sql = "INSERT INTO boletos(id_usuario,id_viaje,fecha_pago,numero_asiento,id_boleto) values(?,?,?,?,?)";
        try {
            PreparedStatement insertStatement = conn.prepareStatement(sql);
            insertStatement.setString(1, boleto.getIdUsuario());
            insertStatement.setString(2,boleto.getIdViaje());
            insertStatement.setDate(3, boleto.getFechaDePago());
            insertStatement.setInt(4, boleto.getNumeroAsiento());
            insertStatement.setString(5, generarIdAleatorio());
            int resultado = insertStatement.executeUpdate();
            if(resultado==0){
                return false;
            }
            return true;
        } catch (SQLException ex) {
             return false;
        }
        
        
        
    }
    public ArrayList<Boleto> obtenerBoletos(String estado, String idCliente){
        String sql = "SELECT * FROM boletos WHERE id_usuario = ?";
        ViajeJdbc viajeDb = new ViajeJdbc(conn);
        ArrayList<Boleto> boletos = new ArrayList<>();
        try {
            PreparedStatement queryStatement = conn.prepareStatement(sql);
            queryStatement.setString(1, idCliente);
            ResultSet resultado = queryStatement.executeQuery();
            while(resultado.next()){
                String idViaje = resultado.getString("id_viaje");
                if(viajeDb.obtenerEstadoViaje(idViaje, estado)){
                   Date fechaPago = resultado.getDate("fecha_pago");
                   int asiento = resultado.getInt("numero_asiento");
                   String idBoleto   = resultado.getString("id_boleto");
                   Boleto boleto = new Boleto(idCliente, idViaje, fechaPago, asiento, idBoleto);
                   boletos.add(boleto);
                    
                
                }
            }
            return boletos;
            
            
        } catch (SQLException ex) {
                return boletos;
           
        }
        
        
        
    }
    
    
    public String generarIdAleatorio() {
        String sql = "SELECT * FROM boletos where id_boleto = ?";
        boolean codigoNoRepetido = false;
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();

        String codigo = "";
        while (codigoNoRepetido == false) {
            codigo = "";
            for (int i = 0; i < 10; i++) {
                codigo = codigo + caracteres.charAt(random.nextInt(caracteres.length()));
            }
            try {
                PreparedStatement queryStatement = conn.prepareStatement(sql);
                queryStatement.setString(1, codigo);
                ResultSet resultado = queryStatement.executeQuery();
                if (resultado.next()) {
                    codigoNoRepetido = false;
                } else {
                    codigoNoRepetido = true;
                }
            } catch (SQLException ex) {

            }

        }

        return codigo;
    }
    
    
    

       public String sqlExcepcion(SQLException excepcion) {
        String error;

        switch (excepcion.getErrorCode()) {
           
            case 1452:
                error = "Un elemento seleccionado no existe o no es válido";
                break;

            case 1062:
                error = "El id de boleto ingresado ya se encuentra registrado.";
                break;

            case 1292:
                error = "Formato no válido.";
                break;

            case 1406:
                error = "Dato ingresado muy largo.";
                break;

            default:
                error = "Ocurrió un inconveniente al procesar los datos.";
                break;
        }

        return error;
    }
    
}
