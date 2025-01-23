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
import pablomorata.gestorapicola.DAO.ColmenaDAO;

/**
 *
 * @author blitowsky
 *
 */
public class Inventario {

    Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    
    public Inventario(){
        
        
        
    }



    public int entreParametros(int parametro, int desde, int hasta) {

        //El return está pensado para que sea usado en un while(false)
        while (!(parametro <= hasta && parametro >= desde)) {

            System.out.println("Ingrese un número desde " + desde + "hasta " + hasta);
            parametro = scanner.nextInt();

        }
        return parametro;

    }
    

}
