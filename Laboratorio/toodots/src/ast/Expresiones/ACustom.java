package ast.Expresiones;

import ast.*;
import ast.Instrucciones.*;
import ast.Tipos.*;
import java.util.List;
import java.util.ArrayList;

public class ACustom extends E {
    private String nombre = null;
    private E acceso = null;
    private Id atributo;
    private DStruct dStruct = null;
    private DEnum dEnum = null;
    
    public ACustom(String nombre, Id atributo, int fila, int columna) {
        super(fila,columna);
        this.nombre = nombre;
        this.atributo = atributo;
    }
    public ACustom(E acceso, Id atributo, int fila, int columna) {
        super(fila,columna);
        this.acceso = acceso;
        this.atributo = atributo;
    }
    
    // SET
    public void setCustom(DStruct dStruct) {
        this.dStruct = dStruct;
    }
    public void setCustom(DEnum dEnum) {
        this.dEnum = dEnum;
    }
    // GET
    public String getNombre() {
        return nombre;
    }
    public E getAcceso() {
        return acceso;
    }
    public Id getAtributo() {
        return atributo;
    }
    public DStruct getStruct() {
        return dStruct;
    }
    public DEnum getEnum() {
        return dEnum;
    }
    public TipoE getTipo() {
        return TipoE.ACUSTOM;
    }
    // PRINT
    public String toString() {
        if (nombre != null) {
            return "Acceso custom: " + nombre;
        } else {
            return "Acceso custom";
        }
    }
    public List<Nodo> getChildren() {
        ArrayList<Nodo> lista = new ArrayList<Nodo>();
        if (acceso != null) {
            lista.add(acceso);
        }
        lista.add(atributo);
        return lista;
    }
}
