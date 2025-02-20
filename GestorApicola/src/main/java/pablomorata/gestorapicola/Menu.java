/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola;

import java.util.Scanner;
import pablomorata.gestorapicola.DAO.GestorDAOs;
import pablomorata.gestorapicola.DAO.UsuariosDAO;
import pablomorata.gestorapicola.Utiles.Validador;

/**
 *
 * @author blitowsky
 */
public class Menu {

    int entrada;
    Usuario administrador;
    Usuario cliente;
    Usuario proveedores;
    boolean salir = false;
    Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    public Menu() {

        administrador = new Administrador();
        cliente = new Cliente();
        proveedores = new Proveedores();

    }

    public void selectorOpciones() {

        while (!salir) {

            System.out.println("Ingrese su usuario: \n1: Administrador \n2: Cliente \n3: Proveedor \n4: Crear nuevo usuario \n0: Salir");
            entrada = Validador.entreParametros(0, 4);
           

            ///validar entrada con excepciones
        
            switch (entrada) {
                case 0 ->
                    salir = true;
                    
                case 1 -> {

                    if (validarUsuario("Administrador")) {
                        administrador.selectorOpciones();
                    }
                }

                case 2 -> {

                    if (validarUsuario("Cliente")) {
                        cliente.selectorOpciones();
                    }
                }

                case 3 -> {

                    if (validarUsuario("Proveedor")) {
                        proveedores.selectorOpciones();
                    }
                }
                case 4 -> nuevoUsuario();
            }
        }
    }

    public boolean validarUsuario(String tipoUsuario) {

        int id;
        int contador = 0;
        boolean aprobado = false;
        System.out.println("Ingrese el nombre de su usuario:");
        String nombre = scanner.next();
        
        if (GestorDAOs.obtenerInt("id", tipoUsuario, nombre) != -1) {
            id = GestorDAOs.obtenerInt("id", tipoUsuario, nombre);

            do {
                
                System.out.println("Ingrese la contraseña");
                String contraseña = scanner.next();
                
                if (contraseña.equals(GestorDAOs.obtenerString("contraseña", tipoUsuario, id))) {

                    return true;

                } else {
                    
                    System.out.println("La contraseña ingresada es incorrecta, intente nuevamente...");
                    
                }
                contador++;

            } while (contador < 6 && !aprobado);

            if (contador < 6) {

                System.out.println("Ha superado la cantidad máxima de intentos");
                salir = true;

            }

        } else {

            System.out.println("El nombre ingresado no corresponde a ningún usuario registrado");

        }
        return false;
    }

    public void nuevoUsuario() {
        
        String tipoUsuario = null;

        System.out.println("Ingrese el tipo de usuario que desea registrar ");
        int entradaTipoUsuario = scanner.nextInt();
        
        switch (entradaTipoUsuario) {
            case 1 ->
                tipoUsuario = "Administrador";
            case 2 ->
                tipoUsuario = "Cliente";
            case 3 ->
                tipoUsuario = "Proveedor";
        }

        System.out.println("Ingrese el nombre del " + tipoUsuario);
        String nombre = scanner.next();

        System.out.println("Ingrese la contraseña del " + tipoUsuario);
        String contraseña = scanner.next();

        UsuariosDAO.registrarNuevoUsuario(nombre, contraseña, tipoUsuario);
    }

}
