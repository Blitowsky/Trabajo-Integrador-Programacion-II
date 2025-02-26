/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pablomorata.gestorapicola;


import static pablomorata.gestorapicola.Cliente.scanner;
import pablomorata.gestorapicola.DAO.UsuariosDAO;
import pablomorata.gestorapicola.Utiles.Validador;

/**
 *
 * @author blitowsky
 */
public class Administrador extends Usuario {


    int entrada;
    Colmena colmena = new Colmena();
    Producto producto;
    Consumible consumible;
    Menu menu;
    boolean salir = false;

    public Administrador() {
        
        colmena = new Colmena();
        producto = new Producto();
        consumible = new Consumible();


    }

    public Administrador(String nombre, String ubicacion, int id, int prioridad) {

        super(nombre, id, prioridad);
        colmena = new Colmena();
        producto = new Producto();
        consumible = new Consumible();

    }

    @Override
    public void selectorOpciones(String nombre) {

        while (!salir) {

            System.out.println("Ingrese que área desea administrar:");
            System.out.println(" 0: Cerrar el programa \n 1: Colmenas \n 2: Productos \n 3: Consumibles");
            entrada = Validador.entreParametros(0, 3);

            switch (entrada) {

                case 0 ->
                    salir = true;
                case 1 ->
                    colmena.selectorOpciones();
                case 2 ->
                    producto.selectorOpciones();
                case 3 ->
                    consumible.selectorOpciones();
                case 4 -> modificarNombre(nombre);
                case 5 -> modificarContraseña(nombre);

            }

        }

    }
    public void modificarNombre(String nombreActual){
        
        System.out.println("Ingrese el nuevo nombre:");
        String nuevoNombre;
        nuevoNombre = scanner.next();
        
        UsuariosDAO.modificarDatosRegistro("Administrador", "nombre", nuevoNombre, nombreActual);
        
        
    }
    public void modificarContraseña(String nombreActual){
        
        System.out.println("Ingrese su contraseña actual");
        String contraseñaActual = scanner.next();
        
        while (menu.validarUsuario("Cliente", contraseñaActual)) {
            
        }
        
        System.out.println("Ingrese el nuevo nombre:");
        String nuevoNombre;
        nuevoNombre = scanner.next();
        
        UsuariosDAO.modificarDatosRegistro("Administrador", "contraseña", nuevoNombre, nombreActual);
        
    }

}
