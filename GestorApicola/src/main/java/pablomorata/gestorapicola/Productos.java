/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola;

import pablomorata.gestorapicola.Abstractas.Entidad;

/**
 *
 * @author blitowsky
 */
public class Productos extends Entidad {
    
    
    String nombre;
    int cantidad;
    double precio;
    
    public Productos(String nombre, int cantidad, double precio){
        
        super("producto");
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
    
}
