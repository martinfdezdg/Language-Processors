package ast.Instrucciones;

import ast.*;
import ast.Expresiones.*;
import ast.Tipos.*;
import java.util.List;
import java.util.ArrayList;

public class DPuntero extends I {
    private Id id;
    private T tipoDatos;
    private E valor = null;
    
    public DPuntero(Id id, T tipoDatos, int fila, int columna) {
        super(fila,columna);
        this.id = id;
        this.tipoDatos = tipoDatos;
    }
    public DPuntero(Id id, T tipoDatos, E valor, int fila, int columna) {
        super(fila,columna);
        this.id = id;
        this.tipoDatos = tipoDatos;
        this.valor = valor;
    }
  
    // GET
    public Id getId() {
        return id;
    }
    public T getTipoDatos() {
        return tipoDatos;
    }
    public E getValor() {
        return valor;
    }
    public TipoI getTipo() {
        return TipoI.DPUNT;
    }
    // PRINT
    public String toString() {
        return "Puntero";
    }
    public List<Nodo> getChildren() {
        ArrayList<Nodo> lista = new ArrayList<Nodo>();
        lista.add(id);
        lista.add(tipoDatos);
        if (valor != null) {
            lista.add(new NodoAux("Valor",valor,fila,columna));
        }
        return lista;
    }
}
