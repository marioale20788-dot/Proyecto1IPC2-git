package AdministradorSucursal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author mario
 */
public class Conector {

    private static final String IP = "localhost";
    private static final int PUERTO = 3306;
    private static final String SCHEMA = "SISTEMA_DE_BUSES";
    private String USER_NAME = "mario";
    private String PASSWORD = "pastormario2911";
    public static final String URL = "jdbc:mysql://" + IP + ":" + PUERTO + "/" + SCHEMA;
    private Connection connection;

    public void connect()  {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.getLogger(Conector.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException ex) {
            System.getLogger(Conector.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public java.sql.Connection getConnection() {
        return connection;
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("🔒 Conexión cerrada.");
            } catch (SQLException e) {
                System.err.println("❌ Error al cerrar: " + e.getMessage());
            }
        } else {
            System.out.println("⚠️ No hay conexión que cerrar.");
        }
    }

}
