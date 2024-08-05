/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Alejandro
 */
public class Producto {
    private int idProducto;
    private String nombre;
    private double tamanio;
    private double peso;
    private double precio;

    public Producto() {
    }

    public Producto(int idProducto, String nombre, double tamanio, double peso, double precio) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.tamanio = tamanio;
        this.peso = peso;
        this.precio = precio;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public double getTamanio() {
        return tamanio;
    }

    public void setTamanio(double tamanio) {
        this.tamanio = tamanio;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return getNombre() + " - $" + getPrecio() + " - " + getTamanio() + "cm - " + getPeso() + "kg\n";
    }
}
