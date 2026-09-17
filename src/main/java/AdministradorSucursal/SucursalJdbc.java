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
                    Sucursal sucursal = llenarResultSet(crearSucursal);
                    sucursalesAdmin.add(sucursal);
                }

            }

            return sucursalesAdmin;

        } catch (SQLException ex) {
            return null;
        }

    }
    

    public String crearSucursal(Sucursal sucursal,String idUsuario) {
        UsuarioJdbc UsDb = new UsuarioJdbc(conn);
        
        String sql = "INSERT INTO sucursal(id_sucursal,nombre_sucursal,depreciacion,latitud,longitud) VALUES(?,?,?,?,?)";
        PreparedStatement insertStatement;
        try {
            insertStatement = conn.prepareStatement(sql);
            insertStatement.setString(1, sucursal.getIdSucursal());
            insertStatement.setString(2, sucursal.getNombre());
            insertStatement.setDouble(3, sucursal.getDepreciacion());
            insertStatement.setDouble(4, sucursal.getLatitud());
            insertStatement.setDouble(5, sucursal.getLongitud());
            int i = insertStatement.executeUpdate();
            if(i==1){
                UsDb.insertarUsuarioAsucursal(idUsuario, sucursal.getIdSucursal());
            }
            return i + " SUCURSAL CREADA";
        } catch (SQLException ex) {
           return sqlExcepcion(ex);
        }

    }

    public ArrayList<Sucursal> retornarTodasSucursales() {
        ArrayList<Sucursal> sucursales = new ArrayList<>();
        String sql = "SELECT * FROM sucursal";
        try {
            PreparedStatement queryStatement = conn.prepareStatement(sql);
            ResultSet resultado = queryStatement.executeQuery();
            while (resultado.next()) {
                Sucursal sucursal = llenarResultSet(resultado);
                sucursales.add(sucursal);
            }
            return sucursales;

        } catch (SQLException ex) {

            return sucursales;

        }

    }

    public Sucursal llenarResultSet(ResultSet resultado) throws SQLException {

        String idSucursal = resultado.getString("id_sucursal");
        String nombre = resultado.getString("nombre_sucursal");
        double depreciacion = resultado.getDouble("depreciacion");
        double latitud = resultado.getDouble("latitud");
        double longitud = resultado.getDouble("longitud");
        Sucursal sucursal = new Sucursal(idSucursal, nombre, depreciacion, latitud, longitud);
        return sucursal;
    }

    public ArrayList<Sucursal> buscarSucursalesDestino(String idOrigen) {
        String sql = "SELECT * FROM sucursal WHERE id_sucursal != ?";
        ArrayList<Sucursal> sucursalesDestino = new ArrayList<>();

        try {
            PreparedStatement qualyStatement = conn.prepareStatement(sql);
            qualyStatement.setString(1, idOrigen);
            ResultSet resultado = qualyStatement.executeQuery();
            while (resultado.next()) {

                Sucursal sucursal = llenarResultSet(resultado);
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
                Sucursal sucursal = llenarResultSet(resultado);
                return sucursal;
            }

        } catch (SQLException ex) {
            return null;
        }
        return null;

    }
    
    public String editarSucursal(Object nuevoValor,String opcionAEditar,String idSucursal){
        String sql = "UPDATE sucursal SET "+opcionAEditar+" = ? WHERE id_sucursal = ? ";
        try {
            PreparedStatement updateStatement = conn.prepareStatement(sql);
            if(opcionAEditar.equalsIgnoreCase("depreciacion")){
                updateStatement.setDouble(1, (double)nuevoValor);
                
            }
            if(opcionAEditar.equalsIgnoreCase("nombre_sucursal")){
                 updateStatement.setString(1, (String)nuevoValor);
            }
               updateStatement.setString(2, idSucursal);
            int resultado = updateStatement.executeUpdate();
            if(resultado==0){
                return " NO SE PUDO EDITAR LA SUCURSAL";
            }
            return " DAATO EDITADO CORRECTAMENTE";
        } catch (SQLException ex) {
            return sqlExcepcion(ex);
        }
        
    }
    public String eliminarAsignacion(String dpiAdmin, String idSucursal){
        String sql = "DELETE FROM asignacion_sucursal WHERE dpi_administrador = ? AND id_sucursal = ?";
        if(verficarMasDeUnaAsignacion(idSucursal)==false){
            return " DEBE HABER AL MENOS UN ADMINSTRADOR ENLAZADO CON LA SUCURSAL";
        }
        try {
            PreparedStatement updateStatement = conn.prepareStatement(sql);
            updateStatement.setString(1, dpiAdmin);
            updateStatement.setString(2, idSucursal);
            int  i = updateStatement.executeUpdate();
            return i+" asignacion eliminada";
            
        } catch (SQLException ex) {
            return sqlExcepcion(ex);
        }
    }
     public String insertarUsuarioAsucursal(String idAdmin, String idSucursal) {
        String sql = " INSERT INTO asignacion_sucursal(dpi_administrador,id_sucursal) VALUES(?,?)";
        try {
            PreparedStatement inserStatement = conn.prepareStatement(sql);
            inserStatement.setString(1, idAdmin);
            inserStatement.setString(2, idSucursal);
            int i = inserStatement.executeUpdate();
            return i + " asinacion realizada";
        } catch (SQLException ex) {
            return sqlExcepcion(ex);
            

        }
     }
    
    public boolean verficarMasDeUnaAsignacion(String idSucursal){
        String sql = "SELECT * FROM asignacion_sucursal WHERE id_sucursal = ?";
        try {
            PreparedStatement queryStatement = conn.prepareStatement(sql);
            queryStatement.setString(1, idSucursal);
            ResultSet resultado = queryStatement.executeQuery();
            int contador = 0;
            while (resultado.next()){
                contador++;
           }
            if(contador==1){
                return false;
            }
            return true;
        } catch (SQLException ex) {
           return false;
        }
        
        
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
                    Sucursal sucursal = llenarResultSet(resultado);
                    sucursales.add(sucursal);

                }

            } catch (SQLException ex) {
                return sucursales;
            }

        }

        return sucursales;

    }
        public String sqlExcepcion(SQLException excepcion) {
        String error;

        switch (excepcion.getErrorCode()) {
          
            case 1452:
                error = "Un elemento seleccionado no existe o no es válido";
                break;

            case 1062:
                error = "El id de sucursal ingresado ya se encuentra registrado.";
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
