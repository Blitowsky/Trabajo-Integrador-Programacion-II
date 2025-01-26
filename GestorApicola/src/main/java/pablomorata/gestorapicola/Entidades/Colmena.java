/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola.Entidades;

import pablomorata.gestorapicola.Abstractas.Entidad;

/**
 *
 * @author blitowsky
 * 
 */
public class Colmena extends Entidad{

    private int id;
    private boolean abejas;
    private int cantMiel;
    private int cantMarcos;
    private String estadoColmena;
    private String observaciones;

    public Colmena(){
        
        super("colmena");
        
    }

  
    public Colmena(int id, boolean abejas, int cantMiel, int cantMarcos, String estadoColmena) {
        super("colmena");
        this.id = id;
        this.abejas = abejas;
        this.cantMarcos = cantMarcos;
        this.cantMiel = cantMiel;
        this.estadoColmena = estadoColmena;

    }

    public int getid() {

        return id;

    }

    public boolean isAbejas() {
        return abejas;
    }

    public int getCantMiel() {
        return cantMiel;
    }

    public int getCantMarcos() {
        return cantMarcos;
    }

    public String getEstadoColmena() {
        return estadoColmena;
    }

   

    public void setAbejas(boolean abejas) {
        this.abejas = abejas;
    }

    public void setCantMiel(int cantMiel) {
        this.cantMiel = cantMiel;
    }

    public void setCantMarcos(int cantMarcos) {
        this.cantMarcos = cantMarcos;
    }

    public void setEstadoColmena(String estadoColmena) {
        this.estadoColmena = estadoColmena;
        
    }


}
