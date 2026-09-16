/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdministradorSucursal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author mario
 */
public class DetalleViajeJdbc {

    private Connection conn;

    public DetalleViajeJdbc(Connection conn) {
        this.conn = conn;
    }

    public String crearDetalleViaje(DetalleViaje detalle) {
        String sql = "INSERT INTO detalle_viaje (id_viaje, id_bus, id_chofer, kilometraje_inicial_bus, fecha_salida) VALUES (?, ?, ?, ?, ?)";
        ViajeJdbc updateViaje = new ViajeJdbc(conn);
        try {
            PreparedStatement insertStatement = conn.prepareStatement(sql);
            insertStatement.setString(1, detalle.getIdViaje());
            insertStatement.setString(2, detalle.getIdBus());
            insertStatement.setString(3, detalle.getIdChofer());
            insertStatement.setDouble(4, detalle.getKilometrajeInicialBus());
            insertStatement.setTimestamp(5, Timestamp.valueOf(detalle.getFechaSalida()));
            int resultado = insertStatement.executeUpdate();
            if (resultado == 0) {
                return " NO SE PUDO INICIAR EL VIAJE";

            } else {
                if (updateViaje.updateStadoViaje("INICIADO", detalle.getIdViaje())) {
                    return detalle.getIdViaje() + " A SIDO INICIADO CORRECTAMENTE";
                } else {
                    return " NO SE PUDO INICIAR EL VIAJE";
                }

            }

        } catch (SQLException ex) {
            return sqlExcepcion(ex) + " * - - NO SE PUDO INICIAR EL VIAJE";
        }

    }

    public boolean terminarDetalleViaje(double gastoCombustible, double kmFinal, LocalDateTime llegada, String idViaje) {

        String sql = "UPDATE detalle_viaje SET kilometraje_final = ?, fecha_llegada = ?, gasto_total_combustible = ? WHERE id_viaje = ?";

        try {
            PreparedStatement updateStatement = conn.prepareStatement(sql);
            updateStatement.setDouble(1, kmFinal);
            updateStatement.setTimestamp(2, Timestamp.valueOf(llegada));
            updateStatement.setDouble(3, gastoCombustible);
            updateStatement.setString(4, idViaje);
            int resultado = updateStatement.executeUpdate();
            if (resultado == 0) {
                return false;
            } else {
                return true;

            }

        } catch (SQLException ex) {
            return false;
        }

    }

    public DetalleViaje obtenerDetalleViaje(String idViaje) {
        String sql = "SELECT * FROM detalle_viaje WHERE id_viaje = ?";
        DetalleViaje detalleViaje = null;
        try {
            PreparedStatement queryStatement = conn.prepareStatement(sql);
            queryStatement.setString(1, idViaje);
            ResultSet resultado = queryStatement.executeQuery();
            while (resultado.next()) {
                double kmFinal = resultado.getDouble("kilometraje_final");
                double kmInicial = resultado.getDouble("kilometraje_inicial_bus");
                String idChofer = resultado.getString("id_chofer");
                String idBus = resultado.getString("id_bus");
                Timestamp salidaTime = resultado.getTimestamp("fecha_salida");
                LocalDateTime salida = salidaTime.toLocalDateTime();
                Timestamp llegadaTime = resultado.getTimestamp("fecha_llegada");
                LocalDateTime llegada = llegadaTime.toLocalDateTime();
                double gastoCombustible = resultado.getDouble("gasto_total_combustible");
                detalleViaje = new DetalleViaje(idViaje, kmFinal, kmInicial, idChofer, idBus, salida, llegada, gastoCombustible);
                return detalleViaje;

            }

        } catch (SQLException ex) {
            return detalleViaje;
        }
        return detalleViaje;
    }

    public String sqlExcepcion(SQLException excepcion) {
        String error;

        switch (excepcion.getErrorCode()) {
            case 1451:
                error = "No se puede eliminar el detalle viaje porque tiene egistros asociados.";
                break;

            case 1452:
                error = "Un elemento seleccionado no existe o no es válido";
                break;

            case 1062:
                error = "El id de viaje ingresado ya se encuentra registrado.";
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
