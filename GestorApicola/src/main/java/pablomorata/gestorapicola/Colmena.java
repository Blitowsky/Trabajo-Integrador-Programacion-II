/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola;

/**
 *
 * @author blitowsky
 */
public class Colmena{
    
    int id;
    boolean abejas;
    int cantMiel;
    int cantMarcos;
    String estadoColmena;  
    int cantColmenas = 0;
    
    public Colmena(int id, boolean abejas, int cantMiel, int cantMarcos, String estadoColmena){
        
        this.id = id;
        this.abejas = abejas;
        this.cantMarcos = cantMarcos;
        this.cantMiel = cantMiel;
        this.estadoColmena = estadoColmena;
        cantColmenas ++;
        
    }
    public Colmena(boolean abejas, int cantMiel, int cantMarcos, String estadoColmena){
        
        this.abejas = abejas;
        this.cantMarcos = cantMarcos;
        this.cantMiel = cantMiel;
        this.estadoColmena = estadoColmena;
        cantColmenas ++;
        
    }

    
    public int getid(){
        
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

    public int getCantColmenas() {
        return cantColmenas;
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
