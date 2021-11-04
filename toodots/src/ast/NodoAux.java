package ast;

import ast.Instrucciones.*;
import ast.Expresiones.*;
import ast.Tipos.*;
import java.util.List;
import java.util.ArrayList;

public class NodoAux extends Nodo {
    private String id;
    private List<Nodo> instrucciones = null;
    private E exp = null;
    
    public NodoAux(String id, List<Nodo> instrucciones, int fila, int columna) {
        super(fila,columna);
        this.id = id;
        this.instrucciones = instrucciones;
    }
    
    public NodoAux(String id, E exp, int fila, int columna) {
        super(fila,columna);
        this.id = id;
        this.exp = exp;
    }
    
    public String toString() {
        return id;
    }
    public List<Nodo> getChildren() {
        if (instrucciones != null) {
            return instrucciones;
        } else {
            List<Nodo> lista = new ArrayList<Nodo>();
            lista.add(exp);
            return lista;
        }
    }
}
