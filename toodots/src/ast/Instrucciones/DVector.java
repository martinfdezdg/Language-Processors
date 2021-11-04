package ast.Instrucciones;

import ast.*;
import ast.Expresiones.*;
import ast.Tipos.*;
import java.util.List;
import java.util.ArrayList;

public class DVector extends I {
    private Id id;
    private T tipoDatos;
    private E longitud = null;
    private List<Nodo> contenido = null; // List<E>
    
    public DVector(Id id, T tipoDatos, int fila, int columna) {
        super(fila,columna);
        this.id = id;
        this.tipoDatos = tipoDatos;
    }
    public DVector(Id id, T tipoDatos, E longitud, int fila, int columna) {
        super(fila,columna);
        this.id = id;
        this.tipoDatos = tipoDatos;
        this.longitud = longitud;
    }
    public DVector(Id id, T tipoDatos, List<Nodo> contenido, int fila, int columna) {
        super(fila,columna);
        this.id = id;
        this.tipoDatos = tipoDatos;
        this.longitud = new Entero(Integer.toString(contenido.size()),fila,columna);
        this.contenido = contenido;
    }
    
    // GET
    public Id getId() {
        return id;
    }
    public T getTipoDatos() {
        return tipoDatos;
    }
    public E getLongitud() {
        return longitud;
    }
    public List<Nodo> getContenido() {
        return contenido;
    }
    public TipoI getTipo() {
        return TipoI.DVECTOR;
    }
    // PRINT
    public String toString() {
        return "Vector";
    }
    public List<Nodo> getChildren() {
        ArrayList<Nodo> lista = new ArrayList<Nodo>();
        lista.add(id);
        lista.add(tipoDatos);
        if (longitud != null) {
            lista.add(new NodoAux("Longitud",longitud,fila,columna));
            if (contenido != null && !contenido.isEmpty()){
                lista.add(new NodoAux("Contenido",contenido,fila,columna));
            }
        }
        return lista;
    }
}
