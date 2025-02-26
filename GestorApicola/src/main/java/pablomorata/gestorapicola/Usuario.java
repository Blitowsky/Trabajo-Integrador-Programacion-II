/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola;

import java.util.Date;
import pablomorata.gestorapicola.Abstractas.Persona;

/*
 *
 * @author blitowsky
 *
 */

public class Usuario extends Persona{
      
    int id;
    int prioridad;
    
    
    public Usuario(){
           
    }
    
    
    public Usuario(String nombre, int id, int prioridad){
        
        super(nombre);
        this.id = id;
        this.prioridad = prioridad;
        
    }
    
    public void selectorOpciones(String nombre) {
        
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    
      
}
