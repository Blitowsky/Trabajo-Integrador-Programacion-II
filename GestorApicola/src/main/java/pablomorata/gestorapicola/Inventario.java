/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

 /* CÓDIGO ÚTIL PARA VER ESTRUCTURAS O TENER DE REFERENCIA:

        //return !(parametro <= hasta && parametro >= desde);



 */
package pablomorata.gestorapicola;

import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author blitowsky
 *
 */
public class Inventario {

    Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    ArrayList<Object> consumibles;

    public Inventario() {
        consumibles = new ArrayList<>();
    }

    public int entreParametros(int parametro, int desde, int hasta) {

        //El return está pensado para que sea usado en un while(false)
        while (!(parametro <= hasta && parametro >= desde)) {

            System.out.println("Ingrese un número desde " + desde + " hasta " + hasta);
            parametro = scanner.nextInt();

        }
        return parametro;

    }

    public int intMayorA(int parametro, int mayorA) {

        while (parametro < mayorA) {

            System.out.println("Ingrese un número mayor a " + mayorA);
            parametro = scanner.nextInt();

        }
        return parametro;

    }
    
    public double doubleMayorA(double parametro, int mayorA) {

        while (parametro < mayorA) {

            System.out.println("Ingrese un número mayor a " + mayorA);
            parametro = scanner.nextDouble();

        }
        return parametro;

    }
}
