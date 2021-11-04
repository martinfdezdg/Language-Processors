package ast.Expresiones;

import ast.*;
import ast.Instrucciones.*;
import ast.Tipos.*;
import java.util.List;
import java.util.ArrayList;

public class ADireccion extends E {
    private String nombre;
    private DVariable dVariable;
    
    public ADireccion(String nombre, int fila, int columna) {
        super(fila,columna);
        this.nombre = nombre;
    }
    
    // SET
    public void setVariable(DVariable dVariable) {
        this.dVariable = dVariable;
    }
    // GET
    public String getNombre() {
        return nombre;
    }
    public DVariable getVariable() {
        return dVariable;
    }
    public TipoE getTipo() {
        return TipoE.ADIR;
    }
    // PRINT
    public String toString() {
        return "Acceso direccion: " + nombre;
    }
}
