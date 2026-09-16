/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdministradorSucursal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mario
 */
public class UsuarioJdbc {

    private Connection conn;

    public UsuarioJdbc(Connection conn) {
        this.conn = conn;

    }

    public Usuario buscarUsuario(String nombreUsuario, String contrasena) {

        String sql = "SELECT * FROM usuario WHERE nombre_usuario = ? and contrasena = ?";
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
                Usuario usuario = new Usuario(dpiUsuario, nombreUsuario, nitUsuario, numeroTelefono,creditoExistente, direccion, tipoUsuario, estado, contrasena);
               return usuario;
            }
    
        } catch (SQLException ex) {
            return null;
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
