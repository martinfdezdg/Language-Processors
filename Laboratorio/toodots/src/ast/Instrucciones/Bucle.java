package ast.Instrucciones;

import ast.*;
import ast.Expresiones.*;
import ast.Tipos.*;
import java.util.List;
import java.util.ArrayList;

public class Bucle extends I {
    private E exp;
    private List<Nodo> instrucciones;
    
    public Bucle(E exp, List<Nodo> instrucciones, int fila, int columna) {
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
        return TipoI.BUCLE;
    }
    // PRINT
    public String toString() {
        return "Bucle";
    }
    public List<Nodo> getChildren() {
        ArrayList<Nodo> lista = new ArrayList<Nodo>();
        lista.add(new NodoAux("Condicion",exp,fila,columna));
        lista.add(new NodoAux("Bloque anidado",instrucciones,fila,columna));
        return lista;
    }
}
