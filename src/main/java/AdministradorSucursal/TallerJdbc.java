/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdministradorSucursal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author mario
 */
public class TallerJdbc {
    
    private Connection conn;

    public TallerJdbc(Connection conn) {
        this.conn = conn;
    }
    
    
    
    public String crearMantenimiento(TallerObj taller){
        String sql = "INSERT INTO mantenimiento(numero_placa,fecha_mantenimiento,monto_repuestos,monto_mano_de_obra,numero_recibo) values(?,?,?,?,?)";
        try {
            PreparedStatement insertStatement = conn.prepareStatement(sql);
            insertStatement.setString(1, taller.getIdBus());
            insertStatement.setDate(2, taller.getFechaMantenimient());
            insertStatement.setDouble(3, taller.getMontoRepuestos());
            insertStatement.setDouble(4, taller.getMonotManoDeObra());
               insertStatement.setString(5, taller.getRecibo());
            int resultado = insertStatement.executeUpdate();
            if(resultado==0){
                return " NO SE PUDO GUARDAR EL MANTENIMIENTO";
            }
            return " MANTENIMIENTO GUARDADO CORRECTAMENTE";
        } catch (SQLException ex) {
            return sqlExcepcion(ex);
        }
        
        
    }
    public String sqlExcepcion(SQLException excepcion) {
        String error;

        switch (excepcion.getErrorCode()) {
          
            case 1452:
                error = "Un elemento seleccionado no existe o no es válido";
                break;

            case 1062:
                error = "El id de recibo ingresado ya se encuentra registrado.";
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
