/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola.Abstractas;


/**
 *
 * @author blitowsky
 */

public abstract class Persona {
    
    String nombre;
    String ubicacion;  
    
    public Persona(){
        
        
        
    }
    
    public Persona(String nombre, String ubicacion){
        
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
           
    
}
