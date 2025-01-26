/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola;

import java.util.Scanner;
import pablomorata.gestorapicola.Controllers.ColmenaController;
import pablomorata.gestorapicola.Controllers.ConsumibleController;
import pablomorata.gestorapicola.Controllers.ProductoController;

/**
 *
 * @author blitowsky
 */
public class Menu {

    Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    private final ColmenaController colmenaController = new ColmenaController();
    private final ProductoController productoController = new ProductoController();
    private final ConsumibleController consumibleController = new ConsumibleController();
    private final Inventario inventario = new Inventario();

    int entrada;
    boolean salir = false;


    public void elegirAcciones() {

        while (salir == false) {

            System.out.println("Ingrese que área desea gestionar:");
            System.out.println(" 0: Cerrar el programa \n 1: Colmenas \n 2: Productos \n 3: Consumibles");

            entrada = scanner.nextInt();
            entrada = inventario.entreParametros(entrada, 0, 3);

            switch (entrada) {

                case 0 -> salir = true;
                case 1 -> colmenaController.selectorOpciones();
                case 2 -> productoController.selectorOpciones();
                case 3 -> consumibleController.selectorOpciones();
                

            }

        }

    }

}
