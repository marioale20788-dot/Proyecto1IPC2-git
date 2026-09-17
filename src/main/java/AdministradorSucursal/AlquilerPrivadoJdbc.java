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
import java.util.Random;

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

                AlquilerPrivadoObj alquiler = obtenerAlquiler(resultado);
                privados.add(alquiler);
            }

            return privados;

        } catch (SQLException ex) {
            return privados;
        }

    }

    public String eliminarAlquilerPrivado(String idAlquiler, String idUsuario){
      String sql = "DELETE FROM alquiler_privado WHERE id_alquiler = ? AND id_cliente = ?";
        try {
            PreparedStatement updateStatement = conn.prepareStatement(sql);
            updateStatement.setString(1, idAlquiler);
            updateStatement.setString(2, idUsuario);
           int i = updateStatement.executeUpdate();
           return i+" elementos eliminados";
            
        } catch (SQLException ex) {
           return sqlExcepcion(ex);
        }
      
    }
    
    public String crearAlquilerPrivado(AlquilerPrivadoObj alquiler) {
        String sql = "INSERT INTO alquiler_privado ("
                + "id_alquiler, id_cliente, estado, fecha_salida, fecha_retorno, "
                + "destino_coordenadas, origen_coordenadas, precio_estimado, "
                + "id_sucursal, numero_pasajeros, distancia_km, estadoViaje) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement insertStatement = conn.prepareStatement(sql);
            insertStatement.setString(1, alquiler.getIdAlquilerPrivado());
            insertStatement.setString(2, alquiler.getIdCliente());
            insertStatement.setInt(3, 0); 
            insertStatement.setDate(4, alquiler.getFechaSalida());
            insertStatement.setDate(5, alquiler.getFechaRetorno());
            insertStatement.setString(6, alquiler.getDestino());
            insertStatement.setString(7, alquiler.getOrigen());
            insertStatement.setDouble(8, alquiler.getPrecioEstimado());
            insertStatement.setString(9, alquiler.getIdSucursal());
            insertStatement.setInt(10, alquiler.getPasajeros());
            insertStatement.setDouble(11, alquiler.getDistancia());
            insertStatement.setString(12, "ESPERANDO");
            int resultado = insertStatement.executeUpdate();
            if(resultado==0){
                return " NO SE PUDO CREAR EL VIAJE";
            }
            return " VIAJE SOLICITADO CORRECTAMENTE";

        } catch (SQLException ex) {
            return sqlExcepcion(ex);
        }

    }
    public String confirmarAlquilerPrivado(boolean estado, String idAlquiler,String idCliente){
        String sql = "UPDATE alquiler_privado SET estado = ? WHERE id_alquiler = ? AND id_cliente = ?";
        try {
            PreparedStatement insertStatement = conn.prepareStatement(sql);
            insertStatement.setBoolean(1, estado);
            insertStatement.setString(2, idAlquiler);
            insertStatement.setString(3, idCliente);
            int i = insertStatement.executeUpdate();
            if(i==0){
                return "NO SE PUDO CONFIRMAR EL VIAJE";
            }
             return "VIAJE CONFIRMADO";
        } catch (SQLException ex) {
            return "NO SE PUDO CONFIRMAR EL VIAJE";
        }
        
        
    }

    public ArrayList<AlquilerPrivadoObj> alquileresPrivadosClientes(String idCliente, boolean estado) {
        String sql = "SELECT * FROM alquiler_privado WHERE estado = ? AND id_cliente = ? AND estadoViaje = 'ESPERANDO'";
        ArrayList<AlquilerPrivadoObj> privados = new ArrayList<>();

        try {
            PreparedStatement queryStatement = conn.prepareStatement(sql);
            queryStatement.setBoolean(1, estado);
            queryStatement.setString(2, idCliente);
            ResultSet resultado = queryStatement.executeQuery();
            while (resultado.next()) {

                AlquilerPrivadoObj alquiler = obtenerAlquiler(resultado);
                privados.add(alquiler);
            }

            return privados;

        } catch (SQLException ex) {
            return privados;
        }

    }

    public boolean confirmarViajeAlquiler(String idAlquiler, String idCliente) {
        String sql = "UPDATE alquiler_privado SET estadoViaje = 'CREADO' WHERE id_alquiler = ? AND id_cliente = ?";
        try {
            PreparedStatement updateStatement = conn.prepareStatement(sql);
            updateStatement.setString(1, idAlquiler);
              updateStatement.setString(2, idCliente);
            int resultado = updateStatement.executeUpdate();
            if (resultado == 0) {
                return false;
            }
            return true;
        } catch (SQLException ex) {
            return false;
        }

    }

    public String caonfirmarPrecioFinal(String idAlquiler, double precioFinal, String idSucursal) {
        String sql = "UPDATE alquiler_privado SET precio_final = ? WHERE id_alquiler = ? AND id_sucursal = ? ";
        try {
            PreparedStatement updateStatement = conn.prepareStatement(sql);
            updateStatement.setDouble(1, precioFinal);
            updateStatement.setString(2, idAlquiler);
            updateStatement.setString(3, idSucursal);
            int resultado = updateStatement.executeUpdate();
            return resultado + " precio a sido confirmado";
        } catch (SQLException ex) {
            return sqlExcepcion(ex);
        }

    }

    public AlquilerPrivadoObj obtenerAlquiler(ResultSet resultado) throws SQLException {
        AlquilerPrivadoObj alquiler = null;

        String idAlquiler = resultado.getString("id_alquiler");
        String idCliente = resultado.getString("id_cliente");
        boolean estadoAlquiler = resultado.getBoolean("estado");
        Date salida = resultado.getDate("fecha_salida");
        Date llegada = resultado.getDate("fecha_retorno");
        String destino = resultado.getString("destino_coordenadas");
        String origen = resultado.getString("origen_coordenadas");
        double precioEstimado = resultado.getDouble("precio_estimado");
        Double precioFinal = resultado.getDouble("precio_final");
        int pasajeros = resultado.getInt("numero_pasajeros");
        double distancia = resultado.getDouble("distancia_km");
        String estadoViaje = resultado.getString("estadoViaje");
        String idSucursal = resultado.getString("id_sucursal");
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
                alquiler = obtenerAlquiler(resultado);
                return alquiler;

            }
            return alquiler;
        } catch (SQLException ex) {
            return alquiler;
        }

    }

    public String generarIdAleatorio() {
        String sql = "SELECT * FROM alquiler_privado WHERE id_alquiler = ?";
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
