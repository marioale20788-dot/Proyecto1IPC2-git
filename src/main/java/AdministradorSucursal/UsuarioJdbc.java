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
public class UsuarioJdbc {

    private Connection conn;

    public UsuarioJdbc(Connection conn) {
        this.conn = conn;

    }

    public String crearUsuario(Usuario usuario) {
        String slq = "INSERT INTO usuario(dpi_usuario,nombre_usuario,nit_usuario,numero_telefono,credito_existente,direccion,tipo,"
                + "estado,contrasena) values(?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement insertStatement = conn.prepareCall(slq);
            insertStatement.setString(1, usuario.getDpi());
            insertStatement.setString(2, usuario.getNombre());
            insertStatement.setString(3, usuario.getNit());
            insertStatement.setString(4, usuario.getNumeroDeTelefono());
            insertStatement.setDouble(5, 0);
            insertStatement.setString(6, usuario.getDireccion());
            insertStatement.setString(7, usuario.getTipo());
            insertStatement.setBoolean(8, usuario.getEstado());
            insertStatement.setString(9, usuario.getContrasena());
            int i = insertStatement.executeUpdate();
            return i + " usuario creado";
        } catch (SQLException ex) {
            return sqlExcepcion(ex);
        }

    }

    public boolean buscarUsuarioMismoNombre(String nombre) {
        String sql = "SELECT * FROM usuario WHERE nombre_usuario = ?";
        try {
            PreparedStatement queryStatement = conn.prepareStatement(sql);
            queryStatement.setString(1, nombre);
            ResultSet resultado = queryStatement.executeQuery();
            return resultado.next();
        } catch (SQLException ex) {
            return false;
        }

    }

    public Usuario buscarUsuario(String nombreUsuario, String contrasena) {

        String sql = "SELECT * FROM usuario WHERE nombre_usuario = ? AND contrasena = ? AND estado = true";
        try {
            PreparedStatement qualyStatement = conn.prepareStatement(sql);
            qualyStatement.setString(1, nombreUsuario);
            qualyStatement.setString(2, contrasena);
            ResultSet resultado = qualyStatement.executeQuery();
            while (resultado.next()) {
                String dpiUsuario = resultado.getString("dpi_usuario");
                String nitUsuario = resultado.getString("nit_usuario");
                String numeroTelefono = resultado.getString("numero_telefono");
                double creditoExistente = resultado.getDouble("credito_existente");
                String direccion = resultado.getString("direccion");
                String tipoUsuario = resultado.getString("tipo");
                Boolean estado = resultado.getBoolean("estado");
                Usuario usuario = new Usuario(dpiUsuario, nombreUsuario, nitUsuario, numeroTelefono, creditoExistente, direccion, tipoUsuario, estado, contrasena);
                return usuario;
            }

        } catch (SQLException ex) {
            return null;
        }

        return null;

    }

    public Usuario obtenerUsuario(String idUsuario) {
        String sql = "SELECT * FROM usuario WHERE dpi_usuario = ?";
        Usuario usuario = null;
        try {
            PreparedStatement queryStatement = conn.prepareStatement(sql);
            queryStatement.setString(1, idUsuario);
            ResultSet resultado = queryStatement.executeQuery();
            while (resultado.next()) {

                usuario = ResultSetUsuario(resultado);
                return usuario;
            }

        } catch (SQLException ex) {
            return usuario;
        }
        return usuario;
    }

    public Usuario ResultSetUsuario(ResultSet resultado) throws SQLException {
        String dpi = resultado.getString("dpi_usuario");
        String nombre = resultado.getString("nombre_usuario");
        String nit = resultado.getString("nit_usuario");
        String numeroTelefono = resultado.getString("numero_telefono");
        double credito = resultado.getDouble("credito_existente");
        String direccion = resultado.getString("direccion");
        String tipo = resultado.getString("tipo");
        boolean estado = resultado.getBoolean("estado");
        String contrasena = resultado.getString("contrasena");
        Usuario us = new Usuario(dpi, nombre, nit, numeroTelefono, credito, direccion, tipo, estado, contrasena);

        return us;

    }

    public void insertarUsuarioAsucursal(String idAdmin, String idSucursal) {
        String sql = " INSERT INTO asignacion_sucursal(dpi_administrador,id_sucursal) VALUES(?,?)";
        try {
            PreparedStatement inserStatement = conn.prepareStatement(sql);
            inserStatement.setString(1, idAdmin);
            inserStatement.setString(2, idSucursal);
            inserStatement.executeUpdate();
        } catch (SQLException ex) {

        }

    }

    public ArrayList<Usuario> usuariosAdminSucursal() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE tipo = 'ADMIN_SUCURSAL' AND estado = true";
        try {
            PreparedStatement queryStatement = conn.prepareStatement(sql);
            ResultSet resultado = queryStatement.executeQuery();
            while (resultado.next()) {

                Usuario usuario = ResultSetUsuario(resultado);
                usuarios.add(usuario);
            }
            return usuarios;

        } catch (SQLException ex) {
            return usuarios;
        }

    }

    public boolean comprarBoletos(double nuevoCredito, String idUsuario) {

        String sql = "UPDATE usuario SET credito_existente = ? WHERE dpi_usuario = ?";
        try {
            PreparedStatement updateStatement = conn.prepareStatement(sql);
            updateStatement.setDouble(1, nuevoCredito);
            updateStatement.setString(2, idUsuario);
            return updateStatement.execute();
        } catch (SQLException ex) {
            return false;
        }

    }

    public String recargarCreditos(double creditos, String idCliente) {
        String sql = "UPDATE usuario SET credito_existente = credito_existente + ? WHERE dpi_usuario = ?";
        try {
            PreparedStatement updateStatement = conn.prepareStatement(sql);
            updateStatement.setDouble(1, creditos);
            updateStatement.setString(2, idCliente);
            int i = updateStatement.executeUpdate();
            return i + " DEPOSITO ha sido realizado";
        } catch (SQLException ex) {
            return " NO SE PUDO HACER EL DEPOSITO";
        }

    }

    public ArrayList<Usuario> usuariosAdministradores(String idSucursal) {
        String sql = "SELECT * FROM asignacion_sucursal WHERE id_sucursal = ? ";
        ArrayList<Usuario> usuariosAdminSucursal = new ArrayList<>();
        try {
            PreparedStatement queryStatement = conn.prepareStatement(sql);
            queryStatement.setString(1, idSucursal);
            ResultSet resultado = queryStatement.executeQuery();
            while (resultado.next()) {
                String idUsuario = resultado.getString("dpi_administrador");
                Usuario usario = obtenerUsuario(idUsuario);
                usuariosAdminSucursal.add(usario);
            }
            return usuariosAdminSucursal;
        } catch (SQLException ex) {
            return usuariosAdminSucursal;
        }
    }

    public ArrayList<Usuario> usuariosPorTipoNombre(String nombreUsuario, String tipoUsuario) {
        String sql = "SELECT * FROM usuario WHERE tipo = ?";
        if (nombreUsuario != null) {
            sql = sql + " AND nombre_usuario = ?";
        }
        ArrayList<Usuario> usuariosAdminSucursal = new ArrayList<>();
        try {
            PreparedStatement queryStatement = conn.prepareStatement(sql);
            queryStatement.setString(1, tipoUsuario);
            if (nombreUsuario != null) {
                queryStatement.setString(2, nombreUsuario);
            }

            ResultSet resultado = queryStatement.executeQuery();
            while (resultado.next()) {
                Usuario idUsuario = ResultSetUsuario(resultado);
                usuariosAdminSucursal.add(idUsuario);
            }
            return usuariosAdminSucursal;
        } catch (SQLException ex) {
            return usuariosAdminSucursal;
        }
    }

    public String DesactivarUsuario(String idUsuario, boolean estado) {
        String sql = "UPDATE usuario SET estado = ? WHERE dpi_usuario = ?";
        try {
            PreparedStatement updateStatement = conn.prepareStatement(sql);
            updateStatement.setBoolean(1, estado);
            updateStatement.setString(2, idUsuario);
            int i = updateStatement.executeUpdate();
            return i +" CAMBIO REALIZADO";
        } catch (SQLException ex) {
            return sqlExcepcion(ex);

        }

    }

    public ArrayList<Usuario> usuariosAdministradoresFaltantes(String idSucursal) {
        String sql = "SELECT * FROM asignacion_sucursal WHERE dpi_administrador = ? AND id_sucursal = ? ";
        ArrayList<Usuario> usuariosAdminSucursalFaltantes = new ArrayList<>();
        ArrayList<Usuario> usuariosAdminSucursal = usuariosAdminSucursal();
        for (Usuario usuario : usuariosAdminSucursal) {
            try {
                PreparedStatement queryStatement = conn.prepareStatement(sql);
                queryStatement.setString(1, usuario.getDpi());
                queryStatement.setString(2, idSucursal);
                ResultSet resultado = queryStatement.executeQuery();
                if (resultado.next() == false) {
                    usuariosAdminSucursalFaltantes.add(usuario);
                }

            } catch (SQLException ex) {
                return usuariosAdminSucursalFaltantes;

            }

        }
        return usuariosAdminSucursalFaltantes;

    }

    public String sqlExcepcion(SQLException excepcion) {
        String error = excepcion.getMessage();

        switch (excepcion.getErrorCode()) {

            case 1062:
                error = "El número de dpi ingresado ya se encuentra registrado.";
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
