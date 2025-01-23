/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola.Abstractas;

/**
 *
 * @author blitowsky
 */
public abstract class Entidad {
    
    private String nombreEntidad;
    
    public Entidad(String nombreEntidad){
        
        this.nombreEntidad = nombreEntidad;
        
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }
    
    
}
