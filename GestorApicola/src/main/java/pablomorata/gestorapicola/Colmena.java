/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola;

import java.util.ArrayList;
import java.util.Scanner;
import pablomorata.gestorapicola.DAO.ColmenaDAO;
import pablomorata.gestorapicola.DAO.GestorDAOs;
import pablomorata.gestorapicola.DAO.Database;
import pablomorata.gestorapicola.Abstractas.Objeto;
import pablomorata.gestorapicola.Interfaces.CRUDInterface;
import pablomorata.gestorapicola.Utiles.Validador;

/**
 *
 * @author blitowsky
 *
 */
public class Colmena extends Objeto implements CRUDInterface {

    static Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    
    ArrayList<Colmena> colmenas = new ArrayList<>();
    ColmenaDAO colmenaDAO;
    int opcion;
    private int id;
    private boolean abejas;
    private int cantMiel;
    private int cantMarcos;
    private String observaciones;

    public Colmena() {

        colmenaDAO = new ColmenaDAO();

    }

    public Colmena(int id, boolean abejas, int cantMiel, int cantMarcos, String estadoColmena, int peso, int prioridad, String utilidad) {

        super("colmena", utilidad, prioridad, peso);
        this.id = id;
        this.abejas = abejas;
        this.cantMarcos = cantMarcos;
        this.cantMiel = cantMiel;
        this.observaciones = estadoColmena;

    }

    @Override
    public void selectorOpciones() {
        
        boolean salirMenu = false;
        
        while (!salirMenu) {
            System.out.println("--- MENÚ ----");
            System.out.println(" 0: salir \n  1: Agregar \n  2: Eliminar \n  3: Modificar \n 4: Mostrar \n  5: test");
            System.out.println("Ingrese la opción del menú a elegir");

            opcion = Validador.entreParametros(0, 5);

            switch (opcion) {

                case 1 -> agregarAInventario();

                case 2 -> eliminarDeInventario();

                case 3 -> modificar();

                case 4 -> mostrar();

                case 5 -> System.out.println(GestorDAOs.obtenerInt("marcos", "Colmena", 1)); //case pruebas
                

                case 0 ->{
                    salirMenu = true;
                    Database.limpiarBuffer(scanner);                    
            
                }
            }
        }
    }

    @Override
    public void agregarAInventario() {

        System.out.println("La colmena posee abejas? 1: SI | 2: NO");
        int hayAbejas = Validador.entreParametros(1, 2);
        abejas = false;

        if (hayAbejas == 1) {
            abejas = true;
        }

        System.out.println("Ingrese el nivel de miel de la colmena (0: sin miel / 10: completamente llena)");
        int miel = Validador.entreParametros(0, 10);

        System.out.println("Ingrese la cantidad de marcos de la colmena");
        int marcos = Validador.entreParametros(0, 10);

        System.out.println("Ingrese el peso de la colmena");
        int peso = Validador.entreParametros(0, 10);

        System.out.println("Ingrese la función de la colmena");
        String utilidad = scanner.next();

        System.out.println("Ingrese la prioridad de la colmena (0: poco relevante / 5: muy relevante)");
        int prioridad = Validador.entreParametros(0, 5);

        System.out.println("Observaciones");
        observaciones = scanner.next();

        colmenaDAO.agregarColmena(abejas, miel, marcos, observaciones, peso, utilidad, prioridad);

    }

    @Override
    public void eliminarDeInventario() {

        System.out.println("Ingrese el id de la colmena a eliminar");
        id = Validador.intMayorA(1);
        colmenaDAO.eliminarColmena(id);

    }

    @Override

    public void modificar() {

        System.out.println("Ingrese el id de la colmena a modificar");
        id = Validador.intMayorA(1);
        
        
       
        boolean salirModificar = false;
        int entrada;
        String columna;
        int intAuxiliar;
        String nuevoValor;
        do {

            System.out.println("¿Qué atributo quiere modificar?");
            System.out.println("1: Abejas \n 2: Miel \n 3: Marcos \n 4: Estado");
            System.out.println("Para salir, ingrese 0");
            entrada = Validador.entreParametros(0, 4);

            switch (entrada) {

                case 1 -> {
                    
                    columna = "abejas";
                    System.out.println("Ingrese si la colmena posee abejas");
                    intAuxiliar = Validador.entreParametros(1, 2);
                    nuevoValor = String.valueOf(intAuxiliar);

                    colmenaDAO.modificarColmena(id, columna, nuevoValor);
                }

                case 2 -> {

                    columna = "miel";

                    System.out.println("Ingrese el nivel de Miel");
                    intAuxiliar = Validador.entreParametros(0, 10);

                    nuevoValor = String.valueOf(intAuxiliar);
                    colmenaDAO.modificarColmena(id, columna, nuevoValor);
                }

                case 3 -> {
                    columna = "marcos";

                    System.out.println("Ingrese la cantidad de marcos");
                    intAuxiliar = Validador.entreParametros(0, 10);
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

    }

    @Override

    public void mostrar() {

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

    @Override
    public void registrar() {
    }

    @Override
    public void eliminar() {
    }

}
