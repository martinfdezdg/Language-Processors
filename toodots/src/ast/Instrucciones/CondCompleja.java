package ast.Instrucciones;

import ast.*;
import ast.Expresiones.*;
import ast.Tipos.*;
import java.util.List;
import java.util.ArrayList;

public class CondCompleja extends I {
    private E exp;
    private List<Nodo> instrucciones1;
    private List<Nodo> instrucciones2;
    
    public CondCompleja(E exp, List<Nodo> instrucciones1, List<Nodo> instrucciones2, int fila, int columna) {
        super(fila, columna);
        this.exp = exp;
        this.instrucciones1 = instrucciones1;
        this.instrucciones2 = instrucciones2;
    }
  
    // GET
    public E getCondicion() {
        return exp;
    }
    public List<Nodo> getInstrucciones1() {
        return instrucciones1;
    }
    public List<Nodo> getInstrucciones2() {
        return instrucciones2;
    }
    public TipoI getTipo() {
       return TipoI.CONDCOM;
    }
    // PRINT
    public String toString() {
        return "Condicion compleja";
    }
    public List<Nodo> getChildren() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.add(new NodoAux("Condicion",exp,fila,columna));
        lista.add(new NodoAux("Rama true",instrucciones1,fila,columna));
        lista.add(new NodoAux("Rama false",instrucciones2,fila,columna));
        return lista;
    }
}
