/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola.Entidades;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import pablomorata.gestorapicola.DAO.ConsumibleDAO;
import pablomorata.gestorapicola.DAO.GestorDAOs;
import pablomorata.gestorapicola.Database;
import pablomorata.gestorapicola.Abstractas.Objeto;
import pablomorata.gestorapicola.Validador;

/**
 *
 * @author blitowsky
 */
public class Consumible extends Objeto{
    
    private int id;
    private String nombre;
    private int cantidad;
    private double precio;
    static Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    Consumible consumible;
    ArrayList<Consumible> consumibles = new ArrayList<>();
    ConsumibleDAO consumibleDAO;

   
    public Consumible(){
        
        consumibleDAO = new ConsumibleDAO();
        
    }
    
    public Consumible(int id, String nombre, int cantidad, double precio,int peso, int prioridad, String utilidad){
        
        super("consumible",utilidad, prioridad, peso);
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }
     public void selectorOpciones() {

        boolean salir = false;
        int opcion;

        while (!salir) {

            try {

                System.out.println("MENÚ:");
                System.out.println(" 1: Registrar consumible \n 2: Agregar consumible a inventario \n 3: Eliminar consumible de inventario ");
                System.out.println(" 4: Modificar consumible \n 5: Eliminar consumible \n 6: Ver lista de consumibles");
                System.out.println("Ingrese la opción del menú a elegir");
                opcion = scanner.nextInt();
                Validador.entreParametros(opcion, 0, 6);

            } catch (InputMismatchException e) {

                System.out.println("#ERROR# " + e.getMessage());
                Database.limpiarBuffer(scanner);
                opcion = Validador.errorPorCaracter();
                Validador.entreParametros(opcion, 0, 6);


            }

            switch (opcion) {

                case 0 ->
                    salir = true;

                case 1 -> { //registrar producto

                    System.out.println("Ingrese el nombre del consumible");
                    String nombre = scanner.next();

                    System.out.println("Ingrese la cantidad inicial en inventario");
                    int cantidad = scanner.nextInt();
                    Validador.intMayorA(cantidad, 0);

                    System.out.println("Ingrese el precio inicial del consumible");
                    double precio = scanner.nextDouble();
                    Validador.doubleMayorA(precio, 0);
                    
                    System.out.println("Ingrese el peso del consumible");
                    int peso = scanner.nextInt();
                    peso = Validador.entreParametros(peso, 0, 10);
                    
                    System.out.println("Ingrese la utilidad del consumible");
                    String utilidad = scanner.next();
                    
                    System.out.println("Ingrese la prioridad del consumible (0: poco relevante / 5: muy relevante)");
                    int prioridad = scanner.nextInt();
                    prioridad = Validador.entreParametros(prioridad, 0, 5);

                    consumibleDAO.registrarConsumible(nombre, cantidad, precio,peso,prioridad,utilidad);
                    System.out.println("Al consumible registrado se le asignó la id:" + GestorDAOs.devolverId(nombre, "consumible"));

                }

                case 2 -> { //agregar a Validador

                    System.out.println("Ingrese el nombre del consumible");
                    String nombre = scanner.next();

                    System.out.println("Ingrese la cantidad a agregar al inventario");
                    int cantidad = scanner.nextInt();
                    Validador.intMayorA(cantidad, 0);

                    consumibleDAO.agregarAInventario(nombre, cantidad);

                }
                case 3 -> { // eliminar de Validador

                    System.out.println("Ingrese el nombre del consumible");
                    String nombre = scanner.next();

                    System.out.println("Ingrese la cantidad a agregar al inventario");
                    int cantidad = scanner.nextInt();
                    cantidad = Validador.intMayorA(cantidad, 0);

                    consumibleDAO.eliminarDelInventario(nombre, cantidad);

                }
                case 4 -> { //moidificar atributos

                    String columna;
                    int id;

                    while (!salir) {

                        System.out.println("Ingrese el atributo que desea modificar");
                        columna = scanner.next();

                        try {

                            System.out.println("Ingrese la id del consumible");
                            id = scanner.nextInt();
                            id = Validador.intMayorA(id, 0);

                        } catch (InputMismatchException e) {
                            
                            System.out.println("#ERROR# " + e.getMessage());
                            Database.limpiarBuffer(scanner);
                            id = Validador.errorPorCaracter();
                                                        id = Validador.intMayorA(id, 0);

                        }

                        System.out.println("Ingrese el nuevo valor");
                        String nuevoValor = scanner.next();
                        nuevoValor = String.valueOf(Validador.intMayorA(Integer.parseInt(nuevoValor), 0));

                        consumibleDAO.modificarConsumible(id, columna, nuevoValor);

                    }

                    salir = false;

                }

                case 5 -> { //eliminar de registro

                    System.out.println("Ingrese el nombre del consumible");
                    String nombre = scanner.next();

                    consumibleDAO.eliminarDeRegistro(nombre);

                }
                case 6 -> {


                    int id = 1;

                    while (consumibleDAO.traerConsumibles(id) != null) {

                        consumibles.add(consumibleDAO.traerConsumibles(id));
                        id++;

                    }

                    System.out.println("-------------------------------");

                    for (Consumible puntero : consumibles) {
                        

                        System.out.println("id: " + puntero.getId());
                        System.out.println("nombre: " + puntero.getNombre());
                        System.out.println("cantidad: " + puntero.getCantidad());
                        System.out.println("precio: " + puntero.getPrecio());
                        System.out.println("Peso: " + puntero.getPeso());
                        System.out.println("Utilidad: " + puntero.getUtilidad());
                        System.out.println("Prioridad: " + puntero.getPrioridad());
                        System.out.println("-------------------------------");

                    }

                }

            }

        }

    }

    public int getId() {
        return id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
}
