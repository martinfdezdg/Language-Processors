package launcher;

import asint.*;
import ast.*;
import alex.AnalizadorLexicoTiny;
import asem.AnalizadorSemanticoTiny;
import code.GeneradorCodigo;
import errors.GestionErroresTiny;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Main {
    public static String fichero;
    private static String nombreFichero;
    
    public static void main(String[] args) throws Exception {
        System.out.println("\n  " + "\033[1m\033[4m" + "TOODOTS" + "\033[0m" + "\n");
        
        fichero = args[0];
        nombreFichero = fichero.substring(fichero.lastIndexOf("/")+1,fichero.lastIndexOf("."));
        System.out.println("  Fichero: " + nombreFichero + ".txt\n");
        
        Scanner ficheroEscaneado = new Scanner(new File(fichero));
        int i = 1;
        while (ficheroEscaneado.hasNextLine()) {
            System.out.println("  \033[2m" + i + "\033[0m  " + ficheroEscaneado.nextLine());
            i++;
        }
        
        System.out.println("\033[2m" + "\n+ ANALISIS DEL CODIGO: " + nombreFichero);
        Reader input = new InputStreamReader(new FileInputStream(fichero));

        System.out.println("- Analizando el lexico y la sintaxis...");
        AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
        AnalizadorSintacticoTiny asint = new AnalizadorSintacticoTiny(alex);
        P programa = null;
        try {
            programa = (P) asint.parse().value;
        } catch (Exception e) {
            e.printStackTrace();
            GestionErroresTiny.errorExcepcion();
        }
        
        GestionErroresTiny.errores();
        
        System.out.println("- Analizando la semantica...");
        AnalizadorSemanticoTiny asem = new AnalizadorSemanticoTiny(programa);
        try {
            asem.parse();
        } catch (Exception e) {
            e.printStackTrace();
            GestionErroresTiny.errorExcepcion();
        }
        
        GestionErroresTiny.errores();
        
        System.out.println("\n+ VISUALIZACION DEL CODIGO: " + nombreFichero);
        System.out.println("- Mostrando arbol sintactico...\n" + "\033[0m");
        System.out.println(programa.start());
        
        System.out.println("\033[2m" + "+ GENERACION DEL CODIGO: " + nombreFichero);
        try {
            GeneradorCodigo gencode = new GeneradorCodigo(programa,nombreFichero);
            gencode.parse();
            System.out.println("- Generando el fichero " + nombreFichero + ".wat...\n" + "\033[0m");
        } catch (Exception e) {
            System.out.println("- Codigo no disponible\n" + "\033[0m");
        }
    }
}
