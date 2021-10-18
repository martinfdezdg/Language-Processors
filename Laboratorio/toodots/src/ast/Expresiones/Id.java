package ast.Expresiones;

import ast.*;
import ast.Instrucciones.*;
import ast.Tipos.*;
import java.util.List;
import java.util.ArrayList;

public class Id extends E {
    private String nombre;
    private T tipoDatos;
    private boolean esPuntero = false;
    
    public Id(String nombre, int fila, int columna) {
        super(fila,columna);
        this.nombre = nombre;
    }
    
    // SET
    public void setTipoDatos(T tipoDatos) {
        this.tipoDatos = tipoDatos;
    }
    public void setEsPuntero() {
        this.esPuntero = true;
    }
    // GET
    public String getNombre() {
        return nombre;
    }
    public T getTipoDatos() {
        return tipoDatos;
    }
    public boolean getEsPuntero() {
        return esPuntero;
    }
    public TipoE getTipo() {
        return TipoE.ID;
    }
    // PRINT
    public String toString() {
        return "Id: " + nombre;
    }
}
