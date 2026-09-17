/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdmnistrarSistema.sucursales;

import AdministradorSucursal.Conector;
import AdministradorSucursal.Sucursal;
import AdministradorSucursal.SucursalJdbc;
import AdministradorSucursal.Usuario;
import AdministradorSucursal.UsuarioJdbc;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 *
 * @author mario
 */
public class validarSucursal {

    public String crearSucursal(HttpServletRequest request) {
        UsuarioJdbc usDb = new UsuarioJdbc(Conector.getInstance().getConnection());
        String idSucursal = "";
        String nombreSucursal = "";
        double montoDepreciacion = 0;
        double latitud = 0;
        double longitud = 0;
        boolean datosCorrectos = true;
        String idAdmin = "";
        String error = "";

        try {
            idSucursal = request.getParameter("idSucursal");
            nombreSucursal = request.getParameter("nombreSucursal");
            idAdmin = request.getParameter("adminSeleccionado");
            if (idSucursal.isEmpty() || nombreSucursal.isEmpty()) {
                datosCorrectos = false;
                error = " DEBE INGRESAR UN VALOR PARA ID SUCURSAL Y NOMBRE SUCURSAL";

            }
            montoDepreciacion = Double.valueOf(request.getParameter("montoDepreciacion"));
            if (usDb.obtenerUsuario(idAdmin) == null) {
                datosCorrectos = false;
                error = " ADMIN NO ENCONTRADO";
            }
            if (montoDepreciacion <= 0) {
                datosCorrectos = false;
                error = " DEPRECIACION DEBE SER AL MENOS MAYOR A CERO";
            }
            latitud = Double.valueOf(request.getParameter("latitudSucursal"));
            longitud = Double.valueOf(request.getParameter("longitudSucursal"));
            if (latitud == 0.0 || longitud == 0.0) {
                datosCorrectos = false;
                error = " SELECCIONE UNA UBICACION PARA LA SUCURSAL";

            }
        } catch (NumberFormatException | NullPointerException e) {
            datosCorrectos = false;
            error = "FORMATO DE ENTRADA INCORRECTO";

        }

        if (datosCorrectos == true) {
            SucursalJdbc sucursalDb = new SucursalJdbc(Conector.getInstance().getConnection());
            Sucursal sucursal = new Sucursal(idSucursal, nombreSucursal, montoDepreciacion, latitud, longitud);
            return sucursalDb.crearSucursal(sucursal, idAdmin);
        } else {
            return error;
        }

    }

    public ArrayList<Sucursal> obtenerSucursales() {
        SucursalJdbc sucursalDb = new SucursalJdbc(Conector.getInstance().getConnection());
        UsuarioJdbc usDb = new UsuarioJdbc(Conector.getInstance().getConnection());
        ArrayList<Sucursal> sucursales = sucursalDb.retornarTodasSucursales();
        for (Sucursal sucursal : sucursales) {
            sucursal.setAdministradores(usDb.usuariosAdministradores(sucursal.getIdSucursal()));
            sucursal.setAdministradoresFaltantes(usDb.usuariosAdministradoresFaltantes(sucursal.getIdSucursal()));

        }
        return sucursales;
    }

    public ArrayList<Usuario> obtenerUsuariosAdminSitema(HttpServletRequest request) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        UsuarioJdbc UsDb = new UsuarioJdbc(Conector.getInstance().getConnection());
        try {
            String nombreUsuario = request.getParameter("nombreUsuario");
            String tipo = request.getParameter("tipoUsuario");
            if (nombreUsuario.isEmpty()) {
                usuarios = UsDb.usuariosPorTipoNombre(null, tipo);
            } else {
                usuarios = UsDb.usuariosPorTipoNombre(nombreUsuario, tipo);
            }

            return usuarios;
        } catch (NullPointerException e) {
            return usuarios;
        }
    }

    public String desactivarUsuario(HttpServletRequest request) {
        try {
            String idUsuario = request.getParameter("idUsuario");
            String tipo = request.getParameter("tipo");
            UsuarioJdbc usuarioDb = new UsuarioJdbc(Conector.getInstance().getConnection());
            SucursalJdbc sucursalDb = new SucursalJdbc(Conector.getInstance().getConnection());
            boolean estado;
            try {
                estado = Boolean.valueOf(request.getParameter("estado"));
                if (tipo.equalsIgnoreCase("CLIENTE")) {

                    return usuarioDb.DesactivarUsuario(idUsuario, !estado);
                } else if (tipo.equalsIgnoreCase("ADMIN_SUCURSAL")) {
                    ArrayList<Sucursal> sucursalesAsociadas = sucursalDb.buscarSucursal(idUsuario);
                    for (Sucursal sucursalesAsociada : sucursalesAsociadas) {
                        if (sucursalDb.verficarMasDeUnaAsignacion(sucursalesAsociada.getIdSucursal()) == false) {
                            return " NO SE PUDO ELIMINAR YA QUE ES ADMINISTRADOR DE UNA SUCURSAL QUE TIENE UN SOLO ENCARGADO";
                        }

                    }
                    return usuarioDb.DesactivarUsuario(idUsuario, !estado);

                }

            } catch (NumberFormatException e) {
                return " NO SE PUDO REALIZAR ESTA ACCION";
            }
        } catch (NullPointerException e) {
            return " NO SE PUDO REALIZAR ESTA ACCION";
        }

        return " NO SE PUDO REALIZAR ESTA ACCION";
    }

    public String AccionAdminSucursal(HttpServletRequest request) {
        SucursalJdbc sucursalDb = new SucursalJdbc(Conector.getInstance().getConnection());
        try {
            String accion = request.getParameter("accion");
            String idSucursal = request.getParameter("idSucursal");
            if (accion.equalsIgnoreCase("editar")) {
                String itemAeditar = request.getParameter("itemAEditar");
                String nuevoValor = request.getParameter("nuevoValor");
                Object nuevoValorReal;
                if (itemAeditar.isEmpty() || nuevoValor.isEmpty()) {
                    return "DEBE INDICAR UN NUEVO VALOR";
                }
                if (itemAeditar.equalsIgnoreCase("depreciacion")) {
                    try {
                        double nuevaDepreciacion = Double.valueOf(nuevoValor);
                        if (nuevaDepreciacion <= 0) {
                            return "FORMATO DE ENTRADA INCORRECTO, DIGITO NEGATIVO O CERO";
                        }
                        nuevoValorReal = nuevaDepreciacion;
                        return sucursalDb.editarSucursal(nuevoValorReal, itemAeditar, idSucursal);

                    } catch (NumberFormatException e) {
                        return " FORMATO DE ENTRADA INCORRECTO";
                    }

                } else if (itemAeditar.equalsIgnoreCase("nombre_sucursal")) {
                    nuevoValorReal = nuevoValor;
                    return sucursalDb.editarSucursal(nuevoValorReal, itemAeditar, idSucursal);

                }

            } else if (accion.equalsIgnoreCase("eliminar")) {
                String dpiAdmin = request.getParameter("idUsuario");
                return sucursalDb.eliminarAsignacion(dpiAdmin, idSucursal);

            } else if (accion.equalsIgnoreCase("agregar")) {
                String dpiAdmin = request.getParameter("idUsuario");
                return sucursalDb.insertarUsuarioAsucursal(dpiAdmin, idSucursal);

            }

        } catch (NullPointerException e) {
            return " no se pudo realizar esta accion";
        }
        return "NO SE PUDO REALIZAR ESTA ACCION";

    }

}
