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
public class RutaJdbc {
    
    
    private Connection conn;

    public RutaJdbc(Connection conn) {
        this.conn = conn;
    }
    
    
    public ArrayList<Ruta> BuscarRutas(String SucursalOrigen, String nombreRuta){
        
        String sql = "SELECT * FROM ruta WHERE id_sucursal_origen = ?";
        if(nombreRuta!=null){
            sql= sql + " AND id_ruta = ?";
        }
        
        ArrayList<Ruta> rutas = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, SucursalOrigen);
            if(nombreRuta!=null){
                statement.setString(2, nombreRuta);
            }
            
            ResultSet resultado = statement.executeQuery();
            while(resultado.next()){
                String nombre = resultado.getString("id_ruta");
                String idSucursalOrigen = resultado.getString("id_sucursal_origen");
                String idSucursalDestino = resultado.getString("id_sucursal_destino");
                double distanciaKm = resultado.getDouble("distancia_km");
                Ruta ruta = new Ruta(nombre, idSucursalOrigen, idSucursalDestino, distanciaKm);
                rutas.add(ruta);
            }
            return rutas;
            
            
        } catch (SQLException ex) {
            return rutas;
        }
        
        
        
    }
    
    public String eliminarRuta(String nombreRuta ,String idSucursalOrigen){
      
        String sql = "DELETE FROM ruta WHERE id_ruta = ? AND id_sucursal_origen = ?";
        try {
            PreparedStatement updateStatement = conn.prepareStatement(sql);
            updateStatement.setString(1, nombreRuta);
             updateStatement.setString(2, idSucursalOrigen);
            int resultado = updateStatement.executeUpdate();
              String resultadoTexto;
            if(resultado==0){
                 resultadoTexto  ="NO SE PUDO ELIMINAR LA RUTA";
            }else{
                  resultadoTexto  = resultado +  " ELEMENTO ELIMINADO";
            }

            return resultadoTexto;
            
        } catch (SQLException ex) {
           String error = " NO SE PUDO ELIMINAR LA RUTA";
           return error;
        }
        
    }
    
    public boolean buscarRuta(String sucursalOrigen,String sucursalDestino){
        String sql = "SELECT * FROM ruta WHERE id_sucursal_origen = ? AND id_sucursal_destino = ? ";
        try {
            PreparedStatement qualyStatement = conn.prepareStatement(sql);
            qualyStatement.setString(1, sucursalOrigen);
            qualyStatement.setString(2, sucursalDestino);
            ResultSet resultado = qualyStatement.executeQuery();
             return resultado.next();
            
        } catch (SQLException ex) {
            return false;
         
        }
        
    }
    
    
    public String crearRuta(Ruta ruta){
        String sql = "INSERT INTO ruta(id_ruta,id_sucursal_origen,id_sucursal_destino,distancia_km) values(?,?,?,?)";
        String resultadoTxt = "";
        try {
            PreparedStatement insertStatement = conn.prepareStatement(sql);
            insertStatement.setString(1, ruta.getNombreRutal());
            insertStatement.setString(2, ruta.getIdSucursalOrigen());
            insertStatement.setString(3, ruta.getIdSucursalDestino());
            insertStatement.setDouble(4, ruta.getDistanciaEnKm());
            int resultado = insertStatement.executeUpdate();
            if(resultado==0){
                resultadoTxt = resultado + "NO SE PUDO CREAR EL ELEMENTO";
            }else{
                resultadoTxt = resultado + " elemento creado";
            }
            
            
            
        } catch (SQLException ex) {
          return sqlExcepcion(ex);
        }
        
        return resultadoTxt;
        
    }
    
   public String sqlExcepcion(SQLException excepcion) {
    String error;

    switch (excepcion.getErrorCode()) {
        case 1451:
            error = "No se puede eliminar la ruta porque tiene viajes registros asociados.";
            break;

        case 1452:
            error = "La sucursal seleccionada no existe o no es válida.";
            break;

        case 1062:
            error = "El nombre de ruta ingresado ya se encuentra registrado.";
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
