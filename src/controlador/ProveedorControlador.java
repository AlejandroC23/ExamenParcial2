/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Producto;
import modelo.Proveedor;

/**
 *
 * @author Alejandro
 */
public class ProveedorControlador {
    //ATRIBUTOS
    //Métodos
    private Proveedor proveedor;
    //Conexión
    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;
    
    public void crearProveedor(Proveedor pro){
        try {//Excepción que lanza la consulta
            //STRING ESTÁTICO CON COMPONENTES DINÁMICOS
            String consulta = "INSERT INTO proveedores(pro_nombre, "
                    + "pro_ciudad, "
                    + "pro_cedula, "
                    + "pro_codigo, "
                    + "pro_telefono, "
                    + "pro_email) " +
            "VALUES ('" + pro.getNombre() + "', '" 
                    + pro.getCiudad() + "', '" 
                    + pro.getCedula() + "', '" 
                    + pro.getCedula() + "', '" 
                    + pro.getTelefono() + "', '" 
                    + pro.getEmail() + "');";
            
            ejecutar =(PreparedStatement) connection.prepareCall(consulta);
            
            //Ejecutar la consulta
            int res = ejecutar.executeUpdate();
            
            if(res > 0){
                ejecutar.close();
            }else{
                ejecutar.close();
            }
            
        } catch(Exception e){
            //Captura el error en memoria y continúa la ejecución
            //ERROR - DEBUG
            System.out.println("ERROR: " + e);
        }
    }
    
    public void editarCodigo(String codigo, int idProveedor){
        try {//Excepción que lanza la consulta
            //STRING ESTÁTICO CON COMPONENTES DINÁMICOS
            String consulta = "UPDATE proveedores SET "
                    + "pro_codigo = '" + codigo + "' "
                    + "WHERE pro_id = " + idProveedor;
            
            ejecutar =(PreparedStatement) connection.prepareCall(consulta);
            
            //Ejecutar la consulta
            int res = ejecutar.executeUpdate();
            
            if(res > 0){
                ejecutar.close();
            }else{
                ejecutar.close();
            }
            
        } catch(Exception e){
            //Captura el error en memoria y continúa la ejecución
            //ERROR - DEBUG
            System.out.println("ERROR: " + e);
        }
    }
    
    public int buscarIdProveedor(String cedula){
        try {
            String consulta = "SELECT pro_id FROM proveedores "
                    + "WHERE pro_cedula = '" + cedula + "';";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            
            resultado = ejecutar.executeQuery(consulta);
            
            if(resultado.next()){
                int idProveedor = resultado.getInt("pro_id");
                return idProveedor;
            }else{
                System.out.println("¡ERROR! Ingrese una cédula válida");
            }
            ejecutar.close();
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                     + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return 0;
    }
    
    public boolean existeCuenta(String cedula){
        try {
            String consulta = "SELECT * FROM proveedores "
                    + "WHERE pro_cedula = '" + cedula + "';";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            
            resultado = ejecutar.executeQuery(consulta);
            
            if(resultado.next()){
                ejecutar.close();
                return true;
            }else{
                ejecutar.close();
                return false;
            }
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                     + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return false;
    }
    
    public ArrayList<Proveedor> listarProveedores(){
        ArrayList<Proveedor> listadoProveedores = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM proveedores;";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            resultado = ejecutar.executeQuery(consulta);
            
            while(resultado.next()){
                Proveedor pro = new Proveedor();
                ProductoControlador produc = new ProductoControlador();
                
                pro.setIdProveedor(resultado.getInt("pro_id"));
                pro.setNombre(resultado.getString("pro_nombre"));
                pro.setCiudad(resultado.getString("pro_ciudad"));
                pro.setCodigo(resultado.getString("pro_codigo"));
                pro.setCedula(resultado.getString("pro_cedula"));
                pro.setTelefono(resultado.getString("pro_telefono"));
                pro.setEmail(resultado.getString("pro_email"));
                
                ArrayList<Producto> listaProductos = produc.listarProductos(pro.getIdProveedor());
                pro.setListaProductos(listaProductos);
                
                listadoProveedores.add(pro);
            }
            ejecutar.close();
            return listadoProveedores;
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                     + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return listadoProveedores;
    }
}
