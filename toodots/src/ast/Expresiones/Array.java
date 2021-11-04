package ast.Expresiones;

import ast.*;
import ast.Instrucciones.*;
import ast.Tipos.*;
import java.util.List;
import java.util.ArrayList;

public class Array extends E {
    private List<Nodo> expresiones;
    
    public Array(List<Nodo> expresiones, int fila, int columna) {
        super(fila,columna);
        this.expresiones = expresiones;
    }
    
    // GET
    public List<Nodo> getExpresiones() {
        return expresiones;
    }
    public TipoE getTipo() {
        return TipoE.ARRAY;
    }
    // PRINT
    public String toString() {
        return "Vector";
    }
    public List<Nodo> getChildren() {
        return expresiones;
    }
}
