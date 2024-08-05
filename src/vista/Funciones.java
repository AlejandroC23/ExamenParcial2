/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Alejandro
 */
public class Funciones {
    //Limpiar pantalla
    public static void cls() {
        for (int i = 0; i < 30; i++) {
            System.out.println("");
        }
    }
    
    public static void pause(){
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                // Comando para pausar en Windows
                new ProcessBuilder("cmd", "/c", "pause").inheritIO().start().waitFor();
            } else {
                System.out.println("El comando 'pause' no es compatible con este sistema operativo.");
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error al intentar pausar la consola: " + ex.getMessage());
        }
    }
    
    public static boolean isValidNumeric(String texto) {
        String regex = "^[0-9]+$";
        return texto.matches(regex);
    }
    
    public static boolean isValidText(String texto) {
        String regex = "^[a-zA-Z]+( [a-zA-Z]+)*$";
        return texto.matches(regex);
    }
    
    public static boolean isValidEmail(String texto) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(regex, texto);
    }
}
