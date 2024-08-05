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
public class ProductoControlador {
    //ATRIBUTOS
    //Métodos
    private Producto producto;
    //Conexión
    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;
    
    public void crearProducto(Producto produ, int idProveedor){
        try {//Excepción que lanza la consulta
            //STRING ESTÁTICO CON COMPONENTES DINÁMICOS
            String consulta = "INSERT INTO productos(produ_nombre, "
                    + "produ_tamanio, "
                    + "produ_peso, "
                    + "produ_precio, "
                    + "pro_id) " +
            "VALUES ('" + produ.getNombre() + "', " 
                    + produ.getTamanio()+ ", " 
                    + produ.getPeso() + ", " 
                    + produ.getPrecio()+ ", "  
                    + idProveedor + ");";
            
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
    
    public ArrayList<Producto> listarProductos(int idProveedor){
        ArrayList<Producto> listadoProductos = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM productos "
                    + "WHERE pro_id = " + idProveedor + ";";
            ejecutar = (PreparedStatement) connection.prepareCall(consulta);
            resultado = ejecutar.executeQuery(consulta);
            
            while(resultado.next()){
                Producto produ = new Producto();
                
                produ.setIdProducto(resultado.getInt("produ_id"));
                produ.setNombre(resultado.getString("produ_nombre"));
                produ.setTamanio(resultado.getDouble("produ_tamanio"));
                produ.setPeso(resultado.getDouble("produ_peso"));
                produ.setPrecio(resultado.getDouble("produ_precio"));
                
                listadoProductos.add(produ);
            }
            ejecutar.close();
            return listadoProductos;
        } catch (Exception e) {
            System.out.println("¡ERROR EN EL SISTEMA! COMUNIQUESE CON EL ADMINISTRADOR\n"
                     + "PARA SOLUCIONAR SU PROBLEMA: " + e);
        }
        return listadoProductos;
    }
    
}
