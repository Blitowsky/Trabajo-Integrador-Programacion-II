/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pablomorata.gestorapicola.Producto;


/**
 *
 * @author blitowsky
 */
public class ProductoDAO {
    
    public void registrarProducto(String nombre, int cantidad, double precio, int peso, int prioridad, String utilidad) {

        String sql = "INSERT INTO Producto (nombre, cantidad, precio, peso, utilidad, prioridad) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.connect(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nombre);
            pstmt.setInt(2, cantidad);
            pstmt.setDouble(3, precio);
            pstmt.setInt(4, peso);
            pstmt.setInt(5, prioridad);
            pstmt.setString(6, utilidad);
            pstmt.executeUpdate();
            System.out.println("Producto registrado correctamente.");
            
        } catch (SQLException e) {
            
            System.err.println("Error al registrar producto: " + e.getMessage());
        }

    }
    
    public void agregarAInventario(String nombre, int cantidad){
        
        String query = "UPDATE Producto SET cantidad =  cantidad + ? WHERE nombre = ?";
        int cantidadPrevia = GestorDAOs.obtenerInt("cantidad", "Producto", nombre);

        try(Connection conn = Database.connect(); 
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            
            pstmt.setInt(1, cantidadPrevia + cantidad);
            pstmt.setString(2, nombre.toLowerCase());
            pstmt.executeUpdate();
            System.out.println("Producto/s agregado correctamente");
            
            
        } catch (SQLException e) {
            
            System.out.println("Error al agregar el producto/s: " + e.getMessage());
            
        }
        
    }
    
     public void eliminarDeInventario(String nombre, int cantidad){
        
        String query = "UPDATE Producto SET cantidad = ? WHERE nombre = ?";
        int cantidadPrevia = GestorDAOs.obtenerInt("cantidad", "Producto", nombre);
        try(Connection conn = Database.connect(); 
            PreparedStatement pstmt = conn.prepareStatement(query)) {
                        
            pstmt.setInt(1, cantidadPrevia - cantidad);
            pstmt.setString(2, nombre.toLowerCase());
            pstmt.executeUpdate();
            System.out.println("Producto/s eliminado correctamente");
            
        } catch (SQLException e) {
            
            System.err.println("Error al eliminar el producto/s: " + e.getMessage());
            
        } catch (Exception e) { // Captura cualquier otra excepción
            System.err.println("Error general: " + e.getMessage());
        }
        
                GestorDAOs.reasignarId("Producto");

        
    }
    
    public Producto traerProductos(int id) {

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
                    int peso = rs.getInt("peso");
                    int prioridad = rs.getInt("prioridad");
                    String utilidad = rs.getString("utilidad");


                    return new Producto(productoId, nombre, cantidad, precio, peso, prioridad,utilidad);
                    
                }
            }

        } catch (SQLException e) {
            
            System.err.println("Error al retornar el producto: " + e.getMessage());
        }
        return null;        

    }
    public void modificarProducto(int id, String columnaModificar, String nuevoValor){
        
        String query = "UPDATE Producto SET " + columnaModificar + " = ? WHERE id = ? ";
        
        try(Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(query)){
            
            switch (columnaModificar.toLowerCase()) {
                case "nombre", "utilidad" -> {
                    
                    pstmt.setString(1, nuevoValor);
                    
                }
                case "cantidad", "prioridad", "peso" -> {
                    
                    pstmt.setInt(1,Integer.parseInt(nuevoValor));
                    
                    
                }
                case "precio" -> {
                    
                    pstmt.setDouble(1, Double.parseDouble(nuevoValor));
                    
                }
            }
            
        } catch(SQLException e){
            
            System.out.println("Fallo al modificar producto " + e.getMessage());
            
        }
        
    }
    
    public void eliminarProducto(int id){
        
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
    
    public void venderProducto(String nombre, int cantidad){
        
        eliminarDeInventario(nombre,cantidad);
        
        
        
    }
}
