/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

 
package pablomorata.gestorapicola;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 *
 * @author blitowsky
 *
 */
public class Validador {

    static Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    ArrayList<Object> consumibles;

    public Validador() {
        consumibles = new ArrayList<>();
    }

    public static int entreParametros(int parametro, int desde, int hasta) {

        //El return está pensado para que sea usado en un while(false)
        while (!(parametro <= hasta && parametro >= desde)) {

            System.out.println("#ERROR 01# \nIngrese un número desde " + desde + " hasta " + hasta);
            parametro = scanner.nextInt();

        }
        return parametro;

    }

    public static int intMayorA(int parametro, int mayorA) {

        while (parametro < mayorA) {

            System.out.println("#ERROR 03# \nIngrese un número mayor a " + mayorA);
            parametro = scanner.nextInt();

        }
        return parametro;

    }

    public static double doubleMayorA(double parametro, int mayorA) {

        while (parametro < mayorA) {

            System.out.println("#ERROR 04# \nIngrese un número mayor a " + mayorA);
            parametro = scanner.nextDouble();

        }
        return parametro;

    }

    public static int errorPorCaracter() {
        boolean entradaAceptada = false;
        int entrada = 0;

        while (!entradaAceptada) {

            try {

                System.out.println("Por favor, ingrese un número");
                entrada = scanner.nextInt();
                entradaAceptada = true;

            } catch (InputMismatchException e) {

                System.out.println("#ERROR 02#");
                scanner.next();

            }

        }

        return entrada;

    }
    

}
