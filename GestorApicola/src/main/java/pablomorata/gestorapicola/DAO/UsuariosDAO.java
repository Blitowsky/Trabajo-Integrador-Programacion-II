/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author blitowsky
 */
public class UsuariosDAO {
    
    
    public static void registrarNuevoUsuario(String nombre, String contraseña, String clase){
        
        
        String query = "INSERT INTO " + clase + "(nombre,contraseña) VALUES (?,?)";
        
        
        try(Connection conn = Database.connect();
            PreparedStatement pstmt = conn.prepareStatement(query)){
            
            pstmt.setString(1, nombre);
            pstmt.setString(2, contraseña);
            pstmt.executeUpdate();
                    
            
        } catch(SQLException e){
            
            System.out.println("Error al registrar usuario " + e.getMessage());
            
        }
        
    }
    
    public static void modificarDatosRegistro(String clase, String atributo, String nuevoValor, int id){
        
        
        String query = "UPDATE " + clase + " SET " + atributo + " = ? WHERE id = ? ";
        
        try(Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(query)){
            
            pstmt.setString(1, nuevoValor);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            
            
        } catch(SQLException e){
            
            System.out.println("Fallo al modificar producto " + e.getMessage());
            
        }
        
    }
    
}
