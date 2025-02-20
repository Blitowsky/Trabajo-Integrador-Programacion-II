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
import pablomorata.gestorapicola.Usuario;
import pablomorata.gestorapicola.Utiles.Validador;

/**
 *
 * @author blitowsky
 * 
 */


public class Cliente extends Usuario{
    
    static Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    ProductoDAO productoDAO;

    AdministradorDAO administradorDAO;
    boolean salir = false;
    
    String ubicación; 
    
    public Cliente(){
        productoDAO = new ProductoDAO();
        administradorDAO = new AdministradorDAO();     

        
    }
    
    public Cliente(String nombre, String ubicacion, int id, Date antiguedad, int prioridad){
        
        super(nombre, id, antiguedad, prioridad);
        this.ubicación = ubicacion;
        administradorDAO = new AdministradorDAO();     
        productoDAO = new ProductoDAO();
    }
    
    @Override
    public void selectorOpciones(){
        while(!salir){
            
            System.out.println("Ingrese la acción a realizar:");
            System.out.println("1: Compra de producto");
            int opcion = Validador.entreParametros(0, 4);

            switch (opcion){

                case 0 -> salir = true;
                case 1 -> vender();
                case 2 -> modificarNombre();
                case 3 -> modificarContraseña();
                case 4 -> cualEsMiId();


            }
            
        }
       
    }
    
    public void vender(){
        
        System.out.println("Ingrese el nombre del producto a comprar");
        String nombre = scanner.next();
        
        System.out.println("Ingrese la cantidad de artículos a comprar");
        int cantidad = Validador.intMayorA(1);
        
        System.out.println("Ingrese el nombre del apicultor");
        String nombreApicultor = scanner.next();
        
        id = GestorDAOs.obtenerInt("id", "producto", nombre);
        
        int precio = GestorDAOs.obtenerInt("precio", "producto", id);
        
        productoDAO.venderProducto(nombre, cantidad);
        administradorDAO.modificarBalance(nombreApicultor,id, precio , cantidad, true);
        
        
    }
         
    public void modificarNombre(){
        
        
    }
    public void modificarContraseña(){
        
        
        
    }
    public void cualEsMiId(){
        
        
        
    }
    
}
