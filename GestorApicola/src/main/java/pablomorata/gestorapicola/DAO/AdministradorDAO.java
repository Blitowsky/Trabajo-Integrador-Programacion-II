/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pablomorata.gestorapicola.Database;

/**
 *
 * @author blitowsky
 */
public class AdministradorDAO {
    int balance;
    
    public void modificarBalance(int id, int precio, int cantidad, boolean positivo){
        
        String modificador = "UPDATE Administrador SET balance = ? WHERE id = ? ";
        String obtenerBalance = "SELECT FROM Administrador WHERE id = ?";
        
        try(Connection conn = Database.connect(); 
                PreparedStatement pstmt1 = conn.prepareStatement(obtenerBalance);
                PreparedStatement pstmt2 = conn.prepareStatement(modificador))
        {
            
            pstmt1.setInt(1, id);
            pstmt1.executeUpdate();

            try(ResultSet rs = pstmt1.executeQuery()){
                
                if(rs.next()) balance = rs.getInt("balance"); 
                
            }    
            
            pstmt2.setInt(1, balance);
            pstmt2.setInt(2, id);     
            pstmt2.executeUpdate();
 
            
        }catch(SQLException e){
            
            System.err.println("Error al modificar el balance");
            
        }
            
    }
    
    
}
