package ast.Expresiones;

import ast.*;
import ast.Instrucciones.*;
import ast.Tipos.*;
import java.util.List;
import java.util.ArrayList;

public class APuntero extends E {
    private String nombre;
    private DPuntero dPuntero;
    
    public APuntero(String nombre, int fila, int columna) {
        super(fila,columna);
        this.nombre = nombre;
    }
    
    // SET
    public void setPuntero(DPuntero dPuntero) {
        this.dPuntero = dPuntero;
    }
    // GET
    public String getNombre() {
        return nombre;
    }
    public DPuntero getPuntero() {
        return dPuntero;
    }
    public TipoE getTipo() {
        return TipoE.APUNT;
    }
    // PRINT
    public String toString() {
        return "Acceso puntero: " + nombre;
    }
}
