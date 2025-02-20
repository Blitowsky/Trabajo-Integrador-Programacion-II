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
    Date antiguedad;
    int prioridad;
    
    
    public Usuario(){
           
    }
    
    
    public Usuario(String nombre, int id, Date antiguedad, int prioridad){
        
        super(nombre);
        this.id = id;
        this.antiguedad = antiguedad;
        this.prioridad = prioridad;
        
    }
    
    public void selectorOpciones() {
        
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Date antiguedad) {
        this.antiguedad = antiguedad;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    
      
}
