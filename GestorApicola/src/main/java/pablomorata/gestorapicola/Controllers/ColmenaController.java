/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola.Controllers;

import java.util.ArrayList;
import java.util.Scanner;
import pablomorata.gestorapicola.Entidades.Colmena;
import pablomorata.gestorapicola.DAO.ColmenaDAO;
import pablomorata.gestorapicola.DAO.GestorDAOs;
import pablomorata.gestorapicola.Database;
import pablomorata.gestorapicola.Interfaces.IController;
import pablomorata.gestorapicola.Inventario;

/**
 *
 * @author blitowsky
 */
public class ColmenaController implements IController{

    Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    Inventario inventario = new Inventario();
    Colmena colmena = new Colmena();
    ArrayList<Colmena> colmenas = new ArrayList<>();

    int opcion;

    @Override
    public void selectorOpciones() {
        boolean salirMenu = false;

        while (salirMenu == false) {

            System.out.println("--- MENÚ ----");
            System.out.println("  1: Agregar \n  2: Eliminar \n  3: Modificar \n -1: Mostrar \n  0: salir \n -2: test");
                        System.out.println("Ingrese la opción del menú a elegir");

            opcion = scanner.nextInt();
            Database.disconnect();

            switch (opcion) {

                case 1: //agregar colmena

                    System.out.println("La colmena posee abejas? 1: SI | 2: NO");
                    int hayAbejas = scanner.nextInt();
                    hayAbejas = inventario.entreParametros(hayAbejas, 1, 2);
                    boolean abejas = false;

                    if (hayAbejas == 1) {
                        abejas = true;
                    }

                    System.out.println("Ingrese el nivel de miel de la colmena (0: sin miel / 10: completamente llena)");
                    int miel = scanner.nextInt();
                    miel = inventario.entreParametros(miel, 0, 10);

                    System.out.println("Ingrese la cantidad de marcos de la colmena");
                    int marcos = scanner.nextInt();
                    marcos = inventario.entreParametros(marcos, 0, 10);

                    System.out.println("Ingrese el estado de la colmena");
                    String estado = scanner.next();

                    ColmenaDAO.agregarColmena(abejas, miel, marcos, estado);

                    break;

                case 2: //eliminar colmena

                    System.out.println("Ingrese el id de la colmena a eliminar");
                    int id = scanner.nextInt();
                    ColmenaDAO.eliminarColmena(id);
                    GestorDAOs.reasignarId(colmena.getNombreEntidad());
                    break;

                case 3: //modificar atributos de la colmena

                    System.out.println("Ingrese el id de la colmena a modificar");
                    id = scanner.nextInt();
                    boolean salirModificar = false;
                    int entrada;
                    String columna;
                    int intAuxiliar;

                    String nuevoValor;

                    do {
                        System.out.println("¿Qué atributo quiere modificar?");
                        System.out.println("1: Abejas \n 2: Miel \n 3: Marcos \n 4: Estado");
                        System.out.println("Para salir, ingrese 0");
                        entrada = scanner.nextInt();

                        inventario.entreParametros(entrada, 0, 4);

                        switch (entrada) {

                            case 1 -> {
                                columna = "abejas";
                                System.out.println("Ingrese si la colmena posee abejas");
                                intAuxiliar = scanner.nextInt();
                                inventario.entreParametros(intAuxiliar, 1, 2);
                                nuevoValor = String.valueOf(intAuxiliar);

                                ColmenaDAO.modificarColmena(id, columna, nuevoValor);
                            }

                            case 2 -> {

                                columna = "miel";

                                System.out.println("Ingrese el nivel de Miel");
                                intAuxiliar = scanner.nextInt();
                                inventario.entreParametros(intAuxiliar, 0, 10);

                                nuevoValor = String.valueOf(intAuxiliar);
                                ColmenaDAO.modificarColmena(id, columna, nuevoValor);
                            }

                            case 3 -> {
                                columna = "marcos";

                                System.out.println("Ingrese la cantidad de marcos");
                                intAuxiliar = scanner.nextInt();
                                inventario.entreParametros(intAuxiliar, 0, 10);
                                nuevoValor = String.valueOf(intAuxiliar);
                                ColmenaDAO.modificarColmena(id, columna, nuevoValor);
                            }

                            case 4 -> {
                                columna = "estado";
                                System.out.println("Ingrese el estado de la colmena");
                                nuevoValor = scanner.next();
                                ColmenaDAO.modificarColmena(id, columna, nuevoValor);
                            }
                            case 0 ->
                                salirModificar = true;

                        }

                    } while (salirModificar == false);

                    break;

                case -1:

                    GestorDAOs.reasignarId(colmena.getNombreEntidad());
                    
                     id = 1;

                    while (ColmenaDAO.traerColmenas(id) != null) {

                        colmenas.add(ColmenaDAO.traerColmenas(id));
                        id++;

                    }
                    
                    System.out.println("-------------------------------");

                    for (Colmena puntero : colmenas) {

                        System.out.println("ID: " + puntero.getid());
                        System.out.println("Posee abejas?:" + puntero.isAbejas());
                        System.out.println("Nivel de miel: " + puntero.getCantMiel());
                        System.out.println("Cantidad de marcos: " + puntero.getCantMarcos());
                        System.out.println("Estado: " + puntero.getEstadoColmena());
                        System.out.println("-------------------------------");

                    }
                    colmenas.clear();

                    break;

                case 0:
                    salirMenu = true;

                case -2:
                //case pruebas

            }

        }

    }

}
