package ast.Instrucciones;

import ast.*;
import ast.Expresiones.*;
import ast.Tipos.*;
import java.util.List;
import java.util.ArrayList;

public class Imprimir extends I {
    private E exp;
    
    public Imprimir(E exp, int fila, int columna) {
        super(fila, columna);
        this.exp = exp;
    }
  
    // GET
    public E getExp() {
        return exp;
    }
    public TipoI getTipo() {
        return TipoI.PRINT;
    }
    // PRINT
    public String toString() {
        return "Imprimir";
    }
    public List<Nodo> getChildren() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.add(exp);
        return lista;
    }
}
