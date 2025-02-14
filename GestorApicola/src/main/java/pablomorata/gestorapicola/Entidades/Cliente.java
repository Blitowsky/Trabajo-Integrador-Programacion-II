/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola.Entidades;

import java.util.Date;
import java.util.Scanner;
import pablomorata.gestorapicola.DAO.ProductoDAO;

/**
 *
 * @author blitowsky
 * 
 */


public class Cliente extends Usuario{
    
    static Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    
    ProductoDAO productoDAO;
    
    public Cliente(){
        
    }
    
    public Cliente(String nombre, String ubicacion, int id, Date antiguedad, int prioridad){
        
        super(nombre, ubicacion, id, antiguedad, prioridad);
        productoDAO = new ProductoDAO();

    }
    
    
    public void vender(String nombre, int cantidad){
        
        productoDAO.venderProducto(nombre, cantidad);
        
    }
            
    
    
}
