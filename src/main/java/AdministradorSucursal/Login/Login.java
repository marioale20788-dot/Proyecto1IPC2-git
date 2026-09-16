/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdministradorSucursal.Login;

import AdministradorSucursal.Conector;
import AdministradorSucursal.Sucursal;
import AdministradorSucursal.SucursalJdbc;
import AdministradorSucursal.Usuario;
import AdministradorSucursal.UsuarioJdbc;
import jakarta.resource.spi.Connector;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 *
 * @author mario
 */
public class Login {
    
    
    
    public Usuario buscarUsuario(HttpServletRequest request){
        
        String nombreUsuario = request.getParameter("nombreUsuario");
        String contrasena = request.getParameter("contrasena");
        UsuarioJdbc usarioBaseDeDatos = new UsuarioJdbc(Conector.getInstance().getConnection());
        Usuario usuario = usarioBaseDeDatos.buscarUsuario(nombreUsuario, contrasena);
        if(usuario==null){
            return null;
            
        }else{
            return usuario;
        }
    
    }
    
    public ArrayList<Sucursal> buscarSucursalesAsociadasAAdmi(String dpiUsuario){
        SucursalJdbc sucursalBaseDeDatos = new SucursalJdbc(Conector.getInstance().getConnection());
        ArrayList<Sucursal> sucursales = sucursalBaseDeDatos.buscarSucursal(dpiUsuario);
        return sucursales;
        
        
        
        
        
    }
    
    
    
    
    
    
    
    
}
