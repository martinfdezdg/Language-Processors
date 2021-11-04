package ast.Tipos;

import ast.Nodo;
import java.util.ArrayList;
import java.util.List;

public abstract class T extends Nodo {
    public T() {
        super(0,0);
    }
    public T(int fila, int columna) {
        super(fila,columna);
    }
    public String getNombre() {return null;}
    public abstract TipoT getTipo();
    public abstract String toString();
    public List<Nodo> getChildren() {
        return new ArrayList<Nodo>();
    }

}
