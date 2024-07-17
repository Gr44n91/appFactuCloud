package com.FactuCloud.app.util;

import java.util.regex.Pattern;

public class ValidarDniCif {

    // Expresión regular para validar CIF o DNI
    private static final String CIF_DNI_REGEX = "^(?:(?:[0-9]{8}[A-Za-z])|(?:[A-Za-z][0-9]{7}[A-Za-z0-9]))$";
    private static final Pattern CIF_DNI_PATTERN = Pattern.compile(CIF_DNI_REGEX);

    // Método para validar un CIF o DNI
    public static boolean validarCifDni(String cifDni) {
        return cifDni != null && CIF_DNI_PATTERN.matcher(cifDni).matches();
    }
    // Método para validar la letra de un DNI o CIF
    public static boolean validarLetra(String dniCif) {
        // Convertir el número a mayúsculas
        dniCif = dniCif.toUpperCase();

        // Extraer los dígitos del DNI o CIF
        String digitos = dniCif.substring(0, dniCif.length() - 1);

        // Extraer la letra del DNI o CIF
        char letra = dniCif.charAt(dniCif.length() - 1);

        // Calcular el resto de la división entre los dígitos y 23
        int resto = Integer.parseInt(digitos) % 23;

        // Array con las letras posibles para un DNI
        char[] letrasDni = "TRWAGMYFPDXBNJZSQVHLCKE".toCharArray();

        // Array con las letras posibles para un CIF
        char[] letrasCif = "JABCDEFGHI".toCharArray();

        // Verificar si la letra corresponde al DNI o CIF
        if (digitos.length() == 8) {
            return letra == letrasDni[resto];
        } else if (digitos.length() == 7) {
            return letra == letrasCif[resto];
        } else {
            return false; // Longitud incorrecta para un DNI o CIF
        }
    }
}
