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
public class ProductoDAO {
    
    public static void agregarProducto(String nombre, int cantidad, double precio) {

        String sql = "INSERT INTO Producto (nombre, cantidad, precio) VALUES (?, ?, ?)";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, cantidad);
            pstmt.setDouble(3, precio);
            pstmt.executeUpdate();
            System.out.println("Producto insertado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar producto: " + e.getMessage());
        }
        Database.disconnect();

    }
    
    public static void traerColmenas() {

        String sql = "SELECT * FROM Producto";

        try (Connection conn = Database.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("id del producto: " + rs.getInt("ID"));
                System.out.println("Nombre del producto" + rs.getString("nombre"));
                System.out.println("Cantidad en existencia: " + rs.getInt("cantidad"));
                System.out.println("Precio del producto: " + rs.getDouble("precio"));
                System.out.println("-------------------");
            }
        } catch (SQLException e) {
            System.err.println("Error al listar productos: " + e.getMessage());
        }
        Database.disconnect();

    }
    
}
