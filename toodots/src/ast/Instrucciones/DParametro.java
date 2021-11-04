package ast.Instrucciones;

import ast.*;
import ast.Expresiones.*;
import ast.Tipos.*;
import java.util.List;
import java.util.ArrayList;

public class DParametro extends I {
    private Id id;
    private T tipoDatos;
    
    public DParametro(Id id, T tipoDatos, int fila, int columna) {
        super(fila,columna);
        this.id = id;
        this.tipoDatos = tipoDatos;
    }
    
    // GET
    public Id getId() {
        return id;
    }
    public T getTipoDatos() {
        return tipoDatos;
    }
    public TipoI getTipo() {
        return TipoI.DPARAM;
    }
    // PRINT
    public String toString() {
        return "Parametro";
    }
    public List<Nodo> getChildren() {
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.add(id);
        lista.add(tipoDatos);
        return lista;
    }
}
