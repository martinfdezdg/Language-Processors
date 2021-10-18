package ast.Expresiones;

import ast.*;
import ast.Instrucciones.*;
import ast.Tipos.*;
import java.util.List;
import java.util.ArrayList;

public class Caracter extends E {
    private String nombre;
    
    public Caracter(String nombre, int fila, int columna) {
        super(fila,columna);
        this.nombre = nombre;
    }
    
    // GET
    public TipoE getTipo() {
        return TipoE.CAR;
    }
    // PRINT
    public String toString() {
        return "Valor: " + nombre;
    }
}
