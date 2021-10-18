package ast.Expresiones;

import ast.*;
import ast.Instrucciones.*;
import ast.Tipos.*;
import java.util.List;
import java.util.ArrayList;

public class AVector extends E {
    private String nombre = null;
    private E acceso = null;
    private E posicion;
    private DVector dVector;
    
    public AVector(String nombre, E posicion, int fila, int columna) {
        super(fila,columna);
        this.nombre = nombre;
        this.posicion = posicion;
    }
    public AVector(E acceso, E posicion, int fila, int columna) {
        super(fila,columna);
        this.acceso = acceso;
        this.posicion = posicion;
    }
    
    // SET
    public void setVector(DVector dVector) {
        this.dVector = dVector;
    }
    // GET
    public String getNombre() {
        return nombre;
    }
    public E getAcceso() {
        return acceso;
    }
    public E getPosicion() {
        return posicion;
    }
    public DVector getVector() {
        return dVector;
    }
    public TipoE getTipo() {
        return TipoE.AVECTOR;
    }
    // PRINT
    public String toString() {
        if (nombre != null) {
            return "Vector: " + nombre;
        } else {
            return "Vector";
        }
    }
    public List<Nodo> getChildren() {
        ArrayList<Nodo> lista = new ArrayList<Nodo>();
        if (acceso != null) {
            lista.add(acceso);
        }
        lista.add(new NodoAux("Posicion",posicion,this.fila,this.columna));
        return lista;
    }
}
