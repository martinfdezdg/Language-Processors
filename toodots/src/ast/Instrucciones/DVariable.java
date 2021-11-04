package ast.Instrucciones;

import ast.*;
import ast.Expresiones.*;
import ast.Tipos.*;
import java.util.List;
import java.util.ArrayList;

public class DVariable extends I {
    private Id id;
    private T tipoDatos;
    private E exp = null;
    
    public DVariable(Id id, T tipoDatos, int fila, int columna) {
        super(fila,columna);
        this.id = id;
        this.tipoDatos = tipoDatos;
    }
    
    public DVariable(Id id, T tipoDatos, E exp, int fila, int columna) {
        super(fila,columna);
        this.id = id;
        this.tipoDatos = tipoDatos;
        this.exp = exp;
    }
    
    // GET
    public Id getId() {
        return id;
    }
    public E getExp() {
        return exp;
    }
    public T getTipoDatos() {
        return tipoDatos;
    }
    public TipoI getTipo() {
        return TipoI.DVAR;
    }
    // PRINT
    public String toString() {
        return "Variable";
    }
    public List<Nodo> getChildren() {
        ArrayList<Nodo> lista = new ArrayList<Nodo>();
        lista.add(id);
        lista.add(tipoDatos);
        if (exp != null) {
            lista.add(new NodoAux("Valor",exp,fila,columna));
        }
        return lista;
    }
}
