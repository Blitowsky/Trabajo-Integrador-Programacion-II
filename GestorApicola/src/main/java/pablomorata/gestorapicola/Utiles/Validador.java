/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola.Utiles;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 *
 * @author blitowsky
 *
 */
public class Validador {

    static Scanner scanner = new Scanner(System.in).useDelimiter("\n");


    public Validador() {
    }

    public static int entreParametros(int desde, int hasta) {
        int parametro;
        try {

            parametro = scanner.nextInt();

            while (!(parametro <= hasta && parametro >= desde)) {

                System.out.println("#ERROR 01# \nIngrese un número desde " + desde + " hasta " + hasta);
                parametro = scanner.nextInt();

            }
            return parametro;

        } catch (InputMismatchException e) {

            parametro = errorPorCaracter(desde, hasta);
            return parametro;

        }

    }

    public static int intMayorA(int mayorA) {
        int parametro;

        try {
            parametro = scanner.nextInt();
            while (parametro < mayorA) {

                System.out.println("#ERROR 03# \nIngrese un número mayor a " + mayorA);
                parametro = scanner.nextInt();

            }
            return parametro;

        } catch (InputMismatchException e) {

            parametro = errorPorCaracter(mayorA);
            return parametro;

        }

    }

    public static double doubleMayorA(int mayorA) {
        
        double parametro;
        
        try{
            
         parametro = scanner.nextInt();

        while (parametro < mayorA) {

            System.out.println("#ERROR 04# \nIngrese un número mayor a " + mayorA);
            parametro = scanner.nextDouble();

        }
        return parametro;   
            
        } catch (InputMismatchException e){
            
            parametro = errorPorCaracter(mayorA, false);
            return parametro;   
        }
        
    }

    public static int errorPorCaracter(int desde, int hasta) {
        
        boolean entradaAceptada = false;
        int entrada = 0;

        while (!entradaAceptada) {

            try {
                scanner.next();
                System.out.println("Por favor, ingrese un número");
                entrada = entreParametros(desde, hasta);
                entradaAceptada = true;

            } catch (InputMismatchException e) {

                System.out.println("#ERROR 02#");
                scanner.next();

            }

        }

        return entrada;

    }
     public static double errorPorCaracter(int mayorA, boolean ifDouble) {
        boolean entradaAceptada = false;
        double entrada = 0;

        while (!entradaAceptada) {

            try {
                scanner.next();

                System.out.println("Por favor, ingrese un número");
                entrada = doubleMayorA(mayorA);
                entradaAceptada = true;

            } catch (InputMismatchException e) {

                System.out.println("#ERROR 02#");
                scanner.next();

            }

        }

        return entrada;

    }
    
    public static int errorPorCaracter(int mayorA) {
        boolean entradaAceptada = false;
        int entrada = 0;

        while (!entradaAceptada) {

            try {
                scanner.next();

                System.out.println("Por favor, ingrese un número");
                entrada = intMayorA(mayorA);
                entradaAceptada = true;

            } catch (InputMismatchException e) {

                System.out.println("#ERROR 02#");
                scanner.next();

            }

        }

        return entrada;

    }
    

}
