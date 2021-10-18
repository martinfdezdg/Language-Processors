package ast.Expresiones;

import ast.*;
import ast.Instrucciones.*;
import ast.Tipos.*;
import java.util.List;
import java.util.ArrayList;

public class Entero extends E {
    private String descripcion = "Valor: ";
    private String nombre;
    
    public Entero(String nombre, int fila, int columna) {
        super(fila,columna);
        this.nombre = nombre;
    }
    
    // SET
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    // GET
    public String getNombre() {
        return nombre;
    }
    public TipoE getTipo() {
        return TipoE.ENT;
    }
    // PRINT
    public String toString() {
        return descripcion + nombre;
    }
}
