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
public class DepreciacionJdbc {

    private Connection conn;

    public DepreciacionJdbc(Connection conn) {
        this.conn = conn;
    }

    public String insertarDepreciacion(String idViaje, double montoDepreciacion, double kmRecorridos) {
        Depreciacion depreciacion = new Depreciacion(idViaje, montoDepreciacion, kmRecorridos);
        double total = depreciacion.obtenerDepreciacion();
        String sql = "INSERT INTO depreciacion_viaje (id_viaje,monto_depreciacion,tasa_usada,kilometros_recorridos) values(?,?,?,?)";
        try {
            PreparedStatement insertStatement = conn.prepareStatement(sql);
            insertStatement.setString(1, idViaje);
            insertStatement.setDouble(2, total);
            insertStatement.setDouble(3, montoDepreciacion);
            insertStatement.setDouble(4, kmRecorridos);
            int i = insertStatement.executeUpdate();
            return " VIAJE TERMINADO CORRECTAMENTE";
        } catch (SQLException ex) {
           return " VIAJE NO SE TERMINO CORRECTAMENTE";
        }

    }

}
