package errors;

import alex.UnidadLexica;

public class GestionErroresTiny {
    public static int numErroresLexicos = 0;
    public static int numErroresSintacticos = 0;
    public static int numErroresSemanticos = 0;
    
    public static int numErroresExcepciones = 0;
    
    public static void errores() {
        int numErroresTotales = numErroresLexicos + numErroresSintacticos + numErroresSemanticos;
        if (numErroresTotales > 0 || numErroresExcepciones > 0) {
            System.err.println("\033[0;31m");
            System.err.format("%2s","");
            System.err.print("\033[1m" + "[ERRORES]: " + "\033[0;31m");
            if (numErroresTotales == 0) System.err.println("Errores graves encontrados");
            else if (numErroresTotales == 1) System.err.println(numErroresTotales + " error encontrado");
            else System.err.println(numErroresTotales + " errores encontrados");
            System.err.format("%13s","");
            System.err.println("Soluciona los problemas antes de continuar");
            System.err.println("\033[0m");
            System.exit(1);
        }
    }
    
    public static void errorLexico(int fila, String lexema) {
        numErroresLexicos++;
        System.err.println("\033[0;31m");
        System.err.format("%2s","");
        System.err.println("\033[1m" + "[ERROR LEXICO]: " + "\033[0;31m" + "Elemento inesperado " + lexema + " en la fila " + fila);
    }
    public static void errorSintactico(UnidadLexica unidadLexica) {
        numErroresSintacticos++;
        System.err.println("\033[0;31m");
        System.err.format("%2s","");
        System.err.println("\033[1m" + "[ERROR SINTACTICO]: " + "\033[0;31m" + "Elemento inesperado " + unidadLexica.lexema() + " en la fila " + unidadLexica.fila());
        System.err.format("%22s","");
    }
    public static void errorSemantico(int fila, String lexema, String mensaje) {
        numErroresSemanticos++;
        System.err.println("\033[0;31m");
        System.err.format("%2s","");
        System.err.println("\033[1m" + "[ERROR SEMANTICO]: " + "\033[0;31m" + "Elemento inesperado " + lexema + " en la fila " + fila);
        System.err.format("%21s","");
        System.err.println(mensaje);
    }
    public static void errorExcepcion() {
        numErroresExcepciones++;
    }
}
