/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import pablomorata.gestorapicola.DAO.ConsumibleDAO;
import pablomorata.gestorapicola.DAO.GestorDAOs;
import pablomorata.gestorapicola.DAO.Database;
import pablomorata.gestorapicola.Abstractas.Objeto;
import pablomorata.gestorapicola.Interfaces.CRUDInterface;
import pablomorata.gestorapicola.Utiles.Validador;

/**
 *
 * @author blitowsky
 */
public class Consumible extends Objeto implements CRUDInterface {

    private int id;
    private String nombre;
    private int cantidad;
    private double precio;
    static Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    ArrayList<Consumible> consumibles = new ArrayList<>();
    ConsumibleDAO consumibleDAO;

    public Consumible() {

        consumibleDAO = new ConsumibleDAO();

    }

    public Consumible(int id, String nombre, int cantidad, double precio, int peso, int prioridad, String utilidad) {

        super("consumible", utilidad, prioridad, peso);
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    @Override
    public void selectorOpciones() {

        boolean salir = false;
        int opcion;

        while (!salir) {
System.out.println("MENÚ:");
                System.out.println(" 1: Registrar consumible \n 2: Agregar consumible a inventario \n 3: Eliminar consumible de inventario ");
                System.out.println(" 4: Modificar consumible \n 5: Eliminar consumible \n 6: Ver lista de consumibles");
                System.out.println("Ingrese la opción del menú a elegir");
                opcion = Validador.entreParametros(0, 6);
       

            switch (opcion) {

                case 0 ->
                    salir = true;


                case 1 ->
                    registrar();

                case 2 ->
                    agregarAInventario();

                case 3 ->
                    eliminarDeInventario();

                case 4 ->
                    modificar();

                case 5 ->
                    eliminar();

                case 6 ->
                    mostrar();
            }

        }

    }

    @Override
    public void registrar() {

        System.out.println("Ingrese el nombre del consumible");
        nombre = scanner.next();

        System.out.println("Ingrese la cantidad inicial en inventario");
        cantidad = Validador.intMayorA(0);

        System.out.println("Ingrese el precio inicial del consumible");
        precio = Validador.doubleMayorA(0);

        System.out.println("Ingrese el peso del consumible");
        int peso = Validador.entreParametros(0, 10);

        System.out.println("Ingrese la utilidad del consumible");
        String utilidad = scanner.next();

        System.out.println("Ingrese la prioridad del consumible (0: poco relevante / 5: muy relevante)");
        int prioridad = Validador.entreParametros(0, 5);

        consumibleDAO.registrarConsumible(nombre, cantidad, precio, peso, prioridad, utilidad);
        System.out.println("Al consumible registrado se le asignó la id:" + GestorDAOs.devolverId(nombre, "consumible"));

    }

    @Override
    public void agregarAInventario() {

        System.out.println("Ingrese el nombre del consumible");
        nombre = scanner.next();

        System.out.println("Ingrese la cantidad a agregar al inventario");
        cantidad = Validador.intMayorA(0);

        consumibleDAO.agregarAInventario(nombre, cantidad);

    }

    @Override

    public void eliminarDeInventario() {

        System.out.println("Ingrese el nombre del consumible");
        nombre = scanner.next();

        System.out.println("Ingrese la cantidad a agregar al inventario");
        cantidad = Validador.intMayorA(0);

        consumibleDAO.eliminarDelInventario(nombre, cantidad);

    }

    @Override
    public void eliminar() {

        System.out.println("Ingrese el nombre del consumible");
        nombre = scanner.next();

        consumibleDAO.eliminarDeRegistro(nombre);
        GestorDAOs.reasignarId("Consumible");

    }

    @Override
    public void modificar() {

        String columna;
        boolean salir = false;

        while (!salir) {

            System.out.println("Ingrese el atributo que desea modificar");
            columna = scanner.next();
            System.out.println("Ingrese la id del consumible");
            id = Validador.intMayorA(0);

            System.out.println("Ingrese el nuevo valor");
            int nuevoValor = Validador.intMayorA(0);

            consumibleDAO.modificarConsumible(id, columna, String.valueOf(nuevoValor));
            
            ///ERROR GRAVE, BUCLE SIN SALIDA
            ///hay que validar que exista el atributo en la tabla, si no lo encuentra queda
            ///atrapado en el bucle de preguntar id y el nuevo valor
            ///POR OTRO LADO NO HAY CONDICION DE SALIDA DEL WHILE
                

        }

    }

    @Override
    public void mostrar() {

        id = 1;

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
