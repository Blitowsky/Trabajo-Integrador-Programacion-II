/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola;
import java.util.Scanner;


/**
 *
 * @author blitowsky
 */
public class Menu {
    
    Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    Inventario inventario = new Inventario();
    
    int opcion;
    boolean salir = false;
    
    public void selectorOpciones(){
        
        do {
            
            System.out.println("Ingrese la opción del menú a elegir \n 1: agregar \n -1: mostrar ");
            opcion = scanner.nextInt();
            
            switch (opcion) {
                case 1:
                    
                    System.out.println("Ingrese la id de la colmena");
                    int id = scanner.nextInt();
                    System.out.println("La colmena posee abejas?");
                    boolean abejas = scanner.nextBoolean();
                    System.out.println("Ingrese el nivel de miel de la colmena (0: sin miel / 10: completamente llena)");
                    int miel = scanner.nextInt();
                    System.out.println("Ingrese la cantidad de marcos de la colmena");
                    int marcos = scanner.nextInt();
                    System.out.println("Ingrese el estado de la colmena");
                    String estado = scanner.next();
                         
                    
                    Colmena colmena = new Colmena(id, abejas, miel, marcos , estado);
                    inventario.sumarColmena(colmena);
                    
                    break;
                    
                case 2:
                    
                    System.out.println("Ingrese el id de la colmena a eliminar");
                    id = scanner.nextInt();
                    
                    inventario.eliminarColmena(id);
                    
                    break;
                    
                    
                case 3:
                    
                    System.out.println("Ingrese el id de la colmena a modificar");
                    id = scanner.nextInt();
                    
                    Colmena puntero = inventario.devolverColmena(id);
                    
                    System.out.println("La colmena posee abejas?");
                    abejas = scanner.nextBoolean();
                    puntero.setAbejas(abejas);
                    
                    System.out.println("Ingrese el nivel de miel de la colmena (0: sin miel / 10: completamente llena)");
                    miel = scanner.nextInt();
                    puntero.setCantMiel(miel);
                    
                    System.out.println("Ingrese la cantidad de marcos de la colmena");
                    marcos = scanner.nextInt();
                    puntero.setCantMarcos(marcos);
                    
                    System.out.println("Ingrese el estado de la colmena");
                    estado = scanner.next();
                    puntero.setEstadoColmena(estado);
                    
                case -1:
                    
                    inventario.mostrarListaColmenas();
                    break;
                    
                case 0: 
                    salir = true;
                    break;
                 
                    
                
             
            }
    
    
    
        }while(salir == false);     
        
    }
    
    
        
    
    
    
}
