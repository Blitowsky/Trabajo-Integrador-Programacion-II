/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import pablomorata.gestorapicola.folder.NewJFrame;
import java.util.Scanner;

/**
 *
 * @author blitowsky
 */
public class Menu {

    Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    Inventario inventario = new Inventario();
    NewJFrame frame = new NewJFrame();

    int opcion;
    boolean salir = false;

    public void selectorOpciones() {

        do {

            System.out.println("Ingrese la opción del menú a elegir \n 1: Agregar \n 2: Eliminar \n 3: Modificar \n -1: Mostrar ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:

                    System.out.println("Ingrese la id de la colmena");
                    int id = scanner.nextInt();

                    id = inventario.idUnico(id);

                    System.out.println("La colmena posee abejas?");
                    int hayAbejas = scanner.nextInt();

                    hayAbejas = inventario.entreParametros(hayAbejas, 1, 2);

                    boolean abejas = false;

                    System.out.println("Ingrese el nivel de miel de la colmena (0: sin miel / 10: completamente llena)");
                    int miel = scanner.nextInt();

                    miel = inventario.entreParametros(miel, 0, 10);

                    System.out.println("Ingrese la cantidad de marcos de la colmena");

                    int marcos = scanner.nextInt();

                    marcos = inventario.entreParametros(marcos, 0, 10);

                    System.out.println("Ingrese el estado de la colmena");
                    String estado = scanner.next();

                    if (hayAbejas == 1) {
                        abejas = true;
                    }

                    Colmena colmena = new Colmena(id, abejas, miel, marcos, estado);

                    inventario.sumarColmena(colmena);

                    String sql = "INSERT INTO Colmena (ID, Abejas, Marcos, Miel, Estado) VALUES (?, ?, ?, ?, ?)";

                    try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setInt(1, id);
                        pstmt.setBoolean(2, abejas);
                        pstmt.setInt(3, miel);
                        pstmt.setInt(4, marcos);
                        pstmt.setString(5, estado);
                        pstmt.executeUpdate();
                        System.out.println("Colmena insertada correctamente.");
                    } catch (SQLException e) {
                        System.err.println("Error al insertar colmena: " + e.getMessage());
                    }
                    Database.disconnect();

                    break;

                case 2:

                    System.out.println("Ingrese el id de la colmena a eliminar");
                    id = scanner.nextInt();

                    inventario.eliminarColmena(id);

                    break;

                case 3:

                    System.out.println("Ingrese el id de la colmena a modificar");
                    id = scanner.nextInt();

                    Colmena puntero = inventario.devolverColmena(id);

                    System.out.println("La colmena posee abejas?");
                    abejas = scanner.nextBoolean();
                    puntero.setAbejas(abejas);

                    System.out.println("Ingrese el nivel de miel de la colmena (0: sin miel / 10: completamente llena)");
                    miel = scanner.nextInt();
                    puntero.setCantMiel(miel);

                    System.out.println("Ingrese la cantidad de marcos de la colmena");
                    marcos = scanner.nextInt();
                    puntero.setCantMarcos(marcos);

                    System.out.println("Ingrese el estado de la colmena");
                    estado = scanner.next();
                    puntero.setEstadoColmena(estado);

                case 4:

                case -1:

                    inventario.mostrarListaColmenas();
                    break;

                case 0:
                    salir = true;
                    break;

            }

        } while (salir == false);

    }

}
