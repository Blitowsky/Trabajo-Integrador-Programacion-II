/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola.Entidades;

import java.util.Date;


/**
 *
 * @author blitowsky
 */
public class Proveedores extends Usuario{
    
    String nombre;
    
    public Proveedores(){
        
        
        
    }
    
    public Proveedores(String nombre, String ubicacion, int id, Date antiguedad, int prioridad){
        
        super(nombre, ubicacion, id, antiguedad, prioridad);
       
    }
    

}
