/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola.Entidades;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import pablomorata.gestorapicola.DAO.ColmenaDAO;
import pablomorata.gestorapicola.DAO.GestorDAOs;
import pablomorata.gestorapicola.Database;
import pablomorata.gestorapicola.Abstractas.Objeto;
import pablomorata.gestorapicola.Validador;

/**
 *
 * @author blitowsky
 *
 */
public class Colmena extends Objeto {

    static Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    Colmena colmena;
    ArrayList<Colmena> colmenas = new ArrayList<>();
    ColmenaDAO colmenaDAO;
    int opcion;
    private int id;
    private boolean abejas;
    private int cantMiel;
    private int cantMarcos;
    private String observaciones;
    
    public Colmena(){
        
        colmenaDAO = new ColmenaDAO();

    }
            
            
            

    public Colmena(int id, boolean abejas, int cantMiel, int cantMarcos, String estadoColmena,int peso, int prioridad, String utilidad) {
        
        super("colmena",utilidad, prioridad, peso);
        this.id = id;
        this.abejas = abejas;
        this.cantMarcos = cantMarcos;
        this.cantMiel = cantMiel;
        this.observaciones = estadoColmena;

    }
    public void selectorOpciones() {
        boolean salirMenu = false;

        while (!salirMenu) {

            try {

                System.out.println("--- MENÚ ----");
                System.out.println(" 0: test \n  1: Agregar \n  2: Eliminar \n  3: Modificar \n 4: Mostrar \n  5: salir");
                System.out.println("Ingrese la opción del menú a elegir");

                opcion = scanner.nextInt();
                Validador.entreParametros(opcion, 1, 5);

            } catch (InputMismatchException e) {

                System.err.println("#ERROR# \n Caracter inválido");
                Database.limpiarBuffer(scanner);
                opcion = Validador.errorPorCaracter();
                Validador.entreParametros(opcion, 1, 5);

            }

            Database.disconnect();

            switch (opcion) {

                case 1: //agregar colmena

                    System.out.println("La colmena posee abejas? 1: SI | 2: NO");
                    int hayAbejas = scanner.nextInt();
                    hayAbejas = Validador.entreParametros(hayAbejas, 1, 2);
                    boolean abejas = false;

                    if (hayAbejas == 1) {
                        abejas = true;
                    }

                    System.out.println("Ingrese el nivel de miel de la colmena (0: sin miel / 10: completamente llena)");
                    int miel = scanner.nextInt();
                    miel = Validador.entreParametros(miel, 0, 10);

                    System.out.println("Ingrese la cantidad de marcos de la colmena");
                    int marcos = scanner.nextInt();
                    marcos = Validador.entreParametros(marcos, 0, 10);
                    
                    System.out.println("Ingrese el peso de la colmena");
                    int peso = scanner.nextInt();
                    peso = Validador.entreParametros(peso, 0, 10);
                    
                    System.out.println("Ingrese la función de la colmena");
                    String utilidad = scanner.next();
                    
                    System.out.println("Ingrese la prioridad de la colmena (0: poco relevante / 5: muy relevante)");
                    int prioridad = scanner.nextInt();
                    prioridad = Validador.entreParametros(prioridad, 0, 5);

                    System.out.println("Observaciones");
                    String observaciones = scanner.next();

                    colmenaDAO.agregarColmena(abejas, miel, marcos, observaciones, peso, utilidad, prioridad);

                    break;


                case 2: //eliminar colmena

                    System.out.println("Ingrese el id de la colmena a eliminar");
                    int id = scanner.nextInt();
                    colmenaDAO.eliminarColmena(id);
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

                        try {

                            System.out.println("¿Qué atributo quiere modificar?");
                            System.out.println("1: Abejas \n 2: Miel \n 3: Marcos \n 4: Estado");
                            System.out.println("Para salir, ingrese 0");
                            entrada = scanner.nextInt();

                            Validador.entreParametros(entrada, 0, 4);

                        } catch (InputMismatchException e) {

                            System.err.println("#ERROR 02# ---> Caracter inválido");
                            Database.limpiarBuffer(scanner);
                            entrada = Validador.errorPorCaracter();
                            Validador.entreParametros(entrada, 0, 4);

                        }

                        switch (entrada) {

                            case 1 -> {
                                columna = "abejas";
                                System.out.println("Ingrese si la colmena posee abejas");
                                intAuxiliar = scanner.nextInt();
                                Validador.entreParametros(intAuxiliar, 1, 2);
                                nuevoValor = String.valueOf(intAuxiliar);

                                colmenaDAO.modificarColmena(id, columna, nuevoValor);
                            }

                            case 2 -> {

                                columna = "miel";

                                System.out.println("Ingrese el nivel de Miel");
                                intAuxiliar = scanner.nextInt();
                                Validador.entreParametros(intAuxiliar, 0, 10);

                                nuevoValor = String.valueOf(intAuxiliar);
                                colmenaDAO.modificarColmena(id, columna, nuevoValor);
                            }

                            case 3 -> {
                                columna = "marcos";

                                System.out.println("Ingrese la cantidad de marcos");
                                intAuxiliar = scanner.nextInt();
                                Validador.entreParametros(intAuxiliar, 0, 10);
                                nuevoValor = String.valueOf(intAuxiliar);
                                colmenaDAO.modificarColmena(id, columna, nuevoValor);
                            }

                            case 4 -> {
                                columna = "estado";
                                System.out.println("Ingrese el estado de la colmena");
                                nuevoValor = scanner.next();
                                colmenaDAO.modificarColmena(id, columna, nuevoValor);
                            }
                            case 0 ->
                                salirModificar = true;

                        }

                    } while (!salirModificar);

                    break;

                case 4:


                    id = 1;

                    while (colmenaDAO.traerColmenas(id) != null) {

                        colmenas.add(colmenaDAO.traerColmenas(id));
                        id++;

                    }
                    

                    System.out.println("-------------------------------");

                    for (Colmena puntero : colmenas) {
                        System.out.println("ID: " + puntero.getid());
                        System.out.println("Posee abejas?:" + puntero.isAbejas());
                        System.out.println("Nivel de miel: " + puntero.getCantMiel());
                        System.out.println("Cantidad de marcos: " + puntero.getCantMarcos());
                        System.out.println("Estado: " + puntero.getEstadoColmena());
                        System.out.println("Peso: " + puntero.getPeso());
                        System.out.println("Utilidad: " + puntero.getUtilidad());
                        System.out.println("Prioridad: " + puntero.getPrioridad());
                        System.out.println("-------------------------------");

                    }
                    colmenas.clear();

                    break;

                case 5:
                    salirMenu = true;
                    Database.limpiarBuffer(scanner);
                    break;

                case 0:
                //case pruebas

            }

        }

    }
    

    public int getid() {

        return id;

    }

    public boolean isAbejas() {
        return abejas;
    }

    public int getCantMiel() {
        return cantMiel;
    }

    public int getCantMarcos() {
        return cantMarcos;
    }

    public String getEstadoColmena() {
        return observaciones;
    }

    public void setAbejas(boolean abejas) {
        this.abejas = abejas;
    }

    public void setCantMiel(int cantMiel) {
        this.cantMiel = cantMiel;
    }

    public void setCantMarcos(int cantMarcos) {
        this.cantMarcos = cantMarcos;
    }

    public void setEstadoColmena(String estadoColmena) {
        this.observaciones = estadoColmena;

    }

}
