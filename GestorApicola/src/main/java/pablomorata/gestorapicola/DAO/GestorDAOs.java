/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pablomorata.gestorapicola.Database;

/**
 *
 * @author blitowsky
 */
public class GestorDAOs {
    
    public static void reasignarId(String nombreEntidad) {
        String selectQuery = "SELECT id FROM " + nombreEntidad + " ORDER BY id ASC";
        String updateQuery = "UPDATE " + nombreEntidad + " SET id = ? WHERE id = ?";

        try (Connection conn = Database.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(selectQuery)) {

            int nuevoId = 1; // Inicia desde 1
            while (rs.next()) {
                int idActual = rs.getInt("id");

                try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
                    pstmt.setInt(1, nuevoId); // Nuevo ID
                    pstmt.setInt(2, idActual); // ID actual
                    pstmt.executeUpdate();
                }

                nuevoId++;
            }

            System.out.println("ids reasignados en la base de datos.");

        } catch (SQLException e) {

            System.err.println("Error al reasignar ids " + e.getMessage());

        }
        Database.disconnect();
    }
    
    public static void hasNext(int id){
        
        
        
        
    }
}
