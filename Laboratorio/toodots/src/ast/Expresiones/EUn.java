package ast.Expresiones;

import ast.Nodo;
import java.util.List;
import java.util.ArrayList;

public class EUn extends E {
    private TipoE op;
    private E opnd;
    
    public EUn(TipoE op, E opnd, int fila, int columna) {
        super(fila,columna);
        this.op = op;
        this.opnd = opnd;
    }
    
    // GET
    public E getOpnd() {
        return opnd;
    }
    public TipoE getTipo() {
        return op;
    }
    // PRINT
    public String toString() {
        String str = "Operador: ";
        switch(this.op) {
            case NOT:
                str += "not";
                break;
            default:
                str += "Unknown";
                break;
        }
        return str;
    }
    public List<Nodo> getChildren() {
        ArrayList<Nodo> lista = new ArrayList<Nodo>();
        lista.add(opnd);
        return lista;
    }
}
