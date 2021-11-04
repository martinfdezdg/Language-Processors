package ast;

import java.util.List;
import java.util.ArrayList;

import ast.Instrucciones.*;
import ast.Expresiones.*;
import ast.Tipos.*;

public class P extends Nodo {
    private List<Nodo> objetos, proceso = null;
    private int filaObj, columnaObj;
    private int filaPro, columnaPro;
    
    public P(List<Nodo> objetos, int filaObj, int columnaObj, List<Nodo> proceso, int filaPro, int columnaPro) {
        super (0,0);
        this.objetos = objetos;
        this.filaObj = filaObj;
        this.columnaObj = columnaObj;
        this.proceso = proceso;
        this.filaPro = filaPro;
        this.columnaPro = columnaPro;
    }
    public P(List<Nodo> objetos, int filaObj, int columnaObj) {
        super (0,0);
        this.objetos = objetos;
        this.filaObj = filaObj;
        this.columnaObj = columnaObj;
    }
    
    public String start() {
        return this.printNode("");
    }
    public List<Nodo> getObjetos() {
        return objetos;
    }
    public List<Nodo> getProceso() {
        return proceso;
    }
    public List<Nodo> getChildren() {
        ArrayList<Nodo> lista = new ArrayList<Nodo>();
        lista.add(new NodoAux("Objetos",objetos,filaObj,columnaObj));
        if (proceso != null) {
            lista.add(new NodoAux("Proceso",proceso,filaPro,columnaPro));
        }
        return lista;
    }
    public String toString() {
        if (proceso == null) {
            return "  " + "Clase";
        }
        else {
            return "  " + "Programa";
        }
    }
}
