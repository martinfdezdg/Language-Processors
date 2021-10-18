package ast.Instrucciones;

import ast.*;
import ast.Expresiones.*;
import ast.Tipos.*;
import java.util.List;
import java.util.ArrayList;

public class Asignacion extends I {
    private Id id = null;
    private E acceso = null;
    private E exp;
    
    public Asignacion(Id id, E exp, int fila, int columna) {
        super(fila,columna);
        this.id = id;
        this.exp = exp;
    }
    
    public Asignacion(E acceso, E exp, int fila, int columna) {
        super(fila,columna);
        this.acceso = acceso;
        this.exp = exp;
    }
    
    // GET
    public Id getId() {
        return id;
    }
    public E getAcceso() {
        return acceso;
    }
    public E getExp() {
        return exp;
    }
    public TipoI getTipo() {
        return TipoI.ASIGN;
    }
    // PRINT
    public String toString() {
        return "Asignacion";
    }
    public List<Nodo> getChildren() {
        ArrayList<Nodo> lista = new ArrayList<Nodo>();
        if (id != null) {
            lista.add(id);
        }
        else lista.add(acceso);
        lista.add(new NodoAux("Valor",exp,fila,columna));
        return lista;
    }
}
