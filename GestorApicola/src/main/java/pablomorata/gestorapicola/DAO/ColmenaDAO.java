/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pablomorata.gestorapicola.Entidades.Colmena;
import pablomorata.gestorapicola.Database;

/**
 *
 * @author blitowsky
 */
public class ColmenaDAO {

    public Colmena traerColmenas(int id) {

        String sql = "SELECT * FROM Colmena WHERE id = ?";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id); // Asignar el valor del parámetro

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) { // Mover el cursor a la primera fila
                    int colmenaId = rs.getInt("id");
                    boolean abejas = rs.getBoolean("abejas");
                    int miel = rs.getInt("miel");
                    int marcos = rs.getInt("marcos");
                    String estado = rs.getString("observaciones");
                    int peso = rs.getInt("peso");
                    String utilidad = rs.getString("utilidad");
                    int prioridad = rs.getInt("prioridad");
                    // Crear y devolver el objeto Colmena
                    return new Colmena(colmenaId, abejas, miel, marcos, estado, peso,prioridad ,utilidad);
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al retornar el objeto colmena colmenas: " + e.getMessage());
        }
        return null;

    }

    public void agregarColmena(boolean abejas, int miel, int marcos, String observaciones, int peso, String utilidad, int prioridad) {

        String sql = "INSERT INTO Colmena (abejas, marcos, miel, observaciones, peso, utilidad, prioridad) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, abejas);
            pstmt.setInt(2, miel);
            pstmt.setInt(3, marcos);
            pstmt.setString(4, observaciones.toLowerCase());
            pstmt.setInt(5, peso);
            pstmt.setString(6, utilidad);
            pstmt.setInt(7, prioridad);

                       
            pstmt.executeUpdate();
            System.out.println("Colmena insertada correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar colmena: " + e.getMessage());
        }

    }

    public void eliminarColmena(int id) {

        String eliminarSql = "DELETE FROM Colmena WHERE id = ?";

        try (Connection conn = Database.connect(); PreparedStatement eliminarStmt = conn.prepareStatement(eliminarSql)) {
            
            eliminarStmt.setInt(1, id);
            eliminarStmt.executeUpdate();

        } catch (SQLException e) {

            System.err.println("Error al eliminar colmena: " + e.getMessage());

        }   
    }

    public void modificarColmena(int id, String columna, String nuevoValor) {

        String query = "UPDATE Colmena SET " + columna + " = ? WHERE id = ?";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {

            switch (columna) {

                case "abejas" -> {

                    pstmt.setBoolean(1, "1".equals(nuevoValor));
                }

                case "miel", "marcos", "peso", "prioridad" -> {
                    
                    pstmt.setInt(1, Integer.parseInt(nuevoValor));
                }

                case "observaciones", "utilidad" ->
                    
                    pstmt.setString(1, nuevoValor);

                default ->
                    throw new IllegalArgumentException("Columna no válida" + columna);

            }
            
            pstmt.setInt(2, id);

            
            pstmt.executeUpdate();
            System.out.println("Colmena actualizada correctamente");

            
        } catch (SQLException e) {

            
            System.err.println("Error al modificar el atributo " + e.getMessage());
            e.printStackTrace();

            
        }
    }
}
