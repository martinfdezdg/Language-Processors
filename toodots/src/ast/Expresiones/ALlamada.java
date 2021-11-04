package ast.Expresiones;

import ast.*;
import ast.Instrucciones.*;
import ast.Tipos.*;
import java.util.List;
import java.util.ArrayList;

public class ALlamada extends E {
    private String nombre;
    private List<Nodo> argumentos;
    private DFuncion dFuncion;
    
    public ALlamada(String nombre, List<Nodo> argumentos, int fila, int columna) {
        super(fila,columna);
        this.nombre = nombre;
        this.argumentos = argumentos;
    }
    
    // SET
    public void setFuncion(DFuncion dFuncion) {
        this.dFuncion = dFuncion;
    }
    // GET
    public String getNombre() {
        return nombre;
    }
    public List<Nodo> getArgumentos() {
        return argumentos;
    }
    public DFuncion getFuncion() {
        return dFuncion;
    }
    public TipoE getTipo() {
        return TipoE.ALLAM;
    }
    // PRINT
    public String toString() {
        return "Llamada: " + nombre;
    }
    public List<Nodo> getChildren() {
        return argumentos;
    }
}
