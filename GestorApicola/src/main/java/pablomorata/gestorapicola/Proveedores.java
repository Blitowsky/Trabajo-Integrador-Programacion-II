/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola;

import java.util.Scanner;
import static pablomorata.gestorapicola.Cliente.scanner;
import pablomorata.gestorapicola.DAO.AdministradorDAO;
import pablomorata.gestorapicola.DAO.GestorDAOs;
import pablomorata.gestorapicola.DAO.ProductoDAO;
import pablomorata.gestorapicola.DAO.UsuariosDAO;
import pablomorata.gestorapicola.Utiles.Validador;


/*
 *
 * @author blitowsky
 * 
 */

public class Proveedores extends Usuario{
    
    
    String ubicacion;
    boolean envio;
    boolean salir = false;
    Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    String nombre;
    String name;
    
    
    ProductoDAO productoDAO;
    AdministradorDAO administradorDAO;
    
    
    public Proveedores(){
        
        productoDAO = new ProductoDAO();
        administradorDAO = new AdministradorDAO();   

    }
    
    public Proveedores(String nombre, String ubicacion, int id, int prioridad, boolean envio){
        
        super(nombre, id, prioridad);
        this.ubicacion = ubicacion;
        this.envio = envio;
        productoDAO = new ProductoDAO();
        administradorDAO = new AdministradorDAO();   
        
    }
    
    @Override
    public void selectorOpciones(String nombre){
        
        name = nombre;
        id = GestorDAOs.obtenerInt("id", "Proveedor", name);
        
        
        while(!salir){
            
            System.out.println("Ingrese la acción a realizar:");
            System.out.println("0: Salir \n1: Compra de producto \n2: Modificar nombre \n3: Modificar Contraseña");
            int opcion = Validador.entreParametros(0, 4);
            
            switch (opcion){

                case 0 -> salir = true;
                case 1 -> comprar();
                case 2 -> modificarNombre(nombre);
                case 3 -> modificarContraseña(nombre, id);
                
            }  
        }
    }
    
    public void comprar(){
        
        System.out.println("Ingrese el nombre del producto a comprar");
        nombre = scanner.next();
         while (!GestorDAOs.obtenerString(nombre, "Consumible")){
            
            System.out.println("El elemento ingresado no existe, por favor, verifique la entrada \n "
                             + "En caso de querer cancelar la operación, ingrese 0");
            
            nombre = scanner.next();
            
            if (nombre.equals("0")) break;
                
        }
        
        System.out.println("Ingrese la cantidad de artículos a comprar");
        int cantidad = Validador.intMayorA(1);
        
        System.out.println("Ingrese el nombre del apicultor");
        String nombreApicultor = scanner.next();
        
         while (!GestorDAOs.obtenerString(nombreApicultor, "Consumible")){
            
            System.out.println("El apicultor ingresado no existe, por favor, verifique la entrada \n "
                             + "En caso de querer cancelar la operación, ingrese 0");
            
            nombreApicultor = scanner.next();
            
            if (nombreApicultor.equals("0")) break;
                
        }
        
        id = GestorDAOs.obtenerInt("id", "consumible", nombre);
        int precio = GestorDAOs.obtenerInt("precio", "consumible", id);
        
        productoDAO.venderProducto(nombre, cantidad);
        administradorDAO.modificarBalance(nombreApicultor,id, precio , cantidad, false);
        
    }
         
    public void modificarNombre(String nombreActual){
        
        System.out.println("Ingrese el nuevo nombre:");
        String nuevoNombre;
        nuevoNombre = scanner.next();
        
        UsuariosDAO.modificarDatosRegistro("Proveedor", "nombre", nuevoNombre, nombreActual);
        name = nuevoNombre;

    }
     public void modificarContraseña(String nombreActual, int id){
        
        if (validar("Proveedor", id)) {
            System.out.println("Ingrese la nueva contraseña:");
            String nuevoValor = scanner.next();
        
            UsuariosDAO.modificarDatosRegistro("Proveedor", "contraseña", nuevoValor, nombreActual);
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
    

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public boolean isEnvio() {
        return envio;
    }

    public void setEnvio(boolean envio) {
        this.envio = envio;
    }
    
    

}
