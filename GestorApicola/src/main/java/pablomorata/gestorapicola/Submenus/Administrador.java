/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola.Submenus;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import pablomorata.gestorapicola.Database;
import pablomorata.gestorapicola.Entidades.Colmena;
import pablomorata.gestorapicola.Entidades.Consumible;
import pablomorata.gestorapicola.Entidades.Producto;
import pablomorata.gestorapicola.Entidades.Usuario;
import pablomorata.gestorapicola.Validador;

/**
 *
 * @author blitowsky
 */

public class Administrador extends Usuario{

    static Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    int entrada;
    Colmena colmena = new Colmena();
    Producto producto;
    Consumible consumible;
    boolean salir = false;

    public Administrador(){       
    }
    
    public Administrador(String nombre, String ubicacion, int id, Date antiguedad, int prioridad){
        
        super(nombre, ubicacion, id, antiguedad, prioridad);
        colmena = new Colmena();
        producto = new Producto();
        consumible = new Consumible();
        
    }
    
    @Override
    public void selectorOpciones() {

        while (!salir) {

            System.out.println("Ingrese que área desea gestionar:");
            System.out.println(" 0: Cerrar el programa \n 1: Colmenas \n 2: Productos \n 3: Consumibles");

            try {
                entrada = scanner.nextInt();
                entrada = Validador.entreParametros(entrada, 0, 3);

            } catch (InputMismatchException e) {

                System.out.println("#ERROR 02# " + e.getMessage());
                Database.limpiarBuffer(scanner);
                entrada = Validador.errorPorCaracter();
                entrada = Validador.entreParametros(entrada, 0, 3);

            }

            switch (entrada) {

                case 0 ->
                    salir = true;
                case 1 ->
                    colmena.selectorOpciones();
                case 2 ->
                    producto.selectorOpciones();
                case 3 ->
                    consumible.selectorOpciones();

            }

        }

    }

}
