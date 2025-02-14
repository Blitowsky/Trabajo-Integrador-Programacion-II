/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola;

import java.util.Scanner;
import pablomorata.gestorapicola.Entidades.Cliente;
import pablomorata.gestorapicola.Entidades.Proveedores;
import pablomorata.gestorapicola.Entidades.Usuario;
import pablomorata.gestorapicola.Submenus.Administrador;

/**
 *
 * @author blitowsky
 */

public class MenuDerivador {
    
    int entrada;
    Usuario administrador;
    Usuario cliente;
    Usuario proveedores;
    Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    
    public MenuDerivador(){
         
        administrador = new Administrador();
        cliente = new Cliente();
        proveedores = new Proveedores();

    }
    
    public void selectorOpciones(){
        
        entrada = scanner.nextInt();
        
        
        
        switch (entrada) {
            case 1 -> administrador.selectorOpciones();
            case 2 -> {}
            case 3 -> {}
           
        }
        
        
    }
    
}
