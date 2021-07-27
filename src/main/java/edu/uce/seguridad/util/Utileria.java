package edu.uce.seguridad.util;

import edu.uce.seguridad.model.Usuario;

import java.util.Random;

public class Utileria {

    private static Usuario nuevoUsuario;

    public static Usuario generarUsuario(String nombre, String apellido, String role) {
        nuevoUsuario = new Usuario();
        nuevoUsuario.setNombreUsuario(generarNombreUsuario(nombre, apellido));
        nuevoUsuario.setContrasena(generarContrasena());
        nuevoUsuario.setRole(role);
        return nuevoUsuario;
    }

    public static String generarNombreUsuario(String nombre, String apellido) {
        char[] letra = new char[2];
        // Obtengo la primero y ultima letra del nombre
        letra[0] = nombre.toLowerCase().charAt(0);
        letra[1] = nombre.toLowerCase().charAt(nombre.length() - 1);

        Random r = new Random();
        int numero1 = r.nextInt(50) + 1; // Numero entre 1 y 50
        int numero2 = r.nextInt(50) + 1;
        StringBuilder nombreUsuario = new StringBuilder();
        // Lo concateno con el apellido
        nombreUsuario.append(letra[0])
                .append(apellido.toLowerCase().trim())
                .append(letra[1]).append(numero1 += numero2);
        return nombreUsuario.toString();
    }

    public static String generarContrasena() {
        Random r = new Random();
        StringBuilder pass = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            pass.append(r.nextInt(10) + 1);
        }
        for (int i = 0; i < 3; i++) {
            pass.append((char) (r.nextInt(26) + 'A'));
        }
        return pass.toString();
    }

}
