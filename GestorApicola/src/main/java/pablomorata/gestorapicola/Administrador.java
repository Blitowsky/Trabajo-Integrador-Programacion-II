/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import pablomorata.gestorapicola.DAO.AdministradorDAO;
import pablomorata.gestorapicola.DAO.Database;
import pablomorata.gestorapicola.Colmena;
import pablomorata.gestorapicola.Consumible;
import pablomorata.gestorapicola.Producto;
import pablomorata.gestorapicola.Usuario;
import pablomorata.gestorapicola.Utiles.Validador;

/**
 *
 * @author blitowsky
 */
public class Administrador extends Usuario {

    static Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    int entrada;
    Colmena colmena = new Colmena();
    Producto producto;
    Consumible consumible;
    boolean salir = false;

    public Administrador() {
        colmena = new Colmena();
        producto = new Producto();
        consumible = new Consumible();

    }

    public Administrador(String nombre, String ubicacion, int id, Date antiguedad, int prioridad) {

        super(nombre, id, antiguedad, prioridad);
        colmena = new Colmena();
        producto = new Producto();
        consumible = new Consumible();

    }

    @Override
    public void selectorOpciones() {

        while (!salir) {

            System.out.println("Ingrese que área desea administrar:");
            System.out.println(" 0: Cerrar el programa \n 1: Colmenas \n 2: Productos \n 3: Consumibles");
            entrada = Validador.entreParametros(0, 3);

            switch (entrada) {

                case 0 ->
                    salir = true;
                case 1 ->
                    colmena.selectorOpciones();
                case 2 ->
                    producto.selectorOpciones();
                case 3 ->
                    consumible.selectorOpciones();
                case 4 -> {
                }

            }

        }

    }

}
