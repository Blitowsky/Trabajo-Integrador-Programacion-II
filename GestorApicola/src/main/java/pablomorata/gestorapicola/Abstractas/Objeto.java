/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola.Abstractas;


/**
 *
 * @author blitowsky
 */
public abstract class Objeto{
    
    String nombreEntidad;
    int peso;
    String utilidad;
    int prioridad;
    public Objeto(){
        
        
        
    }
    
    public Objeto(String nombreEntidad, String utilidad, int prioridad, int peso){
                    
        this.nombreEntidad = nombreEntidad;
        this.utilidad = utilidad;
        this.prioridad = prioridad;
        this.peso = peso;
        
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(String utilidad) {
        this.utilidad = utilidad;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }
            
    
    
}
