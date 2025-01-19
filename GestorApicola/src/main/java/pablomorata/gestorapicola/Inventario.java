/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author blitowsky
 * 
 */

public class Inventario {

    private int cantColmenas = 0;

    ArrayList<Colmena> colmenas;
    ArrayList<Object> herramientas;
    ArrayList<Object> consumibles;

    public Inventario() {

        colmenas = new ArrayList<>();
        herramientas = new ArrayList<>();
        consumibles = new ArrayList<>();

    }

    public boolean idUnico(int id) {

        for (Colmena puntero : colmenas) {

            if (puntero.getid() == id) {

                return false;

            }
        }
        return true;

    }
    
    public boolean entreParametros(int parametro, int desde, int hasta){
        
        //El return está pensado para que sea usado en un while(false)
        
        return !(parametro <= hasta && parametro >= desde);
        
    }

    public Colmena devolverColmena(int id) {

        for (Colmena puntero : colmenas) {

            if (puntero.getid() == id) {

                return puntero;

            }

        }
        return null;

    }

    public void sumarColmena(Colmena idColmena) {

        colmenas.add(idColmena);
        cantColmenas++;

    }

    public void restarColmena(Colmena idColmena) {

        colmenas.add(idColmena);
        cantColmenas--;

    }

    public void mostrarListaColmenas() {

        for (Colmena puntero : colmenas) {

            System.out.println("\n Colmena número: " + puntero.getid() + ":");
            System.out.println("Posee abejas: " + puntero.isAbejas());
            System.out.println("El nivel de miel es: " + puntero.getCantMiel());
            System.out.println("Posee " + puntero.getCantMarcos() + " marcos");
            System.out.println("El estado de la colmena es " + puntero.getEstadoColmena() + "\n");

        }

    }

    public void eliminarColmena(int id) {

        Iterator iterator = colmenas.iterator();

        while (iterator.hasNext()) {

            if (iterator.next() == devolverColmena(id)) {

                iterator.remove();

            }

        }

    }

    public void setcantColmenas(int cantColmenas) {

        this.cantColmenas = cantColmenas;

    }

    public int getcantColmenas() {

        return cantColmenas;

    }

}
