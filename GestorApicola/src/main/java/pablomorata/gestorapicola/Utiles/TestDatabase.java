/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola.Utiles;

import java.sql.Connection;
import pablomorata.gestorapicola.DAO.Database;

/**
 *
 * @author blitowsky
 */
public class TestDatabase {
    
    public static void main(String[] args){
        
        Connection conn = Database.connect();
                
        if (conn != null){
            System.out.println("Conexión existosa.");
        } else {
            System.out.println("Conexión fallida. ");
            
        }
        
    }
    
}
