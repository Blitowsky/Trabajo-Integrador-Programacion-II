/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola;

import java.util.Date;
import java.util.Scanner;
import pablomorata.gestorapicola.DAO.AdministradorDAO;
import pablomorata.gestorapicola.DAO.GestorDAOs;
import pablomorata.gestorapicola.DAO.ProductoDAO;
import pablomorata.gestorapicola.Utiles.Validador;


/**
 *
 * @author blitowsky
 */
public class Proveedores extends Usuario{
    
    
    String ubicacion;
    boolean envio;
    boolean salir = false;
    Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    
    ProductoDAO productoDAO;
    AdministradorDAO administradorDAO;
    
    
    public Proveedores(){
        
        productoDAO = new ProductoDAO();
        administradorDAO = new AdministradorDAO();   
        
    }
    
    public Proveedores(String nombre, String ubicacion, int id, Date antiguedad, int prioridad, boolean envio){
        
        super(nombre, id, antiguedad, prioridad);
        this.ubicacion = ubicacion;
        this.envio = envio;
        productoDAO = new ProductoDAO();
        administradorDAO = new AdministradorDAO();   
        
    }
    
    @Override
    public void selectorOpciones(){
        
        while(!salir){
            
            System.out.println("Ingrese la acción a realizar:");
            System.out.println("1: Venta de producto");
            int opcion = Validador.entreParametros(0, 4);
            
            switch (opcion){

                case 0 -> salir = true;
                case 1 -> comprar();
                case 2 -> modificarNombre();
                case 3 -> modificarContraseña();
                case 4 -> cualEsMiId();
                
            }  
        }
    }
    
    public void comprar(){
        
        System.out.println("Ingrese el nombre del producto a comprar");
        String nombre = scanner.next();
        
        System.out.println("Ingrese la cantidad de artículos a comprar");
        int cantidad = Validador.intMayorA(1);
        
        System.out.println("Ingrese el nombre del apicultor");
        String nombreApicultor = scanner.next();
        
        id = GestorDAOs.obtenerInt("id", "consumible", nombre);
        int precio = GestorDAOs.obtenerInt("precio", "consumible", id);
        
        productoDAO.venderProducto(nombre, cantidad);
        administradorDAO.modificarBalance(nombreApicultor,id, precio , cantidad, false);
        
    }
         
    public void modificarNombre(){
        
        
        
    }
    public void modificarContraseña(){
        
        
        
    }
    public void cualEsMiId(){
        
        
        
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
