/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

 /* CÓDIGO ÚTIL PARA VER ESTRUCTURAS O TENER DE REFERENCIA:

        //return !(parametro <= hasta && parametro >= desde);



 */
package pablomorata.gestorapicola;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author blitowsky
 *
 */
public class Inventario {

    private int cantColmenas = 0;
    Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    ArrayList<Colmena> colmenas;
    ArrayList<Object> herramientas;
    ArrayList<Object> consumibles;

    public Inventario() {

        colmenas = new ArrayList<>();
        herramientas = new ArrayList<>();
        consumibles = new ArrayList<>();

    }

    public int idUnico(int id) { //agregar otro parámetro numérico que luego se transforme en alguno de los Arrays que requiera id única

        for (Colmena puntero : colmenas) {

            while (puntero.getid() == id) {

                System.out.println("La id ingresada ya existe, ingrese otra");
                id = scanner.nextInt();

            }
        }
        return id;

    }

    public int entreParametros(int parametro, int desde, int hasta) {

        //El return está pensado para que sea usado en un while(false)
        while (!(parametro <= hasta && parametro >= desde)) {

            System.out.println("Ingrese un número desde " + desde + "hasta " + hasta);
            parametro = scanner.nextInt();

        }
        return parametro;

    }

    public Colmena devolverColmena(int id) {

        for (Colmena puntero : colmenas) {

            if (puntero.getid() == id) {

                return puntero;

            }

        }
        return null;

    }

    public void sumarColmena(Colmena idColmena) {

        colmenas.add(idColmena);
        cantColmenas++;

    }

    public void restarColmena(Colmena idColmena) {

        colmenas.add(idColmena);
        cantColmenas--;

    }

    public void mostrarListaColmenas() {

        String sql = "SELECT * FROM Colmena";

        try (Connection conn = Database.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("ID"));
                System.out.println("Posee abejas? : " + rs.getBoolean("Abejas"));
                System.out.println("Nivel de miel: " + rs.getInt("Marcos"));
                System.out.println("Cantidad de marcos: " + rs.getInt("Miel"));
                System.out.println("Estado: " + rs.getString("Estado"));
                System.out.println("-------------------");
            }
        } catch (SQLException e) {
            System.err.println("Error al listar colmenas: " + e.getMessage());
        }
        Database.disconnect();

        for (Colmena puntero : colmenas) {

            System.out.println("\n Colmena número: " + puntero.getid() + ":");
            System.out.println("Posee abejas: " + puntero.isAbejas());
            System.out.println("El nivel de miel es: " + puntero.getCantMiel());
            System.out.println("Posee " + puntero.getCantMarcos() + " marcos");
            System.out.println("El estado de la colmena es " + puntero.getEstadoColmena() + "\n");

        }

    }

    public void eliminarColmena(int id) {

        Iterator iterator = colmenas.iterator();

        while (iterator.hasNext()) {

            if (iterator.next() == devolverColmena(id)) {

                iterator.remove();

            }

        }

    }

    public void setcantColmenas(int cantColmenas) {

        this.cantColmenas = cantColmenas;

    }

    public int getcantColmenas() {

        return cantColmenas;

    }

}
