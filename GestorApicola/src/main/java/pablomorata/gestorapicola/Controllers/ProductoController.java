/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola.Controllers;

import java.util.ArrayList;
import java.util.Scanner;
import pablomorata.gestorapicola.DAO.GestorDAOs;
import pablomorata.gestorapicola.DAO.ProductoDAO;
import pablomorata.gestorapicola.Inventario;
import pablomorata.gestorapicola.Entidades.Producto;
import pablomorata.gestorapicola.Interfaces.IController;

/**
 *
 * @author blitowsky
 */
public class ProductoController implements IController {

    Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    Inventario inventario = new Inventario();
    Producto producto = new Producto();
    ArrayList<Producto> productos = new ArrayList<>();

    @Override
    public void selectorOpciones() {

        boolean salir = false;

        while (salir == false) {
            System.out.println("---- MENÚ ----");
            System.out.println(" 0: Salir \n 1: Registrar nuevo producto \n 2: Agregar a inventario \n 3: Eliminar de inventario");
            System.out.println(" 4: Eliminar producto \n 5: Vender producto \n 6: Mostrar productos en inventario");
            System.out.println("Ingrese la acción del menú a elegir ");

            int opcion = scanner.nextInt();
            opcion = inventario.entreParametros(opcion, 0, 6);

            switch (opcion) {

                case 0 ->
                    salir = true;
                case 1 -> {

                    System.out.println("Ingrese el nombre del producto a registrar");
                    String nombre = scanner.next();

                    System.out.println("Ingrese la cantidad inicial en inventario");
                    int cantidad = scanner.nextInt();
                    cantidad = inventario.intMayorA(cantidad, 0);

                    System.out.println("Ingrese el precio de lista del producto");
                    double precio = scanner.nextDouble();
                    precio = inventario.doubleMayorA(precio, 0);

                    ProductoDAO.registrarProducto(nombre, cantidad, precio);
                }

                case 2 -> {

                    System.out.println("Ingrese el nombre del producto que desea agregar al inventario");
                    String nombre = scanner.next();

                    System.out.println("Ingrese la cantidad de productos a agregar");
                    int cantidad = scanner.nextInt();

                    ProductoDAO.agregarAInventario(nombre, cantidad);

                }
                case 3 -> {

                    System.out.println("Ingrese el nombre del producto que desea eliminar del inventario");
                    String nombre = scanner.next();

                    System.out.println("Ingrese la cantidad de productos a eliminar");
                    int cantidad = scanner.nextInt();

                    ProductoDAO.eliminarDeInventario(nombre, cantidad);

                }

                case 4 -> {

                    System.out.println("Ingrese la id del producto a eliminar");
                    int id = scanner.nextInt();
                    ProductoDAO.eliminarProducto(id);

                }

                case 5 -> { //destinado a la venta de productos

                }
                case 6 -> {

                    GestorDAOs.reasignarId(producto.getNombreEntidad());
                    int id = 1;
                    while (ProductoDAO.traerProductos(id) != null) {

                        productos.add(ProductoDAO.traerProductos(id));
                        id++;

                    }
                    System.out.println("-------------------------------");

                    for (Producto puntero : productos) {

                        System.out.println("ID: " + puntero.getid());
                        System.out.println("Nombre del producto: " + puntero.getNombre());
                        System.out.println("Cantidad en inventario:" + puntero.getCantidad());
                        System.out.println("Nivel de miel: " + puntero.getPrecio());
                        System.out.println("-------------------------------");

                    }

                }

            }

        }

    }

}
