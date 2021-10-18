package ast.Expresiones;

import ast.*;
import ast.Instrucciones.*;
import ast.Tipos.*;
import java.util.List;
import java.util.ArrayList;

public abstract class E extends Nodo {
    public E(int fila, int columna) {
        super(fila,columna);
    }
    public abstract TipoE getTipo();
    public E getOpnd1() {throw new UnsupportedOperationException("opnd1");}
    public E getOpnd2() {throw new UnsupportedOperationException("opnd2");}
    public String toString() {throw new UnsupportedOperationException("toString");}
    public List<Nodo> getChildren() {return new ArrayList<Nodo>();}
}
