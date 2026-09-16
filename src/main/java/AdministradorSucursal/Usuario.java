/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdministradorSucursal;

/**
 *
 * @author mario
 */
public class Usuario {
    
    private String dpi;
    private String nombre;
    private String nit;
    private String numeroDeTelefono;
    private Double credito;
    private String direccion;
    private String tipo;
    private boolean estado;
    private String contrasena;

    public Usuario(String dpi, String nombre, String nit, String numeroDeTelefono, Double credito, String direccion, String tipo, boolean estado, String contrasena) {
        this.dpi = dpi;
        this.nombre = nombre;
        this.nit = nit;
        this.numeroDeTelefono = numeroDeTelefono;
        this.credito = credito;
        this.direccion = direccion;
        this.tipo = tipo;
        this.estado = estado;
        this.contrasena = contrasena;
    }
    
    
    

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNumeroDeTelefono() {
        return numeroDeTelefono;
    }

    public void setNumeroDeTelefono(String numeroDeTelefono) {
        this.numeroDeTelefono = numeroDeTelefono;
    }

    public Double getCredito() {
        return credito;
    }

    public void setCredito(Double credito) {
        this.credito = credito;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    
    
}
