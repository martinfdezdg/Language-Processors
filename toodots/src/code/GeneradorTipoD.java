package code;

import ast.*;
import ast.Instrucciones.*;
import ast.Expresiones.*;
import ast.Tipos.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class GeneradorTipoD {
    TablaBloques tabla;
    
    public GeneradorTipoD(TablaBloques tabla) {
        this.tabla = tabla;
    }
    
    // Identificador
    public List<String> code(Id id, int bloque) {
        List<String> codigo = new ArrayList<String>();
        
        codigo.add("i32.const " + tabla.getDireccion(bloque,id.getNombre()));
        codigo.add("i32.const " + tabla.getDireccionMarco(bloque));
        codigo.add("i32.add");

        return codigo;
    }
    
    // Declaracion array
    public List<String> code(Id id, int iterador, int bloque) {
        List<String> codigo = new ArrayList<String>();
        
        int direccion = tabla.getDireccion(bloque,id.getNombre())+iterador;
        
        codigo.add("i32.const " + direccion);
        codigo.add("i32.const " + tabla.getDireccionMarco(bloque));
        codigo.add("i32.add");

        return codigo;
    }
    
    // Acceso array
    public List<String> code(Id id, E posicion, int tam, int bloque) {
        GeneradorTipoE generadorTipoE = new GeneradorTipoE(tabla);
        List<String> codigo = new ArrayList<String>();
    
        codigo.addAll(code(id,bloque));
        codigo.add("i32.const " + tam);
        codigo.addAll(generadorTipoE.code(posicion,bloque));
        codigo.add("i32.mul");
        codigo.add("i32.add");
        
        return codigo;
    }
}
