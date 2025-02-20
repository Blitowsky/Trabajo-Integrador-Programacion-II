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
public class AdministradorDAO {
    int balance;
    
    public void modificarBalance(String nombreApicultor, int idProducto, int precio, int cantidad, boolean positivo){
        
        String modificador = "UPDATE Administrador SET balance = ? WHERE nombre = ? ";
        
        int balancePrevio = GestorDAOs.obtenerInt("balance", "Administrador", nombreApicultor);
        
        try(Connection conn = Database.connect(); 
                PreparedStatement pstmt = conn.prepareStatement(modificador))
        {
            
            System.out.println(precio * cantidad + " Este es el precio agregado y este el previo " + balancePrevio);
            if (positivo)  balance = balancePrevio + precio * cantidad;
            else balance = balancePrevio - precio * cantidad;
           
            
            pstmt.setInt(1, balance);
            pstmt.setString(2, nombreApicultor);     
            pstmt.executeUpdate();
 
            
        }catch(SQLException e){
            
            System.err.println("Error al modificar el balance");
            
        }
            
    }
    
    
}
