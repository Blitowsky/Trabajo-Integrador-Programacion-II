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
import pablomorata.gestorapicola.Entidades.Consumible;

/**
 *
 * @author blitowsky
 */
public class ConsumibleDAO {
    
    public static void registrarConsumible(String nombre, int cantidad, double precio){
        
        String query = "INSERT INTO Consumible (nombre, cantidad, precio) VALUES (?, ?, ?)";
        
        try(Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(query)){
            
            pstmt.setString(1, nombre);
            pstmt.setInt(2, cantidad);
            pstmt.setDouble(3, precio);
            pstmt.executeUpdate();
            System.out.println("Registro exitoso ");
            
            
        }catch(SQLException e){
            
            System.err.println("Error al registrar consumible" + e.getMessage());
            
        }
            
        
    }
    
    public static void agregarAInventario(String nombre, int cantidad){
        
        String query = "UPDATE Consumible SET cantidad = cantidad + ? WHERE nombre = ?";
        
        try(Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(query)){
            
            pstmt.setInt(1, cantidad);
            pstmt.setString(2, nombre);
            pstmt.executeUpdate();
            System.out.println("Exito al actualizar consumibles");
            
            
        }catch(SQLException e){
            
            System.err.println("Error al actualizar consumibles");
            
        }
        
    }
    public static void eliminarDelInventario(String nombre, int cantidad){
        
        String query = "UPDATE Consumible SET cantidad = cantidad - ? WHERE nombre = ?";
        
        try(Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(query)){
            
            pstmt.setInt(1, cantidad);
            pstmt.setString(2, nombre);
            pstmt.executeUpdate();
            System.out.println("Exito al actualizar consumibles");
            
            
        }catch(SQLException e){
            
            System.err.println("Error al actualizar consumibles");
            
        }
        
    }
    
    public static void eliminarDeRegistro(String nombre){
        
        String query = "DELETE FROM Consumible WHERE nombre = ?";
        
        try(Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(query)){
            
            pstmt.setString(1, nombre);
            pstmt.executeUpdate();
            System.out.println("Se eliminó el consumible correctamente ");
            
        }catch(SQLException e){
            
            System.err.println("Error al eliminar consumible " + e.getMessage());
            
        }
        
    }
    
    public static Consumible traerConsumibles(int id){
        
        String query = "SELECT * FROM Consumible WHERE id = ?";
        
        try(Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(query)){
            
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                
                if (rs.next()) { 
                    
                    int productoId = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    int cantidad = rs.getInt("cantidad");
                    double precio = rs.getDouble("precio");

                    return new Consumible(productoId, nombre, cantidad, precio);
                    
                }
            }            
            
        }catch(SQLException e){
            
            System.err.println("Error al traer consumibles " + e.getMessage());
                    
            
        }
        
        return null;
    } 
    
    
    
}
