package ast.Instrucciones;

import ast.*;
import ast.Expresiones.*;
import ast.Tipos.*;
import java.util.List;
import java.util.ArrayList;

public class CondBasica extends I {
    private E exp;
    private List<Nodo> instrucciones;
    
    public CondBasica(E exp, List<Nodo> instrucciones, int fila, int columna) {
        super(fila, columna);
        this.exp = exp;
        this.instrucciones = instrucciones;
    }
  
    // GET
    public E getCondicion() {
        return exp;
    }
    public List<Nodo> getInstrucciones() {
        return instrucciones;
    }
    public TipoI getTipo() {
        return TipoI.CONDBAS;
    }
    // PRINT
    public String toString() {
        return "Condicion simple";
    }
    public List<Nodo> getChildren() {
        ArrayList<Nodo> lista = new ArrayList<Nodo>();
        lista.add(new NodoAux("Condicion",exp,this.fila,this.columna));
        lista.add(new NodoAux("Rama true",instrucciones,this.fila,this.columna));
        return lista;
    }
}
