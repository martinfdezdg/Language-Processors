package ast.Instrucciones;

import ast.*;
import ast.Expresiones.*;
import ast.Tipos.*;
import java.util.List;
import java.util.ArrayList;

public class DStruct extends I {
    private Id id;
    private T tipoDatos;
    private List<Nodo> atributos; //List<I> (I: DVar o DVector)
    
    public DStruct(Id id, List<Nodo> atributos, int fila, int columna) {
        super(fila,columna);
        this.id = id;
        this.tipoDatos = new Tipo(TipoT.CUSTOM, id.getNombre());
        this.atributos = atributos;
    }
    
    // GET
    public Id getId() {
        return id;
    }
    public T getTipoDatos() {
        return tipoDatos;
    }
    public List<Nodo> getAtributos() {
        return atributos;
    }
    public TipoI getTipo() {
        return TipoI.DSTRUCT;
    }
    // PRINT
    public String toString() {
        return "Struct";
    }
    public List<Nodo> getChildren() {
        ArrayList<Nodo> lista = new ArrayList<Nodo>();
        lista.add(id);
        lista.add(new NodoAux("Atributos",atributos,fila,columna));
        /*
        for (int i = 0; i < atributos.size(); ++i) {
            lista.add(atributos.get(i));
        }
        */
        return lista;
    }
}
