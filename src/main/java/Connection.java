
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
public class Connection {
        private static final String IP = "localhost";
    private static final int PUERTO = 3306;
    private static final String SCHEMA = "CAFETERIA";
    private String USER_NAME = "mario";
    private String PASSWORD="pastormario2911";
    public static final String URL = "jdbc:mysql://"+ IP + ":" + PUERTO;
    private java.sql.Connection connection;




    public void connect() {
            try {
                connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            } catch (SQLException ex) {
                System.out.println("no se pudo conectar");;
            }

    }

    public java.sql.Connection getConnection() {
        return connection;
    }
    
    
    
    
    
    
}
