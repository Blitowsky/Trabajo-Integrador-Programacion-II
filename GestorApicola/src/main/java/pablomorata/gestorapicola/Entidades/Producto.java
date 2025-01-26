/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola.Entidades;

import pablomorata.gestorapicola.Abstractas.Entidad;

/**
 *
 * @author blitowsky
 */
public class Producto extends Entidad {
    
    private int id;
    private String nombre;
    private int cantidad;
    private double precio;
    
    public Producto(){
        
        super("producto");
        
    }
    
    public Producto(int id,String nombre, int cantidad, double precio){
        
        super("producto");
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        
    }
    
    public int getid(){
        
        return id;
        
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
