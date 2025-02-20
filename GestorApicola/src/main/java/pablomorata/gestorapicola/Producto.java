/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import pablomorata.gestorapicola.DAO.GestorDAOs;
import pablomorata.gestorapicola.DAO.ProductoDAO;
import pablomorata.gestorapicola.DAO.Database;
import pablomorata.gestorapicola.Abstractas.Objeto;
import pablomorata.gestorapicola.Utiles.Validador;

/**
 *
 * @author blitowsky
 */
public class Producto extends Objeto {

    private int id;
    private String nombre;
    private int cantidad;
    private double precio;
    static Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    Producto producto;
    ArrayList<Producto> productos = new ArrayList<>();

    ProductoDAO productoDAO;

    public Producto() {

        productoDAO = new ProductoDAO();

    }

    public Producto(int id, String nombre, int cantidad, double precio, int peso, int prioridad, String utilidad) {

        super("producto", utilidad, prioridad, peso);
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

                System.out.println("---- MENÚ ----");
                System.out.println(" 0: Salir \n 1: Registrar nuevo producto \n 2: Agregar a inventario \n 3: Eliminar de inventario");
                System.out.println(" 4: Eliminar producto \n 5: Modificar producto \n 6: Mostrar productos en inventario");
                System.out.println("Ingrese la acción del menú a elegir ");

                opcion = Validador.entreParametros(0, 6);

            } catch (InputMismatchException e) {

                System.err.println("#ERROR# \n Caracter inválido");
                Database.limpiarBuffer(scanner);
                opcion = Validador.entreParametros(0, 6);

            }

            switch (opcion) {

                case 0 -> salir = true;

                case 1 -> registrar();

                case 2 -> agregarAInventario();
                
                case 3 -> eliminarDeInventario();

                case 4 -> eliminar();

                case 5 -> modificar();
                
                case 6 -> mostrar();

            }

        }

    }

    public void mostrar() {
        id = 1;
        while (productoDAO.traerProductos(id) != null) {

            productos.add(productoDAO.traerProductos(id));
            id++;

        }
        System.out.println("-------------------------------");

        for (Producto puntero : productos) {

            System.out.println("ID: " + puntero.getid());
            System.out.println("Nombre del producto: " + puntero.getNombre());
            System.out.println("Cantidad en inventario:" + puntero.getCantidad());
            System.out.println("Precio: " + puntero.getPrecio());
            System.out.println("Peso: " + puntero.getPeso());
            System.out.println("Utilidad: " + puntero.getUtilidad());
            System.out.println("Prioridad: " + puntero.getPrioridad());
            System.out.println("-------------------------------");

        }
    }

    public void modificar() {

        boolean salir = false;

        String columna;

        while (!salir) {

            

                System.out.println("Ingrese el atributo que desea modificar");
                columna = scanner.next();
                
                ///ERROR GRAVE, BUCLE SIN SALIDA
                ///hay que validar que exista el atributo en la tabla, si no lo encuentra queda
                ///atrapado en el bucle de preguntar id y el nuevo valor
                ///POR OTRO LADO NO HAY CONDICION DE SALIDA DEL WHILE
                
                System.out.println("Ingrese la id del producto");
                id = Validador.intMayorA(0);

          

            System.out.println("Ingrese el nuevo valor");
            int nuevoValor = Validador.intMayorA(0);

            productoDAO.modificarProducto(id, columna, String.valueOf(nuevoValor));

        }


    }

    public void eliminar() {

        System.out.println("Ingrese la id del producto a eliminar");
        id =  Validador.intMayorA(1);
        productoDAO.eliminarProducto(id);
        productoDAO.eliminarDeInventario(nombre, cantidad);
    }

    public void eliminarDeInventario() {

        System.out.println("Ingrese el nombre del producto que desea eliminar del inventario");
        nombre = scanner.next();

        System.out.println("Ingrese la cantidad de productos a eliminar");
        cantidad = Validador.intMayorA(1);


    }

    public void agregarAInventario() {

        System.out.println("Ingrese el nombre del producto que desea agregar al inventario");
        nombre = scanner.next();

        System.out.println("Ingrese la cantidad de productos a agregar");
        cantidad = Validador.intMayorA(1);

        productoDAO.agregarAInventario(nombre, cantidad);
    }

    public void registrar() {

        System.out.println("Ingrese el nombre del producto a registrar");
        nombre = scanner.next();

        System.out.println("Ingrese la cantidad inicial en inventario");
        cantidad = Validador.intMayorA(0);

        System.out.println("Ingrese el precio de lista del producto");
        precio =  Validador.doubleMayorA(0);

        System.out.println("Ingrese el peso del consumible");
        int peso = Validador.entreParametros(0, 10);

        System.out.println("Ingrese la utilidad del consumible");
        String utilidad = scanner.next();

        System.out.println("Ingrese la prioridad del consumible (0: poco relevante / 5: muy relevante)");
        int prioridad = Validador.entreParametros(0, 5);

        productoDAO.registrarProducto(nombre, cantidad, precio, peso, prioridad, utilidad);

        System.out.println("Al consumible registrado se le asignó la id:" + GestorDAOs.devolverId(nombre, "producto"));
    }

    public int getid() {

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
