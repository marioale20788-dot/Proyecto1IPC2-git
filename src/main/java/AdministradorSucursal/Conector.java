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

    private static Conector instance;

    private Conector() {
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {

            System.out.println("Error al conectarse");
            e.printStackTrace();
        }
    }

    public static Conector getInstance() {
        if (instance == null) {
            instance = new Conector();
        }
        return instance;
    }

   

    public java.sql.Connection getConnection() {
        return connection;
    }



}
