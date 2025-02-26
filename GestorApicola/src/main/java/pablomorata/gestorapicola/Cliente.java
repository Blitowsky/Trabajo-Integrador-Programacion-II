/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola;

import java.util.Scanner;
import pablomorata.gestorapicola.DAO.AdministradorDAO;
import pablomorata.gestorapicola.DAO.GestorDAOs;
import pablomorata.gestorapicola.DAO.ProductoDAO;
import pablomorata.gestorapicola.DAO.UsuariosDAO;
import pablomorata.gestorapicola.Utiles.Validador;

/**
 *
 * @author blitowsky
 * 
 */


public class Cliente extends Usuario{
    
    static Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    ProductoDAO productoDAO;
    String name;

    AdministradorDAO administradorDAO;
    boolean salir = false;
    
    String ubicación; 
    
    public Cliente(){
        
        productoDAO = new ProductoDAO();
        administradorDAO = new AdministradorDAO();   
        
    }
    
    public Cliente(String nombre, String ubicacion, int id, int prioridad){
        
        super(nombre, id, prioridad);
        this.ubicación = ubicacion;
        administradorDAO = new AdministradorDAO();     
        productoDAO = new ProductoDAO();
 
    }
    
    @Override
    public void selectorOpciones(String nombre){
        
        name = nombre;
        id = GestorDAOs.obtenerInt("id", "Cliente", name);
        
        System.out.println("Cliente: " + nombre);
        System.out.println("ID: " + GestorDAOs.obtenerInt("id","Cliente", nombre));
        
        while(!salir){
            
            System.out.println("Ingrese la acción a realizar:");
            System.out.println("0: Salir \n1: Compra de producto \n2: Modificar nombre \n 3: Modificar Contraseña");
            int opcion = Validador.entreParametros(0, 4);

            switch (opcion){

                case 0 -> salir = true;
                case 1 -> vender();
                case 2 -> modificarNombre(name);
                case 3 -> modificarContraseña(name, id);

            }
            
        }
       
    }
    
    public void vender(){
        
        System.out.println("Ingrese el nombre del producto a comprar");
        String nombre = scanner.next();
        
        
        while (!GestorDAOs.obtenerString(nombre, "Producto")){
            
            System.out.println("El elemento ingresado no existe, por favor, verifique la entrada \n "
                             + "En caso de querer cancelar la operación, ingrese 0");
            
            nombre = scanner.next();
            
            if (nombre.equals("0")) break;
                
        }
                
        
        
        System.out.println("Ingrese la cantidad de artículos a comprar");
        int cantidad = Validador.intMayorA(1);
        
        System.out.println("Ingrese el nombre del apicultor");
        String nombreApicultor = scanner.next();
        
         while (!GestorDAOs.obtenerString(nombre, "Administrador")){
            
            System.out.println("El Apicultor ingresado no existe, por favor, verifique la entrada \n "
                             + "En caso de querer cancelar la operación, ingrese 0");
            
            nombre = scanner.next();
            
            if (nombre.equals("0")) break;
                
        }
        
        id = GestorDAOs.obtenerInt("id", "producto", nombre);
        
        int precio = GestorDAOs.obtenerInt("precio", "producto", id);
        
        productoDAO.venderProducto(nombre, cantidad);
        administradorDAO.modificarBalance(nombreApicultor,id, precio , cantidad, true);
        
        
    }
    
         
    public void modificarNombre(String nombreActual){
        
        System.out.println("Ingrese el nuevo nombre:");
        String nuevoNombre;
        nuevoNombre = scanner.next();
        
        UsuariosDAO.modificarDatosRegistro("Cliente", "nombre", nuevoNombre, nombreActual);
        name = nuevoNombre;
        
    }
    public void modificarContraseña(String nombreActual, int id){
        
        if (validar("Cliente", id)) {
            System.out.println("Ingrese la nueva contraseña:");
            String nuevoValor = scanner.next();
        
            UsuariosDAO.modificarDatosRegistro("Cliente", "contraseña", nuevoValor, nombreActual);
        }
        
       
        
    }
    
    private boolean validar(String tipoUsuario, int id) {

        int contador = 0;
        boolean aprobado = false;

        do {
                
            System.out.println("Ingrese la contraseña");
            String contraseña = scanner.next();

            if (contraseña.equals(GestorDAOs.obtenerString("contraseña", tipoUsuario, id))) {

                return true;

            } else {

                System.out.println("La contraseña ingresada es incorrecta, intente nuevamente...");

            }
            contador++;

        } while (contador < 6 && !aprobado);

            if (contador < 6) {

                System.out.println("Ha superado la cantidad máxima de intentos");

            }      
        
        return false;
    }
    
}
