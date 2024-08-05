/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author Alejandro
 */
public class Proveedor {
    private int idProveedor;
    private String cedula;
    private String nombre;
    private String ciudad;
    private String codigo;
    private String telefono;
    private String email;
    private ArrayList<Producto> listaProductos;

    public Proveedor() {
    }

    public Proveedor(int idProveedor, String cedula, String nombre, String ciudad, String codigo, String telefono, String email, ArrayList<Producto> listaProductos) {
        this.idProveedor = idProveedor;
        this.cedula = cedula;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.codigo = codigo;
        this.telefono = telefono;
        this.email = email;
        this.listaProductos = listaProductos;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @Override
    public String toString() {
        return "              Código de Proveedor - " + getCodigo() + "\n"
                + "    Cedula: " + getCedula() + "\n"
                + "    Proveedor: " + getNombre() + "\n"
                + "    Ciudad: " + getNombre() + "\n"
                + "    Teléfono: " + getTelefono() + "\n"
                + "    Email: " + getEmail();
    }
    
    public boolean validarCedula(String c){
        if (c == null || c.length() != 10) {
            return false;
        }

        if (!c.matches("\\d{10}")) {
            return false;
        }

        int codigoProvincia = Integer.parseInt(c.substring(0, 2));
        int tercerDigito = Integer.parseInt(c.substring(2, 3));

        if (codigoProvincia < 1 || codigoProvincia > 24 || tercerDigito >= 6) {
            return false;
        }

        int[] coeficientes = {2, 1, 2, 1, 2, 1, 2, 1, 2};
        int sum = 0;

        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(c.charAt(i)) * coeficientes[i];
            sum += digito > 9 ? digito - 9 : digito;
        }

        int comprobadorDigito = (10 - (sum % 10)) % 10;

        return comprobadorDigito == Character.getNumericValue(c.charAt(9));
    }
}
