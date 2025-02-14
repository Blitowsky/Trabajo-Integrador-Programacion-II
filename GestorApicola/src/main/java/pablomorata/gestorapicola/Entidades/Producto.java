/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola.Entidades;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import pablomorata.gestorapicola.DAO.GestorDAOs;
import pablomorata.gestorapicola.DAO.ProductoDAO;
import pablomorata.gestorapicola.Database;
import pablomorata.gestorapicola.Abstractas.Objeto;
import pablomorata.gestorapicola.Validador;

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
   
    public Producto(){
        
        productoDAO = new ProductoDAO();
        
    }
    
    public Producto(int id,String nombre, int cantidad, double precio,int peso, int prioridad, String utilidad){
        
        super("producto",utilidad, prioridad, peso);
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
                System.out.println(" 4: Eliminar producto \n 5: Vender producto \n 6: Mostrar productos en inventario");
                System.out.println("Ingrese la acción del menú a elegir ");

                opcion = scanner.nextInt();
                opcion = Validador.entreParametros(opcion, 0, 6);

            } catch (InputMismatchException e) {

                System.err.println("#ERROR# \n Caracter inválido");
                Database.limpiarBuffer(scanner);
                opcion = Validador.errorPorCaracter();
                opcion = Validador.entreParametros(opcion, 0, 6);

            }

            switch (opcion) {

                case 0 ->
                    salir = true;
                case 1 -> {

                    System.out.println("Ingrese el nombre del producto a registrar");
                    String nombre = scanner.next();

                    System.out.println("Ingrese la cantidad inicial en inventario");
                    int cantidad = scanner.nextInt();
                    cantidad = Validador.intMayorA(cantidad, 0);

                    System.out.println("Ingrese el precio de lista del producto");
                    double precio = scanner.nextDouble();
                    precio = Validador.doubleMayorA(precio, 0);
                    
                    System.out.println("Ingrese el peso del consumible");
                    int peso = scanner.nextInt();
                    peso = Validador.entreParametros(peso, 0, 10);
                    
                    System.out.println("Ingrese la utilidad del consumible");
                    String utilidad = scanner.next();
                    
                    System.out.println("Ingrese la prioridad del consumible (0: poco relevante / 5: muy relevante)");
                    int prioridad = scanner.nextInt();
                    prioridad = Validador.entreParametros(prioridad, 0, 5);

                    productoDAO.registrarProducto(nombre, cantidad, precio, peso, prioridad, utilidad);
                    
                    
                    
                    System.out.println("Al consumible registrado se le asignó la id:" + GestorDAOs.devolverId(nombre, "producto"));
                }

                case 2 -> {

                    System.out.println("Ingrese el nombre del producto que desea agregar al inventario");
                    String nombre = scanner.next();

                    System.out.println("Ingrese la cantidad de productos a agregar");
                    int cantidad = scanner.nextInt();

                    productoDAO.agregarAInventario(nombre, cantidad);

                }
                case 3 -> {

                    System.out.println("Ingrese el nombre del producto que desea eliminar del inventario");
                    String nombre = scanner.next();

                    System.out.println("Ingrese la cantidad de productos a eliminar");
                    int cantidad = scanner.nextInt();

                    productoDAO.eliminarDeInventario(nombre, cantidad);

                }

                case 4 -> {

                    System.out.println("Ingrese la id del producto a eliminar");
                    int id = scanner.nextInt();
                    productoDAO.eliminarProducto(id);

                }

                case 5 -> {

                    String columna;
                    int id;

                    while (!salir) {

                        System.out.println("Ingrese el atributo que desea modificar");
                        columna = scanner.next();

                        try {

                            System.out.println("Ingrese la id del producto");
                            id = scanner.nextInt();
                            id = Validador.intMayorA(id, 0);

                        } catch (InputMismatchException e) {

                            System.err.println("#ERROR# \n Caracter inválido");
                            Database.limpiarBuffer(scanner);
                            id = Validador.errorPorCaracter();
                            id = Validador.intMayorA(id, 0);

                        }

                        System.out.println("Ingrese el nuevo valor");
                        String nuevoValor = scanner.next();
                        nuevoValor = String.valueOf(Validador.intMayorA(Integer.parseInt(nuevoValor), 0));

                        productoDAO.modificarProducto(id, columna, nuevoValor);

                    }

                    salir = false;

                }
                case 6 -> {

                    int id = 1;
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

            }

        }

    }
    
    public int getid(){
        
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
