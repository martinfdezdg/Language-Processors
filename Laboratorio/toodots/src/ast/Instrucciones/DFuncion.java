package ast.Instrucciones;

import ast.*;
import ast.Expresiones.*;
import ast.Tipos.*;
import java.util.List;
import java.util.ArrayList;

public class DFuncion extends I {
    private Id id;
    private T tipoDatos;
    private List<Nodo> parametros;
    private List<Nodo> instrucciones;
    private E exp = null;
    
    public DFuncion(Id id, T tipoDatos, List<Nodo> parametros, List<Nodo> instrucciones, E exp, int fila, int columna) {
        super(fila, columna);
        this.id = id;
        this.tipoDatos = tipoDatos;
        this.parametros = parametros;
        this.instrucciones = instrucciones;
        this.exp = exp;
    }
    public DFuncion(Id id, T tipoDatos, List<Nodo> parametros, List<Nodo> instrucciones, int fila, int columna) {
        super(fila, columna);
        this.id = id;
        this.tipoDatos = tipoDatos;
        this.parametros = parametros;
        this.instrucciones = instrucciones;
    }
    
    // GET
    public Id getId() {
        return id;
    }
    public T getTipoDatos() {
        return tipoDatos;
    }
    public List<Nodo> getParametros() {
        return parametros;
    }
    public List<Nodo> getInstrucciones() {
        return instrucciones;
    }
    public E getExp() {
        return exp;
    }
    public TipoI getTipo() {
        return TipoI.DFUN;
    }
    // PRINT
    public String toString() {
        return "Funcion";
    }
    public List<Nodo> getChildren() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.add(id);
        lista.add(tipoDatos);
        lista.add(new NodoAux("Parametros",parametros,fila,columna));
        lista.add(new NodoAux("Bloque anidado",instrucciones,fila,columna));
        if (exp != null) {
            lista.add(new NodoAux("Return",exp,fila,columna));
        }
        return lista;
    }
}
