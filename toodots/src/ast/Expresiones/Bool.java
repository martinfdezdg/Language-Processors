package ast.Expresiones;

import ast.*;
import ast.Instrucciones.*;
import ast.Tipos.*;
import java.util.List;
import java.util.ArrayList;

public class Bool extends E {
    private String nombre;
    
    public Bool(String nombre, int fila, int columna) {
        super(fila,columna);
        this.nombre = nombre;
    }
    
    // GET
    public TipoE getTipo() {
        if (this.nombre == "true")
            return TipoE.TRUE;
        else
            return TipoE.FALSE;
    }
    // PRINT
    public String toString() {
        return "Valor: " + nombre;
    }
}
