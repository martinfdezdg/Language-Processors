package asem;

import ast.*;
import ast.Instrucciones.*;
import java.util.List;
import java.util.ArrayList;

public class AnalizadorSemanticoTiny {
    private P programa;
    private TablaSimbolos tabla = new TablaSimbolos();
    
    public AnalizadorSemanticoTiny(P programa) {
        this.programa = programa;
        
    }
    public void parse() throws Exception {
        List<Nodo> objetos = programa.getObjetos();
        List<Nodo> proceso = programa.getProceso();
        
        // Vinculacion
        tabla.abreBloque();
        
        VinculadorTipoI vinculadorTipoI = new VinculadorTipoI(tabla);
        for (Nodo nodo : objetos) {
            I instruccion = (I) nodo;
            vinculadorTipoI.vinculacion(instruccion);
        }
        if (proceso != null) {
            for (Nodo nodo : proceso) {
                I instruccion = (I) nodo;
                vinculadorTipoI.vinculacion(instruccion);
            }
        }
        
        tabla.cierraBloque();
        
        // Comprobacion de tipos
        boolean tiposCorrectos = true;
        
        ComprobadorTipoI comprobadorTipoI = new ComprobadorTipoI();
        for (Nodo nodo : objetos) {
            I instruccion = (I) nodo;
            try {
                if (!comprobadorTipoI.comprobacion(instruccion)) tiposCorrectos = false;
            } catch (NullPointerException e) {
                tiposCorrectos = false;
            }
        }
            
        if (proceso != null) {
            for (Nodo nodo : proceso) {
                I instruccion = (I) nodo;
                try {
                    if (!comprobadorTipoI.comprobacion(instruccion)) tiposCorrectos = false;
                } catch (NullPointerException e) {
                    tiposCorrectos = false;
                }
            }
        }
        
        // if (!tiposCorrectos) throw new Exception();
    }
}
