package ast.Instrucciones;

import ast.Nodo;
import java.util.ArrayList;
import java.util.List;

public abstract class I extends Nodo {
    
    public I(int fila, int columna) {
        super(fila,columna);
    }
    
    public abstract TipoI getTipo();
    public abstract String toString();
    public List<Nodo> getChildren() {return new ArrayList<Nodo>();}
}
