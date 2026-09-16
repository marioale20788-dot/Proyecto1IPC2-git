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

/**
 *
 * @author mario
 */
public class SucursalJdbc {

    private Connection conn;

    public SucursalJdbc(Connection con) {
        this.conn = con;
    }

    public ArrayList<Sucursal> buscarSucursal(String dpiAdmin) {
        String sql = "SELECT id_sucursal FROM asignacion_sucursal WHERE dpi_administrador = ?";
        String sqlSucursal = "SELECT * FROM sucursal WHERE id_sucursal = ?";
        ArrayList<Sucursal> sucursalesAdmin = new ArrayList<>();
        try {
            PreparedStatement qualyStatement = conn.prepareStatement(sql);
            qualyStatement.setString(1, dpiAdmin);
            ResultSet sucursales = qualyStatement.executeQuery();
            while (sucursales.next()) {
                PreparedStatement quaStatement = conn.prepareStatement(sqlSucursal);
                quaStatement.setString(1, sucursales.getString("id_sucursal"));
                ResultSet crearSucursal = quaStatement.executeQuery();
                while (crearSucursal.next()) {
                    String idSucursal = crearSucursal.getString("id_sucursal");
                    String nombre = crearSucursal.getString("nombre_sucursal");
                    double depreciacion = crearSucursal.getDouble("depreciacion");
                    double latitud = crearSucursal.getDouble("latitud");
                    double longitud = crearSucursal.getDouble("longitud");
                    Sucursal sucursal = new Sucursal(idSucursal, nombre, depreciacion, latitud, longitud);
                    sucursalesAdmin.add(sucursal);
                }

            }

            return sucursalesAdmin;

        } catch (SQLException ex) {
            return null;
        }

    }

    public ArrayList<Sucursal> buscarSucursalesDestino(String idOrigen) {
        String sql = "SELECT * FROM sucursal WHERE id_sucursal != ?";
        ArrayList<Sucursal> sucursalesDestino = new ArrayList<>();

        try {
            PreparedStatement qualyStatement = conn.prepareStatement(sql);
            qualyStatement.setString(1, idOrigen);
            ResultSet resultado = qualyStatement.executeQuery();
            while (resultado.next()) {
                String idSucursal = resultado.getString("id_sucursal");
                String nombre = resultado.getString("nombre_sucursal");
                double depreciacion = resultado.getDouble("depreciacion");
                double latitud = resultado.getDouble("latitud");
                double longitud = resultado.getDouble("longitud");
                Sucursal sucursal = new Sucursal(idSucursal, nombre, depreciacion, latitud, longitud);
                sucursalesDestino.add(sucursal);
            }
            return sucursalesDestino;

        } catch (SQLException ex) {
            return sucursalesDestino;
        }

    }

    public Sucursal buscarSucursalPorId(String idSucursal) {
        String sql = "SELECT * FROM sucursal wHERE id_sucursal = ?";
        try {
            PreparedStatement qualyStatement = conn.prepareStatement(sql);
            qualyStatement.setString(1, idSucursal);
            ResultSet resultado = qualyStatement.executeQuery();
            while (resultado.next()) {
                String nombre = resultado.getString("nombre_sucursal");
                double depreciacion = resultado.getDouble("depreciacion");
                double latitud = resultado.getDouble("latitud");
                double longitud = resultado.getDouble("longitud");
                Sucursal sucursal = new Sucursal(idSucursal, nombre, depreciacion, latitud, longitud);
                return sucursal;
            }

        } catch (SQLException ex) {
            return null;
        }
        return null;

    }

    public ArrayList<Sucursal> buscarSucursalesDestinoRutas(ArrayList<Ruta> rutas) {
        String sql = "SELECT * FROM sucursal wHERE id_sucursal = ?";
        ArrayList<Sucursal> sucursales = new ArrayList<>();
        for (Ruta ruta : rutas) {

            try {
                PreparedStatement qualyStatement = conn.prepareStatement(sql);
                qualyStatement.setString(1, ruta.getIdSucursalDestino());
                ResultSet resultado = qualyStatement.executeQuery();
                while (resultado.next()) {
                    String nombre = resultado.getString("nombre_sucursal");
                    double depreciacion = resultado.getDouble("depreciacion");
                    double latitud = resultado.getDouble("latitud");
                    double longitud = resultado.getDouble("longitud");
                    Sucursal sucursal = new Sucursal(ruta.getIdSucursalDestino(), nombre, depreciacion, latitud, longitud);
                    sucursales.add(sucursal);

                }
              

            } catch (SQLException ex) {
                return sucursales;
            }

        }

        return sucursales;

    }

}
