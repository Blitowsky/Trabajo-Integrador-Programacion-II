/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola.Controllers;

import java.util.ArrayList;
import java.util.Scanner;
import pablomorata.gestorapicola.DAO.ConsumibleDAO;
import pablomorata.gestorapicola.DAO.GestorDAOs;
import pablomorata.gestorapicola.Entidades.Consumible;
import pablomorata.gestorapicola.Interfaces.IController;
import pablomorata.gestorapicola.Inventario;

/**
 *
 * @author blitowsky
 */
public class ConsumibleController implements IController {

    Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    Inventario inventario = new Inventario();
    Consumible consumible = new Consumible();
    ArrayList<Consumible> consumibles = new ArrayList<>();

    @Override
    public void selectorOpciones() {

        boolean salir = false;

        while (salir == false) {

            System.out.println("MENÚ:");
            System.out.println(" 1: Registrar consumible \n 2: agregar consumible a inventario \n 3: eliminar consumible de inventario ");
            System.out.println(" 4: Eliminar consumible 5:");
            System.out.println("Ingrese la opción del menú a elegir");
            int opcion = scanner.nextInt();
            inventario.entreParametros(opcion, 0, 5);

            switch (opcion) {
                
                case 0 -> salir = true;

                case 1 -> { //registrar producto

                    System.out.println("Ingrese el nombre del consumible");
                    String nombre = scanner.next();

                    System.out.println("Ingrese la cantidad inicial en inventario");
                    int cantidad = scanner.nextInt();
                    inventario.intMayorA(cantidad, 0);

                    System.out.println("Ingrese el precio inicial del consumible");
                    double precio = scanner.nextDouble();
                    inventario.doubleMayorA(precio, 0);

                    ConsumibleDAO.registrarConsumible(nombre, cantidad, precio);

                }

                case 2 -> { //agregar a inventario

                    System.out.println("Ingrese el nombre del consumible");
                    String nombre = scanner.next();

                    System.out.println("Ingrese la cantidad a agregar al inventario");
                    int cantidad = scanner.nextInt();
                    inventario.intMayorA(cantidad, 0);

                    ConsumibleDAO.agregarAInventario(nombre, cantidad);

                }
                case 3 -> { // eliminar de inventario

                    System.out.println("Ingrese el nombre del consumible");
                    String nombre = scanner.next();

                    System.out.println("Ingrese la cantidad a agregar al inventario");
                    int cantidad = scanner.nextInt();
                    inventario.intMayorA(cantidad, 0);

                    ConsumibleDAO.eliminarDelInventario(nombre, cantidad);

                }

                case 4 -> { //eliminar de registro

                    System.out.println("Ingrese el nombre del consumible");
                    String nombre = scanner.next();

                    ConsumibleDAO.eliminarDeRegistro(nombre);

                }
                case 5 -> {

                    GestorDAOs.reasignarId(consumible.getNombreEntidad());

                    int id = 1;

                    while (ConsumibleDAO.traerConsumibles(id) != null) {

                        consumibles.add(ConsumibleDAO.traerConsumibles(id));
                        id++;

                    }

                    System.out.println("-------------------------------");

                    for (Consumible puntero : consumibles) {

                        System.out.println("id: " + puntero.getId());
                        System.out.println("nombre: " + puntero.getNombre());
                        System.out.println("cantidad: " + puntero.getCantidad());
                        System.out.println("precio: " + puntero.getPrecio());
                        System.out.println("-------------------------------");

                    }

                }

            }

        }

    }

}
