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
public class ColmenaDAO {

    public static void traerColmenas() {

        String sql = "SELECT * FROM Colmena";

        try (Connection conn = Database.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("id: " + rs.getInt("id"));
                System.out.println(rs.getBoolean("Abejas") + " posee abejas");
                System.out.println("Nivel de miel: " + rs.getInt("Marcos"));
                System.out.println("Cantidad de marcos: " + rs.getInt("Miel"));
                System.out.println("Estado: " + rs.getString("Estado"));
                System.out.println("-------------------");
            }
        } catch (SQLException e) {
            System.err.println("Error al listar colmenas: " + e.getMessage());
        }
        Database.disconnect();

    }

    public static void agregarColmena(boolean abejas, int miel, int marcos, String estado) {

        String sql = "INSERT INTO Colmena (Abejas, Marcos, Miel, Estado) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, abejas);
            pstmt.setInt(2, miel);
            pstmt.setInt(3, marcos);
            pstmt.setString(4, estado);
            pstmt.executeUpdate();
            System.out.println("Colmena insertada correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar colmena: " + e.getMessage());
        }
        Database.disconnect();

    }

    public static void eliminarColmena(int id) {

        String eliminarSql = "DELETE FROM Colmena WHERE id = ?";

        try (Connection conn = Database.connect(); PreparedStatement eliminarStmt = conn.prepareStatement(eliminarSql)) {

            eliminarStmt.setInt(1, id);
            eliminarStmt.executeUpdate();

        } catch (SQLException e) {

            System.err.println("Error al eliminar colmena: " + e.getMessage());

        }
        Database.disconnect();
    }

    

    public static void modificarColmena(int id, String columna, String nuevoValor) {

        String query = "UPDATE Colmena SET " + columna + " = ? WHERE id = ?";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {

            switch (columna) {

                case "abejas" -> {
                    boolean nuevoValorBoolean = "1".equals(nuevoValor);

                    pstmt.setBoolean(1, nuevoValorBoolean);
                }

                case "miel", "marcos" -> {
                    int nuevoValorInt = Integer.parseInt(nuevoValor);
                    pstmt.setInt(1, nuevoValorInt);
                }

                case "estado" -> pstmt.setString(1, nuevoValor);
                
                default -> throw new IllegalArgumentException("Columna no válida" + columna);

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
