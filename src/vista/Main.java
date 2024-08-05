/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ProductoControlador;
import controlador.ProveedorControlador;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;
import modelo.Producto;
import modelo.Proveedor;

/**
 *
 * @author Alejandro
 */
public class Main {
    private static final String TITULO = """
                            *------------* Sistema de Proveedores para Tiendas *------------*
                                                  - Administrador -
                                         
                            """;
    private static final String SEPARADOR = """
                           *------------*            *------------*           *------------*
                           """;
    
    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader br = new BufferedReader(reader);
        Scanner s = new Scanner(System.in);
        
        String opc;
        
        do{
            Funciones.cls();
            System.out.println(TITULO);
            System.out.println("""
                                   1. Ver proveedores y sus productos
                                   2. Agregar proveedor
                                   3. Agregar producto a proveedor
                                   4. Salir
                               
                               """);
            System.out.print("    Opción: ");
            opc = s.next();
            switch(opc){
                case "1" -> {
                    verProveedores();
                }
                case "2" -> {
                    agregarProveedor();
                }
                case "3" -> {
                    agregarProducto();
                }
                case "4" -> {
                    break;
                }
                default -> {
                    Funciones.cls();
                    System.out.println("¡ERROR! Ingrese una opción correcta.");
                    Funciones.pause();
                }
            }
        }while(true);
    }
    
    public static void verProveedores() throws IOException {
        ProveedorControlador proc = new ProveedorControlador();
        
        Scanner s = new Scanner(System.in);
        
        do {
            ArrayList<Proveedor> listadoProveedores = proc.listarProveedores();
            Funciones.cls();
            System.out.println(TITULO);
            System.out.print("     Seleccione el proveedor: \n");
            for (Proveedor p : listadoProveedores) {
                System.out.print("     "
                        + p.getIdProveedor() + ". "
                        + p.getNombre() + ".\n");
            }
            System.out.print("     Opcion: ");
            String opcCar = s.next();

            if (Funciones.isValidNumeric(opcCar)) {
                int a = Integer.parseInt(opcCar);
                if (a > listadoProveedores.size() || a < listadoProveedores.size()) {
                    Funciones.cls();
                    System.out.println("    ¡ERROR! Ingrese una opción correcta.");
                    Funciones.pause();
                } else {
                    String opcYN;
                    do {
                        Funciones.cls();
                        System.out.println(TITULO);
                        System.out.println("El proveedor seleccionado es: " + listadoProveedores.get(a-1).getNombre());
                        System.out.print("¿Es correcto? [Y/n] ");
                        opcYN = s.next();
                        switch (opcYN) {
                            case "Y" -> {
                                if(listadoProveedores.get(a-1).getListaProductos().isEmpty()){
                                    Funciones.cls();
                                    System.out.println(TITULO);
                                    System.out.println(listadoProveedores.get(a-1));
                                    System.out.println(SEPARADOR);
                                    System.out.println("     El proveedor seleccionado aún no tiene productos asociados.");
                                    Funciones.pause();
                                }else{
                                    verProductos(listadoProveedores.get(a-1));
                                }
                                break;
                            }
                            case "n" -> {
                                break;
                            }
                            default -> {
                                Funciones.cls();
                                System.out.println("¡ERROR! Ingrese una opción correcta.");
                                Funciones.pause();
                            }
                        }
                    } while ("n".equals(opcYN) && "Y".equals(opcYN));

                    if ("Y".equals(opcYN)) {
                        break;
                    }
                }
            } else {
                Funciones.cls();
                System.out.println("    ¡ERROR! Ingrese una opción correcta.");
                Funciones.pause();
            }
        } while (true);
    }
    
    public static void verProductos(Proveedor pro){
        Funciones.cls();
        System.out.println(TITULO);
        System.out.println(pro.toString());
        System.out.println(SEPARADOR);
        for(Producto p : pro.getListaProductos()){
            System.out.println(p.toString());
        }
        Funciones.pause();
    }
    
    public static void agregarProveedor() throws IOException{
        InputStream inputStream = System.in;
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader br = new BufferedReader(reader);
        Scanner s = new Scanner(System.in);
        
        ProveedorControlador proc = new ProveedorControlador();
        Proveedor pro = new Proveedor();
        
        //Campo - Nombre
        do {
            Funciones.cls();
            System.out.print(TITULO);
            System.out.print("  Ingrese el nombre del proveedor: ");
            String nombres = br.readLine();
            if (Funciones.isValidText(nombres) == false) {
                Funciones.cls();
                System.out.println("    ¡ERROR! No puede ingresar caracteres "
                        + "especiales o números, ingrese el nombre nuevamente.");
                Funciones.pause();
            } else {
                String opcYN;
                do {
                    Funciones.cls();
                    pro.setNombre(nombres.toUpperCase());
                    System.out.println("Los nombres ingresados son: " + pro.getNombre());
                    System.out.print("¿Es correcto? [Y/n] ");
                    opcYN = s.next();
                    switch (opcYN) {
                        case "Y" -> {
                            break;
                        }
                        case "n" -> {
                            break;
                        }
                        default -> {
                            Funciones.cls();
                            System.out.println("¡ERROR! Ingrese una opción correcta.");
                            Funciones.pause();
                        }
                    }
                } while ("n".equals(opcYN) && "Y".equals(opcYN));

                if ("Y".equals(opcYN)) {
                    break;
                }
            }
        } while (true);
        
        //Campo - Cédula
        do {
            Funciones.cls();
            System.out.print(TITULO);
            System.out.print("  Ingrese la cédula del proveedor: ");
            String cedula = s.next();
            if (pro.validarCedula(cedula)) {
                if (proc.existeCuenta(cedula)) {
                    Funciones.cls();
                    System.out.println("""
                                        ¡ERROR! Ya existe el proveedor registrado en el sistema con esta cédula.
                                       """);
                    Funciones.pause();
                    main(null);
                    break;
                } else {
                    String opcYN;
                    do {
                        Funciones.cls();
                        pro.setCedula(cedula);
                        System.out.println("La cédula ingresada es: " + pro.getCedula());
                        System.out.print("¿Es correcto? [Y/n] ");
                        opcYN = s.next();
                        switch (opcYN) {
                            case "Y" -> {
                                break;
                            }
                            case "n" -> {
                                break;
                            }
                            default -> {
                                Funciones.cls();
                                System.out.println("¡ERROR! Ingrese una opción correcta.");
                                Funciones.pause();
                            }
                        }
                    } while ("n".equals(opcYN) && "Y".equals(opcYN));

                    if ("Y".equals(opcYN)) {
                        break;
                    }
                }
            } else {
                Funciones.cls();
                System.out.println("""
                                            ¡ERROR! Ingrese una cédula correcta.
                                       """);
                Funciones.pause();
            }
        } while (true);
        
        //Campo - Ciudad
        do {
            Funciones.cls();
            System.out.print(TITULO);
            System.out.print("  Ingrese la ciudad del proveedor: ");
            String ciudad = br.readLine();
            if (Funciones.isValidText(ciudad) == false) {
                Funciones.cls();
                System.out.println("    ¡ERROR! No puede ingresar caracteres "
                        + "especiales o números, ingrese la ciudad nuevamente.");
                Funciones.pause();
            } else {
                String opcYN;
                do {
                    Funciones.cls();
                    pro.setCiudad(ciudad.toUpperCase());
                    System.out.println("La ciudad ingresada son: " + pro.getCiudad());
                    System.out.print("¿Es correcto? [Y/n] ");
                    opcYN = s.next();
                    switch (opcYN) {
                        case "Y" -> {
                            break;
                        }
                        case "n" -> {
                            break;
                        }
                        default -> {
                            Funciones.cls();
                            System.out.println("¡ERROR! Ingrese una opción correcta.");
                            Funciones.pause();
                        }
                    }
                } while ("n".equals(opcYN) && "Y".equals(opcYN));

                if ("Y".equals(opcYN)) {
                    break;
                }
            }
        } while (true);
        
        //Campo - Teléfono
        do {
            Funciones.cls();
            System.out.print(TITULO);
            System.out.print("  Ingrese el teléfono del proveedor: ");
            String telefono = br.readLine();
            if (Funciones.isValidNumeric(telefono) == false) {
                Funciones.cls();
                System.out.println("    ¡ERROR! No puede ingresar caracteres "
                        + "especiales o letras, ingrese el teléfono nuevamente.");
                Funciones.pause();
            } else {
                if (telefono.length() == 10) {
                    String opcYN;
                    do {
                        Funciones.cls();
                        pro.setTelefono(telefono);
                        System.out.println("El teléfono ingresado es: " + pro.getTelefono());
                        System.out.print("¿Es correcto? [Y/n] ");
                        opcYN = s.next();
                        switch (opcYN) {
                            case "Y" -> {
                                break;
                            }
                            case "n" -> {
                                break;
                            }
                            default -> {
                                Funciones.cls();
                                System.out.println("¡ERROR! Ingrese una opción correcta.");
                                Funciones.pause();
                            }
                        }
                    } while ("n".equals(opcYN) && "Y".equals(opcYN));

                    if ("Y".equals(opcYN)) {
                        break;
                    }
                } else {
                    Funciones.cls();
                    System.out.println("    ¡ERROR! El teléfono ingresado es incorrecto. "
                            + " Ingrese su teléfono nuevamente.");
                    Funciones.pause();
                }
            }
        } while (true);
        
        //Campo - Email
        do {
            Funciones.cls();
            System.out.print(TITULO);
            System.out.print("  Ingrese el email del proveedor: ");
            String email = br.readLine();
            if (Funciones.isValidEmail(email) == false) {
                Funciones.cls();
                System.out.println("    ¡ERROR! No puede ingresar caracteres "
                        + "especiales, ingrese el email nuevamente.");
                Funciones.pause();
            } else {
                String opcYN;
                do {
                    Funciones.cls();
                    pro.setEmail(email);
                    System.out.println("El email ingresado es: " + pro.getEmail());
                    System.out.print("¿Es correcto? [Y/n] ");
                    opcYN = s.next();
                    switch (opcYN) {
                        case "Y" -> {
                            break;
                        }
                        case "n" -> {
                            break;
                        }
                        default -> {
                            Funciones.cls();
                            System.out.println("¡ERROR! Ingrese una opción correcta.");
                            Funciones.pause();
                        }
                    }
                } while ("n".equals(opcYN) && "Y".equals(opcYN));

                if ("Y".equals(opcYN)) {
                    break;
                }
            }
        } while (true);
        
        proc.crearProveedor(pro);
        int idProveedor = proc.buscarIdProveedor(pro.getCedula());
        String codigo = "SPT - " + (100000 + idProveedor);
        
        proc.editarCodigo(codigo, idProveedor);
    }
    
    public static void agregarProducto() throws IOException{
        InputStream inputStream = System.in;
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader br = new BufferedReader(reader);
        Scanner s = new Scanner(System.in);
        
        ProveedorControlador proc = new ProveedorControlador();
        ProductoControlador produc = new ProductoControlador();
        Proveedor pro =  new Proveedor();
        Producto produ =  new Producto();
        
        do {
            ArrayList<Proveedor> listadoProveedores = proc.listarProveedores();
            Funciones.cls();
            System.out.println(TITULO);
            System.out.print("     Seleccione el proveedor: \n");
            for (Proveedor p : listadoProveedores) {
                System.out.print("     "
                        + p.getIdProveedor() + ". "
                        + p.getNombre() + ".\n");
            }
            System.out.print("     Opcion: ");
            String opcCar = s.next();

            if (Funciones.isValidNumeric(opcCar)) {
                int a = Integer.parseInt(opcCar);
                if (a > listadoProveedores.size() || a < listadoProveedores.size()) {
                    Funciones.cls();
                    System.out.println("    ¡ERROR! Ingrese una opción correcta.");
                    Funciones.pause();
                } else {
                    String opcYN;
                    do {
                        Funciones.cls();
                        System.out.println(TITULO);
                        System.out.println("El proveedor seleccionado es: " + listadoProveedores.get(a-1).getNombre());
                        System.out.print("¿Es correcto? [Y/n] ");
                        opcYN = s.next();
                        switch (opcYN) {
                            case "Y" -> {
                                pro = listadoProveedores.get(a-1);
                                break;
                            }
                            case "n" -> {
                                break;
                            }
                            default -> {
                                Funciones.cls();
                                System.out.println("¡ERROR! Ingrese una opción correcta.");
                                Funciones.pause();
                            }
                        }
                    } while ("n".equals(opcYN) && "Y".equals(opcYN));

                    if ("Y".equals(opcYN)) {
                        break;
                    }
                }
            } else {
                Funciones.cls();
                System.out.println("    ¡ERROR! Ingrese una opción correcta.");
                Funciones.pause();
            }
        } while (true);
        
        //Campo - Nombre
        do {
            Funciones.cls();
            System.out.print(TITULO);
            System.out.print("  Ingrese el nombre del producto: ");
            String nombres = br.readLine();
            if (Funciones.isValidText(nombres) == false) {
                Funciones.cls();
                System.out.println("    ¡ERROR! No puede ingresar caracteres "
                        + "especiales o números, ingrese el nombre nuevamente.");
                Funciones.pause();
            } else {
                String opcYN;
                do {
                    Funciones.cls();
                    produ.setNombre(nombres.toUpperCase());
                    System.out.println("El nombre ingresado es: " + produ.getNombre());
                    System.out.print("¿Es correcto? [Y/n] ");
                    opcYN = s.next();
                    switch (opcYN) {
                        case "Y" -> {
                            break;
                        }
                        case "n" -> {
                            break;
                        }
                        default -> {
                            Funciones.cls();
                            System.out.println("¡ERROR! Ingrese una opción correcta.");
                            Funciones.pause();
                        }
                    }
                } while ("n".equals(opcYN) && "Y".equals(opcYN));

                if ("Y".equals(opcYN)) {
                    break;
                }
            }
        } while (true);
        
        //Campo - Tamanio
        do {
            Funciones.cls();
            System.out.print(TITULO);
            System.out.print("  Ingrese el tamaño del producto (cm): ");
            double tamanio = s.nextDouble();
            String opcYN;
            do {
                Funciones.cls();
                produ.setTamanio(tamanio);
                System.out.println("El tamaño ingresado es: " + produ.getTamanio());
                System.out.print("¿Es correcto? [Y/n] ");
                opcYN = s.next();
                switch (opcYN) {
                    case "Y" -> {
                        break;
                    }
                    case "n" -> {
                        break;
                    }
                    default -> {
                        Funciones.cls();
                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                        Funciones.pause();
                    }
                }
            } while ("n".equals(opcYN) && "Y".equals(opcYN));

            if ("Y".equals(opcYN)) {
                break;
            }
        } while (true);
        
        //Campo - Peso
        do {
            Funciones.cls();
            System.out.print(TITULO);
            System.out.print("  Ingrese el peso del producto (kg): ");
            double peso = s.nextDouble();
            String opcYN;
            do {
                Funciones.cls();
                produ.setPeso(peso);
                System.out.println("El peso ingresado es: " + produ.getPeso());
                System.out.print("¿Es correcto? [Y/n] ");
                opcYN = s.next();
                switch (opcYN) {
                    case "Y" -> {
                        break;
                    }
                    case "n" -> {
                        break;
                    }
                    default -> {
                        Funciones.cls();
                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                        Funciones.pause();
                    }
                }
            } while ("n".equals(opcYN) && "Y".equals(opcYN));

            if ("Y".equals(opcYN)) {
                break;
            }
        } while (true);
        
        //Campo - Precio
        do {
            Funciones.cls();
            System.out.print(TITULO);
            System.out.print("  Ingrese el precio, en dólares, del producto: ");
            double precio = s.nextDouble();
            String opcYN;
            do {
                Funciones.cls();
                produ.setPrecio(precio);
                System.out.println("El precio ingresado es: " + produ.getPrecio());
                System.out.print("¿Es correcto? [Y/n] ");
                opcYN = s.next();
                switch (opcYN) {
                    case "Y" -> {
                        break;
                    }
                    case "n" -> {
                        break;
                    }
                    default -> {
                        Funciones.cls();
                        System.out.println("¡ERROR! Ingrese una opción correcta.");
                        Funciones.pause();
                    }
                }
            } while ("n".equals(opcYN) && "Y".equals(opcYN));

            if ("Y".equals(opcYN)) {
                break;
            }
        } while (true);
        
        produc.crearProducto(produ, pro.getIdProveedor());
    }
}
