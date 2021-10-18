package ast.Instrucciones;

import ast.*;
import ast.Expresiones.*;
import ast.Tipos.*;
import java.util.List;
import java.util.ArrayList;

public class DEnum extends I {
    private Id id;
    private T tipoDatos;
    private List<Nodo> identificadores; //List<Id>
    
    public DEnum(Id id, List<Nodo> identificadores, int fila, int columna) {
        super(fila,columna);
        this.id = id;
        this.tipoDatos = new Tipo(TipoT.CUSTOM, id.getNombre());
        this.identificadores = identificadores;
    }
  
    // GET
    public Id getId() {
        return id;
    }
    public T getTipoDatos() {
        return tipoDatos;
    }
    public List<Nodo> getIdentificadores() {
        return identificadores;
    }
    public TipoI getTipo() {
        return TipoI.DENUM;
    }
    // PRINT
    public String toString() {
        return "Enum";
    }
    public List<Nodo> getChildren() {
        ArrayList<Nodo> lista = new ArrayList<Nodo>();
        lista.add(id);
        lista.add(new NodoAux("Identificadores",identificadores,fila,columna));
        /*
        for (int i = 0; i < identificadores.size(); ++i) {
            lista.add(identificadores.get(i));
        }
        */
        return lista;
    }
}
