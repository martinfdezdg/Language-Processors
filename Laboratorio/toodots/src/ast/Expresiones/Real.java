package ast.Expresiones;

import ast.*;
import ast.Instrucciones.*;
import ast.Tipos.*;
import java.util.List;
import java.util.ArrayList;

public class Real extends E {
    private String nombre;
    
    public Real(String nombre, int fila, int columna) {
        super(fila,columna);
        this.nombre = nombre;
    }
    
    // GET
    public TipoE getTipo() {
        return TipoE.REAL;
    }
    // PRINT
    public String toString() {
        return "Valor: " + nombre;
    }
}
