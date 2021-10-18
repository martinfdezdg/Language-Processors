package ast.Expresiones;

import ast.Nodo;
import java.util.List;
import java.util.ArrayList;

public class EBin extends E {
    private TipoE op;
    private E opnd1;
    private E opnd2;
    
    public EBin(TipoE op, E opnd1, E opnd2, int fila, int columna) {
        super(fila,columna);
        this.op = op;
        this.opnd1 = opnd1;
        this.opnd2 = opnd2;
    }
    
    // GET
    public E getOpnd1() {
        return opnd1;
    }
    public E getOpnd2() {
        return opnd2;
    }
    public TipoE getTipo() {
        return op;
    }
    // PRINT
    public String toString(){
        String str = "Operador: ";
        switch(this.op) {
            case OR:
                str += "or";
                break;
            case AND:
                str += "and";
                break;
            case IGUAL:
                str += "=";
                break;
            case DISTINTO:
                str += "><";
                break;
            case MENOR:
                str += "<";
                break;
            case MAYOR:
                str += ">";
                break;
            case MENORIGUAL:
                str += "<=";
                break;
            case MAYORIGUAL:
                str += ">=";
                break;
            case SUMA:
                str += "+";
                break;
            case RESTA:
                str += "-";
                break;
            case MULT:
                str += "*";
                break;
            case DIV:
                str += "/";
                break;
            case MOD:
                str += "%";
                break;
            default:
                str += "Unknown";
                break;
        }
        return str;
    }
    public List<Nodo> getChildren() {
        ArrayList<Nodo> lista = new ArrayList<Nodo>();
        lista.add(opnd1);
        lista.add(opnd2);
        return lista;
    }
}
