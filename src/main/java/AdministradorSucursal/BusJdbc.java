/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdministradorSucursal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author mario
 */
public class BusJdbc {

    private Connection connection;
    private Bus bus;

    public BusJdbc(Connection conn, Bus bus) {
        this.connection = conn;
        this.bus = bus;
    }

    public String CrearNuevoBus() {
        String sql = "INSERT INTO buses(numero_placa,marca,modelo,foto,fabricacion,kilometraje_actual,capacidad_pasajeros,estado_operativo,id_sucursal)"
                + "values(?,?,?,?,?,?,?,?,?) ";
        try {
            PreparedStatement insertStatement = connection.prepareStatement(sql);
            insertStatement.setString(1, bus.getNumeroPlaca());
            insertStatement.setString(2, bus.getMarca());
            insertStatement.setString(3, bus.getModelo());
            insertStatement.setString(4, bus.getFoto());
            insertStatement.setInt(5, bus.getFechaDeFabricacion());
            insertStatement.setDouble(6, bus.getKilometrajeActual());
            insertStatement.setInt(7, bus.getCapacidadPasajeros());
            insertStatement.setBoolean(8, bus.estado());
            insertStatement.setString(9, bus.getIdSucursalAsociada());
            String correcto = String.valueOf(insertStatement.executeUpdate());
            return correcto + " bus creado correctamente";

        } catch (SQLException ex) {
            return sqlExcepcion(ex);

        }

    }

    public ArrayList<Bus> BuscarBuses(String idSucursal, String placa) {
        String sql = "SELECT * FROM buses WHERE id_sucursal = ?";
        if (placa != null) {
            sql = sql + " AND numero_placa = ?";
        }

        ArrayList<Bus> buses = new ArrayList<>();
        PreparedStatement qualyStatement;
        try {
            qualyStatement = connection.prepareStatement(sql);
            qualyStatement.setString(1, idSucursal);
            if (placa != null) {
                qualyStatement.setString(2, placa);
            }
            ResultSet busesBuscados = qualyStatement.executeQuery();
            while (busesBuscados.next()) {
                String numeroPlaca = busesBuscados.getString("numero_placa");
                String marca = busesBuscados.getString("marca");
                String modelo = busesBuscados.getString("modelo");
                String foto = busesBuscados.getString("foto");
                int fabricacion = busesBuscados.getInt("fabricacion");
                double kmActual = busesBuscados.getDouble("kilometraje_actual");
                int capacidadPasajeros = busesBuscados.getInt("capacidad_pasajeros");
                boolean estado = busesBuscados.getBoolean("estado_operativo");
                Bus bus = new Bus(numeroPlaca, modelo, marca, foto, fabricacion, kmActual, capacidadPasajeros, estado, idSucursal);
                buses.add(bus);

            }
            return buses;

        } catch (SQLException ex) {
            return buses;
        }

    }

    public boolean buscarBusEnViaje(String placa) {

        String sql = "SELECT * FROM viaje WHERE id_bus = ? AND (estado = 'ESPERA' OR estado = 'INICIADO')";
        try {
            PreparedStatement qualyStatement = connection.prepareStatement(sql);
            qualyStatement.setString(1, placa);
            ResultSet resultado = qualyStatement.executeQuery();
            return resultado.next();
        } catch (SQLException ex) {
            return true;
        }

    }
    
   

    public ArrayList<Bus> BuscarBusesDisponibles(String idSucursal) {
        String sql = "SELECT * FROM buses WHERE id_sucursal = ? AND estado_operativo = true";
        ArrayList<Bus> buses = new ArrayList<>();
        try {
            PreparedStatement qualyStatement = connection.prepareStatement(sql);
            qualyStatement.setString(1, idSucursal);
            ResultSet resultado = qualyStatement.executeQuery();
            while (resultado.next()) {
                String placa = resultado.getString("numero_placa");
                if (buscarBusEnViaje(placa) == false) {
                    String marca = resultado.getString("marca");
                    String modelo = resultado.getString("modelo");
                    String foto = resultado.getString("foto");
                    int fabricacion = resultado.getInt("fabricacion");
                    double kmActual = resultado.getDouble("kilometraje_actual");
                    int capacidadPasajeros = resultado.getInt("capacidad_pasajeros");
                    boolean estado = resultado.getBoolean("estado_operativo");
                    Bus bus = new Bus(placa, modelo, marca, foto, fabricacion, kmActual, capacidadPasajeros, estado, idSucursal);
                    buses.add(bus);

                }

            }
            return buses;

        } catch (SQLException ex) {
            System.getLogger(BusJdbc.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return buses;
    }

    public String buscarSucursalAsociada(String numeroPlaca) {
        try {
            String sql = "SELECT id_sucursal FROM buses WHERE numero_placa = ?";

            PreparedStatement queryStatement = connection.prepareStatement(sql);
            queryStatement.setString(1, numeroPlaca);
            ResultSet sucursal = queryStatement.executeQuery();
            while (sucursal.next()) {
                return sucursal.getString("id_sucursal");
            }
        } catch (SQLException ex) {

        }
        return null;
    }

    public String updateBus(String variableCambiar, Object nuevoDato, String placaBus) {
        String sql = "UPDATE buses SET " + variableCambiar + " = ? WHERE numero_placa = ?";
        try {

            PreparedStatement updateStatement = connection.prepareStatement(sql);
            if (variableCambiar.equalsIgnoreCase("estado_operativo")) {
                boolean estado = Boolean.parseBoolean(String.valueOf(nuevoDato));
                updateStatement.setBoolean(1, estado);
            } else if (variableCambiar.equalsIgnoreCase("fabricacion") || variableCambiar.equalsIgnoreCase("capacidad_pasajeros")) {
                updateStatement.setInt(1, (int) nuevoDato);

            } else if (variableCambiar.equalsIgnoreCase("kilometraje_actual")) {
                updateStatement.setDouble(1, (double) nuevoDato);
            } else {
                updateStatement.setString(1, (String) nuevoDato);
            }
            updateStatement.setString(2, placaBus);
            String correcto = String.valueOf(updateStatement.executeUpdate());
            return correcto + " elemento editado correctamente";

        } catch (SQLException ex) {
            return sqlExcepcion(ex);
        }

    }

    public boolean verficarBusEnViaje(String placaBus) {
        String sql = "SELECT * FROM viaje WHERE id_bus = ? AND (estado = 'ESPERA' OR estado = 'INICIADO')";
        try {
            PreparedStatement qualyStatement = connection.prepareStatement(sql);
            qualyStatement.setString(1, placaBus);
            ResultSet resultado = qualyStatement.executeQuery();
            while (resultado.next()) {
                return true;

            }

        } catch (SQLException ex) {
            return false;
        }
        return false;

    }

    public String sqlExcepcion(SQLException excepcion) {
        String error = excepcion.getMessage();

        switch (excepcion.getErrorCode()) {
            case 1452:
                error = "La sucursal seleccionada no existe o no es válida";
                break;

            case 1062:
                error = "El número de placa ingresado ya se encuentra registrado.";
                break;
            case 1292:
                error = "Formato no valido.";
                break;
            case 1406:
                error = "dato ingresado muy largo.";
                break;

            default:
                error = "Ocurrió un inconveniente al guardar los datos.";
                break;
        }
        return error;

    }

    public String buscarSucursalAsociada(String numeroPlaca) {
        try {
            String sql = "SELECT id_sucursal FROM buses WHERE numero_placa = ?";

            PreparedStatement queryStatement = connection.prepareStatement(sql);
            queryStatement.setString(1, numeroPlaca);
            ResultSet sucursal = queryStatement.executeQuery();
            while (sucursal.next()) {
                return sucursal.getString("id_sucursal");
            }
        } catch (SQLException ex) {

        }
        return null;
    }

    public String updateBus(String variableCambiar, Object nuevoDato, String placaBus) {
        String sql = "UPDATE buses SET " + variableCambiar + " = ? WHERE numero_placa = ?";
        try {

            PreparedStatement updateStatement = connection.prepareStatement(sql);
            if (variableCambiar.equalsIgnoreCase("estado_operativo")) {
                boolean estado = Boolean.parseBoolean(String.valueOf(nuevoDato));
                updateStatement.setBoolean(1, estado);
            } else if (variableCambiar.equalsIgnoreCase("fabricacion") || variableCambiar.equalsIgnoreCase("capacidad_pasajeros")) {
                updateStatement.setInt(1, (int) nuevoDato);

            } else if (variableCambiar.equalsIgnoreCase("kilometraje_actual")) {
                updateStatement.setDouble(1, (double) nuevoDato);
            } else {
                updateStatement.setString(1, (String) nuevoDato);
            }
            updateStatement.setString(2, placaBus);
            String correcto = String.valueOf(updateStatement.executeUpdate());
            return correcto + " elemento editado correctamente";

        } catch (SQLException ex) {
            return sqlExcepcion(ex);
        }

    }

    public boolean verficarBusEnViaje(String placaBus) {
        String sql = "SELECT * FROM viaje WHERE id_bus = ? AND (estado = 'ESPERA' OR estado = 'INICIADO')";
        try {
            PreparedStatement qualyStatement = connection.prepareStatement(sql);
            qualyStatement.setString(1, placaBus);
            ResultSet resultado = qualyStatement.executeQuery();
            while (resultado.next()) {
                return true;

            }

        } catch (SQLException ex) {
            return false;
        }
        return false;

    }

    public String sqlExcepcion(SQLException excepcion) {
        String error = excepcion.getMessage();

        switch (excepcion.getErrorCode()) {
            case 1452:
                error = "La sucursal seleccionada no existe o no es válida";
                break;

            case 1062:
                error = "El número de placa ingresado ya se encuentra registrado.";
                break;

            default:
                error = "Ocurrió un inconveniente al guardar los datos.";
                break;
        }
        return error;

    }

}
