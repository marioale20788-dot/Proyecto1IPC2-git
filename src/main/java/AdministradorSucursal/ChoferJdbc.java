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
public class ChoferJdbc {

    private Connection conn;
    private Chofer chofer;

    public ChoferJdbc(Connection conn, Chofer chofer) {
        this.conn = conn;
        this.chofer = chofer;
    }

    public String crearChofer() {

        String sql = """
                    INSERT INTO chofer(numero_licencia,nombre_completo,foto,tipo_licencia,estado_operativo,numero_telefono,salario_base,id_sucursal,fecha_vencimiento_licencia)
                    VALUES(?,?,?,?,?,?,?,?,?)               
                    """;
        try {
            PreparedStatement insertStatement = conn.prepareStatement(sql);
            insertStatement.setString(1, chofer.getNumeroDeLicencia());
            insertStatement.setString(2, chofer.getNombreCompleto());
            insertStatement.setString(3, chofer.getFoto());
            insertStatement.setString(4, chofer.getTipoLicencia());
            insertStatement.setBoolean(5, chofer.estado());
            insertStatement.setString(6, chofer.getNumeroDeTelefono());
            insertStatement.setDouble(7, chofer.getSalarioBase());
            insertStatement.setString(8, chofer.getIdSucursal());
            insertStatement.setDate(9, chofer.getFechaVencimiento());
            String resultado = String.valueOf(insertStatement.executeUpdate());
            return resultado + " CHOFER HA SIDO AGREGADO";

        } catch (SQLException ex) {
            return sqlExcepcion(ex);

        }

    }

    public ArrayList<Chofer> buscarChoferes(String idChofer, String idSucursal) {
        String sql = "SELECT * FROM chofer WHERE id_sucursal = ?";
        if (idChofer != null) {
            sql = sql + " AND numero_licencia = ?";
        }
        ArrayList<Chofer> choferes = new ArrayList<>();
        PreparedStatement qualyStatement;
        try {
            qualyStatement = conn.prepareStatement(sql);
            qualyStatement.setString(1, idSucursal);
            if (idChofer != null) {
                qualyStatement.setString(2, idChofer);
            }
            ResultSet chofersBuscados = qualyStatement.executeQuery();
            while (chofersBuscados.next()) {
                String numeroLicencia = chofersBuscados.getString("numero_licencia");
                String nombreCompleto = chofersBuscados.getString("nombre_completo");
                String foto = chofersBuscados.getString("foto");
                String tipoLicencia = chofersBuscados.getString("tipo_licencia");
                String numeroTelefono = chofersBuscados.getString("numero_telefono");
                Double salarioBase = chofersBuscados.getDouble("salario_base");
                Date fechaVencimiento = chofersBuscados.getDate("fecha_vencimiento_licencia");
                boolean estadoOperativo = chofersBuscados.getBoolean("estado_operativo");
                Chofer chofer = new Chofer(nombreCompleto, numeroTelefono, numeroLicencia, foto, tipoLicencia, estadoOperativo, salarioBase, idSucursal, fechaVencimiento);
                choferes.add(chofer);

            }
            return choferes;

        } catch (SQLException ex) {
            return null;
        }

    }

    public boolean verificarViajes(String numeroLicencia) {
        String sql = "SELECT * FROM viaje WHERE id_chofer = ? AND (estado = 'ESPERA' OR estado = 'INICIADO')";
        try {
            PreparedStatement qualyStatement = conn.prepareStatement(sql);
            qualyStatement.setString(1, numeroLicencia);
            ResultSet resultado = qualyStatement.executeQuery();
            return resultado.next();

        } catch (SQLException ex) {
            return false;
        }

    }

    public ArrayList<Chofer> choferesDisponibles(String idSucursal) {
        String sql = "SELECT * FROM chofer WHERE id_sucursal = ? AND estado_operativo = true";
        ArrayList<Chofer> choferes = new ArrayList<>();
        try {
            PreparedStatement qualyStatement = conn.prepareStatement(sql);
            qualyStatement.setString(1, idSucursal);
            ResultSet resultado = qualyStatement.executeQuery();
            while (resultado.next()) {
                String numeroLicencia = resultado.getString("numero_licencia");
                if (verificarViajes(numeroLicencia) == false) {
                    String nombreCompleto = resultado.getString("nombre_completo");
                    String foto = resultado.getString("foto");
                    String tipoLicencia = resultado.getString("tipo_licencia");
                    String numeroTelefono = resultado.getString("numero_telefono");
                    Double salarioBase = resultado.getDouble("salario_base");
                    Date fechaVencimiento = resultado.getDate("fecha_vencimiento_licencia");
                    boolean estadoOperativo = resultado.getBoolean("estado_operativo");
                    Chofer chofer = new Chofer(nombreCompleto, numeroTelefono, numeroLicencia, foto, tipoLicencia, estadoOperativo, salarioBase, idSucursal, fechaVencimiento);
                    choferes.add(chofer);

                }
            }
            return choferes;

        } catch (SQLException ex) {
            return choferes;
        }

    }

    public String updateChofer(String variableCambiar, Object nuevoDato, String numeroLicencia) {
        String sql = "UPDATE chofer SET " + variableCambiar + " = ? WHERE numero_licencia = ?";
        try {

            PreparedStatement updateStatement = conn.prepareStatement(sql);
            if (variableCambiar.equalsIgnoreCase("estado_operativo")) {
                boolean estado = Boolean.parseBoolean(String.valueOf(nuevoDato));
                updateStatement.setBoolean(1, estado);
            } else if (variableCambiar.equalsIgnoreCase("salario_base")) {
                updateStatement.setDouble(1, (double) nuevoDato);

            } else if (variableCambiar.equalsIgnoreCase("fecha_vencimiento_licencia")) {
                Date fecha = Date.valueOf((String) nuevoDato);
                updateStatement.setDate(1, fecha);
            } else {
                String dato = String.valueOf(nuevoDato);
                updateStatement.setString(1, dato);
            }
            updateStatement.setString(2, numeroLicencia);
            String correcto = String.valueOf(updateStatement.executeUpdate());
            return correcto + " elemento editado correctamente";

        } catch (SQLException ex) {
            return sqlExcepcion(ex);
        }
    }

    public String buscarSucursalAsociada(String numeroPlaca) {
        try {
            String sql = "SELECT id_sucursal FROM chofer WHERE numero_licencia = ?";

            PreparedStatement queryStatement = conn.prepareStatement(sql);
            queryStatement.setString(1, numeroPlaca);
            ResultSet sucursal = queryStatement.executeQuery();
            while (sucursal.next()) {
                return sucursal.getString("id_sucursal");
            }
        } catch (SQLException ex) {

        }
        return null;
    }

    public String sqlExcepcion(SQLException excepcion) {
        String error = excepcion.getMessage();

        switch (excepcion.getErrorCode()) {
            case 1452:
                error = "La sucursal seleccionada no existe o no es válida";
                break;

            case 1062:
                error = "El número de licencia ingresado ya se encuentra registrado.";
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

}
