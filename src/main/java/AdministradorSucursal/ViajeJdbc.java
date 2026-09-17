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
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author mario
 */
public class ViajeJdbc {

    private Connection conn;

    public ViajeJdbc(Connection conn) {
        this.conn = conn;
    }

    public String crearViaje(ViajeObj viaje, String idSucursalOrigen) {
        String sql = "INSERT INTO viaje (id_viaje, tipo, id_bus, fecha_salida_programada, fecha_llegada_estimada, precio_boleto, id_chofer, id_ruta, id_viaje_alquiler, id_sucursal) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        try {
            PreparedStatement inserStatement = conn.prepareStatement(sql);
            inserStatement.setString(1, viaje.getIdViaje());
            inserStatement.setString(2, viaje.getTipo());
            inserStatement.setString(3, viaje.getIdBus());
            inserStatement.setTimestamp(4, Timestamp.valueOf(viaje.getSalida()));
            inserStatement.setTimestamp(5, Timestamp.valueOf(viaje.getLlegada()));
            inserStatement.setDouble(6, viaje.getPrecioBoleto());
            inserStatement.setString(7, viaje.getIdChofer());
            inserStatement.setString(8, viaje.getIdRuta());
            inserStatement.setString(9, viaje.getIdViajeAlquiler());
            inserStatement.setString(10, idSucursalOrigen);
            int resultado = inserStatement.executeUpdate();
            if (resultado == 0) {
                return " NO SE PUDO REALIZAR EL CAMBIO";
            } else {
                return " 1 viaje a sido creado";
            }

        } catch (SQLException ex) {
            return sqlExcepcion(ex);
        }

    }

    public ArrayList<ViajeObj> obtenerTodosViajesRegulares() {
        String sql = "SELECT * FROM viaje WHERE estado = 'ESPERA' AND tipo = 'REGULAR'";
        ArrayList<ViajeObj> viajes = new ArrayList<>();
        try {
            PreparedStatement queryStatement = conn.prepareStatement(sql);
            ResultSet resultado = queryStatement.executeQuery();
            while (resultado.next()) {
                ViajeObj viaje = viajeCiclo(resultado);
                viajes.add(viaje);

            }
            return viajes;

        } catch (SQLException ex) {
            return viajes;
        }

    }

    public ViajeObj viajeCiclo(ResultSet resultado) throws SQLException {
        String viajeId = resultado.getString("id_viaje");
        String tipo = resultado.getString("tipo");
        String idBus = resultado.getString("id_bus");
        String estado = resultado.getString("estado");
        Timestamp salidat = resultado.getTimestamp("fecha_salida_programada");
        LocalDateTime salida = salidat.toLocalDateTime();
        Timestamp llegadat = resultado.getTimestamp("fecha_llegada_estimada");
        LocalDateTime llegada = llegadat.toLocalDateTime();
        double precio = resultado.getDouble("precio_boleto");
        String idChofer = resultado.getString("id_chofer");
        String idRuta = resultado.getString("id_ruta");
        String idViajeAlquiler = resultado.getString("id_viaje_alquiler");

        ViajeObj viaje = new ViajeObj(viajeId, tipo, idBus, estado, salida, llegada, precio, idChofer, idRuta, idViajeAlquiler);
        return viaje;

    }

    public int boletosVendidos(String idViaje) {
        String sql = "SELECT * FROM boletos WHERE id_viaje = ?";
        int vendidos = 0;
        try {
            PreparedStatement qualyStatement = conn.prepareStatement(sql);
            qualyStatement.setString(1, idViaje);
            ResultSet resultado = qualyStatement.executeQuery();
            while (resultado.next()) {
                vendidos++;

            }
            return vendidos;
        } catch (SQLException ex) {
            return vendidos;
        }

    }

    public String eliminarViaje(String idSucursalOrigen, String idViaje) {
        String sql = "DELETE FROM viaje where id_viaje = ? AND id_sucursal = ?";
        try {
            PreparedStatement updateStatement = conn.prepareStatement(sql);
            updateStatement.setString(1, idViaje);
            updateStatement.setString(2, idSucursalOrigen);
            int resultado = updateStatement.executeUpdate();
            if (resultado == 0) {
                return " NO SE PUDO ELIMINAR EL VIAJE";
            } else {
                return " 1 VIAJE A SIDO ELIMINADO";
            }
        } catch (SQLException ex) {
            return sqlExcepcion(ex);
        }

    }

    public boolean updateStadoViaje(String estado, String idViaje) {
        String update = "UPDATE viaje SET estado = ? WHERE id_viaje = ?";
        try {
            PreparedStatement updateStatement = conn.prepareStatement(update);
            updateStatement.setString(1, estado);
            updateStatement.setString(2, idViaje);
            int resultado = updateStatement.executeUpdate();
            if (resultado == 0) {
                return false;
            }
            return true;
        } catch (SQLException ex) {
            return false;
        }

    }

    public ViajeObj obtenerViaje(String idViaje, String idSucursal) {
        String sql = "SELECT * FROM viaje WHERE id_viaje = ? AND id_sucursal = ?";

        ViajeObj viaje = null;
        try {
            PreparedStatement queryStatement = conn.prepareStatement(sql);
            queryStatement.setString(1, idViaje);
            queryStatement.setString(2, idSucursal);
            ResultSet resultado = queryStatement.executeQuery();
            while (resultado.next()) {
                viaje = viajeCiclo(resultado);
                return viaje;

            }
            return viaje;
        } catch (SQLException ex) {
            return viaje;
        }

    }

    public boolean obtenerEstadoViaje(String idViaje, String estado) {
        String sql = "SELECT * FROM viaje WHERE id_viaje = ? AND estado = ?";
        try {
            PreparedStatement queryStatement = conn.prepareStatement(sql);
            queryStatement.setString(1, idViaje);
            queryStatement.setString(2, estado);
            ResultSet resultado = queryStatement.executeQuery();
            return resultado.next();
        } catch (SQLException ex) {
            return false;
        }

    }

    public ViajeObj obtenerViajePorId(String idViaje) {
        String sql = "SELECT * FROM viaje WHERE id_viaje = ?";

        ViajeObj viaje = null;
        try {
            PreparedStatement queryStatement = conn.prepareStatement(sql);
            queryStatement.setString(1, idViaje);
            ResultSet resultado = queryStatement.executeQuery();
            while (resultado.next()) {
                viaje = viajeCiclo(resultado);
                return viaje;
            }
            return viaje;
        } catch (SQLException ex) {
            return viaje;
        }

    }

    public ArrayList<ViajeObj> obtenerViajes(String[] estados, String[] tipoViaje, String idViaje, String idSucursal) {
        ArrayList<ViajeObj> viajes = new ArrayList<>();
        String sql = "SELECT * FROM viaje WHERE id_sucursal = ?";
        DetalleViajeJdbc detalleBaseDeDatos = new DetalleViajeJdbc(Conector.getInstance().getConnection());
        boolean masEstados = false;
        boolean masTipos = false;
        if (estados != null && estados.length > 0) {
            for (String estado : estados) {
                if (masEstados == true) {
                    sql = sql + " OR estado = ?";

                } else {
                    sql = sql + " AND (estado = ?";
                    masEstados = true;

                }

            }
            sql = sql + ")";

        }

        if (tipoViaje != null && tipoViaje.length > 0) {
            for (String viaje : tipoViaje) {

                if (masTipos == true) {
                    sql = sql + " OR tipo = ?";
                } else {
                    sql = sql + " AND (tipo = ?";
                    masTipos = true;
                }

            }
            sql = sql + ")";
        }

        if (idViaje != null && !idViaje.isEmpty()) {
            sql = sql + " AND id_viaje = ?";
        }

        try {
            PreparedStatement queryStatement = conn.prepareStatement(sql);
            queryStatement.setString(1, idSucursal);
            int contador = 1;

            if (estados != null && estados.length > 0) {
                for (String estado : estados) {
                    contador++;
                    queryStatement.setString(contador, estado);
                }
            }

            if (tipoViaje != null && tipoViaje.length > 0) {
                for (String viaje : tipoViaje) {
                    contador++;
                    queryStatement.setString(contador, viaje);
                }
            }

            if (idViaje != null && !idViaje.isEmpty()) {
                contador++;
                queryStatement.setString(contador, idViaje);
            }

            ResultSet resultado = queryStatement.executeQuery();
            while (resultado.next()) {
                String viajeId = resultado.getString("id_viaje");
                String tipo = resultado.getString("tipo");
                String estado = resultado.getString("estado");

                ViajeObj viaje = viajeCiclo(resultado);

                if (tipo.equalsIgnoreCase("REGULAR")) {
                    viaje.setCantidadBoletosVendidos(boletosVendidos(viajeId));
                }
                if (estado.equalsIgnoreCase("FINALIZADO")) {
                    DetalleViaje detalle = detalleBaseDeDatos.obtenerDetalleViaje(viajeId);
                    viaje.setDetalle(detalle);

                }

                viajes.add(viaje);
            }

            return viajes;

        } catch (SQLException ex) {
            return viajes;
        }
    }

    public String editarViaje(String idViaje, String datoACambiar, Object nuevoValor) {
        String sql = "UPDATE viaje SET " + datoACambiar + " = ? WHERE id_viaje = ?";
        try {
            PreparedStatement updateStatement = conn.prepareStatement(sql);
            if (datoACambiar.equalsIgnoreCase("precio_boleto")) {
                updateStatement.setDouble(1, (double) nuevoValor);
            } else if (datoACambiar.equalsIgnoreCase("fecha_salida_programada") || datoACambiar.equalsIgnoreCase("fecha_llegada_estimada")) {
                Timestamp fecha = Timestamp.valueOf((LocalDateTime) nuevoValor);
                updateStatement.setTimestamp(1, fecha);
            } else {
                updateStatement.setString(1, (String) nuevoValor);
            }
            updateStatement.setString(2, idViaje);

            int resultado = updateStatement.executeUpdate();

            return resultado + " cambio a sido realizado";
        } catch (SQLException e) {
            return sqlExcepcion(e);

        }

    }

    public String generarIdAleatorio() {
        String sql = "SELECT * FROM viaje where id_viaje = ?";
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
            case 1451:
                error = "No se puede eliminar el viaje porque tiene egistros asociados.";
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
