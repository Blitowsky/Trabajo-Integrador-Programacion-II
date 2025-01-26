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
import pablomorata.gestorapicola.Entidades.Producto;


/**
 *
 * @author blitowsky
 */
public class ProductoDAO {
    
    public static void registrarProducto(String nombre, int cantidad, double precio) {

        String sql = "INSERT INTO Producto (nombre, cantidad, precio) VALUES (?, ?, ?)";

        try (Connection conn = Database.connect(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nombre);
            pstmt.setInt(2, cantidad);
            pstmt.setDouble(3, precio);
            pstmt.executeUpdate();
            System.out.println("Producto registrado correctamente.");
            
        } catch (SQLException e) {
            
            System.err.println("Error al registrar producto: " + e.getMessage());
        }

    }
    
    public static void agregarAInventario(String nombre, int cantidad){
        
        String query = "UPDATE Producto SET cantidad =  cantidad + ? WHERE nombre = '?'";
        
        try(Connection conn = Database.connect(); 
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(2, nombre.toLowerCase());
            pstmt.setInt(1, cantidad);
            pstmt.executeUpdate();
            System.out.println("Producto/s agregado correctamente");
            
        } catch (SQLException e) {
            
            System.out.println("Error al agregar el producto/s: " + e.getMessage());
            
        }
        
    }
     public static void eliminarDeInventario(String nombre, int cantidad){
        
        String query = "UPDATE Producto SET cantidad =  cantidad - ? WHERE nombre = '?'";
        
        try(Connection conn = Database.connect(); 
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(2, nombre.toLowerCase());
            pstmt.setInt(1, cantidad);
            pstmt.executeUpdate();
            System.out.println("Producto/s agregado correctamente");
            
        } catch (SQLException e) {
            
            System.out.println("Error al agregar el producto/s: " + e.getMessage());
            
        }
        
    }
    
    public static Producto traerProductos(int id) {

       String sql = "SELECT * FROM Producto WHERE id = ?";

        try (Connection conn = Database.connect(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                
                if (rs.next()) { 
                    
                    int productoId = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    int cantidad = rs.getInt("cantidad");
                    double precio = rs.getDouble("precio");

                    return new Producto(productoId, nombre, cantidad, precio);
                    
                }
            }

        } catch (SQLException e) {
            
            System.err.println("Error al retornar el producto: " + e.getMessage());
        }
        return null;        

    }
    
    public static void eliminarProducto(int id){
        
        String query = "DELETE FROM Producto WHERE id = ?";
        
        try (Connection conn = Database.connect(); 
             PreparedStatement eliminarStmt = conn.prepareStatement(query)) {
            
            eliminarStmt.setInt(1, id);
            eliminarStmt.executeUpdate();
            System.out.println("Se eliminó el producto correctamente ");
            
        } catch (SQLException e) {
            
            System.err.println("Error al eliminar producto" + e.getMessage());
        }
        
    }
}
