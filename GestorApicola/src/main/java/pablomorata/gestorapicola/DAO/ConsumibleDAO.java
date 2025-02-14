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

    public void registrarConsumible(String nombre, int cantidad, double precio, int peso, int prioridad, String utilidad) {

        String query = "INSERT INTO Consumible (nombre, cantidad, precio, peso, prioridad,utilidad) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, nombre);
            pstmt.setInt(2, cantidad);
            pstmt.setDouble(3, precio);
            pstmt.setInt(4, peso);
            pstmt.setInt(5, prioridad);
            pstmt.setString(6, utilidad);
            pstmt.executeUpdate();
            System.out.println("Registro exitoso ");

        } catch (SQLException e) {

            System.err.println("Error al registrar consumible" + e.getMessage());

        }

    }

    public void agregarAInventario(String nombre, int cantidad) {

        String query = "UPDATE Consumible SET cantidad = cantidad + ? WHERE nombre = ?";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, cantidad);
            pstmt.setString(2, nombre);
            pstmt.executeUpdate();
            System.out.println("Exito al actualizar consumibles");

        } catch (SQLException e) {

            System.err.println("Error al actualizar consumibles");

        }

    }

    public void modificarConsumible(int id, String columnaModificar, String nuevoValor) {

        String query = "UPDATE Consumible SET " + columnaModificar + " = ? WHERE id = ? ";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {

            switch (columnaModificar.toLowerCase()) {
                case "nombre" -> {

                    pstmt.setString(1, nuevoValor);

                }
                case "cantidad" -> {

                    pstmt.setInt(1, Integer.parseInt(nuevoValor));

                }
                case "precio" -> {

                    pstmt.setDouble(1, Double.parseDouble(nuevoValor));

                }
            }

        } catch (SQLException e) {

            System.out.println("Fallo al modificar consumible " + e.getMessage());

        }

    }

    public void eliminarDelInventario(String nombre, int cantidad) {

        String query = "UPDATE Consumible SET cantidad = cantidad - ? WHERE nombre = ?";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, cantidad);
            pstmt.setString(2, nombre);
            pstmt.executeUpdate();
            System.out.println("Exito al actualizar consumibles");

        } catch (SQLException e) {

            System.err.println("Error al actualizar consumibles");

        }

    }

    public void eliminarDeRegistro(String nombre) {

        String query = "DELETE FROM Consumible WHERE nombre = ?";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, nombre);
            pstmt.executeUpdate();
            System.out.println("Se eliminó el consumible correctamente ");

        } catch (SQLException e) {

            System.err.println("Error al eliminar consumible " + e.getMessage());

        }

    }

    public Consumible traerConsumibles(int id) {

        String query = "SELECT * FROM Consumible WHERE id = ?";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {

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

                    return new Consumible(productoId, nombre, cantidad, precio, peso, prioridad, utilidad);

                }
            }

        } catch (SQLException e) {

            System.err.println("Error al traer consumibles " + e.getMessage());

        }

        return null;
    }

}
