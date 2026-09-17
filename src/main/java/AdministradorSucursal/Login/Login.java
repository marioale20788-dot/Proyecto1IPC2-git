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

    public Usuario buscarUsuario(HttpServletRequest request) {

        String nombreUsuario = request.getParameter("nombreUsuario");
        String contrasena = request.getParameter("contrasena");
        UsuarioJdbc usarioBaseDeDatos = new UsuarioJdbc(Conector.getInstance().getConnection());
        Usuario usuario = usarioBaseDeDatos.buscarUsuario(nombreUsuario, contrasena);
        if (usuario == null) {
            return null;

        } else {
            return usuario;
        }

    }

    public ArrayList<Sucursal> buscarSucursalesAsociadasAAdmi(String dpiUsuario) {
        SucursalJdbc sucursalBaseDeDatos = new SucursalJdbc(Conector.getInstance().getConnection());
        ArrayList<Sucursal> sucursales = sucursalBaseDeDatos.buscarSucursal(dpiUsuario);
        return sucursales;

    }

    public String crearUsuario(HttpServletRequest request) {
        String dpiUsuario = request.getParameter("dpiUsuario");
        String nombreUsuario = request.getParameter("nombreUsuario");
        String nitUsuario = request.getParameter("nitUsuario");
        String numeroDeTelefono = request.getParameter("numeroDeTelefono");
        String direccion = request.getParameter("direccion");
        String contrasena = request.getParameter("contrasena");
        boolean datosCorrectos = true;
        String error = "";
        if (dpiUsuario != null && nombreUsuario != null && nitUsuario != null && numeroDeTelefono != null
                && direccion != null && contrasena != null) {

            if (dpiUsuario.isEmpty() || nombreUsuario.isEmpty() || nitUsuario.isEmpty() || direccion.isEmpty()
                    || contrasena.isEmpty()) {
                datosCorrectos = false;
                error = " DEBE LLENAR TODAS LAS CASILLAS";

            }

            UsuarioJdbc UsuarioDb = new UsuarioJdbc(Conector.getInstance().getConnection());
            try {
                int numero = Integer.valueOf(numeroDeTelefono);
                boolean nombreUsuarioRepetido = UsuarioDb.buscarUsuarioMismoNombre(nombreUsuario);
                if (nombreUsuarioRepetido == true) {
                    datosCorrectos = false;
                    error = " NOMBRE DE USUARIO REPETIDO";

                }

            } catch (NumberFormatException e) {
                datosCorrectos = false;
                error = " FORMATO DE ENTRADA INCORRECTO";

            }

            if (datosCorrectos == true) {

                String estado = request.getParameter("estado");
                if (estado != null) {
                    try {
                        boolean estadoUs = Boolean.valueOf(estado);
                        Usuario usuario = new Usuario(dpiUsuario, nombreUsuario, nitUsuario, numeroDeTelefono,
                                0.0, direccion, "ADMIN_SUCURSAL", estadoUs, contrasena);
                        return UsuarioDb.crearUsuario(usuario);

                    } catch (Exception e) {
                        return " FORMATO DE ENTRADA INCORRECTO";

                    }
                } else {
                    Usuario usuario = new Usuario(dpiUsuario, nombreUsuario, nitUsuario, numeroDeTelefono,
                            0.0, direccion, "CLIENTE", true, contrasena);

                    return UsuarioDb.crearUsuario(usuario);

                }

            } else {
                return error;

            }

        }
        return " NO SE PUDO CREAR EL USUARIO";

    }

}
