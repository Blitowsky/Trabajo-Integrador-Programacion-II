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

/**
 *
 * @author blitowsky
 * 
 **/

public class GestorDAOs {

    public static int obtenerInt(String atributoBuscado, String clase, String condicionWhere) {
        
        int atributo;
        atributo = -1;
        
        String searchQuery = "SELECT " + atributoBuscado + " FROM " + clase + " WHERE nombre = ?";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(searchQuery);) {

            pstmt.setString(1, condicionWhere);
            
            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {

                    atributo = rs.getInt(atributoBuscado);
                
                }
            }

        } catch (SQLException e) {

            System.err.println("Error al buscar atributo" + e.getMessage());

        }
        
    
        return atributo;
    }
    

    public static int obtenerInt(String atributoBuscado, String clase, int idElemento) {

        int atributo;
        atributo = -1;

        String searchQuery = "SELECT " + atributoBuscado + " FROM " + clase + " WHERE id = ?";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(searchQuery);) {

            pstmt.setInt(1, idElemento);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {

                    atributo = rs.getInt(atributoBuscado);

                }

            }

        } catch (SQLException e) {

            System.err.println("Error al buscar atributo" + e.getMessage());

        }

        return atributo;

    }

    public static double obtenerDouble(String atributoBuscado, String clase, String nombreElemento) {

        double atributo;
        atributo = -1;

        String searchQuery = "SELECT " + atributoBuscado + " FROM " + clase + " WHERE nombre = ?";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(searchQuery);) {

            pstmt.setString(1, nombreElemento);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {

                    atributo = rs.getDouble(nombreElemento);

                }

            }

        } catch (SQLException e) {

            System.err.println("Error al buscar atributo" + e.getMessage());

        }

        return atributo;

    }

    public static double obtenerDouble(String atributoBuscado, String clase, int idElemento) {

        double atributo;
        atributo = -1;

        String searchQuery = "SELECT " + atributoBuscado + " FROM " + clase + " WHERE id = ?";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(searchQuery);) {

            pstmt.setInt(1, idElemento);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {

                    atributo = rs.getDouble(atributoBuscado);

                }

            }

        } catch (SQLException e) {

            System.err.println("Error al buscar atributo" + e.getMessage());

        }

        return atributo;

    }

    public static String obtenerString(String atributoBuscado, String clase, String nombreElemento) {

        String atributo;
        atributo = null;

        String searchQuery = "SELECT " + atributoBuscado + " FROM " + clase + " WHERE nombre = ?";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(searchQuery);) {

            pstmt.setString(1, nombreElemento);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {

                    atributo = rs.getString(nombreElemento);

                }

            }

        } catch (SQLException e) {

            System.err.println("Error al buscar atributo" + e.getMessage());

        }

        return atributo;

    }

    public static String obtenerString(String atributoBuscado, String clase, int idElemento) {

        String atributo;
        atributo = null;

        String searchQuery = "SELECT " + atributoBuscado + " FROM " + clase + " WHERE id = ?";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(searchQuery);) {

            pstmt.setInt(1, idElemento);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {

                    atributo = rs.getString(atributoBuscado);

                }

            }

        } catch (SQLException e) {

            System.err.println("Error al buscar atributo" + e.getMessage());

        }

        return atributo;

    }

    public static boolean obtenerBoolean(String atributoBuscado, String clase, String nombreElemento) {

        boolean atributo;
        atributo = false;

        String searchQuery = "SELECT " + atributoBuscado + " FROM " + clase + " WHERE nombre = ?";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(searchQuery);) {

            pstmt.setString(1, nombreElemento);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {

                    atributo = rs.getBoolean(nombreElemento);

                }

            }

        } catch (SQLException e) {

            System.err.println("Error al buscar atributo" + e.getMessage());

        }

        return atributo;

    }

    public static boolean obtenerBoolean(String atributoBuscado, String clase, int idElemento) {

        boolean atributo;
        atributo = false;

        String searchQuery = "SELECT " + atributoBuscado + " FROM " + clase + " WHERE id = ?";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(searchQuery);) {

            pstmt.setInt(1, idElemento);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {

                    atributo = rs.getBoolean(atributoBuscado);

                }

            }

        } catch (SQLException e) {

            System.err.println("Error al buscar atributo" + e.getMessage());

        }

        return atributo;

    }

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

    public static int devolverId(String nombreElemento, String nombreEntidad) {

        String query = "SELECT id from " + nombreEntidad + " WHERE nombre = ?";

        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, nombreElemento);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) { // Verificar si hay resultados
                    return rs.getInt("id");
                } else {
                    System.out.println("No se encontró el elemento: " + nombreElemento);
                    return -1;
                }

            }

        } catch (SQLException e) {

            System.out.println("Error al buscar la id " + e.getMessage());

        }

        return -1;

    }
    
   public static void modificarNombre(String nuevoNombre, int id, String clase){
       
       String query = "UPDATE " + clase + " SET nombre = ? WHERE id = ?";
       
       try(Connection conn = Database.connect();
           PreparedStatement pstmt = conn.prepareStatement(query)){
           
           pstmt.setString(1, nuevoNombre);
           pstmt.setInt(2, id);
           
           pstmt.executeUpdate();

           
       } catch (SQLException e){
           
           System.err.println("Error al actualizar el nombre " + e.getMessage());
           
       }
       
   }
        
        
    
            
            
            
            
            
}
