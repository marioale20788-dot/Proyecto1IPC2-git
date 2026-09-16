/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdministradorSucursal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author mario
 */
public class AlquilerPrivadoJdbc {

    private Connection conn;

    public AlquilerPrivadoJdbc(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<AlquilerPrivadoObj> alquileresPrivados(String idSucursal, boolean estado) {
        String sql = "SELECT * FROM alquiler_privado WHERE estado = ? AND id_sucursal = ? AND estadoViaje = 'ESPERANDO'";
        ArrayList<AlquilerPrivadoObj> privados = new ArrayList<>();

        if (estado == false) {
            sql = sql + " AND precio_final IS NULL";
        }
        try {
            PreparedStatement queryStatement = conn.prepareStatement(sql);
            queryStatement.setBoolean(1, estado);
            queryStatement.setString(2, idSucursal);
            ResultSet resultado = queryStatement.executeQuery();
            while (resultado.next()) {
             
                AlquilerPrivadoObj alquiler = obtenerAlquiler(resultado, idSucursal);
                privados.add(alquiler);
            }

            return privados;

        } catch (SQLException ex) {
            return privados;
        }

    }
    
    
    public boolean confirmarViajeAlquiler(String idAlquiler){
        String sql = "UPDATE alquiler_privado SET estadoViaje = 'CREADO' WHERE id_alquiler = ?";
        try {
            PreparedStatement updateStatement = conn.prepareStatement(sql);
            updateStatement.setString(1,idAlquiler);
            int resultado = updateStatement.executeUpdate();
            if(resultado==0){
                return false;
            }
            return true;
        } catch (SQLException ex) {
           return false;
        }
        
    }
    
    public String caonfirmarPrecioFinal(String idAlquiler, double precioFinal,String idSucursal){
        String sql = "UPDATE alquiler_privado SET precio_final = ? WHERE id_alquiler = ? AND id_sucursal = ? ";
        try {
            PreparedStatement updateStatement = conn.prepareStatement(sql);
            updateStatement.setDouble(1, precioFinal);
            updateStatement.setString(2, idAlquiler);
            updateStatement.setString(3, idSucursal);
            int resultado = updateStatement.executeUpdate();
            return resultado+" precio a sido confirmado";
        } catch (SQLException ex) {
            return sqlExcepcion(ex);
        }
        
        
    }

    public AlquilerPrivadoObj obtenerAlquiler(ResultSet resultado, String idSucursal) throws SQLException {
        AlquilerPrivadoObj alquiler = null;

        String idAlquiler = resultado.getString("id_alquiler");
        String idCliente = resultado.getString("id_cliente");
        boolean estadoAlquiler = resultado.getBoolean("estado");
        Date salida = resultado.getDate("fecha_salida");
        Date llegada = resultado.getDate("fecha_retorno");
        String destino = resultado.getString("destino_coordenadas");
        String origen = resultado.getString("origen_coordenadas");
        double precioEstimado = resultado.getDouble("precio_estimado");
        double precioFinal = resultado.getDouble("precio_final");
        int pasajeros = resultado.getInt("numero_pasajeros");
        double distancia = resultado.getDouble("distancia_km");
        String estadoViaje = resultado.getString("estadoViaje");
        alquiler = new AlquilerPrivadoObj(idAlquiler, idCliente, estadoAlquiler, salida, llegada, origen, destino,
                precioEstimado, precioFinal, idSucursal, pasajeros, distancia, estadoViaje);
        return alquiler;

    }

    public AlquilerPrivadoObj buscarAlquilerPrivado(String idViajePrivado, String idSucursal) {
        String sql = "SELECT * FROM alquiler_privado WHERE id_alquiler = ? AND id_sucursal = ?";
        AlquilerPrivadoObj alquiler = null;
        try {
            PreparedStatement queryStatement = conn.prepareStatement(sql);
            queryStatement.setString(1, idViajePrivado);
            queryStatement.setString(2, idSucursal);
            ResultSet resultado = queryStatement.executeQuery();
            while (resultado.next()) {
               alquiler = obtenerAlquiler(resultado, idSucursal);
               return alquiler;

            }
            return alquiler;
        } catch (SQLException ex) {
          return alquiler;
        }

    }
     public String sqlExcepcion(SQLException excepcion) {
        String error;

        switch (excepcion.getErrorCode()) {
               case 1452:
                error = "Un elemento seleccionado no existe o no es válido";
                break;

            case 1062:
                error = "El id de alquiler viaje ingresado ya se encuentra registrado.";
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
