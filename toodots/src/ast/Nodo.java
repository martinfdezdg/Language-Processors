package ast;

import java.util.List;
import java.util.ArrayList;

public abstract class Nodo {
    protected final String _cross = "├─ ";
    protected final String _corner = "└─ ";
    protected final String _vertical = "│  ";
    protected final String _space = "   ";
    protected int fila, columna;
    
    public Nodo(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }
    
    public String printNode(String indent) {
        String str = this.toString() + '\n';
        
        List<Nodo> children = this.getChildren();
        int numChildren = children.size();
        
        for (int i = 0; i < numChildren; ++i) {
            Nodo child = children.get(i);
            boolean isLast = (i == numChildren-1);
            str += child.printChildNode(indent,isLast);
        }
        return str;
    }
    
    public String printChildNode(String indent, boolean isLast) {
        String str = indent;
        if (isLast) {
            str += _corner;
            indent += _space;
        } else {
            str += _cross;
            indent += _vertical;
        }
        str += this.printNode(indent);
        return "  " + str;
    }
    
    public int getFila() {
        return fila;
    }
    public int getColumna() {
        return columna;
    }
    public abstract String toString();
    public abstract List<Nodo> getChildren();
}
