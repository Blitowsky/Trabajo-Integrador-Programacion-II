/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author blitowsky
 */
public class Database {
    
    private static final String URL = "jdbc:sqlite:/home/blitowsky/Documents/Facultad/Programación II/Proyecto Final/DBGestorApicola.db";
    private static Connection connection = null;
    
    public static Connection connect(){
        
        try{
            
            if(connection == null || connection.isClosed()){
                
                connection = DriverManager.getConnection(URL);
                System.out.println("Conexión exitosa.");
                     
            }
            
            
        } catch(SQLException e){
            
            System.err.println("Conexión fallida: " + e.getMessage());             
            
        }
        return connection;
        
    }

    public static void disconnect(){
        
        try {
            if(connection != null && !connection.isClosed()){
                
                connection.close();
                System.out.println("Conexión cerrada.");
                
            }
        } catch (SQLException e) {
            
            System.err.println("Error al cerrar la conexión " + e.getMessage());
        }
        
    }
    
}
