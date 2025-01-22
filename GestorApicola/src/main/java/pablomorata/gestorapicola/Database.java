/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
    
    public static void reasignarId(Connection conexion, int idAEliminar) throws SQLException{
        
        String eliminarSql = "DELETE FROM tu_tabla WHERE id = ?";
    String reasignarSql = """
        WITH CTE AS (
            SELECT ROW_NUMBER() OVER (ORDER BY id) AS nueva_id, id
            FROM tu_tabla
        )
        UPDATE tu_tabla
        SET id = (SELECT nueva_id FROM CTE WHERE CTE.id = tu_tabla.id)
    """;

    try (PreparedStatement eliminarStmt = conexion.prepareStatement(eliminarSql);
         Statement reasignarStmt = conexion.createStatement()) {
        eliminarStmt.setInt(1, idAEliminar);
        eliminarStmt.executeUpdate();
        reasignarStmt.executeUpdate(reasignarSql);
    }
        
        
        
    }
    
}
