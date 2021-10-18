package ast.Instrucciones;

import ast.*;
import ast.Expresiones.*;
import ast.Tipos.*;
import java.util.List;
import java.util.ArrayList;

public class Llamada extends I {
    private Id id;
    private List<Nodo> argumentos;
    private DFuncion dFuncion;
    
    public Llamada(Id id, List<Nodo> argumentos, int fila, int columna) {
        super(fila, columna);
        this.id = id;
        this.argumentos = argumentos;
    }
  
    // SET
    public void setFuncion(DFuncion dFuncion) {
        this.dFuncion = dFuncion;
    }
    // GET
    public Id getId() {
        return id;
    }
    public List<Nodo> getArgumentos() {
        return argumentos;
    }
    public DFuncion getFuncion() {
        return dFuncion;
    }
    public TipoI getTipo() {
        return TipoI.LLAM;
    }
    // PRINT
    public String toString() {
        return "Llamada";
    }
    public List<Nodo> getChildren() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.add(id);
        lista.add(new NodoAux("Argumentos",argumentos,fila,columna));
        return lista;
    }
}
